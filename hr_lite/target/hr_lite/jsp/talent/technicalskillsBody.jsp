
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="eForm" name="technicalSkillsForm" type="com.form.TechnicalSkillsForm" />
<%
User user14 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">



<!--there is no custom header content for this example-->

</head>
 
<script language="javascript">
function createSkill(){
	var url = "<%=request.getContextPath()%>/technicalskills.do?method=createTechnicalSkill";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.technicalskills.create",user14.getLocale())%>',url,250,600, messageret);
}
function editSkill(technialSkillId){
	var url = "<%=request.getContextPath()%>/technicalskills.do?method=editTechnicalSkill&technialSkillId="+technialSkillId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.technicalskills.edit",user14.getLocale())%>',url,250,600, messageret);

	
}
function showmessage(returnval) { 
//	window.location.reload();
	}
function showmessage(returnval) { 
//	window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}
	function searchdata(){
		//alert("hi ");
		var skillname = document.technicalSkillsForm.technialSkillName.value.trim();
		 document.technicalSkillsForm.action = "technicalskills.do?method=technicallistlistsearch&technialSkillName="+skillname;
		 document.technicalSkillsForm.submit();

	}
</script>


<body class="yui-skin-sam">
<html:form action="/technicalskills.do?method=logon">
	<div class="div">

<table width="50%">
			<tr>
			<td><%=Constant.getResourceStringValue("admin.technicalskills.name",user14.getLocale())%> : </td>
			<td><html:text property="technialSkillName" size="40" maxlength="500"/></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.search",user14.getLocale())%>" onClick="searchdata()" class="button"></td>
		</tr>
		</table>
		</div>
</html:form>

<br><br>

<a class="button" href="#" onClick="createSkill()"><%=Constant.getResourceStringValue("admin.technicalskills.create",user14.getLocale())%></a>
 <br><br>
 <b>Skill list</b>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<br><br>

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editSkill('" + oRecord.getData("technialSkillId") + "')"+ ">" + sData + "</a>";		
 

        };

    // Column definitions
    var myColumnDefs = [
{key:"technialSkillId", hidden:true, sortable:true},
{key:"technialSkillName",label:"<%=Constant.getResourceStringValue("admin.technicalskills.name",user14.getLocale())%>",width:150, sortable:true,resizeable:true,formatter:formatUrl},
{key:"technialSkillDesc",label:"<%=Constant.getResourceStringValue("admin.technicalskills.desc",user14.getLocale())%>",width:250, sortable:false,resizeable:true},
{key:"status",label:"<%=Constant.getResourceStringValue("admin.technicalskills.status",user14.getLocale())%>", sortable:true,resizeable:true}

        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/technicalskillsListPage.jsp?&techSkillName=<%=eForm.getTechnialSkillName()%>&ddd="+(new Date).getTime()+"&");

    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"technialSkillId"},
{key:"technialSkillName"},
{key:"technialSkillDesc"},
{key:"status"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=technialSkillId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"technialSkillId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
