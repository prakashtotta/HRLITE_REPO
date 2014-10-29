<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/autocompleteMultiple.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<title><%=Constant.getResourceStringValue("hr.user.Usergroup_Details",user1.getLocale())%></title>


<style>
span1{color:#ff0000;}
fieldset {
	width: 475px;
	border: 1px solid #999;
	padding: 10px;
}


legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}

</style>




<bean:define id="cform" name="userGroupForm" type="com.form.UserGroupForm" />


<script language="javascript">

function openuserwindow(userid){

	window.open('user.do?method=edituser&readPreview=2&userId='+userid, 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}

function closewindow(){

	 	  parent.parent.GB_hide(); 
	  
	}

function closewindow2(){

	 	  parent.parent.GB_hide(); 

	  
	}

function deleteuser(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.userGroupForm.action = "usergroup.do?method=deleteUserGroup&usergroupId=<%=cform.getUsergrpId()%>";
   document.userGroupForm.submit();

	   } 
	}

function suspend(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.suspendmsg.usergroup",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.userGroupForm.action = "usergroup.do?method=suspendUserGroup&usergroupId=<%=cform.getUsergrpId()%>";
   document.userGroupForm.submit();

	   } 
	}	

function activate(){
	var theSel = window.document['userGroupForm'].users;
  var userids ="";
    for(i=theSel.length-1; i>=0; i--)
    {

	userids = userids + theSel.options[i].value + ",";
	}
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.usergroup.activatemsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.userGroupForm.action = "usergroup.do?method=activate&userids="+userids+"&usergroupid=<%=cform.getUsergrpId()%>";
   document.userGroupForm.submit();

	   } 
	}	


function opensearchwatchlist(){
  		   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector&boxnumber=usergroup");
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   parent.parent.GB_hide();
	   } 
	}


