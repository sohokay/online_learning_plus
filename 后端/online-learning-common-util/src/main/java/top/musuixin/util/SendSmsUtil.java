package top.musuixin.util;

import cn.hutool.json.JSONUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

/**
 * @author musuixin
 * @date 2020-03-27 20:19
 */
public class SendSmsUtil {
    /**
     * @param phone  ff
     * @param random ff
     * @param code   574088：修改密码  574087：修改手机号 564902：注册
     * @return ff
     */
    public static String sendSms(String phone, int random, int code) {
        SendSmsResponse resp = null;
        try {
            Credential cred = new Credential("AKIDLCqUu6AmGm91jFfy5xZx6zdEc5VqLHNE", "LRKxLTWMwLUn92tYzB7snU2JNTLyZ3Tg");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "", clientProfile);
            String params = "{\"PhoneNumberSet\":[\"+86" + phone + "\"],\"TemplateID\":\"" + code + "\",\"Sign\":\"随心学习系统\",\"TemplateParamSet\":[\"" + random + "\"],\"SmsSdkAppid\":\"1400323398\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
            resp = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            return e.toString();
        }
        String sendSms = SendSmsRequest.toJsonString(resp);
        return JSONUtil.parseObj(sendSms).getJSONArray("SendStatusSet").get(0, SendStatusSet.class).getCode();
    }


}
