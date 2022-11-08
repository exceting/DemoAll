package io.exceting.processors;

import io.exceting.api.WechatService;
import io.exceting.common.Constants;
import io.exceting.model.MediaUpload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageProcessor {

    public static final ImageProcessor INSTANT = new ImageProcessor();

    private ImageProcessor() {
    }

    // 下载图片
    public byte[] getImageByUrl(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(60 * 1000);
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            return buffer.toByteArray();
        } finally {
            buffer.close();
            in.close();
        }
    }

    // 上传图片
    public MediaUpload mediaUpload(String name, byte[] bytes) throws Exception {
        return WechatService.INSTANT.imageUpload(Constants.VAL_MAP.get(Constants.VAL_MAP_ACCESS_TOKEN),
                "image", name, bytes);
    }

}
