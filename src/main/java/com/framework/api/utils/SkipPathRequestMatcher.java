package com.framework.api.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

public class SkipPathRequestMatcher implements RequestMatcher {
	
	private OrRequestMatcher skipMatcher;
	private RequestMatcher processingMatcher;
	
	public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
		Assert.notNull(pathsToSkip);
		List<RequestMatcher> skipPathMatcher = pathsToSkip.stream().map(path->(new AntPathRequestMatcher(path))).collect(Collectors.toList());
		skipMatcher = new OrRequestMatcher(skipPathMatcher);
		processingMatcher = new AntPathRequestMatcher(processingPath);
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		if(skipMatcher.matches(request))
			return false;
		return processingMatcher.matches(request);
	}

}
