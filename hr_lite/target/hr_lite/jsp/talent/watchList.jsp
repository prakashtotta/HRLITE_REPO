	<%@ include file="../common/include.jsp" %>
	<%@ include file="../common/autocomplete.jsp" %>
	<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="wform" name="watchListForm" type="com.form.WatchListForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>
<html>
<head>
<title><%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%></title>
<script type="text/javascript">
var ccids="";
var ccnames="";


String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
function strStartsWith(str, prefix) { 
	return str.indexOf(prefix) === 0;
	}

$(function() {

	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#userUserGrpName").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    
		  
		  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.watchListForm.userUserGrpName.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.watchListForm.userUserGrpId.value=idval.substring(1,idval.length);
			 document.watchListForm.isGroup.value="Y";
			 document.watchListForm.userUserGrpNamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.watchListForm.userUserGrpId.value=item.data;
		 document.watchListForm.isGroup.value="N";
		 document.watchListForm.userUserGrpName.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.watchListForm.userUserGrpNamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		}
		});

	});
</script>
<script type="text/javascript">

	function validateUser(){
	document.watchListForm.userUserGrpName.value=document.watchListForm.userUserGrpNamehidden.value;
	}

		function opensearchassignedto(){
			
		   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=watchlistadd");
			window.open(url, "serachuser","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

			}

		function savedata(){

		var alertstr = "";
  
	var watcher = document.watchListForm.userUserGrpId.value;
		
	var showalert=false;

	
	if(watcher == "" || watcher == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Watcher_is_mandatory",user1.getLocale())%>";
		showalert = true;
		}


	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		
		  document.watchListForm.action = "watchlist.do?method=addwatchList&type=<%=wform.getType()%>&applicantId=<%=wform.getApplicantId()%>&reqid=<%=wform.getReqId()%>";
	   document.watchListForm.submit();
	   
		}


	function removedata(checkname){
		//alert(checkname.length);
		var isanyonechecked=false;
		var len = checkname.length; 
		if(len == undefined){
			 if (document.watchListForm.watchlistchk.checked)
		        {
		            isanyonechecked=true;
		        } 
		}else{ 
			for (i=0; i<checkname.length; i++)
			{
			        if (document.watchListForm.watchlistchk[i].checked)
			        {
			            isanyonechecked=true;
			            break;
			        } 
			}
		}			
		if(isanyonechecked == true){	
	        var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.watcher.deletemsg",user1.getLocale())%>");
		  	if (doyou == true){
				document.watchListForm.action = "watchlist.do?method=removewatchList&type=<%=wform.getType()%>&applicantId=<%=wform.getApplicantId()%>&reqid=<%=wform.getReqId()%>";
		  	    document.watchListForm.submit();
		  	}
		}else{
			alert("<%=Constant.getResourceStringValue("Requisition.watchers.selectwatchers",user1.getLocale())%>");
		}
		
	}

	function checkAll(checkname, exby) {
		var len = checkname.length; 
if(len == undefined) len = 1; 
    if(len==1){
		document.watchListForm.watchlistchk.checked = exby.checked? true:false;
	}else{
	for (i = 0; i < len; i++){	  
  checkname[i].checked = exby.checked? true:false;
	}
	}
	}



	</script>
	</head>
	
	<body class="yui-skin-sam">
	<html:form action="/watchlist.do?method=watchList" method="POST">
<br>
<div class="div">
	<table width="100%">
	<tr>
	<td valign="top" width="20%" >
<b><%=Constant.getResourceStringValue("Requisition.add.watchers",user1.getLocale())%></b>
</td>
   	<td>
          	<input type="hidden" name="userUserGrpNamehidden">
           	<input type="text" id="userUserGrpName" name="userUserGrpName" autocomplete="off"   onblur="validateUser()">

			<span id="assignedto"></span>
			<a href="#" onClick="opensearchassignedto();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
			<input type="hidden" name="userUserGrpId" />
			<input type="hidden" name="isGroup" />

	</td>
<td width="35%" >
<input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%>" onClick="savedata()" class="button">
</td>
</tr>
</table>
</div>
<!-- add text box end-->
	<br>
	<!-- wathchers name start-->


<%
List watchLists = wform.getWatchList();
 if(watchLists != null && watchLists.size()>0){
 %>
 	<div class="div">
	<table width="100%">
 <%
   for(int i=0;i<watchLists.size();i++){
   WatchList watch = (WatchList)watchLists.get(i);
%>
	<%if(i==0){ %>
	<tr>
	<td width="15%">&nbsp;</td>
   <td width="15%">
   <input type="checkbox"  name="checkall"  onClick="checkAll(document.watchListForm.watchlistchk,this)">
   </td>
   <td>
  <b> <%=Constant.getResourceStringValue("Requisition.current.watchers",user1.getLocale())%></b>
   </td>
	</tr>

	<%}%>
   <tr>
	   <td width="15%">&nbsp;</td>
	   <td>
	   <input type="checkbox"  name="watchlistchk" value="<%=watch.getWatchListId()%>">
	   </td>
	   <td>
   		<%if(!StringUtils.isNullOrEmpty(watch.getIsGroup()) && watch.getIsGroup().equals("Y")){%>
		<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=watch.getUserUserGrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=watch.getUserUserGrpName()%></a> 

		<%}else{%>
		<img src="jsp/images/user.gif">
		<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=watch.getUserUserGrpId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=watch.getUserUserGrpName()%></a> 
		<%}%>
   	</td>
	</tr>
	<%}%>
<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>

<tr>  
	<td width="15%">&nbsp;</td>
    <td>
  	<input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.watchers.remove",user1.getLocale())%>" onClick="removedata(document.watchListForm.watchlistchk)" class="button">
   	</td>
   	<td>
   
   	</td>
</tr>
	</table>
</div>
<%}%>

	<!-- wathchers name end-->
	</html:form>
	
	</body>
	</html>