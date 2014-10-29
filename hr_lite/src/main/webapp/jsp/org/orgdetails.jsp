<%@ include file="../common/include.jsp" %>
<%
System.out.println("******* orgDetails.jsp");
String path = (String)request.getAttribute("filePath");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
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



function opensearch(){
  window.open("location.do?method=selectlocation", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
function opensearchorg(){
  window.open("org.do?method=orgselector&orgId=<%=orgform.getOrgId()%>", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

</script>


<body onload="init()" >
<html:form action="/org.do?method=saveOrg" >


<div align="center" class="div">
	

	<br>
	<fieldset><legend><b> <%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization_Details",user1.getLocale())%></b></legend>
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
            <% if (orgform.getIsLegal() != null && orgform.getIsLegal().equals("Y")){ %>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.yes",user1.getLocale())%> </td>
			<%}else{%>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.no",user1.getLocale())%>  </td>
			<%}%>
			
		</tr>

		
	    <%
		String locationurl =null;
        if(orgform.getLocationId() != 0){

    locationurl = "<a href='#' onClick=window.open("+"'"+"location.do?method=locationinfo&readPreview=2&id="+orgform.getLocationId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=320"+"'"+")>"+orgform.getLocationName()+"</a>";

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

    parentorgurl = "<a href='#' onClick=window.open("+"'"+"org.do?method=orginfo&readPreview=2&orgid="+orgform.getParentOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=320"+"'"+")>"+orgform.getParentOrgName()+"</a>";

		}

	%>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.parentorganization",user1.getLocale())%> </td>
			
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
		</fieldset>


</div>

</html:form>