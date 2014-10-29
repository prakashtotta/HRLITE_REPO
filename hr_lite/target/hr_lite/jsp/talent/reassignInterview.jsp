	<%@ include file="../common/include.jsp" %>
	<%@ include file="../common/autocomplete.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="scheduleInterviewForm" type="com.form.ScheduleInterviewForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>

	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
int eventtype = sform.getEventType();
%>
	


<style>
span1{color:#ff0000;}
</style>

	<style type="text/css">
	#myAutoComplete {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}
	#myAutoComplete1 {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}

  
	</style>

    
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<script type="text/javascript">
var ccids="";
var ccnames="";


String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
function strStartsWith(str, prefix) { 
	return str.indexOf(prefix) === 0;
	}

$(function() {

	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#reviewername").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    
		  
		  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.scheduleInterviewForm.reviewername.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.scheduleInterviewForm.reviewerid.value=idval.substring(1,idval.length);
			 document.scheduleInterviewForm.isgroup.value="Y";
			 document.scheduleInterviewForm.reviewernamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.scheduleInterviewForm.reviewerid.value=item.data;
		 document.scheduleInterviewForm.isgroup.value="N";
		 document.scheduleInterviewForm.reviewername.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.scheduleInterviewForm.reviewernamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		}
		});

		$("#watchlistnamesnew").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 ccids = item.data + ","+ ccids;
		 ccnames =  item.value + ","+ ccnames;
		document.scheduleInterviewForm.watchlistids.value=ccids;
		document.scheduleInterviewForm.watchlistnames.value=ccnames;

		
		var tempurl = '<a href="#" onclick=window.open('+"user.do?method=edituser&readPreview=2&userId="+item.data+")>"+item.value+"</a>"

		document.getElementById("watchlist").innerHTML = document.getElementById("watchlist").innerHTML + " , " +tempurl;
		document.scheduleInterviewForm.watchlistnamesnew.value="";
		
		//alert(item.data);
		}
		});



});

	</script>



	<script language="javascript">

	function closewindow(){
		parent.parent.GB_hide();
	  
	}
function validateUser(){
	//alert(document.scheduleInterviewForm.reviewerid.value);
	document.scheduleInterviewForm.reviewername.value=document.scheduleInterviewForm.reviewernamehidden.value;
}
	function opensearchassignedto(){
      var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=intschreassign");
  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=assignedtoselector&boxnumber=intschreassign", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchwatchlist(){
       var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector");
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 // window.open("user.do?method=watchlistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   } 
		}

	function savedata(){

		var alertstr = "";
   	var interviewer = document.scheduleInterviewForm.reviewerid.value;
	var commentsa = document.scheduleInterviewForm.notes.value;
		
	var showalert=false;

	

	if(interviewer == "" || interviewer == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Assignee_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	
	 if(commentsa == "" || commentsa == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Comment_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		
		  document.scheduleInterviewForm.action = "scheduleInterview.do?method=interviwreassignsubmit&applicantId=<bean:write name="scheduleInterviewForm" property="applicantId"/>&reviewerid="+interviewer+"&eventtype=<%=eventtype%>";
	   document.scheduleInterviewForm.submit();
	   //window.top.hidePopWin();
	   
		}

		
function opensearch(){
  window.open("evtmpl.do?method=selectevtemplates", "SearchEvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}



	</script>

<%
String interviwerreassigned = (String)request.getAttribute("interviwerreassigned");
	
if(interviwerreassigned != null && interviwerreassigned.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp; <font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Review_re_assigned_to",user1.getLocale())%>&nbsp;&nbsp;<b><%=sform.getCurrentowner()%></font></b></td>
				<td> <!-- <a href="#" onclick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>
<br>
<%}else {%>

<body class="yui-skin-sam">
	<html:form action="/scheduleInterview.do?method=interviwreassignsubmit"  enctype="multipart/form-data">


		<font color = red ><html:errors /> </font>
		
		<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.reassign_review.note",user1.getLocale())%> </font>
	
			<br>
			<table border="0" width="100%" class="div">

			<tr>

  
				<td><%=Constant.getResourceStringValue("Requisition.assignto",user1.getLocale())%><font color="red">*</font></td>

				<td>
				<input type="hidden" name="reviewernamehidden">
                 <input type="text" id="reviewername" name="reviewername" autocomplete="off"   onblur="validateUser()">


				<span id="assignedto"></span>
				<a href="#" onClick="opensearchassignedto();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
				<input type="hidden" name="reviewerid" />
				<input type="hidden" name="isgroup" />
				
				<br>
				<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>

				</td>
			</tr>

			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%><font color="red">*</font></td>
				<td><html:textarea property="notes" cols="60" rows="4"/></td>
			</tr>


			<tr>
				<td colspan="2">
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.reassign",user1.getLocale())%>" onClick="savedata()" class="button">
			
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>

		</table>
	

	</html:form>


	<%}%>





	</body>