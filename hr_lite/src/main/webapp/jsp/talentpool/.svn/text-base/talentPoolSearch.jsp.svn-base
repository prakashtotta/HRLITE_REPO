<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<SCRIPT LANGUAGE="JavaScript">
function searchapplicants(){
	//var securityparam = "&csrfcode="+document.applicantForm["csrfcode"].value;
	//alert(securityparam);
	document.applicantForm.action = "<%=request.getContextPath()%>/applicant.do?method=serachinapplicantpool";
	document.applicantForm.submit();
}

function editApplicant(appid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicantPoolData&applicantid="+appid;
	GB_showCenter('Applicants',url, 800,900, messageret1);

//window.showModalDialog(url,"<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>","dialogHeight: 600px; dialogWidth: 1000px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; menubar=no; toolbar=no; center: Yes; resizable: Yes; status: off;");

			}

function applicantSummary(id,uuid){
var url = "<%=request.getContextPath()%>/applicant.do?method=applicantDetailsPopup&applicantId="+id+"&secureid="+uuid;
window.open(url, "ApplicantDetails","location=0,status=0,scrollbars=1,menubar=0,resizable=1,left=10, top=10,width=1200,height=900");
}


function resetData(){
  document.applicantForm.applicantId.value="";
  document.applicantForm.fullName.value="";
   document.applicantForm.primarySkill.value="";
 document.applicantForm.keywords.value="";
 
 }  
</script>

<script language="javascript">
window.name = 'myModal';
</script>

<%

String issearchresult = (String)session.getAttribute("issearchresult");
String applicantIdvalue = (String)session.getAttribute("applicantIdvalue");
String primarySkill = (String)session.getAttribute("primarySkill");
String keywords = (String)session.getAttribute("keywords");

if(!StringUtils.isNullOrEmpty(applicantIdvalue) && applicantIdvalue.equals("0")){
	applicantIdvalue="";
}
String fullName = (String)session.getAttribute("fullName");
String emptyStr = "";

List skillList = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
%>

<b> Search applicants</b> &nbsp;&nbsp;<a class="button" href="#" onClick="addApplicant();return false">add applicant</a><br>
<br>

<html:form  action="/applicant.do?method=serachinapplicantpool" target='myModal'>
<input type="hidden" name="csrfcode" value="" />
<div align="center" class="div">
	<table border="0" width="100%">
	
	
	<tr>
     <td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
	 <td><html:text property="applicantId" size="30" maxlength="600" value="<%=(applicantIdvalue== null || applicantIdvalue.equals(0))?emptyStr:applicantIdvalue%>"/></td>
	 <td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
	 <td><html:text property="fullName" size="30" maxlength="600" value="<%=(fullName== null)?emptyStr:fullName%>"/></td>
	</tr>
	<tr>
     <td><%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%></td>
	 <td>
	<select name="primarySkill">
	<option value =""></option>
	<% for (int i=0;i<skillList.size();i++){
		TechnicalSkills skill = (TechnicalSkills)skillList.get(i);
		String selectedvalue= "";
		if(!StringUtils.isNullOrEmpty(skill.getTechnialSkillName()) && skill.getTechnialSkillName().equals(primarySkill)){
			selectedvalue="selected";
		}
	%>
	<option value ="<%=skill.getTechnialSkillName()%>" <%=selectedvalue%>><%=skill.getTechnialSkillName()%></option>
	<%}%>
	</select>

	</td>
	 <td><%=Constant.getResourceStringValue("aquisition.applicant.keyword",user1.getLocale())%></td>
	 <%String newPos = ""; %>
	 <td><html:text property="keywords" size="30" maxlength="600"  value="<%=(keywords== null)?newPos:keywords%>"/></td>
	</tr>

	<tr>
     <td colspan="2"><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicants()" class="button">
	 <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
	 </td>
	</tr>
</table>
</div>
</html:form>

<br>
 
<body class="yui-skin-sam">
<%

if(session.getAttribute("issearchresult") != null){
			request.getSession().removeAttribute("issearchresult");
		}
if(session.getAttribute("applicantIdvalue") != null){
			request.getSession().removeAttribute("applicantIdvalue");
		}
