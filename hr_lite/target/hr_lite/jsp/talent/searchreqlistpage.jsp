
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.dao.*"%>



<%
 
  
  
 


  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.ALL_REQUISION_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.ALL_REQUISION_SCREEN);
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>
<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<span id="reqdata">
	<table>
	<tr>
	<td width="10px"></td>
	<td>
	<a href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>
	
	</td>
	<td>
	<div id="toalrecords"></div>
	</td>
	</tr>
	</table>
	<table>
	<tr>
	<td width="10px"></td>
	<td>
	<div id="dynamicdata"></div>
	</td>
	<td></td>
	</tr>
	</table>




<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {


var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var ndata = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>" height="20"  width="19" align="middle"/>';
		var saveasdata =  '<img src="jsp/images/saveas.gif" border="0" alt="save as job requistion" title="<%=Constant.getResourceStringValue("Requisition.saveasjobreq",user1.getLocale())%>" height="20"  width="19" align="middle"/>';
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + sData + "</a>";
		var saveasqd = "<a href='#' onClick=saveasJobReq('" + oRecord.getData("jobreqId") + "')"+ ">" + saveasdata + "</a>";

		 elCell.innerHTML = editreqd;

        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="edit job requistion" title="<%=Constant.getResourceStringValue("Requisition.editwindow",user1.getLocale())%>" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='#' onClick=editJobReqTemplte('" + oRecord.getData("templateId") + "')"+ ">" + sData + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }
	

		var summaryUrl = function(elCell, oRecord, oColumn, sData) {
			if(oRecord.getData("statusUiValue")=="Active > Open"){
          elCell.innerHTML = "<a href='jobreq.do?method=requistionapplicantlist&backurl=jobreq.do?method=jobreqList&state=0&requistionId=" + oRecord.getData("jobreqId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " >" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary",user1.getLocale())%>" + "</a>";
			}
        };

var formatOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("currentOwnerName");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("currentOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else {
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("currentOwnerName");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("currentOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        };

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"templateId", hidden:true},	
		{key:"uuid", hidden:true},	
		{key:"currentOwnerId", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"jobreqName", label:"<%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
             <%=strcolumncontent%>
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary",user1.getLocale())%>", sortable:false, resizeable:true,formatter:summaryUrl}
					
           
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jobreq.do?method=jobreqListpage&searchpagedisplay=<%=searchpagedisplay%>&jobreqname=<%=aform.getSerachCriteria().getJobreqName()%>&reqno=<%=aform.getSerachCriteria().getRequisition_number()%>&orgId=<%=aform.getSerachCriteria().getOrgId()%>&departmentId=<%=aform.getSerachCriteria().getDepartmentIds()%>&jobgradeId=<%=aform.getSerachCriteria().getJobgradeId()%>&jobtypeId=<%=aform.getSerachCriteria().getJobtypeId()%>&statuscri=<%=aform.getSerachCriteria().getStatus()%>&statecri=<%=aform.getSerachCriteria().getState()%>&jobreqcode=<%=aform.getSerachCriteria().getJobreqcode()%>&uuid=<%=aform.getUuid()%>&budgetcodeid=<%=aform.getSerachCriteria().getBudgetcodeId()%>&isinternal=<%=aform.getSerachCriteria().getIsinternal()%>&isnewposition=<%=aform.getSerachCriteria().getIsnewposition()%>&ispublishredtoexternal=<%=aform.getSerachCriteria().getIspublishredtoexternal()%>&ispublishredtoemployee=<%=aform.getSerachCriteria().getIspublishredtoemployee()%>&ddd="+(new Date).getTime()+"&");
  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
	 myDataSource.connMethodPost = false; 
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
			{key:"templateId"},
			{key:"uuid"},
			{key:"jobreqName"},
			{key:"currentOwnerId"},
			{key:"isGroup"},
			
			 <%=strkeycontent%>,
		{key:"summary"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=jobreqId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);

 // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
    // Update totalRecords on the fly with value from server
	myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.result.count",user1.getLocale())%> : ' + my_row_total;
	 });
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
<%
//}
%>

</span>