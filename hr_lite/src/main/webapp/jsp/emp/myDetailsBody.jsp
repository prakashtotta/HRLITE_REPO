<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>




  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />



<link rel="stylesheet" type="text/css" href="jsp/css/userMenu.css" />

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}
</style>

<script language="javascript">

function chnageImage(url){
	window.open(url, "profileimage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,left=10, top=10,width=400,height=200");
}

function editmyuser(url){

		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Edit_my_details",user1.getLocale())%>",url,400,500, messageret1);	

}

function addEmergencycontactinfo(url){
		//alert(url);
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Add_Emergency_Contact",user1.getLocale())%>",url,300,550, messageret1);	

}
function editEmergencyContact(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.edit.emergency.contact.details",user1.getLocale())%>",url,300,550, messageret1);	
}
function editcontactinfo(url){

	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.contact.details",user1.getLocale())%>",url,500,700, messageret1);	

}
function addUserdependents(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.add.dependents",user1.getLocale())%>",url,300,550, messageret1);	
}

function addUserImigrationinfo(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Imigration.AddImmigration",user1.getLocale())%>",url,500,700, messageret1);	
}

function addUserSalaryComponent(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.add.salary.component",user1.getLocale())%>",url,500,700, messageret1);	
}
function editUserSalaryComponent(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.edit.salary.component",user1.getLocale())%>",url,500,700, messageret1);	
}
function editImmigration(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Imigration.EditImmigration",user1.getLocale())%>",url,500,700, messageret1);	
}
function editUserJobdetails(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.job.edit.jobdetails",user1.getLocale())%>",url,400,700, messageret1);	
}
function editReportToetails(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.reportto.supervisor_subordinate",user1.getLocale())%>",url,400,700, messageret1);	
}

function addUserMembership(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.membership.details.add",user1.getLocale())%>",url,400,600, messageret1);	
}
function editUserMembershipDetail(url){
	//alert(url);
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.membership.edit",user1.getLocale())%>",url,400,600, messageret1);	
}
function addUserQualification(url,qualification){
	//Education
	//Skills
	//Languages
	//License
	
	if(qualification == "Education"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Education.Add",user1.getLocale())%>",url,400,600, messageret1);	
	}else if(qualification == "Skills"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Skills.Add",user1.getLocale())%>",url,400,600, messageret1);	
	}else if(qualification == "Languages"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Languages.Add",user1.getLocale())%>",url,400,600, messageret1);	
	}else if(qualification == "License"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.License.Add",user1.getLocale())%>",url,400,600, messageret1);	
	}

}
function editUserQualificationDetails(url,qualification){

	if(qualification == "Education"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Education.Edit",user1.getLocale())%>",url,400,600, messageret1);	
	}else if(qualification == "Skills"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Skills.Edit",user1.getLocale())%>",url,400,600, messageret1);	
	}else if(qualification == "Languages"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Languages.Edit",user1.getLocale())%>",url,400,600, messageret1);	
	}else if(qualification == "License"){
		GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.License.Edit",user1.getLocale())%>",url,400,600, messageret1);	
	}
	
}
function addUserExperience(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Add_Work_Experience",user1.getLocale())%>",url,550,700, messageret1);	
}

function editUserWorkExperience(url){
	GB_showCenter("<%=Constant.getResourceStringValue("hr.user.Qualifications.Edit_Work_Experience",user1.getLocale())%>",url,550,700, messageret1);	
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

		
		document.createUserForm.action ="user.do?method=uploadUserPhoto";
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



$(document).ready(function() {$('#emergencycontactinfo').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
   retrieveData("emergencycontactinfo");
	 }); 
});
$(document).ready(function() {$('#contactinfo').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
   retrieveData("contactinfo");
	 }); 
});

