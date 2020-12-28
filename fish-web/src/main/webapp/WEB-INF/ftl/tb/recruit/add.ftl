<!DOCTYPE html>
<html>

<head>


<#include "/common/header.ftl">
    <link rel="stylesheet" href="${basePath}/static/webupload/webuploader.css">
    <link rel="stylesheet" href="${basePath}/static/webupload/style.css">
    <link href="${basePath}/static/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/layui/css/layui.css">

    <script charset="utf-8" src="${basePath}/static/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${basePath}/static/kindeditor/lang/zh-CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${basePath}/static/webuploader-0.1.5/webuploader.css">




    <style type="text/css">
        .layui-tab-title {border-bottom: 0px;}
        .thumbnail{border: 0px;}
        #fileList{width: 100%;}
        #filePicker{width:86px;height: 40px;}
        .thumbnail{float: left;margin-right: 10px;width: 150px;}

    </style>

</head>

<body class="gray-bg" >

<form class="form-horizontal m-t" id="myForm" action="${basePath}/tb/recruit/add" method="post" enctype="multipart/form-data">

    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">

            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="builder-tabs builder-form-tabs">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="${basePath}/tb/recruit/add">创建</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="ibox-content" style="border:none;">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title">
                                <li class="layui-this">基本信息</li>
                            </ul>
                            <div class="layui-tab-content" style="height: 100%;">

                                <div class="layui-tab-item layui-show">
                                    <div class="ibox-content">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">标题：</label>
                                            <div class="col-sm-3">

                                                <input id="goodsName" type="text" class="form-control" name="title" required="" placeholder="请输入标题" aria-required="true">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">内容详情：</label>
                                            <div class="col-sm-9" >
                                            <textarea id="editor_id" name="detail" style="width:700px;height:300px;">

                                            </textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a  class="btn btn-danger" type="button"  href="${basePath}/tb/recruit/list"><i class="fa fa-close"></i> 返回</a>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>

    </div>
</form>



<!-- 全局js -->
<script src="${basePath}/static/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/static/hplus/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="${basePath}/static/hplus/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${basePath}/static/hplus/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${basePath}/static/hplus/js/plugins/validate/messages_zh.min.js"></script>
<script src="${basePath}/static/common/js/jquery.form.js"></script>
<script src="${basePath}/static/layer/layer.js"></script>
<script src="${basePath}/static/layui/layui.js"></script>
<!-- iCheck -->
<script src="${basePath}/static/hplus/js/plugins/iCheck/icheck.min.js"></script>
<script src="${basePath}/static/webuploader-0.1.5/webuploader.min.js"></script>


<script >


    var flag=false;
    layui.use(['element', 'layer'], function(){
        var element = layui.element()
                ,layer = layui.layer;

        element.on('tab(docDemoTabBrief)', function(data){
            if(data.index==1&&!flag)
            {
                flag=true;
            }
            //layer.msg('切到到了'+ data.index + '：' + this.innerHTML);
        });
    });


    $().ready(function() {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

        var editor = KindEditor.create('textarea[name="detail"]', {
            uploadJson : '${basePath}/common/editUpload',
            allowFileManager : false,
            allowPreviewEmoticons : false,
            items : ['source', '|', 'undo', 'redo', '|', 'preview', 'template', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image','multiimage',
                'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                'anchor', 'link', 'unlink']
        });

        $("#myForm").validate({
            rules: {
                name: "required",
                type: "required"

            }
        });

        $('#myForm').on('submit', function() {

            $(this).ajaxSubmit({
                type: 'post',
                beforeSubmit:function(){

                    return $("#myForm").valid();

                },
                success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                    // 此处可对 data 作相关处理

                    if(data.status == 200){

                        layer.msg(data.message,{icon: 6,time:2000,shade: 0.1},function(index){
                            window.location.href="${basePath}/tb/recruit/list";
                        });
                    }else{
                        layer.msg(data.message,{icon: 5,time:2000,shade: 0.1},function(index){
                            layer.close(index);
                        });
                    }
                }
            });
            return false; // 阻止表单自动提交事件
        });

    });


</script>


</body>

</html>