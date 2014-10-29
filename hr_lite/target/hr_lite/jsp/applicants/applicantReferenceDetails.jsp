<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<script language="javascript">
function addreferencedata1(url){

	//GB_showCenter('References',url,'height=400','width=500', messageret);

	newwindow=window.open(url,'Add References','height=400,width=500');
	if (window.focus) {newwindow.focus()}
	return false;
}
function messageret(){
	window.location.reload();

			}
function editreference(url){
	//parent.setPopTitle1("Edit reference");
	//parent.showPopWin(aid, 600, 500, null,true);
	//GB_showCenter('<%=Constant.getResourceStringValue("aquisition.applicant.Edit_refrerence",user1.getLocale())%>', url,500,600, messageret);
	newwindow=window.open(url,'Edit References','height=400,width=500');
	if (window.focus) {newwindow.focus()}
	return false;
}
</script>
<%@ include file="referncedetailscommon.jsp" %>
<table>
<tr>
<td>
<%
String addrefurl = request.getContextPath()+"/reference.do?method=addreferencescreen&applicantId="+form.getApplicantId();
%>
<div class="div">
<a class="button" href="#" onClick="javascript:addreferencedata1('<%=addrefurl%>')">
<span><%=Constant.getResourceStringValue("applicant.Refrences.add_references",user.getLocale())%></span></a> </div>
</td>
</tr>
</table>