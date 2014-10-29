<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/dashboardinclude.jsp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String id = (String)request.getParameter("id");
String secureid = (String)request.getParameter("secureid");

String state = "0";
String isapplicationsubmitted = "false";

String requistionId = id.substring(0,id.indexOf("_"));

state = id.substring(id.indexOf("_")+1);
	
 if(state != null && state.equals(Common.APPLICATION_SUBMITTED)){
	 isapplicationsubmitted="true";
 }

%>

<span id="applicantdata">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Applicant List</b><br>
<!-- body start-->

<table>
<tr>
<td width="10px"></td>
<td>
<a class="button" href="#" onClick="javascript:addapplicant()"><%=Constant.getResourceStringValue("aquisition.applicant.createapp",user1.getLocale())%></a> | 
</td>
	 <td>
 <div class="bd">
			<div id="container"></div>
			<a  class="button" href="#" id="delete"><%=Constant.getResourceStringValue("aquisition.applicant.send.profile.for.screening",user1.getLocale())%></a> | 
			
		</div>
</td><td>
 <div class="bd">
			<div id="container"></div>
			<a  class="button" href="#" id="delete1"><%=Constant.getResourceStringValue("aquisition.applicant.markfordeletion",user1.getLocale())%></a> | 
			
		</div>
</td> <td>
 <div class="bd">
			<div id="container"></div>
			<a  class="button" href="#" id="compare"><%=Constant.getResourceStringValue("aquisition.applicant.compare",user1.getLocale())%></a> 
			
		</div>

</td>

</tr>
</table>
<br>
<table>
	<tr>
	<td width="10px"></td>
	<td>
	<div id="toalrecords" class="msg"></div>
	</td>
		<td>
	<a  class="button" href="#" onClick="configuareColumns();return false;"><%=Constant.getResourceStringValue("aquisition.applicant.Configuare_Columns",user1.getLocale())%></a> 
	
	</td>
	</tr>
	</table>
	<table>
	<tr>
	<td width="10px"></td>
	<td>
	<div id="dynamicdata" class="div"></div>
	</td>
	
	</tr>
	</table>



</span>
<script language="javascript">
var isapplicationsubmitted=<%=isapplicationsubmitted%>;
</script>
<%
 String strcolumncontent = ScreenSettingUtils.getApplicationScreenSettings(user1,Common.APPLICANT_SUMMARY_SCREEN);
 String strkeycontent = ScreenSettingUtils.getApplicationScreenSettingsKeys(Common.APPLICANT_SUMMARY_SCREEN);

%>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {

	
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		
		if(oRecord.getData("filterError") > 0){
			elCell.innerHTML = "<a href='#' onClick=callJavascriptOpenfunc('" + oRecord.getData("uuid") + "')"+ ">" +" <img src='jsp/images/red_start_icon.jpg' border='0' height='10' width='10'/>" + sData + "</a>";
			
		}else{
          elCell.innerHTML = "<a href='#' onClick=callJavascriptOpenfunc('" + oRecord.getData("uuid") + "')"+ ">" +" " + sData + "</a>";
		}
        };


var editUrl = function(elCell, oRecord, oColumn, sData) {
	 elCell.innerHTML = "<a href='#' onClick=editapplicant('" + oRecord.getData("applicantId") + "')"+ ">" + "edit" + "</a>";
         
        };

var formatUrljobreq = function(elCell, oRecord, oColumn, sData) {
		
        var editreqd = "<a href='#' onClick=editJobReq('" + oRecord.getData("reqId") + "')"+ ">" + sData + "</a>";
		elCell.innerHTML = editreqd;
        };

	var formatUrlofferowner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isofferownerGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("isofferownerGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("offerownerId")+ "'"+","+"'"+ oRecord.getData("isofferownerGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };

	var formatUrlForRecruiter = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("recruitergroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;
			}else if(oRecord.getData("recruitergroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+sData;		
			}
			
		 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("recruiterId")+ "'"+","+"'"+ oRecord.getData("recruitergroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
        
        };



var formatApplicantOwner = function(elCell, oRecord, oColumn, sData) {
            var ndata1 = "";
	        if(oRecord.getData("isGroup")=='Y'){
				ndata1 = '<img src="jsp/images/User-Group-icon.png" border="0" alt="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.group",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownernamegroup");
             
			 elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownergroupId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";

			}else if(oRecord.getData("isGroup")=='N'){
				ndata1 = '<img src="jsp/images/user.gif" border="0" alt="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.user.user",user1.getLocale())%>" height="20"  width="19"  align="middle"/>'+oRecord.getData("ownername");
				
				elCell.innerHTML = "<a href='#' onClick=userDetailsOrGroup(" +"'"+ oRecord.getData("ownerId")+ "'"+","+"'"+ oRecord.getData("isGroup")+ "'"+ ");return false;"+ ">" + ndata1 + "</a>";
			}
        
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


	YAHOO.widget.DataTable.prototype.deleteRowsBy1 = function (condition) {
	var checknumber ="";
				var start = 0, count = 0, current = 0, 
					recs = this.getRecordSet().getRecords();
			        
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("uuid") +",";
					}
					
        
				 }
                if(checknumber == ""){
					alert("Please select applicants");
					return false;
				}else{
                markdeletebulk(checknumber);
				}
				
				this.render();
			};


			YAHOO.widget.DataTable.prototype.compareRowsBy = function (condition) {
			var checknumber ="";
				var start = 0, count = 0, current = 0, tcount = 0 ,
					recs = this.getRecordSet().getRecords();
			   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("applicantId") +",";
				   tcount++;
					}
					
        
				 }
                if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
					 if(tcount < 2){
						alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg2",user1.getLocale())%>");
					return false;
					}
					if(tcount > 4){
						alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg6",user1.getLocale())%>");
					return false;
					}
                compareapplicants(checknumber);
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
		{key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
			{key:"applicantId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"reqId", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		    <%=strcolumncontent%>
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];

	if(isapplicationsubmitted == false){

		 myColumnDefs = [
			   {key:"Select",label:'<input type="checkbox"/>', width:"30", formatter:"checkbox"},
		  {key:"applicantId", hidden:true},
			{key:"uuid", hidden:true},
		{key:"reqId", hidden:true},
		{key:"offerownerId", hidden:true},
		{key:"ownerId", hidden:true},
		{key:"ownergroupId", hidden:true},
		{key:"ownernamegroup", hidden:true},
		{key:"isGroup", hidden:true},
            {key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:true, resizeable:true,formatter:formatUrl},
		    <%=strcolumncontent%>
		//{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:true, resizeable:true},
			
			{key:"edit",label:"<%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%>", sortable:false, resizeable:true,formatter:editUrl}
		
			
           
        ];
	}
    





    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/treeapplicantlistpage.jsp?requistionId=<%=requistionId%>&secureid=<%=secureid%>&state=<%=state%>&ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
           <%=strkeycontent%>
			
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);

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
			


			
			Event.on('delete','click', function() {
				myDataTable.deleteRowsBy(function (data) {
					return data.Select;
				});
			});

		Event.on('delete1','click', function() {
				myDataTable.deleteRowsBy1(function (data) {
					return data.Select;
				});
			});
		Event.on('compare','click', function() {
				myDataTable.compareRowsBy(function (data) {
					return data.Select;
				});
			});
			
    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();
</script>