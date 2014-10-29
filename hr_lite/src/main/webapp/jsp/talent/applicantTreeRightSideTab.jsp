


<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.dao.*"%>
<%@ page import="com.security.*"%>



<%
 
  
  
 


  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
User hiringmgr = form.getHiringmgr();
if(hiringmgr == null){
	hiringmgr = new User();
}
Recruiter recruiter = form.getRecruiter();

List appusractionsList = ApplicantUserBO.getApplicantActionsList(form.getApplicantId(),form.getUuid());
boolean isApplicantEditAllowed = PermissionBO.isApplicantEditAllowed(user1,hiringmgr.getUserId(),recruiter);

%>

<script type="text/javascript">
var appidrightab = "<%=form.getApplicantId()%>";
</script>
<span id="rightsidetab">
<script type="text/javascript">
function addTagToApp(tagname,appid){
	$.ajax({
	type: 'POST',
  url: "applicant.do?method=addapplicanttag&applicantId="+appid+"&tagname="+tagname+"&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>",
  success: function(data){
 	
  }
 
});
}
function deleteTagToApp(tagname,appid){
	$.ajax({
	type: 'POST',
  url: "applicant.do?method=deleteApplicanttag&applicantId="+appid+"&tagname="+tagname+"&csrfcode=<%=CSRFTokenGenerator.getToken(request)%>",
  success: function(data){
 	
  }
 
});
}
</script>

<% 
			String assignmockexamurl = request.getContextPath()+"/applicant.do?method=assignExams&applicantId="+form.getApplicantId()+"&uuid="+form.getUuid(); 
	String attachurl = request.getContextPath()+"/applicantuserops.do?method=actionattachmentaddscr&idvalue="+form.getApplicantId()+"&action=plain_attachment&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();

	String addtagurl = request.getContextPath()+"/applicant.do?method=addapplicanttagscr&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();		
%>



		<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Actions  </div>
              <a href="#" onClick="assignmockexam('<%=assignmockexamurl%>');return false;"><%=Constant.getResourceStringValue("hr.applicant.assign.exams.Questionnaires",user1.getLocale())%></a>
			  <br>
			  <a href="#" onClick="addattachmentscr('<%=attachurl%>');return false;">Attachments</a>
			  <br>
			  <a href="#" onClick="addwatchers();return false;"><%=Constant.getResourceStringValue("Requisition.watchers",user1.getLocale())%></a>

			  <div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Tags  </div>
              <!--
				<% if(form.getTagId()>0){%>
           <%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%> : <bean:write name="applicantForm" property="tagName"/> 
           <a href="#" onClick="javascript:addtag('<%=request.getContextPath()%>/applicant.do?method=addapplicanttagscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>&tagid=<%=form.getTagId()%>')"> <%=Constant.getResourceStringValue("aquisition.applicant.change.tag",user1.getLocale())%></a>

			<%}else{%>
			<a href="#" onClick="javascript:addtag('<%=request.getContextPath()%>/applicant.do?method=addapplicanttagscr&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>')"> <%=Constant.getResourceStringValue("aquisition.applicant.add.tag",user1.getLocale())%></a>
			<%}%>
				-->
				<%
				String data=BOFactory.getTagsBO().getAllTagsByApplicantJSONMultiple(form.getApplicantId());
				%>
			<input type="text" id="demo-input-pre-populated" name="tags" />
		<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#demo-input-pre-populated").tokenInput("jsp/talent/getTagDataJsonMuti.jsp", {
				 preventDuplicates: true,
			     hintText: "Type in a search tag",
					 onAdd: function (item) {
                    addTagToApp(item.name,appidrightab);
                },
                onDelete: function (item) {
						deleteTagToApp(item.name,appidrightab);
                    
                },
                prePopulate: [
						<%=data%>
                ]
            });
        });
        </script>

			  <div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[sourcesubsource]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Applicant login  </div>

            <%
 String createUserUrl = request.getContextPath()+"/applicantUser.do?method=createApplicantUser&applicantId="+form.getApplicantId()+"&uuid="+form.getUuid();
 ApplicantUser appuser = form.getApplicantuser();


 %>
      <% if(isApplicantEditAllowed || PermissionChecker.isPermissionApplied(Common.MANAGE_APPLICANT_USER, user1)){%>
		 <table>
		
		 <tr>
		 <td>
		 <% if(appuser == null){
			  
				  if(!StringUtils.isNullOrEmpty(user1.getPackagetaken()) && Constant.isPackageContainFunction(user1.getPackagetaken(),"applicantUser")){
			 %>
		 <a href="#" onClick="javascript:createUser('<%=createUserUrl%>')">
		Create applicant login</a> 
		<%}%>
		
		<%}else{
			String edituserUrl = request.getContextPath()+"/applicantUser.do?method=editApplicantUser&applicantId="+form.getApplicantId()+"&uuid="+form.getUuid();
		%>
		<a href="#" onClick="editApplicantUser('<%=edituserUrl%>')"><%=appuser.getEmailId()%></a>
		<%}%>
		 
		 </td>
		 </tr>
		 <tr>
		 <td>
		 <% if(appuser != null){
		String addactionurl = request.getContextPath()+"/applicantUserActions.do?method=addapplicantactionsscr&applicantId="+form.getApplicantId()+"&uuid="+form.getUuid();
		%>
		<a  href="#" onClick="javascript:addapplicantactions('<%=addactionurl%>')">
		<%=Constant.getResourceStringValue("hr.applicant.add.actions",user1.getLocale())%></a> &nbsp; &nbsp; &nbsp; &nbsp; 
		<%}%>
		 </td>
		 </tr>
		 </table>
		 <table>
		<% if(appusractionsList != null && appusractionsList.size()>0){
			int k=1;
			for(int i=0;i<appusractionsList.size();i++){
			ApplicantUserActions action = (ApplicantUserActions)appusractionsList.get(i);
			String actionurl = request.getContextPath()+"/applicantUserActions.do?method=editapplicantactions&actionid="+action.getAppuseractionId()+"&applicantId="+form.getApplicantId()+"&uuid="+form.getUuid();
		%>
		<tr>
		<td>
		#<%=k%>&nbsp;<a  href="#" onClick="javascript:editapplicantactions('<%=actionurl%>')"><%=Constant.getResourceStringValue(action.getActionName(),user1.getLocale())%> </a>&nbsp; &nbsp; 
		<%
			k++;
			}
		%>
		</td>
		</tr>
		<%
		}// end of appusractionsList  
		%>
		 <!-- tasks -->
		</table>
		<%}%>
		</span>


