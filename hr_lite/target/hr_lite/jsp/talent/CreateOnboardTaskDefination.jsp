<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.onboard.*" %>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="onBoardingTaskDefiForm" name="onBoardingTaskDefiForm" type="com.form.OnBoardingTaskDefiForm" />

<style>
span1{color:#ff0000;}
</style>

<script language="javascript">
function addAttributes(){
    var alertstr = "";
	var taskdefid ="<%=onBoardingTaskDefiForm.getTaskdefid()%>";
	if(taskdefid=="0"){
		alert("First save On Boarding task defination");
		return false;
	}


	var Attribute_name = document.onBoardingTaskDefiForm.atrributes.value;
	var Is_mandatory = document.onBoardingTaskDefiForm.isMandatory.value;
	var showalert=false;

if(Attribute_name == "" || Attribute_name == null){
 	alertstr = alertstr + "Enter the attribute name<BR>";
	showalert = true;
	}



 if (showalert){
 	alert(alertstr);
    return false;
      }


//var url="OnBoardingTaskDefi.do?method=saveAttributedetails&taskdefiId=<%=onBoardingTaskDefiForm.getTaskdefid()%>&Attribute_name="+Attribute_name+"&Is_mandatory="+Is_mandatory+";
}

function deleteAttributes(id){

	var url="OnBoardingTaskDefi.do?method=deleteAttribute&Attributeid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveAttributes()",200);


}
  	
function opensearchassignedtohiring(){
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=addprimaryowner");
	  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

	 // window.open("user.do?method=assignedtoselector&boxnumber=addhighiringmanagerreq", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
	}

function opensearchwatchlist(){
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector");
window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

//window.open("user.do?method=watchlistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function validateUserhm(){

	document.onBoardingTaskDefiForm.primaryOwnerName.value=document.onBoardingTaskDefiForm.primaryownernamehidden.value

}

function deletedatasure(){
	  var doyou = confirm("Are you sure would you like to delete?");
	  if (doyou == true){
		  //self.parent.location.reload();
			// parent.parent.GB_hide();
			deletedata();
	   } 
	}
