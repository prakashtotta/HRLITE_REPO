
	<script language="javascript">

function searchPeople(){
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	
		 document.searchpeople.action = "networkhome.do?method=search";
 document.searchpeople.submit();
}
</script>	
		
		<form name="searchpeople" action="" id="search_box" onsubmit="return searchPeople();" method="post">
			<div class="wrapper">
				<input type="text" id="search" name="search" placeholder="Search.." />
				<button type="submit" class="search_btn"><img src="talentnetwork/images/search_icon.png" title="Search" /></button>
			</div>

		</form>