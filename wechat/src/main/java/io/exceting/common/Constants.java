package io.exceting.common;

import com.google.common.collect.Maps;

import java.util.Map;

public class Constants {

    public static final String WECHAT_URL = "https://api.weixin.qq.com";


    public static final Map<String, String> VAL_MAP = Maps.newConcurrentMap();

    public static final String VAL_MAP_KEY_TOKEN = "access_token";

    public static final String VAL_MAP_APP_ID = "app_id";

    public static final String VAL_MAP_APP_SECRET = "app_secret";

}
