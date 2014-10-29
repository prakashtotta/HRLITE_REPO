<%@ include file="../header.jsp" %>

<bean:define id="aform" name="fbJobsForm" type="network.form.FbJobsForm" />
<%
String useridswithcommaend=freader.getFriendsUids(fuser,40);

String jobposted=(String)request.getAttribute("jobposted");
String readPreview=(String)request.getAttribute("readPreview");
%>
<html>

	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<head>

	<link rel='stylesheet' type='text/css' href='talentnetwork/css/tab.css' />


	<LINK rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css">
	<LINK rel="stylesheet" type="text/css" href="talentnetwork/jquery/layout-default-latest.css">

	<STYLE type="text/css">
	/* Using an 'optional-container' instead of 'body', so need body to have a 'height' */
	html, body {
		width:		100%;
		height:		100%;
		padding:	0;
		margin:		0;
		overflow:	hidden !important;
	}
	#optional-container {
		width:			100%;
		height:			100%;
		

	}
	
	

	</STYLE>

	<link rel='stylesheet' type='text/css' href='talentnetwork/css/searchbutton.css' />


	<SCRIPT type="text/javascript" src="talentnetwork/jquery/js/jquery-ui-latest.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="talentnetwork/jquery/js/jquery.layout-latest.js"></SCRIPT>


	<SCRIPT type="text/javascript">
	var myLayout;

	$(document).ready(function(){

		$("#tabs_div").tabs();

		$(".header-footer").hover(
			function(){ $(this).addClass('ui-state-hover'); }
		,	function(){ $(this).removeClass('ui-state-hover'); }
		);

		myLayout = $('#optional-container').layout();
		myLayout.sizePane("west", 300);


		//addThemeSwitcher('.ui-layout-north',{ top: '13px', right: '20px' });

	});


	</SCRIPT>



<SCRIPT type="text/javascript">


function savedata(){
//	alert(document.getElementById("veterans").checked);
	var veterans="";
	if(document.getElementById("veterans").checked == true){
		veterans="Y";
	}else{
		veterans="N";
	}
	var refcode="";
	if(document.getElementById("refcode").checked == true){
		refcode="Y";
	}
//	alert("applyauto1 >> "+document.getElementById("applyauto1").checked+" applyauto2 >> "+document.getElementById("applyauto2").checked);
	var applyauto="";
	if(document.getElementById("applyauto1").checked){
		applyauto="Y";
	}else{
		applyauto="N";
	}
	var applyext="";
	if(document.getElementById("applyauto2").checked){
		applyext="Y";
	}
	var content = nicEditors.findEditor('description').getContent();
	
	document.fbJobsForm.desc.value=content;
	  $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	  var url = "fbjob.do?method=savePostJob&veterans="+veterans+"&refcode="+refcode+"&applyauto="+applyauto+"&applyext="+applyext;
	  document.fbJobsForm.action = url;
	  document.fbJobsForm.submit();
}

