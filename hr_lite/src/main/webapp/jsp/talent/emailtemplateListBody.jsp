<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="aform" name="emailTemplatesForm" type="com.form.EmailTemplatesForm" />
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<script language="javascript">
function createEmailTemplate(){
	
	var url = "<%=request.getContextPath()%>/emailtemplate.do?method=createEmailtemplate";
	GB_showFullScreen('<%=Constant.getResourceStringValue("admin.EmailTemplate.CreateEmailtemplate",user1.getLocale())%>',url, messageret);
	

}

function edittemplate(aid){
	var url = "<%=request.getContextPath()%>/emailtemplate.do?method=editemailtemplate&emailtemplateId="+aid;
	GB_showFullScreen('<%=Constant.getResourceStringValue("admin.EmailTemplate.screenname",user1.getLocale())%>',url, messageret);
}

function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

		}

	function searchcri(){
	       
		   document.emailTemplatesForm.action = "emailtemplate.do?method=searchEmailtemplatelistpage";
		   document.emailTemplatesForm.submit();
 
	}
	function resetData(){
		document.emailTemplatesForm.emailtemplatename.value="";
		document.emailTemplatesForm.emailSubject.value="";
		document.emailTemplatesForm.createdBy.value="";

		 }  
	 
$(function() {

	$("#createdBy").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		//document.jobRequisitionTemplateForm.hiringMgrId.value=item.data;
		//document.jobRequisitionTemplateForm.highiringmanagernamehidden.value=item.value;
		//alert(item.data);
		}
		});


});
function init(){
	document.emailTemplatesForm.emailtemplatename.focus(); 

	}
</script>
<body class="yui-skin-sam" onload="init()" >
<html:form  action="/emailtemplate.do?method=searchemailtemplate" method="post" onsubmit="searchcri()">
<b><%=Constant.getResourceStringValue("admin.EmailTemplate.searchEmailtemplate",user1.getLocale())%></b> <br><br>
	
	<table>
		<tr >
			<td><%=Constant.getResourceStringValue("admin.EmailTemplate.name",user1.getLocale())%> : </td>
			<td><html:text property="emailtemplatename" size="30" maxlength="500"/></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Email Subject : </td>
			<td><html:text property="emailSubject" size="30" maxlength="500"/></td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td>Created By : </td>
			<td> <input type="text" id="createdBy" name="createdBy" autocomplete="off" size="30" value="<%=(aform.getCreatedBy()==null?"":aform.getCreatedBy())%>"><br>
			<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
			</td>
			
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td>
			<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()">
			<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()">
			</td>
		</tr>
	</table>
	<br><br>

<%=Constant.getResourceStringValue("admin.EmailTemplate.emailtemplist",user1.getLocale())%> <br><br>
 
<a  href="#" onClick="createEmailTemplate()"><%=Constant.getResourceStringValue("admin.EmailTemplate.CreateEmailtemplate",user1.getLocale())%></a>






<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=edittemplate('" + oRecord.getData("emailtemplateId") + "')"+ ">" + sData + "</a>";
         
        };

var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deletetemplate('" + oRecord.getData("emailtemplateId") + "')"+ ">" + "delete" + "</a>";
         
        };
    // Column definitions
    var myColumnDefs = [
		{key:"emailtemplateId", hidden:true},
        {key:"emailtemplatename", label:" <%=Constant.getResourceStringValue("admin.EmailTemplate.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:editUrl},
		{key:"emailSubject", label:"<%=Constant.getResourceStringValue("admin.EmailTemplate.sub",user1.getLocale())%>",sortable:true, resizeable:true},
    	{key:"createdBy", label:" <%=Constant.getResourceStringValue("admin.EmailTemplate.createdby",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"createdDate", label:"<%=Constant.getResourceStringValue("admin.EmailTemplate.createdon",user1.getLocale())%>",sortable:true, resizeable:true},
    	{key:"updatedBy", label:"<%=Constant.getResourceStringValue("admin.EmailTemplate.modifiedby",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"updatedDate", label:"<%=Constant.getResourceStringValue("admin.EmailTemplate.modifiedon",user1.getLocale())%>",sortable:true, resizeable:true}
		
			
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/emailtempltelistpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&emailtemplatename=<%=aform.getEmailtemplatename()%>&emailSubject=<%=aform.getEmailSubject()%>&createdBy=<%=aform.getCreatedBy()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"emailtemplateId"},
            {key:"emailtemplatename"},           
			{key:"emailSubject"},
		   	{key:"createdBy"},  
			{key:"createdDate"},
			{key:"updatedDate"},
			{key:"updatedBy"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=emailtemplateId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"emailtemplateId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
</html:form>
</body>

