package cn.stevenjack.dormitory.ShiroSessionOnRedis.Repository;


import cn.stevenjack.dormitory.Utils.LogUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


/**
 * 管理Redis中的Session
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Slf4j
public class ShiroSessionRepository {

    private static final String REDIS_SHIRO_SESSION = "shiro-session:";
    private static final int SESSION_VAL_TIME_SPAN = 1800;

    // 保存到Redis中key的前缀 prefix+sessionId
    @Setter
    private String redisShiroSessionPrefix = REDIS_SHIRO_SESSION;

    // 设置会话的过期时间
    @Setter
    private int redisShiroSessionTimeout = SESSION_VAL_TIME_SPAN;

    @Getter
    @Setter
    private RedisTemplate<String, Session> redisTemplate;


    /**
     * 保存session
     */
    public void saveSession(@NotNull final Session session) {
        try {
            getRedisTemplate().opsForValue()
                    .set(
                            buildRedisSessionKey(
                                    session.getId()
                            )
                            , session
                            , redisShiroSessionTimeout
                            , TimeUnit.SECONDS);
        } catch (Exception e) {
            LogUtils.LogToDB(e);
            log.error("save session to redis error");
        }
    }

    /**
     * 更新session
     */
    public void updateSession(@NotNull final Session session) {
        try {
            getRedisTemplate().boundValueOps(
                    buildRedisSessionKey(
                            session.getId()
                    )
            ).set(session
                    , redisShiroSessionTimeout
                    , TimeUnit.SECONDS
            );
        } catch (Exception e) {
            LogUtils.LogToDB(e);
            log.error("update session error");
        }
    }


    /**
     * 刷新session
     */
    public void refreshSession(@NotNull final Serializable sessionId) {
        getRedisTemplate().expire(
                buildRedisSessionKey(sessionId)
                , redisShiroSessionTimeout
                , TimeUnit.SECONDS
        );
    }


    /**
     * 删除session
     */
    public void deleteSession(@NotNull final Serializable id) {
        try {
            getRedisTemplate().delete(buildRedisSessionKey(id));
        } catch (Exception e) {
            LogUtils.LogToDB(e);
            log.error("delete session error");
        }
    }


    /**
     * 获取session
     */
    public Session getSession(@NotNull final Serializable id) {
        Session session = null;
        try {
            session = getRedisTemplate().boundValueOps(buildRedisSessionKey(id)).get();
        } catch (Exception e) {
            LogUtils.LogToDB(e);
            log.info("get session error");
        }
        return session;
    }

    /**
     * 通过sessionId获取sessionKey
     */
    private String buildRedisSessionKey(@NotNull final Serializable sessionId) {
        return redisShiroSessionPrefix + sessionId;
    }
}
