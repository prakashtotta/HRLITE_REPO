<%@ include file="../header.jsp" %>
<%
String searchquery = (String)request.getParameter("query");
String lastmsg = (String)request.getParameter("lastmsg");
System.out.println(searchquery+lastmsg);
List  joblist = BOFactory.getFbUserBO().searchFbJobs(searchquery, 10, new Integer(lastmsg).intValue()+10);
int total_result_size=joblist.size();
%>
    
		
					
                		<table width="900px">
					
        
		<%  
		int jj=0;
			if(joblist != null){
		%>
		
		<%
			
			for(int i=0;i<joblist.size();i++){
				FbJobs job = (FbJobs)joblist.get(i);
				String trvalue="";
				String trvalueclose="";
				if((jj%2)==0){
				trvalue="</tr><tr>";
				
				}


		%>
		<%=trvalue%>
		<td width="450px" height="150px" bgcolor="#E8E8E8" valign="top">
		<a href="#" onClick="openjob('fbjobs.do?method=jobdetails&id=<%=job.getJobId()%>')"><span style="color: blue;"><%=job.getJobTitle()%></span></a><br>
		<font size="1"><%=job.getHeadline()%>   <span style="font-size: 12px; background-color: #FF99FF;"><%=job.getCompanyName()%> </span><br>
		<span style="font-size: 12px; background-color: #CCFFCC;"><%=job.getCity()%></span>   <span style="font-size: 12px; background-color: #CCFFCC;"><%=job.getExperience()%></span> <span style="font-size: 12px; background-color: #CCFFCC;"><%=job.getJobcategory()%></span> <span style="font-size: 12px; background-color: #CCFFCC;"><%=job.getTenure()%></span> <br>
	    
		<script language="javascript">
		isSavedJob('<%=job.getJobId()%>');
		</script>
		<span id="job_<%=job.getJobId()%>"></span>
	  </td>
		<%
		jj++;	
		}%>
		</tr>
		<%}%>
		
	  </table>
	  
<%
if(total_result_size==10){
%>
 	   <div id="more">
    <a  id="<%=new Integer(lastmsg).intValue()+15%>" class="load_more" href="#">more</a>  </div>
 



<% }else {%>
    
 <div id="more">   <a  id="end" class="load_more" href="#">No More Results </a>  </div>
    
 <%}%>