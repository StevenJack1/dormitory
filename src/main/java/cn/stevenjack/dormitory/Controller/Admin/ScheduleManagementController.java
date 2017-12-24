package cn.stevenjack.dormitory.Controller.Admin;

import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.ScheduleManagementService;
import cn.stevenjack.dormitory.Service.UserInfoService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Controller
@RequestMapping("/ScheduleManagement")
public class ScheduleManagementController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ScheduleManagementService scheduleManagementService;

    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/ScheduleManagement";
    }


    /**
     *  通过角色和宿舍楼获取用户
     * @param buildName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getInfo/buildName/{buildName}/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<User> getDormitoryManager(@PathVariable String buildName,
                                              @PathVariable int pageNumber,
                                              @PathVariable int pageSize){

        return userInfoService.getListByBuildNameAndRole(Role.dormitoryManager,buildName,pageNumber,pageSize);

//        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
