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

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script type="text/javascript" src="jsp/jquery/jquery-1.7.1.js"></script>
<%@ include file="../common/jplot.jsp" %>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String type=request.getParameter("type");
String tmurl="jobreq.do?method=jobreqList&state=Active";
if(!StringUtils.isNullOrEmpty(type) && type.equals("admin")){
	tmurl = "jobreq.do?method=jobreqList&state=";
}
if(!StringUtils.isNullOrEmpty(type) && type.equals("recruiter")){
	tmurl = "jobreq.do?method=myjobreqListRecruiter&state=";
}
if(!StringUtils.isNullOrEmpty(type) && type.equals("hiringmgr")){
	tmurl = "jobreq.do?method=myjobreqList&state=";
}

String piechartdata = BOFactory.getTreeBO().getReqStatusPieChatData(user1.getUserId(),user1.getSuper_user_key(),type);
%>
<span id="offerdeclinedapplicants">
<% if(!StringUtils.isNullOrEmpty(piechartdata)){%>
<div id="info1"></div>
<div id="pie1" style="margin-top:5px; margin-left:5px; width:240px; height:240px;"></div>
<%}else{%>
<b> No Requisitions open </b>
<%}%>
</span>

<script type="text/javascript">
$(document).ready(function(){
	var plot1 = $.jqplot('pie1', [[<%=piechartdata%>]],
		
	{
        gridPadding: {top:0, bottom:38, left:0, right:0},
        seriesDefaults:{
            renderer:$.jqplot.PieRenderer, 
            trendline:{ show:false }, 
            rendererOptions: { padding: 8, showDataLabels: true,dataLabels: 'value'  }
			
        },
        legend:{
            show:true, 
            placement: 'outside', 
            rendererOptions: {
                numberRows: 1
            }, 
            location:'s',
            marginTop: '15px'
        }       
    });

	 $('#pie1').bind('jqplotDataClick', 
            function (ev, seriesIndex, pointIndex, data) {
                //$('#info1').html('Total Count :'+data[0,0]);
				location.href="<%=tmurl%>"+data[0,0];
            }
        );

previousPoint = null;
$('#pie1').bind('jqplotDataMouseOver', function (ev, seriesIndex, pointIndex, data) { 
    var labels = $('#pie1 .jqplot-data-label');
    if (previousPoint != null)
    {
       labels[previousPoint['idx']].innerHTML = previousPoint['data'][1]+'';               
    }
    labels[pointIndex].innerHTML = data[0] + ": " + data[1];
    previousPoint = {'idx':pointIndex, 'data':data};
}); 



});</script>