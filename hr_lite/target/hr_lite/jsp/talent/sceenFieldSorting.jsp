<%

List PERSONAL_INFO_List = new ArrayList();
List WORK_PREFERENCE_List = new ArrayList();
List EDUCATION_List = new ArrayList();
List PROFILE_List = new ArrayList();
List OTHER_List = new ArrayList();
List SOCIAL_List = new ArrayList();
List BACKGROUND_List = new ArrayList();


for(int i = 0; i < screenfieldList.size(); i++){ 
	ScreenFields screenFieldsn = (ScreenFields)screenfieldList.get(i);
	if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_PERSONAL_INFO_GROUP)){
	PERSONAL_INFO_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_WORK_PREFERENCE_GROUP)){
		WORK_PREFERENCE_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_EDUCATION_GROUP)){
		EDUCATION_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_PROFILE_GROUP)){
		PROFILE_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_OTHER_GROUP)){
		OTHER_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_SOCIAL_GROUP)){
		SOCIAL_List.add(screenFieldsn);
	}else if(!StringUtils.isNullOrEmpty(screenFieldsn.getGroupCode()) && screenFieldsn.getGroupCode().equals(Common.APPLICANT_SCREEN_BACKGROUND_GROUP)){
		BACKGROUND_List.add(screenFieldsn);
	}
}

Collections.sort(PERSONAL_INFO_List);
Collections.sort(WORK_PREFERENCE_List);
Collections.sort(EDUCATION_List);
Collections.sort(PROFILE_List);
Collections.sort(OTHER_List);
Collections.sort(SOCIAL_List);
Collections.sort(BACKGROUND_List);


%>


<%@ include file="../talent/screenFieldSortingWorkExpTab.jsp" %>
<%@ include file="../talent/screenFieldSortingEducationTab.jsp" %>

