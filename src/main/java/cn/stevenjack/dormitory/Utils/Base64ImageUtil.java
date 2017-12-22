package cn.stevenjack.dormitory.Utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Base64ImageUtil {
    /**
     * Decode string to image
     * @param base64
     * @param path
     * @return
     * @throws IOException
     */
    public static String decodeToImage(String base64,String path) throws IOException {
        String imageString = removeBase64Header(base64);
        String imageType = getImageType(base64);
        String fileName = UUID.randomUUID().toString();
        byte[] imageByte;
        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(imageString);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        BufferedImage image = ImageIO.read(bis);
        File imageFile = new File(path + fileName + "." + imageType);
        ImageIO.write(image, imageType, imageFile);
        bis.close();
        return fileName + "@" + imageType;
    }

    /**
     * Encode image to string
     * @param path
     * @param name
     * @return
     * @throws IOException
     */
    public static String encodeToString(String path,String name) throws IOException {
        String[] split = name.split("@");
        File imageFile = new File(path + split[0] + "." + split[1]);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(imageFile);
        ImageIO.write(image, split[1], bos);
        byte[] imageBytes = bos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String imageString = encoder.encode(imageBytes);
        bos.close();
        return "data:image/" + split[1] + ";base64,"+imageString;
    }

    /**
     * To make base64 string decoded properly, We need to remove the base64 header from a base64 string. 
     *
     * @param base64 The Base64 string of an image.
     * @return Base64 string without header.
     */
    public static String removeBase64Header(String base64) {
        if(base64 == null) return  null;
        return base64.trim().replaceFirst("data[:]image[/]([a-z])+;base64,", "");
    }

    /**
     * Get the image type.
     *
     * @param base64 The base64 string of an image.
     * @return jpg, png, gif
     */
    public static String getImageType(String base64) {
        String[] header = base64.split("[;]");
        if(header == null || header.length == 0) return null;
        return header[0].split("[/]")[1];
    }
}
