package cn.stevenjack.dormitory.TestUtils;


import cn.stevenjack.dormitory.Model.User;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static cn.stevenjack.dormitory.Utils.StringUtils.getRandomUUID;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:Spring.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@ActiveProfiles("test")
public class BaseTest extends AbstractShiroTest {

    @Autowired
    protected MockHttpSession mockHttpSession;

    @Autowired
    protected DefaultWebSecurityManager securityManager;

    protected MockMvc mockMvc;

    protected InternalResourceViewResolver viewResolver;

    @Mock
    protected Subject mockSubject;

    public BaseTest() {
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    }

    @Before
    public void before() {

        //Easymock

        /*//1.  Create a mock authenticated Subject instance for the test to run:
        Subject mockSubject = createNiceMock(Subject.class);
        expect(mockSubject.isAuthenticated()).andReturn(true);

        //2. Bind the subject to the current thread:
        setSubject(mockSubject);*/

        //使用Mockito实现上面同样的功能

        //1. 初始化Mockito
        MockitoAnnotations.initMocks(this);

        //2.  Create a mock authenticated Subject instance for the test to run:
        Mockito.when(mockSubject.isAuthenticated()).thenReturn(true);
        Mockito.when(mockSubject.getPrincipal()).thenReturn("admin");
        Mockito.when(mockSubject.hasRole("schoolAdmin")).thenReturn(true);


        //3. 绑定这个subject到当前线程
        setSubject(mockSubject);
    }

    @Test
    public void NullTest() {

    }

    public static User getRandomUser() {
        return new User(getRandomUUID(), getRandomUUID());
    }
}
