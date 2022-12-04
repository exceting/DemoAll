package demo.sharemer.api;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import demo.sharemer.config.AppProperties;
import demo.sharemer.utils.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QiniuService {

    @Resource
    private AppProperties appProperties;

    public String imageUpload(String fileName, byte[] file) {

        try {
            String accessKey = Constants.VAL_MAP.get(Constants.VAL_MAP_QINIU_APPKEY);
            String secretKey = Constants.VAL_MAP.get(Constants.VAL_MAP_QINIU_SECRETKEY);
            String bucketName = "sharemer-media";
            Configuration cfg = new Configuration();
            cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(accessKey, secretKey);
            String token = auth.uploadToken(bucketName);
            Response r = uploadManager.put(file, fileName, token);
            if (r.statusCode == 200) {
                return r.bodyString();
            }
        } catch (QiniuException e) {
            if (e.getMessage().contains("file exists")) {
                return fileName;
            }
        }
        return null;
    }


}
