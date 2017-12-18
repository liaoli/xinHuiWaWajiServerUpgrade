package com.xinhui.upgradeapp.content;

/**
 * Created by Administrator on 2017/12/15.
 */

public class MyEnviromant {

    public static String  url = UrlOriginContent.url_online;
    public static String  Origin =UrlOriginContent.Origin_online;
    public static int enviroment = 0;
    public final  static int enviroment_online = 0;
    public final  static int enviroment_test = 1;


    static {

        switch (enviroment){
            case enviroment_online:
                url = UrlOriginContent.url_online;
                Origin = UrlOriginContent.Origin_online;


                break;
            case enviroment_test:
                url = UrlOriginContent.url_test;
                Origin = UrlOriginContent.Origin_test;

                break;
        }

    }

}
