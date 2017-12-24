package cn.stevenjack.dormitory.Service;

import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Repository.Query;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static cn.stevenjack.dormitory.Utils.SHAUtils.getSHA_256;

/**
 * Created by IntelliJ IDEA.
 * User: StevenJack
 * Date: ${DATA}
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserInfoService extends BaseService<User>{
    /**
     * 创建两次次加密加盐的密码SHA256值
     *
     * @param user 用户实体
     * @return 密码加盐后的实体
     */
    public static User makeSHA256PasswordWithSalt(@NotNull User user) {
        user.setPassWord(
                getSHA_256(
                        getSHA_256(
                                user.getUserName() + user.getPassWord()
                        )
                )
        );
        return user;
    }

    /**
     * 创建一次加密的密码SHA256值
     *
     * @param user 用户实体
     * @return 密码一次加密后的实体
     */
    public static User makeSHA256Password(@NotNull User user) {
        user.setPassWord(
                getSHA_256(
                        user.getPassWord()
                )
        );
        return user;
    }

    /**
     * 根据OpenId获取用户
     * @param openId
     * @return
     */
    public User getByOpenId(@NotNull String openId){
        Query query = new Query(entityManager);
        return (User) query.from(User.class)
                .whereEqual("openid",openId)
                .whereEqual("isDeleted", false)
                .createTypedQuery()
                .setFirstResult(0)
                .setMaxResults(1)
                .getSingleResult();
    }

    /**
     * 判断用户是否已存在，不管是否被删除
     *
     * @param userName
     * @return
     */
    public Boolean isExist(String userName) {
        Query query = new Query(entityManager);
        final Long count = (Long) query.from(User.class)
                .whereEqual("userName", userName)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
        return count.intValue() > 0;
    }

    @Override
    public boolean contains(@NotNull User model) {
        return !model.isDeleted() && super.contains(model);
    }

    @Override
    public void deleteAll(@NotNull List<User> modelList) {
        modelList.forEach(this::delete);
    }

    @Override
    public List<User> getAll() {
        List<User> UserList = super.getAll();
        return UserList
                .stream()
                .filter(item -> !item.isDeleted())
                .collect(Collectors.toList());
    }


    @Override
    public PageResults<User> getListByPage(@NotNull Integer currentPageNumber, @NotNull Integer pageSize) {
        String role="user";
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("role",role)
                .whereEqual("isDeleted", false);
        return super.getListByPageAndQuery(currentPageNumber, pageSize, query);
    }

    @Override
    public PageResults<User> getListByPageAndQuery(@NotNull Integer currentPageNumber,
                                                   @NotNull Integer pageSize,
                                                   @NotNull Query query) {
        query.whereEqual("isDeleted", false);
        return super.getListByPageAndQuery(currentPageNumber, pageSize, query);
    }

    @Override
    public int getCount() {
        Query query = new Query(entityManager);
        query.from(User.class)
                .whereEqual("isDeleted", false);
        return super.getCountByQuery(query);
    }

    @Override
    public int getCountByQuery(@NotNull Query query) {
        query.whereEqual("isDeleted", false);
        return super.getCountByQuery(query);
    }

    public PageResults<User> getListByCollege(@NotNull String college,
                                                    @NotNull Integer currentPageNumber,
                                                    @NotNull Integer pageSize){
        Query query=new Query(entityManager);
        query.from(User.class)
                .whereEqual("college",college);
        return super.getListByPageAndQuery(currentPageNumber,pageSize,query);
    }

    public PageResults<User> getListByBuildNameAndRole(@NotNull Role role,
                                                     @NotNull String buildName,
                                                     @NotNull Integer pageNumber,
                                                     @NotNull Integer pageSize){
        Query query=new Query(entityManager);
        query.from(User.class)
                .whereEqual("buildName",buildName)
                .whereEqual("role",role);
        return super.getListByPageAndQuery(pageNumber,pageSize,query);
    }

    public List<User> getListByRole(@NotNull Role role){
        Query query=new Query(entityManager);
        return query.from(User.class)
                .whereEqual("role", role)
                .createTypedQuery()
                .getResultList();
    }
}
