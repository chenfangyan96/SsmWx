<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/jstl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${ctx }/resource/css/common.css">
<link rel="stylesheet" href="${ctx }/resource/css/main.css">
<script type="text/javascript" src="${ctx }/resource/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/js/common.js"></script>
<script type="text/javascript" src="${ctx }/resource/prompt/ymPrompt.js"></script>
<link rel="stylesheet" id='skin' type="text/css" href="${ctx }/resource/prompt/skin/qq/ymPrompt.css" />
</head>
<body>
<div class="container">
<div id="search_bar" class="mt10">
	<div class="box">
		<div class="box_border">
           	<div class="box_top"><b class="pl15">搜索栏目</b></div>
           	<div class="box_center pt10 pb10">
               	<form action="${ctx }/reply/list" method="post" id="searchForm">
             	<table class="form_table" border="0" cellpadding="0" cellspacing="0">
               	<tr>
               		<td>关键字</td>
               		<td><input type="text" name="reply.keyword" class="input-text lh25" size="20" value="${entity.keyword }"></td>
               		<td>内容</td>
               		<td><input type="text" name="reply.content" class="input-text lh25" size="20" value="${entity.content }"></td>
                	<td><input type="submit" name="button" class="btn btn82 btn_search" value="查询"></td>
                	<td><input type="button" name="button" class="btn btn82 btn_add add_" value="新增"></td>
               	</tr>
				</table>
               	</form>
 			</div>
		</div>
	</div>
</div>

<div id="table" class="mt10">
	<div class="box span10 oh">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
			<tr>
           		<th width="5%">编号</th>
           		<th width="20%">关键字</th>
           		<th>内容</th>
         		<th width="10%">操作</th>
            </tr>
            <c:if test="${pager.totalRecord == 0 }">
				<tr><td colspan="4" align="center">暂无记录</td></tr>
			</c:if>
            <c:forEach items="${pager.datas}" var="d" varStatus="v">
				<tr class="tr">
					<td class="td_center">${v.index + 1 }</td>
					<td class="td_center">${d.keyword }</td>
					<td class="td_center">${d.content }</td>
					<td class="td_center">
						<input type="button" data="${d.id }" class="ext_btn ext_btn_success edit_" value="编辑">
						<input type="button" data="${d.id }" class="ext_btn ext_btn_error delete_" value="删除">
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<jsp:include page="../common/pager.jsp">
			<jsp:param value="${pager.totalRecord }" name="items"/>
		</jsp:include>
	</div>
</div>

</div> 
<script type="text/javascript">
var delete_id;
$(function(){
	$(".add_").click(function(){
        ymPrompt.win({title:'添加',height:'350',width:'500',dragOut:false,iframe:true,message:'${ctx}/reply/toAdd',handler:refresh});
    })
    
	$(".edit_").click(function(){
        var id = $(this).attr("data");
        ymPrompt.win({title:'修改',height:'350',width:'500',dragOut:false,iframe:true,message:'${ctx}/reply/toUpdate?reply.id=' + id,handler:refresh});
    })
    
	$(".delete_").click(function(){
        delete_id = $(this).attr("data");
        ymPrompt.confirmInfo({message:'您确定删除此信息？',handler:handlerDel});
    })
});

function handlerDel(tp){
	if(tp=='ok'){
		$.ajax({
            type : "GET",
            url : '${ctx}/reply/delete?reply.id=' + delete_id,
            dataType : "text",
            success : function(result) {
				refresh();
            }
        });
	}
}
</script>
</body>
</html>