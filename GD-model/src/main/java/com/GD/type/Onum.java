package com.GD.type;
/**
 * 
 * @author Administrator
 * 
 * @param <KEYTYPE>
 * @param <VALUETYPE>
 * @param <BEAN>
 */
public interface Onum<KEYTYPE, VALUETYPE> {

	/**
	 * 
	 * @return
	 */
	KEYTYPE getKey();

	/**
	 * 
	 * @return
	 */
	VALUETYPE getDesc();

	// /**
	// *
	// * @param key
	// * @return
	// */
	// BEAN toEnum(KEYTYPE key);
}