<%@ include file="../header.jsp" %>
<%
String id=(String)request.getParameter("id");
FbDTO fdto = BOFactory.getFbUserBO().getSavedJobInfo(new Long(id).longValue(),fuser.getUserId());

%>

<span id="job_<%=id%>">
<%if(!fdto.isSaved()){%>
		<span id="issaved_<%=id%>">
<a href="#" onClick="saveJob(<%=id%>)"><img src="talentnetwork/images/savecompany.gif"></a>
		</span>
        <%}%>
		
	
		
</span>