package org.pettyfox.base.web.dto.rest;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RestFul对象响应
 * @author Petty Fox
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestObjectResponse<T> {
    /**
     * 公共响应码
     */
    private int code;
    /**
     * 业务异常码
     */
    private int bcode;
    private T data;
    private String msg;
    private String errmsg;

    public static <T> RestObjectResponse<T> ok(T data){
        RestObjectResponse<T> ok = new RestObjectResponse<>();
        ok.setData(data);
        ok.setCode(RestObjectResponseCode.API_OK);
        return ok;
    }
    public static <T> RestObjectResponse<T> err(Integer code,Integer bcode,String message){
        RestObjectResponse<T> err = new RestObjectResponse<>();
        err.setCode(code);
        err.setBcode(bcode);
        err.setErrmsg(message);
        return err;
    }
    public static <T> RestObjectResponse<T> err(Integer code,String message){
        RestObjectResponse<T> err = new RestObjectResponse<>();
        err.setCode(code);
        err.setErrmsg(message);
        return err;
    }
    public String toJson(){
        return JSON.toJSONString(this);
    }
}
