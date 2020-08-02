//package org.cxyxh.blogshow.config;
//
//import ch.qos.logback.classic.spi.CallerData;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.db.DBAppenderBase;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModelProperty;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.Date;
//
//@Configuration
//public class LogDBAppender extends DBAppenderBase<ILoggingEvent> {
//
//    protected static final Method GET_GENERATED_KEYS_METHOD;
//    //插入sql
//    protected String insertSQL;
//
//    /* 用户id */
//    static final int IUSER = 1;
//
//    /* 浏览器名字 */
//    static final int BROWSER_NAME = 2;
//
//    /* 浏览器版本 */
//    static final int BROWSER_VERSION = 3;
//
//    /* 操作系统 */
//    static final int OPERATING_SYSTEM = 4;
//
//    /* ip */
//    static final int IP = 5;
//
//    /* 省份 */
//    static final int PROVINCE = 6;
//
//    /* 城市 */
//    static final int CITY = 7;
//
//    /* url */
//    static final int URL = 8;
//
//    /* 请求参数 */
//    static final int OPERATIONAL_PARAMETER = 9;
//
//    // level_string
//    static final int LEVEL_STRING = 10;
//
//    // logger_name 全类名
//    static final int LOGGER_NAME = 11;
//
//    /* 备注 */
//    static final int REMARK = 12;
//
//    /* 日志类型 1.前台登录 2.后台登录 3.前台访问日志 4.后台操作日志 */
//    static final int LOG_TYPE = 13;
//
//    /* 创建时间 */
//    static final int GMT_CREATE = 14;
//
//    /* 修改时间 */
//    private final int GMT_MODIFIED = 15;
//
//
//    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();
//
//    static {
//        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
//        Method getGeneratedKeysMethod;
//        try {
//            // the
//            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
//        } catch (Exception ex) {
//            getGeneratedKeysMethod = null;
//        }
//        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
//    }
//
//    @Override
//    public void start() {
//        // 将写好的sql语句赋值给insertSQL
//        insertSQL = buildInsertSQL();
//        super.start();
//    }
//
//    // 自己写新增sql语句
//    private static String buildInsertSQL() {
//        return  "INSERT INTO `blog`.`t_log`( `i_user`, `browser_name`, `browser_version`, `operating_system`, `ip`, `province`, `city`, `url`, `operational_parameter`, `remark`, `log_type`, `gmt_create`, `gmt_modified`)" +
//                " VALUES ( ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
//    }
//
//    @Override
//    protected Method getGeneratedKeysMethod() {
//        return GET_GENERATED_KEYS_METHOD;
//    }
//
//    @Override
//    protected String getInsertSQL() {
//        return insertSQL;
//    }
//
//    /**
//     * 主要修改的方法
//     *
//     * @param stmt
//     * @param event
//     * @throws SQLException
//     */
//    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
//        // event.getFormattedMessage() 日志打印内容
//        String message = event.getFormattedMessage();
//        // 如果只想存储自己打印的日志，可以这样写日志：logger.info("- XXXX")
//        if (message.startsWith("-")) { // 判断日志消息首字母为 - 的日志，记录到数据库表
//
////            stmt.setString(BROWSER_NAME,event.);
////            static final int BROWSER_NAME = 2;
//            // event.getLevel().toString() 日志级别
//            stmt.setString(LEVEL_STRING, event.getLevel().toString());
//            // new Timestamp(event.getTimeStamp()) 时间
////            stmt.setTimestamp(CREATE_TIME, new Timestamp(event.getTimeStamp()));
//
//            // event.getLoggerName() 全类名
//            stmt.setString(LOGGER_NAME, event.getLoggerName());
//        }
//
//    }
//
//    @Override
//    protected void subAppend(ILoggingEvent eventObject, Connection connection, PreparedStatement statement) throws Throwable {
//        bindLoggingEventWithInsertStatement(statement, eventObject);
//        // This is expensive... should we do it every time?
//        int updateCount = statement.executeUpdate();
//        if (updateCount != 1) {
//            addWarn("Failed to insert loggingEvent");
//        }
//    }
//
//    @Override
//    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {
//    }
//}
