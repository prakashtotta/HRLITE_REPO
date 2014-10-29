<%@ include file="../common/includejava.jsp" %>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(user1.getSuper_user_key());

String domainurl = "http://"+userregdata.getSubdomain()+"."+Constant.getValue("domain.name")+"/hr_lite/jobs.do?method=jobsearch&wsint=yes&l_code=en_US&subdomain="+userregdata.getSubdomain();

%>
<b>If you want to start collecting resumes through your Web site, just paste below codes into any Web page. </b><br>

<textarea rows="10" cols="80">

<iframe id="web-int-hires360" frameborder="0" height="" width ="" src="" scrolling="yes"></iframe> 
<script> 
document.getElementById("web-int-hires360").height = screen.height-50;
document.getElementById("web-int-hires360").width = screen.width-100;
document.getElementById("web-int-hires360").src = "<%=domainurl%>"; 
</script> 
</textarea> 