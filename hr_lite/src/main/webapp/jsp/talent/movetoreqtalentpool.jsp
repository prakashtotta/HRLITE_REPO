<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>
<style>
span1{color:#ff0000;}
</style>
<html>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<script language="javascript">

function hideunhidetalentreq(){
	var tr = document.applicantForm.moveto[0].checked;
	document.applicantForm.action = "applicant.do?method=movetoreqtalentpoolsrc&istaneltpoollist="+tr;
   document.applicantForm.submit();
	
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 parent.parent.GB_hide(); 
	   }
}

function closewindow2(){
	 parent.parent.GB_hide();
 
	}
function move(){
	var tr = document.applicantForm.moveto[0].checked;
	document.applicantForm.action = "applicant.do?method=movetoreqtalentpool&istaneltpoollist="+tr;
	document.applicantForm.submit();
}

function opensearchjobreq(){
	var tu = "lov.do?method=requisitionselector&frompage=createapppage";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  'SearchRequistion','height=600,width=700,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');


}

</script>

<%
	List errorList = (List)request.getAttribute(Common.ERROR_LIST);
	if(errorList != null && errorList.size()>0){%>
<div align="center">
	<table border="0" width="100%">
	
<% for(int i=0;i<errorList.size();i++){
	String errorval = (String)errorList.get(i);
%>	
	        <tr>
			<td><font color="red"><li><%=errorval%></font></td>
			<td></td>
			</tr>
		
<%}%>
		
	</table>
</div>
<%}%>

<body class="yui-skin-sam">
<%
String istaneltpoollist = (String)request.getAttribute("istaneltpoollist");
String talentpoolchecked="";
String reqchecked="";
if(istaneltpoollist != null && istaneltpoollist.equals("yes")){
talentpoolchecked = "CHECKED";
}else{
	reqchecked = "CHECKED";
}

String applicantmovedtotalentpool = (String)request.getAttribute("applicantmovedtotalentpool");
	
if(applicantmovedtotalentpool != null && applicantmovedtotalentpool.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisistion.applicant.moved.to.talentpool",user1.getLocale())%> </font></td>
			<td> <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String applicantmovedtoreq = (String)request.getAttribute("applicantmovedtoreq");
	
if(applicantmovedtoreq != null && applicantmovedtoreq.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisistion.applicant.moved.to.requistion",user1.getLocale())%> </font></td>
			<td><font color="white"> <a href="#" onclick="closewindow2()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a></td>
		</tr>
		
	</table>
</div>

<%}%>


<html:form action="/applicant.do?method=movetoreqtalentpool" >

<html:hidden property="applicantId"/>
<html:hidden property="uuid"/>

<% if(applicantmovedtoreq == null && applicantmovedtotalentpool == null){%>

	<table border="0" width="100%" class="div">
	<font color = red ><html:errors /> </font>
	<tr>
			<td> </td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.moveto",user1.getLocale())%><font color="red">*</font></td>
			<td><%=Constant.getResourceStringValue("aquisistion.talent.pool",user1.getLocale())%>
			<input type="radio" name="moveto" value="T" <%=talentpoolchecked%> onClick="hideunhidetalentreq()">&nbsp;&nbsp;
			<%=Constant.getResourceStringValue("applicant.Round.Requistion",user1.getLocale())%>
			<input type="radio" name="moveto" value="R" <%=reqchecked%> onClick="hideunhidetalentreq()"></td>
		</tr>
      <% if(istaneltpoollist != null && istaneltpoollist.equals("yes")){%>
        <tr>
		<td><%=Constant.getResourceStringValue("aquisistion.talent.pool",user1.getLocale())%></td>

			<td>
			<html:select  property="talentPoolId">
			<bean:define name="applicantForm" property="talentPoolList" id="talentPoolList" />
            <html:options collection="talentPoolList" property="talentPoolId"  labelProperty="talentPoolName"/>
			</html:select>
			</td>
         </tr>
         <%}else{%>
		

       
		<tr>
		<td><%=Constant.getResourceStringValue("Requisition.jobtitle",user1.getLocale())%></td>

				<td>
				<html:select  property="requitionId">
				<bean:define name="applicantForm" property="jobtitleList" id="jobtitleList" />
            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>
				<a href="#" onClick="opensearchjobreq()"><img src="jsp/images/selector.gif" border="0"/></a>
				
				
				</td>
		</tr>
		
	<%}%>
	    
       
	   <tr>
	  <td><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%> </td>
	  <td><html:textarea property="note" cols="60" rows="5"/></td>
	  </tr>

		<tr>
      <td colspan="2"><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.move",user1.getLocale())%>" onClick="move()" class="button">
      <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>

	  </tr>

	  <tr>
	  <td>
	  <span style="visibility:hidden" id="currectctccurrencycodesp"></span>
		<span style="visibility:hidden" id="expectedctccurrencycodesp"></span>
	  </td>
	  <td></td>
	  </tr>

      
	  </table>
<%}%>
</html:form>