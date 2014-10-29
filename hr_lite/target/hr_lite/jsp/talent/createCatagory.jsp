<%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="cform" name="catagoryform" type="com.form.CatagoryForm" /> 

<style>
span1{color:#ff0000;}
</style>
<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */ 
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {  
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
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
setTimeout ( "document.catagoryform.catName.focus(); ", 200 );
}

function savedata(){
	var alertstr = "";
	var showalert=false;	
	var name=document.catagoryform.catName.value.trim();	
	var desc=document.catagoryform.catDesc.value.trim();		
	
	if(name == "" || name == null){
   	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.category.mandatory.catname",user1.getLocale())%><br>";
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
		 
		 
	  document.catagoryform.action = "catagory.do?method=saveCategory";
      document.catagoryform.submit();

 
	}

function updatedata(){
	
	var alertstr = "";
	var showalert=false;	
	var name=document.catagoryform.catName.value.trim();
	var desc=document.catagoryform.catDesc.value.trim();	
			
	if(name == "" || name == null){
   	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.category.mandatory.catname",user1.getLocale())%><br>";
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
		 
	  document.catagoryform.action = "catagory.do?method=updateCategory&catid="+'<bean:write name="catagoryform" property="catId"/>';
 document.catagoryform.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.catagoryform.action = "catagory.do?method=deleteCatagory&catid="+'<bean:write name="catagoryform" property="catId"/>';

		  document.catagoryform.submit();
	  }
	
}

function closewindow34534(){
	parent.parent.GB_hide();

}



function closewindow(){
parent.parent.GB_hide();

}
</script>

<%
String saveCategory = (String)request.getAttribute("saveCategory");
	
if(saveCategory != null && saveCategory.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.category.createpage.savedmessage",user1.getLocale())%></font></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateCategory = (String)request.getAttribute("updateCategory");
	
if(updateCategory != null && updateCategory.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.category.createpage.updatedmessage",user1.getLocale())%></font></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String duplicateCatName = (String)request.getAttribute("duplicateCatName");
	
if(duplicateCatName != null && duplicateCatName.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left"><font color="white">This Category name already exist</font></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteCategory = (String)request.getAttribute("deleteCategory");
	
if(deleteCategory != null && deleteCategory.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.category.createpage.deletedmessage",user1.getLocale())%></font></td>
		</tr>
		
	</table>
</div>
<%}else{%>	
<body class="yui-skin-sam" onload="init()">

<html:form action="/catagory.do?method=saveCategory">


<div align="left" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.category.listbodypage.categoryname",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="catName" size="44" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_description",user1.getLocale())%></td>
			<td><html:textarea property="catDesc" rows="5" cols="41"/></td>
		</tr>
		
		<tr><td><br></td></tr>
	
		<tr>
			<td colspan="2"><%if (cform.getCatId()!=0){%>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
		
		</table>
		
	  </div>


</html:form>
	
</body>

		<%}%>