/**
 * 
 */
package com.util;

import java.net.URLEncoder;

import com.resources.Constant;

/**
 * @author Prakash
 *
 */
public class CommonUtils {

	/**
	 * Method returns the Facebook app id configured in job.properties
	 * @return
	 */
	public static String getFacbookAppId(){
		return Constant.getValue("facebook.app.id");
	}
	
	/**
	 * Method returns the Facebook app key configured in job.properties
	 * @return
	 */
	public static String getFacbookAppKey(){
		return Constant.getValue("facebook.app.secrete.key");
	}
	
	/**
	 * Method returns the Twitter app id configured in job.properties
	 * @return
	 */
	public static String getTwitterAppId(){
		return Constant.getValue("twitter.app.id");
	}
	
	/**
	 * Method returns the Twitter app key configured in job.properties
	 * @return
	 */
	public static String getTwitterAppKey(){
		return Constant.getValue("twitter.app.secrete.key");
	}
	
	/**
	 * Method returns the Facebook login auth url configured in job.properties
	 * @return
	 */
	public static String getFacbookLoginAuthUrl(){
		return Constant.getValue("facebook.login.auth.url");
	}
	
	/**
	 * Method returns the Facebook login redirect url configured in job.properties
	 * @return
	 */
	public static String getFacbookLoginRedirectUrl(){
		return Constant.getValue("facebook.login.redirect.url");
	}
	
	/**
	 * Method returns the Facebook login url configured in job.properties
	 * @return
	 */
	public static String getFacbookLoginUrl(){
		return Constant.getValue("facebook.login.url");
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	
	public static String getFacebookLoginUrl() {
      return getFacbookLoginUrl()+
              "client_id=" +getFacbookAppId()+
              "&redirect_uri="+URLEncoder.encode(getFacbookLoginRedirectUrl())+"&scope=email";             
	}
	
	/**
	 * @param authCode
	 * @return
	 */
	public static String getFacebookAuthURL(String authCode){
		return getFacbookLoginAuthUrl()+
	              "client_id=" +getFacbookAppId()+
	              "&redirect_uri="+URLEncoder.encode(getFacbookLoginRedirectUrl())+ 
	              "&client_secret=" +getFacbookAppKey()+ 
	              "&code=" + authCode;
	}
}
