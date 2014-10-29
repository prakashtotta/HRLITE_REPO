<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>
<bean:define id="questionsform" name="questionForm" type="com.form.QuestionForm" />




<script language="javascript">

function createQuestionnew(){
	var url = "<%=request.getContextPath()%>/question.do?method=createQuestion";
	GB_showCenter("<%=Constant.getResourceStringValue("admin.Questions.CreateQuestion",user1.getLocale())%>",url,250,650, messageret);
}




function editquestion(id){
	var url = "<%=request.getContextPath()%>/question.do?method=editquestion&readPreview=2&edit=2&value=5&questionId="+id;
	//var url = "question.do?method=editquestion&readPreview=2&edit=2&value=5&questionId="+id;
	//parent.showPopWin(url, 500, 300, showmessage,true);
	GB_showCenter('<%=Constant.getResourceStringValue("admin.Questions.editquescreenname",user1.getLocale())%>',url,250,650, messageret);
}
function deletequestion(id){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
		 document.questionForm.action = "question.do?method=questiondelete&readPreview=1&questionId="+id;
	     document.questionForm.submit();
	    
	 }
	
}

function showmessage(returnval) { 
	//window.location.reload();
	}

	function messageret(){
	//window.location.reload();

			}




</script>
<body class="yui-skin-sam">
<br>
 <b><%=Constant.getResourceStringValue("admin.Questions.details",user1.getLocale())%></b> <br><br>

<% String deleteQuestion = (String)request.getAttribute("deleteQuestion");%>

       <% if(deleteQuestion != null && deleteQuestion.equals("yes")){%>
       <img src='jsp/images/greentick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
		<font color="green"><%=Constant.getResourceStringValue("admin.Questions.deletemsg",user1.getLocale())%></font>  
		<br><br>
	<%}%>




<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->


<html:form action="/question.do?method=saveTags">


</html:form>
<a class="button" href="#" onClick="createQuestionnew()"><%=Constant.getResourceStringValue("admin.Questions.CreateQuestion",user1.getLocale())%></a>
<!--<a  href="#" onClick="createQuestionnew()"><%=Constant.getResourceStringValue("admin.Questions.CreateQuestion",user1.getLocale())%>222</a>-->
<br>
<br>
<b>Question list</b>
<br>
<br>

<div id="dynamicdata" class="div"></div>
<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
          
          elCell.innerHTML = "<a href='#' onClick=editquestion('" + oRecord.getData("questionId") + "')"+ ">" + sData + "</a>";

        };
var deleteUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=deletequestion('" + oRecord.getData("questionId") + "')"+ ">"+"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" + "</a>";
         
        };		
    // Column definitions
    var myColumnDefs = [
{key:"questionId", hidden:true, sortable:true},
{key:"questionName",label:"<%=Constant.getResourceStringValue("admin.Questions.name",user1.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"typeVal",label:"<%=Constant.getResourceStringValue("admin.Questions.type",user1.getLocale())%>", sortable:false,resizeable:true},


{key:"delete",label:"<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>",sortable:false,resizeable:true,formatter:deleteUrl} 
       
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/questionlistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
{key:"questionId"},
{key:"questionName"},
{key:"questionType"},
{key:"typeVal"}
			

			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=questionId&dir=asc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"questionId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
