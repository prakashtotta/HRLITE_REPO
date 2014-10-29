package com.bo;

import com.bean.ApplicantTags;
import com.bean.JobApplicant;
import com.bean.Tags;
import com.bean.User;
import com.common.Common;
import com.dao.LovDAO;
import com.util.StringUtils;
import java.util.List;

public class TagsBO
{
  LovDAO lovdao;
  
  public List getAllTagsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllTagsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllTags()
  {
    return this.lovdao.getAllTags();
  }
  
  public String getAllTagsAutoSuggestJSONMulti(long superUserKey, String type, String query)
  {
    String data = "";
    List tags = this.lovdao.getAllTags(superUserKey, type, query);
    for (int i = 0; i < tags.size(); i++)
    {
      Tags tag = (Tags)tags.get(i);
      data = data + "{ " + "\"" + "id" + "\"" + ":" + "\"" + tag.getTagName() + "\"," + "  " + "\"" + "name" + "\"" + ":" + "\"" + tag.getTagName() + "\"" + "}" + ",";
    }
    if (!StringUtils.isNullOrEmpty(data)) {
      data = data.substring(0, data.length() - 1);
    }
    return data;
  }
  
  public String getAllTagsByApplicantJSONMultiple(long applicantId)
  {
    String data = "";
    List tags = this.lovdao.getAllTagsByApplicantId(applicantId);
    for (int i = 0; i < tags.size(); i++)
    {
      ApplicantTags tag = (ApplicantTags)tags.get(i);
      data = data + "{ " + "\"" + "id" + "\"" + ":" + "\"" + tag.getTagname() + "\"," + "  " + "\"" + "name" + "\"" + ":" + "\"" + tag.getTagname() + "\"" + "}" + ",";
    }
    if (!StringUtils.isNullOrEmpty(data)) {
      data = data.substring(0, data.length() - 1);
    }
    return data;
  }
  
  public List getAllTagsForPaginationBySearchCriteria(User user, String name, String type, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllTagsForPaginationBySearchCriteria(user, name, type, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllTagsBySearchCriteria(User user, String name, String type)
  {
    return this.lovdao.getAllTagsBySearchCriteria(user, name, type);
  }
  
  public void saveApplicantTag(User user1, ApplicantTags apptag)
  {
    this.lovdao.saveApplicantTag(apptag);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantIdNameEmailByID(apptag.getApplicantId());
    BOFactory.getApplicantTXBO().addTagActivity(applicant, user1, apptag.getTagname(), "Applicant tagged", Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
  }
  
  public void deleteApplicantTag(User user1, long applicantId, String tagname)
  {
    this.lovdao.deleteApplicantTag(applicantId, tagname);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantIdNameEmailByID(applicantId);
    BOFactory.getApplicantTXBO().addTagActivity(applicant, user1, tagname, "Applicant untagged", Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
}
