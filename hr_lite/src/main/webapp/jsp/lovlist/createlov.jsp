<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.Locale"%>


<html>
<bean:define id="lovListForm" name="lovListForm"	type="com.form.LovListForm"/>
<%
System.out.println("lov list id : "+lovListForm.getLovListId() );
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
List lovlistvaluecodeList = lovListForm.getLovlistvaluecodeList();

String editmode = (String)request.getAttribute("editmode");
%>
<style>
span1{color:#ff0000;}
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;


}
</style>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

			 parent.parent.GB_hide();
	   } 
	}


	
function init(){
setTimeout ( "document.lovListForm.lovListCode.focus(); ", 200 );
document.lovListForm.lovListValueCode.value="";
document.lovListForm.lovListValueName.value="";
//document.roleForm.roleCode.focus();
}

function savedata(){
var alertstr = "";
	var showalert=false;
	var code = document.lovListForm.lovListCode.value.trim();	

			 
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("lov.lovlist.lovcode_required",user1.getLocale())%><br>";
		showalert = true;
		}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	document.lovListForm.action = "lovlist.do?method=saveLov";
 	document.lovListForm.submit();
  
	}



function updatedata(){
	var alertstr = "";
	var showalert=false;
	var code = document.lovListForm.lovListCode.value.trim();	

			 
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("lov.lovlist.lovcode_required",user1.getLocale())%><br>";
		showalert = true;
		}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
     document.lovListForm.action = "lovlist.do?method=updateLov&lovid="+'<bean:write name="lovListForm" property="lovListId"/>';
	 document.lovListForm.submit();

 
	}
	function addLoveListValue(){
		 
		var alertstr = "";
		var showalert=false;
		var code = document.lovListForm.lovListValueCode.value.trim();	
		var valuename = document.lovListForm.lovListValueName.value.trim();
		
		
		
				 
		if(code == "" || code == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("lov.lovlist.lovvaluecode.mandatory",user1.getLocale())%><br>";
			showalert = true;
			}
		if(valuename == "" || valuename == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("lov.lovlist.lovvalue.mandatory",user1.getLocale())%><br>";
			showalert = true;
			}

		 if (showalert){
	     	alert(alertstr);
	        return false;
	          }
	 	//parent.parent.GB_hide();
	     document.lovListForm.action = "lovlist.do?method=addLoveListValue&lovid="+'<bean:write name="lovListForm" property="lovListId"/>'+"&lovListCode="+'<bean:write name="lovListForm" property="lovListCode"/>';
		 document.lovListForm.submit();
		
	}
	function deletelovlistcodevalue(lovlistid){

		// alert(lovlistid);
	 var doyou = confirm("<%=Constant.getResourceStringValue("refferal.common.delete",user1.getLocale())%>");
	  if (doyou == true){
	     document.lovListForm.action = "lovlist.do?method=deleteLovlistcode&lovid="+lovlistid;
		 document.lovListForm.submit();
	  } 
	
	}
	function Inactivate(){
		//alert("inactive ");
	     document.lovListForm.action = "lovlist.do?method=inActiveLovlist&lovid="+'<bean:write name="lovListForm" property="lovListId"/>';
		 document.lovListForm.submit();
	}
	function activate(){
		//alert("active");
	     document.lovListForm.action = "lovlist.do?method=activeLovlist&lovid="+'<bean:write name="lovListForm" property="lovListId"/>';
		 document.lovListForm.submit();
	}
	function closewindow(){

		 parent.parent.GB_hide();
	}

</script>


<%
String lovupdated = (String)request.getAttribute("lovupdated");
	
