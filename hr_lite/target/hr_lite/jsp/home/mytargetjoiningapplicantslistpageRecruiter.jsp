
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.resources.*"%>

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
String interviewstate = (String)request.getParameter("interviewstate");


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
String noofdaysoffset = Constant.getValue("dashboard.noofdays.offset.config");

DataTableBean dtbean = BOFactory.getApplicantBO().getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(user1, interviewstate,new Integer(noofdaysoffset).intValue(),results,startIndex,dir_str,sort_str);

/*String[] fields = {"applicantId","fullName","interviewState","targetjoiningdateStr","reqId","jobTitle","uuid","initiateJoiningProcess","offerownerId","offerownerName","isofferownerGroup"};*/

String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage(Common.DASHBOARD_RECRUITER_IN_JOINED_PROCESS);


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
