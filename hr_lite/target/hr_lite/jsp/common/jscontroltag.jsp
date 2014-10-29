<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>




<%
 
 
 
  


  %>

 <%@ taglib uri="/WEB-INF/jscontrolstags.tld" prefix="jscontrols" %>
<%@ taglib uri="/WEB-INF/jscontrolstags-ajax.tld" prefix="jscontrols-ajax" %>

<%@ page language="java" %>

<link type="text/css" rel="stylesheet" href="jsp/jscontroltag/jscontrolstags.css" ></link>
<link type="text/css" rel="stylesheet" href="jsp/jscontroltag/tree.css" ></link>

<script type="text/javascript" src="jsp/jscontroltag/scriptaculous/prototype.js"></script>



<script type="text/javascript" src="jsp/jscontroltag/scriptaculous/effects.js"></script>
<script type="text/javascript" src="jsp/jscontroltag/scriptaculous/dragdrop.js"></script>
<script type="text/javascript" src="jsp/jscontroltag/tafeltree/Tree.js"></script>
<script type="text/javascript" src="jsp/jscontroltag/jscontrolstags-treeview.js"></script>
<script type="text/javascript" src="jsp/jscontroltag/scriptaculous/controls.js"></script>
<script type="text/javascript" src="jsp/jscontroltag/jscontrolstags-autocomplete.js"></script>

		<style>
			.mover {
			background-color: #FF9999;
			color: #FFEEEE;
			}
			
			.special {
			color: #CC0000;
			}
			
			.tafelTree h3, .tafelTree p, .tafelTree ol {
			margin: 0;
			padding: 0;
			}
			
			.tafelTree p {
			padding-bottom: 1em;
			}
			
			.tafelTree h3 {
			color: #009900;
			}
		</style>

