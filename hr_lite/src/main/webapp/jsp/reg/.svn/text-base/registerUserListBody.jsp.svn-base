<%@ include file="../common/include.jsp" %>

<bean:define id="eform" name="userRegForm" type="com.form.UserRegForm" />

 <%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


List userRegDataList = eform.getUserRegDataList();
String isupdated = (String)request.getAttribute("updated");


%>

<br>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">

<script language="javascript">


function updatedata(userRegdataid){
	//alert(weid+" > "+user+" > "+status);
		var tb_group=document.userRegForm.elements["months_"+userRegdataid];
		var months=tb_group.value
		//alert(tb_group.value);
		var pkg_group=document.userRegForm.elements["packages_"+userRegdataid];
		var packagetaken=pkg_group.value;

        var ipaddress=document.userRegForm.elements["ipaddress_"+userRegdataid];
		var ipaddressv=ipaddress.value;
		


	var url="reguser.do?method=updateRegUserData&userRegdataid="+userRegdataid+"&months="+months+"&packagetaken="+packagetaken+"&ipaddress="+ipaddressv;
	//alert(url);
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

    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    	
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
				  
		    	  if(name="registeruser")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
function init(){
	//document.userRegForm.elements["packages_<%=eform.getUserRegId()%>"].value="<%=eform.getPackagetaken() %> ";
}
</script>
</head>
<body class="yui-skin-sam" onload="init();">

<html:form action="/reguser.do?method=updateRegUserData">
<span id="registeruser">
<table border="0" width="100%" cellspacing="4" cellpadding="4">
<tr bgcolor="#f3f3f3">
	<td width=170>
	
	<b><%=Constant.getResourceStringValue("admin.reguser.name",user1.getLocale())%></b></td>
	<td width=180 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.email",user1.getLocale())%></b></td>
	<td width=120 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.created_date",user1.getLocale())%></b></td>
	<td width=120 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.updated_date",user1.getLocale())%></b></td>
	<td width=150 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.package_taken",user1.getLocale())%></b></td>
	<td width=100 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.expire_date",user1.getLocale())%></b></td>
	<td width=100 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.months",user1.getLocale())%></b></td>
	<td width=100 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.ipaddress",user1.getLocale())%></b></td>
	<td width=150 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.package",user1.getLocale())%></b></td>
	<td width=130 align="left"><b><%=Constant.getResourceStringValue("admin.reguser.update",user1.getLocale())%></b></td>

</tr>


<%
if(userRegDataList != null){

	for(int i=0;i<userRegDataList.size();i++){

		UserRegData userRegData = (UserRegData)userRegDataList.get(i);
    String bgcolor = "";
    bgcolor ="#f3f3f3";
	//if(i%2 == 1)bgcolor ="#B2D2FF";

%>



<tr bgcolor=<%=bgcolor%>>
	<%
	String fullname="";
	if(!StringUtils.isNullOrEmpty(userRegData.getFirstName())){
		fullname=userRegData.getFirstName()+" "+userRegData.getLastName();
	}
	%>
	<td >
	
	<%=fullname%></td>
	<td  align="left"><%=userRegData.getEmailId()==null?"":userRegData.getEmailId() %> <%=EncryptDecrypt.decrypt(userRegData.getPassword())%></td>

	<td  align="left">
	<%
	String createddate="";
	createddate=DateUtil.convertDateToStringDate(userRegData.getCreatedDate(),datepattern);
	%>
	<%=createddate %></td>
	<td  align="left">
	
		<%
	String updateddate="";
		updateddate=DateUtil.convertDateToStringDate(userRegData.getUpdatedDate(),datepattern);
	%>
	<%=updateddate %></td>

	<td  align="left"><%=userRegData.getPackagetaken()==null?"":userRegData.getPackagetaken() %></td>
	<td  align="left">

		<%
	String expireddate="";
		expireddate=DateUtil.convertDateToStringDate(userRegData.getExpireDate(),datepattern);
	%>
	<%=expireddate %></td>
	
	<td  align="left">
	<%
	String monthsbetween="";
	Date currentdt = new Date();
	Date expdt=userRegData.getExpireDate();
	%>

	
	<input type="text" name="months_<%=userRegData.getUserRegId()%>" size="10" value="<%=monthsbetween%>"/></td>
	<td  align="left">
	<input type="text" name="ipaddress_<%=userRegData.getUserRegId()%>" size="10" value="<%=userRegData.getIpaddress()%>"/>
	</td>
	<td  align="left">
	
	<select name="packages_<%=userRegData.getUserRegId()%>" >
		<option value="FREE" <%=userRegData.getPackagetaken().equals(Common.PACKAGE_FREE)?"selected":"" %> ><%=Common.PACKAGE_FREE%></option>
		<option value="BASIC" <%=userRegData.getPackagetaken().equals(Common.PACKAGE_BASIC)?"selected":"" %> ><%=Common.PACKAGE_BASIC %></option>
		<option value="ADVANCE" <%=userRegData.getPackagetaken().equals(Common.PACKAGE_ADVANCE)?"selected":"" %> ><%=Common.PACKAGE_ADVANCE %></option>
		<option value="ENTERPRIZE" <%=userRegData.getPackagetaken().equals(Common.PACKAGE_ENTERPRIZE)?"selected":"" %> ><%=Common.PACKAGE_ENTERPRIZE %></option>
		<option value="ENTERPRIZE-MAX" <%=userRegData.getPackagetaken().equals(Common.PACKAGE_ENTERPRIZE_MAX)?"selected":"" %> ><%=Common.PACKAGE_ENTERPRIZE_MAX %></option>
	</select>
	
	</td>
	<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata(<%=userRegData.getUserRegId() %>)">


	<%if(! StringUtils.isNullOrEmpty(isupdated) && isupdated.equals("yes") && userRegData.getUserRegId() == eform.getUserRegId()){ %><font color="green">updated </font><%}%>

	</td>
	
</tr>
<%}
}
%>
</table>

<br>

</span>


</html:form>
</body>


