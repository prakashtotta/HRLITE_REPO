<%@ include file="../common/include.jsp" %>
<%
System.out.println(" >>>>>>>  resumeDetailsajax.jsp .... refferal");
String path = request.getContextPath()+request.getAttribute("filePath");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String content = Constant.getResourceStringValue("aquisition.applicant.Resume.no.added",user1.getLocale());
%>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<%
if(!StringUtils.isNullOrEmpty(Constant.getValue("scribd.enabled")) && Constant.getValue("scribd.enabled").equalsIgnoreCase("yes")){

	if(form.getScribddocumentid()>0){
%>

<script type='text/javascript' src='http://www.scribd.com/javascripts/view.js'></script>

<div id='embedded_flash'><a href="http://www.scribd.com">Scribd</a></div>

<script type="text/javascript">
var scribd_doc = scribd.Document.getDoc( <%=form.getScribddocumentid()%>, '<%=form.getScribddocumentkey()%>' );

var oniPaperReady = function(e){
// scribd_doc.api.setPage(3);
}

scribd_doc.addParam( 'jsapi_version', 1 );
scribd_doc.addEventListener( 'iPaperReady', oniPaperReady );
scribd_doc.write( 'embedded_flash' );
</script>

<%  }else{%>
<br>
<%
if(form.getResumedetails() != null && form.getResumedetails().length()>0){
	content = StringUtils.doSpecialCharacters(form.getResumedetails());
}

%>
<br>
<%=content%>

<%	}}else{%>

<%
if(form.getResumedetails() != null && form.getResumedetails().length()>0){
	content = StringUtils.doSpecialCharacters(form.getResumedetails());
}

%>
<br>
<%=content%>
<%}%>

		


