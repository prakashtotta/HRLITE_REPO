<%@ page import="java.io.*"%>

<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>
<bean:define id="tpform" name="talentPoolForm" type="com.form.TalentPoolForm" /> 
<%
String searchpagedisplay = (String)request.getAttribute("searchtalentpool");

%>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.TALENT_POOL_SCREEN);
 if(!StringUtils.isNullOrEmpty(strcolumncontent)){
	 strcolumncontent = strcolumncontent.substring(0,strcolumncontent.length()-1);
 }
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.TALENT_POOL_SCREEN);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>
<script language="javascript"> 

window.name = 'myModal';
</script>
<script language="javascript">
function createTalentpool(){
	var url = "<%=request.getContextPath()%>/talentpool.do?method=createTalentpool";
	GB_showCenter("<%=Constant.getResourceStringValue("admin.talentpool.talentpoolbody.pagetitle.create",user1.getLocale())%>",url,380,600, messageret);
}

function editTalentpool(tagid){
	var url = "<%=request.getContextPath()%>/talentpool.do?method=talentpoolDetails&talentpoolid="+tagid;
	GB_showCenter("<%=Constant.getResourceStringValue("admin.talentpool.talentpoolbody.pagetitle.edit",user1.getLocale())%>",url,380,600, messageret);

	
}

function showmessage(returnval) { 
	//window.location.reload();
	}
function showmessage(returnval) { 
	//window.location.reload();
	}

function messageret(){
	//window.location.reload();

			}



function searchTalentpool(){

		 var alertstr = "";
	   var showalert=false;
       var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   var email=document.talentPoolForm.talentPoolemail.value.trim();	
	   if(email.length>0){
            if(reg.test(email) ==false){
     	    alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		    showalert = true;
		  }

	   }
	   if (showalert){
     	alert(alertstr);
		//setTimeout ( "document.talentPoolForm.talentPoolemail.focus(); ", 200 );
		//document.talentPoolForm.talentPoolemail.focus();
        return false;
          }
	  
       document.talentPoolForm.action = "talentpool.do?method=searchTalentpool";
	   document.talentPoolForm.submit();




	}

function resetdata(){
      document.talentPoolForm.talentPoolName.value="";
      document.talentPoolForm.talentPoolemail.value="";
	  document.talentPoolForm.smtpserver.value="";
	  document.talentPoolForm.smtpuser.value="";
	  document.talentPoolForm.orgId.value="";
	  document.talentPoolForm.talentPoolDesc.value="";
	  document.talentPoolForm.isGroup.value="";
	  


	}

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsTalentPool";
    GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}

</script>


<body class="yui-skin-sam">

<html:form action="/talentpool.do?method=saveTalentpool" target='myModal'>
<div class="div">
<table>
<tr> <td><%=Constant.getResourceStringValue("admin.talentpool.talentpoolbody.talentpoolname",user1.getLocale())%></td>
     <td><html:text property="talentPoolName" size="30" maxlength="300"/></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
     <td><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.email",user1.getLocale())%></td>
	 <td><html:text property="talentPoolemail" size="30" maxlength="300"/></td>
</tr>
<tr>
     <td><%=Constant.getResourceStringValue("admin.talentpool.smtpserver",user1.getLocale())%></td>
	 <td><html:text property="smtpserver" size="30" maxlength="300"/></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
     <td><%=Constant.getResourceStringValue("admin.talentpool.smtpuser",user1.getLocale())%></td>
	 <td><html:text property="smtpuser" size="30" maxlength="300"/></td>
</tr>

	<tr>
	<td>Description</td>
	<td><html:text property="talentPoolDesc" size="30" maxlength="500"/></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td><%=Constant.getResourceStringValue("rule.org",user1.getLocale())%></td>
	<td>
	<html:select property="orgId">
    <option value=""></option>
    <bean:define name="talentPoolForm" property="organizationList" id="organizationList"/>
    <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
    </html:select></td>
</tr>
	</tr>
		
		
		<tr>
			<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onclick="searchTalentpool()" class="button"/>&nbsp;&nbsp;&nbsp;<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onclick="resetdata()" class="button"/></td></tr>
</table>
</div>
<br><br>
</html:form>

<a class="button" href="#" onClick="createTalentpool()"><%=Constant.getResourceStringValue("admin.talentpool.talentpoolbody.pagetitle.create",user1.getLocale())%></a>
&nbsp;
<a class="button" href="#" onClick="configuareColumns();return              false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<br><br>
<b>Talent pool list</b>
<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editTalentpool('" + oRecord.getData("talentPoolId") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"talentPoolId", hidden:true, sortable:true},
{key:"talentPoolName", label:"<%=Constant.getResourceStringValue("admin.talentpool.talentpoolbody.talentpoolname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
 <%=strcolumncontent%>

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talentpool/talentpoolListPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&talentpoolname=<%=tpform.getTalentPoolName()%>&emailid=<%=tpform.getTalentPoolemail()%>&smtpserver=<%=tpform.getSmtpserver()%>&smtpuser=<%=tpform.getSmtpuser()%>&description=<%=tpform.getTalentPoolDesc()%>&orgId=<%=tpform.getOrgId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"talentPoolId"},
{key:"talentPoolName"},
<%=strkeycontent%>
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=talentPoolId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"talentPoolId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:10 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
    // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
