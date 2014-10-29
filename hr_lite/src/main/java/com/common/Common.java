package com.common;

import com.util.StringUtils;

public class Common
{
  public static final String USER_DATA = "user_data";
  public static final String AGENCY_DATA = "agency_data";
  public static final String APPLICANT_USER_DATA = "applicant_user_data";
  public static final String EMPLOYEE_REFFERAL_DATA = "employee_refferal_data";
  public static String LOGIN_ERROR_MESSAGE = "error.login";
  public static String ERROR_MSG = "ERROR_MSG";
  public static final String ADMIN_ROLE_CODE = "Admin";
  public static final String SUPER_USER_KEY = "superUserKey";
  public static String OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT = "Applicant";
  public static String APPLICANT_CREATED_BY_TYPE_INTERNAL = "Internal";
  public static String APPLICANT_CREATED_BY_TYPE_EMAIL_READER = "EmailReader";
  public static String APPLICANT_CREATED_BY_TYPE_AGENCY = "Agency";
  public static String APPLICANT_CREATED_BY_TYPE_REFERRAL = "Refferal";
  public static String APPLICANT_CREATED_BY_TYPE_EMAIL = "Email by applicant";
  public static String APPLICANT_CREATED_BY_TYPE_OUT_SIDE_JOB = "External Jobsource";
  public static String SYSTEM_RULE_REQUISTION_APPROVER = "REQUISITION_APPROVERS";
  public static String SYSTEM_RULE_OFFER_APPROVER = "OFFER_APPROVERS";
  public static String SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS = "PUBLISH_REQUISTIONS";
  public static final long DEFAULT_TALENT_POOL_ID = 9999999L;
  public static final long TALENT_POOL_REQUISTION_ID = 9223372036854775807L;
  public static final int INTERVIEW_CLEARED_VALUE = 1;
  public static final int INTERVIEW_REJECTED_VALUE = 2;
  public static final int INTERVIEW_HOLD_VALUE = 3;
  public static final int RESUME_SOURCE_AGENCY_ID = 5;
  public static final int RESUME_SOURCE_FERERRAL_ID = 1;
  public static final String REFERRAL_REDEMPTION_NOT_RELEASED = "Not Released";
  public static final String REFERRAL_REDEMPTION_RELEASED = "Released";
  public static final String RULE_CRITERIA_AFTER_JOINING = "AFTER-JOINING";
  public static final String RULE_CRITERIA_AFTER_UPLOAD = "AFTER-UPLOAD";
  public static final String PRE_APPLICATION_SUBMITTED = "Pre Application Submitted";
  public static final String IN_SCREENING = "In Screening";
  public static final String CLEARED_IN_SCREENING = "Cleared-In Screening";
  public static final String APPLICATION_SUBMITTED = "Application Submitted";
  public static final String TALENT_POOL = "Talent Pool";
  public static final String EXAM_ASSIGNED = "Exam Screening";
  public static final String QUESTIONNAIRE_ASSIGNED = "Questionnaire Assigned";
  public static final String EXAM_ASSIGNED_DELETED = "Exam Screening deleted";
  public static final String QUESTIONNAIRE_ASSIGNED_DELETED = "Questionnaire Assigned deleted";
  public static final String TALENT_POOL_TEMP = "Talent Pool Temp";
  public static final String SCREENING = "Screening";
  public static final String CLEARED = "Cleared";
  public static final String REJECTED = "Rejected";
  public static final String ON_HOLD = "OnHold";
  public static final String ON_BOARD = "On Board - Joined";
  public static final String RESIGNED = "RESIGNED";
  public static final String CLOSED = "Closed";
  public static final String SYSTEM = "system";
  public static final String OPEN = "Open";
  public static final String DELETED = "Deleted";
  public static final String INTERVIEW_ROUND = "Interview Round";
  public static final String INTERVIEW = "Interview";
  public static final String IN_INTERVIEW_PROCESS = "In Interview Process";
  public static final String IN_OFFER_PROCESS = "In Offer Process";
  public static final String OFFER_IN_PROCESS = "Offer";
  public static final String OFFER = "Offer Released";
  public static final String OFFER_INITIATED = "Offer Initiated-In Approval";
  public static final String OFFER_APPROVED_FINAL = "Offer_approved_final";
  public static final String READY_TO_RELEASE_OFFER = "Ready for Release Offer";
  public static final String OFFER_APPROVAL_REJECTED = "Offer rejected by approver";
  public static final String OFFER_APPROVAL_APPROVED = "Offer approved by approver";
  public static final String REFERENCE_CHECK = "Reference Check";
  public static final String OFFER_DECLINED = "Offer Declined";
  public static final String OFFER_ACCEPTED = "Offer Accepted";
  public static final String IN_APPROVAL = "In Approval";
  public static final String APPROVED = "Approved";
  public static final String IN_APPROVAL_REJECTED = "In Approval-Rejected";
  public static final String DRAFT = "Draft";
  public static final String ACTIVE = "Active";
  public static final String USER_TYPE_EMPLOYEE = "Employee";
  public static final String USER_TYPE_VENDOR = "Vendor";
  public static final String USER_CODE_VENDOR = "VENDOR";
  public static final String USER_TYPE_REFERAL = "Referal";
  public static final String USER_CODE_REFERAL = "Referal";
  public static final String TARGET_JOINING_DATE = "Target Joining Date";
  public static String APPLICANT_LIST = "app_list";
  public static String APPLICANT_COUNT = "app_count";
  public static String APPLICANT_ATTACHMENT_TYPE_RESUME = "resume";
  public static String JOBS_LIST = "jobs_list";
  public static String JOBS_COUNT = "jobs_count";
  public static String ORGS_LIST = "orgs_list";
  public static String ORGS_COUNT = "orgs_count";
  public static String USER_GROUP_LIST = "usrgroup_list";
  public static String USER_GROUP_COUNT = "usrgroup_count";
  public static final String REQ_TO_REQ_MOVE = "REQ_TO_REQ_MOVE";
  public static final String TALENT_POOL_TO_REQ_MOVE = "TALENT_POOL_TO_REQ_MOVE";
  public static final String REQ_TO_TALENT_POOL_MOVE = "REQ_TO_TALENT_POOL_MOVE";
  public static final String TALENT_POOL_TO_TALENT_POOL_MOVE = "TALENT_POOL_TO_TALENT_POOL_MOVE";
  public static String REQUISTION_LIST = "req_list";
  public static String REQUISTION_COUNT = "req_count";
  public static String REQUISTION_TEMPLATE_LIST = "req_template_list";
  public static String REQUISTION_TEMPLATE_COUNT = "req_template_count";
  public static String KRA_LIST = "kra_list";
  public static String KRA_COUNT = "kra_count";
  public static final String CONVERTED_TO_USER = "CONVERTED_TO_USER";
  public static final String PACKAGE_FREE = "FREE";
  public static final String PACKAGE_BASIC = "BASIC";
  public static final String PACKAGE_ADVANCE = "ADVANCE";
  public static final String PACKAGE_ENTERPRIZE = "ENTERPRIZE";
  public static final String PACKAGE_ENTERPRIZE_MAX = "ENTERPRIZE-MAX";
  public static final String GOAL_ORGANIZATION_TYPE = "ORGANIZATION";
  public static final String GOAL_JOB_TITLE_TYPE = "JOB_TITLE";
  public static final String YES = "Yes";
  public static final String NO = "No";
  public static final String MALE = "M";
  public static final String FEMALE = "F";
  public static final String HIRING_MGR_SEARCH_ROLE = "HIRING_MGR";
  public static final String RECRUITER_SEARCH_ROLE = "RECRUITER";
  public static final String ALL_REQUISITION_SEARCH_ROLE = "ALL_REQUISITION";
  public static final String MY_ORG_REQUISITION_SEARCH_ROLE = "MY_ORG_REQUISITION";
  public static final String ALL_APPLICANTS_SEARCH_ROLE = "ALL_APP_EXP";
  public static final String ON_BOARD_APPLICANTS_SEARCH_ROLE = "ONBOARD_APP_EXP";
  public static final String ON_BOARDING_NOT_STARTED = "Not started";
  public static final String ON_BOARDING_STARTED = "Started";
  public static final String ON_BOARDING_COMPLETED = "Completed";
  public static final String LOV_VALUE_OTHERS = "Others";
  public static String REFERRAL_SCHEME_TYPE_EMPLOYEE = "E";
  public static String REFERRAL_SCHEME_TYPE_AGENCY = "V";
  public static char DISTANCE_UNIT_MILES = 'M';
  public static final String APPLICATIONS_TO_OFFER_RATIO = "Applications To Offer Ratio";
  public static final String APPLICATIONS_TO_ONBOARD_RATIO = "Applications To Onboard Ratio";
  public static final String OFFER_TO_ONBOARD_RATIO = "Offer To Onboard Ratio";
  public static final String OVERALL_PERFORMANCE = "Overall Performance";
  public static final String REQUISITION_CREATED = "REQUISITION_CREATED";
  public static final String REQUISITION_UPDATED = "REQUISITION_UPDATED";
  public static final String REQUISITION_HIRING_MGR_UPDATED = "REQUISITION_HIRING_MGR_UPDATED";
  public static final String REQUISITION_RECRUITER_UPDATED = "REQUISITION_RECRUITER_UPDATED";
  public static final String REQUISITION_APPROVER_REASSIGNED = "REQUISITION_APPROVER_REASSIGNED";
  public static final String REQUISITION_APPROVAL_REJECTED = "REQUISITION_APPROVAL_REJECTED";
  public static final String REQUISITION_DELETED = "REQUISITION_DELETED";
  public static final String REQUISITION_CLOSED = "REQUISITION_CLOSED";
  public static final String REQUISITION_UPDATE_PUBLISH_INFO = "REQUISITION_UPDATE_PUBLISH_INFO";
  public static final String REQUISITION_PUBLISHED = "REQUISITION_PUBLISHED";
  public static final String REQUISITION_APPROVAL_INITIATED = "REQUISITION_APPROVAL_INITIATED";
  public static final String REQUISITION_APPROVAL_APPROVED = "REQUISITION_APPROVAL_APPROVED";
  public static final String REQUISITION_RECRUITER_ADDED = "REQUISITION_RECRUITER_ADDED";
  public static final String REQUISITION_ATTACHMENT_DELETED = "REQUISITION_ATTACHMENT_DELETED";
  public static final String REQUISITION_ATTACHMENT_ADDED = "REQUISITION_ATTACHMENT_ADDED";
  public static final String REQUISITION_APPROVAER_ADDED = "REQUISITION_APPROVAER_ADDED";
  public static final String REQUISITION_APPROVAER_DELETED = "REQUISITION_APPROVAER_DELETED";
  public static final String REQUISITION_RESET = "REQUISITION_RESET";
  public static final String REQUISITION_RECALLED = "REQUISITION_RECALLED";
  public static String DECLINED_REVIEW = "DECLINED_REVIEW";
  public static final String UOM_CURRENCY = "monetary";
  public static final String UOM_DAYS = "days";
  public static final String USER_STATUS_DELETED = "D";
  public static final String USER_STATUS_SUSPENDED = "I";
  public static final String USER_STATUS_ACTIVE = "A";
  public static final String OFFER_IN_NEGOTIATION = "Offer In Negotiation";
  public static final String OFFER_CANCELED = "Offer Canceled";
  public static final String APPLICANT_MARKED_FOR_DELETION = "Applicant marked for deletion";
  public static final String APPLICANT_RESTORED = "Applicant restored";
  public static final String MARK_DELETED = "Mark deleted";
  public static final String MARK_UNDO_DELETED = "Undo deleted";
  public static final String TAG_TYPE_APPLICANT = "APPLICANT";
  public static final String TAG_TYPE_REQUISITION = "REQUISITION";
  public static final String APPLICANT_TAGGED = "Applicant tagged";
  public static final String APPLICANT_TAG_DELETED = "Applicant untagged";
  public static final String APPLICANT_RECALLED = "Recalled";
  public static final String APPLICANT_USER_CREATED = "Applicant user created";
  public static final String APPLICANT_USER_ACTION_ADDED = "Applicant action added";
  public static final String APPLICANT_USER_ACTION_DELETED = "Applicant action deleted";
  public static final String APPLICANT_USER_ACTION_UPDATED = "Applicant action updated";
  public static final String OFFER_ATTACHMENT = "offerAttachment";
  public static final String APPLICANT_ATTACHMENT = "applicantAttachment";
  public static final String ACTION_ATTACHMENT = "actionAttachment";
  public static final String ADD_OFFER_ATTACHMENT = "ADD_OFFER_ATTACHMENT";
  public static final String DELETE_OFFER_ATTACHMENT = "DELETE_OFFER_ATTACHMENT";
  public static final String REDEMPTION_PAID = "REDEMPTION_PAID";
  public static final String COMMENT_TYPE_APPLICANT = "APPLICANT";
  public static final String COMMENT_TYPE_AGENCY = "Agency";
  public static final String COMMENT_TYPE_EMPLOYEE_REF = "Employee Ref";
  public static final String INIT_OFFER_ATTACHMENT = "initoffer";
  public static final String RELEASE_OFFER_ATTACHMENT = "reloffer";
  public static final String APPLICANTS_ATTACHMENT = "applicantdoc";
  public static final String REQ_ATTACHMENT = "reqattachment";
  public static final String FORM_ERROR_LIST = "errorList";
  public static final String FORM_VARIABLE_LIST = "formVariablesList";
  public static final String FORM_VARIABLE_DATA_LIST = "formVariableDataList";
  public static final String ERROR_LIST = "error_list";
  public static final String ERROR_LIST_PUBLISH = "error_list_publish";
  public static final String ERROR_LIST_WORK_EXP = "error_list_work_exp";
  public static final String ERROR_LIST_EDUCATION = "error_list_education";
  public static final String FORM_VARIABLE_REQUISTION = "REQUISITION_FORM";
  public static final String FORM_VARIABLE_REQUISTION_TMPL = "REQUISITION_TMPL_FORM";
  public static final String FORM_VARIABLE_APPLICANT = "APPLICANT_FORM";
  public static final String VARIABLE_TYPE_TEXT = "text";
  public static final String VARIABLE_TYPE_TEXTREA = "textarea";
  public static final String VARIABLE_TYPE_NUMERIC = "numeric";
  public static final String VARIABLE_TYPE_DATE = "date";
  public static final String VARIABLE_TYPE_LIST = "list";
  public static final String FILTER_VARIABLE_ORIGIN_SCREEN = "SCREEN";
  public static final String FILTER_VARIABLE_ORIGIN_CUSTOM = "CUSTOM";
  public static final String GREATER_THAN = "GREATER_THAN";
  public static final String EQUALS = "EQUALS";
  public static final String LESS_THAN = "LESS_THAN";
  public static final String BETWEEN = "BETWEEN";
  public static final String NOT_EQUALS = "NOT_EQUALS";
  public static final String NOT_NULL = "NOT_NULL";
  public static final String CONTAINS = "CONTAINS";
  public static final String STARTS_WITH = "STARTS_WITH";
  public static final String ENDS_WITH = "ENDS_WITH";
  public static final String GREATER_THAN_EQUAL = "GREATER_THAN_EQUAL";
  public static final String LESS_THAN_EQUAL = "LESS_THAN_EQUAL";
  public static final String NOT_EMPTY = "NOT_EMPTY";
  public static final String EMPTY = "EMPTY";
  public static final String OFFER_DECLINED_RESON = "offer_decline";
  public static final String OFFER_CANCEL_RESON = "offer_cancel";
  public static final String APPLICANT_REJECT_REASON = "applicant_reject";
  public static String REJECTION_EMAIL_COMPONENT = "rejection_email_to_applicant";
  public static String OFFER_EMAIL_COMPONENT = "offer_to_applicant";
  public static String INTERVIEW_FEEDBACK_EMAIL_COMPONENT = "interview_feedback";
  public static String INTERVIEW_SCHEDULE_EMAIL_COMPONENT = "interview_schedule";
  public static String OFFER_APPROVAL_EMAIL_COMPONENT = "send_for_offer_approval";
  public static String OFFER_APPROVE_REJECT_EMAIL_COMPONENT = "offer_approve_reject";
  public static String USER_CREATION_EMAIL_COMPONENT = "user_creation";
  public static String REFERRAL_USER_REGISTRATION = "referral_user_registration";
  public static String APPLICANT_USER_CREATION = "applicant_user_creation";
  public static String FORGOT_PASSWORD = "forgot_password";
  public static String REVIEW_SCHEDULE_EMAIL_COMPONENT = "review_schedule";
  public static String REVIEW_FEEDBACK_EMAIL_COMPONENT = "review_feedback";
  public static String TASK_ASSIGN = "task_assign";
  public static String TASK_COMPLETE = "task_complete";
  public static String REFER_FRIEND = "refer_friend";
  public static String EXAM_SCHEDULE = "exam_schedule";
  public static String COMMENT_TO_APPLICANT = "comment_to_applicant";
  public static String APPLICANT_SUBMIT = "applicant_submit";
  public static String QUESTIONNAIRE_SCHEDULE = "questionnaire";
  public static String REQUISTION_APPROVED = "Requisition approved";
  public static String REQUISTION_APPROVED_FINAL = "Requisition_approved_final";
  public static String ONLY_EMAIL_ONLY = "ONLY_EMAIL_ONLY";
  public static String MASS_EMAIL_TO_APPLICANT = "mass_email_to_applicant";
  public static String PRE_APPLICATION_SUBMIT = "pre_application_submit";
  public static String PRE_TALENT_POOL = "pre_talent_pool";
  public static String REQUISITION_CREATION = "requisition_creation";
  public static String INITIATE_APPROVAL_REQUISITION = "initiate_approval_requisition";
  public static String CLOSE_REQUISITION = "close_requisition";
  public static String DELETE_REQUISITION = "delete_requisition";
  public static String RECALL_REQUISITION = "recall_requisition";
  public static String APPROVE_REQUISITION = "approve_requisition";
  public static String REASSIGN_REQUISITION_APPROVAL = "reassign_requisition_approval";
  public static String REJECT_REQUISITION = "reject_requisition";
  public static String APPLICANT_MOVE = "applicant_move";
  public static String APPLICANT_MARK_DELETE = "applicant_mark_delete";
  public static String APPLICANT_MARK_UNDO_DELETE = "APPLICANT_MARK_UNDO_DELETE";
  public static String COMMENT_TO_ALL = "comment_to_all";
  public static String EVENT_TO_ALL = "event_to_all";
  public static String REQUISTION_APPROVAL_TASK = "Requisition Approval";
  public static String OFFER_APPROVAL_TASK = "Offer Approval";
  public static String REFERENCE_CHECK_TASK = "Reference Check";
  public static String APPLICANT_REVIEW_TASK = "Applicant Review";
  public static String REQUISTION_PUBLISH_TASK = "Requistion Publish";
  public static String OFFER_RELEASE_TASK = "Offer Release";
  public static String APPLICANT_IN_QUEUE = "Applicant In Queue";
  public static String ONBOARDING_TASK = "OnBoarding";
  public static String APPLICANT_INTERVIEW_TASK = "Applicant Interview";
  public static String MANUAL_ADD_TASK = "Assigned task";
  public static String INITIATE_ONBOARDING_TASK = "Initiate OnBoarding";
  public static String ONBOARDING_TASK_COMPLETED = "OnBoarding Task Completed";
  public static String FORGOT_PASSWORD_REFERRAL = "forgot_password_referral";
  public static String FORGOT_PASSWORD_AGENCY = "forgot_password_agency";
  public static String FORGOT_PASSWORD_USER = "forgot_password_user";
  public static String FORGOT_PASSWORD_APPLICANT = "forgot_password_applicant";
  public static final String REASSIGN_REVIEW = "Reassign review";
  public static String BULK_UPLOAD_RESUME = "upload applicants profile";
  public static String BULK_UPLOAD_USERS = "upload users";
  public static String BULK_UPLOAD_EXAM = "upload exam";
  public static String EXPORT_APPLICANTS = "export applicants";
  public static String EXPORT_REQUISTIONS_SEARCH_RESULTS = "export requistion search results";
  public static String EXPORT_MYREQUISTIONS_SEARCH_RESULTS = "export myrequistion search results";
  public static String EXPORT_MYREQUISTIONS_HIRING_MANAGER_SEARCH_RESULTS = "export myrequistion hiring manager search results";
  public static String EXPORT_MYREQUISTIONS_RECRUITER_SEARCH_RESULTS = "export myrequistion recruiter search results";
  public static String EXPORT_APPLICANTS_SEARCH = "export search applicants";
  public static String EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS = "export agency redumption search results";
  public static String EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS = "export referal redumption search results";
  public static String BULK_UPLOAD_STATUS_COMPLETED = "completed";
  public static String BULK_UPLOAD_STATUS_RUNNING = "running";
  public static String BULK_UPLOAD_STATUS_FAIL = "failed";
  public static String TARGET_ON_BOARD_DATE = "Target on board date";
  public static String TARGET_OFFER_RELEASE_DATE = "Target offer release date";
  public static String OFFER_RELEASED_DATE = "Offer released date";
  public static String ON_BOARDED_DATE = "On board date";
  public static String APPLIED_DATE = "Applied date";
  public static String ACCOMPLISHMENT_TYPE = "ACCOMP";
  public static String COMPETENCY_TYPE = "COMP";
  public static String EDUCATION_COLLEGE = "edu_college";
  public static String EDUCATION_CERTIFICATION = "edu_certification";
  public static final String REF_ANSWER_TYPE = "ref";
  public static final String QUESTION_TYPE_TEXT = "text";
  public static final String QUESTION_TYPE_RADIO = "radio";
  public static final String OFFER_WATCHLIST_RELEASE_OFFER = "release_offer";
  public static final String OFFER_WATCHLIST_INITIATE_OFFER = "initiate_offer";
  public static final String SEARCH_PRIVATE = "private";
  public static final String TYPE_SEARCH_APPLICANT = "applicantsearch";
  public static final String SEARCH_RECENT = "recent";
  public static final String SEARCH_SHARED_ALL = "all";
  public static final String LINKEDIN_REQUEST_TOKEN = "requestToken";
  public static final String LINKEDIN_OAUTH_VERIFIER = "oauth_verifier";
  public static final String LINKEDIN_ACCESS_TOCKEN = "Accesstoken";
  public static final String LINKEDIN_TOKEN_SECRET = "Tokensecret";
  public static final String EMAIL_TYPE_SCHEDULE_INTERVIEW = "scheduleInterview";
  public static final String EMAIL_TYPE_ON_BEHALF = "emailtypeonbehalf";
  public static final String TOTAL_APPLICANTS_REVIEWED = "Total Applicants Reviewed";
  public static final String TOTAL_APPLICANTS_CLEARED_REVIEW = "Total Applicants Cleared On Review";
  public static final String TOTAL_APPLICANTS_FAILED_REVIEWED = "Total Applicants Failed On Review";
  public static final String TOTAL_APPLICANTS_SCREENED = "Total Applicants Screened";
  public static final String TOTAL_APPLICANTS_CLEARED_SCREENING = "Total Applicants Cleared On Screening";
  public static final String TOTAL_APPLICANTS_FAILED_SCREENING = "Total Applicants Failed On Screening";
  public static final String TOTAL_APPLICANTS_CLEARED_NEXT_REVIEW = "Total Applicants Cleared Next Review";
  public static final String TOTAL_APPLICANTS_OFFERED = "Total Applicants Offered";
  public static final String TOTAL_APPLICANTS_ON_BOARD = "Total Applicants On Boarded";
  public static final String TOTAL_REQUISTIONS_CREATED = "Total Requisitions Created";
  public static final String TOTAL_REQUISTIONS_PUBLISHED = "Total Requisitions Published";
  public static final String TOTAL_REQUISTIONS_NOT_PUBLISHED = "Total Requisitions Not Published";
  public static final String REQUISTIONS_ASSIGNED = "Requisitions Assigned";
  public static final String REQUISTIONS_FINISHED_ON_TARGET = "Requisitions Finished On Target";
  public static final String REQUISTIONS_MISSSED_TARGET_DATE = "Requisitions Missed Target Date";
  public static final String NO_OF_OPEN_POSITIONS = "No Of Positions Open";
  public static final String NO_OF_POSITIONS_REMAIN = "No Of Positions Remaining";
  public static final String APPLICANT_SCREEN_PERSONAL_INFO_GROUP = "PERSONAL_INFO";
  public static final String APPLICANT_SCREEN_WORK_PREFERENCE_GROUP = "WORK_PREFERENCE";
  public static final String APPLICANT_SCREEN_EDUCATION_GROUP = "EDUCATION";
  public static final String APPLICANT_SCREEN_PROFILE_GROUP = "PROFILE";
  public static final String APPLICANT_SCREEN_OTHER_GROUP = "OTHER";
  public static final String APPLICANT_SCREEN_SOCIAL_GROUP = "SOCIAL";
  public static final String APPLICANT_SCREEN_BACKGROUND_GROUP = "BACKGROUND";
  public static final String APPLICANT_SCREEN = "APPLICANT_SCREEN";
  public static final String APPLICANT_SCREEN_AGENCY = "APPLICANT_SCREEN_AGENCY";
  public static final String APPLICANT_SCREEN_REFERRAL = "APPLICANT_SCREEN_REFERRAL";
  public static final String APPLICANT_SCREEN_TALENTPOOL = "APPLICANT_SCREEN_TALENTPOOL";
  public static final String APPLICANT_SCREEN_EXTERNAL = "APPLICANT_SCREEN_EXTERNAL";
  public static final String APPLICANT_SCREEN_TALENTPOOL_EXTERNAL = "APPLICANT_SCREEN_TALENTPOOL_EXTERNAL";
  public static final String APPLICANT_SCREEN_PROFILE = "APPLICANT_SCREEN_PROFILE";
  public static final String APPLICANT_SCREEN_WORK_EXP = "APPLICANT_SCREEN_WORK_EXP";
  public static final String APPLICANT_SCREEN_EDUCATION = "APPLICANT_SCREEN_EDUCATION";
  public static final String APPLICANT_SCREEN_WORK_EXP_REFERRAL = "APPLICANT_SCREEN_WORK_EXP_REFERRAL";
  public static final String APPLICANT_SCREEN_WORK_EXP_AGENCY = "APPLICANT_SCREEN_WORK_EXP_AGENCY";
  public static final String APPLICANT_SCREEN_WORK_EXP_TALENTPOOL = "APPLICANT_SCREEN_WORK_EXP_TALENTPOOL";
  public static final String APPLICANT_SCREEN_WORK_EXP_EXTERNAL = "APPLICANT_SCREEN_WORK_EXP_EXTERNAL";
  public static final String APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL = "APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL";
  public static final String APPLICANT_SCREEN_EDUCATION_REFERRAL = "APPLICANT_SCREEN_EDUCATION_REFERRAL";
  public static final String APPLICANT_SCREEN_EDUCATION_AGENCY = "APPLICANT_SCREEN_EDUCATION_AGENCY";
  public static final String APPLICANT_SCREEN_EDUCATION_TALENTPOOL = "APPLICANT_SCREEN_EDUCATION_TALENTPOOL";
  public static final String APPLICANT_SCREEN_EDUCATION_EXTERNAL = "APPLICANT_SCREEN_EDUCATION_EXTERNAL";
  public static final String APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL = "APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL";
  public static final String APPLICANT_SCREEN_WORK_EXP_PROFILE = "APPLICANT_SCREEN_WORK_EXP_PROFILE";
  public static final String APPLICANT_SCREEN_EDUCATION_PROFILE = "APPLICANT_SCREEN_EDUCATION_PROFILE";
  public static final String APPLICANT_SCREEN_MYPROFILE = "APPLICANT_SCREEN_MYPROFILE";
  public static final String VISIBLE_FIELDS_LIST = "VISIBLE_FIELDS_LIST";
  public static final String MANDATORY_FIELDS_LIST = "MANDATORY_FIELDS_LIST";
  public static final String APPLICANT_WORK_EXPERIENCE_SCREEN = "APPLICANT_WORK_EXPERIENCE_SCREEN";
  public static final String REQUISITION_SCREEN = "REQUISITION_SCREEN";
  public static final String REQUISITION_SCREEN_JOB_DETAILS_GROUP = "JOB_DETAILS";
  public static final String REQUISITION_SCREEN_JOB_DETAILS_OTHERS_GROUP = "JOB_DETAILS_OTHERS";
  public static final String APPLICANT_SCREEN_WORK_EXP_SKILL_TAB = "APPLICANT_SCREEN_WORK_EXP";
  public static final String APPLICANT_SCREEN_WES_WORK_EXPERIENCE_GROUP = "WORK_EXPERIENCE";
  public static final String APPLICANT_SCREEN_WES_SKILL_GROUP = "SKILLS";
  public static final String APPLICANT_SCREEN_EDUCATION_TAB = "APPLICANT_SCREEN_EDUCATION";
  public static final String APPLICANT_SCREEN_EDUCATION_DETAILS_GROUP = "EDUCATION_DETAILS";
  public static final String APPLICANT_SCREEN_CERTIFICATION_DETAILS_GROUP = "CERTIFICATION_DETAILS";
  public static final String FILTER_STATUS_TEMPLATE_ONLY = "TEMPLATE_ONLY";
  public static final String FILTER_STATUS_REQUISITION_ONLY = "REQUISITION_ONLY";
  public static final String FILTER_STATUS_ALL_APPLICANTS = "ALL_APPLICANTS";
  public static final String FILTER_STATUS_DRAFT = "DRAFT";
  public static final String APPLICANT_EMAIL_READER = "emailreader";
  public static final String REVIEW_TYPE = "REVIEW";
  public static final String REVIEW_SCREENING = "SCREENING";
  public static final String REVIEWER_DASHBOARD = "REVIEWER_DASHBOARD";
  public static final String HIRING_MANAGER_DASHBOARD = "HIRING_MANAGER_DASHBOARD";
  public static final String RECRUITER_DASHBOARD = "RECRUITER_DASHBOARD";
  public static final String ADMIN_DASHBOARD = "ADMIN_DASHBOARD";
  public static final String ALL_ROLE_PERMISSIONS = "ALL_ROLE_PERMISSIONS";
  public static final String USER_CRUD = "USER_CRUD";
  public static final String ROLES_CRUD = "ROLES_CRUD";
  public static final String ORGANIZATION_CRUD = "ORGANIZATION_CRUD";
  public static final String PROJECT_CODES_CRUD = "PROJECT_CODES_CRUD";
  public static final String NOTIFICATION_MGMT = "NOTIFICATION_MGMT";
  public static final String LOCATIONS_CRUD = "LOCATIONS_CRUD";
  public static final String REFERRAL_BUDGET_CODE_CRUD = "REFERRAL_BUDGET_CODE_CRUD";
  public static final String REFERRAL_SCHEME_CRUD = "REFERRAL_SCHEME_CRUD";
  public static final String REFERRAL_SCHEME_TYPE_CRUD = "REFERRAL_SCHEME_TYPE_CRUD";
  public static final String QUESTIONNAIRE_EXAMS = "QUESTIONNAIRE_EXAMS";
  public static final String SALARY_PLAN_CRUD = "SALARY_PLAN_CRUD";
  public static final String BUDGET_CODE_CRUD = "BUDGET_CODE_CRUD";
  public static final String JOB_CODE_CRUD = "JOB_CODE_CRUD";
  public static final String JOB_GRADE_CRUD = "JOB_GRADE_CRUD";
  public static final String JOB_TITLES_CRUD = "JOB_TITLES_CRUD";
  public static final String SALARY_AND_OFFER_INFORMATION_READ = "SALARY_AND_OFFER_INFORMATION_READ";
  public static final String REQUISITION_CRUD = "REQUISITION_CRUD";
  public static final String ALL_REQUISITIONS = "ALL_REQUISITIONS";
  public static final String ALL_REQUISITIONS_READ = "ALL_REQUISITIONS_READ";
  public static final String OWN_ORGANIZATION_REQUISTIONS = "OWN_ORGANIZATION_REQUISTIONS";
  public static final String REQ_TEMPLATE_CRUD = "REQ_TEMPLATE_CRUD";
  public static final String EDIT_REQUISITION_AFTER_PUBLISH = "EDIT_REQUISITION_AFTER_PUBLISH";
  public static final String ADMIN_CONFIGURATIONS = "ADMIN_CONFIGURATIONS";
  public static final String EMAIL_CAMPAIGN_CRUD = "EMAIL_CAMPAIGN_CRUD";
  public static final String TALENT_POOL_CRUD = "TALENT_POOL_CRUD";
  public static final String MASS_EMAIL = "MASS_EMAIL";
  public static final String SYSTEM_RULE_MANAGE = "SYSTEM_RULE_MANAGE";
  public static final String LOV_MANAGEMENT = "LOV_MANAGEMENT";
  public static final String APPLICANTS_CRUD = "APPLICANTS_CRUD";
  public static final String ALL_APPLICANTS = "ALL_APPLICANTS";
  public static final String BULK_UPLOAD_APPLICANTS = "BULK_UPLOAD_APPLICANTS";
  public static final String APPLICANTS_MOVE_TO_REQUISTIONS_TALENTPOOL_BACK = "APPLICANTS_MOVE_TO_REQUISTIONS_TALENTPOOL_BACK";
  public static final String MANAGE_APPLICANT_USER = "MANAGE_APPLICANT_USER";
  public static final String AGENCY_CRUD = "AGENCY_CRUD";
  public static final String AGENCY_REDEMPTIONS = "AGENCY_REDEMPTIONS";
  public static final String REFERRAL_MGMT = "REFERRAL_MGMT";
  public static final String REFERRAL_REDEMPTIONS = "REFERRAL_REDEMPTIONS";
  public static final String ALL_EXPORT_UPLOAD_TASK = "ALL_EXPORT_UPLOAD_TASK";
  public static final String ALL_PENDING_TASKS = "ALL_PENDING_TASKS";
  public static final String ALL_CALENDAR_ACEESS = "ALL_CALENDAR_ACEESS";
  public static final String ON_BOARD_MANAGEMENT = "ON_BOARD_MANAGEMENT";
  public static final String REPORTS_VIEW = "REPORTS_VIEW";
  public static final String APP_SEARCH_SCREEN_HIRING_MGR = "APP_SEARCH_SCREEN_HIRING_MGR";
  public static final String APPLICANT_SUMMARY_SCREEN = "APPLICANT_SUMMARY_SCREEN";
  public static final String APP_SEARCH_SCREEN_RECRUITER = "APP_SEARCH_SCREEN_RECRUITER";
  public static final String ALL_APP_SEARCH_SCREEN = "ALL_APP_SEARCH_SCREEN";
  public static final String ON_BOARDING_SEARCH_SCREEN = "ON_BOARDING_SEARCH_SCREEN";
  public static final String ALL_REQUISION_SCREEN = "ALL_REQUISION_SCREEN";
  public static final String REQUISION_TEMPLATE_SCREEN = "REQUISION_TEMPLATE_SCREEN";
  public static final String MY_REQUISITION_SCREEN = "MY_REQUISITION_SCREEN";
  public static final String USER_SCREEN = "USER_SCREEN";
  public static final String VENDOR_SCREEN = "VENDOR_SCREEN";
  public static final String REFERRAL_SCREEN = "REFERRAL_SCREEN";
  public static final String DASHBOARD_RECRUITER_IN_OFFER_PROCESS = "DASHBOARD_RECRUITER_IN_OFFER_PROCESS";
  public static final String DASHBOARD_RECRUITER_IN_JOINED_PROCESS = "DASHBOARD_RECRUITER_IN_JOINED_PROCESS";
  public static final String DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS = "DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS";
  public static final String TALENT_POOL_SCREEN = "TALENT_POOL_SCREEN";
  public static final String ONBOARD_TASK_DEF_SCREEN = "ONBOARD_TASK_DEF_SCREEN";
  public static final String AGENCY_REDEMPTION__SCREEN = "AGENCY_REDEMPTION__SCREEN";
  public static final String REFERRAL_REDEMPTION_SCREEN = "REFERRAL_REDEMPTION_SCREEN";
  public static final String READING = "READING";
  public static final String SPEAKING = "SPEAKING";
  public static final String WRITING = "WRITING";
  public static final String HIRING_MANAGER = "HIRING-MANAGER";
  public static final String REVIEWER = "REVIEWER";
  