function discard(){
	  var doyou = confirm("Are you sure would you like to discard this changes?");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function discard1(){
	 //self.parent.location.reload();
	 parent.parent.GB_hide();
	}

function savedata(){
	var alertstr = "";
	var showalert=false;
    var numbers=/^[0-9]+$/;

	 var taskdefname=document.onBoardingTaskDefiForm.taskName.value.trim();

		if(taskdefname == "" || taskdefname == null){
     	alertstr = alertstr + "On Board task Defination Name is Mandatory<br>";
		showalert = true;
		}
		var ownername=document.onBoardingTaskDefiForm.primaryownernamehidden.value;
		if(ownername == null || ownername ==""){
     	alertstr = alertstr + "Primary owner name is Mandatory<br>";
		showalert = true;
		}	
       	var days=document.onBoardingTaskDefiForm.noofdays.value.trim();
        if(numbers.test(days)==false){
        alertstr = alertstr + "Please enter days in number<br>";
        showalert = true;
        }


      if (showalert){
     	alert(alertstr);
        return false;
          }

	document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=saveOnBoardTaskDefi&readPreview=2";
	document.onBoardingTaskDefiForm.submit();
	//self.parent.location.reload();
	 
	}
function updatedata(){
     var alertstr = "";
	var showalert=false;
    var numbers=/^[0-9]+$/;

	 var taskdefname=document.onBoardingTaskDefiForm.taskName.value.trim();

		if(taskdefname == "" || taskdefname == null){
     	alertstr = alertstr + "On Board task Defination Name is Mandatory<br>";
		showalert = true;
		//document.getElementById('taskName').focus();
		
		
		}
		document.onBoardingTaskDefiForm.taskName.focus();
		var ownername=document.onBoardingTaskDefiForm.primaryOwnerId.value;
		if(ownername == 0){
     	alertstr = alertstr + "Primary owner name is Mandatory<br>";
		//document.getElementById('primaryownernamehidden').focus();
		showalert = true;
		}	
       	var days=document.onBoardingTaskDefiForm.noofdays.value.trim();
        if(numbers.test(days)==false){
        alertstr = alertstr + "Please enter days in number<br>";
        document.getElementById('noofdays').focus();
		//document.onBoardingTaskDefiForm.noofdays.focus();
		showalert = true;
        }


      if (showalert){
     	alert(alertstr);
        return false;
          }

	document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=updateOnBoardingTaskDefi&readPreview=2&id="+'<bean:write name="onBoardingTaskDefiForm" property="taskdefid"/>';
	document.onBoardingTaskDefiForm.submit();
	//self.parent.location.reload();
	 
	}

function deletedata(){
	document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=deleteOnBoardingTaskDefi&readPreview=1&id="+'<bean:write name="onBoardingTaskDefiForm" property="taskdefid"/>';
    document.onBoardingTaskDefiForm.submit();
	
}
function retrieveAttributes() {
    document.getElementById("loading3").style.visibility = "visible";
	//	document.onBoardingTaskDefiForm.attribute.value="";
	var url="OnBoardingTaskDefi.do?method=getAttributedetails&attid=<%=onBoardingTaskDefiForm.getTaskdefid()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}
function processOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlAttribute(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading3").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtmlAttribute(newTextElements){
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
				  
		    	  if(name="attributeDetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function addElement() {
  var ni = document.getElementById('myDiv');
  var numi = document.getElementById('theValue');
  var num = (document.getElementById('theValue').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  var spanstart = '<span id="compspan_'+num+'">';
  var attributes1 = '<input type="text" size = "50" maxlength= "100" name="attributes_'+num+'"/>';
  var attribteid1 = '<input type="hidden"    name="taskattid_'+num+'"/>';
  var mandatory1 = '<input type="checkbox"  checked   name="mandatory_'+num+'"/>';
  var spanend = '</span>'; 
  var spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
  newdiv.innerHTML = ''+spanstart+''+attributes1+''+attribteid1+''+spaces+''+spaces+''+mandatory1+''+spanend+''+spaces+''+spaces+'<a href=\'#\' onclick=\'removeElement('+divIdName+')\'>remove</a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}

function removeElement(divNum) {
var d = document.getElementById('myDiv');
d.removeChild(divNum);
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
		 
		document.onBoardingTaskDefiForm.primaryOwnerId.value=+item.data;
		document.onBoardingTaskDefiForm.primaryownernamehidden.value=item.value;
		//alert(item.data);
		//alert(item.value);
		}
		});

     

});










	</script>


<%
String saveOnBoardtaskDefination = (String)request.getAttribute("saveOnBoardtaskDefination");
	
if(saveOnBoardtaskDefination != null && saveOnBoardtaskDefination.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white">Data saved successfully</font></td>
			<td> <a href="#" onclick="discard1()"><font color="white">Close Window</font></a></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateOnBoardtaskDefination = (String)request.getAttribute("updateOnBoardtaskDefination");
	
if(updateOnBoardtaskDefination != null && updateOnBoardtaskDefination.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white">Data updated successfully</font></td>
			<td> <a href="#" onclick="discard1()"><font color="white">Close window</font></a></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteOnBoardtaskDefination = (String)request.getAttribute("deleteOnBoardtaskDefination");
	
if(deleteOnBoardtaskDefination != null && deleteOnBoardtaskDefination.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white">Data deleted successfully</font></td>
			<td> <a href="#" onclick="discard1()"><font color="white">Close window</font></a></td>
		</tr>
		
	</table>
</div>

<%}else{%>


<html:form action="/OnBoardingTaskDefi.do?method=saveOnBoardTaskDefi">

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	
	<tr>
			<td><b>On Board task defination</b></td>
			<td></td>
		</tr>
		
		<tr>
			<td>On Board task Defination Name<span1>*<span1></td>
			<td><html:text property="taskName" maxlength="500"/></td>
		</tr>
		
		
		
		<tr>
			<td>Description</td>
			<td><html:textarea property="taskDesc" cols="40" rows="5" /></td>
		</tr>
		<tr>
			<td>Primary owner name<span1>*<span1></td>
		<td>	<input type="hidden" name="primaryownernamehidden">
                
                 <input type="text" id="primaryOwnerName" name="primaryOwnName" 
				 value="<%=(onBoardingTaskDefiForm.getPrimaryOwnername()==null)?"":onBoardingTaskDefiForm.getPrimaryOwnername()%>" onblur="validateUserhm()">
				
				<span id="assignedto"></span>
<a href="#" onClick="opensearchassignedtohiring()"><img src="jsp/images/selector.gif" border="0"/></a>
<%
String primaryownerId = String.valueOf(onBoardingTaskDefiForm.getPrimaryOwnerId());
%>
<html:hidden  property="primaryOwnerId" value="<%=primaryownerId%>"/>



	</td>
	</tr>
	 <tr>
			<td>Event type<span1>*<span1> </td>
			<td>
			<select name="eventType">
			<% if(!StringUtils.isNullOrEmpty(onBoardingTaskDefiForm.getAppliedcri()) && onBoardingTaskDefiForm.getAppliedcri().equals("before")){%>
  			<option value="before" selected="yes" >Before</option>
  			<%}else {%>
			<option value="before">Before</option>
			 <%}%>
  		  	<% if(!StringUtils.isNullOrEmpty(onBoardingTaskDefiForm.getAppliedcri()) && onBoardingTaskDefiForm.getAppliedcri().equals("after")){%>
 			 <option value="after" selected="yes" >After</option>
  			<%}else {%>
			<option value="after">After</option>
		  <%}%>
 
		</select>        
		<html:text property="noofdays" maxlength="2"/>Days
		</td>
		
		</tr> 
		<tr><td></td><td></td></tr>

<tr>
<td>
<%
List attributeList = onBoardingTaskDefiForm.getAttributeList();
%>	
<%if(attributeList != null){ %>
	<input type="hidden" value="<%=attributeList.size()%>" id="theValue" />
	<%}else{ %>
	<input type="hidden" value="" id="theValue" />
	<%} %>
<a href="javascript:;" onclick="addElement();">Add Attributes</a>

 
</td>
<td></td>
</tr>
<tr><td><th>Attributes name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Is mandatory</th></td><td></td></tr>
<tr>
<td>

</td>
<td>
	<div id="myDiv">
<%
int k2=1;
if(attributeList != null){
for(int i=0;i<attributeList.size();i++){
	OnBoardingTaskAttributes jcomp = (OnBoardingTaskAttributes)attributeList.get(i);
     String tdiv = "my"+k2+"Div";
	 String tspancomp = "compspan_"+k2;
	 String tcompname = "attributes_"+k2;
      String tcompmandatory = "mandatory_"+k2;
	  String checkedmancomp = (jcomp.getIsMandatory().equals("Y"))?"checked":"";
	  //String spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
	//String tcompimportance = "accimportance_"+k2;
	String tempdivappcomp = "<div id='"+tdiv+"'"+">"+
		"<span id='"+tspancomp+"'"+">"+
		"<input type=\"text\" readonly size = \"50\" maxlength= \"100\" value="+jcomp.getAttribute()+" name='"+tcompname+"'"+"/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		"<input type=\"checkbox\"" +checkedmancomp+ " name='"+tcompmandatory+"'"+"/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
        
       	"<a href=\'#\' onclick=\'removeElement("+tdiv+")\'>remove</a>"+
	"</div>";
	k2++;

%>

<%=tempdivappcomp%>

<%}}%>

</div>
</td>
</tr>

<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
	<tr><td><% if(onBoardingTaskDefiForm.getReadPreview().equals("2")){%>
	        <input type="button" name="login" value="Update" onClick="updatedata()" class="button">
			<input type="button" name="login" value="Delete" onClick="deletedatasure()" class="button">
			<input type="button" name="login" value="Cancel" onClick="discard()" class="button">
	      <%} else {%>
			<input type="button" name="login" value="Save" onClick="savedata()" class="button">
			<input type="button" name="login" value="Cancel" onClick="discard()" class="button"></td>
			
			
		<%}%>
			
			<td></td>
		</tr>

		</table>
</div>

</html:form>

	<%}%>	
	
