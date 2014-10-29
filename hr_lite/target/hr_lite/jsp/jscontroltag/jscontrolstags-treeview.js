if(!JSControlsTags) var JSControlsTags = {};
JSControlsTags.Treeview =  Class.create();
JSControlsTags.Treeview.prototype = Object.extend({

	initialize : function (id, struct, options) {
		this.setOptions(options);
		var tafelTree = new TafelTree(id, struct, this.options.imgBase, this.options.width, this.options.height, this.options);
		//inherit from the base TafelTree class
		Object.extend(this, tafelTree);
		//Event.observe(this.div, 'mousedown', this.evt_unselect.bindAsEventListener(this), false);
		//Event.observe(document, 'keypress', this.evt_keyPress.bindAsEventListener(this), false);
		
		if (this.options.onDrop)
			this.onDrop = this.options.onDrop;
		else {
			if (this.options.draggables)
				// all nodes must be draggables.
				this.onDrop = this._defaultOnDrop;
		}
	},
	
 	setOptions: function(options) {
	    this.options = Object.extend({ 
	      baseUrl : options.baseUrl ? options.baseUrl : null,
	      imgBase : options.imgBase ? options.imgBase : 'img',
	      width : options.width ? options.width : '100%',
	      height : options.height ? options.height : 'auto'
	    }, options || {});
	}, 
	
	_defaultOnDrop: function() {
		return true;
	}
});