<%

List WORK_EXPERIENCE_List = new ArrayList();
List SKIL_List = new ArrayList();

for(int i = 0; i < screenfieldListWorkTab.size(); i++){ 
	ScreenFields screenFieldsn = (ScreenFields)screenfieldListWorkTab.get(i);
if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_WES_WORK_EXPERIENCE_GROUP)){
		WORK_EXPERIENCE_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_WES_SKILL_GROUP)){
		SKIL_List.add(screenFieldsn);
	}
}

Collections.sort(WORK_EXPERIENCE_List);
Collections.sort(SKIL_List);
%>

 <%

 // List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFieldsWorkExp = screenMapWorkExp.get(Common.MANDATORY_FIELDS_LIST);
 %>