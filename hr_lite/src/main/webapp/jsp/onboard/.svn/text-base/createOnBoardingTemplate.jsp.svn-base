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
<bean:define id="Onboardingtemplateform" name="OnboardingtemplateForm" type="com.form.OnBoardingTemplateForm" />

<style>
span1{color:#ff0000;}
</style>

<script language="javascript">

 function opensearchonboardtaskdefination(num){

	
//var ttt=document.OnboardingtemplateForm.aaa.value;
//var list=<%=Onboardingtemplateform.getOnboardtaskidListVal()%>;
  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("OnBoardingTemplate.do?method=selectOnboardTaskDefination&numcount="+num);
window.open(url,'SearchRequistion','height=550,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');

}

function validateUserhm(){

	document.OnboardingtemplateForm.primaryOwnerName.value=document.OnboardingtemplateForm.primaryownernamehidden.value

}

function deletedatasure(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  //self.parent.location.reload();
			// parent.parent.GB_hide();
			deletedata();
	   } 
	}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		
			 parent.parent.GB_hide();
	   } 
	}
function discard1(){
	
	 parent.parent.GB_hide();
	}

function savedata(){
	var alertstr = "";
	var showalert=false;
  
	 var templatename=document.OnboardingtemplateForm.templateName.value.trim();
	 var desc=document.OnboardingtemplateForm.templateDesc.value.trim();

		if(templatename == "" || templatename == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTemplate.createpage.validation.OnBoardTemplateNameisMandatory",user1.getLocale())%><br>";
		//document.forms[0].taskName.focus();  

		showalert = true;
		}
		/*var taskdefid=document.OnboardingtemplateForm.taskdefid.value;
		if(taskdefid == null || taskdefid ==""){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTemplate.createpage.validation.OnBoardTaskDefinationisMandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		*/
		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
       if (showalert){
     	alert(alertstr);
        return false;
          }
		  
		 
	document.OnboardingtemplateForm.action = "OnBoardingTemplate.do?method=saveOnBoardTemplate&readPreview=2";
		
	document.OnboardingtemplateForm.submit();
	//self.parent.location.reload();
	 
	}
function updatedata(){
var alertstr = "";
	var showalert=false;
  
	 var templatename=document.OnboardingtemplateForm.templateName.value.trim();
	 var desc=document.OnboardingtemplateForm.templateDesc.value.trim();
		if(templatename == "" || templatename == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTemplate.createpage.validation.OnBoardTemplateNameisMandatory",user1.getLocale())%><br>";
		//document.forms[0].taskName.focus();  
		showalert = true;
		}
		/*var taskdefid=document.OnboardingtemplateForm.taskdefinationStringvalue.value;
		if(taskdefid == null || taskdefid ==""){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTemplate.createpage.validation.OnBoardTaskDefinationisMandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		*/
		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
       if (showalert){
     	alert(alertstr);
        return false;
          }
	document.OnboardingtemplateForm.action = "OnBoardingTemplate.do?method=updateOnBoardingTemplate&readPreview=2&id="+'<bean:write name="OnboardingtemplateForm" property="templateid"/>';
	document.OnboardingtemplateForm.submit();
	//self.parent.location.reload();
	 
	}

function deletedata(){
	document.OnboardingtemplateForm.action = "OnBoardingTemplate.do?method=deleteOnBoardingTemplate&readPreview=1&id="+'<bean:write name="OnboardingtemplateForm" property="templateid"/>';
    document.OnboardingtemplateForm.submit();
	
}
//function init(){
//	setTimeout ( "document.OnboardingtemplateForm.templateName.focus(); ", 200 );
	//document.roleForm.roleCode.focus();
//	}


var listsize=<%=Onboardingtemplateform.getListsizeval()%>;

var num=+listsize;
function addElement() { 

  var ni = document.getElementById('myDiv');
  var numi = document.getElementById('theValue');
  var kkk='taskdefid_'+num;
  //var idtaskdef=document.OnboardingtemplateForm.taskdefid_1.value;
 // var num = (document.getElementById('theValue').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  var spanstart = '<span id="onboardtaskdefination_'+num+'">';
  var attributes1 = '<input type="text" size = "50" maxlength= "100" name="attributes_'+num+'"/>';
  var attribteid1 = '<input type="hidden"    name="taskdefid_'+num+'" value=""/>';
  var attribteid2 = '<input type="hidden"    name="taskname_'+num+'" value=""/>';
  //var mandatory1 = '<input type="checkbox"  checked   name="mandatory_'+num+'"/>';
  var spanend = '</span>'; 
  var spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
  newdiv.innerHTML = ''+spanstart+''+spaces+''+spaces+''+spanend+''+attribteid1+''+attribteid2+''+'<a href=\'#\' onclick=\'opensearchonboardtaskdefination('+num+')\'><img src="jsp/images/selector.gif" border="0" alt="add task defination" title="add task defination" height="14"  width="19"/></a>'+''+'<a href=\'#\' onclick=\'removeElement('+divIdName+','+num+')\'><img src="jsp/images/delete.gif" border="0" alt="delete attributes" title="delete attributes" height="14"  width="19"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
  num++;
}