function previewdata(){
//	alert(document.getElementById("veterans").checked);
	var veterans="";
	if(document.getElementById("veterans").checked == true){
		veterans="Y";
	}else{
		veterans="N";
	}
	var refcode="";
	if(document.getElementById("refcode").checked == true){
		refcode="Y";
	}
//	alert("applyauto1 >> "+document.getElementById("applyauto1").checked+" applyauto2 >> "+document.getElementById("applyauto2").checked);
	var applyauto="";
	if(document.getElementById("applyauto1").checked){
		applyauto="Y";
	}else{
		applyauto="N";
	}
	var applyext="";
	if(document.getElementById("applyauto2").checked){
		applyext="Y";
	}
	var content = nicEditors.findEditor('description').getContent();
	
	document.fbJobsForm.desc.value=content;
	  $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	  var url = "fbjob.do?method=previewPostJob&veterans="+veterans+"&refcode="+refcode+"&applyauto="+applyauto+"&applyext="+applyext;
	  document.fbJobsForm.action = url;
	  document.fbJobsForm.submit();
}
function editdata(){

	var content ="<%=aform.getDescription()%>";
//	alert(content);
	document.fbJobsForm.desc.value=content;
//alert(document.fbJobsForm.jobTitle.value);
$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	  var url = "fbjob.do?method=editPostJob";
	  document.fbJobsForm.action = url;
	  document.fbJobsForm.submit();
}
function showrefCodetext(){
	//alert(document.getElementById("refcode").checked);
	if(document.getElementById("refcode").checked == true){
		document.getElementById("refcodetext").style.visibility = "visible";
	}else{
		document.getElementById("refcodetext").style.visibility = "hidden";
	}
	
	
}
function hideexternaltextbox(){
	//alert(document.getElementById("applyauto2").checked);

	document.getElementById("applyautotext").style.visibility = "hidden";
	
}
function showexternaltextbox(){
	//alert(document.getElementById("applyauto1").checked);
	document.getElementById("applyautotext").style.visibility = "visible";
}
function init(){
	document.getElementById("refcodetext").style.visibility = "hidden";
	document.getElementById("applyautotext").style.visibility = "hidden";
	
}
</SCRIPT>
 <script type="text/javascript" src="jsp/js/nicEdit.js"></script>
 <script type="text/javascript">
 //<![CDATA[
 bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
 //]]>  
 </script>
 </head>

<body onload="init()">

<br>

<%@ include file="../tab1.jsp" %>
<%
List frindsUsingList = freader.getFriendsUsingThisApps(fuser,4);
%>
<DIV id="optional-container">
<DIV class="ui-layout-north">
        <table height="50px">
		<tr>
		<td>
		
		<%@ include file="../searchpeople.jsp" %>
		</td>
		<td>
		<font size="1">Friends using TalentNetwork</font>
		<table>
		<tr>
		<% if(frindsUsingList != null){
			for(int i=0;i<frindsUsingList.size();i++){
				FaceBookUser fbuser = (FaceBookUser)frindsUsingList.get(i);

		%>
		<td>
      <a href="<%=fbuser.getLink()%>" target="new"><img src="//graph.facebook.com/<%=fbuser.getFacebookid()%>/picture" border="0"/></a><br>
      <a href="<%=fbuser.getLink()%>" target="new"><font size="2"><%=fbuser.getFullName()%></font></a>
	  </td>
		<%}
		}%>
		</tr>
		</table>
		</td>
		<td width="35px">
		<a href="">View All >> </a>
		</td>
		</tr>
		</table>
	</DIV>


	<DIV id="tabs_div" class="ui-layout-center">
		<UL style="-moz-border-radius-bottomleft: 0; -moz-border-radius-bottomright: 0;">
			<LI><A href="#tab_1"><SPAN>Post Job</SPAN></A></LI>
		</UL>
		<!-- tabs are here -->
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">
				
<%
	
