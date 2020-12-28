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

<form class="form-horizontal m-t" id="myForm" action="${basePath}/join/our/edit" method="post" enctype="multipart/form-data">

    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">

            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="builder-tabs builder-form-tabs">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="${basePath}/join/our/edit">编辑</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="ibox-content" style="border:none;">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                            <ul class="layui-tab-title">
                                <li class="layui-this">基本信息</li>
                                <li>图片信息</li>
                            </ul>
                            <div class="layui-tab-content" style="height: 100%;">

                                <div class="layui-tab-item layui-show">

                                    <div class="ibox-content">

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">电话：</label>
                                            <div class="col-sm-3">

                                                <input id="goodsName" value="${joinOur.telephone}" type="text" class="form-control" name="telephone" required="" placeholder="请输入公司电话" aria-required="true"><span class="help-block m-b-none">两个电话之间用英文逗号（,）隔开</span>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">传真：</label>
                                            <div class="col-sm-3">
                                                <input id="goodsSn" value="${joinOur.fax}" type="text" class="form-control" name="fax" required="" placeholder="请输入公司传真" aria-required="true">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">邮箱：</label>
                                            <div class="col-sm-3">
                                                <input id="nowPrice" value="${joinOur.email}" type="text" class="form-control" name="email" required="" placeholder="请输入邮箱地址" aria-required="true">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">地址：</label>
                                            <div class="col-sm-3">
                                                <input id="originalPrice" value="${joinOur.address}" type="text" class="form-control" name="address" required="" placeholder="请输入公司地址" aria-required="true">
                                            </div>
                                        </div>

                                    </div>


                                </div>
                                <div class="layui-tab-item">
                                    <div class="layui-tab-item layui-show">
                                        <div class="ibox-content">

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">二维码一(144px*144px)：</label>
                                                <div class="col-sm-9" >
                                                    <div >
                                                        <!--用来存放item-->
                                                        <img id="imgPhoto1" src="${joinOur.imageOne}" width="150px" height="150px"/>
                                                        <input id="photo1"  value="${joinOur.imageOne}"  type="hidden" name="imageOne" value=""/>
                                                        <div id="filePicker1">选择图片</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">二维码标题(一)：</label>
                                                <div class="col-sm-3">
                                                    <input id="describe" value="${joinOur.describe}" type="text" class="form-control" name="describe" required="" placeholder="请输入二维码标题" aria-required="true">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">二维码二(144px*144px)：</label>
                                                <div class="col-sm-9" >
                                                    <div >
                                                        <!--用来存放item-->
                                                        <img id="imgPhoto3" src="${joinOur.imageTwo}" width="150px" height="150px"/>
                                                        <input id="photo3"  value="${joinOur.imageTwo}"  type="hidden" name="imageTwo" value=""/>
                                                        <div id="filePicker3">选择图片</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">二维码标题(二)：</label>
                                                <div class="col-sm-3">
                                                    <input id="described" value="${joinOur.describeTwo}" type="text" class="form-control" name="describeTwo" required="" placeholder="请输入二维码标题" aria-required="true">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">地图(995px*580px)：</label>
                                                <div class="col-sm-9" >
                                                    <div >
                                                        <!--用来存放item-->
                                                        <img id="imgPhoto4" src="${joinOur.mapImage}" width="150px" height="150px"/>
                                                        <input id="photo4"  value="${joinOur.mapImage}"  type="hidden" name="mapImage" value=""/>
                                                        <div id="filePicker4">选择图片</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <input type="hidden" name="id" value=" ${joinOur.id}"/>
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a  class="btn btn-danger" type="button"  href="${basePath}/join/our/list"><i class="fa fa-close"></i> 返回</a>
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
                initUploadSingle();
                initUploadSinglest();
                initUploadSingled();
            }
            //layer.msg('切到到了'+ data.index + '：' + this.innerHTML);
        });
    });
    function initUploadSingle(){
        ratio = window.devicePixelRatio || 1,
         // 缩略图大小
        thumbnailWidth = 150 * ratio;
        thumbnailHeight = 150 * ratio;

        // Web Uploader实例
        var uploader1;

        // 初始化Web Uploader
        uploader1 = WebUploader.create({
            // 自动上传。
            auto: true,

            // swf文件路径
            swf:  '${basePath}/static/webuploader-0.1.5/Uploader.swf',

            // 文件接收服务端。
            server: '${basePath}/common/uploadOss',

            // [默认值：'file']  设置文件上传域的name。
            fileVal:'upfile',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker1',

            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        // 当有文件添加进来的时候
        uploader1.on( 'fileQueued', function( file ) {

            // 创建缩略图
            uploader1.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $("#imgPhoto1").replaceWith('<span>不能预览</span>');
                    return;
                }

                $("#imgPhoto1").attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader1.on( 'uploadProgress', function( file, percentage ) {

            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress span');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $li )
                        .find('span');
            }

            $percent.css( 'width', percentage * 100 + '%' );
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader1.on( 'uploadSuccess', function( file,data ) {


            //var fileList = $.parseJSON(callBack);//转换为json对象
            // alert('upload success\n'+data);
            $('#photo1').val(data);
            $( '#'+file.id ).addClass('upload-state-done');
        });

        // 文件上传失败，现实上传出错。
        uploader1.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ),
                    $error = $li.find('div.error');

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败!!!');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader1.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
        uploader1.on('error', function(handler) {
            if (handler == "Q_EXCEED_NUM_LIMIT") {
                alert("只能上传一张图片");
            }
        });

    }

    function initUploadSinglest(){
        ratio = window.devicePixelRatio || 1,
                // 缩略图大小
                thumbnailWidth = 150 * ratio;
        thumbnailHeight = 150 * ratio;

        // Web Uploader实例
        var uploader1;

        // 初始化Web Uploader
        uploader1 = WebUploader.create({
            // 自动上传。
            auto: true,

            // swf文件路径
            swf:  '${basePath}/static/webuploader-0.1.5/Uploader.swf',

            // 文件接收服务端。
            server: '${basePath}/common/uploadOss',

            // [默认值：'file']  设置文件上传域的name。
            fileVal:'upfile',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker3',

            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        // 当有文件添加进来的时候
        uploader1.on( 'fileQueued', function( file ) {

            // 创建缩略图
            uploader1.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $("#imgPhoto3").replaceWith('<span>不能预览</span>');
                    return;
                }

                $("#imgPhoto3").attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader1.on( 'uploadProgress', function( file, percentage ) {

            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress span');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $li )
                        .find('span');
            }

            $percent.css( 'width', percentage * 100 + '%' );
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader1.on( 'uploadSuccess', function( file,data ) {

            $('#photo3').val(data);
            $( '#'+file.id ).addClass('upload-state-done');
        });

        // 文件上传失败，现实上传出错。
        uploader1.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ),
                    $error = $li.find('div.error');

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败!!!');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader1.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
        uploader1.on('error', function(handler) {
            if (handler == "Q_EXCEED_NUM_LIMIT") {
                alert("只能上传一张图片");
            }
        });

    }

    function initUploadSingled(){
        ratio = window.devicePixelRatio || 1,
                // 缩略图大小
                thumbnailWidth = 150 * ratio;
        thumbnailHeight = 150 * ratio;

        // Web Uploader实例
        var uploader1;

        // 初始化Web Uploader
        uploader1 = WebUploader.create({
            // 自动上传。
            auto: true,

            // swf文件路径
            swf:  '${basePath}/static/webuploader-0.1.5/Uploader.swf',

            // 文件接收服务端。
            server: '${basePath}/common/uploadOss',

            // [默认值：'file']  设置文件上传域的name。
            fileVal:'upfile',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker4',

            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        // 当有文件添加进来的时候
        uploader1.on( 'fileQueued', function( file ) {

            // 创建缩略图
            uploader1.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $("#imgPhoto4").replaceWith('<span>不能预览</span>');
                    return;
                }

                $("#imgPhoto4").attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader1.on( 'uploadProgress', function( file, percentage ) {

            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress span');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $li )
                        .find('span');
            }

            $percent.css( 'width', percentage * 100 + '%' );
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader1.on( 'uploadSuccess', function( file,data ) {


            //var fileList = $.parseJSON(callBack);//转换为json对象
            // alert('upload success\n'+data);
            $('#photo4').val(data);
            $( '#'+file.id ).addClass('upload-state-done');
        });

        // 文件上传失败，现实上传出错。
        uploader1.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ),
                    $error = $li.find('div.error');

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败!!!');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader1.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
        uploader1.on('error', function(handler) {
            if (handler == "Q_EXCEED_NUM_LIMIT") {
                alert("只能上传一张图片");
            }
        });

    }




    $().ready(function() {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
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
                            window.location.href="${basePath}/join/our/list";
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