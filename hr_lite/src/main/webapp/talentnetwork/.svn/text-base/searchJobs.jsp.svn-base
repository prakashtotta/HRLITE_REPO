
	<script language="javascript">

function searchJobs(){
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	
		 document.searchjobs.action = "fbjobs.do?method=search";
 document.searchjobs.submit();
}
</script>	
		
		<form name="searchjobs" action="" id="search_box" onsubmit="return searchJobs();" method="post">
			<div class="wrapper">
				<input type="text" id="search" name="search" placeholder="Search.." />
				<button type="submit" class="search_btn"><img src="talentnetwork/images/search_icon.png" title="Search" /></button>
			</div>

		</form>