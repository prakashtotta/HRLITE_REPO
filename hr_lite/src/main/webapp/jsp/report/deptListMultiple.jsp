<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>





<%
 
 
 



  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
List deptList = (List)request.getAttribute("deptList");
%>

<span id="departments">

<% if(deptList != null && deptList.size()>0){
%>
	<select name="departmentIds" multiple="multiple" size="3" class="multilistsmall">
	<%
		for(int i=0;i<deptList.size();i++){
	    Department dept = (Department)deptList.get(i);
	%>
      <option value="<%=dept.getDepartmentId()%>"><%=dept.getDepartmentName()%></option> 

<%}%>
</select>
<%}%>
			
</span>




