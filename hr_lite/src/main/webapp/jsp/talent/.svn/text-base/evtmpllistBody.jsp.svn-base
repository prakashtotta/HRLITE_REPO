<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<head>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>

<script language="javascript">

function addGroupChar(){
	var url = "<%=request.getContextPath()%>/evtmpl.do?method=createevtmpl";
	GB_showCenter('<%=Constant.getResourceStringValue("admin.EvaluationTemplate.CreateEvaluationSheet",user1.getLocale())%> ',url,500,750, messageret);
}

function showmessage(returnval) { 
	window.location.reload();
	}


function selectAll() {
        records = dataTable.getRecordSet().getRecords();
   for (i=0; i < records.length; i++) {
        dataTable.getRecordSet().updateKey(records[i], "checked", "true");
    }
  dataTable.render();
   return false;
}
    
function unselectAll() {
        records = dataTable.getRecordSet().getRecords();
   for (i=0; i < records.length; i++) {
        dataTable.getRecordSet().updateKey(records[i], "checked", "");  
   }
   dataTable.render();
   return false;
}
	
function templtepreviewurl(templid){
  var url = "evtmpl.do?method=evtmplpreview&evtmplid="+templid;
  window.open(url, "EvaluationTemplatePreview","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function editTemplate(jb_id){
	var url = "<%=request.getContextPath()%>/evtmpl.do?method=editEvTemplate&evtmplid="+jb_id;
	GB_showCenter('<%=Constant.getResourceStringValue("admin.EvaluationTemplate.Editevaluationsheet",user1.getLocale())%>',url,500,750, messageret);
	
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
 <script src="http://yui.yahooapis.com/2.6.0/build/yuiloader/yuiloader-min.js" type="text/javascript"></script>
<body class="yui-skin-sam">

<form name="charForm">
<br>
<a  href="#" onclick="addGroupChar()"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.CreateEvaluationSheet",user1.getLocale())%></a> <br>


<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="dynamicdata"></div>

<script type="text/javascript">

YAHOO.example.DynamicData = function() {
	

		  var formatUrl = function(elCell, oRecord, oColumn, sData) {
         	  elCell.innerHTML = "<a href='#' onClick=editTemplate('" + oRecord.getData("evtmplId") + "')"+ ">" + sData + "</a>";
        };

		var previewUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a href='#' onClick=templtepreviewurl('" + oRecord.getData("evtmplId") + "')"+ ">" + "<%=Constant.getResourceStringValue("hr.button.preview",user1.getLocale())%>" + "</a>";
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";

		 

        };

		var deleteUrl = function(elCell, oRecord, oColumn, sData) {
			
         elCell.innerHTML = "<a  href='evtmpl.do?method=chardetails&charId=" + oRecord.getData("groupCharId") + "'"+ ">" + "delete" + "</a>";
		
       //elCell.innerHTML = "<a href='#' onClick='deleteChar("+oRecord.getData("charId")+")'"+ ">" + "delete" + "</a>";

		 

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
					if(recs[j].getData("Select")){
                   checknumber = checknumber + recs[j].getData("evtmplId") +",";
					}
					
        
				 }
  
                templtepreviewurl(checknumber);
				
				this.render();
			};

    // Column definitions
    var myColumnDefs = [
		   	{key:"evtmplId", hidden:true},
            {key:"evTmplName",label:"<%=Constant.getResourceStringValue("admin.EvaluationTemplate.evaluationsheet",user1.getLocale())%> ", sortable:true, resizeable:true,formatter:formatUrl},
		{key:"createdBy",label:"<%=Constant.getResourceStringValue("admin.EvaluationTemplate.createdby",user1.getLocale())%>", sortable:true, resizeable:true},
		{key:"createdDate",label:"<%=Constant.getResourceStringValue("admin.EvaluationTemplate.createdon",user1.getLocale())%>", sortable:true, resizeable:true},
				{key:"preview",label:"<%=Constant.getResourceStringValue("admin.EvaluationTemplate.preview",user1.getLocale())%>", sortable:false, resizeable:true,formatter:previewUrl}
		
			
		
            
        ];

    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    
    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jsp/talent/evtmpllistpage.jsp?ddd="+(new Date).getTime()+"&");
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
			{key:"evtmplId"},
            {key:"evTmplName"},
			{key:"createdBy"},
			{key:"createdDate"},
			  {key:"preview"}
			
			
			
			
            
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=evtmplId&dir=desc&startIndex=0&results=10", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"evtmplId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
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

	
	myDataTable.subscribe('checkboxClickEvent', function(oArgs) {
				var elCheckbox = oArgs.target;
				var newValue = elCheckbox.checked;
				var record = this.getRecord(elCheckbox);
				var column = this.getColumn(elCheckbox);
				record.setData(column.key,newValue);
			});
			
			myDataTable.on('theadCellClickEvent', function (oArgs) {
				var target = oArgs.target,
					column = this.getColumn(target),
					actualTarget = Event.getTarget(oArgs.event),
					check = actualTarget.checked;
					
				if (column.key == 'Select') {
					var rs = this.getRecordSet(), l = rs.getLength();
					for (var i=0;i < l; i++) {
						rs.getRecord(i).setData('Select',check);
					}
					this.render();
				}
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

</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->
</form>
</body>
</html>
