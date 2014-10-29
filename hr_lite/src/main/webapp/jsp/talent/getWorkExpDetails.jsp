<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
String datepattern = Constant.getValue("defaultdateformat");
User userAg = (User)request.getSession().getAttribute(Common.AGENCY_DATA);
User user = (User)request.getSession().getAttribute(Common.USER_DATA);
RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

com.bean.Locale locale= null;
if(userAg != null){

datepattern = DateUtil.getDatePatternFormat(userAg.getLocale());
locale = userAg.getLocale();
}else if(user != null){
	datepattern = DateUtil.getDatePatternFormat(user.getLocale());
    locale = user.getLocale();
}else if(userEmp != null){
	datepattern = DateUtil.getDatePatternFormat(userEmp.getLocale());
	locale = userEmp.getLocale();

}else if(appuser != null){
	datepattern = DateUtil.getDatePatternFormat(appuser.getLocale());
	locale = appuser.getLocale();

}else{
String refuserid = (String)session.getAttribute("refuserid");
if(refuserid != null && !refuserid.equals("null")&& !refuserid.equals("external-jobpost")){
RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
locale= refEmp.getLocale();
 }else{
	 String locale_code = (String)session.getAttribute("locale_code");
	 locale= new com.bean.Locale();
	locale.setLocaleCode(locale_code);
 }
}

%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />



<html:form action="/applicantoffer.do?method=getpreviousOrgdetails" >


<%@ include file="../talent/expDetailsInner.jsp" %>

</html:form>
</html:html>


