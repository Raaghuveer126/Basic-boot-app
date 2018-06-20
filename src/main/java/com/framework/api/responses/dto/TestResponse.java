package com.framework.api.responses.dto;

import com.framework.api.pojos.TestPojo;
import lombok.Data;

@Data
public class TestResponse extends TestPojo{
	private Long testId;
}