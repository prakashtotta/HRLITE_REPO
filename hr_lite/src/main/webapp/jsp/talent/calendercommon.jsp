<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>

<%
String 	reviewerid = (String)request.getAttribute("reviewerid");
String 	depttempid = (String)request.getAttribute("depttempid");

String 	reviewername = (String)request.getAttribute("reviewername");
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
%>

<bean:define id="lovForm" name="lovForm" type="com.form.LovForm" />

<script type="text/javascript">

function setdeptvalue(sel){
	var dep = sel.options[sel.selectedIndex].value; 
	document.lovForm.depttempid.value=dep;
	document.getElementById("message").innerHTML="Organization and department is set , click on date to get all interviews";
}
function setValues(){
	if(document.lovForm.depttempid.value == 0 && document.lovForm.orgId.value ==0){
		alert("<%=Constant.getResourceStringValue("aquisition.interview.list.user.org.alert",user1.getLocale())%>");
		return false;
	}
	   document.lovForm.action = "lov.do?method=callistsubmit&depttempid="+document.lovForm.depttempid.value;
   document.lovForm.submit();
}

function retrieveURLOrg(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	document.lovForm.reviewerid.value=0;
	document.lovForm.reviewername.value="";

	url=url+"&orgId="+document.lovForm.orgId.value;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}

	document.getElementById("message").innerHTML="Organization is set , click on date to get all interviews";
}
function processStateChangeOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpanOrg(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlOrg(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function splitTextIntoSpanOrg(textToSplit){
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

function replaceExistingWithNewHtmlOrg(newTextElements){
	//loop through newTextElements
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
				  
		    	  if(name="departments")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
	
}


function validateUser(){
	if(document.lovForm.reviewername.value == ""){
		document.lovForm.reviewerid.value="0";
	}
	
	//document.lovForm.reviewername.value=document.lovForm.reviewernamehidden.value;
}


$(function() {

	$("#reviewername").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		  
		document.lovForm.reviewerid.value=item.data;
		document.lovForm.reviewernamehidden.value=item.value;
		//alert(item.data);
		}
		});

});

	</script>

<script type="text/javascript">


	YAHOO.namespace("example.calendar");

	YAHOO.example.calendar.init = function() {
		var eLog = YAHOO.util.Dom.get("evtentries");
		var eCount = 1;

 var m_names = new Array("January", "February", "March", 
        "April",
		"May",
		"June", 
		"July", 
		"August", 
		"September", 
        "October",
		"November", 
		"December");


var d = new Date();
var curr_date = d.getDate();
var curr_month = d.getMonth();
var curr_year = d.getFullYear();
var newdate = m_names[curr_month] + " "+curr_date
+ "," +" "+ curr_year;

       logEvent(newdate);
		function logEvent(msg) {
			
			//eLog.innerHTML = '<pre class="entry"><strong>' + eCount + ').</strong> ' + msg + '</pre>' + eLog.innerHTML;

			var tem = "<h3>Retrieving data ... " +
            "</h3>" +
            "<img src='jsp/images/busybar_1.gif' " +
            "alt='Please wait...'>";
			  eLog.innerHTML=tem; 
                          conn = YAHOO.util.Connect.asyncRequest("POST", "jsp/talent/calInterview.jsp?reviewerid="+document.lovForm.reviewerid.value+"&deptartmentid="+document.lovForm.depttempid.value+"&orgid="+document.lovForm.orgId.value+"&date="+msg ,
							  {
                success:function(o) {
                    var r = YAHOO.lang.JSON.parse(o.responseText);
					
                    if (r.replyCode == 201) {
                        //callback(true, r.data);
						
						eLog.innerHTML = r.data;// '<pre class="entry">'+ r.data + '</pre>';
                    } else {
                        alert(r.replyText);
                        callback();
                    }
                },
                failure:function(o) {
                      callback();
                },
                scope:this
            }
						  
						  );
						 
			eCount++;
		}

		function dateToLocaleString(dt, cal) {
                	var wStr = cal.cfg.getProperty("WEEKDAYS_LONG")[dt.getDay()];
                	var dStr = dt.getDate();
                	var mStr = cal.cfg.getProperty("MONTHS_LONG")[dt.getMonth()];
               	 	var yStr = dt.getFullYear();
                	return (mStr + " " + dStr  +","+ " " + yStr);
		}

		function mySelectHandler(type,args,obj) {
			var selected = args[0];
			var selDate = this.toDate(selected[0]);
			 
			logEvent(dateToLocaleString(selDate, this));
		};

		function myDeselectHandler(type, args, obj) {
			var deselected = args[0];
			var deselDate = this.toDate(deselected[0]);

			//logEvent("DESELECTED: " + dateToLocaleString(deselDate, this));
		};

		YAHOO.example.calendar.cal1 = new YAHOO.widget.Calendar("cal1","cal1Container");

		YAHOO.example.calendar.cal1.selectEvent.subscribe(mySelectHandler, YAHOO.example.calendar.cal1, true);
		YAHOO.example.calendar.cal1.deselectEvent.subscribe(myDeselectHandler, YAHOO.example.calendar.cal1, true);

		YAHOO.example.calendar.cal1.render();
	}

	YAHOO.util.Event.onDOMReady(YAHOO.example.calendar.init);
</script>
<div id="message"></div>
<% if(PermissionChecker.isPermissionApplied(Common.ALL_CALENDAR_ACEESS, user)){%>
<br> 
 <html:form  action="lov.do?method=callistSubmit">
<table border="0">
<tr>

 <td>
 <input type="hidden" name="reviewerid" value="<%=reviewerid%>"/>
 <input type="hidden" name="depttempid"/>
				<%=Constant.getResourceStringValue("hr.user",user1.getLocale())%>:<input type="hidden" name="reviewernamehidden" value="<%=reviewername%>">
                 <input type="text" id="reviewername" name="reviewername" value="<%=reviewername%>" autocomplete="off" onblur="validateUser()">
</td>
<td>
				<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>:
			
			<html:select property="orgId" onchange="retrieveURLOrg('lov.do?method=loadDepartmentsWithOutProjectCodeAndSpace');">
			<option value=""></option>
			<bean:define name="lovForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</span>
</td>
<td>
<%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>:
			<span id="departments">
			<html:select property="deptId" onchange="setdeptvalue(this);">
			<option value=""></option>
			<bean:define name="lovForm" property="departmentList" id="departmentList" />
            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
</td>
<td>
				 
</td>

</tr>

</table>
</html:form>
<%}else{%>
<html:form  action="lov.do?method=callistSubmit">
<input type="hidden" name="orgId" value="<%=user.getOrganization().getOrgId()%>"/>
<input type="hidden" name="depttempid" value="0"/>
<input type="hidden" name="reviewerid" value="<%=user.getUserId()%>"/>

</html:form>
<%}%>
	<table>
	<tr>
	<td valign="top">
<div id="cal1Container"></div>
</td>
<td>
<div id="caleventlog" class="eventlog">
	<div class="hd"><%=Constant.getResourceStringValue("task.interview",user.getLocale())%> - <%=reviewername%> </div>

	<div id="evtentries" class="bd">


	</div>
	</div>
	</div>
</td>

	

</tr>
</table>





