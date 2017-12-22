package cn.stevenjack.dormitory.ShiroSessionOnRedis.Listener;


import cn.stevenjack.dormitory.ShiroSessionOnRedis.Repository.CachingShiroSessionDao;
import cn.stevenjack.dormitory.ShiroSessionOnRedis.Service.ShiroSessionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class ShiroSessionListener implements SessionListener {

    @Setter
    private ShiroSessionService shiroSessionService;

    @Setter
    private CachingShiroSessionDao sessionDao;

    @Override
    public void onStart(@NotNull final Session session) {
        // 会话创建时触发
        log.info("session {} onStart", session.getId());
    }

    @Override
    public void onStop(@NotNull final Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        log.info("session {} onStop", session.getId());
    }

    @Override
    public void onExpiration(@NotNull final Session session) {
        sessionDao.delete(session);
        shiroSessionService.sendUnCacheSessionMessage(session.getId());
        log.info("session {} onExpiration", session.getId());
    }

}