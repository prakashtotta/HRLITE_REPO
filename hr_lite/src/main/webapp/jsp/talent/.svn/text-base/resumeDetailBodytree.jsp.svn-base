<%@ include file="../common/include.jsp" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />
<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

</head>


<%
System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^77777777777777777777777777777777777777777777777777777");
%>

<body>
 <%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%>
 <br>
<table>
 <tr>
			<td width="150" class="bodytext"> <%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%>
 </td>
			<td class="bodytext"><bean:write name="applicantForm" property="fullName"/></td>
		</tr>
		<%
        String tempurl1 = "";
       if(form.getJobTitle() != null){  
   tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(form.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+form.getJobTitle()+"</a>";
	   //   tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreq&jobreqId="+form.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+form.getJobTitle()+"</a>";

	   }
		%>
        <tr>
			<td width="150" class="bodytext"> <%=Constant.getResourceStringValue("aquisition.applicant.appliedfor",user1.getLocale())%>
</td>
			<td class="bodytext"><%=tempurl1%></td>
		</tr>

</table>
<br><br>

<ul id="countrytabs" class="shadetabs">
<li><a href="applicant.do?method=applicantDetailstree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>">
<%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=resumeDetailstree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer" class="selected"> <%=Constant.getResourceStringValue("aquisition.applicant.Resume",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicantlogdetailstree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Interviewlog",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicantofferdetailstree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.Offerdetails",user1.getLocale())%></a></li>
<li><a href="applicant.do?method=applicantReferenceDetailstree&applicantId=<%=form.getApplicantId()%>&secureid=<%=form.getUuid()%>" rel="countrycontainer"><%=Constant.getResourceStringValue("aquisition.applicant.References",user1.getLocale())%></a></li>

<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:700px; margin-bottom: 1em; padding: 10px">
<%=(form.getResumedetails() == null)? "Details not added.":StringUtils.doSpecialCharacters(form.getResumedetails())%>
</div>

		
	
</body>
</html>

