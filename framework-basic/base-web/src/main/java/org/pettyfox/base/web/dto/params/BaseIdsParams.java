package org.pettyfox.base.web.dto.params;

import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.comm.exception.DirtyException;

import java.util.List;

/**
 * @author Petty Fox
 * @date 2021-04-14
 */
@Getter
@Setter
public class BaseIdsParams extends BaseParams {

    private List<String> ids;

    @Override
    public void validator() throws DirtyException {
        if(null == ids || ids.isEmpty()){
            throw new DirtyException("请传入id");
        }
    }
}
