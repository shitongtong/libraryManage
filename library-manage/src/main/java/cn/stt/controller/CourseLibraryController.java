//package cn.stt.controller;
//
//import cn.bblink.pager.mybatis.Page;
//import cn.bblink.platform.base.BaseController;
//import cn.bblink.platform.transfer.school.ClassroomTransferClassroomVo;
//import cn.bblink.platform.transfer.school.CourseScheduleTransferCourseVo2;
//import cn.bblink.platform.transfer.school.CourseTransferCourseVo;
//import cn.bblink.platform.transfer.school.LecturerTransferLecturerVo;
//import cn.bblink.platform.vo.school.*;
//import cn.bblink.que.dto.QuePaper;
//import cn.bblink.que.service.QueService;
//import cn.bblink.school.dto.CourseTestPaperDto;
//import cn.bblink.school.po.*;
//import cn.bblink.school.service.CourseService;
//import cn.bblink.school.service.LecturerService;
//import cn.bblink.school.service.SchoolService;
//import com.google.common.collect.Lists;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.xslf.usermodel.XSLFSlide;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.*;
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.List;
//
///**
// * Created by Administrator on 2016-07-11.
// */
//@Controller
//@RequestMapping("/school/courselibrary")
//public class CourseLibraryController extends BaseController {
//
//    @Autowired
//    private CourseService courseService;
//    @Autowired
//    private LecturerService lecturerService;
//    @Autowired
//    private SchoolService schoolService;
//    @Autowired
//    private QueService queService;
//
//    @Autowired
//    private CourseScheduleTransferCourseVo2 courseScheduleTransferCourseVo2;
//    @Autowired
//    private CourseTransferCourseVo courseTransferCourseVo;
//    @Autowired
//    private LecturerTransferLecturerVo lecturerTransferLecturerVo;
//    @Autowired
//    private ClassroomTransferClassroomVo classroomTransferClassroomVo;
//
//    @RequestMapping("list")
//    public String list(Model model, HttpServletRequest request, HttpServletResponse response,Integer pageNo, Integer pageSize){
//        Map paramMap = getParamMap(request);
//        Page page = new Page(request,response);
//        page = courseService.getCourseList(paramMap, page);
//        page.initialize();
//        List<Course> courseList = page.getList();
//        List<CourseVo> courseVoList = courseTransferCourseVo.convert(courseList);
//        page.setList(courseVoList);
//
////        model.addAttribute("courseVoList",courseVoList);
////        model.addAttribute("pageNo",page.getPageNo());
////        model.addAttribute("totalPage",page.getLast());
//        model.addAttribute("page",page);
//        model.addAttribute("paramMap",paramMap);
//
//        getCLSList(request,response,model);
//
//        return "/courselibrary/list";
//    }
//
//    /**
//     * 获取课程、讲师、教室列表
//     * @param model
//     * @return
//     */
//    private void getCLSList(HttpServletRequest request, HttpServletResponse response,Model model){
//        Map paramMap = new HashMap();
////        String schoolId = getSchoolId(request,response);
//        String schoolId = "";
//        paramMap.put("schoolId",schoolId);
//        //课程列表
//        Page<Course> coursePage = courseService.getCourseList(paramMap, new Page());
//        List<Course> courseAllList = coursePage.getList();
//        List<CourseVo> courseAllVoList = courseTransferCourseVo.convert(courseAllList);
//        model.addAttribute("courseAllVoList",courseAllVoList);
//        //讲师列表
//        List<Lecturer> lecturerList = lecturerService.getLecturerList(paramMap);
//        List<LecturerVo> lecturerVoList = lecturerTransferLecturerVo.convert(lecturerList);
//        model.addAttribute("lecturerVoList",lecturerVoList);
//        //教室列表
//        List<SchoolClassroom> classroomList = schoolService.getClassRooms(schoolId);
//        List<ClassroomVo> classroomVoList = classroomTransferClassroomVo.convert(classroomList);
//        model.addAttribute("classroomVoList",classroomVoList);
//
//    }
//
//    @RequestMapping("add")
//    public String toAdd(HttpServletRequest request,HttpServletResponse response,Model model){
//        List<Integer> weekList = new ArrayList<Integer>();
//        for (int i = 1;i<=40;i++){
//            weekList.add(i);
//        }
//        model.addAttribute("weekList",weekList);
////        String schoolId = getSchoolId(request, response);
//        String schoolId = "";
//        model.addAttribute("schoolId",schoolId);
//        return "/courselibrary/add";
//    }
//
//    @RequestMapping("tocourseopen")
//    public String toCourseopen(HttpServletRequest request,HttpServletResponse response,String courseId,Model model){
//        Course courseInfo = courseService.getCourseInfo(courseId);
//        CourseVo courseVo = courseTransferCourseVo.convert(courseInfo);
//        model.addAttribute("courseVo",courseVo);
//
//        getCLSList(request,response,model);
//
//        return "/courselibrary/courseopen";
//    }
//
//    @RequestMapping("courseopen")
//    public String courseopen(HttpServletRequest request, HttpServletResponse response, CourseVo2 vo, Model model){
//
////        String schoolId = getSchoolId(request, response);
//        String schoolId = "";
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        List<CourseSchedule> courseScheduleList = null;
//        try {
//            String format = dateFormat.format(new Date(vo.getStartTime()));
//            Date date = dateFormat.parse(format);
//            long startTime = date.getTime();
//            long endTime = startTime + 24*3600*1000-1;
//            Map<String,Object> paramMap = new HashMap<String,Object>();
//            /*paramMap.put("startTimeNow",startTime);
//            paramMap.put("endTimeNow",endTime);*/
//            paramMap.put("schoolId",schoolId);
//            paramMap.put("status","NORMAL");
//            courseScheduleList = courseService.getSchedules(paramMap);//找出提交的上课日期当天的所有排班
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        if(courseScheduleList != null && courseScheduleList.size() > 0){
//            long startTimeNow = vo.getStartTime().longValue();
//            long endTimeNow = vo.getEndTime().longValue();
//            for (CourseSchedule courseSchedule : courseScheduleList){
//                long startTime = courseSchedule.getStartTime().longValue();
//                long endTime = courseSchedule.getEndTime().longValue();
//                if((startTimeNow >= startTime && startTimeNow <= endTime) || (endTimeNow >= startTime && endTimeNow <= endTime)
//                        ||(startTimeNow < startTime && endTimeNow > endTime)){//上课时间已存在
//                    model.addAttribute("courseVo",vo);
//                    getCLSList(request,response,model);
//                    model.addAttribute("error","此开课时间段已存在课程，请重新选择");
//                    return "/courselibrary/courseopen";
//                }
//            }
//        }
//
//        CourseSchedule courseSchedule = courseScheduleTransferCourseVo2.inverseConvert(vo);
////        String loginUid = getLoginUid(request, response);
//        String loginUid = "";
//        courseSchedule.setCreateUid(loginUid);
//        courseService.addCourseSchedule(courseSchedule);
//        return "redirect:/courselibrary/list.do";
//    }
//
//    @RequestMapping("view")
//    public String view(HttpServletRequest request,String courseId,Model model){
//        Course courseInfo = courseService.getCourseInfo(courseId);
//        CourseVo courseVo = courseTransferCourseVo.convert(courseInfo);
//        model.addAttribute("courseVo",courseVo);
//
//        //课程素材
//        List<CourseWare> courseWareList = courseService.getCourseWares(courseId);
//        List<CourseWareVo> courseWareVoList = transfer(courseWareList, CourseWareVo.class);
//        model.addAttribute("courseWareVoList",courseWareVoList);
//
//        if("Y".equals(courseVo.getIsTest())){
//            //测试信息
//            String queId = courseService.getCourseTestIds(courseId).get(0);
//            QuePaper paper = this.queService.findPaper(queId);
//            model.addAttribute("paper",paper);
//        }
//
//        return "/courselibrary/view";
//    }
//
//    @RequestMapping("edit")
//    public String toEdit(HttpServletRequest request,HttpServletResponse response,String courseId,Model model){
//        Course courseInfo = courseService.getCourseInfo(courseId);
//        CourseVo courseVo = courseTransferCourseVo.convert(courseInfo);
//        model.addAttribute("courseVo",courseVo);
//
//        //课程素材
//        List<CourseWare> courseWareList = courseService.getCourseWares(courseId);
//        List<CourseWareVo> courseWareVoList = transfer(courseWareList, CourseWareVo.class);
//        model.addAttribute("courseWareVoList",courseWareVoList);
//
//        if("Y".equals(courseVo.getIsTest())){
//            //测试信息
//            String queId = courseService.getCourseTestIds(courseId).get(0);
//            QuePaper paper = this.queService.findPaper(queId);
//            model.addAttribute("paper",paper);
//        }
//
//        List<Integer> weekList = new ArrayList<Integer>();
//        for (int i = 1;i<=40;i++){
//            weekList.add(i);
//        }
//        model.addAttribute("weekList",weekList);
//
//        int weekMin = courseVo.getWeekMin();
//        List<Integer> weekMaxList = new ArrayList<Integer>();
//        for (int i = weekMin;i<=40;i++){
//            weekMaxList.add(i);
//        }
//        model.addAttribute("weekMaxList",weekMaxList);
//
//        return "/courselibrary/edit";
//    }
//
//    @RequestMapping("update")
//    public String update(HttpServletRequest request, HttpServletResponse response,
//                         @RequestBody(required = false) CourseTestPageVo vo, Model model){
////        String loginUid = getLoginUid(request, response);
//        String loginUid = "";
//        CourseTestPaperDto paperDto = getCourseTestPaperDto(loginUid,vo);
//        courseService.updateCourseTest(paperDto);
//        return "redirect:/courselibrary/list.do";
//    }
//
//    @RequestMapping("save")
//    public String save(HttpServletRequest request,HttpServletResponse response,
//                       @RequestBody(required = false) CourseTestPageVo vo,Model model){
////        String loginUid = getLoginUid(request, response);
//        String loginUid = "";
//        CourseTestPaperDto paperDto = getCourseTestPaperDto(loginUid,vo);
//        courseService.saveCourseTest(paperDto);
//        return "redirect:/courselibrary/list.do";
//    }
//
//    private CourseTestPaperDto getCourseTestPaperDto(String loginUid, CourseTestPageVo vo) {
//        CourseTestPaperDto paperDto = new CourseTestPaperDto();
//        paperDto.setCreateUid(loginUid);
//        paperDto.setUpdateUid(loginUid);
//        paperDto.setQueType(vo.getType());
//        paperDto.setQueId(vo.getQueId());
//        paperDto.setCourseId(vo.getCourseId());
//        paperDto.setSchoolId(vo.getSchoolId());
//        paperDto.setQuePaper(getQuePaper(loginUid,vo));
//        paperDto.setCourseIntro(vo.getCourseIntro());
//        paperDto.setCourseName(vo.getCourseName());
//        paperDto.setIsTest(vo.getIsTest());
//        paperDto.setMaxWeek(vo.getMaxWeek());
//        paperDto.setMinWeek(vo.getMinWeek());
//        paperDto.setPhoto(vo.getPhoto());
//        paperDto.setCoursewares(transfer(vo.getCoursewares(),CourseTestPaperDto.courseware.class));
//        paperDto.setDeletedTopicIds(vo.getDeletedTopicIds());
//        return paperDto;
//    }
//
//    private QuePaper getQuePaper(String loginUid,CourseTestPageVo vo) {
//        QuePaper quePaper = transfer(vo, QuePaper.class);
//        quePaper.setCreateUid(loginUid);
//        quePaper.setUpdateUid(loginUid);
//
//        quePaper.setTopics(null);
//
//        List<CourseTestPageVo.Result> results = vo.getResults();
//        List<QuePaper.Result> resultList = transfer(results, QuePaper.Result.class);
//        quePaper.setResults(resultList);
//
//        List<QuePaper.Topic> topicList = Lists.newArrayList();
//        if (vo.getTopics() != null && !vo.getTopics().isEmpty()) {
//            List<CourseTestPageVo.Topic> topics = vo.getTopics();
//            QuePaper.Topic topic = null;
//            for (CourseTestPageVo.Topic t : topics) {
//
//                topic = transfer(t, QuePaper.Topic.class);
//                topic.setOptions(null);
//
//                List<CourseTestPageVo.Option> options = t.getOptions();
//                List<QuePaper.Option> optionList = transfer(options, QuePaper.Option.class);
//
//                if (!optionList.isEmpty()) {
//                    topic.setOptions(optionList);
//                }
//                topicList.add(topic);
//            }
//        }
//
//        quePaper.setTopics(topicList);
//        return quePaper;
//    }
//
//    @RequestMapping("downloadWare")
//    public void downloadWare(HttpServletResponse response,String wareUrl,String wareName){
//        download(response,wareUrl,wareName);
//    }
//
//    @RequestMapping("previewWare")
//    public void previewWare(HttpServletResponse response,String wareUrl,String wareName,String wareType){
//        BufferedInputStream bis = null;
//        URL url = null;
//        HttpURLConnection httpUrl = null; // 建立链接
//        try {
//            url = new URL(wareUrl);
//            httpUrl = (HttpURLConnection) url.openConnection();// 连接指定的资源
//            httpUrl.connect();// 获取网络输入流
//            bis = new BufferedInputStream(httpUrl.getInputStream());
//            if("WORD".equals(wareType)){
//                String bodyText = null;
//                WordExtractor ex = new WordExtractor(bis);
//                bodyText = ex.getText();
//                response.getWriter().write(bodyText);
//            }else if("PPT".equals(wareType)){
//                StringBuffer content = new StringBuffer("");
//                /*HSLFSlideShow ppt = new HSLFSlideShow(bis);
//                Dimension pageSize = ppt.getPageSize();
//                List<HSLFSlide> slides = ppt.getSlides();// 获得每一张幻灯片
//                for (int i = 0; i < slides.size(); i++) {
//                    HSLFSlide hslfSlide = slides.get(i);
//                    String title = hslfSlide.getTitle();
//                    content.append(title);
//                   *//* BufferedImage img = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
//                    Graphics2D graphics = img.createGraphics();
//                    hslfSlide.draw(graphics);
//                    FileOutputStream out = new FileOutputStream("/Users/xingzhou/Documents/" + i + ".png");
//                    javax.imageio.ImageIO.write(img, "png", out);
//                    ppt.write(out);
//                    out.close();*//*
//                }*/
//                XMLSlideShow pptx = new XMLSlideShow(bis);
//                Dimension pageSize = pptx.getPageSize();
//                List<XSLFSlide> slides = pptx.getSlides();
//                for (int i = 0; i < slides.size(); i++) {
//                    XSLFSlide xslfSlide = slides.get(i);
//                    String title = xslfSlide.getTitle();
//                    System.out.println("title==="+title);
//                    content.append(title);
//                }
//                response.getWriter().write(content.toString());
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
