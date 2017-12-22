package cn.stevenjack.dormitory.Utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;


public class ImageUtils {
    public static String decodeBase64ToImage(String base64,String path) throws IOException {
        byte[] bytes = decodeBase64(base64);
        // 调整异常数据
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }
        String imageName = getImageName(base64);
        Files.write(Paths.get(path+imageName), bytes, StandardOpenOption.CREATE);
        return imageName;
    }

    public static String encodeImageToBase64(String path,String name) throws IOException {
        InputStream in = new FileInputStream(path+name);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        String base64String = encodeBase64String(bytes);
        String[] split = base64String.split("/", 3);
        String head = split[0].substring(0, 4) + ":" + split[0].substring(4, split[0].length())+"/"+split[1].substring(0,split[1].length()-6)+";"
                +split[1].substring(split[1].length()-6,split[1].length())+",/";
        return head+split[2];
    }

    private static String getImageName(String base64){
        return UUID.randomUUID().toString()+"."+base64.split(";")[0].split(":")[1].split("/")[1];
    }
}
