
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

<bean:define id="Onboardingtemplateform" name="OnboardingtemplateForm" type="com.form.OnBoardingTemplateForm" />

<%
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">



<!--there is no custom header content for this example-->

</head>

</style>
<script language="javascript">
function createOnBoardingTemplate(){
	var url = "<%=request.getContextPath()%>/OnBoardingTemplate.do?method=createOnBoardingTemplate&readPreview=1";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.OnBoardTemplate.bodylistpage.graymessage.CreateOnboardingtemplate",user.getLocale())%>',url,300,650, messageret);
}
function editOnBoardingTemplate(template_id){
	var url = "<%=request.getContextPath()%>/OnBoardingTemplate.do?method=OnBoardingTemplateDetails&readPreview=2&id="+template_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.OnBoardTemplate.bodylistpage.graymessage.CreateOnboardingtemplate.EditOnboardingtemplate",user.getLocale())%>',url,300,650, messageret);

	
}
function showmessage(returnval) { 
//	window.location.reload();
	}


function messageret(){
//	window.location.reload();

	}

function searchcri(){
       
	   document.OnboardingtemplateForm.action = "OnBoardingTemplate.do?method=searchOnboardTemplateListpage";
	   document.OnboardingtemplateForm.submit();
	
	}

function resetData(){
document.OnboardingtemplateForm.templateName.value="";
document.OnboardingtemplateForm.primaryOwnerName.value="";
document.OnboardingtemplateForm.primaryownernamehidden.value="";
document.OnboardingtemplateForm.primaryOwnerId.value="";

}
function validateUserhm(){
document.OnboardingtemplateForm.primaryOwnerName.value=document.OnboardingtemplateForm.primaryownernamehidden.value;
}

</script>

<script type="text/javascript">
	
$(function() {

	$("#primaryOwnerName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.OnboardingtemplateForm.primaryOwnerId.value=+item.data;
		document.OnboardingtemplateForm.primaryownernamehidden.value=item.value;
		}
		});

});

function init(){
document.OnboardingtemplateForm.templateName.focus();
}
</script>
<body class="yui-skin-sam" onLoad="init()" >

<html:form action="/OnBoardingTemplate.do?method=searchOnboardTemplateListpage">
<div class="div">
<table border="0" width="100%">
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTemplate.createpage.search.name.templatename",user.getLocale())%>
			<html:text  property="templateName"/>
		    <!-- <%=Constant.getResourceStringValue("admin.OnBoardTaskDef.PrimaryOwnerName",user.getLocale())%>
			
			
			
			
		         <input type="hidden" id="primaryownernamehidden" name="primaryownernamehidden" value='<%=(Onboardingtemplateform.getPrimaryOwnerName()==null)?"":Onboardingtemplateform.getPrimaryOwnerName()%>'/>
				<input type="text"  id="primaryOwnerName" name="primaryOwnerName"  autocomplete="off" value="<%=(Onboardingtemplateform.getPrimaryOwnerName()==null)?"":Onboardingtemplateform.getPrimaryOwnerName()%>" onblur="validateUserhm()" >
				<span id="primaryOwnerId"></span>-->
				<%
                String primaryownerId = String.valueOf(Onboardingtemplateform.getPrimaryOwnerId());
                 %>
                  <html:hidden  property="primaryOwnerId" value="<%=primaryownerId%>"/>
	   <input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user.getLocale())%>" onClick="searchcri()" class="button">
       <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user.getLocale())%>" onClick="resetData()" class="button">
		
		</td>			
			</td><td></td><td></td>
			
		</tr>
		
<tr>
<td>

</td>
<td></td>
</tr>
</table>
</div>
</html:form>
<br><br>

<body class="yui-skin-sam">


<br><a class="button" href="#" onClick="createOnBoardingTemplate()"><%=Constant.getResourceStringValue("admin.OnBoardTemplate.bodylistpage.graymessage.CreateOnboardingtemplate",user.getLocale())%></a>
 <br><br>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		 var formatUrl = function(elCell, oRecord, oColumn, sData) {
			 elCell.innerHTML =
	elCell.innerHTML = "<a href='#' onClick=editOnBoardingTemplate('" + oRecord.getData("templateid") + "')"+ ">" + sData + "</a>";		
  // elCell.innerHTML = "<a class='submodal-300-200' href='location.do?method=locationdetails&id=" + oRecord.getData("locationId") + "'"+ ">" + sData + "</a>";

        };

    // Column definitions
    var myColumnDefs = [
{key:"templateid", hidden:true, sortable:true},
{key:"templateName",label:"<%=Constant.getResourceStringValue("admin.OnBoardTemplate.bodylistpage.Search.TemplateName",user.getLocale())%>", sortable:true,resizeable:true,formatter:formatUrl},
{key:"templateDesc",label:"<%=Constant.getResourceStringValue("admin.OnBoardTemplate.bodylistpage.OnboardingTemplateDescription",user.getLocale())%>", sortable:false, resizeable:true},
{key:"createdBy",label:"<%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Created_By",user.getLocale())%>", sortable:false, resizeable:true},
{key:"createdDate",label:"<%=Constant.getResourceStringValue("admin.Competencies.Createdon",user1.getLocale())%>", sortable:false, resizeable:true}
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/onboard/OnBoardingTemplatelistPage.jsp?searchpagedisplay=<%=searchpagedisplay%>&onboardtemname=<%=Onboardingtemplateform.getTemplateName()%>&primaryownerid=<%=Onboardingtemplateform.getPrimaryOwnerId()%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [

{key:"templateid"},
{key:"templateName"},
{key:"templateDesc"},
{key:"createdBy"},
{key:"createdDate"}
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=templateid&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"templateid", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
