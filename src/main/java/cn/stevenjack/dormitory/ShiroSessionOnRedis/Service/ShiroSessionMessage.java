package cn.stevenjack.dormitory.ShiroSessionOnRedis.Service;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.connection.DefaultMessage;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;


@SuppressWarnings("WeakerAccess")
public class ShiroSessionMessage extends DefaultMessage {

    public final MessageBody msgBody;

    public ShiroSessionMessage(byte[] channel, byte[] body) {
        super(channel, body);
        msgBody = (MessageBody) new JdkSerializationRedisSerializer().deserialize(body);
    }


    @SuppressWarnings("unused")
    @ToString
    @AllArgsConstructor
    public static class MessageBody implements Serializable {
        public final Serializable sessionId;
        public final String nodeId;
        public String msg = "";

        public MessageBody(Serializable sessionId, String nodeId) {
            this.sessionId = sessionId;
            this.nodeId = nodeId;
        }
    }
}
