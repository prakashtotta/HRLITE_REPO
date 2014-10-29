var fbInJs = new Object();

fbInJs.api_key = '322527694505288';
fbInJs.app_name = 'hireapp';
fbInJs.hosted_at = 'http://localhost:8081/facebookfriends/';
fbInJs.channel_path = 'xd_receiver.html';
fbInJs.allFriends = null;
fbInJs.friendsAndName = null;
fbInJs.loggedIn = false;

fbInJs.api = function () { return FB.Facebook.apiClient;};
fbInJs.dbgDiv = function() {return document.getElementById('mydebugdiv');};
fbInJs.currUser = function() {return fbInJs.api().get_session().uid;};
fbInJs.sessKey = function() {return fbInJs.api().get_session().session_key;};

fbInJs.doFBMinInit = function() {
	FB_RequireFeatures(["XFBML", "Api"], function(){	
		FB.FBDebug.logLevel=4;
		FB.FBDebug.isEnabled = true;
		FB.FBDebug.dump("Initializing");
		FB.Facebook.init(fbInJs.api_key, fbInJs.channel_path);
	});
};

fbInJs.doFBInit = function () {
	FB_RequireFeatures(["XFBML", "Api"], function(){	
		FB.FBDebug.logLevel=4;
		FB.FBDebug.isEnabled = true;
		FB.FBDebug.dump("Initializing");
		FB.Facebook.init(fbInJs.api_key, fbInJs.channel_path);
		FB.ensureInit(function () {
			FB.Facebook.apiClient.users_isAppUser(function(result,exception){
				FB.FBDebug.dump("Is app user: ");
				FB.FBDebug.dump(result);
			    if(result == null) {
			    	fbInJs.loggedIn = false;
				    document.getElementById("loginLink").style.display='block';
				    document.getElementById("logoutLink").style.display='none';
			    }
			    else {
			    	fbInJs.loggedIn = true;
				    document.getElementById("loginLink").style.display='none';
				    document.getElementById("logoutLink").style.display='block';
			    }
			});
			FB.CanvasClient.setSizeToContent();
			FB.CanvasClient.startTimerToSizeToContent();
			fbInJs.fetchFriends();
	    	FB.FBDebug.dump(fbInJs.api().get_session(), "Session Object");
		});
    });
	fbInJs.initUser();
};

fbInJs.askForAuthentication = function () {
	window.parent.location.href = 'http://www.facebook.com/login.php?v=1.0&api_key=' + fbInJs.api_key + '&canvas=true&next=http%3A%2F%2Fapps.facebook.com%2F' + fbInJs.app_name + '%2Findex.html?afterlogin=1';
};

fbInJs.logoutRevokeAuthentication = function () {
	fbInJs.api().revokeAuthorization(null, function(exception) {
		window.parent.location.href = 'http://apps.facebook.com/' + fbInJs.app_name + '/index.html?afterlogout=1';
	});
};

fbInJs.showCurrentUser = function () {
	fbInJs.dbgDiv().innerHTML = ("Current user id: " + fbInJs.currUser() + ". Name: " + fbInJs.friendsAndName[fbInJs.currUser()]);		
};

fbInJs.fetchFriends = function () {
	fbInJs.api().friends_get(null, function(result, exception){
		FB.FBDebug.dump(result, 'friendsResult from non-batch execution '); 
		fbInJs.allFriends = result; 
		fbInJs.allFriends[fbInJs.allFriends.length] = fbInJs.api().get_session().uid;
		var selectBoxArr = new Array();
		selectBoxArr[0] = document.getElementById('notifToId');
		selectBoxArr[1] = document.getElementById('infoId');
		selectBoxArr[2] = document.getElementById('id1');
		selectBoxArr[3] = document.getElementById('id2');
		fbInJs.populateFriendsSelect(selectBoxArr);
	});
};
	
