<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />


<style>
span1{color:#ff0000;}
</style>
<script language="javascript">
function opensearchorg(){
  window.open("org.do?method=orgselector&readPreview=3&orgId=1", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchjobreq(){
		var tu = "lov.do?method=requisitionselector&frompage=bulkuploadresume";
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
    window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");



}




function bulkupload(){

      var alertstr = "";
    var reqid1 = document.applicantForm.reqid1.value.trim();
     
	var zipfilename = document.applicantForm.resumeData.value.trim();
	
		var showalert=false;

	if(reqid1 == 0 || reqid1 == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.Job_Requisition_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
if(zipfilename == "" || zipfilename == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.Bulkupload.alert",user1.getLocale())%><BR>";
		showalert = true;
		}
	

	 if (showalert){
     	alert(alertstr);
        return false;
          }

		  var FileExt =document.applicantForm.resumeData.value.lastIndexOf(".zip")
	if(FileExt==-1) {    
		alert("<%=Constant.getResourceStringValue("aquisition.Bulkupload.alert1",user1.getLocale())%>");    
		return false;
	}


          document.getElementById('progressbartable1').style.display = ''; 
		  document.applicantForm.action = "applicant.do?method=bulkresumeupload";
	  document.applicantForm.submit();
	}

	function init(){
       
	  document.getElementById('progressbartable1').style.display = 'none';   
		}
</script>
<b><%=Constant.getResourceStringValue("aquisition.Bulkupload.bulkupload",user1.getLocale())%></b><br>
<%=Constant.getResourceStringValue("aquisition.Bulkupload.zipfile",user1.getLocale())%>

<%
String resumeparsed = (String)request.getAttribute("resumeparsed");
List successFilelist = (List)request.getAttribute("successFilelist");
List failFilelist = (List)request.getAttribute("failFilelist");
if(resumeparsed != null && resumeparsed.equals("yes")){
%>
<br><div class="msg"><font color="white"><b><%=Constant.getResourceStringValue("aquisition.Bulkupload.Upload_started",user1.getLocale())%></font>&nbsp;<a href="<%=request.getContextPath()%>/bulkuploadtask.do?method=mybulktasklist"><font color="white">my jobs</a></b></font></div><br>

<% if(successFilelist != null){%>
<%=Constant.getResourceStringValue("aquisition.Bulkupload.Files_successfully_uploaded",user1.getLocale())%>
<%	for(int i=0;i<successFilelist.size();i++){
      String fnamesu = (String)successFilelist.get(i);
	%>
<font color="green"><b>* <%=fnamesu + " * "%></b></font>
<%}}%>
<br>

<% if(failFilelist != null){%>
<%=Constant.getResourceStringValue("aquisition.Bulkupload.Files_failed_to_upload",user1.getLocale())%>
<%	for(int i=0;i<failFilelist.size();i++){
      String fnamesu1 = (String)failFilelist.get(i);
	%>
<font color="red"><b>* <%=fnamesu1 + " * "%></b></font>
<%}}%>
<br>
<%}%>
<table id='progressbartable1'>
<tr>
    <td></td>
    <td  style="vertical-align:middle"><img src="jsp/images/loading.gif"></td>
    <td></td>
</tr>
</table>
<body onLoad="init()" class="yui-skin-sam">

<html:form action="/applicant.do?method=bulkresumeupload" enctype="multipart/form-data">

<font color = red ><html:errors /> </font>
<br>
<table border="0" cellpadding="0" cellspacing="0" class="div">

		<tr>
				<td><%=Constant.getResourceStringValue("aquisition.Bulkupload.Job_Requistion",user1.getLocale())%><span1>*</span1></td>


				<td><input type="hidden" name="reqid1" value="">
				<span id="requitionId"></span><a href="#" onClick="opensearchjobreq()"><img src="jsp/images/selector.gif" border="0"/></a>
				<html:hidden  property="requitionId"/>
				
				</td>
				
				
		</tr>

<tr>
				<td width="200"></td>
				<td>
				
				</td>
			</tr>

       <tr>
			<td><br><%=Constant.getResourceStringValue("aquisition.Bulkupload.Zip_file",user1.getLocale())%><span1>*</span1></td>
			<td>
			<br><html:file property="resumeData"/> 
			</td>
		</tr>

			<tr>
				<td width="200"></td>
				<td>
				
				</td>
			</tr>
			

<tr>
				<td width="200">
			<br><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.upload",user1.getLocale())%>" onClick="bulkupload()" class="button"></td>
				<td>
				
				</td>
			</tr>
			

</table>
</html:form>
</body>


    	
