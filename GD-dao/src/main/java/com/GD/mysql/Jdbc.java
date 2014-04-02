package com.GD.mysql;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Jdbc�ӿ�(MySQL����).
 * 
 * @author ����
 * 
 */
public interface Jdbc {

	// /**
	// * ����ع�.
	// *
	// * @return �ɹ�����true,ʧ�ܷ���false.
	// */
	// @Deprecated
	// boolean rollback();
	//
	// /**
	// * �ύ.
	// *
	// * @return �ɹ�����true,ʧ�ܷ���false.
	// */
	// @Deprecated
	// boolean commit();
	//
	// /**
	// * ��n��sql ��Ϊһ�����壨ԭ���ԣ���Ҫô��ִ�У�Ҫôһ����ִ��ʱ��.ͨ�� setAutoCommit ���п���.
	// *
	// * @param autoCommit
	// * �Ƿ�֧������
	// * @return �ɹ�����true,ʧ�ܷ���false.
	// */
	// @Deprecated
	// boolean setAutoCommit(boolean autoCommit);

	/**
	 * ��ȡ����Դ.
	 * 
	 * @return ��õ�����Դ
	 */
	DataSource getDataSource();
	
	
	@Deprecated
	JdbcTemplate getJdbcTemplate() ;

	
	/**
	 * �����װװ�õ�sql,log.info(sql).
	 * 
	 * @param sql
	 *            δ�滻������sql
	 * @param param
	 *            ��������
	 * @return �滻�������sql
	 */
	String printSQL(Log logger, String sql, StatementParameter param);

	/**
	 * ����滻�������sql.
	 * 
	 * @param sql
	 *            δ�滻������sql
	 * @param param
	 *            ��������
	 * @return �滻�������sql
	 */
	String getSQL(String sql, StatementParameter param);

	/**
	 * �Ƿ����sqlҪ��ѯ������.
	 * 
	 * @param sql
	 *            sql
	 * @return ������ڷ���true,�����ڷ���false.
	 */
	boolean exist(String sql);

	/**
	 * �Ƿ����sqlҪ��ѯ������.
	 * 
	 * @param sql
	 *            δ�滻������sql
	 * @param param
	 *            ��������
	 * @return ������ڷ���true,�����ڷ���false.
	 */
	boolean exist(String sql, StatementParameter param);

	/**
	 * ����ִ��sql.
	 * 
	 * @param sql
	 *            sql���
	 * @param setter
	 *            ��Ҫʵ��BatchPreparedStatementSetter�ӿڣ���setValues��getBatchSize�����ӿ�. ������Բο�spring�ӿ�.
	 * @return ���飬��������[1,0,1]���������顣1��ʾ�ɹ���0��ʾʧ��.
	 */
	int[] batchUpdate(String sql, BatchPreparedStatementSetter setter);

	/**
	 * ����sql��ѯ���ݣ�����elementType��������.
	 * 
	 * @param sql
	 * @param elementType
	 *            Class����
	 * @return ���ز�ѯ�ĵ�������
	 */
	<T> T query(String sql, Class<T> elementType);

