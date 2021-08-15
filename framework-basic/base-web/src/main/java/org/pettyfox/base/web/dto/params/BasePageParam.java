package org.pettyfox.base.web.dto.params;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePageParam {
    private int pageNo = 1;
    private int pageSize = 20;

    public BasePageParam(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
    public BasePageParam(){}
}
