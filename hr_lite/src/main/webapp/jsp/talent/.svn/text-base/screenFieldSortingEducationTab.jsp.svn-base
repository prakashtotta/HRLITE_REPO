<%

List EDUCATION_DETAILS_List = new ArrayList();
List CERTIFICATION_List = new ArrayList();

for(int i = 0; i < screenfieldListEduTab.size(); i++){ 
	ScreenFields screenFieldsn = (ScreenFields)screenfieldListEduTab.get(i);
if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_EDUCATION_DETAILS_GROUP)){
		EDUCATION_DETAILS_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_CERTIFICATION_DETAILS_GROUP)){
		CERTIFICATION_List.add(screenFieldsn);
	}
}

Collections.sort(EDUCATION_DETAILS_List);
Collections.sort(CERTIFICATION_List);
%>

  <%

 // List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFieldsEducationTab = screenMapEducationDetials.get(Common.MANDATORY_FIELDS_LIST);
 %>