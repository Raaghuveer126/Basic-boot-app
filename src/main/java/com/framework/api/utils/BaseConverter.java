package com.framework.api.utils;

import com.framework.api.entity.BaseEntity;
import com.framework.api.pojos.BasePojo;

public class BaseConverter {
	public static void setRespose(BaseEntity request, BasePojo response) {
		if(request.getCreated()!=null) {
			response.setCreated(request.getCreated().getTime());
		}
		if(request.getUpdated()!=null) {
			response.setUpdated(request.getUpdated().getTime());				
		}
		response.setCreatedBy(request.getCreatedBy());
		response.setUpdatedBy(request.getUpdatedBy());
	}
}
