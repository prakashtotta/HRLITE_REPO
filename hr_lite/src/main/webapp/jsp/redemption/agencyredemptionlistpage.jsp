
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


String agencyId = (String)request.getParameter("agencyId");
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
String orgId = (String)request.getParameter("orgId");
String departmentId = (String)request.getParameter("departmentId");


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
dataList = RefBO.searchAgencyRedemptions(user1,agencyId,applicantNo,applicantName,requitionId,refBudgetId,refferalSchemeId,refferalSchemeTypeId,ruleId,releasedate,cri,uom,state,isPaid,orgId,departmentId,results,startIndex,dir_str,sort_str);
totalSize = RefBO.getCountOfsearchAgencyRedemptions(user1,agencyId,applicantNo,applicantName,requitionId,refBudgetId,refferalSchemeId,refferalSchemeTypeId,ruleId,releasedate,cri,uom,state,isPaid,orgId,departmentId);
}else{

	dataList = RefBO.getAllAgencyRedemptions(results,startIndex,dir_str,sort_str);
	totalSize = RefBO.getCountOfAllAgencyRedemptions();

}
	

System.out.println(totalSize);
String[] fields = {"agredid","applicantName","JobTitle","refferalSchemeName","refferalSchemeAmount","uom","refferalSchemeTypeName","ruleName","state","applicantstate","eventdate","releaseddate","isPaid","uuid","uom","agencyname","reqName"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+YahooUtil.createJasonDataTableWithOnlyDate(dataList,fields,user1)+
				 "}";
                
System.out.println(data);
%>

<%=data%>
