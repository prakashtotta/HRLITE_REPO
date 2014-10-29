<%@ include file="../common/autocomplete.jsp" %>
<script type="text/javascript" src="../autocomplete/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script>

<script type="text/javascript"> 
//$(document).ajaxStop($.unblockUI);      


 $(document).ready(function() {$('#pageDemo2').click(function() {    
	 $.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
   test();
	 }); 
}); 

function test() { 
$.ajax({
	type: 'POST',
  url: "test1.jsp",
  success: function(data){
  $('#data').html(data);
	completeajx();
  }
});
} 

 
function completeajx(){
	  $.unblockUI();
}

</script>
<a href="#" id="pageDemo2">test</a>
<span id="data">satya</span>