<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<bean:define id="form1" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<jsp:include page="../refferal/template.jsp" flush="true">
<jsp:param name="htmlbody" value="../job/jobdetailsBody.jsp" />
<jsp:param name="title" value="<%=form1.getJobTitle()%>"/>
<jsp:param name="diplayLeftNav" value="false" />
</jsp:include>