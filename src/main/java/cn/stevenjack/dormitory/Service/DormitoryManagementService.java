package cn.stevenjack.dormitory.Service;

import cn.stevenjack.dormitory.Model.DormitoryInfo;
import cn.stevenjack.dormitory.Model.ScheduleInfo;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Repository.Query;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Service
public class DormitoryManagementService extends BaseService<DormitoryInfo> {



    public DormitoryInfo getByBuildNumberAndDormitoryNumber(@NotNull String  buildNumber,
                                             @NotNull String dormitoryNumber){
        Query query=new Query(entityManager);
        return (DormitoryInfo) query.from(DormitoryInfo.class)
                .whereEqual("buildNumber", buildNumber)
                .whereEqual("dormitoryNumber",dormitoryNumber)
                .createTypedQuery()
                .setFirstResult(0)
                .setMaxResults(1)
                .getSingleResult();
    }
}
