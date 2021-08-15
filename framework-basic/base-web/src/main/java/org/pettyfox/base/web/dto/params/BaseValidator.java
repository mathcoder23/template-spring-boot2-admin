package org.pettyfox.base.web.dto.params;

import org.pettyfox.base.comm.exception.DirtyException;

public interface BaseValidator {

    default void validator() throws DirtyException {

    }
}
