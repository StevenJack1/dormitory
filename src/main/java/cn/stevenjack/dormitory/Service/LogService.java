package cn.stevenjack.dormitory.Service;


import cn.stevenjack.dormitory.Model.Log;
import cn.stevenjack.dormitory.Repository.Query;
import org.springframework.stereotype.Service;

@Service
public class LogService extends BaseService<Log> {

    public Long getExceptionCountByCallerFilename(String callerFilename){
        Query query = new Query(entityManager);
        return (Long) query.from(Log.class)
                .whereEqual("caller_filename", callerFilename)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
    }

    public Long getExceptionCount(){
        Query query = new Query(entityManager);
        return (Long) query.from(Log.class)
                .selectCount()
                .createTypedQuery()
                .getSingleResult();
    }
}