fbInJs.populateFriendsSelect = function (selectBoxArr) {
	FB.FBDebug.dump(fbInJs.allFriends.length, 'Number of friends');
	var fieldsArr = ['uid','name'];
	fbInJs.api().users_getInfo(fbInJs.allFriends, fieldsArr, function(result, exception){
		fbInJs.friendsAndName = new Array();
		for(i=0; i < result.length; i++) {
			for(j=0; j < selectBoxArr.length; j++) {
				var selectBox = selectBoxArr[j];
				var thisResult = result[i];
				var option = document.createElement('option');
				option.setAttribute('value',thisResult['uid']);
				option.appendChild(document.createTextNode(thisResult['name']));
				selectBox.appendChild(option);

				fbInJs.friendsAndName[thisResult['uid']] = thisResult['name'];
			}
		}
	});
};
	
fbInJs.showFriends = function () {
	fbInJs.dbgDiv().innerHTML = "";
	if(null != fbInJs.allFriends) {
		for(i=0; i < fbInJs.allFriends.length; i++) {
			snipp = '<fb:profile-pic size="square" uid="'+fbInJs.allFriends[i]+'" facebook-logo="true"></fb:profile-pic><fb:name uid="'+fbInJs.allFriends[i]+'" firstnameonly="false" useyou="true"></fb:name>('+fbInJs.allFriends[i]+')';			
			fbInJs.dbgDiv().innerHTML = fbInJs.dbgDiv().innerHTML + (((i%3)==0) ? "<br/>" : " ||| ") + snipp;
        }
    }
    FB.XFBML.Host.parseDomElement(fbInJs.dbgDiv()); 
};

fbInJs.checkIfFriends = function (uid1, uid2) {
	fbInJs.api().friends_areFriends(uid1,uid2, function(result, exception){
		fbInJs.dbgDiv().innerHTML = "";
		var isornot = (result[0].are_friends) ? " is a " : " is not a ";			
		snipp = '<fb:name uid="'+uid1+'" firstnameonly="false" useyou="true"></fb:name>' + isornot + ' friend of <fb:name uid="'+uid2+'" firstnameonly="false" useyou="true"></fb:name>';
		fbInJs.dbgDiv().innerHTML = snipp;
		FB.XFBML.Host.parseDomElement(fbInJs.dbgDiv());
	});
};

fbInJs.getUserInfo = function (uid) {
	var uidArr = new Array();
	uidArr[0] = uid;
	var fieldsArr = [
		//'uid', 
		'is_blocked', 'status', 'wall_count',
		//'first_name', 'last_name', 
		'name', 
		//'birthday', 
		'birthday_date', 'sex', 'relationship_status',
		'profile_blurb', 'about_me', 'activities', 'books', 'movies', 'music', 'tv', 'affiliations', 'interests', 'political', 'quotes', 'website',
		'timezone', 'locale', 'current_location', 'hometown_location', 
		'education_history', 'hs_info', 'work_history',
		'email_hashes',
		'pic_square', 'pic_big', 'profile_url', 'meeting_for', 'meeting_sex', 'religion', 'significant_other_id',
		'notes_count', 'proxied_email', 'profile_update_time'];
		 
	fbInJs.api().users_getInfo(uidArr, fieldsArr, function(result, exception){
        fbInJs.dbgDiv().innerHTML = "";
        var ret = result[0];
        FB.FBDebug.dump(ret);
        var snip = "";
        for(index=0; index<fieldsArr.length; index++) {
            if(fieldsArr[index] == 'affiliations') {
                snip = snip + '<small><b>'+fieldsArr[index]+':</b></small><br/><ul>';
                affObj = ret[fieldsArr[index]];
                for(affIndex=0; affIndex<affObj.length; affIndex++) {
                    snip = snip + '<li><small>' + affObj[affIndex].status + ' of ' + affObj[affIndex].name + ' ' + affObj[affIndex].type + '</small></li>';
                }
                snip = snip + '</ul><br/>';
            }
            else if(fieldsArr[index] == 'current_location') {
                var loc = ret[fieldsArr[index]];
                if(null != loc)
                	snip = snip + '<small><b>'+fieldsArr[index]+':</b>' + loc.city + ',' + loc.state + ',' + loc.country + ',' + loc.zip + '</small><br/>';
            }
            else if(fieldsArr[index] == 'hometown_location') {
                var loc = ret[fieldsArr[index]];
                if(null != loc)
                	snip = snip + '<small><b>'+fieldsArr[index]+':</b>' + loc.city + ',' + loc.state + ',' + loc.country +'</small><br/>';
            }
            else if(fieldsArr[index] == 'pic_square') {
                snip = snip + '<small><b>'+fieldsArr[index]+':</b><img src="'+ ret[fieldsArr[index]] +'"/></small><br/>';
            }
            else if(fieldsArr[index] == 'pic_big') {
                snip = snip + '<small><b>'+fieldsArr[index]+':</b><img src="'+ ret[fieldsArr[index]] +'"/></small><br/>';
            }
            else if(fieldsArr[index] == 'status') {
                var msg = ret[fieldsArr[index]];
                if(null != msg)
            		snip = snip + '<small><b>'+fieldsArr[index]+':</b>At '+ msg.time + ' said: ' + msg.message +'</small><br/>';
            }
            else {
                if((null !=  ret[fieldsArr[index]]) && ((""+ ret[fieldsArr[index]]) != "")) {
            		snip = snip + '<small><b>'+fieldsArr[index]+':</b>' + ret[fieldsArr[index]] + '</small><br/>';
                }
            }
        }
    	fbInJs.dbgDiv().innerHTML = snip;
	});
};
		
