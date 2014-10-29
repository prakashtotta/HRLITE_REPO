




<%

String rootdata = TalentPoolBO.getTalentPoolListWithTreeFormat(user1.getUserId());
%>

<body class="yui-skin-sam">
<div class="contextMenu" id="myMenu1">	
		<li class="addFolder">
			<img src="jsp/jtree/js/jquery/plugins/simpleTree/images/folder_add.png" /> </li>
		<!--<li class="addDoc"><img src="js/jquery/plugins/simpleTree/images/page_add.png" /> </li>	-->
		<li class="edit"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/folder_edit.png" /> </li>
		<li class="delete"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/folder_delete.png" /> </li>
		<li class="expandAll"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/expand.png"/> </li>
		<li class="collapseAll"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/collapse.png"/> </li>	
</div>
<div class="contextMenu" id="myMenu2">
		<!--<li class="edit"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/page_edit.png" /> </li>-->
		<li class="delete"><img src="jsp/jtree/js/jquery/plugins/simpleTree/images/page_delete.png" /> </li>
</div>

<table>

<tr>
<td valign="top">

           <div id="annualWizard">
			<ul class="simpleTree" id='pdfTree'>		
					<li class="root" id='0'>Talent pools
						<ul>
						
	                  <%=rootdata%>


						</ul>	
					</li>
				
			</ul>
			</div>
			

   
</body>