<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/jstl.jsp"%>
<link rel="stylesheet" href="${ctx}/resource/css/common.css">
<link rel="stylesheet" href="${ctx}/resource/css/main.css">
<script type="text/javascript" src="${ctx}/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/common.js"></script>
<div class="container">
	<div id="forms" class="mt10">
		<div class="box">
			<div class="box_border">
				<div class="box_center">
					<form action="${ctx }/reply/saveConfig" class="jqtransform" onsubmit="return toVaild()" method="post">
					<table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
						<input type="hidden" name="config.id" value="${entity.id }">
						<tr>
							<td class="td_right">URL：</td>
							<td>${ctx }/wx/api</td>
						</tr>
						<tr>
							<td class="td_right">TOKEN：</td>
							<td><input type="text" name="config.token" class="input-text lh30" size="40" maxlength="20" value="${entity.token }">
							可自定义修改
							</td>
						</tr>
						<tr>
							<td class="td_right">端口：</td>
							<td>${pageContext.request.serverPort}</td>
						</tr>
						<tr>
							<td class="td_right">appId：</td>
							<td><input type="text" name="config.appId" class="input-text lh30" size="40" maxlength="50" value="${entity.appId }">
							微信公众号后台获取
							</td>
						</tr>
						<tr>
							<td class="td_right">appSecret：</td>
							<td><input type="text" name="config.appSecret" class="input-text lh30" size="40" maxlength="50" value="${entity.appSecret }">
							微信公众号后台获取
							</td>
						</tr>
         				<tr>
              				<td class="td_right">&nbsp;</td>
               				<td class="">
                				<input type="submit" name="button" class="btn btn82 btn_save2" value="保存"> 
               					<input type="reset" name="button" class="btn btn82 btn_res" value="重置"> 
               					<span id="err" style="color: red;text-align: center;">${msg }</span>
               				</td>
              			</tr>
           			</table>
           			</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function toVaild(){
	return true;
}
</script>