function removeElement(divNum,num) {
	var tasknum = 'taskdefid_'+num;
	var idtaks=document.OnboardingtemplateForm[tasknum].value;	
	var listidtask=document.OnboardingtemplateForm.aaa.value;	
var d = document.getElementById('myDiv');
d.removeChild(divNum);
var array1=new Array();
var array1new=new Array();
array1=listidtask;
for(var i=0;i<array1.length;i++){
	if(idtaks!=array1[i]){
       array1new[i]=array1[i];
	}
}

document.OnboardingtemplateForm.aaa.value=array1new;


}
</script>
<body class="yui-skin-sam" onLoad="init()">
<%
String saveOnBoardtemplate = (String)request.getAttribute("saveOnBoardtemplate");
	
if(saveOnBoardtemplate != null && saveOnBoardtemplate.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.savemessage",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateOnBoardtemplate = (String)request.getAttribute("updateOnBoardtemplate");
	
if(updateOnBoardtemplate != null && updateOnBoardtemplate.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.updatemessage",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteOnBoardtemplate = (String)request.getAttribute("deleteOnBoardtemplate");
	
if(deleteOnBoardtemplate != null && deleteOnBoardtemplate.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.deletemessage",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>
<br>

<html:form action="/OnBoardingTemplate.do?method=saveOnBoardTemplate">
<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	
	<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
		<td><html:hidden  property="listsizeval" /></td>
       <td><input type="hidden" name="aaa" value="<%=Onboardingtemplateform.getOnboardtaskidListVal()%>"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTemplate.bodylistpage.Search.TemplateName",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text  property="templateName" size="57" maxlength="500"/></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.desc",user1.getLocale())%></td>
			<td><html:textarea property="templateDesc" cols="57" rows="5" /></td>
		</tr>



<tr>
<%
	 List taskdefList = new ArrayList();
if(Onboardingtemplateform.getTaskdefinitions()!= null && Onboardingtemplateform.getTaskdefinitions().size()>0){
   
	Iterator itr =Onboardingtemplateform.getTaskdefinitions().iterator();
        while(itr.hasNext()){
		OnBoardingTaskDefinitions ch = (OnBoardingTaskDefinitions)itr.next();
		taskdefList.add(ch);
		
		}
}
%>	
<%if(taskdefList != null){ %>
	<input type="hidden" value="<%=taskdefList.size()%>" id="theValue" />
	<%}else{ %>
	<input type="hidden" value="" id="theValue" />
	<%} %>
<tr><td>
<a href="javascript:;"onclick="addElement();"><%=Constant.getResourceStringValue("admin.OnBoardTemplate.createpage.addtaskdefination",user1.getLocale())%>
</a>
</td>
</tr>
 

<td></td>

<td>
	<div id="myDiv">
<%
int k2=1;
if(taskdefList != null){
for(int i=0;i<taskdefList.size();i++){
	
	OnBoardingTaskDefinitions jcomp = (OnBoardingTaskDefinitions)taskdefList.get(i);
   
	 
	 String tdiv = "my"+k2+"Div";
	 String tspancomp = "onboardtaskdefination_"+k2;
	 String tcompname = "taskdefid_"+k2;
	 String tcompname1= "taskname_"+k2;

	String tempdivappcomp = "<div id='"+tdiv+"'"+">"+
		"<span id='"+tspancomp+"'"+">"+
		"<input type=\"hidden\"  size = \"20\" maxlength= \"100\" name="+tcompname+" value="+jcomp.getTaskdefid()+">"+
        "<input type=\"hidden\"  size = \"20\" maxlength= \"100\" name="+tcompname1+" value="+jcomp.getTaskName()+">"+
		"<a href=\'#\' onclick=window.open("+"'"+"OnBoardingTaskDefi.do?method=OnBoardingTaskDefiDetails&readPreview=3&id="+jcomp.getTaskdefid()+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=550"+"'"+")>"+jcomp.getTaskName()+"</a>"+"&nbsp;"+
		"<a href=\'#\' onclick=\'opensearchonboardtaskdefination("+k2+")\'><img src=\"jsp/images/selector.gif\" border=\"0\" alt=\"add task defination\" title=\"add task\" height=\"14\"  width=\"19\"/></a>"+  
        
       	"<a href=\'#\' onclick=\'removeElement("+tdiv+","+k2+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" alt=\"delete task\" title=\"delete task\" height=\"14\"  width=\"19\"/></a>"+  
	"</div>";
	k2++;

%>

<%=tempdivappcomp%>

<%}}%>

</div>
</td>
</tr>
				 
	    </td>
	</tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
	<tr>
	<td colspan="2">
	<% if(Onboardingtemplateform.getReadPreview().equals("2")){%>
	        <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedatasure()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	      <%} else {%>
			<input type="button" name="login1" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>

			

			
		<%}%>
			
			<td></td>
		</tr>

		</table>
</div>

</html:form>

	<%}%>	
	
