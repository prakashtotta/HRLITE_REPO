
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function createvariable(){
	var url = "<%=request.getContextPath()%>/variable.do?method=createVariable";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.variable.message.createvariable",user1.getLocale())%>',url,350,600, messageret);
}
function editvariable(id){
	var url = "<%=request.getContextPath()%>/variable.do?method=editVariable&variableId="+id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.variable.message.editvariable",user1.getLocale())%>',url,400,600, messageret);
}


function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}
function editscreen(code){
	var url = "<%=request.getContextPath()%>/screenfield.do?method=editscreenfield&screencode="+code;
	GB_showFullScreen('Edit Screen fields',url, messageret);
}
function editscreenReq(code){
	var url = "<%=request.getContextPath()%>/screenfield.do?method=editscreenfieldReq&screencode="+code;
	GB_showFullScreen('Edit Screen fields',url, messageret);
}

function applicantFormMap(screenName){
	var url = "<%=request.getContextPath()%>/variablemap.do?method=requisionFormMap&formcode="+screenName+"&formname="+screenName;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.variable.mapping.editformmapping",user1.getLocale())%>',url,500,750, messageret);
}
</script>

<body class="yui-skin-sam">
<table>
<tr>
<td>
<br><a class="button" href="#" onClick="createvariable()"><%=Constant.getResourceStringValue("admin.variable.Create_Custom_Fields",user1.getLocale())%></a>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>
</td>
<td width="10px"></td>
<td valign="top">

<% if(!StringUtils.isNullOrEmpty(user1.getPackagetaken()) && Constant.isPackageContainFunction(user1.getPackagetaken(),"screenSetting")){%>
<div class="div">
<table>
<tr>

<td>
 <fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen",user1.getLocale())%></b></legend>
 <a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.referral",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN_REFERRAL')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>


</tr>
<tr>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.talentpool",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN_TALENTPOOL')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.talentpool.external",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN_TALENTPOOL_EXTERNAL')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>

</tr>
<tr>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.profile",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN_PROFILE')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.external",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN_EXTERNAL')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>
<td>
</td>
</tr>
<tr>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen.agency",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN_AGENCY')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>

<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("requisition.screen",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('REQUISITION_FORM')"><%=Constant.getResourceStringValue("requisition.screen.custom.fields",user1.getLocale())%></a>
</fieldset>
</td>
</tr>
</table>
<%}else{%>
<table>
<tr>

<td>
 <fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.screen",user1.getLocale())%></b></legend>
 <a  href="#" onClick="applicantFormMap('APPLICANT_SCREEN')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>
<td>
<fieldset><legend><b><%=Constant.getResourceStringValue("requisition.screen",user1.getLocale())%></b></legend>
<a  href="#" onClick="applicantFormMap('REQUISITION_FORM')"><%=Constant.getResourceStringValue("admin.variable.custom_fields_mapping",user1.getLocale())%></a>
</fieldset>
</td>
</tr>
</table>
<%}%>
</td>
</table>
</div>
<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editvariable('" + oRecord.getData("variableId") + "')"+ ">" + sData + "</a>";

        };

	var populateDefaultdate = function(elCell, oRecord, oColumn, sData) {

		if(oRecord.getData("variableType") == 'date'){
			elCell.innerHTML = oRecord.getData("defaultValueDate");
		}else{
			elCell.innerHTML = oRecord.getData("defaultValue");
		}
          
     
        };

    // Column definitions
    var myColumnDefs = [
{key:"variableId", hidden:true, sortable:true},
{key:"variableName",label:"<%=Constant.getResourceStringValue("admin.variable.variable_name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"variableType",label:"<%=Constant.getResourceStringValue("admin.variable.variable_type",user1.getLocale())%>", sortable:true, resizeable:true},
{key:"defaultValue",label:"<%=Constant.getResourceStringValue("admin.variable.default_value",user1.getLocale())%>", sortable:true, resizeable:true,formatter:populateDefaultdate},
{key:"defaultValueDate",label:"<%=Constant.getResourceStringValue("admin.variable.default_date",user1.getLocale())%>", sortable:false, resizeable:true,hidden:true},
{key:"createdBy",label:"<%=Constant.getResourceStringValue("rule.createdby",user1.getLocale())%>", sortable:true, resizeable:true},
{key:"createdDate",label:"<%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Created_Date",user1.getLocale())%>", sortable:true, resizeable:true}


        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/variable/variablelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"variableId"},
{key:"variableName"},
{key:"variableType"},
{key:"defaultValue"},
{key:"defaultValueDate"},
{key:"createdBy"},
{key:"createdDate"}


					
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=variableId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"variableId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
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
