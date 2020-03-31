package top.musuixin.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * @author musuixin
 * @date 2020-03-30 21:42
 */
public class AddressByIp {
    public static String getAddress(String ip) {
        String body = HttpRequest.get("http://api.map.baidu.com/location/ip?ak=4KgoknY9B2YnLlrg1gBISpf0UXfG3do4&ip=" + ip + "&coor=bd09ll").execute().body();
        return JSONUtil.parseObj(JSONUtil.parseObj(body).get("content")).get("address").toString();
    }
}