function completeajx(){
	  $.unblockUI();
}
function retrieveData(name) {
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> <%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>...</h1>' });
   //convert the url to a string
   // document.getElementById("loading").style.visibility = "visible";

   var url = "<%=request.getContextPath()%>";
	if(name == "contactinfo"){
		url=url+"/usercontact.do?method=contactdetails&userId=<%=cform.getUserId()%>";
	}else if(name == "emergencycontactinfo"){
		url=url+"/useremergencycontact.do?method=emergencyContactdetails&userId=<%=cform.getUserId()%>";
	}else if(name == "userdependents"){
		url=url+"/userdependents.do?method=userDependentsdetails&userId=<%=cform.getUserId()%>";
	}else if(name == "immigration"){
		url=url+"/userimmigration.do?method=userImmigrationdetails&userId=<%=cform.getUserId()%>";
	}else if(name == "userjob"){
		url=url+"/userjob.do?method=userJobdetails&userId=<%=cform.getUserId()%>";
	}else if(name == "usersalary"){
		url=url+"/usersalary.do?method=userSalarydetails&userId=<%=cform.getUserId()%>";
	}else if(name == "reportto"){
		url=url+"/userreportto.do?method=userReportTodetails&userId=<%=cform.getUserId()%>";
	}else if(name == "userqualification"){
		url=url+"/user.do?method=userQualificationDetails&userId=<%=cform.getUserId()%>";
	}else if(name == "usermembership"){
		url=url+"/usermembership.do?method=userMembershipDetails&userId=<%=cform.getUserId()%>";
	}else if(name == "userexperience"){
		url=url+"/userexperience.do?method=userExperienceDetails&userId=<%=cform.getUserId()%>";
	}
	
	

	$.ajax({
	type: 'GET',
  url: url,
  success: function(data){
  $('#details_data').html(data);
	completeajx();
  }
});

  	//Do the AJAX call
  	
}
	 

