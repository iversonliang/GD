package com.GD.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnumUtil {

	protected static final Map<Object, Map<Object, Enum<?>>> cache = new ConcurrentHashMap<Object, Map<Object, Enum<?>>>();

	// public static <E extends Enum<E>> EnumSet<E> of(E e) {

	public static <E extends Enum<E>> String getDesc(Object key, Class<E> clazz) {
		// Onum<KEYTYPE, VALUETYPE> obj = EnumUtil.toEnum(key, clazz);
		E e = EnumUtil.get(key, clazz);
		if (e == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Onum<Object, Object> onum = (Onum<Object, Object>) e;
		return (String) onum.getDesc();
	}

	/**
	 * ����IDת��Ϊö��(Ԫ�ز����ڻ����쳣).
	 * 
	 * @param id
	 * @param clazz
	 * @return
	 */
	public static <E extends Enum<E>> E toEnum(Object key, Class<E> clazz) {
		E inum = get(key, clazz);
		if (inum == null) {
			throw new IllegalArgumentException("ö��Ԫ��[" + key + "]������.");
		}
		return inum;
	}

	/**
	 * ����IDת��Ϊö��(Ԫ�ز����ڷ���onum).
	 * 
	 * @param key
	 * @param clazz
	 * @param onum
	 * @return
	 */
	public static <E extends Enum<E>> E toEnum(Object key, Class<E> clazz, E onum) {
		E inum = get(key, clazz);
		if (inum == null) {
			return onum;
		}
		return inum;
	}

	/**
	 * �ж�key�Ƿ����.
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <E extends Enum<E>> boolean contains(Object key, Class<E> clazz) {
		key = toLowerCase(key);
		Map<Object, Enum<?>> map = cache.get(key);
		if (map == null) {
			map = toMap(key, clazz);
		}
		return map.containsKey(key);
	}

	/**
	 * ����IDת��Ϊö��(Ԫ�ز������򷵻�null�������쳣)
	 * 
	 * @param id
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E get(Object key, Class<E> clazz) {
		key = toLowerCase(key);
		Map<Object, Enum<?>> map = cache.get(key);
		if (map == null) {
			map = toMap(key, clazz);
		}
		return (E) map.get(key);
	}

	@SuppressWarnings("unchecked")
	protected static synchronized <E extends Enum<E>> Map<Object, Enum<?>> toMap(Object key, Class<E> clazz) {
		Map<Object, Enum<?>> map = cache.get(key);
		if (map != null) {
			return map;
		}
		map = new HashMap<Object, Enum<?>>();
		EnumSet<E> set = EnumSet.allOf(clazz);
		Iterator<E> iterator = set.iterator();
		while (iterator.hasNext()) {
			Onum<Object, Object> value = (Onum<Object, Object>) iterator.next();
			Object id = value.getKey();
			id = toLowerCase(id);
			map.put(id, (Enum<?>) value);
		}
		// cache.put(key, map);
		cache.put(clazz.getName(), map);
		return map;
	}

	protected static Object toLowerCase(Object key) {
		if (key instanceof String) {
			return ((String) key).toLowerCase();// ͳһת��Сд����
		}
		else {
			return key;
		}
	}

}
