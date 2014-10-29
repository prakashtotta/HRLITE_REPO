<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />

<script language="javascript">

function closewindow2(){

	 	 window.opener.location.reload();
	 window.close();

	  
	}

function uploadImage() {
		
					
		var filename = document.createUserForm.profilePhoto.value;
		
		if(filename == "" || filename == null){
			return false;
		}
		 if (isImageFile(document.createUserForm.profilePhoto.value)) {
	        //do nothing for now;
	        //return true;
	    }
	    else {
	        alert("Your images MUST be saved in .jpg format before uploading.");
	        document.createUserForm.profilePhoto.value="";
			return false;
	    }

		
		document.createUserForm.action ="agencydetails.do?method=uploadUserPhoto&userId=<%=cform.getUserId()%>";
		document.createUserForm.submit();
	}


	
	function isImageFile(fileName) {
	    if (fileName == "") {
	        return true;
	    }
	
	    var pos = fileName.lastIndexOf(".");
	    if (pos != -1) {
	        var ext = fileName.substring(pos + 1, fileName.length).toLowerCase();
	        return (ext == "jpg" || ext == "jpeg" || ext == "jpe" || ext == "jfif" || ext == "gif");
	    }
	    return false;
	}

</script>
<html:form action="/agencydetails.do?method=uploadUserPhoto" enctype="multipart/form-data">

<%
String uploadUserPhoto = (String)request.getAttribute("uploadUserPhoto");
	
if(uploadUserPhoto != null && uploadUserPhoto.equals("yes")){
%>
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("hr.user.profile.image.updated.successfully",user1.getLocale())%></font></td>
			<td> <a href="#" onclick="closewindow2()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<br><br>
<div align="center" class="div">
<table border="0" width="100%">
	
	
	<tr>
<td><%=Constant.getResourceStringValue("hr.user.profile.image.upload",user1.getLocale())%></td>
<td><input type="hidden" name="MAX_FILE_SIZE" value="200000"/>
	 <html:file property="profilePhoto" onchange="uploadImage();"/> 
</td>
</tr>
		
	</table>
<%}%>
</div>
</html:form>