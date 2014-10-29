
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">

<head>
	<script src="http://static.ak.facebook.com/js/api_lib/v0.4/FeatureLoader.js.php" type="text/javascript"></script>
	<script src="fbinjs.js" type="text/javascript"></script>
	<link rel="stylesheet" href="http://static.ak.connect.facebook.com/css/fb_connect.css" type="text/css" />
	<style type="text/css">
		body 
		{ 
			font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
			font-size:11px;
		}
		#mydebugdiv 
		{
			border-width: .2em;
			border-style: dotted;
			border-color: #900;
		}
	</style>	
</head>

<body onLoad="fbInJs.doFBInit();">
	<form>
		<h3>Facebook In Javascript</h3>
		<div id="loginLink">
			Welcome. Before you can proceed, you need to <a href="#" onclick="fbInJs.askForAuthentication(); return false;">login and authenticate</a> the application.
		</div>
		<div id="logoutLink">
			<fb:profile-pic uid="loggedinuser" facebook-logo="true" linked="true"></fb:profile-pic>
			Hello <fb:name uid="loggedinuser" firstnameonly="true" useyou="false"></fb:name>! 
			<a href="#" onclick="fbInJs.logoutRevokeAuthentication(); return false;">Logout</a>
		</div>
		<br/>
		
		<hr noshade="noshade"/>
		<b>Information about yourself, and your friends.</b><br/>
		<input type="button" onclick="fbInJs.showCurrentUser();" value="Show Current User"></input>
		<input type="button" onclick="fbInJs.showFriends();" value="Show All Friends"></input>
		<br/>
		<input type="button" onclick="fbInJs.getUserInfo(document.getElementById('infoId').value);" value="Get User Info"></input>
		<select name="infoId" id="infoId"><option value="0">Select a Friend</option></select>
		<br/>
		<hr noshade="noshade"/>
		<b>Check if some of your friends are related.</b><br/>
		<input type="button" onclick="fbInJs.checkIfFriends(document.getElementById('id1').value, document.getElementById('id2').value);" value="Check Friends"></input>
		<select name="id1" id="id1"> <option value="0">Select a Friend</option> </select> and <select name="id2" id="id2"> <option value="0">Select a Friend</option> </select>
		<br/>
		<hr noshade="noshade"/>
		<b>Application news.</b><br/>
		Text: <input type="text" name="newsText" id="newsText"/> &nbsp; &nbsp;
		Image URL (optional): <input type="img" name="newsImg" id="newsImg"/>
		<input type="button" onclick="fbInJs.addNews(document.getElementById('newsText').value, document.getElementById('newsImg').value);" value="Add News"></input><br/>
		<input type="button" onclick="fbInJs.getAllNews(document.getElementById('newsId'));" value="Get All News"></input><br/>
		<select name="newsId" id="newsId"><option value="0">Select a News</option></select> <input type="button" onclick="fbInJs.clearNews(document.getElementById('newsId').value); fbInJs.getAllNews(document.getElementById('newsId'));" value="Delete News"></input><br/>
		<br/>
		<hr noshade="noshade"/>
		<b>Application activity.</b><br/>
		Text: <input type="text" name="activityText" id="activityText"/> &nbsp; &nbsp;
		<input type="button" onclick="fbInJs.publishActivity(document.getElementById('activityText').value);" value="Add Activity"></input><br/>
		<input type="button" onclick="fbInJs.getAllActivities(document.getElementById('activityId'));" value="Get All Activities"></input><br/>
		<select name="activityId" id="activityId"><option value="0">Select an Activity</option></select> <input type="button" onclick="fbInJs.removeActivity(document.getElementById('activityId').value); fbInJs.getAllActivities(document.getElementById('activityId'));" value="Delete Activity"></input><br/>
		<br/>
		<hr noshade="noshade"/>
		<b>Notification through Counters.</b><br/>
		<input type="button" onclick="fbInJs.counterIncrement();" value="Increment Counter"></input>
		<input type="button" onclick="fbInJs.counterDecrement();" value="Decrement Counter"></input>
		<input type="button" onclick="fbInJs.counterGet();" value="Get Counter"></input>
		<input type="button" onclick="fbInJs.counterSet();" value="Set Counter To 10"></input>
		<hr noshade="noshade"/>
		<b>Facebook messaging through Wall and Notifications.</b><br/>
		Write on your wall 
		<input type="button" onclick="fbInJs.postOnWall(null, fbInJs.currUser());" value="My Wall"></input>
		<br/>
		Message to someone else. Select a friend to send message and push the message type button.<br/> 
		<input type="button" onclick="fbInJs.postOnWall(document.getElementById('notifToId').value, fbInJs.currUser());" value="Friend's Wall"></input>
		<input type="button" onclick="fbInJs.sendNotification(document.getElementById('notifToId').value);" value="User Notification"></input>
		<input type="button" onclick="fbInJs.sendAppNotification(document.getElementById('notifToId').value);" value="App Notification"></input><br/>
		<select name="notifToId" id="notifToId"><option value="0">Select a Friend</option></select>
		<br/>
		<hr noshade="noshade"/>
		<b>Miscellaneous</b><br/>
		Get permanent authorization for the app. 
		<input type="button" onclick="FB.Connect.showPermissionDialog('offline_access');" value="Ask Permissions for Offline Access"/>
		<br/>
		Invite friends to use your app.
		<input type="button" onclick="fbInJs.inviteFriends()" value="Invite Friends"></input>
		<br/>
		<a href="#" onclick="FB.Connect.showBookmarkDialog(); return false;">Bookmark this app.</a> <fb:bookmark type="on-facebook"></fb:bookmark>		
		<hr noshade="noshade"/>
		<br/>

		<div id="fselect" style="display:none; position:absolute; margin:0px 0px 0px 0px; padding:0px; z-index:21; top:0px; width:466px; height:730px; ">
			<iframe src="http://fbinjs.appspot.com/friendInvite.html" width="466" height="730" scrolling="no" frameborder="0"></iframe>
		</div>
		
		<b>Results, if any, appear below:</b><br/>
		<div id="mydebugdiv" width="500" height="1000"> </div>
	</form>
</body>
</html>

