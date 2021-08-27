package org.pettyfox.framework.service.account.doamin.account.biz;

import com.github.pagehelper.PageInfo;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.pettyfox.framework.service.account.interfaces.dto.dto.EditAccountDTO;
import org.pettyfox.framework.service.account.interfaces.dto.vo.AccountVO;

public interface AccountBiz {

    Account getByUsername(String s);

    PageInfo<AccountVO> list(BasePageParam p);

    void updateActiveTime(Long id);

    void save(EditAccountDTO d);
}
