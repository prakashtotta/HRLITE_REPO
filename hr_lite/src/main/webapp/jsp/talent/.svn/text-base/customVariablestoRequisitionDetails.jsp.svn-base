
	<table>
			<%
			 for(int i=0;i<formvariableList.size();i++){
					FormVariablesMap fvaiablemap = (FormVariablesMap)formvariableList.get(i);
					
					Variables variable = fvaiablemap.getVariable();
					if(variable != null){
						  
					   	if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_DATE)){
			%>
					<tr>
						<td width="250px"><%=variable.getVariableName()%></td>
						<td><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></td>

					</tr>	
			
			<%			}
					    if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC))){
			%>
					<tr>
						<td width="250px"><%=variable.getVariableName()%></td>
						<td><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></td>

					</tr>	  	
			<%		   	}
					    if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA)){
			
			%>
					<tr>
						<td width="250px"><%=variable.getVariableName()%></td>
						<td><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></td>

					</tr>	 
					  
			<%		    }
					    
					   if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
						Set lovset = variable.getListData();  
						String selectedvalue = "";
			%>
					<tr>
						<td width="250px"><%=variable.getVariableName()%></td>
						<td>
							<% if(lovset != null && lovset.size()>0){
							Iterator itr = lovset.iterator();
								while(itr.hasNext()){
									VariableListData vdata = (VariableListData)itr.next();
									String selectedtext = "";
									
									if(!StringUtils.isNullOrEmpty(vdata.getIsDefault()) && vdata.getIsDefault().equalsIgnoreCase("Y")){
										selectedtext = "SELECTED";
										selectedvalue=vdata.getVariableValue();
									}
									if(!StringUtils.isNullOrEmpty(variable.getDefaultValue()) && variable.getDefaultValue().equalsIgnoreCase(vdata.getVariableValue())){
										selectedtext = "SELECTED";
										selectedvalue=vdata.getVariableValue();
									}

								} // itr loop end
							}
							%>
							
							<%=selectedvalue %>
    	  				</td>
					</tr>
			<%			}
					} 
			 }
			%>
	</table>
