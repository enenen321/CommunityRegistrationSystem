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
    <link href="${pageContext.request.contextPath}/resource/css/select2.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/resource/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath }/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/select2.min.js"></script>
    <script src="${pageContext.request.contextPath }/resource/js/wangEditor.min.js"></script>
    <script src="${pageContext.request.contextPath }/resource/js/wangEditor-fullscreen-plugin.js"></script>
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
        /*下拉框样式*/
        .select2-container .select2-selection--single{
            height: 40px;
            width: 467px;
            margin-left: 162px;
            margin-top: 10px;
        }
        .select2-container--default .select2-selection--single .select2-selection__arrow{
            top: 15px;
            right: 45px;
        }
        .select2-container--open .select2-dropdown--below{
            margin-left:162px;
            max-width: 467px;
            border: 1px solid #ccc;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        .select2-container .select2-selection--single .select2-selection__rendered{
            margin-top: 3px;
            padding-left: 17px;
        }
        .select2-container--default .select2-selection--single .select2-selection__placeholder{
            font-size: 15px;
            margin-left: -4px;
        }
        .register-back{
            float: left;
            display: inline-block;
            width: 15%;
            margin-bottom: 0px;
        }
        .crs_header{
            margin-left: 260px;
            margin-top: -91px;
            float: left;
            position: absolute;
            color: black;
        }
        h1, .h1, h2, .h2, h3, .h3, h4, .h4, h5, .h5, h6, .h6{
            margin-top: 1px;
            margin-left: 347px;
        }
        .w-e-toolbar{
            margin-left: 160px;
            margin-top: 10px;
            width: 469px;
        }
        .w-e-text-container{
            width: 469px;
            margin-left: 160px;
        }
        .w-e-text p{
            line-height: 1px;
        }

        @media screen and (-webkit-min-device-pixel-ratio: 0) {
            input[type="datetime-local"].form-control {
                margin-left: 158px;
                width: 250px;
            }
        }
        .sr-only{
            margin-left: 70px;
        }

    </style>
</head>
<body style="padding-top:0px;">
<% HttpSession s = request.getSession();%>
<div class="blog-masthead">
    <div class="container"  style="margin-bottom: 100px;">
        <nav class="blog-nav">
            <div class="section">
                <a class="blog-nav-item" href="${pageContext.request.contextPath }/base/homePage">主页</a>
            </div>
            <div class="section">
                <a class="blog-nav-item active">社团管理</a>
                <ul class="menu" id="meu">
                    <li id="cmty_actv"><a href="${pageContext.request.contextPath }/actv/list/1">社团活动列表</a></li>
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
                <a class="blog-nav-item" href="${pageContext.request.contextPath}/base/register-page">
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
                <img onclick="imgSelect()" style="margin-top:15px;" title="点击更换头像" width="25px" height="25px" rel="icon" src="${pageContext.request.contextPath }/resource/images/avatars/<%=s.getAttribute("avatar")%>">
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


<button style="visibility: hidden" class="btn btn-primary btn-lg"
        data-toggle="modal" data-target="#error" id="dialog"></button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="error" tabindex="-1" role="dialog" aria-labelledby="errorLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="errorLabel">
                    社团活动创建提示
                </h4>
            </div>
            <div class="modal-body">
                活动内容限制二十字以内！
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

