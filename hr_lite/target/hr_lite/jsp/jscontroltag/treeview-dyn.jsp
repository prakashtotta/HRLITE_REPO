<%@ include file="../common/jscontroltag.jsp" %>
<html>
	<head>
		<title>test</title>
		
		<style>
			.mover {
			background-color: #FF9999;
			color: #FFEEEE;
			}
			
			.special {
			color: #CC0000;
			}
			
			.tafelTree h3, .tafelTree p, .tafelTree ol {
			margin: 0;
			padding: 0;
			}
			
			.tafelTree p {
			padding-bottom: 1em;
			}
			
			.tafelTree h3 {
			color: #009900;
			}
		</style>		
	</head>
	<body>
		
		<div id="dynamicTreeview" />
		
		
		
	</body>
</html>

<jscontrols-ajax:treeview-dyn tree="${requestScope.dynamicTreeview}"
							  source="dynamicTreeview"
							  baseUrl="${pageContext.request.contextPath}/ajaxtreeview.do"
							  imgBase="${pageContext.request.contextPath}/jsp/jscontroltag/treeview/"
							  width="100%"
							  height="auto"
					 	 	  var="objDynamicTreeview"
					 	 	  checkboxesThreeState="false"
					 	 	  multiline="true"
					 	 	  defaultImg="page.gif" 
					 	 	  defaultImgOpen="folderopen.gif"
					 	 	  defaultImgClose="folder.gif" 
					 	 	  behaviourDrop="sibling" />  	