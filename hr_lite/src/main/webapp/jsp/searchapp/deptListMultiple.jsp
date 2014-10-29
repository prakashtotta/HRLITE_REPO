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
<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<html:form action="/applicant.do?method=logon" >

			<td>
			<span id="departments">
			<html:select property="departmentIds" styleClass="multipleselect" multiple="true" size="3" onchange="retrieveProjcodes('applicant.do?method=loadProjectCodeMultiple');">
			<bean:define name="applicantForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>			
			</span>
			</td>
</html:form>
</html:html>


