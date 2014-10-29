<script type="text/javascript" src="../autocomplete/jquery.min.js"></script>
<script type="text/javascript" src="jquery.tablednd_0_5.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	// Initialise the first table (as before)
	$("#table-1").tableDnD();
	// Make a nice striped effect on the table
	$("#table-2 tr:even").addClass("alt");
	// Initialise the second table specifying a dragClass and an onDrop function that will display an alert
	$("#table-2").tableDnD({
	    onDragClass: "myDragClass",
	    onDrop: function(table, row) {
            var rows = table.tBodies[0].rows;
            var debugStr = "Row dropped was "+row.id+". New order: ";
            for (var i=0; i<rows.length; i++) {
                debugStr += rows[i].id+" ";
            }
	        $("#debugArea").html(debugStr);
	    },
		onDragStart: function(table, row) {
			$("#debugArea").html("Started dragging row "+row.id);
		}
	});

	$('#table-3').tableDnD({
	    onDrop: function(table, row) {
                var rows = table.tBodies[0].rows;
            var debugStr = "";
            for (var i=0; i<rows.length; i++) {
                debugStr += rows[i].id+",";
            }
			alert(debugStr);

	       // alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("../screenfield/arrangeSequence.jsp?arrangestr="+debugStr);
        }
	}); 

	$('#table-4').tableDnD({
	    onDrop: function(table, row) {
	        alert("Result of $.tableDnD.serialise() is "+$.tableDnD.serialize());
		    $('#AjaxResult').load("/articles/ajaxTest.php?"+$.tableDnD.serialize());
        }
	}); 
	
	
	
	$('#table-5').tableDnD({
        onDrop: function(table, row) {
            alert($('#table-5').tableDnDSerialize());
        },
        dragHandle: "dragHandle"
    });

    $("#table-5 tr").hover(function() {
          $(this.cells[0]).addClass('showDragHandle');
    }, function() {
          $(this.cells[0]).removeClass('showDragHandle');
    });
    
});

</script>

