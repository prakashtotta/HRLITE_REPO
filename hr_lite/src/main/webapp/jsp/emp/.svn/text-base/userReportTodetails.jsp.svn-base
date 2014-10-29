<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.employee.*"%>
<bean:define id="cform" name="userReportToForm" type="com.form.employee.UserReportToForm" />



  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println("<<<< userReportTodetails.jsp>>>>>");
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
<html:form action="/userreportto.do?method=logon" >

<span id="details_data">

<fieldset><legend><%=Constant.getResourceStringValue("hr.user.reportto",user1.getLocale())%></legend>
           
            <br>
           	<table border="0" width="50%">
            <tr>
            <td align="left" width="50%"><%=Constant.getResourceStringValue("hr.user.reportto",user1.getLocale())%></td>
             <td align="left"><bean:write name="userReportToForm" property="reportToType" /></td>
             </tr>
              <tr>
            <td align="left"><%=Constant.getResourceStringValue("hr.user.reportto.Name",user1.getLocale())%></td>
             <td align="left"><%=cform.getReportToUserName()==null?"":cform.getReportToUserName()%></td>
             </tr>
              <tr>
            <td align="left"><%=Constant.getResourceStringValue("hr.user.reportto.Reporting_Method",user1.getLocale())%></td>
 			<td align="left"><%=cform.getReportToMethod().equals("NoValue") ? "":cform.getReportToMethod()%></td>
            </tr>
            <%if(!StringUtils.isNullOrEmpty(cform.getReportToMethod()) && cform.getReportToMethod().equals("Other") ){ %>
            <tr>
            <td align="left"><%=Constant.getResourceStringValue("hr.user.reportto.other_Reporting_Method",user1.getLocale())%></td>
 			<td align="left"><bean:write name="userReportToForm" property="reportToMethodOther" /></td>
            </tr>
            <tr>
            <%} %>
                        <%
			String addUserSalary = request.getContextPath()+"/userreportto.do?method=editReportTodetails&userId="+cform.getUserId();		
			%>
			<td align="left">
			<a href="#" onClick="javascript:editReportToetails('<%=addUserSalary%>');return false"><%=Constant.getResourceStringValue("hr.user.job.Edit",user1.getLocale())%></a>
          
            </td>
            <td align="left">
             <a href="#" onClick="javascript:retrieveData('reportto');return false"><%=Constant.getResourceStringValue("hr.user.Reload",user1.getLocale())%></a>
            </td>
            
            </tr>
            </table><br>
			
           <br><br>
 
            </fieldset>
            </span>
   </html:form>