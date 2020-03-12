package top.musuixin.commons.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author musuixin
 * @date 2020-02-25 09:28
 */
public class UploadPicture {
    @Test
    public static String up(MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "TrI0ESWWVtMZfM2hZ9hy9Vz9VFu_IU4f_yf47nBV";
        String secretKey = "V7Zp7ds9i2I4kW60JjdsWjAa0sJUEthhdkHxraiU";
        String bucket = "mzuo";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\360Downloads\\1002217.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.hash;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
