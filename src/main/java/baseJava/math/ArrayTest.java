package baseJava.math;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2021/8/20
 *
 * @author Su Xiaolei
 */
public class ArrayTest {
    public static final int MONTH_COVER_MAX = 10;
    public static final String MONTH_BEGIN_STRING = "0";
    public static final int MAX_MONTH = 12;
    public static final int MAX_MINUTE = 59;

    public static final int MINUTE = 60;
    public static void main(String[] args) {
        int i = 4 / 3;
        System.out.println(i);
    }


    private static String getQuartz() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String format = formatter.format(new Date());
        String yearStr = format.substring(0, 4);
        String monthStr = format.substring(4, 6);
        int quartz = (Integer.valueOf(monthStr) + 2) / 3;
        return yearStr+"_"+quartz;
    }


    public static String getMinute(String minutesStr, int addMinutes) {
        Integer beginHour = Integer.valueOf(minutesStr.substring(0, 2));
        Integer beginMinute = Integer.valueOf(minutesStr.substring(3, 5));

        int addHour = addMinutes / MINUTE;
        int addMinute=addMinutes - (addHour * MINUTE);
        if(addMinutes>0){
            if(addMinute+beginMinute>MAX_MINUTE){
                int tempAddHour = (addMinute + beginMinute) / MINUTE;
                int tempAddMinute = (addMinute + beginMinute) - (tempAddHour * MINUTE);
                addHour+=1;
                beginMinute=tempAddMinute;
            }else {
                beginMinute+=addMinute;
            }
        }else {
            if(addMinute+beginMinute<0){
                addHour-=1;
                beginMinute=addMinute + beginMinute+MINUTE;
            }else {
                beginMinute+=addMinute;
            }
        }

        return   getTime(beginHour+addHour, beginMinute);

    }
    private static String getTime(int hour, int minute) {
        String hourStr="";
        if(hour<MONTH_COVER_MAX){
            hourStr="0"+hour;
        }else {
            hourStr=String.valueOf(hour);
        }
        String minuteStr="";
        if (minute < MONTH_COVER_MAX) {
            minuteStr="0"+minute;
        } else {
            minuteStr=String.valueOf(minute);
        }
        return hourStr+":"+minuteStr;
    }

    private static String getQuarterStr(String currQuarterStr, Integer subNum) {
        Integer yearNum = Integer.valueOf(currQuarterStr.substring(0, 4));
        String splitStr = currQuarterStr.substring(4, 5);
        Integer quarterNum = Integer.valueOf(currQuarterStr.substring(5, 6));
        int divNum = subNum / 4;
        int yuNum = subNum % 4;
        if (quarterNum + yuNum > 4) {
            return getYearStr(yearNum + 1 + divNum) + splitStr + (quarterNum + yuNum - 4);
        } else if (quarterNum + yuNum <= 0) {
            return getYearStr(yearNum - 1 + divNum) + splitStr + (quarterNum + yuNum + 4);
        } else {
            return getYearStr(yearNum + divNum) + splitStr + (quarterNum + yuNum);
        }
    }

    private static String getYearStr(Integer yearNum) {
        if (yearNum.toString().length() < 4) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 4 - yearNum.toString().length(); i++) {
                sb.append("0");
            }
            return sb.toString() + yearNum.toString();
        } else {
            return yearNum.toString();
        }
    }
}
