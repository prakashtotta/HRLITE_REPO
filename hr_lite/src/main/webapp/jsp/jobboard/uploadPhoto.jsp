<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="userRegForm" type="com.form.UserRegForm" />

<script language="javascript">

function closewindow2(){

	 	 window.opener.location.reload();
	 window.close();

	  
	}

function uploadImage() {
		
					
		var filename = document.userRegForm.logoPhoto.value;
		
		if(filename == "" || filename == null){
			return false;
		}
		 if (isImageFile(document.userRegForm.logoPhoto.value)) {
	        //do nothing for now;
	        //return true;
	    }
	    else {
	        alert("Your images MUST be saved in .jpg format before uploading.");
	        document.userRegForm.logoPhoto.value="";
			return false;
	    }

		
		document.userRegForm.action ="jobboard.do?method=uploadLogoPhoto";
		document.userRegForm.submit();
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
<html:form action="/jobboard.do?method=uploadUserPhoto" enctype="multipart/form-data">

<%
String uploadUserPhoto = (String)request.getAttribute("uploadUserPhoto");
	
if(uploadUserPhoto != null && uploadUserPhoto.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("hr.user.profile.image.updated.successfully",user1.getLocale())%></font></td>
			<td> <a href="#" onclick="closewindow2()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<br><br>
<table border="0" width="100%">
	
	
	<tr>
<td><%=Constant.getResourceStringValue("hr.user.profile.image.upload",user1.getLocale())%></td>
<td><input type="hidden" name="MAX_FILE_SIZE" value="200000" />
	 <html:file property="logoPhoto" onchange="uploadImage();"/> 
</td>
</tr>
		
	</table>
<%}%>
</html:form>