<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/jstl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理平台</title>
<link rel="stylesheet" href="${ctx}/resource/css/common.css">
<link rel="stylesheet" href="${ctx}/resource/css/style.css">
<script type="text/javascript" src="${ctx}/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="${ctx}/resource/prompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/resource/prompt/skin/qq/ymPrompt.css" />
<script type="text/javascript">
$(function(){
	$(".sideMenu").slide({
	    titCell:"h3", 
	    targetCell:"ul",
	    defaultIndex:0, 
	    effect:'slideDown', 
	    delayTime:'500' , 
	    trigger:'click', 
	    triggerTime:'150', 
	    defaultPlay:true, 
	    returnDefault:false,
	    easing:'easeInQuint',
	    endFun:function(){
	         scrollWW();
	    }
	});
	$(window).resize(function() {
		scrollWW();
	});
});
</script>
</head>
<body>
    <!-- 这是 头部部分 -->
	<%@ include file="header.jsp"%>
    <!-- 这是 左侧部分 -->
	<%@ include file="left.jsp"%>
    <!-- 这是 右侧部分 -->
	<div class="main">
   		<iframe name="rightFrame" id="rightFrame" src="/test/workbench" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>
	</div>
    <!-- 这是 底部部分 -->
	<%@ include file="footer.jsp"%>
</body>
</html>