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
    </style>
</head>
<body>
<% HttpSession s = request.getSession();%>
<div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
            <a class="blog-nav-item active"
               href="${pageContext.request.contextPath }/front/homepage">
                主页
            </a>
            <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">
                社团管理
            </a>
            <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">
                用户管理
            </a>
            <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">
                个人信息
            </a>
            <a class="blog-nav-item" href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">
                系统日志
            </a>
            <a class="blog-nav-item navbar-right" href="${pageContext.request.contextPath }/login/register">
                注册
            </a>
            <a  class="blog-nav-item navbar-right" href="${pageContext.request.contextPath }/loginOrLogout/back">
                注销
            </a>
            <%--登录用户--%>
            <a class="blog-nav-item navbar-right del">
                <%=s.getAttribute("username")%>
            </a>
            <%--头像--%>
            <div class="blog-nav-item navbar-right" style="width: 25px;height: 25px;margin-top: -2px;margin-right: 3px">
                <img onclick="imgSelect()" title="点击更换头像" width="25px" height="25px" rel="icon" src="${pageContext.request.contextPath }/resource/images/avatars/<%=s.getAttribute("avatar")%>">
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
        return ['png', 'jpg', 'jpeg', 'bmp', 'gif', 'webp', 'psd', 'svg', 'tiff'].indexOf(ext.toLowerCase()) !== -1;
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
        var userId = <%=s.getAttribute("userId")%>
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
</script>
<footer class="blog-footer" style="padding-top: 0px;height:5px;">
    <p>版权所有 XXXXXXXXXXXXXXXXXX</p>
</footer>
</body>
</html>

