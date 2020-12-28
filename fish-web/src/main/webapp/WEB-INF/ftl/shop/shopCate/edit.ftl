<!DOCTYPE html>
<html>

<head>


<#include "/common/header.ftl">
    <link rel="stylesheet" href="${basePath}/static/webupload/webuploader.css">
    <link rel="stylesheet" href="${basePath}/static/webupload/style.css">



</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">

        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>编辑</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>

                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="myForm" action="${basePath}/shop/shopCate/edit" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-3">
                                <input id="name" type="text" value="${item.name}" class="form-control" name="name" required="" placeholder="请输入属性名称" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">图片(100px*100px)：</label>
                            <div class="col-sm-9" >
                                <div >
                                    <!--用来存放item-->
                                    <img id="imgPhoto1" src="${item.imgUrl}" />
                                    <input id="photo1"  type="hidden" name="imgUrl" value="${item.name}"/>
                                    <div id="filePicker1">选择图片</div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <input type="hidden" name="cateId" value="${item.cateId}" />
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a  class="btn btn-danger" type="button"  href="${basePath}/shop/shopCate/list"><i class="fa fa-close"></i> 返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>


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
<script src="${basePath}/static/hplus/js/plugins/iCheck/icheck.min.js"></script>
<script src="${basePath}/static/webupload/webuploader.min.js"></script>
<script >
    $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    function initUploadSingle(){
        ratio = window.devicePixelRatio || 1,
                // 缩略图大小
                thumbnailWidth = 100 * ratio;
        thumbnailHeight = 100 * ratio;
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
            var $li = $( '#'+file.id ), $percent = $li.find('.progress span');
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
            $('#photo1').val(data);
            $( '#'+file.id ).addClass('upload-state-done');
        });

        // 文件上传失败，现实上传出错。
        uploader1.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ), $error = $li.find('div.error');
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
        initUploadSingle();
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
                            window.location.href="${basePath}/shop/shopCate/list";
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