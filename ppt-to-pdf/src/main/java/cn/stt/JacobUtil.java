package cn.stt;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/8.
 */
public class JacobUtil {
    private static final Integer PPT_TO_PDF_OPERAND = 32;

    public static void main(String[] args) throws Exception {
        String pptfile = "D:\\testoffice2pdf\\高三英语秋季精品课4.pptx";
        String pptfile2 = "D:\\testoffice2pdf\\高三英语秋季精品课4-2.pptx";
        String pdffile = "D:\\testoffice2pdf\\高三英语秋季精品课4.pdf";
        String pdffile2 = "D:\\testoffice2pdf\\高三英语秋季精品课4-2.pdf";
        new Thread(() -> {
            try {
                ppt2pdf2(pptfile, pdffile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                ppt2pdf2(pptfile2, pdffile2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    public static void ppt2pdf2(String srcFilePath, String pdfFilePath) throws Exception {
        ActiveXComponent app = null;
        Dispatch ppt = null;
        try {
            ComThread.InitSTA();
            app = new ActiveXComponent("PowerPoint.Application");
            Dispatch ppts = app.getProperty("Presentations").toDispatch();

            /*
             * call
             * param 4: ReadOnly
             * param 5: Untitled指定文件是否有标题
             * param 6: WithWindow指定文件是否可见
             * */
            ppt = Dispatch.call(ppts, "Open", srcFilePath, true, true, false).toDispatch();
            Dispatch.call(ppt, "SaveAs", pdfFilePath, PPT_TO_PDF_OPERAND); // ppSaveAsPDF为特定值32

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ppt != null) {
                Dispatch.call(ppt, "Close");
            }
            if (app != null) {
                app.invoke("Quit");
            }
            ComThread.Release();
        }
    }

}
