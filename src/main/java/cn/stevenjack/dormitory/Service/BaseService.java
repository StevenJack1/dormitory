package cn.stevenjack.dormitory.Service;


import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Repository.BaseRepository;
import cn.stevenjack.dormitory.Repository.Query;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * BaseService作为所有Service的基类，需要使用的话，需要先编写一个继承自此类的类
 *
 * @param <T> 实体类型
 */
@SuppressWarnings({"unchecked", "unused", "SpringJavaAutowiredMembersInspection"})
@Transactional
class BaseService<T> {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private BaseRepository<T> baseRepository;

    private Class<T> modelClass;

    BaseService() {
        modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 这个实体是否存在在数据库
     *
     * @param model 实体
     * @return 是否存在
     */
    @Transactional(readOnly = true)
    public boolean contains(@NotNull final T model) {
        return baseRepository.contains(model);
    }

    /**
     * 使实体变为不受管理的状态
     *
     * @param model 实体
     */
    public void detach(@NotNull final T model) {
        baseRepository.detach(model);
    }

    /**
     * 保存对象
     *
     * @param model 需要添加的对象
     */
    public void save(@NotNull final T model) {
        baseRepository.save(model);
    }

    /**
     * 批量保存对象
     *
     * @param modelList 需要增加的对象的集合
     *                  失败会抛异常
     */
    public void saveAll(@NotNull final List<T> modelList) {
        baseRepository.saveAll(modelList);
    }

    /**
     * 删除对象
     *
     * @param model 需要删除的对象
     *              失败会抛异常
     */
    public void delete(@NotNull final T model) {
        baseRepository.delete(model);
    }

    /**
     * 批量删除对象
     *
     * @param modelList 需要删除的对象的集合
     *                  失败会抛异常
     */
    public void deleteAll(@NotNull final List<T> modelList) {
        baseRepository.deleteAll(modelList);
    }

    /**
     * 按照id删除对象
     *
     * @param id 需要删除的对象的id
     *           失败抛出异常
     */
    public void deleteById(@NotNull final Serializable id) {
        baseRepository.deleteById(modelClass, id);
    }

    /**
     * 更新或保存对象
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    public void saveOrUpdate(@NotNull final T model) {
        baseRepository.saveOrUpdate(model);
    }

    /**
     * 批量更新或保存对象
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    public void saveOrUpdateAll(@NotNull final List<T> modelList) {
        baseRepository.saveOrUpdateAll(modelList);
    }

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键(Serializable)
     * @return model
     */
    @Transactional(readOnly = true)
    public T getById(@NotNull final Serializable id) {
        return baseRepository.getById(modelClass, id);
    }

    /**
     * 获得全部
     *
     * @return List
     */
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return baseRepository.getAll(modelClass);
    }


    /**
     * 分页查询
     *
     * @param currentPageNumber 页码
     * @param pageSize          每页数量
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    public PageResults<T> getListByPage(@NotNull final Integer currentPageNumber,
                                           @NotNull final Integer pageSize) {
        return (PageResults<T>) baseRepository.getListByPage(modelClass, currentPageNumber, pageSize);
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
    public PageResults<T> getListByPageAndQuery(@NotNull Integer currentPageNumber,
                                                @NotNull Integer pageSize,
                                                @NotNull Query query) {
        return baseRepository.getListByPageAndQuery(currentPageNumber, pageSize, query);
    }

    /**
     * 获得数量 利用Count(*)实现
     *
     * @return 数量
     */
    @Transactional(readOnly = true)
    public int getCount() {
        return baseRepository.getCount(modelClass);
    }

    /**
     * 获得符合对应条件的数量 利用Count(*)实现
     *
     * @param query 查询条件
     * @return 数量
     */
    @Transactional(readOnly = true)
    public int getCountByQuery(@NotNull final Query query) {
        return baseRepository.getCountByQuery(query);
    }

    /**
     * 执行Sql语句
     *
     * @param sql    sql
     * @param values 不定参数数组
     * @return 受影响的行数
     */
    public int executeSql(@NotNull final String sql, @NotNull final Object... values) {
        return baseRepository.executeSql(sql, values);
    }

    /**
     * 通过jpql查询
     *
     * @param jpql   jpql语句
     * @param values 参数列表
     * @return 受影响的行数
     */
    @Transactional(readOnly = true)
    public Object queryByJpql(@NotNull final String jpql, @NotNull final Object... values) {
        return baseRepository.queryByJpql(jpql, values);
    }

    /**
     * 获得符合对应条件的数量 利用Count(*)实现
     *
     * @param jpql jpql查询条件
     * @return 数量
     */
    @Transactional(readOnly = true)
    public int getCountByJpql(@NotNull final String jpql, @NotNull final Object... values) {
        return baseRepository.getCountByJpql(jpql, values);
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
    public PageResults<Object> getListByPageAndJpql(@NotNull Integer currentPageNumber,
                                                    @NotNull Integer pageSize,
                                                    @NotNull final String jpql,
                                                    @NotNull Object... values) {
        return baseRepository.getListByPageAndJpql(currentPageNumber, pageSize, jpql, values);
    }

    /**
     * 执行jpql语句
     *
     * @param jpql   jpql语句
     * @param values 参数列表
     * @return 受影响的行数
     */
    public int executeJpql(@NotNull final String jpql, @NotNull final Object... values) {
        return baseRepository.executeJpql(jpql, values);
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
        baseRepository.refresh(model);
    }

    /**
     * 使用flush()方法，强制EntityManager中管理的所有Entity对应的数据库与实体状态同步
     */
    public void flush() {
        baseRepository.flush();
    }
}
