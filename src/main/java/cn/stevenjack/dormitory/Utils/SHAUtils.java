package cn.stevenjack.dormitory.Utils;


import javax.validation.constraints.NotNull;
import java.security.MessageDigest;

import static cn.stevenjack.dormitory.Utils.LogUtils.LogToDB;

public class SHAUtils {
    public static String getSHA_1(@NotNull final String inStr) {
        return baseSHA(inStr, "SHA-1");
    }

    public static String getSHA_256(@NotNull final String inStr) {
        return baseSHA(inStr, "SHA-256");
    }

    public static String getMD5(@NotNull final String inStr) {
        return baseSHA(inStr, "MD5");
    }

    private static String baseSHA(@NotNull final String inStr, @NotNull final String messageDigestInstance) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance(messageDigestInstance);
            byte[] digest = md.digest(inStr.getBytes("UTF-8"));
            outStr = byteToString(digest);
        } catch (Exception ex) {
            LogToDB(ex);
        }
        return outStr;
    }


    public static String byteToString(@NotNull final byte[] digest) {
        StringBuilder str = new StringBuilder();
        String tempStr = "";

        for (byte aDigest : digest) {
            tempStr = (Integer.toHexString(aDigest & 0xff));
            if (tempStr.length() == 1) {
                str.append("0").append(tempStr);
            } else {
                str.append(tempStr);
            }
        }
        return str.toString().toLowerCase();
    }
}
