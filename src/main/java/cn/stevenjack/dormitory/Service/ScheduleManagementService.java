package cn.stevenjack.dormitory.Service;

import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.ScheduleInfo;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Repository.Query;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by StevenJack on 2017/12/24.
 */
@Service
public class ScheduleManagementService extends BaseService<ScheduleInfo>{

    public ScheduleInfo getByWorkTimeAndUser(@NotNull String  workTime,
                                      @NotNull User user){
        Query query=new Query(entityManager);
        return (ScheduleInfo) query.from(ScheduleInfo.class)
                .whereEqual("workTime", workTime)
                .whereEqual("user",user)
                .createTypedQuery()
                .setFirstResult(0)
                .setMaxResults(1)
                .getSingleResult();
    }
}
