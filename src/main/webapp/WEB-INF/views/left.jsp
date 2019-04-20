<%@ page contentType="text/html;charset=UTF-8" %>
<div class="side">
	<div class="sideMenu" style="margin:0 auto">
		<h3>个人管理</h3>
		<ul>
			<li><a href="${ctx }/test/info" target="rightFrame">个人信息</a></li>
			<li><a href="#" onclick="ymPrompt.win({title:'修改密码',height:'230',width:'500',dragOut:false,iframe:true,message:'${ctx}/test/toPsw'});">密码修改</a></li>
			<li><a href="${ctx }/test/list" target="rightFrame">管理员列表</a></li>
		</ul>
		<h3>微信管理</h3>
		<ul>
			<li><a href="${ctx }/test/initConfig" target="rightFrame">微信配置</a></li>
			<li><a href="${ctx }/test/initMenu" target="rightFrame">自定义菜单</a></li>
			
		</ul>
		<h3>回复管理</h3>
		<ul>
			<li><a href="${ctx }/test/toInit?reply.keyword=subscribe" target="rightFrame">关注回复</a></li>
			<li><a href="${ctx }/test/toInit?reply.keyword=error" target="rightFrame">默认回复</a></li>
			<li><a href="${ctx }/test/list" target="rightFrame">文字回复</a></li>
			<li><a href="${ctx }/test/list" target="rightFrame">图文回复</a></li>
		</ul>
<!-- 		<h3>样式管理</h3>
		<ul>
			<li><a href="test" target="rightFrame">样式列表</a></li>
		</ul>
 -->   
 </div>
</div>