<!DOCTYPE html>
<html>

<head>

	


  
</head>

<body >




<div >


    <!-- 1.获取到规则流程图 -->
    <img style="position: absolute;top: 0px;left: 0px;" src="${basePath}/activiti/bpm/viewImage?deploymentId=${deploymentId}&diagramResourceName=${diagramResourceName}">

    <!-- 2.根据当前活动的坐标，动态绘制DIV -->
    <div style="position: absolute;border:1px solid red;top:${acs.y}px;left: ${acs.x}px;width: ${acs.width}px;height:${acs.height}px;   "></div>



</div>



</body>

</html>