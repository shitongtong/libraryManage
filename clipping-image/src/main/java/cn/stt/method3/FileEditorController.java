//package cn.stt.method3;
//
//import java.io.IOException;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//
////import com.quanshi.ums.gate.persistence.entities.Parameters;
////import com.quanshi.ums.gate.view.rest.ImgEditor;
//
///**
// * Created by Administrator on 2016-09-28.
// */
///**
// * 图像上传和修改相关类
// * @author kunpeng.zhao
// *
// */
//@Controller
//@RequestMapping(value="/file")
//public class FileEditorController {
//    ImgEditor imgEditor = new ImgEditor();
//    public String filePathFinal = "";
//    private Logger logger = LoggerFactory.getLogger(FileEditorController.class);
//    @RequestMapping(value="/img/cutandscale",method=RequestMethod.POST)
//    public @ResponseBody int cutAndscaleimg(
//            @RequestParam("w") int w,
//    @RequestParam("h") int h,
//    @RequestParam("x") int x,
//    @RequestParam("y") int y
//    ){
//        imgEditor.cut(filePathFinal,filePathFinal,x,y,w,h);
//        imgEditor.scale(filePathFinal, filePathFinal, 110, 110, false);
//        return 1;
//        }
//
//
//    @RequestMapping(value="/img/upload",method=RequestMethod.POST)
//    public @ResponseBody Parameters addImage(
//            @RequestParam("userphoto") MultipartFile file,
//            HttpServletRequest request,
//            HttpServletResponse response,
//            HttpSession session
//            ){
//        String filePath = "";
//        try {
//            //上传原图
//            filePath = imgEditor.uploadFile(file, request,session);
//            filePathFinal = filePath;
//            //将图片压缩成指定大小
//            imgEditor.zoomImage(filePath,filePath,400,400);
//            } catch (IOException e) {
//            e.printStackTrace();
//            }
//        logger.info("filePath:" + filePath);
//        Parameters parameter = new Parameters();
//        parameter.setFileName(imgEditor.getFileName(file,request,session));
//        return parameter;
//    }
//}
