package cn.stevenjack.dormitory.Config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static cn.stevenjack.dormitory.Utils.LogUtils.LogToDB;

@SuppressWarnings("JavaDoc")
@Aspect
@Component
@Slf4j
public class LogAspect {

    /***
     * 日志切面
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* cn.edu.ncst.jiaogai.*.*.*(..)))")
    public void LogPoint(JoinPoint joinPoint, Throwable ex) {
        LogToDB(joinPoint, ex);
    }

}
