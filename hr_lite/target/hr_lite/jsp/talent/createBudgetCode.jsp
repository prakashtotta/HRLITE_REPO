<%@ include file="../common/include.jsp" %>
<script type="text/javascript" src="jsp/jquery/jquery-1.7.1.js"></script>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="budgetcodeform" name="budgetCodeForm" type="com.form.BudgetCodeForm" />

<style>
span1{color:#ff0000;}
</style>

<style type="text/css"> 


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}



	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.budgetCodeForm.budgetCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
	 var alertstr = "";
	   var numbers=/^[0-9]+$/;
    var budgetCode = document.budgetCodeForm.budgetCode.value.trim();
	var budgetYear = document.budgetCodeForm.budgetYear.value.trim();
	var orgId = document.budgetCodeForm.orgId.value.trim();
	//var departmentId = document.budgetCodeForm.departmentId.value.trim();
	var budgetamount = document.budgetCodeForm.budgetamount.value.trim();
	var desc = document.budgetCodeForm.budgetCentreDesc.value.trim();
	
	
	var showalert=false;

	if(budgetCode == "" || budgetCode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetcode_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(budgetYear == "" || budgetYear == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetyear_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(orgId == "" || orgId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	/*if(departmentId == "" || departmentId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Department_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}*/

	//if(budgetamount == "" || budgetamount == null || budgetamount ==  "0"){
    // 	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetamount_is_required",user1.getLocale())%><BR>";
	//	showalert = true;
	//	}
	if(numbers.test(budgetamount)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetamount_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.BudgetCode.BudgetDescription",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 
	  document.budgetCodeForm.action = "budgetcode.do?method=saveBudgetCode";
 document.budgetCodeForm.submit();
 

 
	}

function updatedata(){

	 var alertstr = "";
	   var numbers=/^[0-9]+$/;
    var budgetCode = document.budgetCodeForm.budgetCode.value.trim();
	var budgetYear = document.budgetCodeForm.budgetYear.value.trim();
	var orgId = document.budgetCodeForm.orgId.value.trim();
	//var departmentId = document.budgetCodeForm.departmentId.value.trim();
	var budgetamount = document.budgetCodeForm.budgetamount.value.trim();
	var desc = document.budgetCodeForm.budgetCentreDesc.value.trim();
	var showalert=false;

	if(budgetCode == "" || budgetCode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetcode_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(budgetYear == "" || budgetYear == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetyear_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(orgId == "" || orgId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Organization_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	/*if(departmentId == "" || departmentId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Department_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}*/

	//if(budgetamount == "" || budgetamount == null || budgetamount ==  "0"){
    // 	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetamount_is_required",user1.getLocale())%><BR>";
	//	showalert = true;
	//	}

	if(numbers.test(budgetamount)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Budgetamount_is_required",user1.getLocale())%><br>";
		showalert = true;
		}

	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.BudgetCode.BudgetDescription",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	
	  document.budgetCodeForm.action = "budgetcode.do?method=updateBudgetCode&id="+'<bean:write name="budgetCodeForm" property="budgetId"/>';
 document.budgetCodeForm.submit();

 
	}

function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.budgetCodeForm.action = "budgetcode.do?method=DeleteBudgetCode&id="+'<bean:write name="budgetCodeForm" property="budgetId"/>';

		  document.budgetCodeForm.submit();
	  }
	
}
function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";
var tdata="";
	
	url=url+"&orgId="+document.budgetCodeForm.orgId.value.trim();
	
	$.ajax({
		type: 'GET',
	  url: url,
	  success: function(data){
	  tdata = $(data).find("#departments");  
	  $('#departments').html(tdata);
		
	  }
	});


var urlnew = "org.do?method=getcurrencycodebyorg&orgId="+document.budgetCodeForm.orgId.value.trim();
	$.ajax({
		type: 'GET',
	  url: urlnew,
	  success: function(data){
			tdata = $(data).find("#currencycode"); 

	  $('#currencycode').html(tdata);
		
	  }
	});

document.getElementById("loading").style.visibility = "hidden";	
	//setTimeout("getCurrencyCode()",300);

	

}
	 



</script>

<%
 String saveBudgetCode = (String)request.getAttribute("saveBudgetCode");
String updateBudgetCode = (String)request.getAttribute("updateBudgetCode");
String deleteBudgetCode = (String)request.getAttribute("deleteBudgetCode");
 System.out.println("saveBudgetCode"+saveBudgetCode);
%>

<body class="yui-skin-sam" onload="init()" >

<html:form action="/budgetcode.do?method=saveBudgetCode">


<% if(updateBudgetCode != null && updateBudgetCode.equals("yes")){%>
<div class="msg"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.BudgetCode.updatemsg",user1.getLocale())%> </font> <!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> -->
	</div>
<%}else{%>

<% if(saveBudgetCode != null && saveBudgetCode.equals("yes")){%>
<div class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.BudgetCode.savemsg",user1.getLocale())%> </font> <!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> -->
	</div>
<%}else{%>

<% if(deleteBudgetCode != null && deleteBudgetCode.equals("yes")){%>
<div class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.BudgetCode.deletemsg",user1.getLocale())%> </font> <!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> -->
	</div>
<%}else{%>
<br><br>

<div align="center" class="div">
<%

if ((budgetcodeform.getReadPreview()).equals("2")){

%>
     <br>
	 <fieldset><legend><%=Constant.getResourceStringValue("admin.BudgetCode.bcd",user1.getLocale())%> </legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%> :</td>
			<td><%=(budgetcodeform.getBudgetCode()==null)?"":budgetcodeform.getBudgetCode()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.Budegetname",user1.getLocale())%> :</td>
			<td><%=(budgetcodeform.getBudgetCentreName()==null)?"":budgetcodeform.getBudgetCentreName()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetDescription",user1.getLocale())%> :</td>
			<td><%=(budgetcodeform.getBudgetCentreDesc()==null)?"":budgetcodeform.getBudgetCentreDesc()%></td>
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetYear",user1.getLocale())%> :</td>
			<td>
			<%=(budgetcodeform.getBudgetYear()==null)?"":budgetcodeform.getBudgetYear()%>
			</td>
		</tr>


		<%
		String orgurl =null;
		System.out.println("budgetcodeform.getOrgId()"+budgetcodeform.getOrgId());
        if(budgetcodeform.getOrgId() != 0){

   orgurl = "<a href='#'  onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+budgetcodeform.getOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330"+"'"+")>"+budgetcodeform.getOrganizationStr()+"</a>";

		}

		%>


				<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> : </td>
			
			<td>
			<%=(orgurl == null)?"":orgurl%>
			
			</td>
		</tr>
		

				<%
		String deptpurl =null;
        if(budgetcodeform.getDepartmentId() != 0){

    deptpurl = "<a href='#' onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+budgetcodeform.getDepartmentId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+budgetcodeform.getDepartmentStr()+"</a>";

		}

		%>


		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> : </td>
			
			<td>
		<%=(deptpurl == null)?"":deptpurl%>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetAmount",user1.getLocale())%> : </td>
			<td><%=budgetcodeform.getBudgetamount()%>&nbsp;&nbsp;<%=budgetcodeform.getBudgetCurrency()%></td>
		</tr>
		</table>
		</fieldset>

<%}else{%>
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<tr>
			<td></td>
			<td></td>
		</tr>	
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text   property="budgetCode" size="57" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.Budegetname",user1.getLocale())%></td>
			<td><html:text property="budgetCentreName" size="57" maxlength="500"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetDescription",user1.getLocale())%></td>
			<td><html:textarea property="budgetCentreDesc" cols="60" rows="5"/></td>
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetYear",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="budgetYear">
			<bean:define name="budgetCodeForm" property="yearsList" id="yearsList" />

            <html:options collection="yearsList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%><font color="red">*</font></td>
			
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<bean:define name="budgetCodeForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.BudgetCode.LoadingDepartments",user1.getLocale())%>	</span>
			
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			<option value=""></option>
			<bean:define name="budgetCodeForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetAmount",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="budgetamount" /> 
			<span id="currencycode"><%=budgetcodeform.getBudgetCurrency()%>
			<html:hidden property="budgetCurrency" value="<%=budgetcodeform.getBudgetCurrency()%>"/>
			</span>
</td>
		</tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
		
		<tr>
			<td colspan="2"><% if ((budgetcodeform.getReadPreview()).equals("1")){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
	<%}%>	
		</table>

		<%}}}%>
</div>

</html:form>


</body>

