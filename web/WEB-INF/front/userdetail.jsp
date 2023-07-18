<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath }/resource/images/title.png">
    <link href="${pageContext.request.contextPath }/resource/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/img.css" type="text/css">
    <link href="${pageContext.request.contextPath }/resource/css/blog.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath }/resource/js/bootstrap.js"></script>
    <title>社团活动报名系统</title>
    <style>
        /*默认通配符*/
        * {
            margin: 0;
            padding: 0;
        }
        /* li在这里只去掉既有样式 不规定宽度 */
        li {
            cursor: pointer;
            line-height: 30px;
            text-underline-style: single;
            text-underline-color: black;
            list-style: none;
        }
        /* 菜单选择器，标识菜单的头，浮动只需要在每个section里做就行 */
        .section {
            float:left;
            font-size: 16px;
            line-height: 40px;
            text-align: center;
            height: 60px;
            overflow: hidden;
            /*animation-name: selectFrames;*/
            /*animation-duration: 0.5s;*/
        }
        /*菜单栏动画效果*/
        @keyframes selectFrames {
            from{
                height: 0;
            }
            to{
                height: 60px;
            }
        }
        /* 菜单缓慢下拉样式 ，隐藏撑出部分*/
        .menu {
            z-index: 2;
            overflow: hidden;
            background-color: transparent;
            margin-top: 10px;
            max-height: 0;
            /*过渡时间*/
            transition: 1.5s;
            position: absolute;
            top: 50px;
        }
        /* 悬停后就可以看到menu了 只能悬停父盒子 */
        .section:hover .menu {
            max-height: 250px;
            background-color: cornflowerblue;
            display: block;
        }
        .menu li {
            font-family: 宋体;
            color: #cdddeb;
            line-height: 20px;
            margin-top: 4px;
            padding-bottom: 5px;
        }
        .menu li a {
            text-decoration: none;
            font-family: 宋体;
            color: #cdddeb;
        }
        .menu li a:hover{
            color: #fff;
        }
        .crs_header{
            margin-left: 260px;
            margin-top: -61px;
            float: left;
            position: absolute;
            color: black;
        }
        td{
            font-family: 宋体;
            height: 70px;
            width: 171px;
            padding: 5px;
        }
        tr{
            border-top: 1px solid lightgray;
            line-height: 30px;
            text-align: center;
        }
        #actvlist{
            height: 350px;
        }
        input{
            border: 1px solid #ccc;
            border-radius: 3px;
            height: 29px;
            padding-left: 1px;
        }
    </style>
</head>
<body>
<% HttpSession s = request.getSession();%>
<div class="blog-masthead">
    <div class="container"  style="display: block;margin-bottom:70px;">
        <nav class="blog-nav">
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/base/homePage">主页</a>
            </div>
            <div class="section">
                <a class="blog-nav-item active">社团管理</a>
                <ul class="menu" id="meu">
                    <li id="cmty_actv"><a href="${pageContext.request.contextPath }/actv/list/1">社团活动列表</a></li>
                    <li id="cmty_user"><a href="${pageContext.request.contextPath }/base/homePage">社团人员管理</a></li>
                    <li id="report_review"><a href="${pageContext.request.contextPath }/actvReview/reviewList/1">人员报名审核</a></li>
                    <li id="actv_create"><a href="${pageContext.request.contextPath }/actv/createActv">社团活动创建</a></li>
                    <li id="cmty_create"><a href="${pageContext.request.contextPath }/sysCmty/createCmt">社团创建</a></li>
                </ul>
            </div>
            <%--普通用户不展示该选项--%>
            <%if (Integer.parseInt(s.getAttribute("roleId").toString()) == 1 || Integer.parseInt(s.getAttribute("roleId").toString()) == 2 || Integer.parseInt(s.getAttribute("roleId").toString()) == 3) {%>
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/sysUser/list/1">系统用户管理</a>
            </div>
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/sysLog/list/1">
                    系统日志
                </a>
            </div>
            <%}%>
            <div class="section">
                <a class="blog-nav-item">个人管理</a>
                <ul class="menu">
                    <li><a href="${pageContext.request.contextPath }/sysCmty/createCmt">社团活动详情</a></li>
                    <li><a href="${pageContext.request.contextPath }/sysUser/detail/<%=s.getAttribute("userId")%>">个人信息</a></li>
                    <li><a href="${pageContext.request.contextPath }/sysCmty/createCmt">参与的社团</a></li>
                </ul>
            </div>
            <div class="section navbar-right">
                <a class="blog-nav-item " href="${pageContext.request.contextPath}/base/register-page">
                    注册
                </a>
            </div>
            <div class="section navbar-right">
                <a  class="blog-nav-item " href="${pageContext.request.contextPath }/base/back">
                    退出
                </a>
            </div>
            <%--登录用户--%>
            <div class="section navbar-right">
                <a class="blog-nav-item">
                    <%=s.getAttribute("username")%>
                </a>
            </div>
            <%--头像--%>
            <div class="section navbar-right" style="margin-right: -3px">
                <img id="img" onclick="imgSelect()" style="margin-top:15px;" title="点击更换头像" width="25px" height="25px" rel="icon" src="${pageContext.request.contextPath }/resource/images/avatars/<%=s.getAttribute("avatar")%>">
            </div>
        </nav>
    </div>
