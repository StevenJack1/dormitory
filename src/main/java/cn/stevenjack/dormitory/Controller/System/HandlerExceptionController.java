package cn.stevenjack.dormitory.Controller.System;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static cn.stevenjack.dormitory.Utils.LogUtils.LogToDB;

@ControllerAdvice
public class HandlerExceptionController {

    @Autowired
    private Environment env;

    /**
     * 无权限访问跳转
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerUnauthenticatedException(NativeWebRequest request, UnauthorizedException ex) {
        if ((request.getHeader("accept").contains("application/json"))) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map<String, java.io.Serializable> attributes = new HashMap<>();
            attributes.put("error", "403");
            if (env.getDefaultProfiles()[0].equals("development")) {
                attributes.put("error", "403");
                attributes.put("cause", HttpStatus.FORBIDDEN);
                attributes.put("MSG", ex.getMessage());
            } else {
                attributes.put("error", "403");
                attributes.put("cause", HttpStatus.FORBIDDEN);
            }
            view.setAttributesMap(attributes);
            return new ModelAndView(view);
        } else {
            ModelAndView modelAndView = new ModelAndView("../../403");
            modelAndView.addObject("MSG", ex.getMessage());
            return modelAndView;
        }
    }

    /**
     * 全局Controller异常处理
     *
     * @param ex 异常
     * @return 跳转出错页面
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handlerExceptionMethod(Exception ex, NativeWebRequest request) {

        //将错误信息记录到数据库 这里记录到数据库的原因是：异常没有向上抛，在这里就被消化了
        LogToDB(ex);

        if ((request.getHeader("accept").contains("application/json"))) {//如果不是异步请求
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map<String, java.io.Serializable> attributes = new HashMap<>();
            attributes.put("error", "500");
            if (env.getDefaultProfiles()[0].equals("development")) {
                attributes.put("cause", HttpStatus.INTERNAL_SERVER_ERROR);
                attributes.put("MSG", ex.toString());
                attributes.put("Line", ex.getStackTrace()[0].getLineNumber());
                attributes.put("Method", ex.getStackTrace()[0].getMethodName());
            } else {
                attributes.put("cause", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            view.setAttributesMap(attributes);
            return new ModelAndView(view);
        } else {
            ModelAndView modelAndView = new ModelAndView("../../500");
            modelAndView.addObject("MSG", ex.toString());
            modelAndView.addObject("Line", ex.getStackTrace()[0].getLineNumber());
            modelAndView.addObject("Method", ex.getStackTrace()[0].getMethodName());
            Writer writer = new StringWriter();
            //客户端输出一下，打开F12可以看到
            ex.printStackTrace(new PrintWriter(writer));
            modelAndView.addObject("detailed", writer.toString());
            return modelAndView;
        }
    }
}
