package cn.stevenjack.dormitory.Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by 张志豪 on 2016/11/2 0002.
 * <p>
 * disable FAIL_ON_EMPTY_BEANS
 * 修改了一些jackson的默认配置，解决生成json时出现的问题
 *
 * @see <a href="http://stackoverflow.com/questions/28862483/spring-and-jackson-how-to-disable-fail-on-empty-beans-through-responsebody"></a>
 */
public class JsonMapper extends ObjectMapper {
    public JsonMapper() {
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        this.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }
}
