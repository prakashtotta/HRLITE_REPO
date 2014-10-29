
	<script language="javascript">

function searchCompanies(){
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	
		 document.searchcompanies.action = "companies.do?method=search";
 document.searchcompanies.submit();
}
</script>	
		
		<form name="searchcompanies" action="" id="search_box" onsubmit="return searchCompanies();" method="post">
			<div class="wrapper">
				<input type="text" id="search" name="search" placeholder="Search.." />
				<button type="submit" class="search_btn"><img src="talentnetwork/images/search_icon.png" title="Search" /></button>
			</div>

		</form>