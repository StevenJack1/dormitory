package cn.stevenjack.dormitory.Utils;


import java.util.UUID;

public class StringUtils {
    /**
     * 是Null或""吗？
     *
     * @param value 需要判断的对象
     * @return 是Null或""吗？
     */
    public static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
