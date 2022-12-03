<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath }/resource/image/favicon.ico">
    <link href="${pageContext.request.contextPath }/resource/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/img.css" type="text/css">
    <link href="${pageContext.request.contextPath }/resource/css/blog.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath }/resource/js/bootstrap.js"></script>
    <title>SB社区</title>
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
               href="${pageContext.request.contextPath }/front/homepage">首页</a>
            <a class="blog-nav-item"
               href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">社团管理</a>
            <a class="blog-nav-item"
               href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">用户管理</a>
            <a class="blog-nav-item"
               href="${pageContext.request.contextPath }/user/userInfomation/<%=s.getAttribute("userId")%>">个人信息</a>
            <a class="blog-nav-item navbar-right"
               href="${pageContext.request.contextPath }/login/register">注册</a>
            <a  class="blog-nav-item navbar-right"
                href="${pageContext.request.contextPath }/loginOrLogout/back">注销</a>
            <%--todo 头像设置--%>
            <a class="blog-nav-item navbar-right" >
                <link rel="icon" href="<%=request.getContextPath()%>/resource/images/title.png">
                <%=s.getAttribute("username")%>
            </a>
            <a class="blog-nav-item navbar-right" >&nbsp;&nbsp;</a>
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
<footer class="blog-footer" style="padding-top: 0px;height:5px;">
    <p>版权所有 XXXXXXXXXXXXXXXXXX</p>
</footer>
</body>
</html>

