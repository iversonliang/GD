package com.GD.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڡ�ʱ�䳣�÷�����.
 * <p>
 * 
 * @author ����
 * 
 */
public class DateTime {
	public static final long HOUR_MILLIS = 1000 * 60 * 60;
	public static final long DAY_MILLIS = HOUR_MILLIS * 24;
	public static final int EIGHT_HOUR_SECOND = 60 * 60 * 8;// 8Сʱ
	public static final int DAY_SECOND = 60 * 60 * 24;// 24Сʱ
	public static final long EIGHT_HOUR_MILLI_SECOND = EIGHT_HOUR_SECOND * 1000L;

	/**
	 * ��ȡ��1970��1��1�վ���������</br>
	 * 
	 * @return ����
	 */
	public static int getDayCount() {
		long daynum = System.currentTimeMillis() / DAY_MILLIS;
		return (int) daynum;
	}

	/**
	 * ��ȡСʱ����.</br>
	 * 
	 * @return Сʱ��
	 */
	public static int getHourCount() {
		long daynum = System.currentTimeMillis() / HOUR_MILLIS;
		return (int) daynum;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param date
	 *            ����
	 * @return ����
	 */
	public static int getDayCount(final Date date) {
		long daynum = date.getTime() / DAY_MILLIS;
		return (int) daynum;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param datetime
	 *            ����
	 * @return ����
	 */
	public static int getDayCount(final String datetime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(datetime);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		long daynum = (date.getTime() + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		System.out.println("daynum:" + daynum + " datetime:" + datetime);
		return (int) daynum;

		// // FIXME ahai ��ȡ����������bug
		// int[] arr = parseDatetimeToArray(datetime);
		// int year = arr[0];
		// int month = arr[1];
		// int day = arr[2];
		//
		// Calendar cal = Calendar.getInstance();
		// cal.set(year, month - 1, day);
		// long daynum = cal.getTimeInMillis() / DAY_MILLIS;
		// return (int) daynum;
	}

	/**
	 * ��ȡ��������֮����������
	 * 
	 * @param date1
	 *            ��ʼ����
	 * @param date2
	 *            ��������
	 * @return ����
	 */
	public static int getDayCount(final String date1, String date2) {

		int dayCount1 = DateTime.getDayCount(date1);
		int dayCount2 = DateTime.getDayCount(date2);
		return (dayCount1 - dayCount2);
	}

	/**
	 * ��ȡ�������ڣ���ʽΪyyyy-MM-dd
	 * 
	 * @return ���������ַ���
	 */
	public static String getDate() {
		return getDate(System.currentTimeMillis());
	}

	/**
	 * ��ȡָ������������ڣ���ʽΪyyyy-MM-dd
	 * 
	 * @param daynum
	 *            ����
	 * @return �����ַ���
	 */
	public static String getDate(final int daynum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, daynum);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * ��ȡָ������������ڣ���ʽΪyyyy-MM-dd
	 * 
	 * @param daynum
	 *            ����
	 * @return �����ַ���
	 */
	public static String addDate(final int daynum) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, daynum);

		return getDate(cal.getTimeInMillis());
	}

	/**
	 * ���ظ�������֮��ָ������������ڣ���ʽΪyyyy-MM-dd
	 * 
	 * @param date
	 *            ����
	 * @param daynum
	 *            ����
	 * @return �����ַ���
	 */
	public static String addDate(final String date, final int daynum) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day + daynum);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * ��ʱ���������ת����int����
	 * 
	 * @param datetime
	 *            ʱ��
	 * @return int����
	 */
	public static int[] parseDatetimeToArray(final String datetime) {
		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = Integer.parseInt(datetime.substring(5, 7));
		int day = Integer.parseInt(datetime.substring(8, 10));

		return new int[] { year, month, day };
	}

	private static final SimpleDateFormat GET_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * ������ת�������ڣ���ʽΪyyyy-MM-dd
	 * 
	 * @param millis
	 *            ������
	 * @return �����ַ���
	 */
	public static synchronized String getDate(final long millis) {
		Date date = new Date();
		if (millis > 0) {
			date.setTime(millis);
		}
		return GET_DATE_FORMAT.format(date);
	}

