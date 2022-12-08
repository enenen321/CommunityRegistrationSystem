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
    </style>
</head>
<body>
<% HttpSession s = request.getSession();%>
<div class="blog-masthead">
    <div class="container"  style="display: block;margin-bottom: 200px;">
        <nav class="blog-nav">
            <div class="section">
            <a class="blog-nav-item active" href="${pageContext.request.contextPath }/base/homePage">主页</a>
            </div>
            <div class="section">
            <a class="blog-nav-item">社团管理</a>
                <ul class="menu" id="meu">
                    <li id="actv_report"><a href="${pageContext.request.contextPath }/base/homePage">社团活动报名</a></li>
                    <li id="cmty_actv"><a href="${pageContext.request.contextPath }/base/homePage">社团活动列表</a></li>
                    <li id="cmty_user"><a href="${pageContext.request.contextPath }/base/homePage">社团人员管理</a></li>
                    <li id="cmty_spend"><a href="${pageContext.request.contextPath }/base/homePage">社团经费审核</a></li>
                    <li id="report_review"><a href="${pageContext.request.contextPath }/base/homePage">人员报名审核</a></li>
                    <li id="actv_create"><a href="${pageContext.request.contextPath }/actv/createActv">社团活动创建</a></li>
                    <li id="cmty_create"><a href="${pageContext.request.contextPath }/sysCmty/createCmt">社团创建</a></li>
                </ul>
            </div>
            <%--普通用户不展示该选项--%>
            <%if (Integer.parseInt(s.getAttribute("roleId").toString()) == 1 || Integer.parseInt(s.getAttribute("roleId").toString()) == 2 || Integer.parseInt(s.getAttribute("roleId").toString()) == 3) {%>
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">系统用户管理</a>
            </div>
            <div class="section">
            <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">
                系统日志
            </a>
            </div>
            <%}%>
            <div class="section">
            <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">个人管理</a>
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


<div class="onepage" id="pageone">
    <div class="onepage-bg" id="onepagebg"></div>
    <div class="container">
        <div class="row">
            <div class="title-text">
                <div class="col-md-12 headfontsize">
                    <h1 class="headh1content">
                        SB社区<small> Fun Here</small><br>
                    </h1>
                    <h3>为世界再添一度饱和</h3>
                    <p class="headtext">软件、方法、资源；想你所想，爱你所爱</p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" style="padding-top:50px;padding-bottom:50px;">
    <div class="row">
        <c:if test="${not empty article1}">
            <div class="col-md-4">
                <h4>
                    <p class="line-limit-length">
                            ${article1.title}
                    </p>
                </h4>
                <p class="line-content-length">
                        ${article1.content}
                </p>
                <p>
                    <a class="btn btn-default"
                       href="${pageContext.request.contextPath }/article/fireArticle/${article1.type}" role="button">
                        更多内容&raquo;
                    </a>
                </p>
            </div>
        </c:if>
        <c:if test="${not empty article2}">
            <div class="col-md-4">
                <h4>
                    <p class="line-limit-length">
                            ${article2.title}
                    </p>
                </h4>
                <p class="line-content-length">
                        ${article2.content}
                </p>
                <p>
                    <a class="btn btn-default"
                       href="${pageContext.request.contextPath }/article/fireArticle/${article2.type}" role="button">
                        更多内容&raquo;
                    </a>
                </p>
            </div>
        </c:if>
        <c:if test="${not empty article3}">
            <div class="col-md-4">
                <h4>
                    <p class="line-limit-length">
                            ${article3.title}
                    </p>
                </h4>
                <p class="line-content-length">
                        ${article3.content}
                </p>
                <p>
                    <a class="btn btn-default"
                       href="${pageContext.request.contextPath }/article/fireArticle/${article3.type}" role="button">
                        更多内容&raquo;
                    </a>
                </p>
            </div>
        </c:if>
    </div>
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
<footer class="blog-footer" style="padding-top: 0px;height:5px;">
    <p>版权所有 XXXXXXXXXXXXXXXXXX</p>
</footer>
</body>
</html>

