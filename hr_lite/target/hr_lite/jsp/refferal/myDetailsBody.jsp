<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/include.jsp" %>

<%@ include file="../common/greybox.jsp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<bean:define id="refform" name="refferalForm" type="com.form.RefferalForm"/>

<script language="javascript">

function editmyDetails(refferalid){
	var url = "<%=request.getContextPath()%>/reflogin.do?method=editRefferalEmp&userId="+refferalid;
											
	//parent.setPopTitle1("Edit my details");
	//parent.showPopWin(url, 400, 140, null,true);
	GB_showCenter('Edit Employee Refferal',url,550,750, messageret);
}
function changepassword(refferalid){
     var url = "<%=request.getContextPath()%>/refferal.do?method=changePassword&refferalid="+refferalid;
	//parent.setPopTitle1("Change password");
	//parent.showPopWin(url, 500, 300, null,true);
	GB_showCenter('Change Password',url,200,400, messageret);
}
function dashboardsetup(url){
	GB_showCenter('Dashboard setup', url, 600,600, messageret1);
}
function messageret1(){
	
			}

function messageret(){
	window.location.reload();

			}

function showmessage(returnval) { 
	window.location.reload();
	}
function uploadImage() {
	
	
	var filename = document.refferalForm.profilePhoto.value;
	
	if(filename == "" || filename == null){
		return false;
	}
	 if (isImageFile(document.refferalForm.profilePhoto.value)) {
        //do nothing for now;
        //return true;
		
    }
    else {
        alert("Your images MUST be saved in .jpg format before uploading.");
        document.refferalForm.profilePhoto.value="";
		return false;
    }

	
	document.refferalForm.action ="refferal.do?method=uploadUserPhoto";
	document.refferalForm.submit();
	
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


<html:form action="/refferal.do?method=logon" enctype="multipart/form-data">

<div align="center" class="div">
<table>
<tr>
<td width="60%">

	<table border="0" width="100%">
	
	
		<tr>
			<td><b>My details</b></td>
			<td></td>
		</tr>
		<tr>
			<td>
			<%if(refform.getProfilePhotoId() == 0){%>
			<img src="jsp/images/user_blank.jpg" width="104" height="104"/>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+refform.getProfilePhotoId();
			%>
			<a href="<%=imgurl%>" rel="gb_imageset[nice_pics]" title="<%=refform.getEmployeename()%>">
            <img src="<%=imgurl%>" width="104" height="104"/>

			<%}%>
			</td>
			<td>
	
     			<input type="hidden" name="MAX_FILE_SIZE" value="200000" />
	 			<html:file property="profilePhoto" onchange="uploadImage();"/> 
  	
			</td>
		</tr>
		
	    <tr>
        <td>Code</td>
		
				     <td><%=(refform.getEmployeecode() == null)?"":refform.getEmployeecode()%></td>
		</tr>
		<tr>
           <td>Name</td>
	
		     <td><%=(refform.getEmployeename() == null)?"":refform.getEmployeename()%></td>
		</tr>

		<tr>
           <td>Email Id</td>
		   <!-- <td><%=refform.getEmployeeemail()%></td> -->
		   <td><%=(refform.getEmployeeemail() == null)?"":refform.getEmployeeemail()%></td>
		</tr>
				<tr>
			<td width="40%">Mobile No :</td>

			 <td><%=(refform.getMobileNo() == null)?"":refform.getMobileNo()%></td>
		</tr>
		<tr>
			<td width="40%">Home Phone :</td>

			<td><%=(refform.getPhoneHome() == null)?"":refform.getPhoneHome()%></td>
		</tr>
		<tr>
			<td width="40%">Office Phone :</td>
			<td><%=(refform.getPhoneOffice() == null)?"":refform.getPhoneOffice()%></td>

		</tr>
		<%
		String orgdyvalue ="<span id=\"parentOrgId\">";
        if(refform.getOrgId() != 0){

  		String tempurl1 = "<a href='#' onClick=window.open("+"'"+"org.do?method=orginfo&orgid="+refform.getOrgId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+refform.getOrgName()+"</a>";
 		orgdyvalue = "<span id=\"parentOrgId\">"+tempurl1+"</span>";
		}

		%>
		<tr>
				<td>Organization :</td>
				<td><%=orgdyvalue%></span>
				
				</td>
			</tr>

		</tr>
		<%
		String deptvalue ="<span id=\"departmentId\">";
        if(refform.getDepartmentId() != 0){

  		 String deptpurl = "<a href='#' onClick=window.open("+"'"+"dept.do?method=departmentinfo&id="+refform.getDepartmentId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+refform.getDepartmentName()+"</a>";
 		deptvalue = "<span id=\"departmentId\">"+deptpurl+"</span>";
		}

		%>

		<tr>
				<td>Department :</td>
				<td><%=deptvalue%></span>
				
				</td>
		</tr>
		<%
		String projectcodevalue ="<span id=\"projectcodeId\"></span>";
        if(refform.getProjectcodeId() != 0){

   String projectcodeurl = "<a href='#' onClick=window.open("+"'"+"projectcodes.do?method=projectCodeinfo&projectcodeId="+refform.getProjectcodeId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+refform.getProjectcode()+"</a>";
 projectcodevalue = "<span id=\"projectcodeId\">"+projectcodeurl+"</span>";
		}

		%>

		<tr>
				<td>Project Code :</td>
				<td><%=projectcodevalue%></span>
				
				
				</td>
		</tr>
			     <tr>
			<td width="40%">Designation :</td>
			<td>
			<%=(refform.getDesignationName()==null)?"":refform.getDesignationName()%>
			</td>
		</tr>

		<tr>

		<tr>
			<td>Locale :</td>
			<td>
			<bean:write name="refform" property="localeName"/>
			
			</td>
		</tr>

		<tr>
			<td>TimeZone :</td>
			<td>
		<bean:write name="refform" property="timezoneName"/>
			</td>
		</tr>

		

		<tr>
			<td>Country :</td>
			<td>
			<bean:write name="refform" property="countryName"/>
			</td>
		</tr>

       <tr>
			<td id="state" >State :</td>
			<td>
		<bean:write name="refform" property="stateName"/>
			</td>
		</tr>
      <tr>
			<td>City :</td>
			<td><bean:write name="refform" property="city"/></td>
		</tr>


	<tr>
			<td>User Name :</td>
			<td><bean:write name="refform" property="userName"/></td>
		</tr>

		

		
 <tr>
			<td>
		
		<div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="button" href="#" onClick="javascript:editmyDetails(<%=refform.getEmployeeReferalId()%>)"><span>edit my details</span></a> </div>

</td>
			<td><div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="button" href="#" onClick="javascript:changepassword(<%=refform.getEmployeeReferalId()%>)"><span>change password</span></a> </div></td>
</tr>
		
 
 </table>
   </div>
   
   </html:form>