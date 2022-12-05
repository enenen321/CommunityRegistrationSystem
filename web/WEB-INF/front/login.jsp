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
</head>
<body style="padding-top:0px;">

<%--获取session--%>
<% HttpSession s = request.getSession();%>
<h2 class="form-signin-heading" style="text-align:center; margin-top: 180px">社团活动报名系统</h2>
<div class="container" style="border: 1px solid skyblue;">
    <h2 class="form-signin-heading" style="text-align:center; margin-top: 50px">登录</h2>
    <form class="form-signin" id="login" action="${pageContext.request.contextPath }/loginOrLogout/crs" method="post">
        <label for="sNo" class="sr-only" style="margin-left: -32px">用户名:</label>
        <input style="margin-top: 10px" type="text" name="sNo" id="sNo" autofocus class="form-control" placeholder="请输入学号" required>
        <label for="password" class="sr-only">密码:</label>
        <input style="margin-top: 10px" type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required>
        <input class="blog-nav-item" type="checkbox" id="remember" name="remember" style="margin-left: 40px"> 记住密码
        <%--todo 待注册--%>
        <label style="margin-left:115px">没有账号？<a onclick="">注册</a></label>
        <button style="margin-top: 10px" class="btn btn-lg btn-primary btn-block" type="submit" onclick="login()">登录
        </button>
    </form>
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
                    登陆提示
                </h4>
            </div>
            <div class="modal-body">
                <%=s.getAttribute("msg")  %>
            </div>
            <div class="modal-footer">
                <button type="button" onClick="reset()"
                        class="btn btn-default" data-dismiss="modal">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>

<%--<div class="container" style="margin-top: 80px">--%>
<%--    <form class="form-signin" id="register" method="post"--%>
<%--          action="${pageContext.request.contextPath }/login/regisSub">--%>
<%--        <h2 class="form-signin-heading" style="text-align:center; margin-top: 50px">--%>
<%--            用户注册--%>
<%--        </h2>--%>
<%--        <label class="sr-only" for="inputSno" >--%>
<%--            学号:--%>
<%--        </label>--%>
<%--        <input style="margin-top: 10px" type="name" name="sno" id="inputSno" required autofocus--%>
<%--               class="form-control" placeholder="请填写学号">--%>
<%--        <label for="inputPhone" class="sr-only">--%>
<%--            手机号:--%>
<%--        </label>--%>
<%--        <input style="margin-top: 10px" type="phone" name="tel" id="inputPhone" required--%>
<%--               class="form-control" placeholder="手机号" pattern="^1((34[0-8])|(8\d{2})|(([35][0-35-9]|4[579]|66|7[35678]|9[1389])\d{1}))\d{7}$">--%>
<%--        <label for="inputPassword" class="sr-only">--%>
<%--            密码:--%>
<%--        </label>--%>
<%--        <input style="margin-top: 10px" type="password" name="password" id="inputPassword"--%>
<%--               class="form-control" placeholder="密码" required>--%>
<%--        <label style="margin-top: 10px" for="coll" class="sr-only">--%>
<%--            所属学院:--%>
<%--        </label>--%>
<%--        <select id="coll" required style="margin-top: 10px">--%>
<%--            <option name="信息学院" id="1">信息学院</option>--%>
<%--            <option name="经管学院" id="2">经管学院</option>--%>
<%--        </select>--%>
<%--        <button class="btn btn-lg btn-primary btn-block" type="submit">--%>
<%--            注册--%>
<%--        </button>--%>
<%--    </form>--%>

<script type="text/javascript">
    var msg = "<%=session.getAttribute("msg")%>";
    if (msg != 'null') {
        $(function () {
            $("#myModal").modal({
                keyboard: true
            });
        });
    }

    function reset() {
        var url = "${pageContext.request.contextPath }/loginOrLogout/back";
        window.location.href = url;
    }

    /*初始化登录窗口*/
    $(function () {
        getCookie();
    })

    /*是否记住密码*/
    function login() {
        setCookie();
    }

    function getCookie() {
        var remember = $.cookie("remember");
        //获取邮箱
        var sNo = $.cookie("sNo");
        //获取密码
        var password = $.cookie("password");
        if (remember) {
            $("#remember").attr("checked", "true");
        }
        if (sNo != "") {
            $("#sNo").val(sNo);
        } else {
            $("#sNo").val("");
        }
        if (password != "") {
            $("#password").val(password);
        } else {
            $("#password").val("");
        }
    }

    /*记住密码操作*/
    function setCookie() {
        var sNo = $('#sNo').val();
        var password = $('#password').val();
        if (password == "" || sNo == ""){
            return alert("学号或密码不能为空！");
        }
        var flag = $("#remember").is(":checked");
        if (flag) {
            $.cookie("remember", "true");
            $.cookie("sNo", sNo);
            $.cookie("password", password);
        } else {
            $.cookie("remember", "false", {expires: -1});
            $.cookie("sNo", "", {expires: -1});
            $.cookie("password", "", {expires: -1});
        }
    }
</script>
</body>
</html>