fbInJs.sendNotification = function(toId) {
	var arr = new Array();
	arr[0] = toId;
	var msg = "Hi " + fbInJs.friendsAndName[toId] + "! This is test from " + fbInJs.app_name;
	fbInJs.api().notifications_send(arr, msg, function(result, exception){
		FB.FBDebug.dump(msg, "Sending notification:");
        fbInJs.dbgDiv().innerHTML = "Notification [" + msg + "] sent to " + toId + ". Result="+result + ". Exception="+exception;
	});
};
	
fbInJs.createAppNotificationParamsObject = function (toId, str) {
	this.api_key = fbInJs.api_key;
	this.call_id = new Date().getTime();
	this.v = "1.0";
	this.to_ids = new Array();
	this.to_ids[0] = toId;
	this.notification = str;
	this.type = "app_to_user";
};
	
/*
 * Application notifications can only be sent only to users of the application.
 * User notifications can be sent to non application users who are friends.
 */
fbInJs.sendAppNotification = function(toId) {
	var msg = "Hi " + fbInJs.friendsAndName[toId] + "! This is test from " + fbInJs.app_name;
	var params = new fbInJs.createAppNotificationParamsObject(toId, msg);
	FB.FBDebug.dump(params, "Params for facebook.notifications.send");
	fbInJs.api().callMethod("facebook.notifications.send", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of facebook.notifications.send");
		FB.FBDebug.dump(exception, "Exception of facebook.notifications.send");
		fbInJs.dbgDiv().innerHTML = "Notification [" + msg + "] sent to " + toId + ". Result="+result + ". Exception="+exception;
	});
};

fbInJs.postOnWall = function (toId, fromId) {
	var attachment;
	// posting to own wall
	if(null == toId) attachment = {'name': fbInJs.app_name,'caption': 'I posted a new message', 'description': fbInJs.app_name + ' is cool!'};
	// posting to friend's wall
	else attachment = {'name': fbInJs.app_name,'caption': fbInJs.friendsAndName[fromId] + ' posted a new message', 'description': fbInJs.friendsAndName[fromId] + ' says ' + fbInJs.app_name + ' is cool!'};
		
	FB.Connect.streamPublish('', attachment, null, toId, 'Add some spice?', function(post_id, exception) {
		FB.FBDebug.dump(post_id, "Result of streamPublish, the post id");
		FB.FBDebug.dump(exception, "Exception of streamPublish");
		fbInJs.dbgDiv().innerHTML = "Published a msg to " + ((null == toId) ? "self" : toId) + ". Result="+result + ". Exception="+exception;
	});
};

