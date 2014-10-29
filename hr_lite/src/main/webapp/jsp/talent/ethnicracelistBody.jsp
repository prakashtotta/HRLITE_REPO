
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="eForm" name="ethnicRaceForm" type="com.form.EthnicRaceForm" />
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
function addEthnicRace(){
	var url = "<%=request.getContextPath()%>/ethnicrace.do?method=createEthnicRace";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.ethnic.create",user1.getLocale())%>',url,250,600, messageret);
}

function editEthnicRace(ethnicRaceId){

	var url = "<%=request.getContextPath()%>/ethnicrace.do?method=editEthnicRace&ethnicRaceId="+ethnicRaceId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.ethnic.edit",user1.getLocale())%>',url,250,600, messageret);
	
}
function deleteEthnicRace(ethnicRaceId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){



		 document.ethnicRaceForm.action ="ethnicrace.do?method=deleteEthnicRace&ethnicRaceId="+ethnicRaceId;
		 document.ethnicRaceForm.submit();
	
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
<html:form action="/ethnicrace.do?method=searchEthnicRace">
</html:form>

<br><br><a class="button" href="#" onClick="addEthnicRace()"><%=Constant.getResourceStringValue("admin.ethnic.create",user1.getLocale())%> </a>
<br><br>
<%
String ethnicracedeleted = (String)request.getAttribute("ethnicracedeleted");
	
if(ethnicracedeleted != null && ethnicracedeleted.equals("yes")){
%>
<b>Ethnic race list</b>
<br><br>

<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("admin.ethnic.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
	<%}%>	
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editEthnicRace('" + oRecord.getData("ethnicRaceId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteEthnicRace('" + oRecord.getData("ethnicRaceId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [

{key:"ethnicRaceId", hidden:true, sortable:true},
{key:"ethnicRaceName",label:"<%=Constant.getResourceStringValue("admin.ethnic.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"ethnicRaceDesc",label:"<%=Constant.getResourceStringValue("admin.ethnic.desc",user1.getLocale())%>",width:250 ,sortable:true,resizeable:true}
       
        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/ethnicracelistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"ethnicRaceId"},
{key:"ethnicRaceName"},
{key:"ethnicRaceDesc"},
{key:"status"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=ethnicRaceId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"ethnicRaceId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
