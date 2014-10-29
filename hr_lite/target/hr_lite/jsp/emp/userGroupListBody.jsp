
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>

  <%

User user14 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>


<bean:define id="groupform" name="userGroupForm" type="com.form.UserGroupForm" />



<script language="javascript">
function addusergroup(){
	var url = "<%=request.getContextPath()%>/usergroup.do?method=createusergroup"
	GB_showCenter('<%=Constant.getResourceStringValue("hr.create.user.pagetitle.create",user14.getLocale())%>',url,580,600, messageret);
}

function editUserGroup(jb_id){
	var url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&usergroupid="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("hr.create.user.pagetitle.edite",user14.getLocale())%>',url,580,600, messageret);	
}

 

function showmessage(returnval) { 
	window.location.reload();
	}


	function messageret(){
	//window.location.reload();

			}

		function searchcri(){
			var userId=document.userGroupForm.userId.value;
			
		
	  document.userGroupForm.action = "usergroup.do?method=searchusergrouplistpage&userId="+userId;
	   document.userGroupForm.submit();
	 
	   
		}

  

function init(){
document.userGroupForm.usergrpName.focus(); 

}

function resetData(){
document.userGroupForm.usergrpName.value="";
document.userGroupForm.userhidden.value="";
document.userGroupForm.userName.value="";
document.userGroupForm.usergrpDesc.value="";
document.userGroupForm.userId.value="0";





}

</script>

<%@ include file="../common/autocompleteForLayout.jsp" %>

	<script>
	$(function() {


		$( "#userName" ).autocomplete({
			source: "jsp/talent/getUserDataJson.jsp",
			minLength: <%=Constant.getValue("autocomplete.min.chars")%>,
			select: function( event, ui ) {
				/*log( ui.item ?
					"Selected: " + ui.item.value + " aka " + ui.item.id :
					"Nothing selected, input was " + this.value );*/

					document.userGroupForm.userId.value=ui.item.id;
					document.userGroupForm.userhidden.value=ui.item.id;
			}
		});
	});
	</script>


<script language="javascript">
/* var $jq = jQuery.noConflict(true);
$jq(function() {

	$("#userName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',	
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.userGroupForm.userId.value=item.data;
		document.userGroupForm.userhidden.value=item.value;
		
		}
		});

});*/

</script>

<br>
 <b><%=Constant.getResourceStringValue("hr.user.search.user.groups",user14.getLocale())%>  </b> 
 <br><br>
<body class="yui-skin-sam" onLoad="init()">

<html:form action="/usergroup.do?method=usergrouplist">
<div class="div">
<table border="0">
		
		<tr>
			<td>
			<%=Constant.getResourceStringValue("hr.user.group_name",user14.getLocale())%>
			</td>
			<td>
			<html:text  property="usergrpName" size="40"/>
			</td>
			<td>Users</td>
			<td>
                <input type="hidden" id="userhidden" name="userhidden" value='<%=(groupform.getUserName()==null)?"":groupform.getUserName()%>'/>
				<input type="text"  id="userName" name="userName"  autocomplete="off" value="<%=(groupform.getUserName()==null)?"":groupform.getUserName()%>" >
				<span id="userId"></span>
				<%
                String usersId = String.valueOf(groupform.getUserId());
                 %>
                  <html:hidden  property="userId" value="<%=usersId%>"/>
				  
				 </td>

			
			</tr>
			<tr>
			<td>
			
			Description
			</td>
			<td>
			<html:text  property="usergrpDesc" size="40"/>
			
			
			
			</td>
			</td>
			<td></td>
			<td></td>

		</tr>
		<tr>			<td></td>
</tr>
<tr>
<td colspan="2">
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user14.getLocale())%>" onclick="javasctipt:searchcri()" class="button">

<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user14.getLocale())%>" onClick="resetData()" class="button">
</td>
<td></td>
<td></td>
<td></td>
</tr>
</table>
</div>
</html:form>



<br><br>


<a class="button" href="#" onClick="addusergroup()"><%=Constant.getResourceStringValue("hr.create.user.group",user14.getLocale())%></a>
<br><br>
<b>User group list</b>
<br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=editUserGroup('" + oRecord.getData("usergrpId") + "')"+ ">" + sData + "</a>";
        };

		var setstatusvalue = function(elCell, oRecord, oColumn, sData) {
           if(oRecord.getData("status")=='A'){
         	  elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.user.active",user14.getLocale())%>";
		   }else if(oRecord.getData("status")=='I'){
               elCell.innerHTML = "<%=Constant.getResourceStringValue("hr.user.inactive",user14.getLocale())%>";
		   }
        };

    // Column definitions
    var myColumnDefs = [
		
		{key:"usergrpId", hidden:true},
			{key:"usergrpName", label:"<%=Constant.getResourceStringValue("hr.user.group_name",user14.getLocale())%>",sortable:true, resizeable:true,formatter:formatUrl},
            {key:"createdBy", label:"<%=Constant.getResourceStringValue("hr.user.group.createdby",user14.getLocale())%>",sortable:true, resizeable:true},
            {key:"createdDate", label:"<%=Constant.getResourceStringValue("hr.user.group.created.on",user14.getLocale())%>",sortable:true, resizeable:true},
			{key:"status", label:"<%=Constant.getResourceStringValue("hr.user.Status",user14.getLocale())%>",sortable:true,formatter:setstatusvalue}

        ];

		
    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/emp/usergrouplistpage.jsp?groupname=<%=groupform.getUsergrpName()%>&description=<%=groupform.getUsergrpDesc()%>&userId=<%=groupform.getUserId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"usergrpId"},
			{key:"usergrpName"},
            {key:"createdBy"},
            {key:"createdDate"},
			{key:"status"}
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=usergrpId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"usergrpId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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
	 var highlightEditableCell = function(oArgs) {
            var elCell = oArgs.target;
            if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
                this.highlightCell(elCell);
            }
        };

	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
</script>



<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>
