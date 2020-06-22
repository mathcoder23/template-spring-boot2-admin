package com.eface.base.comm.exception;

import com.eface.base.comm.type.BaseEnum;
import lombok.Getter;

@Getter
public class DirtyException extends RuntimeException{
    private int bcode;
    public DirtyException(String message){
        super(message);
    }
    public <T extends Enum<T>> DirtyException(BaseEnum<T,Integer> baseEnum){
        super(baseEnum.getName());
        bcode = baseEnum.getValue();
    }
    public DirtyException(String message,int bcode){
        super(message);
    }
}
