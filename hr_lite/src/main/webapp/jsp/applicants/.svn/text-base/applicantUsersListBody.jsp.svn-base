<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.criteria.*"%>
<%@ page import="com.form.*"%>



<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%


String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");
%>

<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8">


</head>
<bean:define id="cform" name="applicantUserForm" type="com.form.ApplicantUserForm" />

<%
////response.setHeader("Cache-Control", "no-cache");
//		//response.setHeader("Pragma", "no-cache");
//		//response.setIntHeader("Expires", 0);
%>
<script language="javascript">

 function searchapplicant(){
     var alertstr = "";
 	var appno = document.applicantUserForm.applicant_number.value.trim();

 	var showalert=false;
 		

 	if(isNaN(appno)){
      	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.not_a_number",user1.getLocale())%><BR>";
 		showalert = true;
 		}

	 if (showalert){
	     	alert(alertstr);
	        return false;
	     }
		
	  document.applicantUserForm.action = "applicantUser.do?method=searchapplicantUsers";
	  document.applicantUserForm.submit();
	
}
 function  resetData(){
	 document.applicantUserForm.fullName.value =""; 
	 document.applicantUserForm.applicant_number.value =""; 
	 document.applicantUserForm.emailId.value =""; 
	 document.applicantUserForm.createdBy.value =""; 
 }
 function  activate(checknumber){
	// alert("Active ids >> "+checknumber);
	  document.applicantUserForm.action = "applicantUser.do?method=updatestatusMultiple&status=A&applicantUserIds="+checknumber;
	  document.applicantUserForm.submit();
 }
 function  inactivate(checknumber){
	 //alert("InActive ids >> "+checknumber);
	 
	  document.applicantUserForm.action = "applicantUser.do?method=updatestatusMultiple&status=I&applicantUserIds="+checknumber;
	  document.applicantUserForm.submit();

 }
 function  deleted(checknumber){
	// alert("delete ids >> "+checknumber);
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	 
	  document.applicantUserForm.action = "applicantUser.do?method=updatestatusMultiple&status=D&applicantUserIds="+checknumber;
	  document.applicantUserForm.submit();
	  }

 }
 
 function init(){
	 document.applicantUserForm.fullName.focus(); 

	 }
</script>

<body class="yui-skin-sam" onLoad="init()">

<html:form action="/applicantUser.do?method=searchapplicantUsers">
<div class="div">
<table width="100%">
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%> &nbsp;&nbsp;&nbsp;</td>
			<td><html:text property="fullName" size="30"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.applicantno",user1.getLocale())%>&nbsp;&nbsp;&nbsp;</td>
			<%
			String appidvalue = (cform.getApplicant_number()==0)?"":String.valueOf(cform.getApplicant_number());
			%>

			<td><html:text property="applicant_number" size="30" value="<%=appidvalue%>"/></td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></td>
			<td><html:text property="emailId" size="30" /></td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Createdby",user1.getLocale())%></td>
			<td><html:text property="createdBy" size="30" /></td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td colspan="4">
			<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchapplicant();" class="button">
		
			<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
			</td>
		</tr>

</table>
</div>
</html:form>
<br>
<b> <%=Constant.getResourceStringValue("aquisition.applicant.Users.list",user1.getLocale())%></b>  <br>
<br>
<input type="button" id="active" name="login" value="<%=Constant.getResourceStringValue("hr.button.activate",user1.getLocale())%>" class="button">&nbsp;&nbsp;<input type="button" id="inactive" name="login" value="<%=Constant.getResourceStringValue("hr.button.inactivate",user1.getLocale())%>" class="button">&nbsp;&nbsp;<input type="button" id="delete" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" class="button">	
<br><br>

<div id="dynamicdata" class="div"></div>

</body>

<script type="text/javascript">

YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.Highlighting = function() {
    var formatUrl = function(elCell, oRecord, oColumn, sData) {
       elCell.innerHTML = "<a href='applicant.do?method=applicantDetails&backtolisturl=applicantUser.do?method=applicantUsersList&applicantId=" + oRecord.getData("applicantId") + "&secureid=" + oRecord.getData("uuid") +"'"+ " >" + sData + "</a>";
    };
    var formatUrl2 = function(elCell, oRecord, oColumn, sData) {
        if(oRecord.getData("status") == "I"){
        	elCell.innerHTML = "InActive";
        }else if(oRecord.getData("status") == "A"){
        	elCell.innerHTML = "Active";
        }
        
     };
    var submitter = function (callback, newValue)
    {
	   
        var record = this.getRecord();
	    var column = this.getColumn();
	//	alert(column);
        var oldValue = this.value;
		//alert(record.getData('address'));
        var datatable = this.getDataTable();

        // send the data to our update page to update the value in the database
        YAHOO.util.Connect.asyncRequest('POST', 'applicantUser.do',
        {
			
            success: function (o)
            {
				 callback(true, o.responseText); 
                
            },
            failure: function (o)
            {
                alert(o.statusText);
                callback();
            },
            scope: this
        },
        'action=cellEdit&column=' + column.key +
				     '&method=updatestatus' +
                     '&status=' + escape(newValue) +
                      '&applicantUserId=' + record.getData('applicantUserId') 
        );
    };
	    var Dom = YAHOO.util.Dom,
		Event = YAHOO.util.Event,
		log = function (msg) {
			YAHOO.log(msg,'info','deleteRowsBy demo');
		};

		YAHOO.widget.DataTable.prototype.inactiveRowsBy = function (condition) {
			var checknumber ="";
			var start = 0, count = 0, current = 0, 
				recs = this.getRecordSet().getRecords();
					   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
		                   checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
							
		        
				}
		        if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
					inactivate(checknumber);
				}
						
				this.render();
					};

		YAHOO.widget.DataTable.prototype.activeRowsBy = function (condition) {
			var checknumber ="";
			var start = 0, count = 0, current = 0, 
				recs = this.getRecordSet().getRecords();
								   
				for (j=0; j < recs.length; j++) {
					if(recs[j] !=null && recs[j].getData("Select")){
					     checknumber = checknumber + recs[j].getData("applicantId") +",";
					}
										
					        
				 }
				if(checknumber == ""){
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
					activate(checknumber);
				}
									
				this.render();
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
					alert("<%=Constant.getResourceStringValue("aquisition.applicant.selectapptmsg",user1.getLocale())%>");
					return false;
				}else{
					deleted(checknumber);
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
			{key:"Select",label:'<input type="checkbox" id="SelectAll"/>', width:"30", formatter:"checkbox"},
			{key:"applicantUserId", hidden:true},
			{key:"uuid", hidden:true},
			{key:"applicantId", hidden:true},
			{key:"fullName", label:"<%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>", sortable:false, resizeable:true,formatter:formatUrl,width:180},
            {key:"applicant_number", label:"<%=Constant.getResourceStringValue("aquisition.applicant.no",user1.getLocale())%>",sortable:false, resizeable:true},
            {key:"emailId", label:"<%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%>",sortable:true, resizeable:true},
    		{key:"jobTitle", label:"<%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>",sortable:false, resizeable:true,width:200},
            {key:"interviewState", label:"<%=Constant.getResourceStringValue("aquisition.applicant.iterview.state",user1.getLocale())%>",sortable:false, resizeable:true},
            {key:"createdBy", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Createdby",user1.getLocale())%>",sortable:true, resizeable:true},
            {key:"status",label:"<%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%>",formatter:formatUrl2}
            ];
    




    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance

	  var myDataSource = new YAHOO.util.DataSource("jsp/applicants/applicantUserslistpage.jsp?fullName=<%=cform.getFullName()%>&applicant_number=<%=cform.getApplicant_number()%>&emailId=<%=cform.getEmailId()%>&createdBy=<%=cform.getCreatedBy()%>&ddd="+(new Date).getTime()+"&");
   

      myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            
			{key:"applicantUserId"},
			{key:"fullName"},
			{key:"applicantId"},
			{key:"emailId"},    
			{key:"jobTitle"}, 
			{key:"interviewState"},
			{key:"createdBy"},     
			{key:"status"},
			{key:"uuid"},
				{key:"applicant_number"}
				
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=applicantUserId&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"applicantUserId", dir:YAHOO.widget.DataTable.CLASS_DESC}, // Sets UI initial sort arrow
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
			Event.on('inactive','click', function() {
				myDataTable.inactiveRowsBy(function (data) {
					return data.Select;
				});
			});

			Event.on('active','click', function() {
				myDataTable.activeRowsBy(function (data) {
					return data.Select;
				});
			});
			Event.on('delete','click', function() {
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


</html>
