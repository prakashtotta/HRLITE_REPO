<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>


<html>
<bean:define id="cform" name="charForm" type="com.form.CharForm" />
<head>

<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>
<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

	  
		function searchdata(){
			//alert("hi ");
			var charname = document.charForm.charName.value.trim();
			 document.charForm.action = "char.do?method=charlistsearch&charName="+charname;
			 document.charForm.submit();

		}
</script>
<script language="javascript">

function addChar(){
	var url = "<%=request.getContextPath()%>/char.do?method=createChar";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Competencies.createuserscreenname",user1.getLocale())%>',url,200,600, messageret);
	
}

function showmessage(returnval) { 
	
	window.location.reload();
	}

function viewchardata(jb_id){
	var url = "<%=request.getContextPath()%>/char.do?method=editChar&charId="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Competencies.edituserscreenname",user1.getLocale())%>',url,200,600, messageret);
}
	
	function showmessage(returnval) { 
	window.location.reload();
	}

	function messageret(){
	window.location.reload();

			}

</script>


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>



<!--there is no custom header content for this example-->

</head>

<body class="yui-skin-sam">

<html:form action="/char.do?method=logon">
<div class="div">
<table width="50%">
			<tr>
			<td><%=Constant.getResourceStringValue("admin.Competencies.Competencyname",user1.getLocale())%>: </td>
			<td><html:text property="charName" size="40" maxlength="500"/></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchdata()" class="button"></td>
		</tr>
		</table>
		</div>
</html:form>

<br><br>

<a class="button" href="#" onclick="addChar()"><%=Constant.getResourceStringValue("admin.Competencies.createuserscreenname",user1.getLocale())%></a>

<br><br>
<b>Competencies list</b>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<br><br>

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=viewchardata('" + oRecord.getData("charId") + "')"+ ">" + sData + "</a>";
        };

		var deleteUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=deletechar('" + oRecord.getData("charId") + "')"+ ">" + "delete" + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";

		 

        };

    // Column definitions
    var myColumnDefs = [
			{key:"charId", hidden:true},
            {key:"charName",label:"<%=Constant.getResourceStringValue("admin.Competencies.Competencyname",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
				{key:"createdBy",label:"<%=Constant.getResourceStringValue("admin.Competencies.Createdby",user1.getLocale())%> ", sortable:true, resizeable:true},
		{key:"createdDate",label:"<%=Constant.getResourceStringValue("admin.Competencies.Createdon",user1.getLocale())%> ", sortable:true, resizeable:true}

			
		
            
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/charlistpage.jsp?charname=<%=cform.getCharName()%>&ddd="+(new Date).getTime()+"&");
    
    
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"charId"},
            {key:"charName"},
			{key:"weight"},
			{key:"createdBy"},
			{key:"createdDate"}
			
			
            
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=charId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"charId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
