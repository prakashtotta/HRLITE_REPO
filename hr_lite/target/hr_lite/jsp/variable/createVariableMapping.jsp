<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.lov.*" %>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="formVariablesmapform" name="formVariablesMapForm" type="com.form.FormVariablesMapForm" />

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

<script language="javascript">
function addAttributes(){
    var alertstr = "";
	var taskdefid ="<%=formVariablesmapform.getFormVariablemapId()%>";
	if(taskdefid=="0"){
		alert("First save On Boarding task defination");
		return false;
	}


	var Attribute_name = document.formVariablesMapForm.atrributes.value;
	var Is_mandatory = document.formVariablesMapForm.isMandatory.value;
	var showalert=false;

if(Attribute_name == "" || Attribute_name == null){
 	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.Entertheattributename",user1.getLocale())%><BR>";
	showalert = true;
	}


 if (showalert){
 	alert(alertstr);
    return false;
      }



}


function opensearchassignedtohiring(){
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=addprimaryowner");
	  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

	 // window.open("user.do?method=assignedtoselector&boxnumber=addhighiringmanagerreq", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
	}

function opensearchvariableselector(i){
 
  window.open("variablemap.do?method=variableselector&variablevalue=variable_"+i+"&idval="+i+"&variableidval=variableid_"+i+"&variablespanvalue=compspan_"+i,"SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=400");
  //alert(i);
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
function closewindow(){
	 //self.parent.location.reload();
	 parent.parent.GB_hide();
	}

function savedata(){
	
	document.formVariablesMapForm.action = "variablemap.do?method=saveOnBoardTaskDefi";
	document.formVariablesMapForm.submit();
	//self.parent.location.reload();
	 
	}
function updatedata(){

	var formcode=document.formVariablesMapForm.formCode.value;
	var formname=document.formVariablesMapForm.formName.value; 
	
	document.formVariablesMapForm.action = "variablemap.do?method=updateFormVariableMap&form_code="+formcode+"&form_name="+formname;
	document.formVariablesMapForm.submit();
	
	}

function deletedata(){
	document.formVariablesMapForm.action = "variablemap.do?method=deleteOnBoardingTaskDefi&id="+'<bean:write name="formVariablesMapForm" property="formVariablemapId"/>';
    document.formVariablesMapForm.submit();
	
}

function variableinfo(variableid){
	//alert("hi ...");
	window.open("variable.do?method=editVariable&readPreview=2&variableId="+variableid, "EvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=180");
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
	 // var variable='document.formVariablesMapForm.variable_'+num+'.value.trim()';
	 // alert(variable);
	  var spanstart = '<span id="compspan_'+num+'">';
	  var varspanstartend='<span id="varspan_'+num+'"></span>';
	  var tablestart= "<table><tr><td width='208px'>";
	  var tabletdendstart="</td><td>";
	  var tableend="<td></tr></table>";
	  var variable1 = '<input type="hidden" size = "30" maxlength= "100" readonly="true" name="variable_'+num+'"/>';
	  var variableid1 = '<input type="hidden"    name="variableid_'+num+'"/>';
	  var mandatory1 = '<input type="checkbox"  checked   name="mandatory_'+num+'"/>';
	  var spanend = '</span>'; 
	  var spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
	  newdiv.innerHTML = ''+spanstart+''+tablestart+''+varspanstartend+''+variable1+''+tabletdendstart+''+variableid1+spaces+'<a href=\'#\' onclick=\'opensearchvariableselector('+num+')\'><img src="jsp/images/selector.gif" border="0"/></a>'+''+spaces+''+spaces+''+tabletdendstart+''+mandatory1+''+spanend+''+spaces+''+spaces+'<a href=\'#\' onclick=\'removeElement('+divIdName+')\'><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"15em\" height=\"15em\" /></a>'+''+tableend;
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



<body class="yui-skin-sam" onLoad="init()" >

<%
 String manageVariableMapping = (String)request.getAttribute("manageVariableMapping");
 %>


<html:form action="/variablemap.do?method=saveOnBoardTaskDefi">

<% if(manageVariableMapping != null && manageVariableMapping.equals("yes")){%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.variable.message.updated",user1.getLocale())%> </font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></div>
		
<%}else{%>

<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>



		<tr>
			<td><b>Screen name :</b></td>
			<td><b><%=Constant.getResourceStringValue(formVariablesmapform.getFormName(),user1.getLocale())%></b></td>
			<td><html:hidden property="formName" /></td>
		</tr>	
		<tr></tr><tr></tr><tr></tr>
		
		<tr>
		<!--
			<td><%=Constant.getResourceStringValue("admin.variablemap.form_code",user1.getLocale())%></td>
			<td><bean:write name="formVariablesMapForm" property="formCode"/></td> 
		-->
			<td><html:hidden property="formCode" /></td>
		</tr>
		
	

<tr>
<td>
<%
List variableList = formVariablesmapform.getVariableList();
%>	
<%if(variableList != null){ %>
	<input type="hidden" value="<%=variableList.size()%>" id="theValue" />
    
	<%}else{ %>
	<input type="hidden" value="" id="theValue" />
	<%} %>
<a class="button" href="javascript:;"onclick="addElement();"><%=Constant.getResourceStringValue("admin.variablemap.add_variables",user1.getLocale())%>
</a>

 
</td>
<td></td>
</tr>
</table>
</div>
<br>

<div align="center" class="div">
<table border="0" width="100%">
<tr><td></td><td align="left">

<b><%=Constant.getResourceStringValue("admin.variable.selector.displaylable.variable",user1.getLocale())%></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.Ismandatory",user1.getLocale())%></b>

</td><td></td></tr>
<tr>
<td>

</td>
<td>
	<div id="myDiv">
<%
int k2=1;
if(variableList != null){
	for(int i=0;i<variableList.size();i++){
		FormVariablesMap jcomp = (FormVariablesMap)variableList.get(i);
	     String tdiv = "my"+k2+"Div";
		 String tspancomp = "compspan_"+k2;
		 String vspancomp = "varspan_"+k2;
		 String tcompname = "variable_"+k2;
	      String tcompmandatory = "mandatory_"+k2;
		  String checkedmancomp = (jcomp.getIsMandatory().equals("Y"))?"checked":"";
		  String variableid1= "variableid_"+k2;
		  
		  //String spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
		//String tcompimportance = "accimportance_"+k2;
		
		String tempdivappcomp = "<div id='"+tdiv+"'"+">"
			+"<span id='"+tspancomp+"'"+">"
			+"<span id='"+vspancomp+"'"+">"
			+"<table><tr><td width='208px'>"
			//+"<a href=\'#\' onclick=\'variableinfo("+jcomp.getVariable().getVariableId()+")\'>"+jcomp.getVariable().getVariableName()+"</a>"
			+"<a href=\'#\' onclick=\'variableinfo("+jcomp.getVariable().getVariableId()+")\'>"+jcomp.getVariable().getVariableName()+"</a>"
			+"<input type=\"hidden\" readonly size = \"30\" maxlength= \"100\" value="+"'"+jcomp.getVariable().getVariableName()+"'"+"  name='"+tcompname+"'"+"/>"
			+"</td><td >"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"<a href=\'#\' onclick=\'opensearchvariableselector("+k2+")\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>"
			+"</td><td >"
			+"<input type=\"hidden\"    value="+jcomp.getVariable().getVariableId()+" name='"+variableid1+"'"+"/>"
			+"</td><td >"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"<input type=\"checkbox\"" +checkedmancomp+ " name='"+tcompmandatory+"'"+"/>"
			+"</td><td >"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"<a href=\'#\' onclick=\'removeElement("+tdiv+")\' ><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"15em\" height=\"15em\"/></a>"
			+"</td></tr></table>"
			+"</div>";

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
	<tr><td>
	        <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	     
			
			</td>
			
			
		
			
			<td></td>
		</tr>

		</table>
</div>
<%}%>
</html:form>

	
	
