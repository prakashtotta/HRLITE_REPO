<%@ include file="../common/include.jsp" %>

<!--<script language="JavaScript">
VIH_BackColor = "palegreen";
VIH_ForeColor = "navy";
VIH_FontPix = "16";
VIH_DisplayFormat = "You are visiting from:<br>IP Address: %%IP%%<br>Host: %%HOST%%";
VIH_DisplayOnPage = "yes";
</script>
<script language="JavaScript" src="http://scripts.hashemian.com/js/visitorIPHOST.js.php"></script>-->

<bean:define id="userRegForm" name="userRegForm" type="com.form.UserRegForm" />

<%
String alreadyexist = (String)request.getAttribute("alreadyexist");
%>

<script language="javascript">


 
function closewindow(){
	 	   parent.parent.GB_hide(); 

	  
	}

function closewindow2(){
	//self.parent.location.reload();
	 	      parent.parent.GB_hide(); 

	  
	}	
function closewindow1(){
	 	   self.close();

	  
	}




$(document).ready(function() {$('#savedata').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
   savedata();
	 }); 
}); 



 
function completeajx(){
	 $.unblockUI();
}




function savedata(){
	var fname=document.userRegForm.firstName.value.trim();
	var lname=document.userRegForm.lastName.value.trim();
	var email=document.userRegForm.emailId.value.trim();
	var middleName=document.userRegForm.middleName.value.trim();
	var OrgName = document.userRegForm.orgName.value.trim();
	var password = document.userRegForm.password.value.trim();
	var subdomain = document.userRegForm.subdomain.value.trim();

	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
	var alertstr="";
	var showalert=false;
	var alphabate=/^[a-zA-Z]+$/;
	var numbers=/^[0-9]+$/;


	if(fname == "" || fname == null){
     	alertstr = alertstr + "First name is required.<BR>";
     	showalert = true;
		}
	if(lname == "" || lname == null)
	{
 	alertstr = alertstr+"Last name is required.<BR>";
 	showalert = true;
		}
	if(email == "" || email == null)
	{
 	alertstr = alertstr+"Email id is required.<BR>";
 	showalert = true;
		}
	if(reg.test(email) ==false){
     	alertstr = alertstr + "Email id is not valid.<BR>";
		showalert = true;
		}
	 
if(OrgName == "" || OrgName == null)
	{
 	alertstr = alertstr+"Organization name is required.<BR>";
 	showalert = true;
		}

if(password == "" || password == null)
	{
 	alertstr = alertstr+"Password is required.<BR>";
 	showalert = true;
		}

if(subdomain == "" || subdomain == null || subdomain =="mycompany")
	{
 	alertstr = alertstr+"Job Board Address is required.<BR>";
 	showalert = true;
		}

	

	if (showalert){
     	alert(alertstr);
        return false;
          }

 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

/*dataString = $("#reguserdata").serialize();
$.ajax({
	type: 'POST',
  url: "reguser.do?method=saveUserdata&csrfcode="+document.userRegForm["csrfcode"].value,
data: dataString,
  success: function(data){
  $('#reguserdatasuccess').html(data);
	completeajx();
  }
});*/

	  document.userRegForm.action = "reguser.do?method=saveUserdata&csrfcode="+document.userRegForm["csrfcode"].value;
  document.userRegForm.submit();
	}

function checkemailexist(){
	var email=document.userRegForm.emailId.value.trim();
   

	$.ajax({
		type: 'GET',
	  url: "reguser.do?method=emailidexistcheck&email="+email,
	  success: function(data){
	  $('#emailidexistmessage').html(data);
		
	  }
	});

}

function checksubdomainexist(){
	var subdomain=document.userRegForm.subdomain.value.trim();
   

	$.ajax({
		type: 'GET',
	  url: "reguser.do?method=subdomainexistcheck&subdomain="+subdomain,
	  success: function(data){
	  $('#subdomainexistmessage').html(data);
		
	  }
	});

}



function leave() {
  window.location = "<%=request.getContextPath()%>/login.do?method=login";
}
function resetEmailid(){
	document.userRegForm.emailId.value="";
}
function resetsubdomain(){
	document.userRegForm.subdomain.value="";
}

