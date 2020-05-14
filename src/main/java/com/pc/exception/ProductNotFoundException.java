package com.pc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	
    private String exceptionDetail;
    private Object fieldValue;

    public ProductNotFoundException( String exceptionDetail, String groupType) {
        super(exceptionDetail+" - "+groupType);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = groupType;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
