<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />


<style>
span1{color:#ff0000;}
</style>
<script language="javascript">



function bulkupload(){

      var alertstr = "";
      var showalert=false;
         
//	  var zipfilename = document.createUserForm.fileData.value.trim();

//	 if(zipfilename == "" || zipfilename == null){
//     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.Bulkupload.alert",user1.getLocale())%><BR>";
//		showalert = true;
//	 }

	var FileExt =document.createUserForm.fileData.value.lastIndexOf(".xlsx")
	
	if(FileExt==-1) {    
		alert("<%=Constant.getResourceStringValue("aquisition.Bulkupload.alert_xls",user1.getLocale())%>");    
		return false;
	}
	

	 if (showalert){
     	alert(alertstr);
        return false;
          }

          //document.getElementById('progressbartable1').style.display = ''; 
		  document.createUserForm.action = "user.do?method=uploademployeesubmit";
	  document.createUserForm.submit();
	}

	function init(){
       
	// document.getElementById('progressbartable1').style.display = 'none';  
		}
</script>
<br>

<body onLoad="init()">

<html:form action="/user.do?method=uploademployee" enctype="multipart/form-data">
<div class="div">
<b><%=Constant.getResourceStringValue("user.Bulkupload.bulkupload",user1.getLocale())%></b><br><br>
<%=Constant.getResourceStringValue("user.Bulkupload.download.sample.employee.xlsx",user1.getLocale())%>&nbsp;&nbsp; : &nbsp;&nbsp;
<a href="<%=request.getContextPath()%>/download/file?isfromjsp=yes&filename=employee.xlsx">employee.xlsx</a>
<br><br>


<%
String uploadsucceed = (String)request.getAttribute("uploadsucceed");

if(uploadsucceed != null && uploadsucceed.equals("yes")){
%>
<br><font color="green"><%=Constant.getResourceStringValue("user.Bulkupload.Upload_started",user1.getLocale())%>&nbsp;<a  class="button" href="<%=request.getContextPath()%>/bulkuploadtask.do?method=mybulktasklist"><%=Constant.getResourceStringValue("user.Bulkupload.view.my.jobs",user1.getLocale())%></a></font><br>

<%}%>

<br>
<!--<table id='progressbartable1'>
<tr>
    <td></td>
    <td  style="vertical-align:middle"><img src="jsp/images/loading.gif"></td>
    <td></td>
</tr>
</table>-->



<font color = red ><html:errors /> </font>
<table border="0" cellpadding="0" cellspacing="0">



       <tr>
			<td><html:file property="fileData"/> </td>
			<td width="20px"></td>
			<td>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.upload",user1.getLocale())%>" onClick="bulkupload()" class="button">
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>

			

			

</table>
</div>
</html:form>


</body>

    	
