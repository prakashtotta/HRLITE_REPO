<SCRIPT type="text/javascript">
function companiesTab(){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="companies.do?method=home";
 
}
function peopleTab(url){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href=url;
}
function jobsTab(){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="fbjobs.do?method=search"; 
}
</script>

<body id="page2">
<ul id="tabs">
<li id="tab1"><a href="#" onClick="peopleTab('networkhome.do?method=home&fid=<%=fuser.getFacebookid()%>&key=<%=fuser.getSessionKey()%>')">People</a></li>
<li id="tab2"><a href="#" onClick="jobsTab()">Jobs</a></li>
<li id="tab3"><a href="#" onClick="companiesTab()">Companies</a></li>

</ul>