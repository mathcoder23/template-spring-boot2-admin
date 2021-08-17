package org.utils.netty.standard;

import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import org.springframework.util.Assert;

import java.io.File;

/**
 * @ClassName SslUtil
 * @Description 证书初始化工具
 * @Author eface
 * @Date 2020/3/18 9:54
 * @Version 1.0
 */
public class SslUtil {
    /**
     * 读取TLS证书及密钥配置,转换为context
     * @param caPath
     * @param keyPath
     * @return
     * @throws Exception
     */
    public static SslContext createSSLContext(String caPath,String keyPath) throws Exception {

        File certChainFile=new File(caPath.replace("/",File.separator));
        File keyFile=new File(keyPath.replace("/",File.separator));
        Assert.isTrue(certChainFile.exists(),"证书ca不存在:"+certChainFile.getCanonicalPath());
        Assert.isTrue(keyFile.exists(),"密钥key不存在:"+keyFile.getCanonicalPath());
        return SslContextBuilder.forServer(certChainFile, keyFile).clientAuth(ClientAuth.NONE)
                .sslProvider(SslProvider.OPENSSL).build();
    }
}
