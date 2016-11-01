<!DOCTYPE html>
<html>
    <!-- BEGIN HEAD -->
    <head>

        <title>课程编辑</title>

    </head>
    <body class=" page-sidebar-closed-hide-logo page-content-white">
        <!-- BEGIN HEADER -->
        <#--<#include "/base/header_page.ftl" />-->
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <#--<#include "/base/sidebar_page.ftl" />-->
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                   
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <span>课程表</span>
                                <#--<i class="fa fa-circle"></i>-->
                                <i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <a href="list.do">课程库</a>
                                <#--<i class="fa fa-circle"></i>-->
                                <i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <span>新增</span>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- END PAGE HEADER-->
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pagination-box-parent">
                                <!--查询-->
                                <div class="portlet light bordered" id="search-margin">
                                    <div class="portlet-body form">
                                        <form role="form" class="form-horizontal" action="#" id="form_sample_4" method="post">
                                            <input type="hidden" id="schoolId" name="schoolId" value="1" />

                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-md-offset-3 col-md-9">
                                                        <button type="button" class="btn green" onclick="submitForm()">提交</button>
                                                        <a href="list.do" class="btn default">取消</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <!-- END FORM-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
           
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->

        <!-- END FOOTER -->


        <script type="text/javascript">

            //提交表单
            function submitForm(){
//                if(!$('#form_sample_4').validationEngine('validate')){return;}
                // var form = $("#form_sample_4").serialize();
                // alert(form);
                var flag = false;
                var formLength = $("form").length;
                for (var i = 0; i<formLength; i++) {
                    var validation = $($("form")[i]).validationEngine({
                         autoHidePrompt:true,
                         autoHideDelay:1000
                    });
                    if(!$(validation).validationEngine('validate')){
                        $(validation).validationEngine('detach');
                        flag = true;
                        return;
                    }
                }
                /*$("form").each(function () {
                    /!*if(!$(this).validationEngine('validate')){
                        flag = true;
                    }*!/

                    var validation = $(this).validationEngine({
                        autoHidePrompt:true,autoHideDelay:2000
                    });
                    if(!$(validation).validationEngine('validate')){
                        $(validation).validationEngine('detach');
                        flag = true;
                    }
                });*/
                if(flag){
                    return;
                }

                /*var validation1 = $('#form_sample_4').validationEngine({autoHidePrompt:true,autoHideDelay:2000});
                var validation2 = $('#form-id-1').validationEngine({autoHidePrompt:true,autoHideDelay:2000});
                var validation3 = $('#form-id-2').validationEngine({autoHidePrompt:true,autoHideDelay:2000});


                if(!$(validation1).validationEngine('validate')){return;}
                if(!$(validation2).validationEngine('validate')){return;}
                if(!$(validation3).validationEngine('validate')){return;}*/


                var minWeek = $("#minWeek").val();
                var maxWeek = $("#maxWeek").val();
                if(parseInt(minWeek) > parseInt(maxWeek)){
                    alert("后孕周应大于前孕周，请重新选择孕周范围");
                    return;
                }
                if ($("#courseware").prop("checked")) {
                    var fileName = $("#coursewareName").val();
                    if(fileName == ''){
                        alert("请上传课件");
                        return;
                    }
                }

                var paper = buildPaper();
                // alert(JSON.stringify(paper));
                // $("#form_sample_4").submit();
                $.ajax({
                    type : "POST",
                    url : "${rc.contextPath}/courselibrary/save.do",
                    contentType : "application/json",
                    async : false,
                    data : JSON.stringify(paper),
                    dataType : "text",
                    success : function(data) {
                        window.location.href="${rc.contextPath}/courselibrary/list.do";
                    },
                    error : function(err) {

                        alert("保存试题错误！");
                    }
                });
            };

            //获取表单参数
            function buildPaper() {
                var paper = new Object();

                paper.photo = $("#imgPath").val();//课程封面图片地址
                paper.courseName = $("#courseName").val();//课程名称
                paper.minWeek = $("#minWeek option:selected").val();//最小孕周
                paper.maxWeek = $("#maxWeek option:selected").val();//最大孕周
                paper.courseIntro = $("#courseIntro").val();//课程简介
                paper.type='TEST';
                paper.que=$("#courseName").val();
                paper.schoolId = $("#schoolId").val();

                coursewares = [];

                //课程素材
                if($("#courseware:checked").val()!=undefined){//课件
                    var courseware = {};
//                    courseware.type="courseware";
                    var fileName = $("input[name=fileName]").val();
                    courseware.wareName=fileName;
                    var filename=fileName.replace(/.*(\/|\\)/, "");
                    var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
                    if (fileExt == 'ppt' || fileExt == 'pptx'){
                        courseware.type="PPT";
                    }else if (fileExt == 'pdf'){
                        courseware.type="PDF";
                    }else if (fileExt == 'doc' || fileExt == 'docx'){
                        courseware.type="WORD";
                    }else{
                        courseware.type="OTHER";
                    }
                    courseware.wareUrl = $("input[name=filePath]").val();
                    courseware.duration = $("#filePages").val();
                    coursewares.push(courseware);
                }
                if($("#video:checked").val()!=undefined){//视频
                    var courseware = {};
                    courseware.type="VIDEO";
                    courseware.wareName="";
                    courseware.wareUrl = $("#videoLink").val();
                    courseware.duration = $("#videoLength").val();
//                    courseware.photo = $("#videoImgPath").val();
                    coursewares.push(courseware);
                }
                paper.coursewares = coursewares;

                //测试
                var questions = [];
                $(".question_one").each(
                        function(i) {
                            var $this = $(this);

                            var question_one = new Object();
                            var options = [];

                            var questionType = $this.find("select[name=questionType] option:selected").val();
                            question_one.type = questionType;
                            question_one.topic = $this.find("input[name=questionName]").val();

                            var $options = $this.find(".question_option").find(".form-group");
                            $options.each(function(j) {

                                var option = new Object();

                                var $this = $(this);
                                if (questionType == 'SINGLE' || questionType == 'MULTI'){
                                    var text = $this.find("span:first").html();
                                    option.optionMark = text.substr(0,1);//A\B 选项名称
                                    option.queOption = $this.find("[name='option']").val();//选项内容
                                    var $correct = $this.find("input[name='isAnswer']");
                                    var isCorrect = $correct.prop("checked");
                                    if (isCorrect) {
                                        option.isAnswer = "Y";
                                    } else {
                                        option.isAnswer = "N";
                                    }
                                }else if(questionType == 'JUDGE'){
                                    if (j==0) {
                                        option.optionMark = "A";
                                        option.queOption = "正确";
                                    }else if (j==1) {
                                        option.optionMark = "B";
                                        option.queOption = "错误";
                                    }
                                    var $correct = $this.find("input[name='isAnswer']");
                                    var isCorrect = $correct.prop("checked");
                                    if (isCorrect) {
                                        option.isAnswer = "Y";
                                    } else {
                                        option.isAnswer = "N";
                                    }
                                }else if(questionType == 'QA'){

                                }else if(questionType == 'DATE'){

                                }

                                option.sort = j;
                                options.push(option);
                            });

                            question_one.options = options;
                            question_one.sort = i;
                            questions.push(question_one);
                        });

                paper.topics = questions;


                //结果文案
                results = [];

                var aVal = $("#alltrue").val();
                var bVal = "很遗憾，您答错了X题"+$("#haserror").val();

                var resultA = {};
                resultA.resultType="ALLRIGHT";
                resultA.result=aVal;
                resultA.resultId="";

                var resultB = {};
                resultB.resultType="HASMISTAKE";
                resultB.result=bVal;
                resultB.resultId="";

                results.push(resultA);
                results.push(resultB);
                paper.results = results;

//                console.log("questions[0].topic.trim()==="+questions[0].topic.trim()+"===")
                if(questions[0].topic.trim()==""){
//                    alert("N");
                    paper.isTest = "N";
                }else{
//                    alert("Y");
                    paper.isTest = "Y";
                }

                return paper;
            }
        </script>
    </body>

</html>