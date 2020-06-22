package com.eface.base.comm.exception;

import com.eface.base.comm.type.BaseEnum;
import lombok.Getter;

@Getter
public class RestCommException extends RuntimeException{
    private int code;
    public RestCommException(String message){
        super(message);
    }
    public <T extends Enum<T>> RestCommException(BaseEnum<T,Integer> baseEnum){
        super(baseEnum.getName());
        code = baseEnum.getValue();
    }
    public RestCommException(String message, int bcode){
        super(message);
    }
}