  public static int getEventType(String interviewstate)
  {
    int eventtype = 0;
    if (!StringUtils.isNullOrEmpty(interviewstate))
    {
      if (interviewstate.equals("Application Submitted")) {
        eventtype = 0;
      }
      if (interviewstate.equals("In Screening")) {
        eventtype = 0;
      }
      if (interviewstate.startsWith("Interview Round"))
      {
        String evt = interviewstate.substring(interviewstate.lastIndexOf("-") + 1);
        eventtype = new Integer(evt).intValue();
      }
      if (interviewstate.startsWith("OnHold"))
      {
        String evt = interviewstate.substring(interviewstate.lastIndexOf("-") + 1);
        if (evt.equals("In Screening")) {
          eventtype = 0;
        } else {
          eventtype = new Integer(evt).intValue();
        }
      }
      if (interviewstate.equals("Offer Released")) {
        eventtype = 999;
      }
      if (interviewstate.equals("Offer Initiated-In Approval")) {
        eventtype = 888;
      }
      if (interviewstate.equals("Exam Screening")) {
        eventtype = 777;
      }
      if (interviewstate.equals("Questionnaire Assigned")) {
        eventtype = 7777;
      }
    }
    return eventtype;
  }
  
  public static int getEventTypeCleared(String interviewstate)
  {
    int eventtype = 0;
    if (!StringUtils.isNullOrEmpty(interviewstate))
    {
      interviewstate = interviewstate.replace("Cleared-", "");
      if (interviewstate.equals("Application Submitted")) {
        eventtype = 0;
      }
      if (interviewstate.equals("In Screening")) {
        eventtype = 0;
      }
      if (interviewstate.startsWith("Interview Round"))
      {
        String evt = interviewstate.substring(interviewstate.lastIndexOf("-") + 1);
        eventtype = new Integer(evt).intValue();
      }
      if (interviewstate.startsWith("OnHold"))
      {
        String evt = interviewstate.substring(interviewstate.lastIndexOf("-") + 1);
        eventtype = new Integer(evt).intValue();
      }
      if (interviewstate.equals("Offer Released")) {
        eventtype = 999;
      }
      if (interviewstate.equals("Offer Initiated-In Approval")) {
        eventtype = 888;
      }
    }
    return eventtype;
  }
}
