	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

	<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<%@ page import="com.common.*"%>
	<%@ page import="com.util.*"%>
	<%@ page import="com.bo.*"%>
	<%@ page import="network.common.*"%>
	<%@ page import="network.bean.*"%>
	<%@ page import="network.util.*"%>
	<%@ page import="java.util.*"%>
	
	<script type="text/javascript" src="talentnetwork/scripts/jquery_minimized_core.js"></script>
	<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="talentnetwork/scripts/date.format.js"></script>
		 <script src="http://connect.facebook.net/en_US/all.js"> </script>
		 <link rel='stylesheet' type='text/css' href='talentnetwork/css/style.css' />

<%
FaceBookUser fuser= (FaceBookUser)session.getAttribute(FbCommon.FB_USER_DATA);

FaceBookReader freader= new FaceBookReader();
%>