	/**
	 * ��ȡ��ǰСʱ
	 * 
	 * @return Сʱ
	 */
	public static int getHour() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ���ص���Ϊ���е�ĳһ��
	 * 
	 * @return ��
	 */
	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��ȡ��ǰ�·�
	 * 
	 * @return �·�
	 */
	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MONTH);
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @return ����
	 */
	public static int getMinute() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * ��ȡ��ǰ����������ʽΪyyyy-MM-dd HH:mm:ss
	 * 
	 * @return ����
	 */
	public static String getTime() {
		return getTime(0);
	}

	/**
	 * ��ȡ��ǰʱ���ָ���������ĺ����� long millis����int minute ��int�ܴ����ֱ��ض�������ó��Ľ������ �ֽ�intת����long���ٽ��м���. �����һ����ʱ��24*60*365*60*1000�����ѳ���int��Χ,�ضϺ�������
	 * 
	 * @param minute
	 *            ָ���ķ�����
	 * @return ������
	 */
	public static String addTime(final int minute) {
		long millis = System.currentTimeMillis();
		millis = millis + (minute * 60L * 1000L);
		return getTime(millis);
	}

	/**
	 * ��ȡ����ʱ���ָ���������ĺ���������ʽΪyyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 *            ������ʱ��
	 * @param minute
	 *            ָ���ķ�����
	 * @return ������
	 */
	public static String addTime(String time, int minute) {
		long millis = getTimestamp(time);
		millis = millis + (minute * 60L * 1000L);
		return getTime(millis);
	}

	private static final SimpleDateFormat GET_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * ����������ȡ����������ʽΪyyyy-MM-dd HH:mm:ss
	 * 
	 * @param second
	 *            ����
	 * @return ������
	 */
	public static String getTime(int second) {
		long millis = second * 1000L;
		return getTime(millis);
	}

	/**
	 * �������ڷ��غ���������ʽΪyyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            ����
	 * @return ������
	 */
	public static String getTime(Date date) {
		long millis = date.getTime();
		return getTime(millis);
	}

	/**
	 * �����ַ����������ڷ��غ�����
	 * 
	 * @param time
	 * @return
	 */
	public static String getTime(String time) {
		if (time == null) {
			return null;
		}
		else {
			return time.substring(0, 19);
		}
	}

	/**
	 * long�ͺ�����ת��yyyy-MM-dd HH:mm:ss���͵��ַ���������
	 * 
	 * @param millis
	 *            ������
	 * @return ����
	 */
	public static synchronized String getTime(final long millis) {
		Date date = new Date();
		if (millis != 0) {
			date.setTime(millis);
		}
		return GET_TIME_FORMAT.format(date);
	}

	/**
	 * �����ַ�����ȡʱ���
	 * 
	 * 
	 * @param datetime
	 *            �ַ���ʱ��
	 * @return
	 */
	public static long getTimestamp(final String datetime) {
		// 2009-10-10 01:01:01.1
		// if (!DateTime.isDateTime(datetime)) {
		// return 1;
		// }
		Calendar cal = Calendar.getInstance();

		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = Integer.parseInt(datetime.substring(5, 7));
		int day = Integer.parseInt(datetime.substring(8, 10));

		int hour = Integer.parseInt(datetime.substring(11, 13));
		int minute = Integer.parseInt(datetime.substring(14, 16));
		int second = Integer.parseInt(datetime.substring(17, 19));

		// System.out.println(year + ":" + month + ":" + day);
		// System.out.println(hour + ":" + minute + ":" + second);
		cal.set(year, month - 1, day, hour, minute, second);
		if (datetime.length() > 19) {
			int mill = Integer.parseInt(datetime.substring(20));
			cal.set(Calendar.MILLISECOND, mill);
		}
		else {
			cal.set(Calendar.MILLISECOND, 0);
		}

		return cal.getTimeInMillis();
	}

	/**
	 * ��ȡ��ǰʱ���
	 * 
	 * @return ��ǰʱ�䣬�Ժ���Ϊ��λ
	 */
	public static long getTimestamp() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	/**
	 * ��ȡUnix�µ�ʱ���
	 * 
	 * @return ��ǰʱ�䣬�Ժ���Ϊ��λ
	 */
	public static int getUnixTimestamp() {
		long timestamp = getTimestamp();
		return (int) (timestamp / 1000);
	}

	/**
	 * �����ַ���ʱ���ȡUnix�µ�ʱ���
	 * 
	 * @param datetime
	 *            �ַ�������
	 * @return ʱ��ֵ���Ժ���Ϊ��λ
	 */
	public static int getUnixTimestamp(String datetime) {
		long timestamp = getTimestamp(datetime);
		// System.out.println("timestamp:" + timestamp);
		return (int) (timestamp / 1000);
	}

	private static final String IS_DATE_REGEX = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";

	/**
	 * �ж��ַ����Ƿ�Ϊ��ȷ�����ڸ�ʽ
	 * 
	 * @param str
	 *            �ַ�������
	 * @return �Ƿ�Ϸ����ڸ�ʽ
	 */
	public static boolean isDate(final String date) {
		if (date == null) {
			return false;
		}

		return date.matches(IS_DATE_REGEX);
	}

	private static final String IS_TIME_REGEX = "^[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$";

	/**
	 * �ж��ַ����Ƿ�Ϊ��ȷ��ʱ���ʽ
	 * 
	 * @param time
	 *            ��ʽ:10:10:10
	 * @return �Ƿ�Ϸ�ʱ���ʽ
	 */
	public static boolean isTime(final String time) {
		if (time == null) {
			return false;
		}
		return time.matches(IS_TIME_REGEX);
	}

	private static final String IS_DATETIME_REGEX = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}(\\.[0-9]{1,3})?$";

	/**
	 * �ж��ַ����Ƿ�Ϊ��ȷ������ + ʱ���ʽ
	 * 
	 * @param datetime
	 *            ��ʽ:2010-10-10 00:00:00
	 * @return �Ƿ�Ϸ����� + ʱ���ʽ
	 */
	public static boolean isDateTime(final String datetime) {
		if (datetime == null || datetime.length() == 0) {
			return false;
		}
		return datetime.matches(IS_DATETIME_REGEX);
	}

	/**
	 * �����ַ���ʱ���ȡ����
	 * 
	 * @param datetime
	 *            �ַ���ʱ��
	 * @return ����
	 */
	public static int getSecond(final String datetime) {
		long time = getTimestamp(datetime);
		return (int) (time / 1000);
	}

	/**
	 * �����ַ���ʱ���ȡGMTʱ��
	 * 
	 * @param time
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getGMT(final String time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimestamp(time));
		Date date = cal.getTime();
		return date.toGMTString();
	}

	private static final String[] CN_WEEK_NAMES = { "��", "һ", "��", "��", "��", "��", "��" };

	/**
	 * ���ص�ǰ���������ڼ������İ�
	 * 
	 * @return ���ڼ�
	 */
	public static String getWeekName() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return CN_WEEK_NAMES[day];
	}

	private static final String[] EN_WEEK_NAMES = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	/**
	 * �����ַ���ʱ�䷵�����ڼ�
	 * 
	 * @param datetime
	 *            �ַ���ʱ��
	 * @return ���ڼ�
	 */
	public static String getWeekName(final String datetime) {
		Calendar cld = Calendar.getInstance();
		cld.setTimeInMillis(getTimestamp(datetime));
		int num = cld.get(Calendar.DAY_OF_WEEK) - 1;
		return EN_WEEK_NAMES[num];
	}

	/**
	 * ��ȡ�·ݵ�����
	 * 
	 * @param monthNum
	 *            0:��ʾ��ǰ�·� ��������ʾǰn���·� ��������ʾ��n���·�
	 * @return ����
	 */
	public static int getDayCountOfMonth(final int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		int daynum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daynum;
	}

	/**
	 * ��ȡ�·ݵĵ�һ�죬��ʽΪyyyy-MM-dd
	 * 
	 * @param monthNum
	 *            0:��ʾ��ǰ�·� ��������ʾǰn���·� ��������ʾ��n���·�
	 * @return ָ���·ݵ�һ��������ַ���
	 */
	public static String getFirstDayOfMonth(final int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * ���ݸ��������ڣ�����ָ��monthNum���º���·ݵĵ�һ�죬��ʽΪyyyy-MM-dd
	 * 
	 * @param date
	 *            ����
	 * @param monthNum
	 *            ����
	 * @return dateָ��monthNum���º���·ݵ�һ��������ַ���
	 */
	public static String getFirstDayOfMonth(final String date, final int monthNum) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DATE, 1);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * ��ȡ����һ����ʽΪyyyy-MM-dd
	 * 
	 * @param date
	 * @return ����һ���ַ�������
	 */
	public static String getMonday(final String date) {
		int[] arr = parseDatetimeToArray(date);
		int year = arr[0];
		int month = arr[1];
		int day = arr[2];

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return getDate(cal.getTimeInMillis());
	}

	/**
	 * �жϴ���������Ƿ�Ϊ����
	 * 
	 * @param time
	 *            �ַ�������
	 * @return boolean ��Ϊ�����򷵻�true
	 */
	public static boolean isToday(String time) {
		if (time == null || time.length() < 10) {
			return false;
		}

		time = time.substring(0, 10);
		if (DateTime.getDate().equals(time)) {
			return true;
		}
		return false;
	}

	private static final SimpleDateFormat GET_INT_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * ��ȡ��ǰʱ�䣬��ʽΪyyyyMMddHHmmss
	 * 
	 * @return ��ǰʱ��
	 */
	public static synchronized String getIntTime() {
		Date date = new Date();
		return GET_INT_TIME_FORMAT.format(date);
	}

}
