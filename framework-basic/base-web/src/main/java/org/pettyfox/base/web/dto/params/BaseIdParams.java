package org.pettyfox.base.web.dto.params;

import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.comm.exception.DirtyException;

/**
 * @author Petty Fox
 * @date 2021-04-14
 */
@Getter
@Setter
public class BaseIdParams extends BaseParams {

    private Long id;

    @Override
    public void validator() throws DirtyException {
        if (null == id) {
            throw new DirtyException("请传入id");
        }
    }
}
