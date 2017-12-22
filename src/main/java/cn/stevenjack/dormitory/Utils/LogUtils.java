package cn.stevenjack.dormitory.Utils;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.util.Date;

/**
 * 向数据库记录异常信息
 */
@Slf4j
public class LogUtils {
    /**
     * 向数据库记录异常信息
     *
     * @param ex 异常
     */
    public static void LogToDB(Exception ex) {
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        //出错行
        int lineNumber = stackTraceElement.getLineNumber();
        //方法签名
        String methodName = stackTraceElement.getMethodName();
        //获得类名
        String className = stackTraceElement.getClassName();

        log.error("方法" + className + "." + methodName, "参数" + stackTraceElement, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }

    /**
     * 向数据库记录异常信息,提供给Aspect调用
     *
     * @param joinPoint 切点
     * @param ex        异常
     */
    public static void LogToDB(JoinPoint joinPoint, Throwable ex) {
        //出错行
        int lineNumber = ex.getStackTrace()[0].getLineNumber();
        //方法签名
        Signature signature = joinPoint.getSignature();
        //参数
        Object[] args = joinPoint.getArgs();
        //拼接参数
        StringBuilder argString = new StringBuilder();
        if (args.length > 0)
            for (Object arg : args)
                argString.append(arg);

        log.error("方法" + signature, "参数" + argString, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }
}
