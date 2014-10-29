

<%

    List formvariableList = aform.getFormVariablesList();

    Map<Long, SearchApplicantCustomFields> customFieldData= aform.getCustomFieldData();

	if(formvariableList != null && formvariableList.size()>0){
	
%>

<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[customvariableappsearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>Custom fields </div>
<div id="customvariableappsearch" style="width  100%;">
<table border="0" width="100%">

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
   <td><%=variable.getVariableName()%></td>
   <td>
		<%
		SearchApplicantCustomFields scacf = customFieldData.get(variable.getVariableId());
	   if(scacf != null){
       variable.setDefaultValue(scacf.getFilterValue1());
	   }
	   String calgentext = VariableDataCaptureUtil.generateJavaScriptForSearchScreen(variable,request);
	   if(scacf != null){
       variable.setDefaultValue(scacf.getFilterValue2());
	   }
	    String calgentext1 = VariableDataCaptureUtil.generateJavaScriptForSearchScreenBetween(variable,request);
		%>

   <select NAME="<%=variable.getVariableCode()%>_datecri"  onchange="unhidecustomDate('<%=variable.getVariableCode()%>')">
    <OPTION VALUE=""></OPTION>
    <% for(int j=0;j<aform.getCriteriaNumericList().size();j++){
		   KeyValue kv = (KeyValue)aform.getCriteriaNumericList().get(j);
		   String selectedtext = "";
		   if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFiltercri()) && scacf.getFiltercri().equals(kv.getKey())){
			   selectedtext="SELECTED";
		   }
	%>

   <OPTION VALUE="<%=kv.getKey()%>" <%=selectedtext%>><%=kv.getValue()%></OPTION>
   <%}%>
   </select>

<%=calgentext%>
<%=calgentext1%>

    </td>
	 <%}%>

	 <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA))){%>
   <td><%=variable.getVariableName()%></td>
   <td>
   <%
		SearchApplicantCustomFields scacf = customFieldData.get(variable.getVariableId());
		  
			String value11 = "";
			if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFilterValue1())){
				value11 = scacf.getFilterValue1();
			}
	 %>
   <select NAME="<%=variable.getVariableCode()%>_textcri">
    <% for(int j=0;j<aform.getCriteriaStringList().size();j++){
		   KeyValue kv = (KeyValue)aform.getCriteriaStringList().get(j);
			String selectedtext = "";
		   if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFiltercri()) && scacf.getFiltercri().equals(kv.getKey())){
			   selectedtext="SELECTED";
		   }

	%>

   <OPTION VALUE="<%=kv.getKey()%>" <%=selectedtext%>><%=kv.getValue()%></OPTION>
   <%}%>
   </select>
   <input type="text" size="30" name="<%=variable.getVariableCode()%>" value="<%=value11%>"/>
  
   </td>
	 <%}%>


   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC)){%>
   <td><%=variable.getVariableName()%></td>
   <td>
   <%
		SearchApplicantCustomFields scacf = customFieldData.get(variable.getVariableId());
        String value22 = "";
		String value11 = "";
		if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFilterValue2())){
			value22 = scacf.getFilterValue2();
		}
		if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFilterValue1())){
			value11 = scacf.getFilterValue1();
		}
			
	 %>
      <select NAME="<%=variable.getVariableCode()%>_numericcri" onchange="unhidecustomNumeric('<%=variable.getVariableCode()%>')">
    <% for(int j=0;j<aform.getCriteriaNumericList().size();j++){
		   KeyValue kv = (KeyValue)aform.getCriteriaNumericList().get(j);
		   String selectedtext = "";
		   if(scacf != null && !StringUtils.isNullOrEmpty(scacf.getFiltercri()) && scacf.getFiltercri().equals(kv.getKey())){
			   selectedtext="SELECTED";
		   }
	%>

   <OPTION VALUE="<%=kv.getKey()%>" <%=selectedtext%> ><%=kv.getValue()%></OPTION>
   <%}%>
   </select>
   <input type="text" size="30" name="<%=variable.getVariableCode()%>" value="<%=value11%>"/>
   <input style="visibility: hidden;" type="text" size="30" name="<%=variable.getVariableCode()+"_1"%>" value="<%=value22%>"/>
   </td>
	 <%}%>



   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
	Set lovset = variable.getListData();   
	%>
   <td><%=variable.getVariableName()%></td>
   <td>
    <%
		SearchApplicantCustomFields scacf = customFieldData.get(variable.getVariableId());
	 %>
	<SELECT NAME="<%=variable.getVariableCode()%>">
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