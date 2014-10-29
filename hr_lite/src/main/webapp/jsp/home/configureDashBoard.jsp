<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<link rel="stylesheet" type="text/css" href="jsp/style.css" />

<bean:define id="cform" name="dashboardForm" type="com.form.DashboardForm" />


<script language="javascript">
 
function closewindow(){
	 	   parent.parent.GB_hide(); 

	  
	}

function closewindow2(){
	//self.parent.location.reload();
	 	      parent.parent.GB_hide(); 

	  
	}	


	function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  //self.parent.location.reload();
	   //window.top.hidePopWin();
	   parent.parent.GB_hide(); 
	   } 
	}

function savedata(){
	
   document.dashboardForm.action = "dashboard.do?method=saveconfigureDashBoard";
   document.dashboardForm.submit();
	}


</script>

<%
String isupdated = (String)request.getAttribute("isupdated");
	
if(isupdated != null && isupdated.equals("yes")){
%>
<div align="center" class="button">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("dashboard.updated.successfully",user1.getLocale())%></font></td>
			
		</tr>
		
	</table>
</div>

<%}%>



<body>
<html:form action="/dashboard.do?method=configureDashBoard" >

<div align="center">
	
<table>
<tr>
<td>
<div class="shopping_cart">
        	<div class="cart_title"><%=Constant.getResourceStringValue("filled.active.requisitions",user1.getLocale())%></div>
            
            <% 
		   String value = (String)cform.getWidgetMap().get("FILLED_ACTIVE_REQ");
		   String checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="FILLED_ACTIVE_REQ" <%=checked%>>
           
          
        
        </div>
</td>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Active requisitions</div>
            
           <% 
		    value = (String)cform.getWidgetMap().get("MY_ACTIVE_REQ");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" disabled="disabled" name="MY_ACTIVE_REQ" <%=checked%>>
           
          
        
        </div>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Requisitions Slipping target</div>
            
           
           <% 
		    value = (String)cform.getWidgetMap().get("REQ_SLIPING_TARGET");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="REQ_SLIPING_TARGET" <%=checked%>>
           
          
        
        </div>

</td>
</tr>
<tr>
<td>
<div class="shopping_cart">
        	<div class="cart_title">On board applicants ( <%=Constant.getValue("dashboard.noofdays.offset.config")%> days)</div>
            
           <% 
		    value = (String)cform.getWidgetMap().get("ONBOARDING_APPLICANTS");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="ONBOARDING_APPLICANTS" <%=checked%>>
           
          
        
        </div>
</td>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Applicants in offer process</div>
            
           
           <% 
		    value = (String)cform.getWidgetMap().get("OFFER_IN_PROCESS");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="OFFER_IN_PROCESS" <%=checked%>>
           
          
        
        </div>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Open Requisitions state</div>
            
           
           <% 
		    value = (String)cform.getWidgetMap().get("OFFER_DECLINED");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="OFFER_DECLINED" <%=checked%>>
           
          
        
        </div>

</td>
</tr>
<tr>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Important links</div>
            
           
           <% 
		    value = (String)cform.getWidgetMap().get("IMP_LINKS");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="IMP_LINKS" <%=checked%>>
           
          
        
        </div>
</td>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Offer accepted applicants</div>
            
           
           <% 
		    value = (String)cform.getWidgetMap().get("OFFER_ACCEPTED");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="OFFER_ACCEPTED" <%=checked%>>
           
          
        
        </div>
<td>
<div class="shopping_cart">
        	<div class="cart_title">Recent interviews</div>
            
           
           <% 
		    value = (String)cform.getWidgetMap().get("RECENT_INTERVIEWS");
		    checked = "";
		   if(value.equals("A")){
			   checked = "checked=checked";
		   }
		   %>
            <input type="checkbox" name="RECENT_INTERVIEWS" <%=checked%>>
           
          
        
        </div>

</td>
</tr>
</table>
			
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="savedata()" class="button">
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
</div>

</html:form>
</body>


  