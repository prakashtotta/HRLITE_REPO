package com.form.employee;

import com.bean.User;
import com.bean.employee.UserDependents;
import com.resources.Constant;
import com.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class UserDependentsForm
  extends ActionForm
{
  public long userDependentId;
  public long userId;
  public String name;
  public String relationship;
  private String dateofbirth = "";
  private List userDependentsList;
  public String relationshipOther;
  
  public String getRelationshipOther()
  {
    return this.relationshipOther;
  }
  
  public void setRelationshipOther(String relationshipOther)
  {
    this.relationshipOther = relationshipOther;
  }
  
  public List getUserDependentsList()
  {
    return this.userDependentsList;
  }
  
  public void setUserDependentsList(List userDependentsList)
  {
    this.userDependentsList = userDependentsList;
  }
  
  public String getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(String dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  private List relationsipList = new ArrayList();
  
  public List getRelationsipList()
  {
    return this.relationsipList;
  }
  
  public void setRelationsipList(List relationsipList)
  {
    this.relationsipList = relationsipList;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getRelationship()
  {
    return this.relationship;
  }
  
  public void setRelationship(String relationship)
  {
    this.relationship = relationship;
  }
  
  public long getUserDependentId()
  {
    return this.userDependentId;
  }
  
  public void setUserDependentId(long userDependentId)
  {
    this.userDependentId = userDependentId;
  }
  
  public void fromValue(UserDependents userdependent, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.userDependentId = userdependent.getUserDependentId();
    this.userId = userdependent.getUserId();
    this.name = userdependent.getName();
    this.relationship = userdependent.getRelationship();
    this.relationshipOther = userdependent.getRelationshipOther();
    if (userdependent.getDateofbirth() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.dateofbirth = DateUtil.convertDateToStringDate(userdependent.getDateofbirth(), datepattern);
    }
  }
  
  public UserDependents toValue(UserDependents userdependent, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    userdependent.setUserId(this.userId);
    userdependent.setName(this.name);
    userdependent.setRelationship(this.relationship);
    userdependent.setRelationshipOther(this.relationshipOther);
    

    return userdependent;
  }
}
