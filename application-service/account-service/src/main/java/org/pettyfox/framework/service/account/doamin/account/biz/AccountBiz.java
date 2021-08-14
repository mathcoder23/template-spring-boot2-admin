package org.pettyfox.framework.service.account.doamin.account.biz;

import org.pettyfox.framework.service.account.doamin.account.po.Account;

public interface AccountBiz {
    Account getByUsername(String s);
}
