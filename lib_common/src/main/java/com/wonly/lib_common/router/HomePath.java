package com.wonly.lib_common.router;

/**
 * @Project: ModuleDevSample
 * @Package: com.wonly.lib_common.router
 * @Author: HSL
 * @Time: 2019/08/22 16:31
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public interface HomePath {

    String HOME = "/home";

    //###########################################################################################//
    //#######                                                                          ##########//
    //#######                             Activity路径                                  ##########//
    //#######                                                                          ##########//
    //###########################################################################################//


    //###########################################################################################//
    //#######                                                                          ##########//
    //#######                             Fragment路径                                  ##########//
    //#######                                                                          ##########//
    //###########################################################################################//
    /**
     * 首页
     */
    String FRAG_HOME_HOME = HOME + "/home";

    //###########################################################################################//
    //#######                                                                          ##########//
    //#######                             Service路径                                   ##########//
    //#######                                                                          ##########//
    //###########################################################################################//
    /**
     * 首页模块服务
     */
    String HOME_MODULE_SERVICE = HOME + "/service";
}
