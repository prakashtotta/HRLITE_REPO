<%--<SCRIPT type="text/javascript" src="jsp/jquery/jquery-1.7.1.js"></SCRIPT>--%>

<script type="text/javascript" src="jsp/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="jsp/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="jsp/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

 <script type="text/javascript">
 //$.noConflict();
		$(document).ready(function() {

		$("#createappmenu").fancybox({
				'width'				: '90%',
				'height'			: '100%',
				'autoScale'			: true,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});

		$("#createmplatemenu").fancybox({
				'width'				: '90%',
				'height'			: '100%',
				'autoScale'			: true,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});

	 		$("#createreqmenu").fancybox({
				'width'				: '90%',
				'height'			: '100%',
				'autoScale'			: true,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});

			$("#fullscreencomponents").fancybox({
				'width'				: '90%',
				'height'			: '100%',
				'autoScale'			: true,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});
	});
</script>
 <script type="text/javascript">
function callBoxFancy(my_href) {
	
var j1 = document.getElementById("fullscreencomponents");
j1.href = my_href;

$('#fullscreencomponents').trigger('click');
}
</script>



<div id="hidden_clicker" style="display:none">
<a  id="fullscreencomponents" href="http://asdf.com" >Hidden Clicker</a>
</div>


