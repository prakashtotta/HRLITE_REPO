<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/yahooincludes.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String searchpagedisplay="yes";
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.REQUISION_TEMPLATE_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.REQUISION_TEMPLATE_SCREEN);
%>
<bean:define id="aform" name="jobRequisitionTemplateForm" type="com.form.JobRequisitionTemplateForm" />
<span id="tmpldata">
<div id="dynamicdata"></div>




<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var ndata = sData + '<img src="jsp/images/open_button.gif" border="0" alt="edit template" title="<%=Constant.getResourceStringValue("Requisition.edittemp",user1.getLocale())%>" height="20"  width="19"/>';
         elCell.innerHTML = "<a href='#' onClick=editTemplate('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";

        };
var submitter = function (callback, newValue)
{
   
    var record = this.getRecord();
    var column = this.getColumn();
//	alert(column);
    var oldValue = this.value;
	//alert(record.getData('address'));
    var datatable = this.getDataTable();

    // send the data to our update page to update the value in the database
    YAHOO.util.Connect.asyncRequest('POST', 'jobtemplate.do',
    {
		
        success: function (o)
        {
			 callback(true, o.responseText); 
            
        },
        failure: function (o)
        {
            alert(o.statusText);
            callback();
        },
        scope: this
    },
    'action=cellEdit&column=' + column.key +
			     '&method=updatestatus' +
                 '&status=' + escape(newValue) +
                 '&templateId=' + record.getData('templateId') 
    );
};

    // Column definitions
    var myColumnDefs = [
			{key:"templateId", hidden:true},
            {key:"templateName", label:"<%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
            <%=strcolumncontent%>
            {key:"status", editor: new YAHOO.widget.RadioCellEditor({radioOptions:["Active","Closed"],disableBtns:true,asyncSubmitter:submitter}), resizeable:true}			
            
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
   
    var myDataSource = new YAHOO.util.DataSource("jobtemplate.do?method=jobtemplatelistpage&searchpagedisplay=<%=searchpagedisplay%>&templatename=<%=aform.getSerachCriteria().getTemplateName()%>&templateid=<%=aform.getSerachCriteria().getTemplateId()%>&orgId=<%=aform.getSerachCriteria().getOrgId()%>&departmentId=<%=aform.getSerachCriteria().getDepartmentIds()%>&jobgradeId=<%=aform.getSerachCriteria().getJobgradeId()%>&jobtypeId=<%=aform.getSerachCriteria().getJobtypeId()%>&statuscri=<%=aform.getSerachCriteria().getStatus()%>&ddd="+(new Date).getTime()+"&");


    
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"templateId"},
            {key:"templateName"},
            <%=strkeycontent%>,
            {key:"status"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=templateId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"templateId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
    };
    myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>


</span>