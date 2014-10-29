
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="eForm" name="emailCampaignForm" type="com.form.EmailCampaignForm" />
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><%=Constant.getResourceStringValue("hr.title",user1.getLocale())%></title>



<!--there is no custom header content for this example-->

</head>

<script language="javascript">
function addEmailCampaign(){
	var url = "<%=request.getContextPath()%>/emailcampaign.do?method=createEmailCampaign";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.emailcampaign.create",user1.getLocale())%>',url,450,700, messageret);
}

function editEmailCampaign(emailCampaignId){
	
	var url = "<%=request.getContextPath()%>/emailcampaign.do?method=editEmailCampaign&emailCampaignId="+emailCampaignId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.emailcampaign.edit",user1.getLocale())%>',url,450,700, messageret);
	
}
function deleteEmailCampaign(emailCampaignId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){



			  document.emailCampaignForm.action = "emailcampaign.do?method=deleteEmailCampaign&emailCampaignId="+emailCampaignId;
			  document.emailCampaignForm.submit();
	
 	}
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
</script>

<body class="yui-skin-sam">
<html:form action="/emailcampaign.do?method=saveEmailCampaign">
</html:form>
<br>
<a class="button" href="#" onClick="addEmailCampaign()"><%=Constant.getResourceStringValue("admin.emailcampaign.create",user1.getLocale())%> </a>
<br><br>

<%
String emailcampaigndeleted = (String)request.getAttribute("emailcampaigndeleted");
	
if(emailcampaigndeleted != null && emailcampaigndeleted.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("admin.emailcampaign.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
	<%}%>	
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<b>Email campaign list</b>
<br><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editEmailCampaign('" + oRecord.getData("emailCampaignId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteEmailCampaign('" + oRecord.getData("emailCampaignId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [

{key:"emailCampaignId", hidden:true, sortable:true},
{key:"emailCampaignName",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignName",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"emailCampaignDesc",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignDesc",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"emailCampaignemail",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignemail",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"emailCampaignFormat",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.emailCampaignFormat",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"smtpserver",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.smtpserver",user1.getLocale())%>", sortable:true,resizeable:true},	
{key:"smtpuser",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.smtpuser",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"smptoport",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.smptport",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.emailcampaign.status",user1.getLocale())%>", sortable:true,resizeable:true},
{key:"delete",label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>",sortable:false,formatter:deleteUrl,resizeable:true} 
       
        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/emailcampaign/emailcampaignlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"emailCampaignId"},
{key:"emailCampaignName"},
{key:"emailCampaignDesc"},
{key:"emailCampaignemail"},
{key:"emailCampaignFormat"},
{key:"smtpserver"},
{key:"smtpuser"},
{key:"smptoport"},
{key:"status"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=emailCampaignId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"emailCampaignId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
