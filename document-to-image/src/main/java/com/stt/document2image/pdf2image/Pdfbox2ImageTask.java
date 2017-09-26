package com.stt.document2image.pdf2image;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/31.
 */
public class Pdfbox2ImageTask extends RecursiveAction {
    private static final long serialVersionUID = -3611254198265061729L;

    public static final int threshold = 50;
    private PDFRenderer renderer;
    private String savePath;
    private String fileNameNoSuffix;
    private int start;
    private int end;

    public Pdfbox2ImageTask(PDFRenderer renderer, String savePath, String fileNameNoSuffix, int start, int end) {
        this.renderer = renderer;
        this.savePath = savePath;
        this.fileNameNoSuffix = fileNameNoSuffix;
        this.start = start;
        this.end = end;
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected void compute() {

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) < threshold;
        if (canCompute) {
            for (int i = start; i < end; i++) {
                BufferedImage image = null;  //296   200:144s; 100:55s; 75:42s   50:28s
                try {
                    image = renderer.renderImageWithDPI(i, 100);
                    String filename = savePath + File.separator + fileNameNoSuffix + "_" + (i + 1) + ".jpg";
                    ImageIO.write(image, "PNG", new File(filename));    //正常生成图片
                } catch (IOException e) {
                    e.printStackTrace();
                }
                atomicInteger.incrementAndGet();
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            Pdfbox2ImageTask leftTask = new Pdfbox2ImageTask(renderer, savePath, fileNameNoSuffix, start, middle);
            Pdfbox2ImageTask rightTask = new Pdfbox2ImageTask(renderer, savePath, fileNameNoSuffix, middle, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待任务执行结束合并其结果
            leftTask.join();
            rightTask.join();

        }
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        String filePath = "D:\\testoffice2pdf\\courseware\\pdf\\Java基础PPT.pdf";
        filePath = "D:\\testoffice2pdf\\courseware\\pdf\\第3章第1节　钠及其重要化合物.pdf";
        filePath = "D:\\testoffice2pdf\\courseware\\pdf\\高三英语秋季精品课4.pdf";

        File file = new File(filePath);
        String fileNameNoSuffix = file.getName().substring(0, file.getName().indexOf("."));
        String savePath = file.getParent() + File.separator + fileNameNoSuffix;
        System.out.println("savePath==" + savePath);
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Pdfbox2ImageTask task = new Pdfbox2ImageTask(renderer, savePath, fileNameNoSuffix, 0, pageCount);
        //执行一个任务
        forkJoinPool.submit(task);
        task.join();
//        forkJoinPool.awaitTermination(30, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        // 关闭线程池
        forkJoinPool.shutdown();

        doc.close();
        System.out.println("pageCount==" + pageCount);
        System.out.println("atomicInteger==" + atomicInteger);
        System.out.println("pdf2image success");

        long endTime = System.currentTimeMillis();
        System.out.println("转换时间：" + (endTime - startTime) / 1000 + "s");
    }

}
