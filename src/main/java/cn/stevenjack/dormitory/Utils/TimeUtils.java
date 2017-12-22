package cn.stevenjack.dormitory.Utils;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;

/**
 * Created by 张志豪 on 2016/11/8 0008.
 */
public class TimeUtils {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @NotNull
    public static String LocalDateTimeNowString() {
        return LocalDateTime.now()
                .format(dateTimeFormatter);
    }


    @NotNull
    public static String LocalDateTimeToString(@NotNull LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 当前时间是否在时间范围内
     *
     * @param startDate string类型的yyyy-mm-dd
     * @param endDate   string类型的yyyy-mm-dd
     * @return
     */
    public static boolean isInTimeFrame(@NotNull String startDate, @NotNull String endDate) {
        return now().isAfter(parse(startDate))
                && now().isBefore(parse(endDate));
    }

    /**
     * 从字符串获取年月日
     *
     * @param dateTimeString
     * @return
     */
    @NotNull
    public static String getDateFromDateTime(@NotNull String dateTimeString) {
        final LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        return localDateTime.getYear() + "年" + localDateTime.getMonthValue() + "月" + localDateTime.getDayOfMonth() + "日";
    }

    /**
     * 从字符串获取年月
     */
    @NotNull
    public static String getDataFromYearAndMonth(@NotNull String dateTimeString) {
        String[] split = dateTimeString.split("-");
        return split[0] + "年" + split[1] + "月";
    }

    /**
     * 从字符串获取年月日
     *
     * @param dateTimeString
     * @return
     */
    @NotNull
    public static String getDateFromDate(@NotNull String dateTimeString) {
        final LocalDate localDate = LocalDate.parse(dateTimeString, dateFormatter);
        return localDate.getYear() + "年" + localDate.getMonthValue() + "月" + localDate.getDayOfMonth() + "日";
    }

    /**
     * 判断传入年度是否在当前年度之前
     *
     * @param year
     * @return
     */
    public static boolean isAfterYear(int year) {
        return now().getYear() > year;
    }
}
