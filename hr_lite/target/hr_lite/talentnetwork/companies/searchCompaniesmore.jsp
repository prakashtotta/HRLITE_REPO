<%@ include file="../header.jsp" %>
<%
String searchquery = (String)request.getParameter("query");
String lastmsg = (String)request.getParameter("lastmsg");
System.out.println(searchquery+lastmsg);
List employerlist = BOFactory.getFbUserBO().searchCompanies(searchquery, 15, new Integer(lastmsg).intValue()+15);
int total_result_size=employerlist.size();
%>
    
		
					
<table width="900px">
					
        
		<%  
		int jj=0;
			if(employerlist != null){
		%>
		
		<%
			
			for(int i=0;i<employerlist.size();i++){
				FbEmployer employer = (FbEmployer)employerlist.get(i);
				String trvalue="";
				String trvalueclose="";
				if((jj%3)==0){
				trvalue="</tr><tr>";
				
				}


		%>
		<%=trvalue%>
		<td width="300px" height="200px" bgcolor="#E8E8E8" valign="top">
		
      <img src="//graph.facebook.com/<%=employer.getFacebookid()%>/picture" border="0"/></a><br>
      <a href="companies.do?method=getemployees&fid=<%=employer.getFacebookid()%>" target="new"><font size="2"><%=employer.getName()%></font></a>
	  <br>
	  <script language="javascript">
		getTotalCountOfEmployeeByEmployer('<%=employer.getFacebookid()%>');
		</script>
		<span id="totalcount_<%=employer.getFacebookid()%>"></span>
		
	  </td>
		<%
		jj++;	
		}%>
		</tr>
		<%}%>
		
	  </table>
	  
<%
if(total_result_size==15){
%>
 	   <div id="more">
    <a  id="<%=new Integer(lastmsg).intValue()+15%>" class="load_more" href="#">more</a>  </div>
 



<% }else {%>
    
 <div id="more">   <a  id="end" class="load_more" href="#">No More Results </a>  </div>
    
 <%}%>