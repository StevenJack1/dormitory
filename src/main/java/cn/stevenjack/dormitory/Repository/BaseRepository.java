package cn.stevenjack.dormitory.Repository;


import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;


/**
 * 只需要一个BaseDao就可以，需要访问数据库的地方，比如BaseService<T>只需要private BaseDao<T> baseDao即可
 *
 * @param <T> 实体类型
 */
@SuppressWarnings({"unchecked"})
@Transactional(timeout = 5)
//, isolation = Isolation.READ_COMMITTED)加上这个注释@NotNull会失效
// propagation = Propagation.REQUIRES_NEW,加上注释的这句单元测试不会回滚，事务失效
@Repository
@Primary
public class BaseRepository<T> {

    //获取到和当前事务关联的 EntityManager 对象
    //实际上是获得EntityManager的代理对象，是线程安全的
    @PersistenceContext
    private EntityManager entityManager;


    /**
     * 这个实体是否存在在数据库
     *
     * @param model 实体
     * @return 是否存在
     */
    @Transactional(readOnly = true)
    public boolean contains(@NotNull final T model) {
        return entityManager.contains(model);
    }

    /**
     * 使实体变为不受管理的状态
     *
     * @param model 实体
     */
    public void detach(@NotNull final T model) {
        entityManager.detach(model);
    }

    /**
     * 保存对象
     *
     * @param model 需要添加的对象
     */
    public void save(@NotNull final T model) {
        entityManager.persist(model);
    }

    /**
     * 批量保存对象
     *
     * @param modelList 需要增加的对象的集合
     *                  失败会抛异常
     */
    public void saveAll(@NotNull final List<T> modelList) {
        modelList.forEach(entityManager::persist);
    }

    /**
     * 删除对象
     *
     * @param model 需要删除的对象
     *              失败会抛异常
     */
    public void delete(@NotNull final T model) {
        entityManager.remove(entityManager.contains(model) ? model : entityManager.merge(model));
    }

    /**
     * 批量删除对象
     *
     * @param modelList 需要删除的对象的集合
     *                  失败会抛异常
     */
    public void deleteAll(@NotNull final List<T> modelList) {
        modelList.forEach(this::delete);
    }

    /**
     * 按照id删除对象
     *
     * @param modelClass 类型，比如User.class
     * @param id         需要删除的对象的id
     *                   失败抛出异常
     */
    public void deleteById(final Class<T> modelClass, @NotNull final Serializable id) {
        this.delete(this.getById(modelClass, id));
    }

    /**
     * 更新或保存对象
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    public void saveOrUpdate(@NotNull final T model) {
        entityManager.merge(model);
    }

    /**
     * 批量更新或保存对象
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    public void saveOrUpdateAll(@NotNull final List<T> modelList) {
        modelList.forEach(entityManager::merge);
    }

    /**
     * 通过主键, 查询对象
     *
     * @param modelClass 类型，比如User.class
     * @param id         主键(Serializable)
     * @return model
     */
    @Transactional(readOnly = true)
    public T getById(Class<T> modelClass, @NotNull final Serializable id) {
        return entityManager.find(modelClass, id);
    }

    /**
     * 获得全部
     *
     * @param modelClass 类型，比如User.class
     * @return List
     */
    @Transactional(readOnly = true)
    public List<T> getAll(Class<T> modelClass) {
        Query query = new Query(modelClass, entityManager);
        return query
                .createTypedQuery()
                .getResultList();
    }

    /**
     * 获得数量 利用Count(*)实现
     *
     * @param modelClass 类型，比如User.class
     * @return 数量
     */
    @Transactional(readOnly = true)
    public int getCount(Class<T> modelClass) {
        Query query = new Query(modelClass, entityManager);
        return getCountByQuery(query);
    }

