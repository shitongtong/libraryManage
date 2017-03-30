package cn.stt.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/23.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        String filePath = "d:/testoffice2pdf/test2.pptx";
//        filePath = "d:/testoffice2pdf/test3.txt";
        filePath = "d:/testoffice2pdf/test4.docx";
        DocConverter docConverter = new DocConverter(filePath);
        docConverter.doc2pdf();
    }
}
