package cn.stevenjack.dormitory.ShiroSessionOnRedis.Session;

import org.apache.shiro.session.mgt.SimpleSession;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * 由于SimpleSession lastAccessTime更改后也会调用SessionDao update方法，
 * 增加标识位，如果只是更新lastAccessTime SessionDao update方法直接返回
 * <p>
 * Session Attribute
 * DefaultSubjectContext.PRINCIPALS_SESSION_KEY 保存 principal
 * DefaultSubjectContext.AUTHENTICATED_SESSION_KEY 保存 boolean是否登陆
 *
 * @see org.apache.shiro.subject.support.DefaultSubjectContext
 */
@SuppressWarnings("WeakerAccess")
public class ShiroSession extends SimpleSession implements Serializable {

    // 除lastAccessTime以外其他字段发生改变时为true
    private boolean isChanged;

    public ShiroSession() {
        super();
        this.setChanged(true);
    }

    public ShiroSession(String host) {
        super(host);
        this.setChanged(true);
    }


    @Override
    public void setId(@NotNull final Serializable id) {
        super.setId(id);
        this.setChanged(true);
    }

    @Override
    public void setStopTimestamp(@NotNull final Date stopTimestamp) {
        super.setStopTimestamp(stopTimestamp);
        this.setChanged(true);
    }

    @Override
    public void setExpired(final boolean expired) {
        super.setExpired(expired);
        this.setChanged(true);
    }

    @Override
    public void setTimeout(final long timeout) {
        super.setTimeout(timeout);
        this.setChanged(true);
    }

    @Override
    public void setHost(@NotNull final String host) {
        super.setHost(host);
        this.setChanged(true);
    }

    @Override
    public void setAttributes(@NotNull final Map<Object, Object> attributes) {
        super.setAttributes(attributes);
        this.setChanged(true);
    }

    @Override
    public void setLastAccessTime(@NotNull final Date lastAccessTime) {
        if (getLastAccessTime() != null) {
            long last = getLastAccessTime().getTime();
            long now = lastAccessTime.getTime();
            //如果60s内访问 则不更新session,否则需要更新远端过期时间
            if ((last - now) / 1000 >= 60) {
                //发送通知
                //设置为已改变，更新到redis
                this.setChanged(true);
            }
        }
        super.setLastAccessTime(lastAccessTime);
    }


    @Override
    public void setAttribute(@NotNull final Object key,
                             @NotNull final Object value) {
        super.setAttribute(key, value);
        this.setChanged(true);
    }

    @Override
    public Object removeAttribute(@NotNull final Object key) {
        this.setChanged(true);
        return super.removeAttribute(key);
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        super.stop();
        this.setChanged(true);
    }

    /**
     * 设置过期
     */
    @Override
    protected void expire() {
        this.stop();
        this.setExpired(true);
    }


    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(final boolean isChanged) {
        this.isChanged = isChanged;
    }
}
