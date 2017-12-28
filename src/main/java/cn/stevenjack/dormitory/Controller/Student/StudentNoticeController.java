package cn.stevenjack.dormitory.Controller.Student;

import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * Created by IntelliJ IDEA.
 * User: StevenJack
 * Date: ${DATA}
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/StudentNotice")
public class StudentNoticeController {
    @Autowired
    private UserInfoService userInfoService;

    @RequiresRoles("student")
    @GetMapping("/")
    public String index() {
        return "Student/StudentNotice";
    }

    @RequiresRoles("student")
    @GetMapping("/getInfo")
    @ResponseBody
    public User getInfo(){
        String userName = getSubject().getPrincipal().toString();
        return userInfoService.getById(userName);
    }

}
