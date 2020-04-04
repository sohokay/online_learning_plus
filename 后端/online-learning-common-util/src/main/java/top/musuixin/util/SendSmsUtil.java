package top.musuixin.util;

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
     *
     * @param phone ff
     * @param random ff
     * @return ff
     */
    public static String sendSms(String phone, int random) {
        SendSmsResponse resp = null;
        try {
            Credential cred = new Credential("AKIDLCqUu6AmGm91jFfy5xZx6zdEc5VqLHNE", "LRKxLTWMwLUn92tYzB7snU2JNTLyZ3Tg");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);
            String params = "{\"PhoneNumberSet\":[\"+86" + phone + "\"],\"TemplateID\":\"564902\",\"Sign\":\"随心学习系统\",\"TemplateParamSet\":[\"" + random + "\"],\"SmsSdkAppid\":\"1400323398\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
            resp = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            return e.toString();
        }
        return SendSmsRequest.toJsonString(resp);
    }


}
