<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.criteria.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.resources.Constant"%>



<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "catId"; // default don't sort
String dir = "asc"; // default sort dir is asc




String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");




String name = (String)request.getParameter("name");
String variabletype = (String)request.getParameter("variabletype");
String variablename = (String)request.getParameter("variablename");
System.out.println("variableName : "+variablename);

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
int totalSize = 0;


 dataList = BOFactory.getBusinessRuleBO().searchFilters(user1,name,variabletype,variablename,user1.getSuper_user_key(),results,startIndex,dir_str,sort_str);
 totalSize= BOFactory.getBusinessRuleBO().countSearchFilters(name,variabletype,variablename,user1.getSuper_user_key());

List newFilterList = new ArrayList();
		for(int i=0;i<dataList.size();i++){
			BusinessFilterConditions fc = (BusinessFilterConditions)dataList.get(i);
			
			String resourcestr="";
			if(fc.getVariableOrigin()!= null && fc.getVariableOrigin().equals(Common.FILTER_VARIABLE_ORIGIN_CUSTOM)){
				resourcestr = fc.getVariableName();
			}else{
				resourcestr="aquisition.applicant."+fc.getVariableName();
			}
			
			String value = Constant.getResourceStringValue(resourcestr,user1.getLocale());
			if(value == null || value.length() ==0){
				value = fc.getVariableName();
			}
			
			fc.setVariableName(value);
			
			newFilterList.add(fc);
		}

System.out.println(totalSize);
String[] fields = 

{"filterConditionId","name","variableName","variableType","variableOrigin","filterCriteria","filterValue1","filterValue2","filterValueDate1","filterValueDate2","valueName"};

String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+newFilterList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTableWithoutTimeZoneConvert(newFilterList,fields,user1)+
				 "}";
                
System.out.println(data);
%>

<%=data%>
