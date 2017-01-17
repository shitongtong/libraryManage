package com.stt.document2image.pdf2image;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017-01-10.
 *
 * @author shitongtong
 */
public class Pdf2Image {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\document2image\\准妈妈孕早期注意事项.pdf";
        File file = new File(filePath);
        String fileNameNoSuffix = file.getName().substring(0, file.getName().indexOf("."));
        String savePath = file.getParent() + File.separator + fileNameNoSuffix;
        System.out.println("savePath=="+savePath);
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        System.out.println("pdf pageSize=="+pageCount);
        for(int i=0;i<pageCount;i++){
            BufferedImage image = renderer.renderImageWithDPI(i, 296);
            String filename = saveDir + File.separator + fileNameNoSuffix + "_" + (i + 1) + ".png";
            ImageIO.write(image, "PNG", new File(filename));
        }
        doc.close();

        System.out.println("pdf2image success");
    }
}
