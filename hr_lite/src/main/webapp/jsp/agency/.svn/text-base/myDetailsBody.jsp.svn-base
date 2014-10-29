<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>



<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />


<script language="javascript">

function chnageImage(url){
	window.open(url, "profileimage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,left=10, top=10,width=400,height=200");
}


function editmyuser(url){

		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Edit_my_details",user1.getLocale())%>",url,350,500, messageret1);	

}

function changepassword(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Change_password",user1.getLocale())%>",url,200,400, messageret1);
}
function dashboardsetup(url){
	GB_showCenter('<%=Constant.getResourceStringValue("hr.user.Dashboard_setup",user1.getLocale())%>', url, 600,600, messageret1);
}
function messageret1(){
	
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

		
		document.createUserForm.action ="agencydetails.do?method=uploadUserPhoto";
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
<%
String firstname = "First Name";
String lastname = "Last Name";
if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_VENDOR)){
	firstname = "Agency name";
	lastname = "Primary contact";
}

%>

<html:form action="/user.do?method=logon" enctype="multipart/form-data">

<div align="Center" class="div">
<table>
<tr>
<td width="70%">

	<table border="0" width="100%">

	

		<tr>
			<td align="left">


<%
		    String changeimageurl = "agencydetails.do?method=uploadUserPhotoscr&userId="+cform.getUserId();
			if(cform.getProfilePhotoId() == 0){
			%>
			
		<a href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="<%=Constant.getResourceStringValue("hr.user.profile.change.image",user1.getLocale())%>" rev="<%=changeimageurl%>"><img src="jsp/images/user_blank.jpg" width="154" height="154" alt="" /></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+cform.getProfilePhotoId();
			%>

			<a href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="change image" rev="<%=changeimageurl%>"><img src="<%=imgurl%>" width="154" height="154" alt="" /></a>

			<%}%>


			</td>
			<td align="left">
	
  	
			</td>
		</tr>

		<tr>
			<td align="left"><%=firstname%></td>
			<td align="left"><bean:write name="cform" property="firstName"/></td>
		</tr>
		<%if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_EMPLOYEE)){%>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.middle_Name",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="middleName"/></td>
		</tr>
		<%}%>
		<tr>
			<td align="left"><%=lastname%></td>
			<td align="left"><bean:write name="cform" property="lastName"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="emailId"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Home_Phone",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="phoneHome"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="phoneOffice"/></td>
		</tr>
		<%if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_EMPLOYEE)){%>
		<%
		String orgdyvalue ="<span id=\"parentOrgId\">";
        if(cform.getOrgId() != 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+cform.getOrgId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+cform.getParentOrgName()+"</a>";
 orgdyvalue = "<span id=\"parentOrgId\">"+tempurl1+"</span>";
		}

		%>
		<tr>
				<td align="left"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
				<td align="left"><%=orgdyvalue%></span>
				
				</td>
			</tr>

		<%
		String deptvalue ="<span id=\"departmentId\">";
        if(cform.getDepartmentId() != 0){

   String deptpurl = "<a href='#' onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+cform.getDepartmentId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+cform.getDepartment()+"</a>";
 deptvalue = "<span id=\"departmentId\">"+deptpurl+"</span>";
		}

		%>

		<tr>
				<td align="left"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
				<td align="left"><%=deptvalue%></span>
				
				</td>
		</tr>

		<%
		String projectcodevalue ="<span id=\"projectcodeId\"></span>";
        if(cform.getProjectcodeId() != 0){

   String projectcodeurl = "<a href='#' onClick=window.open("+"'"+"lov.do?method=projectcodepreview&projectcodeId="+cform.getProjectcodeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+cform.getProjectcode()+"</a>";
 projectcodevalue = "<span id=\"projectcodeId\">"+projectcodeurl+"</span>";
		}

		%>

		<tr>
				<td align="left"><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
				<td align="left"><%=projectcodevalue%></span>
				
				
				</td>
		</tr>
        <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="designationName"/>
			</td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="roleName"/>
			</td>
		</tr>
<%
					//dispaly only if user type is Employee
}
%>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="localeName"/>
			</td>
		</tr>

		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td align="left">
		<bean:write name="cform" property="timezoneName"/>
			</td>
		</tr>

		

		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Country",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="countryName"/>
			</td>
		</tr>

       <tr>
			<td id="state" align="left"><%=Constant.getResourceStringValue("hr.user.State",user1.getLocale())%></td>
			<td align="left">
		<bean:write name="cform" property="stateName"/>
			</td>
		</tr>
      <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.City",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="city"/></td>
		</tr>

			    <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Username",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="userName"/></td>
		</tr>

		<%
String editmyuserurl = request.getContextPath()+"/user.do?method=editmyuser&userId="+cform.getUserId();
String changepasswordurl = request.getContextPath()+"/user.do?method=changepasswordscr&userId="+cform.getUserId();
if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_VENDOR)){
	editmyuserurl = request.getContextPath()+"/agencydetails.do?method=editmyvendor&userId="+cform.getUserId();
	changepasswordurl = request.getContextPath()+"/agencydetails.do?method=changepasswordscr&userId="+cform.getUserId();
}
		%>
 <tr>
			<td align="left">
		
		<div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="button" href="#" onClick="javascript:editmyuser('<%=editmyuserurl%>')">
<span><%=Constant.getResourceStringValue("hr.user.Edit_my_details",user1.getLocale())%></span></a> </div>

</td>
			<td align="left"><div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="button" href="#" onClick="javascript:changepassword('<%=changepasswordurl%>')">
<span><%=Constant.getResourceStringValue("hr.user.Change_password",user1.getLocale())%></span></a> </div></td>
</tr>

   </table>
 </td>
 <td valign="bottom" width="40%">
 <table>
 <tr>
 <td width="50%">

 </td>
 <td align="left"></td>
</tr>
</table>
 </td>
 </tr>
 </table>
   </div>
   </html:form>

   <script type="text/javascript">
GB_myShow = function(caption, url, /* optional */ height, width, callback_fn) {
    var options = {
        caption: caption,
        height: height || 500,
        width: width || 500,
        fullscreen: true,
        show_loading: false,
        callback_fn: callback_fn
    }
    var win = new GB_Window(options);
    return win.show(url);
}
</script>