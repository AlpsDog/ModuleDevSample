package com.wonly.lib_base.net.cert;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @Author: HSL
 * @Time: 2019/9/2 10:18
 * @E-mail: xxx@163.com
 * @Description: https的域名匹配规则
 */
public class TrustAllHostnameVerifier implements HostnameVerifier {

    /**
     * 返回true，信任所有服务器
     */
    @Override
    public boolean verify(String hostname, SSLSession session) {
        //return hostname.equals("server.jeasonlzy.com");
        return true;
    }
}
