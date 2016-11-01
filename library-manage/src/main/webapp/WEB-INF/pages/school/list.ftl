<!DOCTYPE html>
<html>
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <#include "/base/head_meta.ftl"/>
        <#setting datetime_format="MM/dd HH:mm:ss"/>

        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="${rc.contextPath}/assets/pages/css/rightbox.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->

        <title>课程库</title>
        
    <!-- END HEAD -->
    </head>
    <body class="page-sidebar-closed-hide-logo page-content-white">
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
                                <span>课程库</span>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->

                    <!-- END PAGE HEADER-->
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pagination-box-parent">
                                <!--查询bordered-->
                                <div class="portlet light " id="search-margin">
                                    <div class="portlet-body form">
                                        <form action="list.do" class="form-horizontal search-form" id="search-form">
                                        <#--<input type="hidden" name="status" value="${paramMap.status!'0'}"/>
                                            <div class="form-body" style="padding:20px 20px 5px 20px">
                                                <div class="form-group">
                                                    <label class="col-md-1 control-label">课程</label>
                                                    <div class="col-md-3">
                                                        <select class="form-control " name="courseId">
                                                            <option value="">--请选择--</option>
                                                            <#list courseAllVoList as vo>
                                                                <option value="${vo.courseId}" <#if vo.courseId==paramMap.courseId>selected="selected"</#if> >${vo.courseName}</option>
                                                            </#list>
                                                        </select>
                                                    </div>
                                                    <label class="col-md-2 control-label">有无测试</label>
                                                    <div class="col-md-3">
                                                        <select class="form-control " name="isTest">
                                                            <option value="">--请选择--</option>
                                                            <option value="Y" <#if vo.isTest==paramMap.isTest>selected="selected"</#if>>有</option>
                                                            <option value="N" <#if vo.isTest==paramMap.isTest>selected="selected"</#if>>无</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div style="float:right;margin-right:5%">
                                                        <a href="javascript:formSearch();" class="btn btn-outline green"> 搜索
                                                            <i class="fa fa-search"></i>
                                                        </a>
                                                        <a href="javascript:formReset();" class="btn btn-outline green"> 重置
                                                            <i class="fa fa-eraser"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>-->
                                        </form>
                                        <!-- END FORM-->
                                    </div>
                                </div>
                                <!--列表-->

                                <div class="portlet box green">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa icon-list"></i>
                                        </div>
                                       <div class="clearfix" style="float:right;padding-top:3px">
                                            <a href="add.do" class="btn">新增课程库
                                                <i class="fa fa-plus"></i>
                                            </a> 
                                             <#--<a href="javascript:;" onclick="batchDelete()" class="btn">批量删除
                                                 <i class="fa fa-remove"></i>
                                                &lt;#&ndash;<i class="fa fa-minus"></i>&ndash;&gt;
                                            </a>-->
                                            <#--<a href="javascript:;" class="btn">彻底删除
                                                <i class="fa fa-minus-square"></i>
                                            </a>-->
                                                                                          
                                        </div>
                                    </div>

                                    <div class="portlet-body flip-scroll">
                                  
                                        <table class="table table-bordered table-striped table-condensed flip-content"
                                        id="my_sample_1">
                                            <thead class="flip-content">
                                                <tr>
                                                    <#--<th>
                                                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                            <input type="checkbox" class="group-checkable" data-set="#my_sample_1 .checkboxes" />
                                                            <span></span>
                                                        </label>
                                                    </th>-->
                                                    <th> 序号 </th>
                                                    <th> 课程名称 </th>
                                                    <th> 适应孕周 </th>
                                                    <th> 已上课次数</th>
                                                    <th> 有无测试</th>
                                                    <th> 操作     </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                              <#if page.list?? && page.list?size &gt; 0>
	                                                <#list page.list as vo>
	                                                    <tr class="odd gradeX">
	                                                        <#--<td>
	                                                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
	                                                                <input type="checkbox" class="checkboxes" value="${vo.courseId!''}" />
	                                                                <span></span>
	                                                            </label>
	                                                        </td>-->
	                                                        <td> ${((page.pageNo-1)*page.pageSize)+vo_index+1} </td>
	                                                        <td>
	                                                            <a href="${rc.contextPath}/courselibrary/view.do?courseId=${vo.courseId}">${vo.courseName!''}</a>
	                                                        </td>
	                                                        <td> ${vo.weekMin!''}-${vo.weekMax!''} </td>
	                                                        <td>${vo.startCount!'0'}</td>
	                                                        <td>
	                                                            <#if vo.isTest=='Y'>
	                                                                <i class="fa fa-check"></i>
	                                                            <#else >
	                                                                <i class="fa fa-close"></i>
	                                                            </#if>
	                                                        </td>
	                                                        <td>
	                                                            <a href="view.do?courseId=${vo.courseId}">查看<#--<i class="fa fa-search"></i>--></a>&nbsp;
	                                                            <a href="edit.do?courseId=${vo.courseId}">编辑<#--<i class="fa fa-edit"></i>--></a>&nbsp;
	                                                            <a href="tocourseopen.do?courseId=${vo.courseId}">开课<#--<i class="fa fa-unlock"></i>--></a>&nbsp;
	                                                            <#--<a href="delete.do?scheduleId=1" onclick="return confirm('确定删除?')">删除<i class="fa fa-remove"></i></a>-->
	                                                        </td>
	                                                    </tr>
	                                                </#list>
                                                <#else>
                                               		 <tr><td colspan="6"><i class="icon-warn32"></i>暂无相关数据，重新输入条件查询！</div></td></tr>
                                                </#if>
                                            </tbody>
                                        </table>
                                        
                                        <div class="pagination-box" totalPage="${page.last}" active="${page.pageNo}"> </div>
                                        <div class="pagination-box-col-height10"></div>
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
        <#--<div class="page-footer">
            <!-- <div class="page-footer-inner"> 2014 &copy; Metronic by keenthemes.
                <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div> &ndash;&gt;
        </div>-->
        <!-- END FOOTER -->

        <#include "/base/base_script.ftl"/>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${rc.contextPath}/assets/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="${rc.contextPath}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="${rc.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        <script src="${rc.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->

        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <!-- 表格 -->
        <script src="${rc.contextPath}/assets/pages/scripts/table-datatables-managed.js" type="text/javascript"></script>
        <script src="${rc.contextPath}/assets/pages/scripts/pagination-samples.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script>
            $(function () {
//                Layout.setSidebarMenuActiveLink('set', $('#course_library_page'));
                $("body").scrollTop(0);
            });
            /*搜索表单*/
            function formSearch(){
                $("#search-form").submit();
            }
            // 表单参数重置
            function formReset(){
                $("#search-form").find(":input").not(":button,:submit,:reset,:hidden").val("").removeAttr("checked").removeAttr("selected");//通用重置
//                $("#search-form")[0].reset();//dom重置
            }
            var table = $('#my_sample_1');
            table.find('.group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {

                        $(this).prop("checked", true);
                        $(this).parents('tr').addClass("active");
                    } else {
                        $(this).prop("checked", false);
                        $(this).parents('tr').removeClass("active");
                    }
                });
            });

            table.on('change', 'tbody tr .checkboxes', function () {
                $(this).parents('tr').toggleClass("active");
            });

            //批量删除
            function batchDelete() {
                var obj = $(".checkboxes:checked");
                if (obj.length > 0){
                    if(confirm("确定删除？")){
                        var scheduleIds = new Array();
                        //获取选中的记录
                        obj.each(function () {
                            var scheduleId = $(this).val();
                            scheduleIds.push(scheduleId);
                        });
//                        alert("批量删除"+scheduleIds.join(","));
//                alert("批量删除"+scheduleIds.join(""));
                        $.get(
                                "${rc.contextPath}/schedule/batchDelete.do",//path
//                    {scheduleIds: JSON.stringify(scheduleIds)},
                                {scheduleIds: scheduleIds.join(",")},
                                function(data){
                                }
                        );
                    }
                }else{
                    alert("请选择数据");
                }

            }
         </script>
    </body>

</html>