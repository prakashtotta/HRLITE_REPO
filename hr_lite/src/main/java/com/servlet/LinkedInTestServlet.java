package com.servlet;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.resources.Constant;
import java.io.IOException;
import java.io.PrintStream;
import java.util.EnumSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LinkedInTestServlet
  extends HttpServlet
{
  private static final String AUTH_TOKEN_PARAMETER = "oauth_token";
  private static final String AUTH_TOKEN_VERIFIER_PARAMETER = "oauth_verifier";
  private static final String CONSUMER_KEY_OPTION = Constant.getValue("linkedin.consumer.key");
  private static final String CONSUMER_SECRET_OPTION = Constant.getValue("linkedin.consumer.sercret");
  
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    try
    {
      String oauthToken = req.getParameter("oauth_token");
      String oauthVerifier = req.getParameter("oauth_verifier");
      String consumerKeyValue = CONSUMER_KEY_OPTION;
      String consumerSecretValue = CONSUMER_SECRET_OPTION;
      HttpSession session = req.getSession();
      
      LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(consumerKeyValue, consumerSecretValue);
      if ((oauthToken == null) && (oauthVerifier == null))
      {
        LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(Constant.getValue("external.url") + "linkedin/test");
        System.out.println("Fetching request token from LinkedIn..." + requestToken);
        
        session.setAttribute("requestToken", requestToken);
        String authUrl = requestToken.getAuthorizationUrl();
        resp.sendRedirect(authUrl);
      }
      else
      {
        String sVerifier = req.getParameter("oauth_verifier");
        LinkedInRequestToken requestToken = (LinkedInRequestToken)session.getAttribute("requestToken");
        LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, sVerifier);
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Token secret: " + accessToken.getTokenSecret());
        session.setAttribute("oauth_verifier", sVerifier);
        session.setAttribute("Accesstoken", accessToken.getToken());
        session.setAttribute("Tokensecret", accessToken.getTokenSecret());
        
        LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
        LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
        System.out.println("Fetching profile for current user.");
        Person profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
        Person profile2 = client.getProfileById(profile.getId());
        
        resp.sendRedirect("page to which you want to redirect after processing the result");
      }
    }
    catch (Throwable ex)
    {
      ex.printStackTrace();
    }
  }
}