</div>

<button style="visibility: hidden" class="btn btn-primary btn-lg"
        data-toggle="modal" data-target="#myModal" id="dialog"></button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    社团活动创建提示
                </h4>
            </div>
            <div class="modal-body">
                <%=s.getAttribute("msg")%>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="reset()"
                        class="btn btn-default" data-dismiss="modal">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>

<h3 class="crs_header">>>&nbsp;系统用户管理</h3>

<%--社团活动列表--%>
<div class="container">
    <div class="search" style="padding-bottom: 40px">
        <form action="" method="get" style="margin-left:100px;padding-left: 10px">
            <label for="username">用户名：</label>
            <input id="username" type="text" name="username" placeholder="按用户名搜索"/>
            <label for="phone">联系电话：</label>
            <input id="phone" type="text" name="phone" placeholder="按联系电话搜索"/>
            <label for="collName">学院名称：</label>
            <input id="collName" type="text" name="collName" placeholder="按学院名称搜索">
            <button type="submit" class="btn-primary" style="margin-left: 20px;width: 40px">查询</button>
        </form>
    </div>
    <div id="actvlist">
        <table style="margin:0 auto;padding-left: 80px; border: 1px solid black " >
            <thead style="background-color: #afd9ee">
            <tr>
                <td>用户名</td>
                <td>学号</td>
                <td>联系电话</td>
                <td>所属学院</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><div><img style="margin-top: 1px" width="25px" height="25px" rel="icon" src="${pageContext.request.contextPath }/resource/images/avatars/${user.avatar}"></div>${user.username}</td>
                    <td>${user.sno}</td>
                    <td>${user.phone}</td>
                    <td>${user.collName}</td>
                    <td><a href="#">修改</a>|<a href="#">删除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div style="padding-left: 130px; float: right;">
        <nav aria-label="Page navigation" style="margin-top: 58px">
            <ul class="pagination" style="font-family: 宋体">
                <li>
                    <a href="${pageContext.request.contextPath }/sysUser/list/1/">首页</a>
                </li>
                <!--上一页-->
                <li>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <a href="${pageContext.request.contextPath}/sysUser/list/${pageInfo.pageNum-1}/" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                        </a>
                    </c:if>
                </li>
                <!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
                <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                    <c:if test="${page_num == pageInfo.pageNum}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/sysUser/list/${page_num}/">${page_num}</a>
                        </li>
                    </c:if>
                    <c:if test="${page_num != pageInfo.pageNum}">
                        <li>
                            <a href="${pageContext.request.contextPath}/sysUser/list/${page_num}/">${page_num}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <!--下一页-->
                <li>
                    <c:if test="${pageInfo.hasNextPage}">
                        <a href="${pageContext.request.contextPath}/sysUser/list/${pageInfo.pageNum+1}/"
                           aria-label="Next">
                            <span aria-hidden="true">»</span>
                        </a>
                    </c:if>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/sysUser/list/${pageInfo.pages}">尾页</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

<%--图片选择框--%>
<form id="form_face" enctype="multipart/form-data" style="width:auto;">
    <input type="file" name="imgToUpload" id="imgToUpload" onchange="imgUpload();" style="display:none;"/>
</form>

<script>

    /*图片选择弹窗*/
    function imgSelect(){
        document.getElementById("imgToUpload").click();
    }

    /*图片判断*/
    function isImage(ext) {
        return ['png', 'jpg', 'jpeg', 'bmp', 'webp'].indexOf(ext.toLowerCase()) !== -1;
    }

    /*图片上传*/
    function imgUpload(){
        var name = document.getElementById("imgToUpload").value;
        if (name == ""){
            return alert("请选择文件");
        }
        var index = name.lastIndexOf(".");
        var ext = name.substring(index+1);
        if (!isImage(ext)){
            return alert("文件格式错误");
        }
        var file = document.getElementById("imgToUpload").files[0];
        var formData = new FormData();
        var userId = <%=s.getAttribute("userId")%>;
        formData.append("file",file);
        formData.append("userId",userId);
        $.ajax({
            url: "${pageContext.request.contextPath}/sysUser/upload-file",
            type:"post",
            contentType:false,
            processData:false,
            data:formData,
            success:function (){
                //刷新页面显示头像
                history.go(0);
            }
        })

    }
    /*删除li*/
    var roleId = <%=s.getAttribute("roleId")%>;
    if (roleId != 1 && roleId != 2 && roleId != 3){
        $("#cmty_user").remove();
        $("#cmty_spend").remove();
        $("#report_review").remove();
        $("#cmty_create").remove();
        $("#actv_create").remove();
    }
    if (roleId == 2){
        $("#cmty_spend").remove();
        $("#cmty_create").remove();
        $("#actv_create").remove();
    }
    if (roleId == 3){
        $("#cmty_spend").remove();
        $("#cmty_user").remove();
        $("#cmty_create").remove();
        $("#actv_create").remove();
    }

</script>
<footer class="blog-footer" style="padding-top:40px;height:5px;">
    <p style="margin-top: -12px">版权所有 XXXXXXXXXXXXXXXXXX</p>
</footer>
</body>
