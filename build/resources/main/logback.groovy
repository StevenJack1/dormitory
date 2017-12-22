import ch.qos.logback.classic.db.DBAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.db.DriverManagerConnectionSource

import static ch.qos.logback.classic.Level.ERROR
import static ch.qos.logback.classic.Level.WARN

scan("60 seconds")
appender("stdOut", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{40} - %msg%n"
    }
}
appender("db-classic-mysql", DBAppender) {
    connectionSource(DriverManagerConnectionSource) {
        driverClass = "com.mysql.cj.jdbc.Driver"
        url = "jdbc:mysql:///SpringMVCSeedProject?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false"
        user = "root"
        password = ""
    }
}
logger("org", ERROR, ["db-classic-mysql"], false)
logger("com", ERROR, ["db-classic-mysql"], false)
logger("java", ERROR, ["db-classic-mysql"], false)
logger("javax", ERROR, ["db-classic-mysql"], false)
logger("net", ERROR, ["db-classic-mysql"], false)
logger("cn", ERROR, ["db-classic-mysql"], false)
root(WARN, ["stdOut"])