function checkAll(oForm,screenname){
	//alert(oForm);

//alert(oForm.masterCheck.checked);
	if(screenname == 'usersalary'){
		if(oForm.masterCheck.checked == true){
			for (i = 0; i < oForm.salaryIsActive.length; i++){
				oForm.salaryIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.salaryIsActive.length; i++){
				oForm.salaryIsActive[i].checked = false ;
			}
		}
	}else if(screenname == 'emergencycontactinfo'){
		//alert(screenname);
		if(oForm.masterCheck.checked == true){
			for (i = 0; i < oForm.emergencyContactIsActive.length; i++){
				oForm.emergencyContactIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.emergencyContactIsActive.length; i++){
				oForm.emergencyContactIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'userdependents'){
		//alert(screenname);
		if(oForm.masterCheck.checked == true){
			for (i = 0; i < oForm.userdependentsIsActive.length; i++){
				oForm.userdependentsIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userdependentsIsActive.length; i++){
				oForm.userdependentsIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'immigration'){
		//alert(screenname);
		if(oForm.masterCheck.checked == true){
			for (i = 0; i < oForm.userImmigrationIsActive.length; i++){
				oForm.userImmigrationIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userImmigrationIsActive.length; i++){
				oForm.userImmigrationIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'usermembership'){
		//alert(screenname);
		if(oForm.masterCheck.checked == true){
			for (i = 0; i < oForm.usermembershipIsActive.length; i++){
				oForm.usermembershipIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.usermembershipIsActive.length; i++){
				oForm.usermembershipIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'userexperience'){
		//alert(screenname);
		if(oForm.masterCheck.checked == true){
			for (i = 0; i < oForm.userexpIsActive.length; i++){
				oForm.userexpIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userexpIsActive.length; i++){
				oForm.userexpIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'Education'){
		//alert(screenname);
		if(oForm.educaitonMasterCheck.checked == true){
			for (i = 0; i < oForm.userEducationIsActive.length; i++){
				oForm.userEducationIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userEducationIsActive.length; i++){
				oForm.userEducationIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'Skills'){
		//alert(screenname);
		if(oForm.skillsMasterCheck.checked == true){
			for (i = 0; i < oForm.userskillsIsActive.length; i++){
				oForm.userskillsIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userskillsIsActive.length; i++){
				oForm.userskillsIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'Languages'){
		//alert(screenname);
		if(oForm.languageMasterCheck.checked == true){
			for (i = 0; i < oForm.userLanguageIsActive.length; i++){
				oForm.userLanguageIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userLanguageIsActive.length; i++){
				oForm.userLanguageIsActive[i].checked = false ;
			}
		}
		
	}else if(screenname == 'License'){
		//alert(screenname);
		if(oForm.licenseMasterCheck.checked == true){
			for (i = 0; i < oForm.userLicenseIsActive.length; i++){
				oForm.userLicenseIsActive[i].checked = true ;
			}
		}else{
			for (i = 0; i < oForm.userLicenseIsActive.length; i++){
				oForm.userLicenseIsActive[i].checked = false ;
			}
		}
		
	}
	

	
}

function deleteUserDetails(allids,name){
	var checkedItems="";
	 var url = "<%=request.getContextPath()%>";

	//alert(allids+" >> "+allids.length);
	if(name == "usersalary"){
	

		//alert(allids);
		var ids = allids.split(",");

		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
		if(id == ","){
		}else{
			var fname="salaryIsActive_"+id;
			var vv = document.createUserForm.elements[fname].checked;
         
			if(vv==true){
				
				checkedItems = checkedItems+id+",";
			}
		}
		
			/*if(id == ","){
			}else{
				//id=allids[j];

					if(createUserForm["salaryIsActive_"+id].checked == true ){
						checkedItems = checkedItems+","+id;
					}

			}*/
					
		}
		url=url+"/usersalary.do?method=deleteUserSalarydetails&userId=<%=cform.getUserId()%>&checkedItems="+checkedItems;

	}else if(name == "emergencycontactinfo"){
		//alert(allids+" >> "+allids.length);
		var ids = allids.split(",");

		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="emergencyContactIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}
		url=url+"/useremergencycontact.do?method=deleteEmergencyContactMultiple&userId=<%=cform.getUserId()%>&checkedItems="+checkedItems;
		
	}else if(name == "userdependents"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userdependentsIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}
		url=url+"/userdependents.do?method=deleteUserDependentsMultiple&userId=<%=cform.getUserId()%>&checkedItems="+checkedItems;
		
	}else if(name == "immigration"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userImmigrationIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}
		url=url+"/userimmigration.do?method=deleteUserImmigrationMultiple&userId=<%=cform.getUserId()%>&checkedItems="+checkedItems;
		
	}else if(name == "usermembership"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="usermembershipIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}
		url=url+"/usermembership.do?method=deleteUserMembershipMultiple&userId=<%=cform.getUserId()%>&checkedItems="+checkedItems;
		
	}else if(name == "userexperience"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userexpIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}
		url=url+"/userexperience.do?method=deleteUserExperienceMultiple&userId=<%=cform.getUserId()%>&checkedItems="+checkedItems;
		
	}else if(name == "Education"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userEducationIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}
		url=url+"/user.do?method=deleteUserQualificationMultiple&userId=<%=cform.getUserId()%>&screenName=Education&checkedItems="+checkedItems;
		
	}else if(name == "Skills"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userskillsIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}

		url=url+"/user.do?method=deleteUserQualificationMultiple&userId=<%=cform.getUserId()%>&screenName=Skills&checkedItems="+checkedItems;
	}else if(name == "Languages"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userLanguageIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}

		url=url+"/user.do?method=deleteUserQualificationMultiple&userId=<%=cform.getUserId()%>&screenName=Languages&checkedItems="+checkedItems;
	}else if(name == "License"){
		//alert(allids+" >> "+allids.length);
		
		var ids = allids.split(",");
		
		for(j = 0; j < ids.length-1; j++){
			 
			var id=ids[j];
			
			if(id == ","){
			}else{
				var fname="userLicenseIsActive_"+id;
				var vv = document.createUserForm.elements[fname].checked;
	         
				if(vv==true){
					
					checkedItems = checkedItems+id+",";
				}
			}
					
		}

		url=url+"/user.do?method=deleteUserQualificationMultiple&userId=<%=cform.getUserId()%>&screenName=License&checkedItems="+checkedItems;
	}//close if 

	if(checkedItems == null || checkedItems == ""){
		
		alert('<%=Constant.getResourceStringValue("hr.user.select_at_least_one",user1.getLocale())%>');
	}else{
		//alert(checkedItems);
		//Do the AJAX call
		var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 	if (doyou == true){

		  	if (window.XMLHttpRequest) { 
			    req = new XMLHttpRequest();    	// Non-IE browsers
		    	req.onreadystatechange = processStateChange;
	
			    try {
		    		req.open("GET", url, true);
					
			    } catch (e) {}
			    req.send(null);
		  	} else if (window.ActiveXObject) {
		       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
		    	if (req) {
			    	req.onreadystatechange=processStateChange;
			        req.open("GET", url, true);
				    req.send();
					
		    	}
		  	}

	 	}//confirm if

	}
}
function backToUserList(){
	document.createUserForm.action="user.do?method=userListwithPag";
	document.createUserForm.submit();
	
}
function showHideDirectDepositDetails(oForm,index){
	
	
	divname = document.getElementById("bankDetaildata_"+index);
	//alert(divname+" "+bankDetaildata);
	var field=oForm["showHideDirectdepositdetails_"+index];
	if(field.checked == true){ 
		
		divname.style.display = "block";
		
	}else{
		divname.style.display = "none";
		//alert("false");
	}
	
	 
}
function load(size){

	// for (i = 0; i < size; i++){
	//	var divname = document.getElementById("bankDetaildata_"+2);
	//	 divname.style.display = "none";
	// }
	
	
}

function saveJobdata(){
	alert("Hello");
	//document.createUserForm.action ="userjob.do?method=saveUserJobdata";
	//document.createUserForm.submit();
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
<body onload="load(this.form)">
<html:form action="/user.do?method=logon" enctype="multipart/form-data">

<div align="center">
<table border="0">
<tr>

<!-- menu -->
<td valign="top" width="30%">
<table border="0" width="100%">
<tr>
<td>
<%
		    String changeimageurl = "user.do?method=uploadUserPhotoscr&userId="+cform.getUserId();
			if(cform.getProfilePhotoId() == 0){
			%>
			
		<a href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="<%=Constant.getResourceStringValue("hr.user.profile.change.image",user1.getLocale())%>" rev="<%=changeimageurl%>"><img src="jsp/images/user_blank.jpg" width="154" height="154" alt="" /></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+cform.getProfilePhotoId();
			%>

			<a href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="change image" rev="<%=changeimageurl%>"><img src="<%=imgurl%>" width="154" height="154" alt="" /></a>

			<%}%>
</td>
</tr>
<tr>
<%
String personaldetailsurl = request.getContextPath()+"/user.do?method=userprofile&userId="+EncryptDecrypt.encrypt(String.valueOf(cform.getUserId()));
if(cform.getUserId() == user1.getUserId()){
	personaldetailsurl = request.getContextPath()+"/user.do?method=mydetails";
}
%>
<td>
<div id="menu8">
  <ul>
    <%--<li><a href="<%=personaldetailsurl%>" title="<%=Constant.getResourceStringValue("hr.user.personal.details",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.personal.details",user1.getLocale())%></a></li>
    <li><a href="#" id="contactinfo"    title="<%=Constant.getResourceStringValue("hr.user.contact.details",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.contact.details",user1.getLocale())%></a></li>
    <li><a href="#3" id="emergencycontactinfo"   title="<%=Constant.getResourceStringValue("hr.user.emergency.contact.details",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.emergency.contact.details",user1.getLocale())%></a></li>
    <li><a href="#4" onClick="javascript:retrieveData('userdependents');return false;" title="<%=Constant.getResourceStringValue("hr.user.dependents",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.dependents",user1.getLocale())%></a></li>
    <li><a href="#5" onClick="javascript:retrieveData('immigration');return false;" title="<%=Constant.getResourceStringValue("hr.user.imigration",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.imigration",user1.getLocale())%></a></li>
    <li><a href="#6" onClick="javascript:retrieveData('userjob');return false;" title="<%=Constant.getResourceStringValue("hr.user.Job",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.Job",user1.getLocale())%></a></li>
	<li><a href="#7" onClick="javascript:retrieveData('usersalary');return false;" title="<%=Constant.getResourceStringValue("hr.user.Salary",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.Salary",user1.getLocale())%></a></li>
	<!--  <li><a href="#8" title="<%=Constant.getResourceStringValue("hr.user.Tax.exemption",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.Tax.exemption",user1.getLocale())%></a></li>-->
	<li><a href="#9" onClick="javascript:retrieveData('reportto');return false;" title="<%=Constant.getResourceStringValue("hr.user.ReportTo",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.ReportTo",user1.getLocale())%></a></li>
	<li><a href="#10" onClick="javascript:retrieveData('userqualification');return false;" title="<%=Constant.getResourceStringValue("hr.user.Qualification",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.Qualification",user1.getLocale())%></a></li>
	<li><a href="#11" onClick="javascript:retrieveData('usermembership');return false;" title="<%=Constant.getResourceStringValue("hr.user.Membership",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.Membership",user1.getLocale())%></a></li>
	<li><a href="#12" onClick="javascript:retrieveData('userexperience');return false;" title="<%=Constant.getResourceStringValue("hr.user.Experience.details",user1.getLocale())%>"><%=Constant.getResourceStringValue("hr.user.Experience.details",user1.getLocale())%></a>
	</li>--%>
  </ul>
