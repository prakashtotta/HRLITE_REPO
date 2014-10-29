<html>
<head>
<title> Job requisition details</title>
</head>
<body>
<form name="jobreqform" action="">
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	 <tr>
			<td>Job Title*</td>
			<td> </td>
		</tr>
		<tr>
			<td>Job Position Name*</td>
			<td> </td>
		</tr>
		<tr>
			<td>No of openings*</td>
			<td> </td>
		</tr>
		<tr>
			<td>Is New Position</td>
 		</tr>


<tr>
			<td>Target finish date*</td>
 			<td>
			</td>
		</tr>
			
 
		<tr>
				<td>Organization</td>
				 <td>
				
				</td>
			</tr>
 
		<tr>
				<td>Location*</td>
				<td>
				</td>
		</tr>
		<tr>
				<td>Department*</td>
				<td> 
				</td>
		</tr>


 
		<tr>
				<td>Project Code</td>
				<td> 
				</td>
		</tr>



		 

		<tr>
				<td>Hiring Manager*</td>
				<td> 
				</td>
		</tr>

		 
		<tr>
				<td>Job Grade*</td>
				<td> 
				</td>
		</tr>

		 
		<tr>
				<td>Salary Plan</td>
				<td> 
				</td>
		</tr>

		 

		<tr>
				<td>Budget Code</td>
				<td>< 
				</td>
		</tr>


				 
		<tr>
				<td>Employee Referral Scheme</td>
				<td> 
				</td>
		</tr>


					 

		<tr>
				<td>Agency Referral Scheme</td>
				<td> 
				</td>
		</tr>


		 
		<tr>
				<td>Recruiter</td>
				<td> 
				</td>
		</tr>
		
		<tr>
			<td>Notes</td> 
			<td> </td>
		</tr>
	



	 

	<tr>
			 
			<td>Introduction</td>
			<td> </td>
		</tr>
		<tr>
			<td>Job Details</td>
			<td> </td>
		</tr>
		<tr>
			<td>Job Roles</td>
			<td> </td>
		</tr>

		
		<tr>
			<td>Default Standard Working Hours*</td>
			<td> </td>
		</tr>
		<tr>
			<td>Duration in Months</td>
			<td> </td>
		</tr>
		<tr>
			<td>Min Years of Exp Required</td>
			<td> </td>
		</tr>
		<tr>
			<td>Max Years of Exp Required</td>
			<td> </td>
		</tr>
		<tr>
			<td>Min Level of Education</td>
			<td> </td>
		</tr>
		<tr>
			<td>Other Experience</td>
			<td> </td>
		</tr>


<tr>
			<td>Job Type</td>
			<td>
			 
			</td>
		</tr>

		<tr>
			<td>Work Shift</td>
			<td>
			 
		</tr>

		<tr>
			<td>FLSA Status</td>
			<td>
			 
			</td>
		</tr>

		<tr>
			<td>Comp Frequency</td>
			<td>
			 
			</td>
		</tr>
		<tr><td>
	 <logic:iterate id="permission" name="jobreqform" property="permissionsList" type="com.bean.Permissions" scope="request">
<bean:write name="permission" property="perName"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<bean:write name="permission" property="perDesc"/>
<br>
</logic:iterate>
	</td></tr>
</table>
</form>
</body>
</html>