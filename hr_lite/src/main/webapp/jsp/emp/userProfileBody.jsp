<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />


<script language="javascript">

function editmyuser(url){
		GB_showCenter('Edit User',url,550,750, messageret1);	
}

function changepassword(url){
	parent.setPopTitle1("Change password");
	parent.showPopWin(url, 500, 300, null,true);
}
function dashboardsetup(url){
	GB_showCenter('Dashboard setup', url, 600,600, messageret1);
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

		
		document.createUserForm.action ="user.do?method=uploadUserPhotoUserProfile&userId=<%=cform.getUserId()%>";
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

function backuserlistpage(url){
	alert(url);

}

</script>
<%
String firstname = "First Name";
String lastname = "Last Name";
if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_VENDOR)){
	firstname = "Short Name";
	lastname = "Full Name";
}

%>

<html:form action="/user.do?method=logon" enctype="multipart/form-data">

<div align="center" class="div">
<table>
<tr>
<td width="70%">

	<table border="0" width="100%">

	
	<tr>
			<td><b>User details</b></td>
			
		</tr>
		<tr>
			<td>


           <%
		    String changeimageurl = "user.do?method=uploadUserPhotoscr&userId="+cform.getUserId();
			if(cform.getProfilePhotoId() == 0){
			%>
			
		<a href="jsp/images/user_blank.jpg" rel="lightbox" title="<%=Constant.getResourceStringValue("hr.user.profile.change.image",user1.getLocale())%>" rev="<%=changeimageurl%>"><img src="jsp/images/user_blank.jpg" width="104" height="104" alt="" /></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+cform.getProfilePhotoId();
			%>

			<a href="<%=imgurl%>" rel="lightbox" title="change image" rev="<%=changeimageurl%>"><img src="<%=imgurl%>" width="104" height="104" alt="" /></a>

			<%}%>

			</td>
			<td>
	
     
  	
			</td>
		</tr>
	    <tr>
			<td>User type</td>
			<td><bean:write name="cform" property="type"/></td>
		</tr>


		<tr>
			<td><%=firstname%></td>
			<td><bean:write name="cform" property="firstName"/></td>
		</tr>
		<%if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_EMPLOYEE)){%>
		<tr>
			<td>Middle Name</td>
			<td><bean:write name="cform" property="middleName"/></td>
		</tr>
		<%}%>
		<tr>
			<td><%=lastname%></td>
			<td><bean:write name="cform" property="lastName"/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><bean:write name="cform" property="emailId"/></td>
		</tr>
		<tr>
			<td>Home Phone</td>
			<td><bean:write name="cform" property="phoneHome"/></td>
		</tr>
		<tr>
			<td>Office Phone</td>
			<td><bean:write name="cform" property="phoneOffice"/></td>
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
				<td>Organization</td>
				<td><%=orgdyvalue%></span>
				
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
				<td>Department</td>
				<td><%=deptvalue%></span>
				
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
				<td>Project Code</td>
				<td><%=projectcodevalue%></span>
				
				
				</td>
		</tr>
        <tr>
			<td>Designation</td>
			<td>
			<bean:write name="cform" property="designationName"/>
			</td>
		</tr>
		<tr>
			<td>Role</td>
			<td>
			<bean:write name="cform" property="roleName"/>
			</td>
		</tr>
<%
					//dispaly only if user type is Employee
}
%>
		<tr>
			<td>Locale</td>
			<td>
			<bean:write name="cform" property="localeName"/>
			</td>
		</tr>

		<tr>
			<td>Timezone</td>
			<td>
		<bean:write name="cform" property="timezoneName"/>
			</td>
		</tr>

		

		<tr>
			<td>Country</td>
			<td>
			<bean:write name="cform" property="countryName"/>
			</td>
		</tr>

       <tr>
			<td id="state" >State</td>
			<td>
		<bean:write name="cform" property="stateName"/>
			</td>
		</tr>
      <tr>
			<td>City</td>
			<td><bean:write name="cform" property="city"/></td>
		</tr>

			    <tr>
			<td>User Name</td>
			<td><bean:write name="cform" property="userName"/></td>
		</tr>

		<tr>
			<td>User Status</td>
			<% if(cform.getStatus() != null && cform.getStatus().equals("I")){ %>
				<td>Suspended</td>
				<%}%>
			<% if(cform.getStatus() != null && cform.getStatus().equals("A")){ %>
				<td>Active</td>
				<%}%>
		</tr>
		<%
String editmyuserurl = request.getContextPath()+"/user.do?method=edituser&userId="+cform.getUserId();
String changepasswordurl = request.getContextPath()+"/user.do?method=changepasswordscr&userId="+cform.getUserId();
if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_VENDOR)){
	editmyuserurl = request.getContextPath()+"/user.do?method=editmyvendor&userId="+cform.getUserId();
}
		%>
 <tr>
<%if(cform.getBackurl()!=null){%>
			
			
			<td>
			<div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
			<a class="closelink" href="<%=cform.getBackurl()%>" class="squarebutton"><span>back</span></a></div></td>
            <%}%>

			<td>
		
		<div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="closelink" href="#" onClick="javascript:editmyuser('<%=editmyuserurl%>')"><span>edit user</span></a> </div>

</td>

<%if(cform.getUserName() != null ){%>
			<td>
			<div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="closelink" href="#" onClick="javascript:changepassword('<%=changepasswordurl%>')"><span>change password</span></a> </div></td>
<%}%>

<td width="50%">
 <%
String editdashboard = request.getContextPath()+"/dashboard.do?method=mydashboardsetup&userId="+cform.getUserId();
%>
  <div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="closelink" href="#" onClick="javascript:dashboardsetup('<%=editdashboard%>')"><span>dashboard setup</span></a> </div>
 </td>
</tr>

   </table>
 </td>
 </tr>
 </table>
   </div>
   </html:form>