</div>

 <%
String editdashboard = request.getContextPath()+"/dashboard.do?method=mydashboardsetup&userId="+cform.getUserId();
%>
  

</td>
</tr>
</table>

 </td>
<td valign="top" width="70%">
<br>
<!--  
<a class="squarebutton" href="#" onClick="javascript:backToUserList();return false"><%=Constant.getResourceStringValue("hr.button.back",user1.getLocale())%></a>
<br>
-->
	<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.screenfield.Loading",user1.getLocale())%>	</span>

<span id="details_data">
<fieldset><legend><%=Constant.getResourceStringValue("hr.user.personal.details",user1.getLocale())%></legend>
	<table border="0" width="100%">


	    <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.User_type",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="type"/></td>
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
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="phoneOffice"/></td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.SSNNumber",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="ssnNumber"/></td>
		</tr>

		
		<%if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_EMPLOYEE)){%>
		<%
		String orgdyvalue ="<span id=\"parentOrgId\">";
        if(cform.getOrgId() != 0){
        String orgname = (cform.getParentOrgName()==null)?"":cform.getParentOrgName();
  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+cform.getOrgId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+orgname+"</a>";
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
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="roleName"/>
			</td>
		</tr>
		<tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="designationName"/>
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
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Nationality",user1.getLocale())%></td>
			<td align="left">
			<bean:write name="cform" property="nationalityName"/>
			</td>
		</tr>

       
         <tr>
			<td align="left"><%=Constant.getResourceStringValue("hr.user.Username",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="userName"/></td>
		</tr>
<% if(cform.getApplicantId()>0){%>
	    <tr>
			<td align="left"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_Id",user1.getLocale())%></td>
			<td align="left"><bean:write name="cform" property="applicantId"/></td>
		</tr>

<%}%>

		<%
String editmyuserurl = request.getContextPath()+"/user.do?method=editmyuser&userId="+cform.getUserId();
		if(cform.getUserId() != user1.getUserId()){
			editmyuserurl = request.getContextPath()+"/user.do?method=edituser&userId="+cform.getUserId();
		}

String changepasswordurl = request.getContextPath()+"/user.do?method=changepasswordscr&userId="+cform.getUserId();
if(cform.getType() != null && cform.getType().equals(Common.USER_TYPE_VENDOR)){
	editmyuserurl = request.getContextPath()+"/user.do?method=editmyvendor&userId="+cform.getUserId();
}
		%>
 <tr>
<td align="left">
		
		<div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a class="button" href="#" onClick="javascript:editmyuser('<%=editmyuserurl%>')">
<span><%=Constant.getResourceStringValue("hr.user.Edit_details",user1.getLocale())%></span></a> </div>

</td>
<td align="left"><div style="margin: 6px 6px 6px 0px; width: 215px;" class="buttonwrapper">
<a  class="button" href="#" onClick="javascript:changepassword('<%=changepasswordurl%>')">
<span><%=Constant.getResourceStringValue("hr.user.Change_password",user1.getLocale())%></span></a> </div></td>
</tr>

 </table>
 </fieldset>
 </td>

</span>

 </tr>
 </table>
   </div>
   </html:form>
   </body>

   <script type="text/javascript">
GB_myShow = function(caption, url, /* optional */ height, width, callback_fn) {
    var options = {
        caption: caption,
        height: height || 500,
        width: width || 500,
        fullscreen: false,
        show_loading: false,
        callback_fn: callback_fn
    }
    var win = new GB_Window(options);
    return win.show(url);
}
</script>