<div class="tableDemo">
<div id="AjaxResult" style="float: right; width: 250px; border: 1px solid silver; padding: 4px; font-size: 90%">
<h3>Ajax result</h3>
<p>Drag and drop in this table to test out serialise and using JQuery.load()</p>
</div>
<table id="table-3" cellspacing="0" cellpadding="2">
<tr id="3.1">
<td>1</td>
<td>One</td>
<td>
<input type="text" name="one" value="one"/></td>
</tr>
<tr id="3.2">
<td>2</td>
<td>Two</td>
<td>
<input type="text" name="two" value="two"/></td>
</tr>
<tr id="3.3" class="nodrop">
<td>3</td>
<td>Three (Can&#8217;t drop on this row)</td>
<td>
<input type="text" name="three" value="three"/></td>
</tr>
<tr id="3.4">
<td>4</td>
<td>Four</td>
<td>
<input type="text" name="four" value="four"/></td>
</tr>
<tr id="3.5" class="nodrag">
<td>5</td>
<td>Five (Can&#8217;t drag this row)</td>
<td>
<input type="text" name="five" value="five"/></td>
</tr>
<tr id="3.6">
<td>6</td>
<td>Six</td>
<td>
<input type="text" name="six" value="six"/></td>
</tr>
</table>
</div>

<table id="table-4" cellpadding="2" cellspacing="0">
<tbody>
<tr id="4.0" class="nodrop nodrag">
<th>H1</th>
<th>H2</th>
<th>H3</th>
</tr>
<tr style="cursor: move;" id="4.1">
<td>4.1</td>
<td>One</td>
<td>

<input name="one" value="one" type="text"></td>
</tr>
<tr style="cursor: move;" id="4.2">
<td>4.2</td>
<td>Two</td>
<td>
<input name="two" value="two" type="text"></td>
</tr>
<tr class="" style="cursor: move;" id="4.3">
<td>4.3</td>
<td>Three</td>
<td>
<input name="three" value="three" type="text"></td>

</tr>
<tr style="cursor: move;" id="4.4">
<td>4.4</td>
<td>Four</td>
<td>
<input name="four" value="four" type="text"></td>
</tr>
<tr style="cursor: move;" id="4.5">
<td>4.5</td>
<td>Five</td>
<td>
<input name="five" value="five" type="text"></td>
</tr>

<tr style="cursor: move;" id="4.6">
<td>4.6</td>
<td>Six</td>
<td>
<input name="six" value="six" type="text"></td>
</tr>
</tbody>
<tbody>
<tr id="5.0" class="nodrop nodrag">
<th>H1</th>
<th>H2</th>
<th>H3</th>

</tr>
<tr style="cursor: move;" id="5.1">
<td>5.1</td>
<td>One</td>
<td>
<input name="one" value="one" type="text"></td>
</tr>
<tr style="cursor: move;" id="5.2">
<td>5.2</td>
<td>Two</td>
<td>
<input name="two" value="two" type="text"></td>
</tr>

<tr style="cursor: move;" id="5.3">
<td>5.3</td>
<td>Three</td>
<td>
<input name="three" value="three" type="text"></td>
</tr>
<tr style="cursor: move;" id="5.4">
<td>5.4</td>
<td>Four</td>
<td>
<input name="four" value="four" type="text"></td>
</tr>
<tr style="cursor: move;" id="5.5">

<td>5.5</td>
<td>Five</td>
<td>
<input name="five" value="five" type="text"></td>
</tr>
<tr style="cursor: move;" id="5.6">
<td>5.6</td>
<td>Six</td>
<td>
<input name="six" value="six" type="text"></td>
</tr>
</tbody>
<tbody>

<tr id="6.0" class="nodrop nodrag">
<th>H1</th>
<th>H2</th>
<th>H3</th>
</tr>
<tr style="cursor: move;" id="6.1">
<td>6.1</td>
<td>One</td>
<td>
<input name="one" value="one" type="text"></td>
</tr>
<tr style="cursor: move;" id="6.2">

<td>6.2</td>
<td>Two</td>
<td>
<input name="two" value="two" type="text"></td>
</tr>
<tr style="cursor: move;" id="6.3">
<td>6.3</td>
<td>Three</td>
<td>
<input name="three" value="three" type="text"></td>
</tr>
<tr style="cursor: move;" id="6.4">
<td>6.4</td>

<td>Four</td>
<td>
<input name="four" value="four" type="text"></td>
</tr>
<tr style="cursor: move;" id="6.5">
<td>6.5</td>
<td>Five</td>
<td>
<input name="five" value="five" type="text"></td>
</tr>
<tr style="cursor: move;" id="6.6">
<td>6.6</td>
<td>Six</td>

<td>
<input name="six" value="six" type="text"></td>
</tr>
</tbody>
</table>

<tbody><tr id="table5-row-1">
<td class="dragHandle">&nbsp;</td>
<td>1</td>
<td>One</td>
<td>some text</td>
</tr>

<tr id="table5-row-3">
<td class="dragHandle">&nbsp;</td>
<td>3</td>
<td>Three</td>

<td>some text</td>
</tr>
<tr id="table5-row-4">
<td class="dragHandle">&nbsp;</td>
<td>4</td>
<td>Four</td>
<td>some text</td>
</tr><tr class="" id="table5-row-2">
<td class="dragHandle">&nbsp;</td>
<td>2</td>
<td>Two</td>

<td>some text</td>
</tr>
<tr id="table5-row-5">
<td class="dragHandle">&nbsp;</td>
<td>5</td>
<td>Five</td>
<td>some text</td>
</tr>
<tr id="table5-row-6">
<td class="dragHandle">&nbsp;</td>
<td>6</td>
<td>Six</td>

<td>some text</td>
</tr>
</tbody>
