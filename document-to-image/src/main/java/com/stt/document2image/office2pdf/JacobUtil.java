package com.stt.document2image.office2pdf;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Cleaner;

import java.io.File;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 失败！！！
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/8.
 */
@Deprecated
public class JacobUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacobUtil.class);

    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String PDF = "pdf";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String MP4 = "mp4";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";

    // 8 代表word保存成html
    public static final int WORD2HTML = 8;
    // 17代表word保存成pdf
    public static final int WD2PDF = 17;
    public static final int PPT2PDF = 32;
    public static final int XLS2PDF = 0;

    public static void main(String[] args) {
        String pptfile = "D:\\testoffice2pdf\\test2.pptx";
//        pptfile = "D:\\testoffice2pdf\\Java基础PPT.ppt";
        String wordfile = "D:\\testoffice2pdf\\test4.doc";
        String pdffile = "D:\\testoffice2pdf\\office2pdf2\\test4.pdf";
//        ppt2pdf(pptfile,pdffile);
//        pdf2Image(pdffile);
        word2pdf(wordfile,pdffile);
    }

    /**
     * @author shenjianhu:
     * @version 创建时间：2017年4月8日 下午9:07:33
     * @param resourceType 资源类型
     * @param resourcePath 资源路径
     * @return
     * TODO 文件转换
     */
    public static Integer formatConvert(String resourceType, String resourcePath) {
        Integer pages = 0;
        String resource = resourcePath.substring(0, resourcePath.lastIndexOf("."));
        if(resourceType.equalsIgnoreCase(DOC)||resourceType.equalsIgnoreCase(DOCX)){
            //word转成pdf和图片
            word2pdf(resourcePath, resource+".pdf");
            pages = pdf2Image(resource+".pdf");
        }else if(resourceType.equalsIgnoreCase(PDF)){
            //pdf转成图片
            pages = pdf2Image(resourcePath);
        }else if(resourceType.equalsIgnoreCase(XLS)||resourceType.equalsIgnoreCase(XLSX)){
            //excel文件转成图片
            excel2pdf(resourcePath, resource+".pdf");
            pages = pdf2Image(resource+".pdf");
        }else if(resourceType.equalsIgnoreCase(PPT)||resourceType.equalsIgnoreCase(PPTX)){
            //ppt2pdf(resourcePath, resource+".pdf");
            //pages = pdf2Image(resource+".pdf");
            pages = ppt2Image(resourcePath, resource+".jpg");
        }else if(resourceType.equalsIgnoreCase(MP4)){
            //视频文件不转换
            pages = 0;
        }
        return pages;
    }

    /**
     * @author shenjianhu:
     * @version 创建时间：2017年4月18日 下午3:08:11
     * @param pptfile
     * @param imgfile
     * TODO  ppt转换成图片
     */
    public static Integer ppt2Image(String pptfile,String imgfile){
        String imageDir = pptfile.substring(0, pptfile.lastIndexOf("."));
        File dir = new File(imageDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        int length = 0;
        ActiveXComponent app = null;
        try{
            ComThread.InitSTA();
            app = new ActiveXComponent("PowerPoint.Application");
            System.out.println("准备打开ppt文档");
            app.setProperty("Visible", true);
            Dispatch ppts = app.getProperty("Presentations").toDispatch();
            Dispatch ppt = Dispatch.call(ppts, "Open", pptfile, true, true, true).toDispatch();
            System.out.println("-----------------ppt开始转换图片---------------");
            Dispatch.call(ppt, "SaveCopyAs", imgfile, 17);
            System.out.println("-----------------ppt转换图片结束---------------");
            Dispatch.call(ppt, "Close");
            System.out.println("关闭ppt文档");
        }catch(Exception e){
            ComThread.Release();
            e.printStackTrace();
        }finally{
            String files[];
            files = dir.list();
            length = files.length;
            System.out.println(length);
            app.invoke("Quit");
            ComThread.Release();
        }
        return length;
    }

    /**
     * WORD转HTML
     * @param docfile WORD文件全路�?
     * @param htmlfile 转换后HTML存放路径
     */
    public static void wordToHtml(String docfile, String htmlfile)
    {
        // 启动word应用程序(Microsoft Office Word 2003)
        ActiveXComponent app = null;
        System.out.println("*****正在转换...*****");
        try
        {
            ComThread.InitSTA();
            app = new ActiveXComponent("Word.Application");
            // 设置word应用程序不可�?
            app.setProperty("Visible", new Variant(false));
            // documents表示word程序的所有文档窗口，（word是多文档应用程序�?
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 打开要转换的word文件
            Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[] { docfile, new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch();
            // 作为html格式保存到临时文�?
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    htmlfile, new Variant(WORD2HTML) }, new int[1]);
            // 关闭word文件


            Dispatch.call(doc, "Close", new Variant(false));
        }
        catch (Exception e)
        {
            ComThread.Release();
            e.printStackTrace();
        }
        finally
        {
            //关闭word应用程序
            app.invoke("Quit", new Variant[] {});
            ComThread.Release();
        }
        System.out.println("*****转换完毕********");
    }

    public static void word2pdf(String docfile, String pdffile)
    {
        // 启动word应用程序(Microsoft Office Word 2003)
        ActiveXComponent app = null;
        try{
            ComThread.InitSTA();
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);
            System.out.println("*****正在转换...*****");
            // 设置word应用程序不可见
            // app.setProperty("Visible", new Variant(false));
            // documents表示word程序的所有文档窗口，（word是多文档应用程序�?
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 打开要转换的word文件
           /* Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[] { docfile, new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch(); */

            Dispatch doc = Dispatch.call(
                    docs,
                    "Open",
                    docfile,
                    false,
                    true).toDispatch();
            // 调用Document对象的saveAs方法,将文档保存为pdf格式
            /*Dispatch.invoke(doc, "ExportAsFixedFormat", Dispatch.Method, new Object[] {
                    pdffile, new Variant(wdFormatPDF) }, new int[1]);*/

            Dispatch.call(doc, "ExportAsFixedFormat", pdffile, WD2PDF);
            // 关闭word文件
            Dispatch.call(doc, "Close", false);
        }
        catch (Exception e)
        {   ComThread.Release();
            e.printStackTrace();
        }
        finally
        {
            //关闭word应用程序
            app.invoke("Quit", 0);
            ComThread.Release();
        }
        System.out.println("*****转换完毕********");
    }

    /**
     * @author shenjianhu:
     * @version 创建时间：2016年11月16日 下午8:21:29
     * @param pdffile
     * TODO pdf文件按页转成图片
     */
    @Deprecated
    public static int pdf2Image(String pdffile){
        File file = new File(pdffile);
        int pages = 0;
        /*try {
            ComThread.InitSTA();
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            java.nio.ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdf = new PDFFile(buf);
            pages = pdf.getNumPages();
            System.out.println("页数："+pdf.getNumPages());
            File direct = new File(pdffile.substring(0, pdffile.lastIndexOf(".")));
            if(!direct.exists()){
                direct.mkdir();
            }
            for(int i=1;i<=pdf.getNumPages();i++){
                PDFPage page = pdf.getPage(i);
                Rectangle rect = new Rectangle(0, 0, (int)(page.getBBox().getWidth()), (int)(page.getBBox().getHeight()));
                int width = (int) (rect.getWidth()*2);
                int height = (int) (rect.getHeight()*2);
                Image image = page.getImage(width, height, rect, null, true, true);
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(image, 0, 0, width, height, null);
                FileOutputStream out = new FileOutputStream(direct+"/幻灯片"+i+".JPG");
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
                param.setQuality(1f, false);
                encoder.setJPEGEncodeParam(param);
                encoder.encode(tag);
                out.close();
                System.out.println("image in the page -->"+i);
            }
            buf.clear();
            channel.close();
            raf.close();
            unmap(buf);
        } catch (Exception e) {
            ComThread.Release();
            e.printStackTrace();
        } finally{
            ComThread.Release();
        }*/
        return pages;
    }

    /**
     * @author shenjianhu:
     * @version 创建时间：2016年12月19日 上午11:25:22
     * @param buffer
     * TODO pdf转成图片时解除映射，以便后面删除文件时能够删除pdf文件
     */



    public static <T> void unmap(final Object buffer){
        AccessController.doPrivileged(new PrivilegedAction<T>() {
            @Override
            public T run() {
                try{
                    Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                    cleaner.clean();
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    public static void ppt2pdf(String pptfile,String pdffile){
        LOGGER.debug("打开ppt应用");
        ActiveXComponent app = null;
        LOGGER.debug("设置可见性");
        //app.setProperty("Visible", new Variant(false));
        LOGGER.debug("打开ppt文件");
        try {
            ComThread.InitSTA();
            app = new ActiveXComponent("PowerPoint.Application");
            Dispatch files = app.getProperty("Presentations").toDispatch();
            Dispatch file = Dispatch.call(files, "open", pptfile, false, true).toDispatch();
            LOGGER.debug("保存为图片");
            Dispatch.call(file, "SaveAs", pdffile, PPT2PDF);
            LOGGER.debug("关闭文档");
            Dispatch.call(file,"Close");
        } catch (Exception e) {
            ComThread.Release();
            e.printStackTrace();
            LOGGER.error("ppt to images error",e);
            //throw e;
        }finally{
            LOGGER.debug("关闭应用");
            app.invoke("Quit");
            ComThread.Release();
        }
    }

    public static void excel2pdf(String excelfile,String pdffile){
        ActiveXComponent app = null;
        try{
            ComThread.InitSTA(true);
            app = new ActiveXComponent("Excel.Application");
            app.setProperty("Visible", false);
            app.setProperty("AutomationSecurity", new Variant(3));//禁用宏
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            /*Dispatch excel = Dispatch.invoke(excels, "Open", Dispatch.Method, new Object[]{
                    excelfile,
                    new Variant(false),
                    new Variant(false),
            },new int[9]).toDispatch();*/
            Dispatch excel = Dispatch.call(excels, "Open",
                    excelfile,false,true).toDispatch();
            //转换格式ExportAsFixedFormat
            /*Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method, new Object[]{
                    new Variant(0),//pdf格式=0
                    pdffile,
                    new Variant(0)//0=标准(生成的pdf图片不会变模糊) 1=最小文件(生成的pdf图片模糊的一塌糊涂)
            }, new int[1]);*/
            Dispatch.call(excel, "ExportAsFixedFormat",XLS2PDF,
                    pdffile);
            Dispatch.call(excel, "Close", false);
            if(app!=null){
                app.invoke("Quit");
                app=null;
            }
        }catch(Exception e){
            ComThread.Release();
            e.printStackTrace();
        }finally{
            ComThread.Release();
        }
    }

    public static void ppt2html(String pptfile,String htmlfile){
        ActiveXComponent app = null;
        try{
            ComThread.InitSTA(true);
            app = new ActiveXComponent("PowerPoint.Application");
            //app.setProperty("Visible", false);
            app.setProperty("AutomationSecurity", new Variant(3));//禁用宏
            Dispatch dispatch = app.getProperty("Presentations").toDispatch();
            Dispatch dispatch1 = Dispatch.call(dispatch, "Open",
                    pptfile,false,true).toDispatch();
            Dispatch.call(dispatch1, "SaveAs",
                    htmlfile,new Variant(12));
            Dispatch.call(dispatch1, "Close", false);
            if(app!=null){
                app.invoke("Quit");
                app=null;
            }
        }catch(Exception e){
            ComThread.Release();
            e.printStackTrace();
        }finally{
            ComThread.Release();
        }
    }
}
