public class test {

    public static void main(String[] args) {
        /**
         * 0 20/13 12-23 * * ?  12点20到23点20 每13分钟执行一次
         * 0 20/13 12 * * ?   从12点20开始 每13分钟执行一次
         */
//        String str=createQuartzTimeRealJob("20","13","12",null);
//        System.out.println(str);
    }

//    public static String createQuartzTimeRealJob(String minute,String cycle ,String begH, String endH ) {
//        String time = "";
//
//        // 添加 秒
//        time = time + "0";
//
//        // 添加 分
//        if (StringUtils.isBlank(minute)) {
//            time = time + " *";
//        } else {
//            time = time + " " + minute;
//        }
//
//        //周期
//        time = time + "/" + cycle;
//
//        // 添加 时
//        if (StringUtils.isBlank(begH)) {
//            time = time + " *";
//        } else {
//            time = time + " " + begH;
//        }
//        // 添加 时
//        if (!StringUtils.isBlank(endH)) {
//            time = time + "-" + endH;
//        }
//        //增加日月周
//        time = time + " * * ?";
//        return time;
//    }
}
