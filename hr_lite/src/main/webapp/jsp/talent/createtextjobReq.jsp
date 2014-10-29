<%@ include file="../common/include.jsp" %>
<html:html>

<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>


<html:form action="/jobtemplate.do?method=logon" >

	       <span id="isnewposition">
					 <input  type='text' size="30" name='isnewpositionno' maxlength="200" value="<%=(jobreqform.getIsnewPositions()!= null? "" :jobreqform.getIsnewpositionno())%>">
		   </span>


</html:form>
</html:html>