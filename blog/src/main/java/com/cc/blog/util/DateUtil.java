package com.cc.blog.util;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by cc on 17-7-19.
 */
public class DateUtil {
    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getShortDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getDateNumber(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
    public static String TimeStampToDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }

    public static long convertStringToLong(String dateTime, String format) {

        long result = 0;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date dateAndTime = formatter.parse(dateTime);
            result = dateAndTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static long getCurrentDateTime(String format) {
        long result = 0;
        try {
            Date current = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String tmpFormat = formatter.format(current);
            Date formatted = formatter.parse(tmpFormat);
            result = formatted.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getHourFromLong(long millisecond) {
        long hour = millisecond / 1000 / 60 / 60 % 24;
        String result = "00";
        if (hour < 10) {
            result = "0" + hour;
        } else {
            result = hour + "";
        }
        return result;

    }

    public static String getMinuteFromLong(long millisecond) {
        long minute = millisecond / 1000 / 60 % 60;
        String result = "00";
        if (minute < 10) {
            result = "0" + minute;
        } else {
            result = minute + "";
        }
        return result;

    }

    public static String getSecoundFromLong(long mullisecond) {
        long second = mullisecond / 1000 % 60;
        String result = "00";

        if (second < 10) {
            result = "0" + second;
        } else {
            result = second + "";
        }
        return result;
    }


    /**
     * 获取当前日期＋时间
     *
     * @return
     */
    public static String getCurDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String getCurDateTimeNoSpace() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 自动补0
     *
     * @return
     */
    public static String getCurDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String getLastDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.DATE, -1);
        Date d = c.getTime();
        String day = format.format(d);
        c.clear();
        return day;
    }

    /**
     * 获取前一天时间
     *
     * @param cl
     * @return
     */
    public static Date getBeforeDay(Calendar cl) {
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        int day = cl.get(Calendar.DATE);
        cl.set(Calendar.DATE, day - 1);
        return cl.getTime();
    }

    /**
     * 获取当前时间的后一天时间
     *
     * @param cl
     * @return
     */
    public static Date getAfterDay(Calendar cl) {
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        int day = cl.get(Calendar.DATE);
        cl.set(Calendar.DATE, day + 1);
        return cl.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static String getNextDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.MONTH, +1);
        Date d = c.getTime();
        String day = format.format(d);
        c.clear();
        return day;
    }

    /**
     * 月份日期前面不带0
     *
     * @return
     */
    public static String getCurDateNoZero() {
        SimpleDateFormat formatter = new SimpleDateFormat("y-M-d");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String getCurTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }


    /**
     * 获取今天之前一周的日期（7天）
     *
     * @return
     */
    public static List<String> getLastWeekDate() {
        List<String> lastWeek = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM.dd");
        Calendar c = Calendar.getInstance();
        //过去七天
        for (int i = -7; i < 0; i++) {
            c.setTime(new Date(System.currentTimeMillis()));
            c.add(Calendar.DATE, i);
            Date d = c.getTime();
            String day = format.format(d);
            lastWeek.add(day);
            c.clear();
        }
        return lastWeek;
    }

    /**
     * 获取今天之前一个月的日期（30天）
     *
     * @return
     */
    public static List<String> getLastMonthDate() {
        List<String> lastWeek = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM.dd");
        Calendar c = Calendar.getInstance();
        //过去七天
        for (int i = 30; i > 0; i--) {
            c.setTime(new Date(System.currentTimeMillis()));
            System.out.println("i = " + i);
            c.add(Calendar.DATE, -i);
            Date d = c.getTime();
            String day = format.format(d);
            lastWeek.add(day);
            System.out.println("day = " + day);
            c.clear();
        }
        return lastWeek;
    }

    /**
     * 判断是不是刚刚 ，间隔10m 以内算是刚刚
     *
     * @param now  [hh, mm]
     * @param time [hh, mm, ss]
     * @return
     */
    public static boolean isJustNow(String[] now, String[] time) {
        if (!now[0].equals(time[0])) {
            return false;
        } else {
            int n = Integer.parseInt(now[1]);
            int t = Integer.parseInt(time[1]);
            if ((n > t && n - t <= 10) || (n < t && t - n <= 10))
                return true;
            else {
                return false;
            }
        }
    }

    /**
     * number --> week
     *
     * @param day(1--7)
     * @return
     */
    public static String getCurWeek(int day) {
        String week = "";
        if (day < 1 || day > 7)
            return "0";
        switch (day) {
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
            case 7:
                week = "星期天";
                break;

        }
        return week;
    }


    /**
     * @param time_now 目标时间  hh:mm
     * @param time     对比时间  hh:mm
     *                 判断time_now 是不是比time 晚 ？
     * @return
     */
    public static boolean isTimeLater(String time_now, String time) {
        String[] times_now = time_now.split(":");
        String[] times = time.split(":");
        int h1 = Integer.valueOf(times_now[0]);
        int m1 = Integer.valueOf(times_now[1]);
        int h = Integer.valueOf(times[0]);
        int m = Integer.valueOf(times[1]);

        if (h1 > h) {
            return true;
        } else if (h1 == h) {
            if (m1 > m)
                return true;
        }
        return false;
    }


    /**
     * 打个时间戳啊
     *
     * @return
     */
    public static String currentTimeSeconds() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime() / 1000;
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        Long timestamp = Long.parseLong(s) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }


    public static long getMonthFirstDay(String day) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        Integer d = 1;
        if ("firstday".equals(day)) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else if ("lastday".equals(day)) {
            d = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, d);
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else if ("firstweek".equals(day)) {
            d = calendar.MONDAY;
            calendar.set(Calendar.DAY_OF_WEEK, d);
        } else if ("lastweek".equals(day)) {
            d = calendar.SUNDAY;
            calendar.set(Calendar.DAY_OF_WEEK, d);
        }
        System.out.println(calendar.getTimeInMillis());
        return calendar.getTimeInMillis();
    }

    //月份加减
    public static String subMonth(String date, int month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, +month);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        return reStr;
    }

    public static Date strToDate(String s) {
        if (s == null) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd mm:hh:ss");
        DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = fmt.parse(s);
        } catch (ParseException e) {
            try {
                date = fmt2.parse(s);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 计算两个时间戳的时差
     *
     * @param dateTime1 "yyyy-MM-dd HH:mm:ss"
     * @param dateTime2 "yyyy-MM-dd HH:mm:ss"
     * @return 10-2-3          时差： 天-时-分
     */
    public static String calculateDateDiff(String dateTime1, String dateTime2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long d1 = df.parse(dateTime1).getTime();
            long d2 = df.parse(dateTime2).getTime();
            long diff = d1 > d2 ? d1 - d2 : d2 - d1;   //微妙级别的时差

            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

            return days + "-" + hours + "-" + minutes;
        } catch (Exception e) {
            return "0-0-0";
        }
    }


    /**
     * 计算两个时间戳的时差
     *
     * @param dateTime1 "yyyy-MM-dd HH:mm:ss"
     * @param dateTime2 "yyyy-MM-dd HH:mm:ss"
     * @return 10-2-3          时差： 天-时-分
     */
    public static String calculateDateDiff(Date dateTime1, Date dateTime2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long d1 = dateTime1.getTime();
            long d2 = dateTime2.getTime();
            long diff = d1 > d2 ? d1 - d2 : d2 - d1;   //微妙级别的时差

            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

            return days + "-" + hours + "-" + minutes;
        } catch (Exception e) {
            return "0-0-0";
        }
    }

    public static String dateToString(Date date) {
//        yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
