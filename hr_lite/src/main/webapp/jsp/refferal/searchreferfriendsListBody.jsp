<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

<style type="text/css">
	
	body {
	margin:0;
	padding:0;
}
</style>
  <%
 String datepattern = Constant.getValue("defaultdateformat");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

}
%>


<bean:define id="aForm" name="referAFriendForm" type="com.form.ReferAFriendForm" />
<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
 
%>
<script type="text/javascript">


$(function() {

	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#name").autocomplete({
		
		//url: 'jsp/refferal/getReferralApplicantNames.jsp'
		url: 'jsp/refferal/getMyReferfriendNames.jsp'
		
});
});
	</script>
<script language="javascript">

 function searchapplicant(){
	
document.referAFriendForm.action = "referfriend.do?method=searchmyfriends";
document.referAFriendForm.submit();
}

 function editfriends(friends_id){
		var url = "<%=request.getContextPath()%>/referfriend.do?method=editfriend&referafriendId="+friends_id;
		GB_showCenter('Edit Refer Friend',url,300,600, messageret);
	}

function jobdetails(jbid){
		var url = "refjob.do?method=jobdetailsforReferral&reqid="+jbid;
  window.open(url,  'JobRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=no');
	}




	function messageret(){
		window.location.reload();

				}
function init(){

document.referAFriendForm.name.focus();

}
function addfriend(){
	var url = "<%=request.getContextPath()%>/referfriend.do?method=createreferfriend";
	GB_showCenter('Refer a friend',url,400,600,messageret);

}
function messageret(){
	window.location.reload();
}
function resetdata(){
	document.referAFriendForm.name.value="";
}
</script>
<br>
<b><a class="button" href="#" onClick="javascript:addfriend()">Refer a friend</a></b>
<br><br>
<body class="yui-skin-sam" onLoad="init()">
<html:form action="/referfriend.do?method=searchmyfriends">
<div class="div">
<table border="0" width="100%">
		
	
		<tr>
			<td width="40%">Friends Name :</td>
			<td width="40%"><input type="text" class="textdynamic" name="name" id="name"  autocomplete="off" size="30" maxlength="600" value="<%=(aForm.getName()==null)?"":aForm.getName()%>"/></td>
			<td> <input type="button" name="search" value="search" onClick="searchapplicant()" class="button">
			
			</td>
			<td><input type="button" name="reset" value="Reset" onClick="resetdata()" class="button">
	</td>
		</tr>

		
<tr>
<td>

</td>
<td></td>
</tr>
</table>
</div>
<br><br>
 <b>My Refer Friends List  </b><br>
<br>
<div id="dynamicdata" class="div"></div>
</html:form>
<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
        
          elCell.innerHTML = "<a href='#' onClick=editfriends('" + oRecord.getData("referafriendId") + "')"+ ">" + sData + "</a>";		
          };


	var jobdetailsUrl = function(elCell, oRecord, oColumn, sData) {
        
          elCell.innerHTML = "<a href='#' onClick=jobdetails('" + oRecord.getData("jobRequisitionId") + "')"+ ">" + sData + "</a>";		
          };


    // Column definitions
    var myColumnDefs = [
			{key:"referafriendId", hidden:true},
			{key:"jobRequisitionId", hidden:true},
		    {key:"name", label:"Name", sortable:true, resizeable:true,formatter:formatUrl},
            {key:"emailId", label:"Email",sortable:true, resizeable:true},
			{key:"note", label:"Note",sortable:true, resizeable:true},
			{key:"jobTitle", label:"jobTitle", sortable:true, resizeable:true,formatter:jobdetailsUrl}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/refferal/searchreferfriendsListpage.jsp?searchpagedisplay=<%=searchpagedisplay%>&friendsname=<%=aForm.getName()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"referafriendId"},
            {key:"name"},           
		   	{key:"emailId"},
			{key:"note"},
			{key:"status"},
			{key:"jobRequisitionId"},
			{key:"jobTitle"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=referafriendId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"referafriendId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
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

<%//}%>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->



</body>

