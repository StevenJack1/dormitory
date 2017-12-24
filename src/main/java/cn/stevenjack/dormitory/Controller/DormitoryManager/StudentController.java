package cn.stevenjack.dormitory.Controller.DormitoryManager;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by StevenJack on 2017/12/25.
 */
@Controller
@RequestMapping("/Student")
public class StudentController {

    @RequiresRoles("dormitoryManager")
    @GetMapping("/")
    public String index() {
        return "DormitoryManager/Student";
    }
}