fbInJs.inviteDiv = function() {return document.getElementById('fselect');};

fbInJs.inviteFriends = function () {
	if(fbInJs.inviteDiv().style.display=='block') {
		fbInJs.inviteDiv().style.display='none';
		fbInJs.inviteDiv().innerHTML = '<iframe src="' + fbInJs.hosted_at + 'friendInvite.html" width="760" height="730" scrolling="no" frameborder="0"/>';
		FB.XFBML.Host.parseDomElement(fbInJs.inviteDiv()); 
	}
	else if(fbInJs.inviteDiv().style.display=='none') fbInJs.inviteDiv().style.display='block';
};

/*
 * News. From the client only logged in user's news can be manipulated.
 */
fbInJs.clearNews = function(newsId) {
	if(newsId <= 0 ) return;
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	params.news_ids = [newsId];
	FB.FBDebug.dump(params, "Params for dashboard.clearNews");
	fbInJs.api().callMethod("facebook.dashboard.clearNews", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.clearNews");
		FB.FBDebug.dump(exception, "Exception of dashboard.clearNews");
		fbInJs.dbgDiv().innerHTML = "Clear news. Result="+result + ". Exception="+exception;
	});	
};

fbInJs.getAllNews = function(selectBox) {
	for (var i=selectBox.length-1; i > 0; i--) {
		selectBox.remove(i);
	}
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	FB.FBDebug.dump(params, "Params for dashboard.getNews");
	fbInJs.api().callMethod("facebook.dashboard.getNews", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.getNews");
		FB.FBDebug.dump(exception, "Exception of dashboard.getNews");
		fbInJs.dbgDiv().innerHTML = "Get news. Result="+result + ". Exception="+exception;
		for(var newsId in result) {
			var thisResult = result[newsId];
			var thisNews = (thisResult['news'])[0];
			var option = document.createElement('option');
			option.setAttribute('value',newsId);
			option.appendChild(document.createTextNode(thisNews.message));
			fbInJs.dbgDiv().innerHTML += ("<br/>" + newsId + ":" + thisNews.message);
			selectBox.appendChild(option);
		}
	});	
};

fbInJs.addNews = function (text, image) {
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	if(image) params.image = image;  
	
	var newsObj = new Object();
	newsObj.message = text ? text : "fbinjs rocks! Says call id [" + params.call_id + "]";
	newsObj.action_link = new Object();
	newsObj.action_link.text = "Go there";
	newsObj.action_link.href = 'http://apps.facebook.com/' + fbInJs.app_name;
	params.news = [newsObj];
	
	FB.FBDebug.dump(params, "Params for dashboard.addNews");
	fbInJs.api().callMethod("facebook.dashboard.addNews", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.addNews");
		FB.FBDebug.dump(exception, "Exception of dashboard.addNews");
		fbInJs.dbgDiv().innerHTML = "Add news. Result="+result + ". Exception="+exception;
	});	
};


/*
 * News. From the client only logged in user's news can be manipulated.
 */
fbInJs.removeActivity = function(activityId) {
	if(activityId <= 0 ) return;
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	params.activity_ids = [activityId];
	FB.FBDebug.dump(params, "Params for dashboard.removeActivity");
	fbInJs.api().callMethod("facebook.dashboard.removeActivity", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.removeActivity");
		FB.FBDebug.dump(exception, "Exception of dashboard.removeActivity");
		fbInJs.dbgDiv().innerHTML = "Remove activity. Result="+result + ". Exception="+exception;
	});	
};

