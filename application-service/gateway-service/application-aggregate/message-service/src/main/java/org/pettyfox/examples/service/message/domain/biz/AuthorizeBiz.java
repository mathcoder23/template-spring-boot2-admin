package org.pettyfox.examples.service.message.domain.biz;


public interface AuthorizeBiz {

    String createToken(Long userId, String username);

    boolean checkToken(String token);
}
