package org.pettyfox.base.web.dto.rest;

public class ErrorResponse extends RestObjectResponse {
    public ErrorResponse(String message){
        setCode(RestObjectResponseCode.API_ERROR_USER);
        setErrmsg(message);
    }
}
