<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.criteria.*"%>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "jobgradeId"; // default don't sort
String dir = "asc"; // default sort dir is asc




String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

System.out.println("results_str"+results_str);
System.out.println("startIndex_str"+startIndex_str);
System.out.println("sort_str"+sort_str);
System.out.println("dir_str"+dir_str);


String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String talentpoolname = (String)request.getParameter("talentpoolname");
String emailid = (String)request.getParameter("emailid");
String smtpserver = (String)request.getParameter("smtpserver");
String smtpuser = (String)request.getParameter("smtpuser");
String isgroup = (String)request.getParameter("isgroup");
String orgId = (String)request.getParameter("orgId");
String description = (String)request.getParameter("description");

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



	

List dataList = new ArrayList();
List dataListsize = new ArrayList();
int totalSize = 0;


	dataList=TalentPoolBO.getAllTalentpoolForPaginationBySearchCriteria(user1,talentpoolname,emailid,smtpserver,smtpuser,isgroup,description,orgId,results,startIndex,dir_str,sort_str);
    totalSize= TalentPoolBO.getCountOfTalentpoolBySearchCriteria(user1,talentpoolname,emailid,smtpserver,smtpuser,isgroup,description,orgId);
  





System.out.println(totalSize);
String[] fields = 

{"talentPoolId","talentPoolName","talentPoolDesc","talentPoolemail","orgName","isGroup","assigntouserName"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dataList,fields,user1)+
				 "}";
                
System.out.println(data);
%>

<%=data%>
