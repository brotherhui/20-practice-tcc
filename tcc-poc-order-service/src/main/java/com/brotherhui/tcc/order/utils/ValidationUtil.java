package com.brotherhui.tcc.order.utils;

import javax.validation.ValidationException;

public class ValidationUtil {
    public static void validateLongId(String id) {
		//check id format
		try{
			Long.parseLong(id);
		}catch(Exception e) {
			throw new ValidationException("id "+id+" is not long");
		}
    }
}