if(lovupdated != null && lovupdated.equals("yes")){
%>
<div align="left">
	<table border="0" width="50%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("lov.lovlist.lovupdatesucceed",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String lovsaved = (String)request.getAttribute("lovsaved");
	
if(lovsaved != null && lovsaved.equals("yes")){
%>
<div align="left">
	<table border="0" width="50%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("lov.lovlist.lovsavesucceed",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String lovlistdeactivated = (String)request.getAttribute("lovlistdeactivated");
	
if(lovlistdeactivated != null && lovlistdeactivated.equals("yes")){
%>
<div align="left">
	<table border="0" width="50%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("lov.lovlist.deactivated.msg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String lovlistactivated = (String)request.getAttribute("lovlistactivated");
	
if(lovlistactivated != null && lovlistactivated.equals("yes")){
%>
<div align="left">
	<table border="0" width="50%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("lov.lovlist.activated.msg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(! StringUtils.isNullOrEmpty(lovListForm.getStatus())&& lovListForm.getStatus().equals("I") && StringUtils.isNullOrEmpty(lovlistdeactivated)){
%>
<div align="left">
	<table border="0" width="50%">
	
	
	<tr>
			<td><font color="red"><%=Constant.getResourceStringValue("lov.lovlist.inactivated",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td> -->
		</tr>
		
	</table>
</div>

<%}%>
<body class="yui-skin-sam" onload="init()">
<html:form action="/lovlist.do?method=saveLov">



	
<%
String readpreview = (String)request.getAttribute("readpreview");
if (readpreview != null && readpreview.equals("2")){

%>	

<br>
	 <fieldset><legend><%=Constant.getResourceStringValue("lov.lovlist.lovlistdetails",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
	
	
		<tr>
			<td><%=Constant.getResourceStringValue("lov.lovlist.lovcode",user1.getLocale())%></td>
			<td><%=(lovListForm.getLovListCode()==null)?"":lovListForm.getLovListCode()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("lov.lovlist.locale",user1.getLocale())%></td>
			<td><%=(lovListForm.getLovListCode()==null)?"":lovListForm.getLovListCode()%></td>
		</tr>



		

		</table>
		</fieldset>
		
	<%}else{%>	
	<font color = red ><html:errors /> </font>
	<table border="0" width="100%">
	
		<tr>
			<td></td>
			<td></td>
		</tr>
	
		

         <%if (lovListForm.getLovListId() > 0){%>
         <tr>
			<td><%=Constant.getResourceStringValue("lov.lovlist.lovcode",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="lovListCode" maxlength="100" readonly="true"/></td>
		</tr>
		<%}else{%>
		<tr>
			<td><%=Constant.getResourceStringValue("lov.lovlist.lovcode",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="lovListCode" maxlength="100"/></td>
		</tr>
		<%}%>

		</table>
		<br>
		<table border="0" width="100%">
		<tr>
		<td><%if (lovListForm.getLovListId() > 0){%>

			<!--<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()">-->
			<%if(lovListForm.getStatus().equals("A")){ %>
			<!--<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.inactive",user1.getLocale())%>" onClick="Inactivate()">-->
			<%}else if(lovListForm.getStatus().equals("I")){%>
			<!--<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.active",user1.getLocale())%>" onClick="activate()">-->
			<%}%>
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()">
			<%}%>
			<!--<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">-->
			</td>
			<td></td>
		</tr>
		</table>
	<%}%>
	<%if (lovListForm.getLovListId() > 0 && editmode != null && editmode.equals("yes")){%>

	


	<table border="0" width="100%">
	
		<tr>
			<td></td>
			<td></td>
		</tr>
	
		<tr>
			<td width="220px"><%=Constant.getResourceStringValue("lov.lovlist.lovvaluecode",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="lovListValueCode" maxlength="100"/></td>
		</tr>
		<tr>
			<td width="220px"><%=Constant.getResourceStringValue("lov.lovlist.lovvalue",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="lovListValueName" maxlength="100"/></td>
		
			
		</tr>
		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("lov.lovlist.locale",user1.getLocale())%></td>
			<td>
			<html:select  property="localeId">
			<option value=""></option>
			<bean:define name="lovListForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
			<td width="320px"><input type="button" name="login" value="<%=Constant.getResourceStringValue("lov.lovlist.add",user1.getLocale())%>" onClick="addLoveListValue();return false"></td>
		</tr>
		</table>
		<br>
		<%System.out.println("size of lov list value code : "+lovlistvaluecodeList.size()); 
			if(lovlistvaluecodeList != null && lovlistvaluecodeList.size() > 0){%>
			<fieldset><legend><%=Constant.getResourceStringValue("lov.lovlist.lovlistcodedetails",user1.getLocale())%></legend>
			 <span id="lovlistcodedetails">
			 
				 <table border="0" width="70%">
				 <%	
				 Locale lo=null;
				 String localename="";
				for(int i=0;i<lovlistvaluecodeList.size();i++){
					LovList lovlist = (LovList)lovlistvaluecodeList.get(i);
					if(i == 0){
				%>
				 <tr>
				
				 	<td><%=Constant.getResourceStringValue("lov.lovlist.lovvaluecode",user1.getLocale())%></td>
				 	<td><%=Constant.getResourceStringValue("lov.lovlist.lovvalue",user1.getLocale())%></td>
				 	<td width="30%"><%=Constant.getResourceStringValue("lov.lovlist.locale",user1.getLocale())%></td>
				 	<td></td>
				 </tr>
				<%	}
				%>
				 <tr>
				 	
				 	<td><%= lovlist.getLovListValueCode() == null ?"":lovlist.getLovListValueCode()%></td>
				 	<td><%= lovlist.getLovListValueName() == null ?"":lovlist.getLovListValueName()%></td>
				 	<%
				 	
				 	if(lovlist.getLocaleId()!= 0){ 
				 		
				 		lo = BOFactory.getLovBO().getLocaleName(lovlist.getLocaleId());
				 		System.out.println("Locale Name : "+lo.getLocaleName());
				 		localename= lo.getLocaleName();
				 	}%>
				 	<td><%=localename%></td> 
				 	<%if(lovlist.getLovListValueCode() != null && lovlist.getLovListValueName() != null){ %>
				 	<td width="50px"><a href="#" onClick="deletelovlistcodevalue('<%=lovlist.getLovListId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete lov list value" title="<%=Constant.getResourceStringValue("LOV.delete",user1.getLocale())%>" height="20"  width="19"/></a></td>
					<%} %>
				 </tr>
				 
			   <%} %>
				 </table>
			 
			 </span>
			 </fieldset>
			 <%} %>
	<%}%>
</html:form>
</body>

