package com.GD.type;
/**
 * ö�ٽӿ�(key�����Զ���,value�����Զ���)
 * 
 * @author Administrator
 * 
 * @param <KEYTYPE>
 * @param <VALUETYPE>
 * @param <BEAN>
 */
public interface Onum<KEYTYPE, VALUETYPE> {

	/**
	 * ö��key.
	 * 
	 * @return
	 */
	KEYTYPE getKey();

	/**
	 * ö��������Ϣ
	 * 
	 * @return
	 */
	VALUETYPE getDesc();

	// /**
	// * keyתö��.
	// *
	// * @param key
	// * @return
	// */
	// BEAN toEnum(KEYTYPE key);
}