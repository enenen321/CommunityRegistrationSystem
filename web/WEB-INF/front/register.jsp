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
        .register-back{
            float: left;
            display: inline-block;
            width: 36%;
            margin-left: 35px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body style="padding-top:0px;">
<% HttpSession s = request.getSession();%>
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
                    注册提示
                </h4>
            </div>
            <div class="modal-body">
                <%=s.getAttribute("msg")  %>
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

<%--注册框--%>
<h2 class="form-signin-heading" style="text-align:center; margin-top: 160px">社团活动报名系统</h2>
<div id="register-div" class="container" style="background-color: lightblue;">
    <form class="form-signin" id="register" method="post" action="${pageContext.request.contextPath }/base/register">
        <h2 class="form-signin-heading" style="text-align:center; margin-top: 45px">用户注册</h2>
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

<script type="text/javascript">
    var msg = "<%=session.getAttribute("msg")%>";
    if (msg != 'null') {
        $(function () {
            $("#myModal").modal({
                keyboard: true
            });
        });
        if (msg == "该学号已注册"){
            var elementById = document.getElementById("inputSno");
            console.log(elementById);
            document.getElementById("inputSno").style.borderColor= "red";
        }
        if (msg == "该手机号已注册"){
            var elementById = document.getElementById("inputPhone");
            console.log(elementById);
            document.getElementById("inputPhone").style.borderColor= "red";
        }
    }

    function reset(){
        var url="${pageContext.request.contextPath }/base/register-reset";
        window.location.href = url;
    }
    /*提交表单时去掉disabled，防止后台接收不到数据*/
    function remove(){
        $("#role").removeAttr("disabled");
    }
</script>
</body>