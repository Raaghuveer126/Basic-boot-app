package com.framework.api.constants;

public interface RestMappingConstants {
	String APPLICATION_NAME = "/learningbot";
	String APP_BASE = APPLICATION_NAME + "/api";
	String REQUEST = "/request";
	String RESPONSE = "/response";
	String AUTH_TOKEN = "authToken";
	String ROLE_TYPE = "roleType";
	String ID = "id";
	String ID_PARAM = "/{" + ID + "}";
	String OFFSET = "offset";
	String LIMIT = "limit";

	interface Constants{
	}
	interface UserConstants{
		String BASE = APP_BASE + "/users";
		String LOGIN = BASE+"/login";
	}
	interface QuestionConstants{
		String BASE = APP_BASE + "/questions";
		String SEARCH = "/search";
	}
	interface AnswerConstants{
		String BASE = APP_BASE + "/answers";
	}
	interface QuestionAnswerConstants{
		String BASE = APP_BASE + "/questionAnswers";
		String GET_ANSWER = "/getAnswer";
	}
	interface CategoryConstants{
		String BASE = APP_BASE + "/categories";
	}
	interface QuestionCategoryConstants{
		String BASE = APP_BASE + "/questionCategories";
	}
	interface AnswerCategoryConstants{
		String BASE = APP_BASE + "/answerCategories";
	}
	interface TestConstants{
		String BASE = APP_BASE + "/tests";
	}
}

