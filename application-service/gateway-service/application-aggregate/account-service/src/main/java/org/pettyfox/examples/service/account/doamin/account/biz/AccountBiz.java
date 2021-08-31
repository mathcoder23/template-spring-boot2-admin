package org.pettyfox.examples.service.account.doamin.account.biz;

import com.github.pagehelper.PageInfo;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.examples.service.account.interfaces.dto.dto.EditAccountDTO;
import org.pettyfox.examples.service.account.interfaces.dto.vo.AccountVO;
import org.pettyfox.examples.service.account.doamin.account.po.Account;

public interface AccountBiz {

    Account getByUsername(String s);

    PageInfo<AccountVO> list(BasePageParam p);

    void updateActiveTime(Long id);

    void save(EditAccountDTO d);
}
