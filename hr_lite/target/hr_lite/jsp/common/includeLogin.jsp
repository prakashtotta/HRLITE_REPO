<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.criteria.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>




<%@ include file="../common/cache.jsp" %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<link rel="stylesheet" type="text/css" href="jsp/css/style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/button.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/tooltip.css" />




	<link rel="StyleSheet" type="text/css" href="jsp/css/alert.css" type="text/css" />
	
	<script type="text/javascript" src="jsp/js/ta.js"></script>
	<script type="text/javascript" src="jsp/js/CalendarPopup.js"></script>

	<link rel="StyleSheet" type="text/css" href="jsp/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="jsp/js/customAlertBox.js"></script>
		<script type="text/javascript" src="jsp/dtree/dtree.js"></script>


	



<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/autocomplete/assets/skins/sam/autocomplete.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/container/assets/skins/sam/container.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/tabview/assets/skins/sam/tabview.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/menu/assets/skins/sam/menu.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/editor/assets/skins/sam/editor.css" />


<script type="text/javascript" src="jsp/yahoo/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/connection/connection-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/json/json-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/element/element-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/animation/animation-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/button/button-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/container/container-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/menu/menu-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/editor/editor-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/swf/swf-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/charts/charts-min.js"></script>




<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/autocomplete/assets/skins/sam/autocomplete.css" />


<script type="text/javascript" src="jsp/yahoo/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/connection/connection-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/json/json-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/element/element-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/animation/animation-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/swf/swf-min.js"></script>
<script type="text/javascript" src="jsp/yahoo/build/charts/charts-min.js"></script>



<%@ include file="../common/csrfInjection.jsp" %>

	<link rel="stylesheet" type="text/css" href="jsp/jquery/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="jsp/jquery/themes/base/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/uirevamp.css" />














