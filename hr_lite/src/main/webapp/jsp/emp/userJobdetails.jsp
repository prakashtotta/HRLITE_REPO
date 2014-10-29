<%@ include file="../common/include.jsp" %>
<%@ include file="../common/yahooincludes.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userJobForm" type="com.form.employee.UserJobForm" />


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println("<<<< userJobdetails.jsp>>>>>");
%>





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
</style>
<html:form action="/userjob.do?method=logon" enctype="multipart/form-data">

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.job",user1.getLocale())%></legend>
          
            <br>

<table border="0" width="100%">
            <tr>
            	<td align="left" width="35%"><%=Constant.getResourceStringValue("hr.user.job_title",user1.getLocale())%></td>
            	 <td align="left">
 				 <%=cform.getDesignationName()== null ?"":cform.getDesignationName() %>
					<html:hidden property="userId" />
					<html:hidden property="jobdetailsId" />
				</td>
            </tr>
            <tr>
            	<td align="left" width="35%"><%=Constant.getResourceStringValue("hr.user.job_grade",user1.getLocale())%></td>
            	  <td align="left"><%=cform.getJobGrade()== null ?"":cform.getJobGrade().getJobGradeName() %></td>
            </tr>
            <tr>
            	<td align="left"><%=Constant.getResourceStringValue("hr.user.Employment_status",user1.getLocale())%></td>
            	   <td align="left"><%=cform.getJobtype()== null ?"":cform.getJobtype().getJobTypeName() %></td>
            </tr>
            <tr>
            	<td align="left"><%=Constant.getResourceStringValue("hr.user.Job_Category",user1.getLocale())%></td>
            	 <td align="left"><%=cform.getJobcategory() == null ?"":cform.getJobcategory().getJobCategoryName() %></td>
            </tr>
            <tr>
            	<td align="left"><%=Constant.getResourceStringValue("hr.user.Joined_date",user1.getLocale())%></td>
            	<td align="left"><%=cform.getJoinedDate() == null ?"":cform.getJoinedDate() %></td>
            </tr>
			<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
         	
            <tr>
            	<td align="left"><font size="2"><b><%=Constant.getResourceStringValue("hr.user.Employment_Contract",user1.getLocale())%></b></font></td>
            </tr>
           
            <tr>
            	<td align="left"><%=Constant.getResourceStringValue("hr.user.start_date",user1.getLocale())%></td>
            		<td align="left"><%=cform.getContractStartDate() == null ?"":cform.getContractStartDate() %></td>
     
            </tr>
            <tr>
            	<td align="left"><%=Constant.getResourceStringValue("hr.user.end_date",user1.getLocale())%></td>
           	<td align="left"><%=cform.getContractEndDate() == null ?"":cform.getContractEndDate() %></td>
            </tr>
            <tr>
            	<td align="left"><%=Constant.getResourceStringValue("hr.user.contract_details",user1.getLocale())%></td>
           
            </tr>
            			<%
				String addUserSalary = request.getContextPath()+"/userjob.do?method=editUserJobdetails&userId="+cform.getUserId();		
			%>
			
			
           
             <tr>
                     <td align="left">
                     <a href="#" onClick="javascript:editUserJobdetails('<%=addUserSalary%>');return false"><%=Constant.getResourceStringValue("hr.user.job.Edit",user1.getLocale())%></a>
                     </td>
                     <td align="left">
                       <a href="#" onClick="javascript:retrieveData('userjob');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
                     </td>
             </tr>
            </table>
<br>

           <br><br>
	
</fieldset>
</span>
</html:form>