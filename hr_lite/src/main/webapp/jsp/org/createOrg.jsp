<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>

<bean:define id="orgform" name="organizationForm" type="com.form.OrganizationForm" />
<style>
span1{color:#ff0000;}
</style>
<style type="text/css">


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
	</style>
<%

Organization org1 = orgform.getOrganization();
if(org1 != null){
request.setAttribute("org1",org1);
}
%>

<script language="javascript">
var isrootorg = "<%=orgform.getIsRoot()%>";

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   parent.parent.GB_hide();
	   } 
	}
function closewindow(){
	parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.organizationForm.orgName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){

	 var alertstr = "";
    var orgName = document.organizationForm.orgName.value.trim();
	var orgCode = document.organizationForm.orgCode.value.trim();
	var Location = document.organizationForm.locationId.value.trim();
	var Org = document.organizationForm.parentOrgName.value.trim();
	var locationName = document.organizationForm.locationName.value.trim();
	var organizationTypeId = document.organizationForm.organizationTypeId.value.trim();
	var currencyCode = document.organizationForm.currencyCode.value.trim();
	var showalert=false;

	if(orgName == "" || orgName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.orgnamereqmsg",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(orgCode == "" || orgCode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Org_code_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	/*if(Location == "" || Location == null || Location == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Location_required",user1.getLocale())%><BR>";
		showalert = true;
		}*/



	if(Org == "" || Org == null || Org == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Parent_org_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(organizationTypeId == "" || organizationTypeId == null || organizationTypeId == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_type_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
   

   
	if(currencyCode == "" || currencyCode == null || currencyCode == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_currency_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
	
		if(document.organizationForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.organizationForm.action = "org.do?method=saveOrg&parentOrgName="+Org+"&locationName="+locationName;
  document.organizationForm.submit();
  // window.opener.location.href="/lov.do?method=rolelist&a=1";
//self.close();
   
   
	}

   

	function updatedata(){

		var alertstr = "";
		var orgName = document.organizationForm.orgName.value.trim();
		var orgCode = document.organizationForm.orgCode.value.trim();
		var Location = document.organizationForm.locationId.value.trim();
		var Org = document.organizationForm.parentOrgId.value.trim();
			var organizationTypeId = document.organizationForm.organizationTypeId.value.trim();
	var currencyCode = document.organizationForm.currencyCode.value.trim();
	var showalert=false;

	if(orgName == "" || orgName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.orgnamereqmsg",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(orgCode == "" || orgCode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Org_code_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	/*if(Location == "" || Location == null || Location == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Location_required",user1.getLocale())%><BR>";
		showalert = true;
		}*/
if(isrootorg == "N"){
	if(Org == "" || Org == null || Org == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Parent_org_required",user1.getLocale())%><BR>";
		showalert = true;
		}
}


	if(organizationTypeId == "" || organizationTypeId == null || organizationTypeId == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_type_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
   

   
	if(currencyCode == "" || currencyCode == null || currencyCode == "0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_currency_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
		if(document.organizationForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.organizationForm.action = "org.do?method=updateOrganization&orgId="+'<bean:write name="organizationForm" property="orgId"/>';
   document.organizationForm.submit();
  
   
	}

function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	   
	   
	document.organizationForm.action = "org.do?method=deleteOrganization&orgId="+'<bean:write name="organizationForm" property="orgId"/>';
   document.organizationForm.submit();
	  }
}

function opensearch(){
  window.open("location.do?method=selectlocation", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=1000,height=500");
}
function opensearchorg(){
  window.open("org.do?method=orgselector&orgId=<%=orgform.getOrgId()%>", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=1000,height=500");
}

</script>

<%
String organizationdeleted = (String)request.getAttribute("organizationdeleted");
String organizationsaved = (String)request.getAttribute("organizationsaved");
if(organizationsaved != null && organizationsaved.equals("yes")){
	String parentOrgName = (String)request.getParameter("parentOrgName");
	String locationName = (String)request.getParameter("locationName");
	orgform.setParentOrgName(parentOrgName);
	orgform.setLocationName(locationName);
%>

<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.organization.orglistbody.savemesage",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%if(organizationdeleted != null && organizationdeleted.equals("yes")){%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.organization.orglistbody.deletemesage",user1.getLocale())%></font></td>
			<td> <!-- <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<br><br>
<%}else{%>
<body onload="init()" >
<html:form action="/org.do?method=saveOrg" >

<div class="div">

	

<%

if (orgform.getReadPreview() != null && orgform.getReadPreview().equals("2")){

%>
	<fieldset><legend> <%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization_Details",user1.getLocale())%></legend>
	<font color = red ><html:errors /> </font>
	<table border="0" width="100%">
	
		<tr>
			<td></td>
			<td></td>
		</tr>		
	
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%> </td>
			<td><%=(orgform.getOrgName()==null)?"":orgform.getOrgName()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%> </td>
			<td><%=(orgform.getOrgCode()==null)?"":orgform.getOrgCode()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.taxid",user1.getLocale())%></td>
			<td><%=(orgform.getTaxid()==null)?"":orgform.getTaxid()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.NAICS",user1.getLocale())%> </td>
			<td><%=(orgform.getNaics()==null)?"":orgform.getNaics()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.islegal",user1.getLocale())%> </td>
            <% if (orgform.getIsLegal() !=null && orgform.getIsLegal().equals("Y")){ %>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.yes",user1.getLocale())%> </td>
			<%}else{%>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.no",user1.getLocale())%>  </td>
			<%}%>
			
		</tr>

		
	    <%
		String locationurl =null;
        if(orgform.getLocationId() != 0){

    locationurl = "<a href='#' onClick=window.open("+"'"+"location.do?method=locationdetails&readPreview=2&id="+orgform.getLocationId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=320"+"'"+")>"+orgform.getLocationName()+"</a>";

		}

	%>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%> </td>
			
			<td>
		<%=(locationurl == null)?"":locationurl%>
			</td>
		</tr>

        <%
		String parentorgurl =null;
        if(orgform.getParentOrgId() != 0){

    parentorgurl = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+orgform.getParentOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=320"+"'"+")>"+orgform.getParentOrgName()+"</a>";

		}

	%>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.parentorganization",user1.getLocale())%></td>
			
			<td>
		<%=(parentorgurl == null)?"":parentorgurl%>
			</td>
		</tr>
	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.notes",user1.getLocale())%> </td>
			<td><%=(orgform.getNotes()==null)?"":orgform.getNotes()%></td>
		</tr>

		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%>  </td>
			
			<td>
		<%=(orgform.getOrganizationtypeStr()==null)?"":orgform.getOrganizationtypeStr()%>
			</td>
		</tr>
	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.organizationcurrency",user1.getLocale())%>   </td>
			<td>
			<%=(orgform.getCurrencyCode()==null)?"":orgform.getCurrencyCode()%>
			</td>
		</tr>
		</table>
		
<%}else{%>
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

	 
		<tr>
			<td> <%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%><Font color="red">*</Font></td>
			<td><html:text property="orgName" size="50" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%><Font color="red">*</Font></td>
			<td><html:text property="orgCode" size="50" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.taxid",user1.getLocale())%></td>
			<td><html:text size="50" property="taxid" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.NAICS",user1.getLocale())%></td>
			<td><html:text size="50" property="naics" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.islegal",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isLegal" id="1" <%=(orgform.getIsLegal()!= null && orgform.getIsLegal().equals("Y"))? "Checked=true" : "" %> value="Y">&nbsp;&nbsp;
			<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isLegal" id="0" <%=(orgform.getIsLegal()!= null && orgform.getIsLegal().equals("N"))? "Checked=true" : "" %> value="N"></td>
		</tr>

		<%
		String dyvalue ="<span id=\"locationid\">";
        if(orgform.getLocationId() != 0){

  String tempurl = "<a href='#' onClick=window.open("+"'"+"location.do?method=locationdetails&readPreview=2&id="+orgform.getLocationId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=400"+"'"+")>"+orgform.getLocationName()+"</a>";
 dyvalue = "<span id=\"locationid\"><b>"+tempurl+"</b></span>";
		}

		%>

		<tr>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
				<td>&nbsp;&nbsp;<%=dyvalue%></span>&nbsp;&nbsp;<a class="closelink" href="#" onClick="opensearch()"><%=Constant.getResourceStringValue("admin.organization.orglistbody.addlocation",user1.getLocale())%></a>
				<html:hidden  property="locationId"/>
				<html:hidden  property="locationName"/>
				</td>
			</tr>
<%
	
		String orgdyvalue ="<span id=\"parentOrgId\">";
        if(orgform.getParentOrgId() != 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+orgform.getParentOrgId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=400"+"'"+")>"+orgform.getParentOrgName()+"</a>";
 orgdyvalue = "<span id=\"parentOrgId\"><b>"+tempurl1+"</b></span>";
		}

		%>
		<%if(orgform.getIsRoot()!= null && !orgform.getIsRoot().equals("Y")){%>
		<tr>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.parentorganization",user1.getLocale())%> <Font color="red">*</Font></td>
				<td>&nbsp;&nbsp;<%=orgdyvalue%></span>&nbsp;&nbsp;
				
				<a class="closelink" href="#" onClick="opensearchorg()"><%=Constant.getResourceStringValue("admin.organization.orglistbody.parentorganization",user1.getLocale())%> </a>
				

				
				</td>
			</tr>
	<%}%>
					<html:hidden  property="parentOrgId"/>
				<html:hidden  property="parentOrgName"/>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.notes",user1.getLocale())%></td>
			<td><html:textarea property="notes" cols="50" rows="3"/></td>
		</tr>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%><Font color="red">*</Font></td>
			<td>
			<html:select name="organizationForm"  property="organizationTypeId">
			<option value=""></option>
			<bean:define name="organizationForm" property="orgnizationTypeList" id="orgnizationTypeList" />

            <html:options collection="orgnizationTypeList" property="organizationTypeId"  labelProperty="lorganizationTypeName"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.organizationcurrency",user1.getLocale())%><Font color="red">*</Font> </td>
			<td>
			<html:select  property="currencyCode">
			<option value=""></option>
			<bean:define name="organizationForm" property="currencyCodeList" id="currencyCodeList" />

            <html:options collection="currencyCodeList" property="currencyCode"  labelProperty="currencyName"/>
			</html:select>
			</td>
		</tr>
			</table>
			<table border="0" width="100%">
		<tr>
			<td>
			
			<%if (orgform.getOrgId() > 0){%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}
			
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
<%}%>
	</table>

</html:form>


<%}// delete if condition%>