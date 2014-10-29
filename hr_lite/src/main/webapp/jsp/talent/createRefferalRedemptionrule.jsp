<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="ruleform" name="refferalRedemptionRuleForm" type="com.form.RefferalRedemptionRuleForm" />

<style>
span1{color:#ff0000;}
</style>
<style type="text/css">
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
	legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
	</style>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
			 parent.parent.GB_hide();
	   } 
	}
function init(){
setTimeout ( "document.refferalRedemptionRuleForm.ruleName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
	var alertstr = "";
	var showalert=false;
	var numbers=/^[0-9]+$/;
	var name = document.refferalRedemptionRuleForm.ruleName.value.trim();
	var ruleDesc= document.refferalRedemptionRuleForm.ruleDesc.value.trim();
    var creditafterdays = document.refferalRedemptionRuleForm.creditAfterdays.value.trim();
	if(name == "" || name == null){
     	alertstr = alertstr + "Rule name is Mandatory <br>";
		showalert = true;
		}
	if(ruleDesc == "" || ruleDesc == null){
     	alertstr = alertstr + "Rule description is Mandatory <br>";
		showalert = true;
		}

	if(creditafterdays < 0 || creditafterdays > 32767){
     	alertstr = alertstr + " please enter Credit after days between 0 to 32767 <br>";
		showalert = true;
	}
	if(numbers.test(creditafterdays)== false){
     	alertstr = alertstr + "Please enter value in numbers <br>";
		showalert = true;
	}
		
	if(ruleDesc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
		if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.refferalRedemptionRuleForm.action = "rulelovlist.do?method=saveRule&readPreview=2";
 document.refferalRedemptionRuleForm.submit();

 
	}

function updatedata(){

	var alertstr = "";
	var showalert=false;
	var numbers=/^[0-9]+$/;
	var name = document.refferalRedemptionRuleForm.ruleName.value.trim();
	var creditafterdays = document.refferalRedemptionRuleForm.creditAfterdays.value.trim();
	var ruleDesc= document.refferalRedemptionRuleForm.ruleDesc.value.trim();
   
	if(name == "" || name == null){
     	alertstr = alertstr + "Rule name is Mandatory <br>";
		showalert = true;
		}
	if(ruleDesc == "" || ruleDesc == null){
     	alertstr = alertstr + "Rule description is Mandatory <br>";
		showalert = true;
		}

 	if(creditafterdays < 0 || creditafterdays > 32767){
     	alertstr = alertstr + " please enter Credit after days between 0 to 32767 <br>";
		showalert = true;
		}
	if(numbers.test(creditafterdays)== false){
     	alertstr = alertstr + "Please enter value in numbers<br>";
		showalert = true;
	}
	if(ruleDesc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
		if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.refferalRedemptionRuleForm.action = "rulelovlist.do?method=updateRule&readPreview=2&id=<%=ruleform.getRuleId()%>";
 document.refferalRedemptionRuleForm.submit();


 //window.top.hidePopWin();+'<bean:write name="refferalRedemptionRuleForm" property="ruleId"/>'
 
	}


	function closewindow(){
	  parent.parent.GB_hide();
}
	function deleteRedemptionrule(id){

	  	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
		 if (doyou == true){
	  // var url = "<%=request.getContextPath()%>/rulelovlist.do?method=dedeleteRule&id="+id;
		//GB_showCenter('<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.redumption_Rule_Delete",user1.getLocale())%>',url,200,500, messageret);
		document.refferalRedemptionRuleForm.action = "rulelovlist.do?method=dedeleteRule&readPreview=1&id="+'<bean:write name="refferalRedemptionRuleForm" property="ruleId"/>';
	   document.refferalRedemptionRuleForm.submit();
			 }
	}
</script>
<%
String savedRule = (String)request.getAttribute("savedRule");
String updateRule = (String)request.getAttribute("updateRule");
String deleteRule = (String)request.getAttribute("deleteRule");

%>
<body class="yui-skin-sam" onload="init()">

<html:form action="/rulelovlist.do?method=saveRule">

<div align="center" class="div">
	
<% if(savedRule != null && savedRule.equals("yes")){%>
<font color="white"><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.savemsg",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> -->
	
<%}%>

<% if(updateRule != null && updateRule.equals("yes")){%>
<font color="white"><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.updatemsg",user1.getLocale())%></font><!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> -->
	
<%}%>
<% if(deleteRule != null && deleteRule.equals("yes")){%>
<font color="white"><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.deletemsg",user1.getLocale())%></font> <!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> -->
	
<%}else{%>
		
	
</div>
<br><br>
<div class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<!--  <td><b><u><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.create_rule",user1.getLocale())%></u></b></td>-->
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Name",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="ruleName" maxlength="300" size="53"/></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%><span1>*</span1></td>
			<td><html:textarea property="ruleDesc" cols="50" rows="5"/></td>
		</tr>
		<%--
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			<td>
				<html:select property="orgId">
				<bean:define name="refferalRedemptionRuleForm" property="orgnizationList" id="orgnizationList" />

            	<html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
				</html:select>
			</td>
		</tr>
		--%>
		
		<tr>
			<td>Criteria<span1>*</span1></td>
			<td>
	
			<html:select property="criteria" >

			<bean:define name="refferalRedemptionRuleForm" property="criteriaList" id="criteriaList" />
            <html:options collection="criteriaList"  property="key"  labelProperty="value"/>
			</html:select>
	      
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="creditAfterdays" maxlength="800" size="5"/></td>
		</tr>
		

	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	
		</table>
		<table>
		<tr>
			<td>
              <%if (ruleform.getRuleId()==0){ %>
                <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
              <%}else{%>
			    <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			    <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteRedemptionrule(<%=ruleform.getRuleId()%>)" class="button">
			
			  <%}%>
			     <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
	
		</tr>
		
		</table>
		<%}%>
	</div>	


</html:form>



</body>