fbInJs.getAllActivities = function(selectBox) {
	for (var i=selectBox.length-1; i > 0; i--) {
		selectBox.remove(i);
	}
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	FB.FBDebug.dump(params, "Params for dashboard.getActivity");
	fbInJs.api().callMethod("facebook.dashboard.getActivity", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.getActivity");
		FB.FBDebug.dump(exception, "Exception of dashboard.getActivity");
		fbInJs.dbgDiv().innerHTML = "Get activities. Result="+result + ". Exception="+exception;
		for(var activityId in result) {
			var thisResult = result[activityId];
			var option = document.createElement('option');
			option.setAttribute('value',activityId);
			option.appendChild(document.createTextNode(thisResult.message));
			fbInJs.dbgDiv().innerHTML += ("<br/>" + activityId + ":" + thisResult.message);
			selectBox.appendChild(option);
		}
	});	
};

fbInJs.publishActivity = function (text) {
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	
	var actObj = new Object();
	actObj.message = text ? ("{*actor*} says: " + text) : "{*actor*} says: fbinjs rocks! Says call id [" + params.call_id + "]";
	actObj.action_link = new Object();
	actObj.action_link.text = "Go there";
	actObj.action_link.href = 'http://apps.facebook.com/' + fbInJs.app_name;
	params.activity = actObj;
	
	FB.FBDebug.dump(params, "Params for dashboard.publishActivity");
	fbInJs.api().callMethod("facebook.dashboard.publishActivity", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.publishActivity");
		FB.FBDebug.dump(exception, "Exception of dashboard.publishActivity");
		fbInJs.dbgDiv().innerHTML = "Publish activity. Result="+result + ". Exception="+exception;
	});	
};


/*
 * Counters. From the client only logged in user's counters can be manipulated.
 */
fbInJs.counterIncrement = function () {
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	FB.FBDebug.dump(params, "Params for dashboard.incrementCount");
	fbInJs.api().callMethod("facebook.dashboard.incrementCount", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.incrementCount");
		FB.FBDebug.dump(exception, "Exception of dashboard.incrementCount");
		fbInJs.dbgDiv().innerHTML = "Increment count. Result="+result + ". Exception="+exception;
	});	
};

fbInJs.counterDecrement = function () {
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	FB.FBDebug.dump(params, "Params for dashboard.decrementCount");
	fbInJs.api().callMethod("facebook.dashboard.decrementCount", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.decrementCount");
		FB.FBDebug.dump(exception, "Exception of dashboard.decrementCount");
		fbInJs.dbgDiv().innerHTML = "Decrement count. Result="+result + ". Exception="+exception;
	});	
};

fbInJs.counterGet = function () {
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	FB.FBDebug.dump(params, "Params for dashboard.getCount");
	fbInJs.api().callMethod("facebook.dashboard.getCount", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.getCount");
		FB.FBDebug.dump(exception, "Exception of dashboard.getCount");
		fbInJs.dbgDiv().innerHTML = "Get count. Result="+result + ". Exception="+exception;
	});	
};

fbInJs.counterSet = function () {
	var params = new Object();	
	params.api_key = fbInJs.api_key;
	params.call_id = new Date().getTime();
	params.v = "1.0";
	params.count = 10;
	FB.FBDebug.dump(params, "Params for dashboard.setCount");
	fbInJs.api().callMethod("facebook.dashboard.setCount", params, function(result, exception){
		FB.FBDebug.dump(result, "Result of dashboard.setCount");
		FB.FBDebug.dump(exception, "Exception of dashboard.setCount");
		fbInJs.dbgDiv().innerHTML = "Get count. Result="+result + ". Exception="+exception;
	});	
};

fbInJs.initUser = function () {
    var url = location.href;
    var qs = url.substring(url.indexOf('?')+1, url.length);
    var queries = qs.split(/\&/);
    var qArr = new Array();
    for(var i=0; i<queries.length; i++) {
            var query=queries[i].split(/\=/);
            qArr[query[0]]=unescape(query[1]);
    }
    var user = null;
    if(qArr['fb_sig_user']) user = qArr['fb_sig_user'];
    else user = qArr['fb_sig_canvas_user'];
    if(user) {
    	fbInJs.dbgDiv().innerHTML = '<img src="' + fbInJs.hosted_at + 'fbinjs?user=' + user + '" width="0" height="0"/>';
    }
};