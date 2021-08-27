package org.pettyfox.framework.service.account.interfaces.facade;

import com.github.pagehelper.PageInfo;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.base.web.dao.BaseEntityNotId;
import org.pettyfox.base.web.dto.params.BaseIdsParams;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseCrudController<E extends BaseEntityNotId, V> extends BaseController {

    @PostMapping("save")
    public abstract RestObjectResponse<E> save(@RequestBody E entity);

    @PostMapping("delete")
    public abstract RestObjectResponse<String> delete(@RequestBody BaseIdsParams p);

    @GetMapping("list")
    public abstract RestObjectResponse<PageInfo<V>> list(@RequestBody BasePageParam p);

}
