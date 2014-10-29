<%@ include file="../common/include.jsp" %>
<script type="text/javascript" src="jsp/autocomplete/jquery.min.js"></script>
<script type="text/javascript" src="jsp/dragdrop/jquery.tablednd_0_5.js"></script>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.lov.*" %>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.filter.*" %>
<%
System.out.println("screen field .... ");
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String screenfieldsupdated = (String)request.getAttribute("screenfieldsupdated");
System.out.println("screenfieldsupdated : "+screenfieldsupdated);
%>
<bean:define id="screenForm" name="screenFieldForm" type="com.form.ScreenFieldForm" />

<style>
span1{color:#ff0000;}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</style>

<style>
td.dragHandle {
	
}
td.showDragHandle {
	background-position: center; cursor: move; background-image: url("jsp/images/updown2.gif"); background-repeat: no-repeat;
}
td.dottedline{
	border-top:1px dashed #f00; margin-top:1em; padding-top:1em;
}
</style>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	 parent.parent.GB_hide();
	 
	   } 
	}
function savedata(){
	//alert("hello there .. ");
	document.getElementById('progressbartable1').style.display = 'inline';
	  document.screenFieldForm.action = "screenfield.do?method=savescreenfields&screencode=<%=screenForm.getScreenCode()%>";
	  document.screenFieldForm.submit();

}
function init(){

	document.getElementById('progressbartable1').style.display = 'none'; 
}
function loadscreenfield(url){
	var screencode = document.screenFieldForm.screenCode.value.trim();
//	alert(screenName);

   //convert the url to a string
//   document.getElementById("loading").style.visibility = "visible";

	//alert(screencode);
	url=url+"&screencode="+screencode;
	


  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}

function processStateChange() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    //	document.getElementById("loading").style.visibility = "hidden";	
  	}
}
function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}
function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements ffffffffff
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="fields")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
function enableChkBox(id){
	//alert(" : from :"+from+""+id);


	var id=id;
var check1="isvisible_"+id;
if(document.screenFieldForm[check1].checked){
    document.screenFieldForm['isMandatory_'+id].disabled=false;
}else{
document.screenFieldForm['isMandatory_'+id].disabled=true;

}
	
	

}
</script>

<script type="text/javascript">
$(document).ready(function() {


	$('#table-1').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-1 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });


	$('#table-2').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-2 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });


	$('#table-3').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-3 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });


	$('#table-4').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-4 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });

	$('#table-5').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-5 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });


	$('#table-6').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-6 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });



	$('#table-7').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";//"Row dropped was "+row.id+". New order: ";
            for (var i=1; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			//alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("<%=request.getContextPath()%>/jsp/screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        },
			dragHandle: "dragHandle"

	}); 

	$("#table-7 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });


	
    
});

</script>




<body class="yui-skin-sam" onLoad="init()" >

<%if(screenfieldsupdated != null && screenfieldsupdated.equals("yes")){ %>

<font color="green">
&nbsp;<img src='jsp/images/greentick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;
<b><%=Constant.getResourceStringValue("admin.screenfield.updateMsg",user1.getLocale())%></b></font><br>
<%} %>


<b><%=Constant.getResourceStringValue("admin.screenfield.applicant.screen.elements",user1.getLocale())%></b>
<html:form action="/screenfield.do?method=savescreenfields">

<table>
	<tr>
		<td width="150"></td>
		<td width="200"><b><%=Constant.getResourceStringValue("admin.screenfield.Field_Name",user1.getLocale())%></b></td>
		<td width="200" align="center"><b><%=Constant.getResourceStringValue("admin.screenfield.isVisible",user1.getLocale())%></b> </td>
		<td width="200" align="center"><b><%=Constant.getResourceStringValue("admin.screenfield.isMandatory",user1.getLocale())%></b> </td>
	</tr>
</table>

<%
// Group wise arragne
List fieldList = screenForm.getScreenfieldsList();

List PERSONAL_INFO_List = new ArrayList();
List WORK_PREFERENCE_List = new ArrayList();
List EDUCATION_List = new ArrayList();
List PROFILE_List = new ArrayList();
List OTHER_List = new ArrayList();
List SOCIAL_List = new ArrayList();
List BACKGROUND_List = new ArrayList();


for(int i = 0; i < screenForm.getScreenfieldsList().size(); i++){ 
	ScreenFields screenFieldsn = (ScreenFields)fieldList.get(i);
	if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_PERSONAL_INFO_GROUP)){
	PERSONAL_INFO_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_WORK_PREFERENCE_GROUP)){
		WORK_PREFERENCE_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_EDUCATION_GROUP)){
		EDUCATION_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_PROFILE_GROUP)){
		PROFILE_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_OTHER_GROUP)){
		OTHER_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_SOCIAL_GROUP)){
		SOCIAL_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_BACKGROUND_GROUP)){
		BACKGROUND_List.add(screenFieldsn);
	}
}
Collections.sort(PERSONAL_INFO_List);
Collections.sort(WORK_PREFERENCE_List);
Collections.sort(EDUCATION_List);
Collections.sort(PROFILE_List);
Collections.sort(OTHER_List);
Collections.sort(SOCIAL_List);
Collections.sort(BACKGROUND_List);


