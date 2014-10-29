
<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>

<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>



<%
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
%>

<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.ONBOARD_TASK_DEF_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.ONBOARD_TASK_DEF_SCREEN);

%>
<bean:define id="onBoardingTaskDefiForm" name="onBoardingTaskDefiForm" type="com.form.OnBoardingTaskDefiForm"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
 


<!--there is no custom header content for this example-->

</head>
 </style>
<script language="javascript">

function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}

function createOnBoardingTaskDefi(){
	var url = "<%=request.getContextPath()%>/OnBoardingTaskDefi.do?method=createOnBoardingTaskDefi&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createonboarding.taskdefination",user.getLocale())%>',url,350,700, messageret);
}
function editOnBoardingTaskDefi(taskdef_id){
	var url = "<%=request.getContextPath()%>/OnBoardingTaskDefi.do?method=OnBoardingTaskDefiDetails&readPreview=2&id="+taskdef_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.editonboarding.taskdefination",user.getLocale())%>',url,350,700, messageret);

	
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

function searchcri(){
	var primaryOwnerId=0;
	  if(document.onBoardingTaskDefiForm.primaryOwnerId.value.trim() <0 || document.onBoardingTaskDefiForm.primaryOwnerId.value.trim()==null || document.onBoardingTaskDefiForm.primaryOwnerId.value.trim()==""){
		  
		   primaryOwnerId=0;
	  }else{
       primaryOwnerId=document.onBoardingTaskDefiForm.primaryOwnerId.value;
	  }
	   document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=searchOnboardTaskDefListpage&primaryOwnerId="+primaryOwnerId;
	   document.onBoardingTaskDefiForm.submit();
	
	}

function validateUserhm(){
document.onBoardingTaskDefiForm.primaryOwnername.value=document.onBoardingTaskDefiForm.primaryownernamehidden.value;
}

function resetData(){

document.onBoardingTaskDefiForm.taskName.value="";
document.onBoardingTaskDefiForm.primaryOwnername.value="";
document.onBoardingTaskDefiForm.primaryOwnerId.value="";

}
</script>



<script type="text/javascript">
	
$(function() {

	$("#primaryOwnername").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.onBoardingTaskDefiForm.primaryOwnerId.value=item.data;
		document.onBoardingTaskDefiForm.primaryownernamehidden.value=item.value;
		
		}
		});

});

function init(){
document.onBoardingTaskDefiForm.taskName.focus();
}

function configuareColumns(){
	var url = "<%=request.getContextPath()%>/configureColumns.do?method=configureColumnsOnboardTaskDefination";
    GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.configure.columns",user1.getLocale())%>',url,450,700, messageret);
}

</script>

<body class="yui-skin-sam">

 <html:form action="/OnBoardingTaskDefi.do?method=searchOnboardTaskDefListpage">
 <div class="div">
<table border="0" width="100%">
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.listpage.Taskdefinationname",user.getLocale())%>
			<html:text  property="taskName"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<!--<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.PrimaryOwnerName",user.getLocale())%>
			
			
			
		
		         <input type="hidden" id="primaryownernamehidden" name="primaryownernamehidden" value='<%=(onBoardingTaskDefiForm.getPrimaryOwnername()==null)?"":onBoardingTaskDefiForm.getPrimaryOwnername()%>'/>
				<input type="text"  id="primaryOwnername" name="primaryOwnername"  autocomplete="off" value="<%=(onBoardingTaskDefiForm.getPrimaryOwnername()==null)?"":onBoardingTaskDefiForm.getPrimaryOwnername()%>" onblur="validateUserhm()" >
				<span id="primaryOwnerId"></span>-->
				<%
                String primaryownerId = String.valueOf(onBoardingTaskDefiForm.getPrimaryOwnerId());
                 %>
                  <html:hidden  property="primaryOwnerId" value="<%=primaryownerId%>"/>
	    
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user.getLocale())%>" onClick="searchcri()" class="button">
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user.getLocale())%>" onClick="resetData()" class="button">
		</td>			
			
			
		</tr>
		
<tr>


<td>

</td>
<td></td>
</tr>
</table>
</div>
</html:form>
<br> <br>
<a class="button" href="#" onClick="createOnBoardingTaskDefi()"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createonboarding.taskdefination",user.getLocale())%></a>
&nbsp;<!-- <a href="#" onClick="configuareColumns();return              false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a>-->
 <br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editOnBoardingTaskDefi('" + oRecord.getData("taskdefid") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };


var formatPrimaryOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("primaryOwnerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };

    // Column definitions
    var myColumnDefs = [

		{key:"taskdefid", hidden:true, sortable:true},
		{key:"primaryOwnerId", hidden:true, sortable:true},
		
{key:"taskName",label:"<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.listpage.Taskdefinationname",user.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
 <%=strcolumncontent%>
	{key:"isGroup", hidden:true, sortable:true}
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/onboard/OnBoardingTaskDefilistPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&taskname=<%=onBoardingTaskDefiForm.getTaskName()%>&primaryownerid=<%=onBoardingTaskDefiForm.getPrimaryOwnerId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"taskdefid"},
{key:"taskName"},
<%=strkeycontent%>
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=taskdefid&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"taskdefid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