if(jobposted != null && jobposted.equals("yes")){
%>

	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/greentick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="green">Job Posted successfully</font></td>
			
		</tr>
		
	</table>


<%}else{%>
			<br>
			
			
	<html:form action="/fbjob.do?method=saveFbJobs">
	<%
	
	if(readPreview != null && readPreview.equals("2")){
	%>	
		<fieldset>
			<table border="0" width="100%" cellspacing="2" cellpadding="2">
					<tr>
						<td width="40%">Job Title:</td>
						<td><%=aform.getJobTitle()==null?"":aform.getJobTitle()%>
						<html:hidden property="jobTitle" value="<%=aform.getJobTitle()==null?"":aform.getJobTitle()%>"/>
						
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>Headline:</td>
						<td><%=aform.getHeadline() ==null?"":aform.getHeadline()%>
						<html:hidden property="headline" value="<%=aform.getHeadline()==null?"":aform.getHeadline()%>" /> 
						
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>Company Name:</td>
						<td><%=aform.getCompanyName() ==null?"":aform.getCompanyName()%>
						<html:hidden property="companyName" value="<%=aform.getCompanyName()==null?"":aform.getCompanyName()%>"  />
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>City:</td>
						<td><%=aform.getCity() ==null?"":aform.getCity()%>
						<html:hidden property="city" value="<%=aform.getCity()==null?"":aform.getCity()%>"  />
						</td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><%=aform.getCountry() ==null?"":aform.getCountry()%>
						<html:hidden property="country" value="<%=aform.getCountry()==null?"":aform.getCountry()%>"  />
						</td>
					</tr>
					
					
					<tr>
						<td>State:</td>
						<td><%=aform.getState() ==null?"":aform.getState()%>
						<html:hidden property="state" value="<%=aform.getState()==null?"":aform.getState()%>"  />
						
						</td>
					</tr>
					<tr>
						<td>Postal Code:</td>
						<td><%=aform.getPostalcode() ==null?"":aform.getPostalcode()%>
						<html:hidden property="postalcode" value="<%=aform.getPostalcode()==null?"":aform.getPostalcode()%>"  />
						</td>
					</tr>
					<tr>
						<td>Description:</td>
						<td><%=aform.getDescription() ==null?"":aform.getDescription()%>
						<input type="hidden" name="desc"/>
						</td>
					</tr>
					<tr>
						<td>Job Category:</td>
						<td><%=aform.getJobcategory() ==null?"":aform.getJobcategory()%>
						<html:hidden property="jobcategory" value="<%=aform.getJobcategory()==null?"":aform.getJobcategory()%>"  />
						</td>
					</tr>
					<tr>
						<td>Experience</td>
						<td><%=aform.getExperience() ==null?"":aform.getExperience()%>
						<html:hidden property="experience" value="<%=aform.getExperience()==null?"":aform.getExperience()%>"  />
						</td>
					</tr>
					<tr>
						<td>Tenure</td>
						<td><%=aform.getTenure()== null?"":aform.getTenure()%>
						<html:hidden property="tenure" value="<%=aform.getTenure()==null?"":aform.getTenure()%>"  />
						</td>
					</tr>
					<tr>
						<td>Perks:</td>
						<td><%=aform.getPerks()== null?"":aform.getPerks()%>
						<html:hidden property="perks" value="<%=aform.getPerks()==null?"":aform.getPerks()%>"  />
						</td>
					</tr>
	
					<tr>
						<td>Vetarans:</td>
						<td><%=aform.getVeterans() == null?"":aform.getVeterans()%>
						<html:hidden property="veterans" value="<%=aform.getVeterans()==null?"":aform.getVeterans()%>"  />
						</td>
					</tr>
					
					<tr>
						<td>Reference Code:</td>
						<td><%=aform.getReferencecode() == null?"":aform.getReferencecode()%>
						<html:hidden property="referencecode" value="<%=aform.getReferencecode()==null?"":aform.getReferencecode()%>"  />
						</td>
					</tr>
					<%if(aform.getApplyauto().equals("Y")){ %>
					<tr>
						<td colspan="2">Apply with Talent Network</td>
					</tr>
					<%}else{ %>
					<tr>
						<td colspan="2">Apply with External</td>
					</tr>
					<tr>
						<td>URL:</td><td><%=aform.getAppyurl() == null?"":aform.getAppyurl()%>
						<html:hidden property="appyurl" value="<%=aform.getAppyurl()==null?"":aform.getAppyurl()%>"  />
						</td>
					</tr>
					<tr>
						<td>Email:</td><td><%=aform.getAppyemail() == null?"":aform.getAppyemail()%>
						<html:hidden property="appyemail" value="<%=aform.getAppyemail()==null?"":aform.getAppyemail()%>"  />
						</td>
					</tr>
						
					<%} %>
							
	
					<tr>
						<td colspan="2">
						<input type="button" class="button" name="save"   value="Submit" onClick="savedata()">
						<input type="button" class="button" name="edit"   value="Edit" onClick="editdata()">
	
					</tr>
					
					<tr><td>&nbsp;</td></tr>
			</table>
		</fieldset>
	
		
	<%}else{%>
	
			<table border="0" width="100%" cellspacing="2" cellpadding="2">
					<tr>
						<td width="25%">Job Title:</td>
						<td><html:text property="jobTitle" maxlength="100" size="35"/></td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>Headline:</td>
						<td><html:text property="headline" maxlength="100" size="35"/></td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>Company Name:</td>
						<td><html:text property="companyName" maxlength="100" size="35"/></td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>City:</td>
						<td><html:text property="city" maxlength="100" size="35"/></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td>
						<html:select  property="country">
					   	<option value=""></option>
						<bean:define name="fbJobsForm" property="countryList" id="countryList" />
			            <html:options collection="countryList" property="countryName"  labelProperty="countryName"/>
						</html:select>&nbsp;
		
						<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>Loading States...</span></td>
					</tr>
					
					
					<tr>
						<td>State:</td>
						<td>
						
							<html:text property="state" maxlength="100" size="35"/>
						
						</td>
					</tr>
					<tr>
						<td>Postal Code:</td>
						<td><html:text property="postalcode" maxlength="100" size="35"/></td>
					</tr>
					<tr>
						<td>Description:</td>
						<td>
						<html:textarea property="description" styleId="description" cols="48" rows="10"/>
						<input type="hidden" name="desc"/>
						</td>
					</tr>
					<tr>
						<td >
						
						Job Category:
						</td>
						<td>
							<!-- Job Category -->
							<html:select property="jobcategory">
							<option value="">-Industry-</option>
							<bean:define name="fbJobsForm" property="jobCategoryList" id="jobCategoryList" />
				            <html:options collection="jobCategoryList" property="jobCategoryName"  labelProperty="jobCategoryName"/>
							</html:select>
							<!-- Experience -->
							<html:select  property="experience">
							<option value="">-Experience-</option>
							<bean:define name="fbJobsForm" property="fbexpList" id="fbexpList" />
				            <html:options collection="fbexpList" property="key"  labelProperty="value"/>
							</html:select>
							<!-- Tenure -->
							<html:select  property="tenure">
							<option value="">-Tenure-</option>
							<bean:define name="fbJobsForm" property="fbtenureList" id="fbtenureList" />
				            <html:options collection="fbtenureList" property="key"  labelProperty="value"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td>Perks:</td>
						<td><html:text property="perks" maxlength="100" size="35"/></td>
					</tr>
	
					<tr>
						<td>Vetarans:</td>
						
						<td>
						<%if(!StringUtils.isNullOrEmpty(aform.getVeterans()) && aform.getVeterans().equals("Y")){ %>
							<input type="checkbox" name="veterans" checked="true" id="veterans"/>
						<%}else{ %>
							<input type="checkbox" name="veterans" id="veterans"/>
						<%} %>
						</td>
						
					</tr>
					
					<tr>
						<td>Reference Code:</td>
						<td><input id="refcode" type="checkbox" name="refcode" onClick="showrefCodetext()" />
						<font size="3">Yes, I want to add a unique ID to this job</font>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div id="refcodetext"><html:text property="referencecode" maxlength="100" size="35"/><font size="2">(optional)</font>	</div>
						</td>
					
					</tr>
							
					<tr>
						<td></td>
						<td><input type="radio" id="applyauto1" name="applyauto" checked="true" onClick="hideexternaltextbox()"/>Apply with Talent Network&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="radio" id="applyauto2" name="applyauto" onClick="showexternaltextbox()"/>Apply with External </td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div id="applyautotext">
							Url:&nbsp;&nbsp;&nbsp;&nbsp;
							<html:text property="appyurl" maxlength="100" size="35"/><br><br>
							Email:&nbsp;<html:text property="appyemail" maxlength="100" size="35"/>
							</div>
						</td>
					
					</tr>
					<tr>
						<td colspan="2">
						<input type="button" class="button" name="save"   value="Submit" onClick="savedata()">
						<input type="button" class="button" name="preview" value="Preview" onClick="previewdata()"></td>
					</tr>
					
					<tr><td>&nbsp;</td></tr>
			</table>
		<%}%>
	</html:form>
	<%}%>	
			</DIV>
			
		</DIV>

	

	</DIV>

	<DIV class="ui-layout-west"> 
	<%@ include file="east.jsp" %>
	
	</DIV>

	<DIV class="ui-layout-south"> South </DIV>

</DIV>



</body>
</html>

