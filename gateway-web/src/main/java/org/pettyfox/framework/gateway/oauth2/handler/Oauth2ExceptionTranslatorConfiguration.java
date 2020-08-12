package org.pettyfox.framework.gateway.oauth2.handler;

import org.pettyfox.base.comm.exception.DirtyException;
import org.pettyfox.base.web.rest.RestObjectResponse;
import org.pettyfox.base.web.rest.RestObjectResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import java.rmi.ServerException;

/**
 * spring cloud security 自定义异常处理,非授权、权限拒绝的异常
 */
@Slf4j
@Configuration
public class Oauth2ExceptionTranslatorConfiguration {

    @Bean
    public WebResponseExceptionTranslator oauth2ResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {

            @Override
            public ResponseEntity translate(Exception e) throws Exception {

                int status = 500;
                int bcode = 0;

                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                RestObjectResponse body = new RestObjectResponse();
                HttpStatus statusCode = responseEntity.getStatusCode();

                if(e instanceof InvalidGrantException){
                    //非法凭证
                    statusCode = HttpStatus.OK;
                    status = 2000;
                    bcode = 403;
                }

                Throwable temp = e.getCause();
                if(temp instanceof DirtyException){
                    status = RestObjectResponseCode.API_ERROR_BCODE;
                    statusCode = HttpStatus.OK;
                }else if(e instanceof ServerException){
                    status = 510;
                }
                if("Token was not recognised".equals(e.getMessage())){
                    //无法识别的token
                    statusCode = HttpStatus.OK;
                    status = 2301;
                }
                body.setBcode(bcode);
                body.setCode(status);
                body.setErrmsg(responseEntity.getBody().getMessage());
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                return new ResponseEntity<>(body, headers, statusCode);

            }
        };
    }

}