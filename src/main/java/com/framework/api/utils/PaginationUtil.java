package com.framework.api.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.framework.api.enums.CustomApiResponseCode;
import com.framework.api.responses.dto.BasePaginationApiResponse;


public class PaginationUtil {

	public static void setMetaData(HttpServletRequest request,
			BasePaginationApiResponse apiResponse, Integer offset, Integer limit) {
		Map<String, String> requestParams = new HashMap<>(request.getParameterMap().size());
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			String value =request.getParameter(key);
			requestParams.put(key, value);
		}
		String currentUrl = request.getRequestURL().toString();
		if(apiResponse.getTotalCount()>(offset+limit)) {
			requestParams.put("offset", String.valueOf(offset + limit));
			apiResponse.setNextPageUrl(getUrl(currentUrl,requestParams));
		}
		if(offset!=0) {
			int a = (offset-limit>0)?(offset-limit):0;
			requestParams.put("offset", String.valueOf(a));
			apiResponse.setPreviousPageUrl(getUrl(currentUrl,requestParams));
		}
		apiResponse.setCurrentPage(NumberUtils.getCurrentNumberOfPages(limit,offset));
		apiResponse.setTotalPage(NumberUtils.getTotalNumberOfPages(apiResponse.getTotalCount(), limit));
		apiResponse.setNextPage(NumberUtils.getNextNumberOfPages(apiResponse.getTotalCount(), limit,offset));
		apiResponse.setPreviousPage(NumberUtils.getPreviousNumberOfPages(apiResponse.getTotalCount(), limit,offset));
	}

	private static String getUrl(String currentUrl, Map<String, String> requestParams) {
		if(requestParams!=null) {
			StringBuilder stringBuilder=new StringBuilder();
			for(Map.Entry<String, String> entry:requestParams.entrySet()) {
				stringBuilder
				.append(entry.getKey())
				.append("=")
				.append(entry.getValue())
				.append("&");
			}
			if(stringBuilder.length()>0) {
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				currentUrl=currentUrl+"?"+stringBuilder.toString();
			}
		}
		return currentUrl;
	}

	public static <T> BasePaginationApiResponse<T> getApiResponse(T data) {
		BasePaginationApiResponse<T> apiResponse = new BasePaginationApiResponse<>(false,
				data, CustomApiResponseCode.SUCCESS);
		apiResponse.setData(data);
		return apiResponse;
	}

}