</script>

<%
if(alreadyexist != null && alreadyexist.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red" size="3">You have already registered with us , contact sales team to get registered again.</font></td>

		</tr>
	
		
	</table>
</div>
<%}%>

<body>
<html:form action="/reguser.do?method=reg" styleId="reguserdata">


<div class="container">

<font color="brown" size="5">Signup </font>

<span id="reguserdatasuccess">
</span>

<table border="0" width="100%" >
	<font color = red ><html:errors /> </font>

<tr>
			<td></td>
			<td>
			
	
			
			</td>
			
		</tr>

		<tr>
			<td valign="top">Create a Job Board Address  :<font color="red">*</font></td>
			<td><html:text property="subdomain" size="30" value="mycompany" onfocus="if (this.value == 'mycompany')this.value = '';" maxlength="450" styleClass="textdynamic" onblur="javascript:checksubdomainexist()" />.hires360.com<br>
			<span id="nono" STYLE="font-size: smaller;color:red">Letters and numbers only. No spaces and no "www".Example: mycompany.hires360.com</span>
			<span id="nono" STYLE="font-size: smaller;">(You can change this later)</span>
			<span id="subdomainexistmessage">
			<input type="hidden" name="issubdomainexist" value="no"/>
			</span>
			</td>
		</tr>

	    <tr>
			<td >First Name:<font color="red">*</font></td>
			<td><html:text property="firstName" size="40" maxlength="200" styleClass="text"/></td>
		</tr>
		<tr>
			<td >Middle Name:</td>
			<td><html:text property="middleName" size="40" maxlength="200" styleClass="text"/></td>
		</tr>
		<tr>
			<td >Last Name:<font color="red">*</font></td>
			<td><html:text property="lastName" size="40" maxlength="500" styleClass="text"/></td>
		</tr>
		<tr>
			<td >Email id ( user name ):<font color="red">*</font></td>
			<td><html:text property="emailId" size="40" maxlength="500" styleClass="text" onblur="javascript:checkemailexist()" />
			<span id="emailidexistmessage">
			<input type="hidden" name="isemailexist" value="no"/>
			</span>
			</td>
		</tr>
		<tr>
			<td>Password:<font color="red">*</font></td>
			<td><html:password property="password" size="40" maxlength="50" styleClass="text"/></td>
		</tr>
		<tr>
			<td>Confirm Password:<font color="red">*</font></td>
			<td><html:password property="cpassword" size="40" maxlength="50" styleClass="text"/></td>
		</tr>     


		<tr>
			<td>Organization Name:<font color="red">*</font></td>
			<td><html:text property="orgName" size="40" maxlength="200" styleClass="text"/></td>
		</tr>


		<tr>
			<td>Office phone</td>
			<td><html:text property="phoneOffice" size="40" maxlength="20" styleClass="text"/></td>
		</tr>
		<tr>
			<td>Home phone</td>
			<td><html:text property="phoneHome" size="40" maxlength="20" styleClass="text"/></td>
		</tr>

		         <tr>
			<td>Nationality</td>
			<td>
			<html:select  property="nationalityId" styleClass="list">
			<bean:define name="userRegForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>
			</td>

		<tr>
			<td>City:</td>
			<td><html:text property="city" size="40" maxlength="200" styleClass="text"/></td>
		</tr>


		<tr>
			<td >Time zone :</td>
			<td>
			<html:select  property="timezoneId" styleClass="list">
			<bean:define name="userRegForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
			
			</tr>



	
	
    <tr>
			<td>
			<html:hidden property="ipaddress" value="<%=request.getRemoteAddr()%>"/>
			<html:hidden property="packagetaken" value="<%=userRegForm.getPackagetaken()%>"/>
			
			
			<input type="button" name="login" value="Create my account" onClick="savedata()">
		
			</td>
			<td>
			<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>Submit operation may take a few minutes, please be patient!!!</span>
			</td>
		</tr>

		
	</table>
</div>

</html:form>



</body>