    /**
     * 分页查询
     *
     * @param modelClass        类型，比如User.class
     * @param currentPageNumber 页码
     * @param pageSize          每页数量
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    public PageResults<T> getListByPage(Class<T> modelClass,
                                        @NotNull final Integer currentPageNumber,
                                        @NotNull final Integer pageSize) {
        return this.getPageResultsByQuery(
                pageSize,
                getCount(modelClass),
                currentPageNumber,
                new Query(modelClass, entityManager)
        );
    }

    /**
     * 按条件分页
     *
     * @param currentPageNumber 页码
     * @param pageSize          每页数量
     * @param query             封装的查询条件
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    public PageResults<T> getListByPageAndQuery(@NotNull final Integer currentPageNumber,
                                                @NotNull final Integer pageSize,
                                                @NotNull final Query query) {
        //获得符合条件的总数目
        //int totalCount = getCountByQuery((Query) query.deepClone());
        int totalCount = getCountByQuery(SerializationUtils.clone(query));
        return this.getPageResultsByQuery(pageSize, totalCount, currentPageNumber, query);
    }

    /**
     * 分页查询的共用方法
     *
     * @param pageSize          每页数量
     * @param totalCount        总数
     * @param currentPageNumber 当前页
     * @param query             query
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    PageResults<T> getPageResultsByQuery(@NotNull final Integer pageSize,
                                         @NotNull final Integer totalCount,
                                         @NotNull final Integer currentPageNumber,
                                         @NotNull final Query query) {
        PageResults<T> pageResults = new PageResults<>();

        pageResults.initPageResults(pageSize, totalCount, currentPageNumber);

        int FirstResult;
        if (pageResults.getCurrentPage() == 1) {
            FirstResult = 0;
        } else {
            FirstResult = pageResults.getPreviousPage() * pageSize;
        }

        List<T> list = query
                .createTypedQuery()
                .setFirstResult(FirstResult)
                .setMaxResults(pageSize)
                .getResultList();
        pageResults.setResults(list);
        return pageResults;

    }

    /**
     * 获得符合对应条件的数量 利用Count(*)实现
     *
     * @param query 查询条件
     * @return 数量
     */
    @Transactional(readOnly = true)
    public int getCountByQuery(@NotNull final Query query) {
        return Integer.parseInt(
                query.selectCount()
                        .createTypedQuery()
                        .getSingleResult()
                        .toString()
        );
    }

    /**
     * 执行Sql语句
     *
     * @param sql    sql
     * @param values 不定参数数组
     * @return 受影响的行数
     */
    public int executeSql(@NotNull String sql, @NotNull final Object... values) {
        javax.persistence.Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 1; i <= values.length; i++) {
            nativeQuery.setParameter(i, values[i - 1]);
        }
        return nativeQuery.executeUpdate();
    }

    /**
     * 通过jpql查询
     *
     * @param jpql   jpql语句
     * @param values 不定参数数组
     * @return 返回值
     */
    @Transactional(readOnly = true)
    public Object queryByJpql(@NotNull final String jpql, @NotNull final Object... values) {
        javax.persistence.Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.getResultList();
    }

    /**
     * 获得符合对应条件的数量 利用Count(*)实现
     *
     * @param jpql jpql查询条件
     * @return 数量
     */
    @Transactional(readOnly = true)
    public int getCountByJpql(@NotNull final String jpql, @NotNull final Object... values) {
        javax.persistence.Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.getResultList().size();
    }

    /**
     * 通过Jpql分页查询
     *
     * @param currentPageNumber 当前页
     * @param pageSize          每页数量
     * @param jpql              jpql语句
     * @param values            jpql参数
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    public PageResults<Object> getListByPageAndJpql(@NotNull final Integer currentPageNumber,
                                                    @NotNull final Integer pageSize,
                                                    @NotNull final String jpql,
                                                    @NotNull final Object... values) {
        int totalCount = getCountByJpql(jpql, values);

        PageResults<Object> pageResults = new PageResults<>();

        pageResults.initPageResults(pageSize, totalCount, currentPageNumber);

        javax.persistence.Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }

        int FirstResult;
        if (pageResults.getCurrentPage() == 1) {
            FirstResult = 0;
        } else {
            FirstResult = pageResults.getPreviousPage() * pageSize;
        }

        List<Object> list = query
                .setFirstResult(FirstResult)
                .setMaxResults(pageSize)
                .getResultList();
        pageResults.setResults(list);
        return pageResults;
    }

    /**
     * 执行jpql语句
     *
     * @param jpql   jpql语句
     * @param values 参数
     * @return 受影响的行数
     */
    public int executeJpql(@NotNull final String jpql, @NotNull final Object... values) {
        javax.persistence.Query query = entityManager.createQuery(jpql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.executeUpdate();
    }

    /**
     * refresh 刷新实体状态
     * 若在加载某个Entity实例之后，而数据库因另一个操作而发生变动，
     * 可以使用refresh()方法，将数据库的更改加载到Entity实例中，
     * 若Entity先前有了更改，则会被覆盖
     *
     * @param model 实体
     */
    public void refresh(@NotNull T model) {
        entityManager.refresh(model);
    }

    /**
     * 使用flush()方法，强制EntityManager中管理的所有Entity对应的数据库与实体状态同步
     */
    public void flush() {
        entityManager.flush();
    }
}