if(session.getAttribute("fullName") != null){
			request.getSession().removeAttribute("fullName");
		}
if(session.getAttribute("primarySkill") != null){
			request.getSession().removeAttribute("primarySkill");
		}
if(session.getAttribute("keywords") != null){
			request.getSession().removeAttribute("keywords");
		}



%>
<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<%//if(issearchresult != null && issearchresult.equals("yes")){%>


<div id="toalrecords" class="msg"></div><br>
<div id="dynamicdata" class="div"></div>

<script type="text/javascript">

YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.Highlighting = function() {
	

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editApplicant('" + oRecord.getData("applicantId") + "');return false;"+ ">" + sData + "</a>";
         
        };

var formatUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=applicantSummary(" +"'"+ oRecord.getData("applicantId")+ "'"+","+"'"+ oRecord.getData("uuid")+ "'"+ ");return false;"+ ">" + "<%=Constant.getResourceStringValue("aquisition.applicant.summary.details",user1.getLocale())%>" + "</a>"
         
        };



var Dom = YAHOO.util.Dom,
				Event = YAHOO.util.Event,
				log = function (msg) {
					YAHOO.log(msg,'info','deleteRowsBy demo');
				};

YAHOO.widget.DataTable.prototype.deleteRowsBy = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                sendbulkresumeforscreening(checknumber);
				}
				
				this.render();
			};

YAHOO.widget.DataTable.prototype.forAllRecords = function (fn,scope) {
		
		if (!Lang.isFunction(fn)) {return;}
		scope || (scope = this);
		for (var rs = this.getRecordSet(), l = rs.getLength(), i = 0; i < l; i++) {
			if (fn.call(scope, rs.getRecord(i), i) === false) {return;}
		}
	};

    // Column definitions
    var myColumnDefs = [
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:editUrl,width:180},
             {key:"email", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"phone", label:"<%=Constant.getResourceStringValue("aquisition.applicant.phone",user1.getLocale())%>",sortable:true, resizeable:true},
		{key:"heighestQualification", label:"<%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"appliedDate", label:"<%=Constant.getResourceStringValue("aquisition.applicant.applied.date",user1.getLocale())%>",sortable:true, resizeable:true,width:150},
		{key:"primarySkill", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"summary", label:"<%=Constant.getResourceStringValue("aquisition.applicant.summary.details",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl}
		
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("../talentpool/applicantserachlistpage.jsp?keywords=<%=keywords%>&fullName=<%=fullName%>&applicantIdvalue=<%=applicantIdvalue%>&primarySkill=<%=primarySkill%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"applicantId"},
            {key:"fullName"},           
		   	{key:"email"},
		{key:"phone"},
		{key:"heighestQualification"},
		{key:"interviewState"},
		{key:"appliedDate"},
		{key:"jobTitle"},
		{key:"resumeSourceTypeStr"},
		{key:"ownername"},
			{key:"primarySkill"},			
			{key:"uuid"},
            {key:"summary"}
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };

    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
	        // Enable row highlighting
        myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
        myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
		myDataTable.subscribe( 'dataReturnEvent', function(oArgs) {
		var my_row_total = oArgs.response.meta.totalRecords;
		document.getElementById('toalrecords').innerHTML = '<%=Constant.getResourceStringValue("hr.total.no.of.results",user1.getLocale())%> : ' + my_row_total;
   });
    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    }

	myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
	   
		var elCheckbox = oArgs.target;
		var newValue = elCheckbox.checked;
		var record = this.getRecord(elCheckbox);
		var column = this.getColumn(elCheckbox);
		record.setData(column.key,newValue);
		
		var allChecked = true;
		this.forAllRecords(function (r) {
			 
			if (!r.getData('Select')) {
				allChecked = false;
				return false;
				 
			}
		});
		Dom.get('SelectAll').checked = allChecked;
	});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					
					for (var i=0;i < l; i++) {
						if(rs.getRecord(i) != null){
						rs.getRecord(i).setData('Select',check);
						}
					}
					this.render();
				}
			});
			



		Event.on('movetoreq','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
});
</script>
<%//}%>

</body>