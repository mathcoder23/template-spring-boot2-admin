package org.pettyfox.base.web.dto.params;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseQueryParam {
    private int pageNo = 1;
    private int pageSize = 20;

    public BaseQueryParam(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
    public BaseQueryParam(){}
}
