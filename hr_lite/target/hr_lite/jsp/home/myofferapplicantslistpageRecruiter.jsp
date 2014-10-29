
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
 


 


  %>
    <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>

<%


int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");



if(startIndex_str != null && startIndex_str.length() > 0){
  startIndex = Integer.parseInt(startIndex_str);
}
if(sort_str != null && sort_str.length() > 0){
  sort = sort_str;
}
if(dir_str != null && dir_str.length() > 0){
  dir = dir_str;
}
if(results_str != null && results_str.length() > 0){
  results = Integer.parseInt(results_str);
}

DataTableBean dtbean  = BOFactory.getApplicantBO().getApplicantsInOfferProcessByRecruiter(user1,results,startIndex,dir_str,sort_str);


/*String[] fields = {"applicantId","fullName","interviewState","offerreleasedate","reqId","jobTitle","uuid","offerownerId","offerownerName","isofferownerGroup"};*/

String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage(Common.DASHBOARD_RECRUITER_IN_OFFER_PROCESS);

System.out.println("dtbean.getTotalcount()"+dtbean.getTotalcount());

String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dtbean.getValueList().size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+dtbean.getTotalcount()+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dtbean.getValueList(),fields,user1)+
				 "}";
                

%>

<%=data%>
