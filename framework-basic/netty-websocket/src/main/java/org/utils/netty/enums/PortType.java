package org.utils.netty.enums;

/**
 * @author guozhigang
 * @date 2020/5/22 12:11
 */
public enum PortType {
    RANDOM(0),
    //当固定为0时，采用随机端口
    FIXED(1),
    //当配置为0时，采用随机端口
    CONFIG(2);

    int value;
    PortType(int value){
        this.value = value;
    }
}
