package com.GD.util;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * ������֤
 * 
 * @author ����
 * 
 */
public class AssertUtil {

	/**
	 * �ж�һ���ַ��Ƿ񳬹���󳤶�</br>
	 * ע�⣺����ַ�Ϊ�գ���java.lang.IllegalArgumentException�쳣</br> ��������Ϊ��</br>
	 * 
	 * @param str
	 *            �ַ�
	 * @param maxLength
	 *            ��󳤶�
	 * @param message
	 *            �׳����쳣��Ϣ
	 */
	public static void maxLength(String str, int maxLength, String message) {
		if (StringUtils.isEmpty(str)) {
			throw new IllegalArgumentException("��������Ϊ��.");
		}
		if (str.length() > maxLength) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ���ֶ��Ƿ�Ϸ����ֶ����</br> �Ϸ����ֶ���Ʊ�����a-z����A-Z��ͷ
	 * 
	 * @param fieldName
	 *            �ֶ����
	 * @param message
	 *            �׳����쳣��Ϣ
	 */
	public static void assertFieldName(String fieldName, String message) {
		if (StringUtils.isEmpty(fieldName)) {
			throw new IllegalArgumentException(message);
		}
		boolean isFieldName = fieldName.matches("^[a-zA-Z]+$");
		if (!isFieldName) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ�������Ƿ�Ϊ��</br>
	 * 
	 * @param obj
	 *            ����
	 * @param message
	 *            ���Ϊ��,�׳����쳣��Ϣ
	 */
	public static void notNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ�������Ƿ�Ϊ��</br>
	 * 
	 * @param obj
	 *            ����
	 * @param message
	 *            ���Ϊ��,�׳����쳣��Ϣ
	 */
	public static void assertNotNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ���ַ��Ƿ�Ϊ��</br>
	 * 
	 * @param str
	 *            �ַ�
	 * @param message
	 *            ���Ϊ��,�׳����쳣��Ϣ
	 */
	public static void assertNotEmpty(String str, String message) {
		if (StringUtils.isEmpty(str)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertNotEmpty(List<?> list, String message) {
		if (list == null || list.size() == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ���ַ��Ƿ�Ϊ��</br>
	 * 
	 * @param str
	 *            �ַ�
	 * @param message
	 *            ���Ϊ��,�׳����쳣��Ϣ
	 */
	public static void empty(String str, String message) {
		if (StringUtils.isEmpty(str)) {
			throw new IllegalArgumentException(message);
		}
	}

//	/**
//	 * �б?Ϊ������Ϊ�������쳣</br>
//	 * 
//	 * @param list
//	 *            �б�
//	 * @param message
//	 *            ����б�Ϊ�գ��׳����쳣��Ϣ
//	 */
//	public static void isEmpty(List<?> list, String message) {
//		if (ListUtil.isNotEmpty(list)) {
//			return;
//		}
//		throw new IllegalArgumentException(message);
//	}
//
//	/**
//	 * �ж��б��Ƿ�Ϊ�գ�Ϊ�������쳣</br>
//	 * 
//	 * @param list
//	 *            �б�
//	 * @param message
//	 *            ����б�Ϊ�գ��׳����쳣��Ϣ
//	 */
//	public static void assertNotEmpty(List<?> list, String message) {
//		if (ListUtil.isEmpty(list)) {
//			throw new IllegalArgumentException(message);
//		}
//	}

	/**
	 * �ж�set�Ƿ�Ϊ�գ�Ϊ�������쳣</br>
	 * 
	 * @param set
	 *            �б�
	 * @param message
	 *            ���setΪ�գ��׳����쳣��Ϣ
	 */
	public static void assertNotEmpty(Set<?> set, String message) {
		if (set == null || set.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ������ֵ�Ƿ�Ϊ��,��Ϊ�����쳣 </br>
	 * 
	 * @param flag
	 *            �б�
	 * @param message
	 *            ���flagΪ�գ��׳����쳣��Ϣ
	 */

	public static void assertTrue(boolean flag, String message) {
		if (!flag) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ�������Ƿ����0</br>
	 * 
	 * @param num
	 *            ����
	 * @param message
	 *            Ϊ�գ�����С��0���׳����쳣��Ϣ
	 */
	public static void greatZero(Integer num, String message) {
		if (num == null || num <= 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ��Long���Ƿ����0</br>
	 * 
	 * @param num
	 *            Long
	 * @param message
	 *            Ϊ�գ�����С��0���׳����쳣��Ϣ
	 */

	public static void greatZero(Long num, String message) {
		if (num == null || num <= 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * �ж�һ�������Ƿ����0</br>
	 * 
	 * @param num
	 *            ����
	 * @param message
	 *            Ϊ�գ�����С��0���׳����쳣��Ϣ
	 */
	public static void isGreaterZero(Integer num, String message) {
		AssertUtil.greatZero(num, message);
	}

	/**
	 * �ж϶��������Ƿ���� </br>
	 * 
	 * @param num1
	 *            ����1
	 * @param num2
	 *            ����2
	 * @param message
	 *            �����ʱ���׳����쳣��Ϣ
	 */
	public static void isEquals(int num1, int num2, String message) {
		if (num1 == num2) {
			return;
		}
		throw new IllegalArgumentException(message);
	}

	/**
	 * �ж϶����ַ��Ƿ���ȣ����Դ�Сд</br>
	 * 
	 * @param str1
	 *            �ַ�1
	 * @param str2
	 *            �ַ�2
	 * @param message
	 *            �����ʱ���׳����쳣��Ϣ
	 */
	public static void equalsIgnoreCase(String str1, String str2, String message) {
		if (str1.equalsIgnoreCase(str2)) {
			return;
		}
		throw new IllegalArgumentException(message);
	}

	/**
	 * �ж�һ�������Ƿ���ڵ���0,����������쳣</br>
	 * 
	 * @param str
	 *            ����
	 * @param message
	 *            �����ʱ���׳����쳣��Ϣ
	 */
	public static void isGreaterEqualZero(Integer num, String message) {
		if (num >= 0) {
			return;
		}
		throw new IllegalArgumentException(message);
	}

}
