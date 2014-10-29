<%@ include file="../common/include.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.lov.*" %>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.filter.*" %>

<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />
<%

String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String closeurl = "jobreq.do?method=editjobreq&jobreqId="+bForm.getIdvalue()+"&tabno=7";

String savebusinessrule =(String)request.getAttribute("updatedapplicantfilters");
%>
<script language="javascript"> 

window.name = 'myModal';
</script>

<script language="javascript">
var formtype = "<%=bForm.getFrom_type()%>";
function closewindow(){
 
    if(formtype != null && formtype == 'TEMPLATE'){
	}else if(formtype != null && formtype == 'ALL_APPLICANTS'){
	}else{
	window.opener.document.location.href="<%=closeurl%>";
	}
	 window.close();
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	 //parent.parent.GB_hide();
		  window.close();
	   } 
	}


function updatedata(){

	var alertstr = "";
	var showalert=false;


	  document.businessRuleForm.action = "businessrule.do?method=manageApplicantFilter&reqId=<%=bForm.getIdvalue()%>&type=<%=bForm.getFrom_type()%>";
	  document.businessRuleForm.submit();
}




function opensearchfilter(bn){
	//alert(boxnumber);
	var t = "businessrule.do?method=applicantFilterselector&boxnumber="+bn;
	//var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(t);

	var url = "jsp/emp/temp.jsp?reurl="+t;
  
  window.open(t,  "SearchFilter","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

</script>
<body class="yui-skin-sam">

<html:form action="/businessrule.do?method=manageApplicantFilter" target='myModal'>

<% if(! StringUtils.isNullOrEmpty(savebusinessrule) && savebusinessrule.equals("yes")) {%>
<div class="msg"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.applicant.filters.updated.message.req",user1.getLocale())%></font></div>
<%} %>

<div class="div">
<fieldset><legend><b><%=Constant.getResourceStringValue("admin.applicant.filters",user1.getLocale())%></legend>
<%
List filtersList = bForm.getFilterCriteriasList();
%>
<table>
	<tr>
		
		<td>
		<input type="hidden" name="idlistvalflt" value=""/>
		<input type="hidden" value="<%=filtersList.size()%>" id="theValue2" />
<p><a href="javascript:;" onclick="addElement2();"><img src="jsp/images/add.gif" border="0" alt="add approver" title="<%=Constant.getResourceStringValue("admin.businessRule.add.applicant.filter",user1.getLocale())%>" height="20"  width="19"/></a></p>
		</td>
		<td>

		</td>
	</tr>
	<tr>
	<td><b><%=Constant.getResourceStringValue("admin.businessRule.applicant.filter",user1.getLocale())%></b></td>
	<!-- <td><b><%=Constant.getResourceStringValue("admin.filter.is.silent",user1.getLocale())%></b></td> -->
	</tr>
    <tr>
	<td>
	<div id="myDiv2"> 

<%
int k=1;
for(int i=0;i<filtersList.size();i++){
	BusinessCriteria japp = (BusinessCriteria)filtersList.get(i);
     String tdiv = "my2"+k+"Div";
	 String tspan = "approverspan_"+k;
	 String tfiltername = "filtername_"+k;
	 String tuserid ="filterid_"+k;
	 String apptx = " Approved";
	 String leveltext1 = "Filter-"+k+"&nbsp;&nbsp";
	 String filterlink = "";
	 String issilent = "issilent_"+k;
String checkesilent = (japp.getIsSilent().equals("Y"))?"checked":"";

		filterlink="	<img src=\"jsp/images/applicant_filter_icon.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"window.open('businessrule.do?method=viewApplicantFilter&filterid="+japp.getBusinessCriteraId()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')\">"+japp.getName()+"</a>";
	
	String tempdivapp = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getName()+"' name='"+tfiltername+"'"+"/>"+" "+filterlink + "  "+
       "<input type=\"hidden\"   value="+japp.getBusinessCriteraId()+" name='"+tuserid+"'"+"/>"+
	   	"</span>"+
		"<a href=\'#\' onclick=\'opensearchfilter("+k+")\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a> ";


String chkboxissilent = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<input type=\"checkbox\"" +checkesilent+ " name='"+issilent+"'"+"/>"+"&nbsp;Is Silent?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

String tempdivappdel =	" <a href=\'#\' onclick=\'removeElement2("+tdiv+","+k+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"15em\" hieght=\"15em\"/></a>";

String tempdivapp1 =  "</div>";

	tempdivapp = tempdivapp + chkboxissilent+ tempdivappdel + tempdivapp1;


	k++;
%>

<%=tempdivapp%>

<%}%>


</div>
	</td>
	<td>
	</td>
	</tr>

</table>

</fieldset>
<br>
<table>
	<tr>
		<td>
      
         <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
       
		<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
	</tr>
</table>
</div>
</html:form>
</body>

<script language="javascript">
	function addElement2() {

  var ni = document.getElementById('myDiv2');
  var numi = document.getElementById('theValue2');
  var num = (document.getElementById('theValue2').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my2'+num+'Div';
  newdiv.setAttribute('id',divIdName);

  var spanstart = '<span width=200 class="blue" id="approverspan_'+num+'">';
  var username1 = '<input type="hidden"  value="" name="filtername_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="filterid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="level_'+num+'"/>';
    var leveltext = 'Filter-'+num+'&nbsp;&nbsp';
	var issilent = '<input type="checkbox"  name="issilent_'+num+'"/>&nbsp;Is Silent?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    var spanend = '</span>'; 
	 var spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ spanend+''+'<a href=\'#\' onclick=\'opensearchfilter('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+spaces+''+issilent+''+'  <a href=\'#\' onclick=\'removeElement2('+divIdName+','+num+')\'><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"15em\" hieght=\"15em\"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}




function removeElement2(divNum,num) {
	var approvenum = 'filterid_'+num;
	var idapprove=document.businessRuleForm[approvenum].value;
	var listidapprover=document.businessRuleForm.idlistvalflt.value;
var d = document.getElementById('myDiv2');
d.removeChild(divNum);
//document.getElementById('theValue2').value=num-1;
var array1=new Array();
var array1new=new Array();
//array1=listidapprover.split(/\.\d{2},?/);
array1=listidapprover;
for(var i=0;i<array1.length;i++){
	
	
	if(idapprove!=array1[i]){
		
			
			
       array1new[i]=array1[i];
	}
	
}
document.businessRuleForm.idlistvalflt.value=array1new;
//alert(array1new);
}

</script>