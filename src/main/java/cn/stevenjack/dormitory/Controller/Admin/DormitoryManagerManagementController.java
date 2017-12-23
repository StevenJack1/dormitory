package cn.stevenjack.dormitory.Controller.Admin;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Controller
@RequestMapping("/DormitoryManagerManagement")
public class DormitoryManagerManagementController {
    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/DormitoryManagerManagement";
    }
}
