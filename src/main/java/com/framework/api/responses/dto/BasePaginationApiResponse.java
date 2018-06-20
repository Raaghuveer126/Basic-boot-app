package com.framework.api.responses.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.framework.api.enums.CustomApiResponseCode;

@JsonInclude(Include.NON_NULL)
public class BasePaginationApiResponse<T> extends BaseApiResponse<T>{
	private Long totalCount;
	private Long currentPage;
	private Long nextPage;
	private Long previousPage;
	private Long totalPage;
	
	private String previousPageUrl;
	private String nextPageUrl;
	public BasePaginationApiResponse(){};
	
	public BasePaginationApiResponse(boolean error, String code, T data, String message) {
		super(error,code,data,message);
	}
	
	public BasePaginationApiResponse(boolean error, String code, T data, String message, String detailedMessage) {
		this(error, code, data, message);
		super.detailMessage = detailedMessage;
	}
	
	public BasePaginationApiResponse(boolean error, T data, CustomApiResponseCode lavaOssResponseCode){
		this(error, lavaOssResponseCode.getCode(), data, lavaOssResponseCode.getMessage());
	}

	public BasePaginationApiResponse(boolean error, T data, CustomApiResponseCode lavaOssResponseCode, String detailMessage){
		this(error, lavaOssResponseCode.getCode(), data, lavaOssResponseCode.getMessage(), detailMessage);
	}
	
	public BasePaginationApiResponse(boolean error, T data, CustomApiResponseCode lavaOssResponseCode, List<String> fieldErrors){
		this(error, lavaOssResponseCode.getCode(), data, lavaOssResponseCode.getMessage());
		super.fieldErrors = fieldErrors;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getPreviousPageUrl() {
		return previousPageUrl;
	}

	public void setPreviousPageUrl(String previousPageUrl) {
		this.previousPageUrl = previousPageUrl;
	}

	public String getNextPageUrl() {
		return nextPageUrl;
	}

	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getNextPage() {
		return nextPage;
	}

	public void setNextPage(Long nextPage) {
		this.nextPage = nextPage;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(Long previousPage) {
		this.previousPage = previousPage;
	}
	
}
