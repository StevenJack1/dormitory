package cn.stevenjack.dormitory.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;


@SuppressWarnings("unused")
public class CustomerLocalDateDeserialize extends JsonDeserializer<LocalDate> {

    /**
     * 反序列化 2016-10-05 格式的字符串为 LocalDate 类型
     *
     * @param paramJsonParser
     * @param paramDeserializationContext
     * @return
     * @throws IOException
     */
    @Override
    public LocalDate deserialize(JsonParser paramJsonParser,
                                 DeserializationContext paramDeserializationContext)
            throws IOException {
        String str = paramJsonParser.getText().trim();
        return LocalDate.parse(str);
    }
}