	/**
	 * ����sql��ѯ���ݣ�����elementType��������.
	 * 
	 * @param sql
	 * @param elementType
	 *            Class����
	 * @param param
	 *            ��������
	 * @return ���ز�ѯ�ĵ�������
	 */
	<T> T query(String sql, Class<T> elementType, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����elementType��������.
	 * 
	 * @param sql
	 * @param elementType
	 *            Class����
	 * @param params
	 *            �����б�
	 * @return ���ز�ѯ�ĵ�������
	 */
	<T> T query(String sql, Class<T> elementType, Object... params);

	/**
	 * ����sql��ѯ���ݣ�����Map�����List.
	 * 
	 * @param sql
	 * @return List
	 */
	List<Map<String, Object>> queryForMaps(String sql);

	/**
	 * ����sql��ѯ����.
	 * 
	 * @param sql
	 *            ��ѯ���ݵ�sql
	 * @param elementType
	 *            ���ݶ�Ӧ��model����
	 * @return ��ѯ������
	 */

	<T> List<T> queryForList(String sql, Class<T> elementType);

	/**
	 * ����sql��ѯ����.
	 * 
	 * @param sql
	 *            ��ѯ���ݵ�sql
	 * @param elementType
	 *            ���ݶ�Ӧ��model����
	 * @param start
	 *            ��ѯ���
	 * @param size
	 *            ��ѯ����
	 * @return ��ѯ������
	 */
	<T> List<T> queryForList(String sql, Class<T> elementType, int start, int size);

	/**
	 * ����sql��ѯ����.
	 * 
	 * @param sql
	 *            ��ѯ���ݵ�sql
	 * @param elementType
	 *            ���ݶ�Ӧ��model����
	 * @param params
	 *            �����б�
	 * @return ��ѯ������
	 */
	<T> List<T> queryForList(String sql, Class<T> elementType, Object... params);

	/**
	 * ����sql��ѯ���ݣ�����elementType���������List.
	 * 
	 * @param sql
	 *            sql
	 * @param elementType
	 *            ���ݶ�Ӧ��model����
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	<T> List<T> queryForList(String sql, Class<T> elementType, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����elementType���������List.
	 * 
	 * @param sql
	 *            sql
	 * @param elementType
	 *            ���ݶ�Ӧ��model����
	 * @param param
	 *            �����б�
	 * @param start
	 *            ��ѯ���
	 * @param size
	 *            ��ѯ����
	 * @return ��ѯ������
	 */
	<T> List<T> queryForList(String sql, Class<T> elementType, StatementParameter param, int start, int size);

	/**
	 * ����sql��ѯ���ݣ�����Long��List.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	List<Long> queryForLongs(String sql, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����Long��List.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @param start
	 *            ��ѯ���
	 * @param size
	 *            ��ѯ����
	 * @return ��ѯ������
	 */
	List<Long> queryForLongs(String sql, StatementParameter param, int start, int size);

	/**
	 * ����sql��ѯ���ݣ�����Integer��List.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	List<Integer> queryForInts(String sql, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����Integer��List.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @param start
	 *            ��ѯ���
	 * @param size
	 *            ��ѯ����
	 * @return ��ѯ������
	 */
	List<Integer> queryForInts(String sql, StatementParameter param, int start, int size);

	/**
	 * ����sql��ѯ���ݣ�����String��List.
	 * 
	 * @param sql
	 *            sql
	 * @return ��ѯ������
	 */
	List<String> queryForStrings(String sql);

	/**
	 * ����sql��ѯ���ݣ�����String��List.
	 * 
	 * @param sql
	 *            sql
	 * @param start
	 *            ��ѯ���
	 * @param size
	 *            ��ѯ����
	 * @return ��ѯ������
	 */
	List<String> queryForStrings(String sql, int start, int size);

	/**
	 * ����sql��ѯ���ݣ�����String��List.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	List<String> queryForStrings(String sql, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����String��List.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @param start
	 *            ��ѯ���
	 * @param size
	 *            ��ѯ����
	 * @return ��ѯ������
	 */
	List<String> queryForStrings(String sql, StatementParameter param, int start, int size);

	/**
	 * ����sql��ѯ���ݣ�����longֵ.
	 * 
	 * @param sql
	 *            sql
	 * @return ��ѯ������
	 */
	Long queryForLong(String sql);

	/**
	 * ����sql��ѯ���ݣ�����longֵ.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	Long queryForLong(String sql, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����longֵ.
	 * 
	 * @param sql
	 *            sql
	 * @param params
	 *            �����б�
	 * @return ��ѯ������
	 */
	Long queryForLong(String sql, Object... params);

	/**
	 * ����sql��ѯ���ݣ�����intֵ.
	 * 
	 * @param sql
	 *            sql
	 * @return ��ѯ������
	 */
	Integer queryForInt(String sql);

	/**
	 * ����sql��ѯ���ݣ�����intֵ.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            params �����б�
	 * @return ��ѯ������
	 */
	Integer queryForInt(String sql, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����intֵ.
	 * 
	 * @param sql
	 *            sql
	 * @param params
	 *            �����б�
	 * @return ��ѯ������
	 */
	Integer queryForInt(String sql, Object... params);

	/**
	 * ����sql��ѯ���ݣ�����Date����.
	 * 
	 * @param sql
	 *            sql
	 * @return ��ѯ������
	 */
	java.util.Date queryForDate(String sql);

	/**
	 * 
	 * ����sql��ѯ���ݣ�����Date����.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	java.util.Date queryForDate(String sql, StatementParameter param);

	/**
	 * ����sql��ѯ���ݣ�����String.
	 * 
	 * @param sql
	 *            sql
	 * @return ��ѯ������
	 */
	String queryForString(String sql);

	/**
	 * ����sql��ѯ���ݣ�����String.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ��ѯ������
	 */
	String queryForString(String sql, StatementParameter param);

//	/**
//	 * ִ��insert�����׳�DuplicateKeyException.
//	 * 
//	 * @param builder
//	 *            InsertBuilder
//	 * @return �ɹ�����true��ʧ�ܷ���false
//	 */
//	@Deprecated
//	boolean insertIgnoreForBoolean(InsertBuilder builder);

//	/**
//	 * ִ��replace into�����׳�DuplicateKeyException.
//	 * 
//	 * @param builder
//	 *            ReplaceBuilder
//	 * @return �ɹ�����true��ʧ�ܷ���false
//	 */
//	@Deprecated
//	boolean insertIgnoreForBoolean(ReplaceBuilder builder);

	/**
	 * ִ��insert�����׳�DuplicateKeyException.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	// @Deprecated
	boolean insertIgnoreForBoolean(String sql, StatementParameter param);

	/**
	 * ִ��insert.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	boolean insertForBoolean(String sql, StatementParameter param);

	boolean insertForBoolean(String sql, Object... params);

	/**
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	Long incr(String sql, StatementParameter param);

//	/**
//	 * ִ��insert.
//	 * 
//	 * @param builder
//	 *            InsertBuilder
//	 * @return �ɹ�����true��ʧ�ܷ���false
//	 */
//	boolean insertForBoolean(InsertBuilder builder);

//	/**
//	 * ִ��replace into.
//	 * 
//	 * @param builder
//	 *            ReplaceBuilder
//	 * @return �ɹ�����true��ʧ�ܷ���false
//	 */
//	boolean insertForBoolean(ReplaceBuilder builder);

	/**
	 * ִ��update.
	 * 
	 * @param sql
	 *            sql
	 * @param params
	 *            �����б�
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	boolean updateForBoolean(String sql, Object... params);

	/**
	 * ִ��update.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	boolean updateForBoolean(String sql, StatementParameter param);

//	/**
//	 * ִ��update.
//	 * 
//	 * @param builder
//	 *            SqlBuilder
//	 * @return �ɹ�����true��ʧ�ܷ���false
//	 */
//	boolean updateForBoolean(SqlBuilder builder);

//	/**
//	 * ִ��update.
//	 * 
//	 * @param builder
//	 *            SqlBuilder
//	 * @return ���ظ��³ɹ��ļ�¼��
//	 */
//	int update(SqlBuilder builder);

	/**
	 * ִ��update.
	 * 
	 * @param sql
	 *            sql
	 * @param param
	 *            �����б�
	 * @return ���ظ��³ɹ��ļ�¼��
	 */
	int update(String sql, StatementParameter param);

	/**
	 * ִ�и���sql.
	 * 
	 * @param sql
	 * @return �ɹ�����1,ʧ�ܷ���0.
	 */
	int update(String sql);

	/**
	 * ִ�в���sql,������id.
	 * 
	 * @param sql
	 *            Ҫִ�е�sql
	 * @param param
	 *            ��������
	 * @return ִ�гɹ����id.
	 */
	long insertForLastId(String sql, StatementParameter param);

	/**
	 * ����ִ��sql.
	 * 
	 * @param sqls
	 * @return
	 */
	int[] batchUpdate(String[] sqls);

//	boolean insertByBean(String sql, Object bean);
//
//	boolean updateByBean(String sql, Object bean);

//	<T> Page<T> queryForPage(String sql, Class<T> elementType);
//
//	<T> Page<T> queryForPage(String sql, Class<T> elementType, int start, int size);
//
//	<T> Page<T> queryForPage(String sql, Class<T> elementType, Object... params);
//
//	<T> Page<T> queryForPage(String sql, Class<T> elementType, StatementParameter param);
//
//	<T> Page<T> queryForPage(String sql, Class<T> elementType, StatementParameter param, int start, int size);
}
