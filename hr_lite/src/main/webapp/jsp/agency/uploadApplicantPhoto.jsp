<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">

function closewindow2(){

	 	 window.opener.location.reload();
		/** tree view applicant call **/
		//window.opener.callJavascriptOpenfunc("<%=form.getUuid()%>");
	 window.close();

	  
	}

function uploadImage() {
		
					
		var filename = document.applicantForm.profilePhoto.value;
		
		if(filename == "" || filename == null){
			return false;
		}
		 if (isImageFile(document.applicantForm.profilePhoto.value)) {
	        //do nothing for now;
	        //return true;
	    }
	    else {
	        alert("Your images MUST be saved in .jpg format before uploading.");
	        document.applicantForm.profilePhoto.value="";
			return false;
	    }

		
		document.applicantForm.action ="agencyops.do?method=uploadApplicantPhoto&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>";
		document.applicantForm.submit();
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
<html:form action="/agencyops.do?method=uploadApplicantPhoto" enctype="multipart/form-data">

<%
String uploadApplicantPhoto = (String)request.getAttribute("uploadApplicantPhoto");
	
if(uploadApplicantPhoto != null && uploadApplicantPhoto.equals("yes")){
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
<table border="0" width="100%">
	
	
	<tr>
<td><%=Constant.getResourceStringValue("hr.user.profile.image.upload",user1.getLocale())%></td>
<td><input type="hidden" name="MAX_FILE_SIZE" value="200000" />
	 <html:file property="profilePhoto" onchange="uploadImage();"/> 
</td>
</tr>
		
	</table>
<%}%>
</html:form>