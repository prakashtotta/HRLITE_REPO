<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String action = request.getParameter("action");
String ownerEl = request.getParameter("ownerEl");

System.out.println("ownerEl satyaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ownerEl);


String content = "";
if(action != null && action.equals("getElementList")){
	if(ownerEl == null){
		//content = TalentPoolBO.getTalentPoolElementsByOwnerId(0);
	}else{
	content = BOFactory.getTreeBO().getApplicantsTree(ownerEl);
	}
}
if(action != null && action.equals("insertElement")){

String name = request.getParameter("name");
String slave = request.getParameter("slave");
if(ownerEl != null){
ownerEl = ownerEl.replaceAll("id=", "");
ownerEl = ownerEl.replaceAll("'", "");
}
if(ownerEl != null && name != null && slave != null){
System.out.println("ownerElownerElownerElownerElownerElownerEl"+ownerEl);
	if(new Long(ownerEl).longValue() == 0){
		content = "-1";
		
	}else{
	content = TalentPoolBO.insertTalentPoolElement(ownerEl,name,slave);
	}
}

	
}
if(action != null && action.equals("updateElementName")){
	String name = request.getParameter("name");
    String elementId = request.getParameter("elementId");
    if(ownerEl != null){
	ownerEl = ownerEl.replaceAll("id=", "");
	ownerEl = ownerEl.replaceAll("'", "");
	}
	if(ownerEl != null && name != null && elementId != null){

	 if(new Long(ownerEl).longValue() == 0){
		content = "-1";
		//content = TalentPoolBO.updateTalentPoolElement(name,elementId,ownerEl);
	}else{
	content = TalentPoolBO.updateTalentPoolElement(name,elementId,ownerEl);
	}
	}
}
if(action != null && action.equals("deleteElement")){
	String elementId = request.getParameter("elementId");
if(ownerEl != null){
ownerEl = ownerEl.replaceAll("id=", "");
ownerEl = ownerEl.replaceAll("'", "");
}
System.out.println(elementId);
System.out.println(ownerEl);
if(ownerEl != null && elementId != null){
	if(new Long(ownerEl).longValue() == 0){
		content = "-1";
	}else{
	content = TalentPoolBO.deleteTalentPoolElements(elementId,ownerEl);
	}
}

	
}
if(action != null && action.equals("changeOrder")){

String elementId = request.getParameter("elementId");
String destOwnerEl = request.getParameter("destOwnerEl");
String position = request.getParameter("position");
String oldOwnerEl = request.getParameter("oldOwnerEl");


 oldOwnerEl = oldOwnerEl.substring(oldOwnerEl.indexOf("_")+1);
  destOwnerEl = destOwnerEl.substring(destOwnerEl.indexOf("_")+1);

  System.out.println(elementId);
System.out.println(destOwnerEl);
System.out.println(position);
System.out.println(oldOwnerEl);

if(elementId != null && destOwnerEl != null  && oldOwnerEl != null && !destOwnerEl.equals("0")){
//content = TalentPoolBO.changeOrderTalentPoolElement(elementId,oldOwnerEl,destOwnerEl,position);
BOFactory.getApplicantTXBO().moveApplicantFromFolderToFolder(user1,elementId,oldOwnerEl,destOwnerEl);
}

	
}

%>

<%=content%>

