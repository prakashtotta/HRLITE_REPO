
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<bean:define id="jForm" name="languagesForm" type="com.form.LanguagesForm" />
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
function addLanguage(){
	var url = "<%=request.getContextPath()%>/languages.do?method=addLanguage";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.language.create",user1.getLocale())%>',url,250,600, messageret);
}

function editLanguage(languageId){

	var url = "<%=request.getContextPath()%>/languages.do?method=editLanguage&languageId="+languageId;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.language.edit",user1.getLocale())%>',url,250,600, messageret);
	
}
function deleteLanguage(languageId){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){



			  document.languagesForm.action = "languages.do?method=deleteLanguage&languageId="+languageId;
			  document.languagesForm.submit();
	
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
	function searchdata(){
		//alert("hi ");
		var languageName = document.languagesForm.languageName.value.trim();

		  document.languagesForm.action = "languages.do?method=languagelistsearch&languageName="+languageName;
		  document.languagesForm.submit();

	}
	function resetdata(){
		 document.languagesForm.languageName.value="";
	}
</script>

<body class="yui-skin-sam">
<html:form action="/languages.do?method=languagelistsearch">
<div class="div">
<table border="0" width="100%">
			<tr>
			<td><%=Constant.getResourceStringValue("hr.user.language",user1.getLocale())%> : </td>
			<td><html:text property="languageName" size="18" maxlength="500"/></td>
			<td>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchdata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button">
			</td>
		</tr>
		</table>
		</div>
</html:form>
<br><br>

<a class="button" href="#" onClick="addLanguage()"><%=Constant.getResourceStringValue("admin.language.create",user1.getLocale())%> </a>

<br><br>
<b><%=Constant.getResourceStringValue("admin.language.list",user1.getLocale())%></b>
<br><br>

<%
String languagedeleted = (String)request.getAttribute("languagedeleted");
	
if(languagedeleted != null && languagedeleted.equals("yes")){
%>

<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("admin.language.deleted",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>
	<%}%>	
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editLanguage('" + oRecord.getData("languageId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deleteLanguage('" + oRecord.getData("languageId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [

{key:"languageId", hidden:true, sortable:true},
{key:"languageName",label:"<%=Constant.getResourceStringValue("admin.language.languagename",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"languageDesc",label:"<%=Constant.getResourceStringValue("admin.language.desc",user1.getLocale())%>",width:250 ,sortable:true,resizeable:true}
        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/languageslistpage.jsp?&languageName=<%=jForm.getLanguageName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"languageId"},
{key:"languageName"},
{key:"languageDesc"},
{key:"status"},
{key:"delete"}

			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=languageId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"languageId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
