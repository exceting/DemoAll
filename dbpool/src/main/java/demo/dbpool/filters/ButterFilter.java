package demo.dbpool.filters;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.jdbc.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Create by 18073 on 2019/9/14.
 */
public class ButterFilter implements Filter {
    @Override
    public void init(DataSourceProxy dataSource) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void configFromProperties(Properties properties) {

    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public ConnectionProxy connection_connect(FilterChain chain, Properties info) throws SQLException {
        return null;
    }

    @Override
    public StatementProxy connection_createStatement(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql) throws SQLException {
        return null;
    }

    @Override
    public CallableStatementProxy connection_prepareCall(FilterChain chain, ConnectionProxy connection, String sql) throws SQLException {
        return null;
    }

    @Override
    public String connection_nativeSQL(FilterChain chain, ConnectionProxy connection, String sql) throws SQLException {
        return null;
    }

    @Override
    public void connection_setAutoCommit(FilterChain chain, ConnectionProxy connection, boolean autoCommit) throws SQLException {

    }

    @Override
    public boolean connection_getAutoCommit(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return false;
    }

    @Override
    public void connection_commit(FilterChain chain, ConnectionProxy connection) throws SQLException {

    }

    @Override
    public void connection_rollback(FilterChain chain, ConnectionProxy connection) throws SQLException {

    }

    @Override
    public void connection_close(FilterChain chain, ConnectionProxy connection) throws SQLException {

    }

    @Override
    public boolean connection_isClosed(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return false;
    }

    @Override
    public DatabaseMetaData connection_getMetaData(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public void connection_setReadOnly(FilterChain chain, ConnectionProxy connection, boolean readOnly) throws SQLException {

    }

    @Override
    public boolean connection_isReadOnly(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return false;
    }

    @Override
    public void connection_setCatalog(FilterChain chain, ConnectionProxy connection, String catalog) throws SQLException {

    }

    @Override
    public String connection_getCatalog(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public void connection_setTransactionIsolation(FilterChain chain, ConnectionProxy connection, int level) throws SQLException {

    }

    @Override
    public int connection_getTransactionIsolation(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return 0;
    }

    @Override
    public SQLWarning connection_getWarnings(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public void connection_clearWarnings(FilterChain chain, ConnectionProxy connection) throws SQLException {

    }

    @Override
    public StatementProxy connection_createStatement(FilterChain chain, ConnectionProxy connection, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public CallableStatementProxy connection_prepareCall(FilterChain chain, ConnectionProxy connection, String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Class<?>> connection_getTypeMap(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public void connection_setTypeMap(FilterChain chain, ConnectionProxy connection, Map<String, Class<?>> map) throws SQLException {

    }

    @Override
    public void connection_setHoldability(FilterChain chain, ConnectionProxy connection, int holdability) throws SQLException {

    }

    @Override
    public int connection_getHoldability(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return 0;
    }

    @Override
    public Savepoint connection_setSavepoint(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public Savepoint connection_setSavepoint(FilterChain chain, ConnectionProxy connection, String name) throws SQLException {
        return null;
    }

    @Override
    public void connection_rollback(FilterChain chain, ConnectionProxy connection, Savepoint savepoint) throws SQLException {

    }

    @Override
    public void connection_releaseSavepoint(FilterChain chain, ConnectionProxy connection, Savepoint savepoint) throws SQLException {

    }

    @Override
    public StatementProxy connection_createStatement(FilterChain chain, ConnectionProxy connection, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public CallableStatementProxy connection_prepareCall(FilterChain chain, ConnectionProxy connection, String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql, int autoGeneratedKeys) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql, int[] columnIndexes) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatementProxy connection_prepareStatement(FilterChain chain, ConnectionProxy connection, String sql, String[] columnNames) throws SQLException {
        return null;
    }

    @Override
    public Clob connection_createClob(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public Blob connection_createBlob(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public NClob connection_createNClob(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public SQLXML connection_createSQLXML(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public boolean connection_isValid(FilterChain chain, ConnectionProxy connection, int timeout) throws SQLException {
        return false;
    }

    @Override
    public void connection_setClientInfo(FilterChain chain, ConnectionProxy connection, String name, String value) throws SQLClientInfoException {

    }

    @Override
    public void connection_setClientInfo(FilterChain chain, ConnectionProxy connection, Properties properties) throws SQLClientInfoException {

    }

    @Override
    public String connection_getClientInfo(FilterChain chain, ConnectionProxy connection, String name) throws SQLException {
        return null;
    }

    @Override
    public Properties connection_getClientInfo(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public Array connection_createArrayOf(FilterChain chain, ConnectionProxy connection, String typeName, Object[] elements) throws SQLException {
        return null;
    }

    @Override
    public Struct connection_createStruct(FilterChain chain, ConnectionProxy connection, String typeName, Object[] attributes) throws SQLException {
        return null;
    }

    @Override
    public String connection_getSchema(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return null;
    }

    @Override
    public void connection_setSchema(FilterChain chain, ConnectionProxy connection, String schema) throws SQLException {

    }

    @Override
    public void connection_abort(FilterChain chain, ConnectionProxy connection, Executor executor) throws SQLException {

    }

    @Override
    public void connection_setNetworkTimeout(FilterChain chain, ConnectionProxy connection, Executor executor, int milliseconds) throws SQLException {

    }

    @Override
    public int connection_getNetworkTimeout(FilterChain chain, ConnectionProxy connection) throws SQLException {
        return 0;
    }

    @Override
    public boolean resultSet_next(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public void resultSet_close(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public boolean resultSet_wasNull(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public String resultSet_getString(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public boolean resultSet_getBoolean(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return false;
    }

    @Override
    public byte resultSet_getByte(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public short resultSet_getShort(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public int resultSet_getInt(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public long resultSet_getLong(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public float resultSet_getFloat(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public double resultSet_getDouble(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public BigDecimal resultSet_getBigDecimal(FilterChain chain, ResultSetProxy resultSet, int columnIndex, int scale) throws SQLException {
        return null;
    }

    @Override
    public byte[] resultSet_getBytes(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date resultSet_getDate(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Time resultSet_getTime(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Timestamp resultSet_getTimestamp(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public InputStream resultSet_getAsciiStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public InputStream resultSet_getUnicodeStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public InputStream resultSet_getBinaryStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public String resultSet_getString(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public boolean resultSet_getBoolean(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return false;
    }

    @Override
    public byte resultSet_getByte(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public short resultSet_getShort(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public int resultSet_getInt(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public long resultSet_getLong(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public float resultSet_getFloat(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public double resultSet_getDouble(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public BigDecimal resultSet_getBigDecimal(FilterChain chain, ResultSetProxy resultSet, String columnLabel, int scale) throws SQLException {
        return null;
    }

    @Override
    public byte[] resultSet_getBytes(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date resultSet_getDate(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Time resultSet_getTime(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Timestamp resultSet_getTimestamp(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public InputStream resultSet_getAsciiStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public InputStream resultSet_getUnicodeStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public InputStream resultSet_getBinaryStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public SQLWarning resultSet_getWarnings(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return null;
    }

    @Override
    public void resultSet_clearWarnings(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public String resultSet_getCursorName(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return null;
    }

    @Override
    public ResultSetMetaData resultSet_getMetaData(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return null;
    }

    @Override
    public Object resultSet_getObject(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Object resultSet_getObject(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public int resultSet_findColumn(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public Reader resultSet_getCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader resultSet_getCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal resultSet_getBigDecimal(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal resultSet_getBigDecimal(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public boolean resultSet_isBeforeFirst(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_isAfterLast(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_isFirst(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_isLast(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public void resultSet_beforeFirst(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_afterLast(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public boolean resultSet_first(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_last(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public int resultSet_getRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return 0;
    }

    @Override
    public boolean resultSet_absolute(FilterChain chain, ResultSetProxy resultSet, int row) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_relative(FilterChain chain, ResultSetProxy resultSet, int rows) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_previous(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public void resultSet_setFetchDirection(FilterChain chain, ResultSetProxy resultSet, int direction) throws SQLException {

    }

    @Override
    public int resultSet_getFetchDirection(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return 0;
    }

    @Override
    public void resultSet_setFetchSize(FilterChain chain, ResultSetProxy resultSet, int rows) throws SQLException {

    }

    @Override
    public int resultSet_getFetchSize(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return 0;
    }

    @Override
    public int resultSet_getType(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return 0;
    }

    @Override
    public int resultSet_getConcurrency(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return 0;
    }

    @Override
    public boolean resultSet_rowUpdated(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_rowInserted(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSet_rowDeleted(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public void resultSet_updateNull(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {

    }

    @Override
    public void resultSet_updateBoolean(FilterChain chain, ResultSetProxy resultSet, int columnIndex, boolean x) throws SQLException {

    }

    @Override
    public void resultSet_updateByte(FilterChain chain, ResultSetProxy resultSet, int columnIndex, byte x) throws SQLException {

    }

    @Override
    public void resultSet_updateShort(FilterChain chain, ResultSetProxy resultSet, int columnIndex, short x) throws SQLException {

    }

    @Override
    public void resultSet_updateInt(FilterChain chain, ResultSetProxy resultSet, int columnIndex, int x) throws SQLException {

    }

    @Override
    public void resultSet_updateLong(FilterChain chain, ResultSetProxy resultSet, int columnIndex, long x) throws SQLException {

    }

    @Override
    public void resultSet_updateFloat(FilterChain chain, ResultSetProxy resultSet, int columnIndex, float x) throws SQLException {

    }

    @Override
    public void resultSet_updateDouble(FilterChain chain, ResultSetProxy resultSet, int columnIndex, double x) throws SQLException {

    }

    @Override
    public void resultSet_updateBigDecimal(FilterChain chain, ResultSetProxy resultSet, int columnIndex, BigDecimal x) throws SQLException {

    }

    @Override
    public void resultSet_updateString(FilterChain chain, ResultSetProxy resultSet, int columnIndex, String x) throws SQLException {

    }

    @Override
    public void resultSet_updateBytes(FilterChain chain, ResultSetProxy resultSet, int columnIndex, byte[] x) throws SQLException {

    }

    @Override
    public void resultSet_updateDate(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Date x) throws SQLException {

    }

    @Override
    public void resultSet_updateTime(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Time x) throws SQLException {

    }

    @Override
    public void resultSet_updateTimestamp(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Timestamp x) throws SQLException {

    }

    @Override
    public void resultSet_updateAsciiStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void resultSet_updateBinaryStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void resultSet_updateCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader x, int length) throws SQLException {

    }

    @Override
    public void resultSet_updateObject(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Object x, int scaleOrLength) throws SQLException {

    }

    @Override
    public void resultSet_updateObject(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Object x) throws SQLException {

    }

    @Override
    public void resultSet_updateNull(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {

    }

    @Override
    public void resultSet_updateBoolean(FilterChain chain, ResultSetProxy resultSet, String columnLabel, boolean x) throws SQLException {

    }

    @Override
    public void resultSet_updateByte(FilterChain chain, ResultSetProxy resultSet, String columnLabel, byte x) throws SQLException {

    }

    @Override
    public void resultSet_updateShort(FilterChain chain, ResultSetProxy resultSet, String columnLabel, short x) throws SQLException {

    }

    @Override
    public void resultSet_updateInt(FilterChain chain, ResultSetProxy resultSet, String columnLabel, int x) throws SQLException {

    }

    @Override
    public void resultSet_updateLong(FilterChain chain, ResultSetProxy resultSet, String columnLabel, long x) throws SQLException {

    }

    @Override
    public void resultSet_updateFloat(FilterChain chain, ResultSetProxy resultSet, String columnLabel, float x) throws SQLException {

    }

    @Override
    public void resultSet_updateDouble(FilterChain chain, ResultSetProxy resultSet, String columnLabel, double x) throws SQLException {

    }

    @Override
    public void resultSet_updateBigDecimal(FilterChain chain, ResultSetProxy resultSet, String columnLabel, BigDecimal x) throws SQLException {

    }

    @Override
    public void resultSet_updateString(FilterChain chain, ResultSetProxy resultSet, String columnLabel, String x) throws SQLException {

    }

    @Override
    public void resultSet_updateBytes(FilterChain chain, ResultSetProxy resultSet, String columnLabel, byte[] x) throws SQLException {

    }

    @Override
    public void resultSet_updateDate(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Date x) throws SQLException {

    }

    @Override
    public void resultSet_updateTime(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Time x) throws SQLException {

    }

    @Override
    public void resultSet_updateTimestamp(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Timestamp x) throws SQLException {

    }

    @Override
    public void resultSet_updateAsciiStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream x, int length) throws SQLException {

    }

    @Override
    public void resultSet_updateBinaryStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream x, int length) throws SQLException {

    }

    @Override
    public void resultSet_updateCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader, int length) throws SQLException {

    }

    @Override
    public void resultSet_updateObject(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Object x, int scaleOrLength) throws SQLException {

    }

    @Override
    public void resultSet_updateObject(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Object x) throws SQLException {

    }

    @Override
    public void resultSet_insertRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_updateRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_deleteRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_refreshRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_cancelRowUpdates(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_moveToInsertRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public void resultSet_moveToCurrentRow(FilterChain chain, ResultSetProxy resultSet) throws SQLException {

    }

    @Override
    public Statement resultSet_getStatement(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return null;
    }

    @Override
    public Object resultSet_getObject(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref resultSet_getRef(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Blob resultSet_getBlob(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Clob resultSet_getClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Array resultSet_getArray(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Object resultSet_getObject(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref resultSet_getRef(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Blob resultSet_getBlob(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Clob resultSet_getClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Array resultSet_getArray(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Date resultSet_getDate(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Date resultSet_getDate(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time resultSet_getTime(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time resultSet_getTime(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp resultSet_getTimestamp(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp resultSet_getTimestamp(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public URL resultSet_getURL(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public URL resultSet_getURL(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public void resultSet_updateRef(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Ref x) throws SQLException {

    }

    @Override
    public void resultSet_updateRef(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Ref x) throws SQLException {

    }

    @Override
    public void resultSet_updateBlob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Blob x) throws SQLException {

    }

    @Override
    public void resultSet_updateBlob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Blob x) throws SQLException {

    }

    @Override
    public void resultSet_updateClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Clob x) throws SQLException {

    }

    @Override
    public void resultSet_updateClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Clob x) throws SQLException {

    }

    @Override
    public void resultSet_updateArray(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Array x) throws SQLException {

    }

    @Override
    public void resultSet_updateArray(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Array x) throws SQLException {

    }

    @Override
    public RowId resultSet_getRowId(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public RowId resultSet_getRowId(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public void resultSet_updateRowId(FilterChain chain, ResultSetProxy resultSet, int columnIndex, RowId x) throws SQLException {

    }

    @Override
    public void resultSet_updateRowId(FilterChain chain, ResultSetProxy resultSet, String columnLabel, RowId x) throws SQLException {

    }

    @Override
    public int resultSet_getHoldability(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return 0;
    }

    @Override
    public boolean resultSet_isClosed(FilterChain chain, ResultSetProxy resultSet) throws SQLException {
        return false;
    }

    @Override
    public void resultSet_updateNString(FilterChain chain, ResultSetProxy resultSet, int columnIndex, String nString) throws SQLException {

    }

    @Override
    public void resultSet_updateNString(FilterChain chain, ResultSetProxy resultSet, String columnLabel, String nString) throws SQLException {

    }

    @Override
    public void resultSet_updateNClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, NClob nClob) throws SQLException {

    }

    @Override
    public void resultSet_updateNClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, NClob nClob) throws SQLException {

    }

    @Override
    public NClob resultSet_getNClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public NClob resultSet_getNClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public SQLXML resultSet_getSQLXML(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public SQLXML resultSet_getSQLXML(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public void resultSet_updateSQLXML(FilterChain chain, ResultSetProxy resultSet, int columnIndex, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public void resultSet_updateSQLXML(FilterChain chain, ResultSetProxy resultSet, String columnLabel, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public String resultSet_getNString(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public String resultSet_getNString(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public Reader resultSet_getNCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader resultSet_getNCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public void resultSet_updateNCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader x, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateNCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateAsciiStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream x, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateBinaryStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream x, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader x, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateAsciiStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream x, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateBinaryStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream x, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateBlob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateBlob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateNClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateNClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader, long length) throws SQLException {

    }

    @Override
    public void resultSet_updateNCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader x) throws SQLException {

    }

    @Override
    public void resultSet_updateNCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader) throws SQLException {

    }

    @Override
    public void resultSet_updateAsciiStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream x) throws SQLException {

    }

    @Override
    public void resultSet_updateBinaryStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream x) throws SQLException {

    }

    @Override
    public void resultSet_updateCharacterStream(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader x) throws SQLException {

    }

    @Override
    public void resultSet_updateAsciiStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream x) throws SQLException {

    }

    @Override
    public void resultSet_updateBinaryStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream x) throws SQLException {

    }

    @Override
    public void resultSet_updateCharacterStream(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader) throws SQLException {

    }

    @Override
    public void resultSet_updateBlob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, InputStream inputStream) throws SQLException {

    }

    @Override
    public void resultSet_updateBlob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, InputStream inputStream) throws SQLException {

    }

    @Override
    public void resultSet_updateClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader reader) throws SQLException {

    }

    @Override
    public void resultSet_updateClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader) throws SQLException {

    }

    @Override
    public void resultSet_updateNClob(FilterChain chain, ResultSetProxy resultSet, int columnIndex, Reader reader) throws SQLException {

    }

    @Override
    public void resultSet_updateNClob(FilterChain chain, ResultSetProxy resultSet, String columnLabel, Reader reader) throws SQLException {

    }

    @Override
    public ResultSetProxy statement_executeQuery(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
        return null;
    }

    @Override
    public int statement_executeUpdate(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
        return 0;
    }

    @Override
    public void statement_close(FilterChain chain, StatementProxy statement) throws SQLException {

    }

    @Override
    public int statement_getMaxFieldSize(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public void statement_setMaxFieldSize(FilterChain chain, StatementProxy statement, int max) throws SQLException {

    }

    @Override
    public int statement_getMaxRows(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public void statement_setMaxRows(FilterChain chain, StatementProxy statement, int max) throws SQLException {

    }

    @Override
    public void statement_setEscapeProcessing(FilterChain chain, StatementProxy statement, boolean enable) throws SQLException {

    }

    @Override
    public int statement_getQueryTimeout(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public void statement_setQueryTimeout(FilterChain chain, StatementProxy statement, int seconds) throws SQLException {

    }

    @Override
    public void statement_cancel(FilterChain chain, StatementProxy statement) throws SQLException {

    }

    @Override
    public SQLWarning statement_getWarnings(FilterChain chain, StatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public void statement_clearWarnings(FilterChain chain, StatementProxy statement) throws SQLException {

    }

    @Override
    public void statement_setCursorName(FilterChain chain, StatementProxy statement, String name) throws SQLException {

    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
        return false;
    }

    @Override
    public ResultSetProxy statement_getResultSet(FilterChain chain, StatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public int statement_getUpdateCount(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public boolean statement_getMoreResults(FilterChain chain, StatementProxy statement) throws SQLException {
        return false;
    }

    @Override
    public void statement_setFetchDirection(FilterChain chain, StatementProxy statement, int direction) throws SQLException {

    }

    @Override
    public int statement_getFetchDirection(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public void statement_setFetchSize(FilterChain chain, StatementProxy statement, int rows) throws SQLException {

    }

    @Override
    public int statement_getFetchSize(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public int statement_getResultSetConcurrency(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public int statement_getResultSetType(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public void statement_addBatch(FilterChain chain, StatementProxy statement, String sql) throws SQLException {

    }

    @Override
    public void statement_clearBatch(FilterChain chain, StatementProxy statement) throws SQLException {

    }

    @Override
    public int[] statement_executeBatch(FilterChain chain, StatementProxy statement) throws SQLException {
        return new int[0];
    }

    @Override
    public Connection statement_getConnection(FilterChain chain, StatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public boolean statement_getMoreResults(FilterChain chain, StatementProxy statement, int current) throws SQLException {
        return false;
    }

    @Override
    public ResultSetProxy statement_getGeneratedKeys(FilterChain chain, StatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public int statement_executeUpdate(FilterChain chain, StatementProxy statement, String sql, int autoGeneratedKeys) throws SQLException {
        return 0;
    }

    @Override
    public int statement_executeUpdate(FilterChain chain, StatementProxy statement, String sql, int[] columnIndexes) throws SQLException {
        return 0;
    }

    @Override
    public int statement_executeUpdate(FilterChain chain, StatementProxy statement, String sql, String[] columnNames) throws SQLException {
        return 0;
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql, int autoGeneratedKeys) throws SQLException {
        return false;
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql, int[] columnIndexes) throws SQLException {
        return false;
    }

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql, String[] columnNames) throws SQLException {
        return false;
    }

    @Override
    public int statement_getResultSetHoldability(FilterChain chain, StatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public boolean statement_isClosed(FilterChain chain, StatementProxy statement) throws SQLException {
        return false;
    }

    @Override
    public void statement_setPoolable(FilterChain chain, StatementProxy statement, boolean poolable) throws SQLException {

    }

    @Override
    public boolean statement_isPoolable(FilterChain chain, StatementProxy statement) throws SQLException {
        return false;
    }

    @Override
    public ResultSetProxy preparedStatement_executeQuery(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public int preparedStatement_executeUpdate(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        return 0;
    }

    @Override
    public void preparedStatement_setNull(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, int sqlType) throws SQLException {

    }

    @Override
    public void preparedStatement_setBoolean(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, boolean x) throws SQLException {

    }

    @Override
    public void preparedStatement_setByte(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, byte x) throws SQLException {

    }

    @Override
    public void preparedStatement_setShort(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, short x) throws SQLException {

    }

    @Override
    public void preparedStatement_setInt(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, int x) throws SQLException {

    }

    @Override
    public void preparedStatement_setLong(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, long x) throws SQLException {

    }

    @Override
    public void preparedStatement_setFloat(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, float x) throws SQLException {

    }

    @Override
    public void preparedStatement_setDouble(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, double x) throws SQLException {

    }

    @Override
    public void preparedStatement_setBigDecimal(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, BigDecimal x) throws SQLException {

    }

    @Override
    public void preparedStatement_setString(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, String x) throws SQLException {

    }

    @Override
    public void preparedStatement_setBytes(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, byte[] x) throws SQLException {

    }

    @Override
    public void preparedStatement_setDate(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Date x) throws SQLException {

    }

    @Override
    public void preparedStatement_setTime(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Time x) throws SQLException {

    }

    @Override
    public void preparedStatement_setTimestamp(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Timestamp x) throws SQLException {

    }

    @Override
    public void preparedStatement_setAsciiStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void preparedStatement_setUnicodeStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void preparedStatement_setBinaryStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x, int length) throws SQLException {

    }

    @Override
    public void preparedStatement_clearParameters(FilterChain chain, PreparedStatementProxy statement) throws SQLException {

    }

    @Override
    public void preparedStatement_setObject(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Object x, int targetSqlType) throws SQLException {

    }

    @Override
    public void preparedStatement_setObject(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Object x) throws SQLException {

    }

    @Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        return false;
    }

    @Override
    public void preparedStatement_addBatch(FilterChain chain, PreparedStatementProxy statement) throws SQLException {

    }

    @Override
    public void preparedStatement_setCharacterStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader, int length) throws SQLException {

    }

    @Override
    public void preparedStatement_setRef(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Ref x) throws SQLException {

    }

    @Override
    public void preparedStatement_setBlob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Blob x) throws SQLException {

    }

    @Override
    public void preparedStatement_setClob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Clob x) throws SQLException {

    }

    @Override
    public void preparedStatement_setArray(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Array x) throws SQLException {

    }

    @Override
    public ResultSetMetaData preparedStatement_getMetaData(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public void preparedStatement_setDate(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Date x, Calendar cal) throws SQLException {

    }

    @Override
    public void preparedStatement_setTime(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Time x, Calendar cal) throws SQLException {

    }

    @Override
    public void preparedStatement_setTimestamp(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Timestamp x, Calendar cal) throws SQLException {

    }

    @Override
    public void preparedStatement_setNull(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public void preparedStatement_setURL(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, URL x) throws SQLException {

    }

    @Override
    public ParameterMetaData preparedStatement_getParameterMetaData(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        return null;
    }

    @Override
    public void preparedStatement_setRowId(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, RowId x) throws SQLException {

    }

    @Override
    public void preparedStatement_setNString(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, String value) throws SQLException {

    }

    @Override
    public void preparedStatement_setNCharacterStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader value, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setNClob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, NClob value) throws SQLException {

    }

    @Override
    public void preparedStatement_setClob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setBlob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setNClob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setSQLXML(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public void preparedStatement_setObject(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {

    }

    @Override
    public void preparedStatement_setAsciiStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setBinaryStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setCharacterStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader, long length) throws SQLException {

    }

    @Override
    public void preparedStatement_setAsciiStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x) throws SQLException {

    }

    @Override
    public void preparedStatement_setBinaryStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream x) throws SQLException {

    }

    @Override
    public void preparedStatement_setCharacterStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader) throws SQLException {

    }

    @Override
    public void preparedStatement_setNCharacterStream(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader value) throws SQLException {

    }

    @Override
    public void preparedStatement_setClob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader) throws SQLException {

    }

    @Override
    public void preparedStatement_setBlob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, InputStream inputStream) throws SQLException {

    }

    @Override
    public void preparedStatement_setNClob(FilterChain chain, PreparedStatementProxy statement, int parameterIndex, Reader reader) throws SQLException {

    }

    @Override
    public void callableStatement_registerOutParameter(FilterChain chain, CallableStatementProxy statement, int parameterIndex, int sqlType) throws SQLException {

    }

    @Override
    public void callableStatement_registerOutParameter(FilterChain chain, CallableStatementProxy statement, int parameterIndex, int sqlType, int scale) throws SQLException {

    }

    @Override
    public boolean callableStatement_wasNull(FilterChain chain, CallableStatementProxy statement) throws SQLException {
        return false;
    }

    @Override
    public String callableStatement_getString(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public boolean callableStatement_getBoolean(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return false;
    }

    @Override
    public byte callableStatement_getByte(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public short callableStatement_getShort(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public int callableStatement_getInt(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public long callableStatement_getLong(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public float callableStatement_getFloat(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public double callableStatement_getDouble(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return 0;
    }

    @Override
    public BigDecimal callableStatement_getBigDecimal(FilterChain chain, CallableStatementProxy statement, int parameterIndex, int scale) throws SQLException {
        return null;
    }

    @Override
    public byte[] callableStatement_getBytes(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date callableStatement_getDate(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Time callableStatement_getTime(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Timestamp callableStatement_getTimestamp(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Object callableStatement_getObject(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal callableStatement_getBigDecimal(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Object callableStatement_getObject(FilterChain chain, CallableStatementProxy statement, int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref callableStatement_getRef(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Blob callableStatement_getBlob(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Clob callableStatement_getClob(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Array callableStatement_getArray(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Date callableStatement_getDate(FilterChain chain, CallableStatementProxy statement, int parameterIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time callableStatement_getTime(FilterChain chain, CallableStatementProxy statement, int parameterIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp callableStatement_getTimestamp(FilterChain chain, CallableStatementProxy statement, int parameterIndex, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public void callableStatement_registerOutParameter(FilterChain chain, CallableStatementProxy statement, int parameterIndex, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public void callableStatement_registerOutParameter(FilterChain chain, CallableStatementProxy statement, String parameterName, int sqlType) throws SQLException {

    }

    @Override
    public void callableStatement_registerOutParameter(FilterChain chain, CallableStatementProxy statement, String parameterName, int sqlType, int scale) throws SQLException {

    }

    @Override
    public void callableStatement_registerOutParameter(FilterChain chain, CallableStatementProxy statement, String parameterName, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public URL callableStatement_getURL(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public void callableStatement_setURL(FilterChain chain, CallableStatementProxy statement, String parameterName, URL val) throws SQLException {

    }

    @Override
    public void callableStatement_setNull(FilterChain chain, CallableStatementProxy statement, String parameterName, int sqlType) throws SQLException {

    }

    @Override
    public void callableStatement_setBoolean(FilterChain chain, CallableStatementProxy statement, String parameterName, boolean x) throws SQLException {

    }

    @Override
    public void callableStatement_setByte(FilterChain chain, CallableStatementProxy statement, String parameterName, byte x) throws SQLException {

    }

    @Override
    public void callableStatement_setShort(FilterChain chain, CallableStatementProxy statement, String parameterName, short x) throws SQLException {

    }

    @Override
    public void callableStatement_setInt(FilterChain chain, CallableStatementProxy statement, String parameterName, int x) throws SQLException {

    }

    @Override
    public void callableStatement_setLong(FilterChain chain, CallableStatementProxy statement, String parameterName, long x) throws SQLException {

    }

    @Override
    public void callableStatement_setFloat(FilterChain chain, CallableStatementProxy statement, String parameterName, float x) throws SQLException {

    }

    @Override
    public void callableStatement_setDouble(FilterChain chain, CallableStatementProxy statement, String parameterName, double x) throws SQLException {

    }

    @Override
    public void callableStatement_setBigDecimal(FilterChain chain, CallableStatementProxy statement, String parameterName, BigDecimal x) throws SQLException {

    }

    @Override
    public void callableStatement_setString(FilterChain chain, CallableStatementProxy statement, String parameterName, String x) throws SQLException {

    }

    @Override
    public void callableStatement_setBytes(FilterChain chain, CallableStatementProxy statement, String parameterName, byte[] x) throws SQLException {

    }

    @Override
    public void callableStatement_setDate(FilterChain chain, CallableStatementProxy statement, String parameterName, Date x) throws SQLException {

    }

    @Override
    public void callableStatement_setTime(FilterChain chain, CallableStatementProxy statement, String parameterName, Time x) throws SQLException {

    }

    @Override
    public void callableStatement_setTimestamp(FilterChain chain, CallableStatementProxy statement, String parameterName, Timestamp x) throws SQLException {

    }

    @Override
    public void callableStatement_setAsciiStream(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream x, int length) throws SQLException {

    }

    @Override
    public void callableStatement_setBinaryStream(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream x, int length) throws SQLException {

    }

    @Override
    public void callableStatement_setObject(FilterChain chain, CallableStatementProxy statement, String parameterName, Object x, int targetSqlType, int scale) throws SQLException {

    }

    @Override
    public void callableStatement_setObject(FilterChain chain, CallableStatementProxy statement, String parameterName, Object x, int targetSqlType) throws SQLException {

    }

    @Override
    public void callableStatement_setObject(FilterChain chain, CallableStatementProxy statement, String parameterName, Object x) throws SQLException {

    }

    @Override
    public void callableStatement_setCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader, int length) throws SQLException {

    }

    @Override
    public void callableStatement_setDate(FilterChain chain, CallableStatementProxy statement, String parameterName, Date x, Calendar cal) throws SQLException {

    }

    @Override
    public void callableStatement_setTime(FilterChain chain, CallableStatementProxy statement, String parameterName, Time x, Calendar cal) throws SQLException {

    }

    @Override
    public void callableStatement_setTimestamp(FilterChain chain, CallableStatementProxy statement, String parameterName, Timestamp x, Calendar cal) throws SQLException {

    }

    @Override
    public void callableStatement_setNull(FilterChain chain, CallableStatementProxy statement, String parameterName, int sqlType, String typeName) throws SQLException {

    }

    @Override
    public String callableStatement_getString(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public boolean callableStatement_getBoolean(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return false;
    }

    @Override
    public byte callableStatement_getByte(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public short callableStatement_getShort(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public int callableStatement_getInt(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public long callableStatement_getLong(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public float callableStatement_getFloat(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public double callableStatement_getDouble(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return 0;
    }

    @Override
    public byte[] callableStatement_getBytes(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date callableStatement_getDate(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Time callableStatement_getTime(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Timestamp callableStatement_getTimestamp(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Object callableStatement_getObject(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public BigDecimal callableStatement_getBigDecimal(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Object callableStatement_getObject(FilterChain chain, CallableStatementProxy statement, String parameterName, Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Ref callableStatement_getRef(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Blob callableStatement_getBlob(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Clob callableStatement_getClob(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Array callableStatement_getArray(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Date callableStatement_getDate(FilterChain chain, CallableStatementProxy statement, String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Time callableStatement_getTime(FilterChain chain, CallableStatementProxy statement, String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public Timestamp callableStatement_getTimestamp(FilterChain chain, CallableStatementProxy statement, String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    @Override
    public URL callableStatement_getURL(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public RowId callableStatement_getRowId(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public RowId callableStatement_getRowId(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void callableStatement_setRowId(FilterChain chain, CallableStatementProxy statement, String parameterName, RowId x) throws SQLException {

    }

    @Override
    public void callableStatement_setNString(FilterChain chain, CallableStatementProxy statement, String parameterName, String value) throws SQLException {

    }

    @Override
    public void callableStatement_setNCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader value, long length) throws SQLException {

    }

    @Override
    public void callableStatement_setNClob(FilterChain chain, CallableStatementProxy statement, String parameterName, NClob value) throws SQLException {

    }

    @Override
    public void callableStatement_setClob(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public void callableStatement_setBlob(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream inputStream, long length) throws SQLException {

    }

    @Override
    public void callableStatement_setNClob(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public NClob callableStatement_getNClob(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public NClob callableStatement_getNClob(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void callableStatement_setSQLXML(FilterChain chain, CallableStatementProxy statement, String parameterName, SQLXML xmlObject) throws SQLException {

    }

    @Override
    public SQLXML callableStatement_getSQLXML(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public SQLXML callableStatement_getSQLXML(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public String callableStatement_getNString(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public String callableStatement_getNString(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Reader callableStatement_getNCharacterStream(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader callableStatement_getNCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public Reader callableStatement_getCharacterStream(FilterChain chain, CallableStatementProxy statement, int parameterIndex) throws SQLException {
        return null;
    }

    @Override
    public Reader callableStatement_getCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName) throws SQLException {
        return null;
    }

    @Override
    public void callableStatement_setBlob(FilterChain chain, CallableStatementProxy statement, String parameterName, Blob x) throws SQLException {

    }

    @Override
    public void callableStatement_setClob(FilterChain chain, CallableStatementProxy statement, String parameterName, Clob x) throws SQLException {

    }

    @Override
    public void callableStatement_setAsciiStream(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream x, long length) throws SQLException {

    }

    @Override
    public void callableStatement_setBinaryStream(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream x, long length) throws SQLException {

    }

    @Override
    public void callableStatement_setCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader, long length) throws SQLException {

    }

    @Override
    public void callableStatement_setAsciiStream(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream x) throws SQLException {

    }

    @Override
    public void callableStatement_setBinaryStream(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream x) throws SQLException {

    }

    @Override
    public void callableStatement_setCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public void callableStatement_setNCharacterStream(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader value) throws SQLException {

    }

    @Override
    public void callableStatement_setClob(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public void callableStatement_setBlob(FilterChain chain, CallableStatementProxy statement, String parameterName, InputStream inputStream) throws SQLException {

    }

    @Override
    public void callableStatement_setNClob(FilterChain chain, CallableStatementProxy statement, String parameterName, Reader reader) throws SQLException {

    }

    @Override
    public <T> T unwrap(FilterChain chain, Wrapper wrapper, Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(FilterChain chain, Wrapper wrapper, Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public void clob_free(FilterChain chain, ClobProxy wrapper) throws SQLException {

    }

    @Override
    public InputStream clob_getAsciiStream(FilterChain chain, ClobProxy wrapper) throws SQLException {
        return null;
    }

    @Override
    public Reader clob_getCharacterStream(FilterChain chain, ClobProxy wrapper) throws SQLException {
        return null;
    }

    @Override
    public Reader clob_getCharacterStream(FilterChain chain, ClobProxy wrapper, long pos, long length) throws SQLException {
        return null;
    }

    @Override
    public String clob_getSubString(FilterChain chain, ClobProxy wrapper, long pos, int length) throws SQLException {
        return null;
    }

    @Override
    public long clob_length(FilterChain chain, ClobProxy wrapper) throws SQLException {
        return 0;
    }

    @Override
    public long clob_position(FilterChain chain, ClobProxy wrapper, String searchstr, long start) throws SQLException {
        return 0;
    }

    @Override
    public long clob_position(FilterChain chain, ClobProxy wrapper, Clob searchstr, long start) throws SQLException {
        return 0;
    }

    @Override
    public OutputStream clob_setAsciiStream(FilterChain chain, ClobProxy wrapper, long pos) throws SQLException {
        return null;
    }

    @Override
    public Writer clob_setCharacterStream(FilterChain chain, ClobProxy wrapper, long pos) throws SQLException {
        return null;
    }

    @Override
    public int clob_setString(FilterChain chain, ClobProxy wrapper, long pos, String str) throws SQLException {
        return 0;
    }

    @Override
    public int clob_setString(FilterChain chain, ClobProxy wrapper, long pos, String str, int offset, int len) throws SQLException {
        return 0;
    }

    @Override
    public void clob_truncate(FilterChain chain, ClobProxy wrapper, long len) throws SQLException {

    }

    @Override
    public void dataSource_releaseConnection(FilterChain chain, DruidPooledConnection connection) throws SQLException {

    }

    @Override
    public DruidPooledConnection dataSource_getConnection(FilterChain chain, DruidDataSource dataSource, long maxWaitMillis) throws SQLException {
        return null;
    }

    @Override
    public int resultSetMetaData_getColumnCount(FilterChain chain, ResultSetMetaDataProxy metaData) throws SQLException {
        return 0;
    }

    @Override
    public boolean resultSetMetaData_isAutoIncrement(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSetMetaData_isCaseSensitive(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSetMetaData_isSearchable(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSetMetaData_isCurrency(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public int resultSetMetaData_isNullable(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return 0;
    }

    @Override
    public boolean resultSetMetaData_isSigned(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public int resultSetMetaData_getColumnDisplaySize(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return 0;
    }

    @Override
    public String resultSetMetaData_getColumnLabel(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }

    @Override
    public String resultSetMetaData_getColumnName(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }

    @Override
    public String resultSetMetaData_getSchemaName(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }

    @Override
    public int resultSetMetaData_getPrecision(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return 0;
    }

    @Override
    public int resultSetMetaData_getScale(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return 0;
    }

    @Override
    public String resultSetMetaData_getTableName(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }

    @Override
    public String resultSetMetaData_getCatalogName(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }

    @Override
    public int resultSetMetaData_getColumnType(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return 0;
    }

    @Override
    public String resultSetMetaData_getColumnTypeName(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }

    @Override
    public boolean resultSetMetaData_isReadOnly(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSetMetaData_isWritable(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean resultSetMetaData_isDefinitelyWritable(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return false;
    }

    @Override
    public String resultSetMetaData_getColumnClassName(FilterChain chain, ResultSetMetaDataProxy metaData, int column) throws SQLException {
        return null;
    }
}
