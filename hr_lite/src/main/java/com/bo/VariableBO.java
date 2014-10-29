package com.bo;

import com.bean.lov.FormVariablesMap;
import com.bean.lov.Variables;
import com.common.ValidationException;
import com.dao.VariablesDAO;
import java.util.List;

public class VariableBO
{
  VariablesDAO variabledao;
  
  public List getAllVariableDetailsForPagination(int pageSize, int startIndex, long super_user_key, String dir_str, String sort_str)
  {
    return this.variabledao.getAllVariableDetailsForPagination(pageSize, startIndex, super_user_key, dir_str, sort_str);
  }
  
  public List getAllVariableDetails(long super_user_key)
  {
    return this.variabledao.getAllVariableDetails(super_user_key);
  }
  
  public List getFormVariablesList(String formcode, long idvalue, long super_user_key)
  {
    return this.variabledao.getFormVariablesList(formcode, idvalue, super_user_key);
  }
  
  public List getAllFormVariablesListForApplicant(long super_user_key)
  {
    return this.variabledao.getAllFormVariablesListForApplicant(super_user_key);
  }
  
  public List getAllFormVariablesListForRequistion(long super_user_key)
  {
    return this.variabledao.getAllFormVariablesListForRequistion(super_user_key);
  }
  
  public List getFormVariablesListWithoutIdValue(String formcode, long super_user_key)
  {
    return this.variabledao.getFormVariablesListWithoutIdValue(formcode, super_user_key);
  }
  
  public Variables getVariableByVaribleCode(String variableCode, long super_user_key)
  {
    return this.variabledao.getVariableByVaribleCode(variableCode, super_user_key);
  }
  
  public List getVariablesValues(long idvalue, String formcode)
  {
    return this.variabledao.getVariablesValues(idvalue, formcode);
  }
  
  public List getVariablesValuesForApplicant(long idvalue)
  {
    return this.variabledao.getVariablesValuesForApplicant(idvalue);
  }
  
  public Variables saveVariable(Variables variables, String[] listdata, String defaultselect)
  {
    return this.variabledao.saveVariable(variables, listdata, defaultselect);
  }
  
  public Variables updateVariable(Variables variables, String[] listdata, String defaultselect)
  {
    return this.variabledao.updateVariable(variables, listdata, defaultselect);
  }
  
  public Variables getVariablesDetailsByisMandatory(String id)
  {
    return this.variabledao.getVariablesDetailsByisMandatory(id);
  }
  
  public Variables getVariablesDetails(String id)
  {
    return this.variabledao.getVariablesDetails(id);
  }
  
  public void deleteVariable(long id)
    throws ValidationException, Exception
  {
    this.variabledao.deleteVariable(id);
  }
  
  public void deleteVariableListData(long variableid)
  {
    this.variabledao.deleteVariableListData(variableid);
  }
  
  public List getVariableListData(String id)
  {
    return this.variabledao.getVariableListData(id);
  }
  
  public List getFormVariablesMapByFormCode(String code, long super_user_key)
  {
    return this.variabledao.getFormVariablesMapByFormCode(code, super_user_key);
  }
  
  public List getFormVariablesMapByFormCodeAndId(String code, long id)
  {
    return this.variabledao.getFormVariablesMapByFormCodeAndId(code, id);
  }
  
  public boolean isVariableIdAttachedToScreenCode(String code, long variableid, long super_user_key)
  {
    return this.variabledao.isVariableIdAttachedToScreenCode(code, variableid, super_user_key);
  }
  
  public void deleteFormVariableMap(String formCode, long super_user_key)
  {
    this.variabledao.deleteFormVariableMap(formCode, super_user_key);
  }
  
  public void deleteFormVariableMapByFormCodeAndId(String formCode, long id, long super_user_key)
  {
    this.variabledao.deleteFormVariableMapByFormCodeAndId(formCode, id, super_user_key);
  }
  
  public FormVariablesMap updateFormVariableMap(FormVariablesMap formvariableMap)
  {
    return this.variabledao.updateFormVariableMap(formvariableMap);
  }
  
  public int getVariableByCriteraCount(String vname, String vtype, long super_user_key)
  {
    return this.variabledao.getVariableByCriteraCount(vname, vtype, super_user_key);
  }
  
  public List getVariableByCritera(String vname, String vtype, long super_user_key, int start, int range)
  {
    return this.variabledao.getVariableByCritera(vname, vtype, super_user_key, start, range);
  }
  
  public VariablesDAO getVariabledao()
  {
    return this.variabledao;
  }
  
  public void setVariabledao(VariablesDAO variabledao)
  {
    this.variabledao = variabledao;
  }
}
