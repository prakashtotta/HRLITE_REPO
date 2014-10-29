<%@ include file="../common/include.jsp" %>

    	
<a href="<%=request.getContextPath()%>/reports/pdf?reportfilename=test.jasper" target="new">test report</a> <br>
<a href="<%=request.getContextPath()%>/reports/pdf?reportfilename=Interview_status_Report.jasper" target="new">Interview_status_Report.jasper</a> <br>
<a href="<%=request.getContextPath()%>/jsp/report/viewer.jsp?reportfilename=Interview_status_Report.jasper">test html</a> <br>

<a href="<%=request.getContextPath()%>/jsp/report/viewer.jsp?reportfilename=requistion_opening_stat.jasper">Requisition opening statistics..</a> <br>
<a href="<%=request.getContextPath()%>/reports.do?method=requisitionstatfirstpage">Requisition opening statistics</a> <br>
<a href="<%=request.getContextPath()%>/reports.do?method=applicantOfferedJoinedStatByOrg">applicant Offered Joined Stat By Org</a> <br>
<a href="<%=request.getContextPath()%>/reports.do?method=applicantOfferedJoinedStatByReq">applicant Offered Joined Stat By Requisition</a> <br>