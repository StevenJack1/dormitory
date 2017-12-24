package cn.stevenjack.dormitory.UtilsTest;


import org.junit.Test;

import static cn.stevenjack.dormitory.Utils.SHAUtils.*;
import static org.junit.Assert.assertEquals;

public class SHAUtilsTest {
    @Test
    public void sha1Test() {
        String sha1 = getSHA_1("admin");
        assertEquals("d033e22ae348aeb5660fc2140aec35850c4da997", sha1);
    }

    @Test
    public void sha256Test() {
        String sha256 = getSHA_256("123456123456");
        System.out.println(
                getSHA_256(
                        getSHA_256("admin" + "admin")
                        // admin + 9cf3e758a497c6274bd066d0b2168432f8a34aad95f63a65677a9a56acec94a7
                )
        );
        System.out.println(
                getSHA_256("student" + "student")
        );
        assertEquals("958d51602bbfbd18b2a084ba848a827c29952bfef170c936419b0922994c0589", sha256);
    }

    @Test
    public void MD5Test() {
        String md5 = getMD5("admin");
        assertEquals(md5, "21232f297a57a5a743894a0e4a801fc3");
    }
}
