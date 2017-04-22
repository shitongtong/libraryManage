package com.stt.document2image.word2image;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2017-01-10.
 *
 * @author shitongtong
 */
public class Word2Html {

    public static void main(String argv[]) {
        try {
//            convert2Html("D:\\document2image\\注意事项.doc","D:\\document2image\\注意事项.html");
//            convert2Html("D:\\workDir\\服务条款.doc","D:\\document2image\\服务条款.html");
//            convert2Html("D:\\workDir\\pass_mail_template_.docx","D:\\document2image\\pass_mail_template_.html");
//            word2007ToHtml("D:\\document2image\\test.docx","D:\\document2image\\test.html");
            word2007ToHtml("D:\\document2image\\报名邮件 (1).docx","D:\\document2image\\报名邮件 (1).html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void word2007ToHtml(String fileName, String outPutFileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

                // 1) Load DOCX into XWPFDocument
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                // 2) Prepare XHTML options (here we set the IURIResolver to
                // load images from a "word/media" folder)
                File imageFolderFile = new File("D:/document2image/");
                XHTMLOptions options = XHTMLOptions.create().URIResolver(
                        new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                //options.setIgnoreStylesIfUnused(false);
                //options.setFragment(true);

                // 3) Convert XWPFDocument to XHTML
                OutputStream out = new FileOutputStream(new File(outPutFileName));
                XHTMLConverter.getInstance().convert(document, out, options);
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }

    //输出html文件
    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;

        BufferedWriter bw = null;
        org.jsoup.nodes.Document doc = Jsoup.parse(content);
        content=doc.html();
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"gb2312")); //UTF-8
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }

    //word 转 html
    public static void convert2Html(String fileName, String outPutFile)
            throws TransformerException, IOException,
            ParserConfigurationException {

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
        //兼容2007 以上版本
//        XWPFDocument wordDocument = new XWPFDocument(new FileInputStream(fileName));
//        XSSFWorkbook xssfwork=new XSSFWorkbook(new FileInputStream(fileName));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.setPicturesManager( new PicturesManager()
        {
            public String savePicture(byte[] content,
                                      PictureType pictureType, String suggestedName,
                                      float widthInches, float heightInches )
            {
                return "test/"+suggestedName;
            }
        } );
        wordToHtmlConverter.processDocument(wordDocument);
        //save pictures
        List pics=wordDocument.getPicturesTable().getAllPictures();
        if(pics!=null){
            for(int i=0;i<pics.size();i++){
                Picture pic = (Picture)pics.get(i);
                System.out.println();
                try {
                    pic.writeImageContent(new FileOutputStream("D:/document2image/"
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //UTF-8
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
        serializer.transform(domSource, streamResult);
        out.close();
        writeFile(new String(out.toByteArray()), outPutFile);
    }
}
