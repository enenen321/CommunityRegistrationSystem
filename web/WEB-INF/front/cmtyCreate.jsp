<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<%=request.getContextPath()%>/resource/images/title.png">
    <title>社团活动报名系统</title>
    <link href="${pageContext.request.contextPath}/resource/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/blog.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/signin.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery.cookie.js"></script>
    <style>
        /*默认通配符*/
        * {
            margin: 0;
            padding: 0;
        }
        .line-limit-length {
            max-width: 600px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .line-content-length {
            overflow:hidden;
            text-overflow:ellipsis;
            display:-webkit-box;
            -webkit-box-orient:vertical;
            -webkit-line-clamp:5;
        }
        /*a {*/
        /*    !*text-decoration: none;*!*/
        /*    color: black;*/
        /*}*/
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
            z-index: 3;
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
        .cmty_manager{
            margin-left: 260px;
            margin-top: -60px;
            float: left;
            position: absolute;
            color: black;
        }
        #cmty{
            float: left;
            padding: 20px;
            display: inline-block;
        }

    </style>
</head>
<body style="padding-top:0px;">
<% HttpSession s = request.getSession();%>
<div class="blog-masthead">
    <div class="container"  style="margin-bottom: 80px;">
        <nav class="blog-nav">
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/base/homePage">主页</a>
            </div>
            <div class="section">
                <a class="blog-nav-item active">社团管理</a>
                <ul class="menu" id="meu">
                    <li id="actv_report"><a href="${pageContext.request.contextPath }/base/homePage">社团活动报名</a></li>
                    <li id="cmty_actv"><a href="${pageContext.request.contextPath }/base/homePage">社团活动列表</a></li>
                    <li id="cmty_user"><a href="${pageContext.request.contextPath }/base/homePage">社团人员管理</a></li>
                    <li id="cmty_spend"><a href="${pageContext.request.contextPath }/base/homePage">社团经费审核</a></li>
                    <li id="report_review"><a href="${pageContext.request.contextPath }/base/homePage">人员报名审核</a></li>
                    <li id="cmty_create"><a href="${pageContext.request.contextPath }/sysCmty/createCmt">社团创建</a></li>
                </ul>
            </div>
            <%--普通用户不展示该选项--%>
            <%if (Integer.parseInt(s.getAttribute("roleId").toString()) == 1 || Integer.parseInt(s.getAttribute("roleId").toString()) == 2 || Integer.parseInt(s.getAttribute("roleId").toString()) == 3) {%>
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">
                    系统日志
                </a>
            </div>
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">系统用户管理</a>
            </div>
            <%}%>
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">个人管理</a>
                <ul class="menu">
                    <li href="#">社团活动详情</li>
                    <li href="#">个人信息</li>
                    <li href="#">参与的社团</li>
                </ul>
            </div>
            <div class="section navbar-right">
                <a class="blog-nav-item " href="${pageContext.request.contextPath }/login/register">
                    注册
                </a>
            </div>
            <div class="section navbar-right">
                <a  class="blog-nav-item " href="${pageContext.request.contextPath }/base/back">
                    注销
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
                <img onclick="imgSelect()" style="margin-top:15px;" title="点击更换头像" width="25px" height="25px" rel="icon" src="${pageContext.request.contextPath }/resource/images/avatars/<%=s.getAttribute("avatar")%>">
            </div>
        </nav>
    </div>
</div>


<%--社团创建--%>
<h3 class="cmty_manager">>>&nbsp;社团管理</h3>
<div id="register-div" class="container" style="background-color: lightblue;">
    <form class="form-signin" id="cmty" method="post" action="${pageContext.request.contextPath }/base/register">
        <h2 class="form-signin-heading" style="text-align:center; margin-top: 45px">社团创建</h2>
        <label class="sr-only" for="inputSno" style="margin-top: 9px">学号:</label>
        <input style="margin-top: 10px" value="" type="text" name="sno" id="inputSno" required autofocus
               class="form-control" placeholder="请填写学号">
        <label for="inputPhone" class="sr-only" style="margin-left: -32px">
            手机号:
        </label>
        <input style="margin-top: 10px" type="phone" name="phone" id="inputPhone" required
               class="form-control" placeholder="请填写手机号" value="" pattern="^1((34[0-8])|(8\d{2})|(([35][0-35-9]|4[579]|66|7[35678]|9[1389])\d{1}))\d{7}$">
        <label for="inputUsername" class="sr-only" style="margin-left: -32px">
            用户名:
        </label>
        <input style="margin-top: 10px" value="" type="text" name="username" id="inputUsername"
               class="form-control" placeholder="请填写用户名" required>
        <label for="inputPassword" class="sr-only">
            密码:
        </label>
        <input style="margin-top: 10px" value="" type="password" name="password" id="inputPassword"
               class="form-control" placeholder="请填写密码" required>
        <label style="margin-top: 10px;margin-left: -38px" for="coll" class="sr-only">
            所属学院:
        </label>
        <select id="coll" name="collId" required style="margin-top: 10px" class="form-control">
            <option value="1">信息学院</option>
            <option value="2">经管学院</option>
            <option value="3">动科学院</option>
        </select>
        <label style="margin-top: 20px;" for="role" class="sr-only">
            角色:
        </label>
        <select id="role" name="roleId" required disabled style="margin-top: 10px;" class="form-control">
            <option value="4">普通用户</option>
            <option value="1">社团管理员</option>
            <option value="3">辅导员</option>
            <option value="2">社团团长</option>
        </select>
        <button style="margin-top: 10px;" onclick="remove()" class="btn btn-lg btn-primary btn-block register-back" type="submit">注册</button>
        <button style="margin-top: 10px;" class="btn btn-lg btn-primary btn-block btn register-back" type="button" onclick="window.history.go(-1)">返回</button>
    </form>
</div>


<%--图片选择框--%>
<form id="form_face" enctype="multipart/form-data" style="width:auto;">
    <input type="file" name="imgToUpload" id="imgToUpload" onchange="imgUpload();" style="display:none;"/>
</form>

<script type="text/javascript">
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
                //刷新页面
                history.go(0);
            }
        })

    }

    /*删除li*/
    var roleId = <%=s.getAttribute("roleId")%>;
    console.log(roleId);
    if (roleId != 1 && roleId != 2 && roleId != 3){
        $("#cmty_user").remove();
        $("#cmty_spend").remove();
        $("#report_review").remove();
        $("#cmty_create").remove();
    }
    if (roleId == 2){
        $("#cmty_spend").remove();
        $("#cmty_create").remove();
    }
    if (roleId == 3){
        $("#cmty_spend").remove();
        $("#cmty_user").remove();
        $("#cmty_create").remove();
    }
</script>
<footer class="blog-footer" style="padding-top: 0px;height:20px;margin-top: 30px">
    <p>版权所有 XXXXXXXXXXXXXXXXXX</p>
</footer>
</body>