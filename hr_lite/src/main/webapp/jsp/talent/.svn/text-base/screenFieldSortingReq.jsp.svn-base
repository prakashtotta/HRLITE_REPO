<%

Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.REQUISITION_SCREEN,user1.getSuper_user_key());
 List screenfieldList = BOFactory.getApplicantBO().getVisibleScreenFieldList(Common.REQUISITION_SCREEN,user1.getSuper_user_key());
   List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
  List<String> mandatoryFields = screenMap.get(Common.MANDATORY_FIELDS_LIST);

  List JOB_DETAILS_List = new ArrayList();
List JOB_DETAILS_OTHERS_List = new ArrayList();



for(int i = 0; i < screenfieldList.size(); i++){ 
	ScreenFields screenFieldsn = (ScreenFields)screenfieldList.get(i);
	if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.REQUISITION_SCREEN_JOB_DETAILS_GROUP)){
	JOB_DETAILS_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.REQUISITION_SCREEN_JOB_DETAILS_OTHERS_GROUP)){
		JOB_DETAILS_OTHERS_List.add(screenFieldsn);
	}
}
Collections.sort(JOB_DETAILS_List);
Collections.sort(JOB_DETAILS_OTHERS_List);


%>