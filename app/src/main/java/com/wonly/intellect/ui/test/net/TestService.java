package com.wonly.intellect.ui.test.net;

import com.wonly.lib_base.base.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.intellect.ui.test.net
 * @Author: HSL
 * @Time: 2019/09/02 15:59
 * @E-mail: xxx@163.com
 * @Description: 测试接口
 */
public interface TestService {

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean> login(@Field("username") String username, @Field("password") String password);
}
