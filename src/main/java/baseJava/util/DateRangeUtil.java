package baseJava.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2021/6/1
 *
 * @author Su Xiaolei
 */
public class DateRangeUtil {
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String DEFAULT_MONTH_PATTERN = "yyyy-MM";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static List<String> getRange(String beginDate, String endDate) {

        List<String> result = new ArrayList<>();
        if (!isAscDate(beginDate, endDate)) {
            return result;
        }

        Calendar cal = getCalendarFormat(beginDate);
        result.add(beginDate);
        if (endDate.equals(getDateFormatStr(cal))) {

            return result;
        }
        while (true) {
            cal.add(Calendar.DATE,
                    1);
            if (endDate.equals(getDateFormatStr(cal))) {
                result.add(endDate);
                break;
            } else {
                result.add(getDateFormatStr(cal));
            }
        }
        return result;

    }

    /**
     * @param dateStr yyyy-MM-dd
     * @return jF4x Calendar
     */
    private static Calendar getCalendarFormat(String dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(5, 7)) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(dateStr.substring(8, 10)));
        return cal;
    }

    private static Boolean isAscDate(String beginDate, String endDate) {
        return getDateTimeFormat(endDate).compareTo(getDateTimeFormat(beginDate)) >= 0;
    }


    /**
     * @param dateStr yyyy-MM-dd
     * @return JFHxDate
     */
    public static Date getDateTimeFormat(String dateStr) {
        Calendar cal = getCalendarFormat(dateStr);
        return cal.getTime();
    }

    /**
     * @param dateStr yyyyMMdd
     * @return #F HxDate
     */
    private static Date getDateTimeNoFormat(String dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(4, 6)) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(dateStr.substring(6, 8)));
        return cal.getTime();
    }

    public static List<String> getRangeNotContain(String beginDate, String endDate) {
        endDate = getAddDayFormat(endDate, -1);
        return getRange(beginDate, endDate);
    }

    public static String getDateFormatStr(Calendar cal) {
        return cal.get(Calendar.YEAR) + "-" + getDayMonthStr(cal.get(Calendar.MONTH) + 1)
                + "-"+ getDayMonthStr(cal.get(Calendar.DATE));
    }

    /**
     * 获取caL的日期字符串separatestr为"-" 结果为yyy-MM-dd
     * N
     *
     * @param cal         cal
     * @param separateStr 分隔符号
     *                    areturn获取caL的日期字符串
     */
    public static String getDateFormatStr(Calendar cal, String separateStr) {
        return cal.get(Calendar.YEAR) + separateStr + getDayMonthStr(cal.get(Calendar.MONTH) + 1)
                + separateStr + getDayMonthStr(cal.get(Calendar.DATE));
    }

    /**
     * @param dateStr yyyyMM
     * @return 获取 Calendar
     */
    public static Calendar getCalendarYearMonthFormat(String dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(4, 6)) - 1);
        return cal;
    }

    /**
     * yyyy-MM-dd
     *
     * @param dateStr yyyy-MM-dd
     * @param num     增加或减少的数字
     * @return yyyy-MM-dd
     */
    public static String getAddFormat(String dateStr, Integer num, Integer type) {
        Calendar cal = getCalendarFormat(dateStr);
        if (Calendar.YEAR == type) {
            cal.add(Calendar.YEAR, num);
        }
        if (Calendar.MONTH == type) {
            cal.add(Calendar.MONTH, num);
        }
        if (Calendar.DATE == type) {

            cal.add(Calendar.DATE, num);
        }
        return getDateFormatStr(cal);
    }


    /**
     * 获取前后天yyyy-MM-dd
     *
     * @param dateStr yyyy-MM-dd
     * @param num
     * @return yyyy-MM-dd
     */
    public static String getAddDayFormat(String dateStr, Integer num) {
        return getAddFormat(dateStr, num, Calendar.DATE);
    }

    /**
     * 获取前后月yyyy-MM-dd
     *
     * @param dateStr yyyy-MM-dd
     * @param num     增减数字
     * @return yyyy-MM-dd
     */
    public static String getAddMonthFormat(String dateStr, Integer num) {
        return getAddFormat(dateStr, num, Calendar.MONTH);
    }

    public static String getDayMonthStr(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return String.valueOf(num);
        }
    }
}