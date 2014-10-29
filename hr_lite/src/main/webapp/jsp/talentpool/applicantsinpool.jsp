<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String id = (String)request.getAttribute("id");
%>
<title><%=Constant.getResourceStringValue("aquisition.applicant.list",user1.getLocale())%></title>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<script language="javascript">

function searchapplicants(){
	document.applicantForm.action = "<%=request.getContextPath()%>/applicant.do?method=serachinapplicantpoolpopuppage&id=<%=id%>";
	document.applicantForm.submit();
}

function editapplicant(aid){
	var url = "<%=request.getContextPath()%>/applicant.do?method=editapplicantPoolData&applicantid="+aid;
	
window.showModalDialog(url,"<%=Constant.getResourceStringValue("aquisition.applicant.editapp",user1.getLocale())%>","dialogHeight: 600px; dialogWidth: 900px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; menubar=no; toolbar=no; center: Yes; resizable: Yes; status: off;");

}
	
	
	function messageret(){
	window.location.reload();
	
			}
			function messageret1(){
	
			}

function closewindow(){

//parent.parent.GB_hide();

self.close(); 
	  
	}

	function closerefresh(){
		parent.parent.GB_hide();
location.href=location.href;
	  
	}

function resetData(){
  document.applicantForm.applicantNo.value="";
  document.applicantForm.fullName.value="";
   document.applicantForm.primarySkill.value="";
  document.applicantForm.keywords.value="";
 
 } 

</script>
<br>
<a href="#" onClick="closerefresh()" >close</a>
<br><br>
<html:form action="/applicant.do?method=serachinapplicantpoolpopuppage">

<table border="0" width="75%">
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%></td>
<td><html:text property="applicantNo" size="30"/></td>
<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
<td><html:text property="fullName" size="30" maxlength="500"/></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%></td>
<td>
			<html:select  property="primarySkill">
			<option value=""></option>
			<bean:define name="applicantForm" property="lovList" id="lovList" />
            <html:options collection="lovList" property="technialSkillName"  labelProperty="technialSkillName"/>
			</html:select>
</td>
<td><%=Constant.getResourceStringValue("aquisition.applicant.keyword",user1.getLocale())%></td>
	 <td><html:text property="keywords" size="30" maxlength="600"  /></td>
<td></td>
<td></td>
</tr>
</table>
<table>
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicants()">
</td>
<td>
<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()">
</td>

</tr>
</table>
</html:form>

<body class="yui-skin-sam">

 <%=Constant.getResourceStringValue("aquisition.applicant.list",user1.getLocale())%>  <br><br>

<div id="toalrecords"></div>
<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.Highlighting = function() {
	

var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "');return false;"+ ">" + sData + "</a>";
         
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
		{key:"primarySkill", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Skill",user1.getLocale())%>", sortable:true, resizeable:true}
        ];





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talentpool/applicantlistpage.jsp?keywords=<%=aform.getKeywords()%>&id=<%=id%>&fullname=<%=aform.getFullName()%>&applicantNo=<%=aform.getApplicantNo()%>&primarySkill=<%=aform.getPrimarySkill()%>&ddd="+(new Date).getTime()+"&");
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
			{key:"uuid"}
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

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>