<%--社团活动创建--%>
<h3 class="crs_header">>>&nbsp;社团管理</h3>
<div class="container" >
    <div id="comment-place">
        <div class="comment-post" id="comment-post">
            <h3>
                <i class="fa fa-commenting-o fa-fw" style="margin-left: 50px"></i>
                社团活动创建 <i class="icon-comment-alt"></i>
            </h3>
            <form class="form-horizontal" role="form" id="comment-form" style="margin-left: 100px">
                <label for="cmtyId" class="sr-only">
                    社团名称:
                </label>
                <select id="cmtyId" name="cmtyId" required style="margin-top: 10px;"  class="form-control">
                    <c:forEach items='<%=s.getAttribute("cmtyList")%>' var="cmty">
                        <option style="font-family: 宋体" value=${cmty.id}>${cmty.cmtyName}</option>
                    </c:forEach>
                </select>
                <label  for="actvTitle" class="sr-only">
                    活动主题:
                </label>
                <input id="actv_title" style="margin-top: 10px; width: 56%;margin-left: 160px;font-family: 宋体" type="text" name="actvTitle" id="actvTitle" required
                       class="form-control" placeholder="请填写活动主题" value="">
                <label for="editor" class="sr-only">
                    活动内容:
                </label>
                <input type="hidden" name="pid" id="comment-pid"
                       value="0" size="22" tabindex="1">
                <div id="editor" name="actvContent" style="width: 100%;height: 150px;font-family: 宋体">
                    <p></p>
                </div>
                <label style="margin-top: 50px; width: 96px;margin-left: 43px;" for="deadline" class="sr-only">
                    报名截止日期:
                </label>
                <input style="margin-top: 43px; font-family: 宋体" type="datetime-local" name="deadline" id="deadline" required
                       class="form-control" placeholder="请选择截至日期" value="">
                <a style="margin-top: 10px; margin-left: 165px" href="javascript:addActv()" class="btn btn-lg btn-primary btn-block register-back" type="button">创建</a>
                <button style="margin-top: 10px; margin-left: 175px" class="btn btn-lg btn-primary btn-block btn register-back" type="button" onclick="window.history.go(-1)">返回</button>
            </form>
        </div>
    </div>
</div>

<%--图片选择框--%>
<form id="form_face" enctype="multipart/form-data" style="width:auto;">
    <input type="file" name="imgToUpload" id="imgToUpload" onchange="imgUpload();" style="display:none;"/>
</form>

<script type="text/javascript">
    var msg = "<%=session.getAttribute("msg")%>";
    if (msg != 'null') {
        $(function () {
            $("#myModal").modal({
                keyboard: true
            });
        })};

    function addActv(){
        //获取社团id
        var cmtyId = $("#cmtyId").val();
        //获取活动主题
        var actvTitle = $("#actv_title").val();
        //获取活动内容
        var actvContent = editor.txt.text();
        var length = actvContent.length;
        if (length > 20){
            $(function (){
                $("#error").model({
                   keyboard: true
                });
            })};

        //获取截止日期
        var deadline = $("#deadline").val();
        var actv = JSON.stringify({"cmtyId":cmtyId,"actvTitle":actvTitle,"actvContent":actvContent,"deadline":deadline});
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/actv/add",
            contentType:"application/json;charset=utf-8",
            data:actv,
            success:function (){
                history.go(0);
            },
            fail:function (){
                history.go(0);
            }
        });
    }

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
            },
            fail:function (){
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

    $("#cmtyId").select2({
        placeholder:"按社团名称搜索"
    });

    function reset() {
        var url = "${pageContext.request.contextPath }/actv/createActv-reset";
        window.location.href = url;
    }
</script>

<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor')
    // 自定义菜单配置
    editor.customConfig.menus = [
        'bold', // 粗体
        'italic', // 斜体
        'underline', // 下划线
        'list', // 列表
        'emoticon', // 表情
    ];
    // debug模式下，有 JS 错误会以throw Error方式提示出来
    editor.customConfig.debug = true;
    // 关闭粘贴样式的过滤
    editor.customConfig.pasteFilterStyle = false;
    // 自定义处理粘贴的文本内容
    editor.customConfig.pasteTextHandle = function(content) {
        // content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
        return content + '<p>在粘贴内容后面追加一行</p>'
    };
    // 插入网络图片的回调
    editor.customConfig.linkImgCallback = function(url) {
    };
    editor.customConfig.zIndex = 100;
    editor.create();
</script>
<footer class="blog-footer" style="padding-top:40px;height:5px;">
    <p style="margin-top: 87px">版权所有 XXXXXXXXXXXXXXXXXX</p>
</footer>
</body>