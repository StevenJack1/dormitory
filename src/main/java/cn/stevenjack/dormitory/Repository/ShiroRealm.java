package cn.stevenjack.dormitory.Repository;


import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Realm是专用于安全框架的Repository
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权访问
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的用户名
        String userName = (String) super.getAvailablePrincipal(principals);

        //保存角色
        Role role;
        //拿到当前登陆的用户
        User user = userService.getById(userName);
        if (user != null) {
           role = user.getRole();
        } else {
            throw new AuthorizationException();
        }
        //给当前用户设置角色
        info.addRole(role.name());
        return info;
    }

    /**
     * 登录认证
     * <p/>
     * For most dataSources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param authToken the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        User user = userService.getById(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user.getUserName(), user.getPassWord(), user.toString()
            );
        } else {
            return null;
        }
    }
}
