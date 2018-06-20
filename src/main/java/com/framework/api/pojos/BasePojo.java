package com.framework.api.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePojo {
	private Long created;
	private Long updated;
	private String createdBy;
	private String updatedBy;
}
