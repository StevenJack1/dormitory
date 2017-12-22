package cn.stevenjack.dormitory.Config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Created by 张志豪 on 2016/10/24 0024.
 * The problem is that when you return a view name from your controller method,
 * the Spring DispatcherServlet will do a forward to the given view, preserving the original PUT method.
 * <p>
 * On attempting to handle this forward, Tomcat will refuse it,
 * with the justification that a PUT to a JSP could be construed to mean "replace this JSP file on the server with the content of this request."
 * <p>
 * Really you want your controller to handle your PUT requests and then to subsequently forward to your JSPs as GET.
 * Fortunately Servlet 3.0 provides a means to filter purely on the FORWARD dispatcher.
 */
@SuppressWarnings("JavaDoc")
public class GetMethodConvertingFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(wrapRequest((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        // do nothing
    }

    /**
     * 将PUT和DELETE请求变成GET请求（否则JSP回报错，因为其不支持
     *
     * @param request
     * @return
     */
    private static HttpServletRequestWrapper wrapRequest(HttpServletRequest request) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
    }
}
