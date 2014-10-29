<%@ include file="../header.jsp" %>
<%
String searchquery = (String)request.getParameter("query");
String lastmsg = (String)request.getParameter("lastmsg");
System.out.println(searchquery+lastmsg);
List usersList = BOFactory.getFbUserBO().searchFbUsers(searchquery, 25, new Integer(lastmsg).intValue()+25);
int total_result_size=usersList.size();
%>
    
		
					
        <table width="900px">
		<%  
		int jj=0;
			if(usersList != null){
		%>
		
		<%
			
			for(int i=0;i<usersList.size();i++){
				FaceBookUser fbuseren = (FaceBookUser)usersList.get(i);
				String trvalue="";
				String trvalueclose="";
				if((jj%5)==0){
				trvalue="</tr><tr>";
				
				}


		%>
		<%=trvalue%>
		<td bgcolor="#D8D8D8" width="190px" valign="top">
		
      <a href="<%=fbuseren.getLink()%>" target="new"><img src="//graph.facebook.com/<%=fbuseren.getFacebookid()%>/picture" border="0"/></a><br>
	  <font size="2" color="#3366CC"><%=StringUtils.isNullOrEmpty(fbuseren.getTopLine())?"":fbuseren.getTopLine()%></font>
	  <br>
      <a href="p/<%=fbuseren.getFuserName()%>" target="new"><font size="2"><%=fbuseren.getFullName()%></font></a><br>
	  <% if(!StringUtils.isNullOrEmpty(fbuseren.getEmailId())){%>
	  <input type="checkbox" name="endusers" value="<%=fbuseren.getFacebookid()%>" />
	  <%}else{%>
	  <!--<input type="checkbox" disabled="true" name="endusers" value="<%=fbuseren.getFacebookid()%>" />-->
	  <%}%>
	  
	  </td>
		<%
		jj++;	
		}%>
		</tr>
		<%}%>
	</table>	
	  
<%
if(total_result_size==25){
%>
 	   <div id="more">
    <a  id="<%=new Integer(lastmsg).intValue()+25%>" class="load_more" href="#">more</a>  </div>
 



<% }else {%>
    
 <div id="more">   <a  id="end" class="load_more" href="#">No More Results </a>  </div>
    
 <%}%>