
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


String employeecode = (String)request.getParameter("employeecode");
String employeename = (String)request.getParameter("employeename");
String employeeemail = (String)request.getParameter("employeeemail");
String applicantName = (String)request.getParameter("applicantName");
String requitionId = (String)request.getParameter("requitionId");
String cri = (String)request.getParameter("cri");
String refBudgetId = (String)request.getParameter("refBudgetId");
String refferalSchemeId = (String)request.getParameter("refferalSchemeId");
String refferalSchemeTypeId = (String)request.getParameter("refferalSchemeTypeId");
String ruleId = (String)request.getParameter("ruleId");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String applicantNo = (String)request.getParameter("applicantNo");
String uom = (String)request.getParameter("uom");
String state = (String)request.getParameter("state");
String isPaid = (String)request.getParameter("isPaid");
String releasedate = (String)request.getParameter("releasedate");


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




int totalSize = 0;


List dataList = new ArrayList();


if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
dataList = RefBO.searchReferralRedemptions(user1,employeecode,employeename,employeeemail,applicantNo,applicantName,requitionId,refBudgetId,refferalSchemeId,refferalSchemeTypeId,ruleId,releasedate,cri,uom,state,isPaid,results,startIndex,dir_str,sort_str);
totalSize = RefBO.getCountOfsearchReferralRedemptions(user1,employeecode,employeename,employeeemail,applicantNo,applicantName,requitionId,refBudgetId,refferalSchemeId,refferalSchemeTypeId,ruleId,releasedate,cri,uom,state,isPaid);
}else{

	dataList = RefBO.getAllEmployeeReferralRedemptions(user1,results,startIndex,dir_str,sort_str);
	totalSize = RefBO.getCountOfAllEmployeeReferralRedemptions(user1);

}
	

System.out.println(totalSize);
String[] fields = {"refredid","applicantName","JobTitle","refferalSchemeName","refferalSchemeAmount","uom","refferalSchemeTypeName","ruleName","state","applicantstate","eventdate","releaseddate","isPaid","uuid","uom","employeecode","employeename","employeeemail","reqName","applicant_number","requisition_number"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+YahooUtil.createJasonDataTableWithOnlyDate(dataList,fields,user1)+
				 "}";
                

%>

<%=data%>