%>


  <!-- for group wise PERSONAL_INFO -->
<table id="table-1">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Personal Informations</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	ScreenFields screenFields= null;
	
	
	for(int i = 0; i < PERSONAL_INFO_List.size(); i++){ 
		screenFields = (ScreenFields)PERSONAL_INFO_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise PERSONAL_INFO -->
   


	<tr bgcolor="<%=bgcolor%>" id="1.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>
<table>

<!-- for group wise OTHERS -->
<table id="table-2">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Others</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	
	
	
	for(int i = 0; i < OTHER_List.size(); i++){ 
		screenFields = (ScreenFields)OTHER_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise OTHERS -->
   


	<tr bgcolor="<%=bgcolor%>" id="2.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>



 <!-- for group wise WORK_PREFERENCE -->
<table id="table-3">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Work preference</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	
	
	
	for(int i = 0; i < WORK_PREFERENCE_List.size(); i++){ 
		screenFields = (ScreenFields)WORK_PREFERENCE_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise WORK_PREFERENCE -->
   


	<tr bgcolor="<%=bgcolor%>" id="3.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>
<!-- for group wise EDUCATION -->
<table id="table-4">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Education</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	
	
	
	for(int i = 0; i < EDUCATION_List.size(); i++){ 
		screenFields = (ScreenFields)EDUCATION_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise EDUCATION -->
   


	<tr bgcolor="<%=bgcolor%>" id="4.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>


<!-- for group wise PROFILE -->
<table id="table-5">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Profile</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	
	
	
	for(int i = 0; i < PROFILE_List.size(); i++){ 
		screenFields = (ScreenFields)PROFILE_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise PROFILE -->
   


	<tr bgcolor="<%=bgcolor%>" id="5.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>


<!-- for group wise Social -->
<table id="table-6">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Social</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	
	
	
	for(int i = 0; i < SOCIAL_List.size(); i++){ 
		screenFields = (ScreenFields)SOCIAL_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise PROFILE -->
   


	<tr bgcolor="<%=bgcolor%>" id="6.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>


<!-- for group wise Background -->
<table id="table-7">
<tr bgcolor="gray" id="1.0" class="nodrop nodrag">
<td><b>Background</b></td><td></td><td></td><td></td><td></td>
</tr>
<%
	
	
	
	for(int i = 0; i < BACKGROUND_List.size(); i++){ 
		screenFields = (ScreenFields)BACKGROUND_List.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#e7e9fe";
	%>
	 
   <!-- for group wise PROFILE -->
   


	<tr bgcolor="<%=bgcolor%>" id="7.<%=screenFields.getScreenfieldId()%>" >
	<%
	String resourcestr="aquisition.applicant."+screenFields.getFieldcode();
	%>
	    <td class="dragHandle" width="150"></td>	
		<td width="200"><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>
			
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<b>Y</b>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<b>N</b>
			<%} %>
		<%}else{ %>
			<%if(screenFields.getIsvisible().equals("Y")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>" checked>
			<%}else if(screenFields.getIsvisible().equals("N")){ %>
			<input type="checkbox" id="isvisible_<%=screenFields.getScreenfieldId()%>" onClick="enableChkBox('<%=screenFields.getScreenfieldId()%>');" name="isvisible_<%=screenFields.getScreenfieldId()%>">
			<%} %>
		<%}%>	
		</td>
		<td width="200" align="center">
		<%if(screenFields.getIssystem().equals("Y")){ %>

				<%if(screenFields.getIsMandatory().equals("Y")){ %>
				<b>Y</b>
				<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<b>N</b>
				<%} %>
		<%}else{ %>
			<%if(screenFields.getIsMandatory().equals("Y")){ %>
			<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>"  checked>
			<%}else if(screenFields.getIsMandatory().equals("N")){ %>
				<%if(screenFields.getIsvisible().equals("Y")){ %>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" >
				<%}else{%>
					<input type="checkbox" id="isMandatory_<%=screenFields.getScreenfieldId()%>" name="isMandatory_<%=screenFields.getScreenfieldId()%>" disabled>
				<%}%>
			<%}%>
		<%} %>
		</td>
	</tr>
	<%} %>
</table>





<table width="100%">
	<tr>
		
			<td>
						<span id='progressbartable1'>
						<img src="jsp/images/indicator.gif" height="20"  width="40"/>
						</span>
			<% if(screenForm.getScreenfieldsList().size() > 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="savedata()" class="button">
			<%} %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
	</tr>

</table>


</html:form>

<div id="AjaxResult" style="visibility=hidden">

</div>



</body>

	
	
