<script type="text/javascript" src="jsp/js/animatedcollapse.js"></script>

<script type="text/javascript">
animatedcollapse.addDiv('customvariablereq', 'fade=0,speed=400,group=pets7,hide=1,persist=1');

animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()
</script>

<%

    List formvariableList = aform.getFormVariablesList();

    Map<Long, SearchCustomFields> customFieldData= aform.getCustomFieldData();

	if(formvariableList != null && formvariableList.size()>0){
	
%>

<div class="msg"><a href="#" rel="toggle[customvariablereq]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>Custom fields </div>
<div id="customvariablereq" style="width  100%;">
<table border="0" width="100%" class="div">

     <% 
		 int colnlength=0;
	 for(int i=0;i<formvariableList.size();i++){
	FormVariablesMap fvaiablemap = (FormVariablesMap)formvariableList.get(i);
	Variables variable = fvaiablemap.getVariable();
	if(variable != null){
     
     %>
    <tr>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_DATE)){
	   	   
	   %>
   <td width="40%"><%=variable.getVariableName()%></td>
   <td>
		<%
		SearchCustomFields scacf = customFieldData.get(variable.getVariableId());
	   if(scacf != null){
       variable.setDefaultValue(scacf.getFilterValue1());
	   }
	   String calgentext = VariableDataCaptureUtil.generateJavaScriptForReqSearchScreen(variable,request);
	   
		%>

   

<%=calgentext%>


    </td>
	 <%}%>

	 <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA))){%>
   <td width="40%"><%=variable.getVariableName()%></td>
   <td>
   <%
		SearchCustomFields scacf = customFieldData.get(variable.getVariableId());
		  
			String value11 = "";
			if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFilterValue1())){
				value11 = scacf.getFilterValue1();
			}
	 %>
   
   <input type="text" size="30" class="textdynamic" name="<%=variable.getVariableCode()%>" value="<%=value11%>"/>
  
   </td>
	 <%}%>


   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC)){%>
   <td width="40%"><%=variable.getVariableName()%></td>
   <td>
   <%
		SearchCustomFields scacf = customFieldData.get(variable.getVariableId());
        String value22 = "";
		String value11 = "";
		if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFilterValue2())){
			value22 = scacf.getFilterValue2();
		}
		if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFilterValue1())){
			value11 = scacf.getFilterValue1();
		}
			
	 %>
      
   <input type="text" size="30" class="textdynamic" name="<%=variable.getVariableCode()%>" value="<%=value11%>"/>
   
   </td>
	 <%}%>



   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
	Set lovset = variable.getListData();   
	%>
   <td width="40%"><%=variable.getVariableName()%></td>
   <td>
    <%
		SearchCustomFields scacf = customFieldData.get(variable.getVariableId());
	 %>
	<SELECT NAME="<%=variable.getVariableCode()%>" class="list">
	<option value=""></option>
	<% if(lovset != null && lovset.size()>0){
		Iterator itr = lovset.iterator();
					while(itr.hasNext()){
			VariableListData vdata = (VariableListData)itr.next();
			
			String selectedtext = "";
		   if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getAnswerOption()) && scacf.getAnswerOption().equals(vdata.getVariableValue())){
			   selectedtext="SELECTED";
		   }
     %>
	<OPTION VALUE="<%=vdata.getVariableValue()%>" <%=selectedtext%>><%=vdata.getVariableValue()%></OPTION>

    <%
		} // itr loop end
	%>
    </td>
	 <%
		}//lovset null check
   }// if type is list
	%>


    </tr>
     <%
		}// if variable null check
		 } // for loop end
		 %>  

</table>
</div>

<%}%>