function init(){
setTimeout ( "document.userGroupForm.usergrpName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){

var alertstr = "";
var userids ="";
	var usergrpName = document.userGroupForm.usergrpName.value.trim();
	var desc = document.userGroupForm.usergrpDesc.value.trim();
	var showalert=false;

   if(usergrpName == "" || usergrpName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.group.group_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	

userids = $('input[name="users"]').val();  

	  document.userGroupForm.action = "usergroup.do?method=saveusergroup&userids="+userids;
   document.userGroupForm.submit();
	}


function setUserValues(newText, newValue){
	 var theSel = window.document['userGroupForm'].users;
	//alert(theSel+" "+theSel.length+" "+newText);
  if (theSel.length == 0) {
    var newOpt1 = new Option(newText, newValue);
    theSel.options[0] = newOpt1;
    theSel.selectedIndex = 0;
  } else if (theSel.selectedIndex != -1) {
    var selText = new Array();
    var selValues = new Array();
    var selIsSel = new Array();
    var newCount = -1;
    var newSelected = -1;
    var i;
    for(i=0; i<theSel.length; i++)
    {
      newCount++;
      selText[newCount] = theSel.options[i].text;
      selValues[newCount] = theSel.options[i].value;
      selIsSel[newCount] = theSel.options[i].selected;
      
      if (newCount == theSel.selectedIndex) {
        newCount++;
        selText[newCount] = newText;
        selValues[newCount] = newValue;
        selIsSel[newCount] = false;
        newSelected = newCount - 1;
      }
    }
    for(i=0; i<=newCount; i++)
    {
      var newOpt = new Option(selText[i], selValues[i]);
      theSel.options[i] = newOpt;
      theSel.options[i].selected = selIsSel[i];
    }
  }
}

function removeUsersfromlist()
{
	var theSel = window.document['userGroupForm'].users;
  var selIndex = theSel.selectedIndex;
  if (selIndex != -1) {
    for(i=theSel.length-1; i>=0; i--)
    {
      if(theSel.options[i].selected)
      {
        theSel.options[i] = null;
      }
    }
    if (theSel.length > 0) {
      theSel.selectedIndex = selIndex == 0 ? 0 : selIndex - 1;
    }
  }
}


function updatedata(){

	var alertstr = "";
var userids ="";
	var usergrpName = document.userGroupForm.usergrpName.value.trim();
	var desc = document.userGroupForm.usergrpDesc.value.trim();
	var showalert=false;

   if(usergrpName == "" || usergrpName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.group.group_name_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	
	 if (showalert){
     	alert(alertstr);
        return false;
          }


  
userids = $('input[name="users"]').val();
	 document.userGroupForm.action = "usergroup.do?method=updateusergroup&userids="+userids+"&usergroupid=<%=cform.getUsergrpId()%>";
  document.userGroupForm.submit();
}
	
</script>
<%
String isusergrpactivated = (String)request.getAttribute("isusergrpactivated");

if(isusergrpactivated != null && isusergrpactivated.equals("yes")){
%>
<div align="center" class="button">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.usergroupr_activated_succ",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>


<%
String isusergrpadded = (String)request.getAttribute("isusergrpadded");
String isusergrsuspended = (String)request.getAttribute("isusergrsuspended");	
if(isusergrpadded != null && isusergrpadded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.usergroup_added_succ",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String isusergrpupdated = (String)request.getAttribute("isusergrpupdated");
	
if(isusergrpupdated != null && isusergrpupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.usergroup_updated_succ",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%	
if(cform.getStatus() != null && cform.getStatus().equals("I")){
	if(isusergrsuspended==null){
%>

<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.usergroupr_suspended",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
<%}%>
<%}%>

<%
if(isusergrsuspended != null && isusergrsuspended.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.usergroupr_suspended_succ",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else if(cform.getStatus() != null && cform.getStatus().equals("D")){%>

<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("hr.user.usergroupr_deleted_succ",user1.getLocale())%><font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<body onload="init()">
<html:form action="/usergroup.do?method=logon" >

<%
if (cform.getReadPreview() != null && cform.getReadPreview().equals("2")){
%>
 <fieldset><legend><%=Constant.getResourceStringValue("hr.user.Usergroup_Details",user1.getLocale())%></legend>
  <div class="personPopupResult">
	 <table border="0" width="100%">

	
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.group_name.name",user1.getLocale())%></td>
			<td><%=(cform.getUsergrpName() ==null)?"":cform.getUsergrpName()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.group_description",user1.getLocale())%></td>
			<td><%=(cform.getUsergrpDesc()==null)?"":cform.getUsergrpDesc()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("rule.user.names",user1.getLocale())%></td>
		
			
		
		<td>
		<% 
			int i=0;
			if(cform.getUsersset() != null && cform.getUsersset().size()>0){
             Iterator itr = cform.getUsersset().iterator();
			while(itr.hasNext()){
				User touser = (User)itr.next();
				 String selectedtext = "";
				 i++;
				 if(i==cform.getUsersset().size()){
				 selectedtext = "selected='selected'";
				 }

		%>
		
		
			
			<!--  <td><%=touser.getFirstName()+" "+touser.getLastName()%></td> -->
			
			<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=touser.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=550')"><%=touser.getFirstName()+" "+touser.getLastName()%></a>
			
	
		

		
		<%}}%>
		</td>
		</tr>

	</table>
	</div>
</fieldset>
<%}else{ %>


<br>
     <div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

    <input type="hidden" name="useridlist" value="<%=cform.getUseridListVal()%>"/>
	 
		<tr>
			<td width="100px"><%=Constant.getResourceStringValue("hr.user.group_name.name",user1.getLocale())%><font color="red">*</font></td>
			<td width="350px"><html:text property="usergrpName" size="45" maxlength="200"/></td>
		</tr>
		<tr>
			<td width="100px"><%=Constant.getResourceStringValue("hr.user.group_description",user1.getLocale())%></td>
			<td width="350px"><html:textarea property="usergrpDesc" cols="40" rows="5"/></td>
		</tr>

		<tr>
			<td width="100px"><%=Constant.getResourceStringValue("rule.user.names",user1.getLocale())%></td>
			
			<td width="350px"> 

             <table>
			 <tr>
			 <td>

		<% 
						 
			String data="";
			if(cform.getUsersset() != null && cform.getUsersset().size()>0){
             Iterator itr = cform.getUsersset().iterator();

			while(itr.hasNext()){
				User touser = (User)itr.next();
							
				 data = data + "{ "+"\""+"id"+"\""+":"+"\""+touser.getUserId()+"\","+"  "+"\""+"name"+"\""+":"+"\""+touser.getFirstName()+ " "+touser.getLastName()+"\""+"}"+",";
			}
		
		if(!StringUtils.isNullOrEmpty(data)){
			data = data.substring(0,data.length()-1);
		}
			}
			
		%>
        
	
         
        <input type="text" id="demo-input-pre-populated" name="users" />
		<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#demo-input-pre-populated").tokenInput("jsp/talent/getUserDataJsonMuti.jsp", {
				 preventDuplicates: true,
			     hintText: "Type in a search user",
                prePopulate: [
						<%=data%>
                ]
            });
        });
        </script>
   
          </td>
		  </tr>
		  </table>
	   
			</td>
		</tr>
		
<tr><td colspan="2">
		<div>


			<% if (cform.getUsergrpId() > 0){%>
				<% if(cform.getStatus() != null && !cform.getStatus().equals("I")){
			
			%>
				<input type="button" id="updateusergroup" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteuser()" class="button">
				<%}%>
				<% if(cform.getStatus() != null && cform.getStatus().equals("A")){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.suspend",user1.getLocale())%>" onClick="suspend()" class="button">
				<%}%>
				<% if(cform.getStatus() != null && cform.getStatus().equals("I")){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.activate",user1.getLocale())%>" onClick="activate()" class="button">
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteuser()" class="button">
				<%}%>
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</div>
</tr></td>

		
	</table>
	 
</div>
<%} %>
</html:form>
<%}%>


