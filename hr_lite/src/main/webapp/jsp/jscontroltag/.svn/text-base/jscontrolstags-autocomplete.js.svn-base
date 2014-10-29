/*
 * Javascript Control Autocomplete to manage formula autocomplete with
 * HTML textBox (eg : to help user to type formula like cos, sin,...).
 * This control extends scriptaculo Autocompleter. 
 *
 * To use it, you must incude in your HTML page javascript : 
 * <script language="javascript" type="text/javascript" src="js/prototype-1.4.0.js"></script>
 * <script language="javascript" type="text/javascript" src="js/controls.js"></script>
 * <script language="javascript" type="text/javascript" src="js/jscontrolstags-autocomplete.js"></script>
 *
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
 if(!JSControlsTags) var JSControlsTags = {};
JSControlsTags.Autocomplete = Class.create();
JSControlsTags.Autocomplete.prototype = Object.extend(new Autocompleter.Base(), {
  /*
   * Javascript Control Autocomplete Constructor
   * @param source ID HTML textBox (included into HTML form).
   * @param items array of item 
   * @param options (see scriptaculo Autocompleter options).
  */
  initialize: function(source, items, options) {
    this.source = source;
    this.list = "JSControlsTagsAuto_" + this.source;    
    $(this.source).setAttribute("autocomplete", "off");
    
    
    // style="display:none;border:1px solid black;background-color:white;"
    // create DIV
    new Insertion.After(this.source, '<div id="' + this.list + '" ></div>');
    

    $(this.list).style.maxHeight =100 + 'px';
    $(this.list).style.overflowY = 'auto';
      
      
    this.baseInitialize(this.source, this.list, options);
    this.options.items = items;
    Element.addClassName($(this.list),this.options.className);
    this.forceActivation = false;
   
  },
  
  setOptions: function(options) {
    this.options = Object.extend({
      maxItems:10,
      partialSearch: true,
      minChars: 1,
      partialChars: 1,
      ignoreCase: true,
      fullSearch:true,
      frequency: 0.1,
      tokens: options.tokens || new Array('+','/','*','-'),
      className: options.className || "autocomplete",
      fade : options.fade,
      onSelectedItem : options.onSelectedItem ? options.onSelectedItem : null,
      selector: function(instance) {

        var ret       = []; // Beginning matches
        var partial   = []; // Inside matches
        var entry     = instance.getToken();
        var count     = 0;

        var length = instance.options.items.length;
		if (instance.forceActivation == true) {
			// force activation => all items must be displayed
			for (var i = 0; i < length ; i++) { 
				var item = instance.options.items[i];
		        var description = item.description;
		        var elem = item.keyword.toString();
					ret.push("<li id=' " + i + "'>" + elem + instance.getHtmlDescription(description) + "</li>");
			}
		}
		else {
	        for (var i = 0; i < length ; i++) { 
	        	if (instance.options.choices) {
			    	if (!(ret.length < instance.options.choices))
			    	  break;
			      }
	          var item = instance.options.items[i];
	          var description = item.description;
	          var elem = item.keyword.toString();
	          
	          var foundPos = instance.options.ignoreCase ? 
	            elem.toLowerCase().indexOf(entry.toLowerCase()) : 
	            elem.indexOf(entry);
			
			    while (foundPos != -1) {
	            if (foundPos == 0 && elem.length != entry.length) { 
	              ret.push("<li id=' " + i + "'><strong>" + elem.substr(0, entry.length) + "</strong>" + 
	                elem.substr(entry.length) + instance.getHtmlDescription(description) + "</li>");
	              break;
	            } else if (entry.length >= instance.options.partialChars && 
	              instance.options.partialSearch && foundPos != -1) {
	              if (instance.options.fullSearch || /\s/.test(elem.substr(foundPos-1,1))) {
	                partial.push("<li id=' " + i + "'>" + elem.substr(0, foundPos) + "<strong>" +
	                  elem.substr(foundPos, entry.length) + "</strong>" + elem.substr(
	                  foundPos + entry.length) + instance.getHtmlDescription(description) + "</li>");
	                break;
	              }
	            }
	
	            foundPos = instance.options.ignoreCase ? 
	              elem.toLowerCase().indexOf(entry.toLowerCase(), foundPos + 1) : 
	              elem.indexOf(entry, foundPos + 1);
	
	          }
	        }
	        if (partial.length) {
	        	if (instance.options.choices) {
	          		ret = ret.concat(partial.slice(0, instance.options.choices - ret.length));
	          	}
	          	else {
	          		if (ret.length == 0) 
	          			ret = partial;
	          		else
	          			ret = ret.concat(partial.slice(0, ret.length));
	          	}
	        }
		}
        return "<ul>" + ret.join('') + "</ul>";
      },
      
      onShow : function(element, update){ 
          if(!update.style.position || update.style.position=='absolute') {
            update.style.position = 'absolute';
            Position.clone(element, update, {setHeight: false, offsetTop: element.offsetHeight});
          }
          if (options.fade)
          	Effect.Appear(update,{duration:0.20});
          else 
          	Element.show(update);
      },
      
      onHide : function(element, update){ 
      	if (options.fade)
      		new Effect.Fade(update,{duration:0.20})
      	else
        	Element.hide(update) ;
      }
    }, options || {});
  }, 
  
  getHtmlDescription:function(description) {
    if (description != null) {
        return " : " + "<code>" + description + "</code>";
    }
    return "";
  },
  
  getUpdatedChoices: function() {
    this.updateChoices(this.options.selector(this));
	// Compute size of DIV
	if (this.entryCount > 0) {
		var firstItem = this.getEntry(0);
		var heigth = firstItem.offsetHeight * this.options.maxItems;
		this.update.style.maxHeight = heigth + 'px'; // <= for Mozilla
		// Just for IE
	    if (this.entryCount > this.options.maxItems) {
	    	// Set Height for DIV
	    	this.update.style.height = heigth + 'px'; // <= for IE
	    }
	    else {
	    	this.update.style.height = ''; // <= for IE
	        //this.update.style.overflowY = 'auto';
	    }
	}
  },
  
  onKeyPress: function(event) {
    if(this.active)
      switch(event.keyCode) {
       case Event.KEY_TAB:
       case Event.KEY_RETURN:
         this.selectEntry();
         Event.stop(event);
       case Event.KEY_ESC:
         this.hide();
         this.active = false;
         Event.stop(event);
         return;
       case Event.KEY_LEFT:
       case Event.KEY_RIGHT:
         return;
       case Event.KEY_UP:
         if (this.index > 0) {
           // item select (li) is NOT the FIRST item of the list
           // set the PREVIOUS item of the list.
           this.markPrevious();
           this.render();       
           // Set the scrollbar of DIV with good position.
           this.updateScrollbar();
         }
         if(navigator.appVersion.indexOf('AppleWebKit')>0) Event.stop(event);
         Event.stop(event);
         return;
       case Event.KEY_DOWN:
         if (this.index < this.entryCount -1) {
           // item select (li) is NOT the LAST item of the list
           // set the NEXT item of the list.
           this.markNext();
           this.render();
           // Set the scrollbar of DIV with good position.
           this.updateScrollbar();           
         }
         if(navigator.appVersion.indexOf('AppleWebKit')>0) Event.stop(event);
         Event.stop(event);
         return;
       case 33:
       	// Page UP
        if (this.index > 0) {
		   // item select (li) is NOT the FIRST item of the list
		   // set the PREVIOUS PAGE item of the list.
		   this.markPreviousPage();
		   this.render();       
		   // Set the scrollbar of DIV with good position.
		   this.updateScrollbar();
         }
         if(navigator.appVersion.indexOf('AppleWebKit')>0) Event.stop(event);
         Event.stop(event);
         return;
		case 34:
		 // Page DOWN
         if (this.index < this.entryCount -1) {
           // item select (li) is NOT the LAST item of the list
           // set the NEXT item of the list.
           this.markNextPage();
           this.render();
           // Set the scrollbar of DIV with good position.
           this.updateScrollbar();
         }
         if(navigator.appVersion.indexOf('AppleWebKit')>0) Event.stop(event);
         Event.stop(event);
         return;      
       	case 36:
	       	// START
		   	this.markFirst();
		   	this.render();       
		   	// Set the scrollbar of DIV with good position.
		   	this.updateScrollbar();
	 		if(navigator.appVersion.indexOf('AppleWebKit')>0) Event.stop(event);
	 		Event.stop(event);
	 		return;         
	   	case 35:
	       	// END
		   	this.markEnd();
		   	this.render();       
		   	// Set the scrollbar of DIV with good position.
		   	this.updateScrollbar();
	 		if(navigator.appVersion.indexOf('AppleWebKit')>0) Event.stop(event);
	 		Event.stop(event);
	 		return;       
      }
     else 
      if(event.keyCode==Event.KEY_TAB || event.keyCode==Event.KEY_RETURN) 
        return;
        
    this.changed = true;
    this.hasFocus = true;

    if(this.observer) clearTimeout(this.observer);
      this.observer = 
        setTimeout(this.onObserverEvent.bind(this), this.options.frequency*1000);
  }, 
  
  updateScrollbar: function() {
    var htmlLiElement = this.getCurrentEntry();
    this.update.scrollTop = htmlLiElement.offsetTop;
  },
  
  getToken: function() {
      var tokenPos = this.findLastToken();
      if (tokenPos != -1)
        var ret = this.element.value.substr(tokenPos + 1).replace(/^\s+/,'');//.replace(/\s+$/,'');
      else
        var ret = this.element.value;
      return /\n/.test(ret) ? '' : ret;
  }, 
  
  selectEntry: function() {
    this.active = false;
    var htmlLiElement = this.getCurrentEntry();
    if (htmlLiElement != null) {
      // Update HTML content with keyword selected.
      // Li ID is the index of options.items Array 
      // get item 
      var index = parseInt(htmlLiElement.id);
      var item = this.options.items[index];
      var keyword = item.keyword;
      htmlLiElement.innerHTML = keyword;
      // Call callback onSelectedItem
      if (this.options.onSelectedItem) {
      	var mustBeContinue = this.options.onSelectedItem(item, this);
      	if(mustBeContinue == false)
      		// Selection must be stopped.
      		return false;
      }
    }    
    this.updateElement(htmlLiElement);
    
  },
  
  markPreviousPage: function() {
  	var maxItems = this.options.maxItems;
    if(this.index - maxItems > 0) this.index-=maxItems
      else this.index = 0;
  },  
  
  markNextPage: function() {
  	var maxItems = this.options.maxItems;
    if(this.index + maxItems < this.entryCount-1) this.index +=maxItems;
      else this.index = this.entryCount-1;
  },
  
  markFirst: function() {
  	this.index = 0;
  },  
  
  markEnd: function() {
  	this.index = this.entryCount-1;
  },
  
  openWithAllItems: function() {
  	this.forceActivation = true;
  	$(this.source).focus();
  	this.activate();
  	setTimeout(this.unForceActivation.bind(this), 250);
  },
  
  unForceActivation: function() {
  	this.forceActivation = false;
  },
  
  hide: function() {
  	if (this.forceActivation != true) {
	    this.stopIndicator();
	    if(Element.getStyle(this.update, 'display')!='none') this.options.onHide(this.element, this.update);
	    if(this.iefix) Element.hide(this.iefix);
  	}
  }
});