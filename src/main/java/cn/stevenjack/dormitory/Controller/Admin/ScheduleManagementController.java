package cn.stevenjack.dormitory.Controller.Admin;

import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.DormitoryManagementService;
import cn.stevenjack.dormitory.Service.ScheduleManagementService;
import cn.stevenjack.dormitory.Service.UserInfoService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.batik.svggen.font.table.LigatureSet;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @Autowired
    private DormitoryManagementService dormitoryManagementService;

    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/ScheduleManagement";
    }


    /**
     *  通过角色和宿舍楼获取用户
     * @param buildName
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getInfo/buildName/{buildName}")
    public List<User> getDormitoryManager(@PathVariable String buildName){
        List<User> dormitoryManagerList = userInfoService.getAll().stream().
                                filter(p -> (p.getRole() == Role.dormitoryManager && p.getBuildName().equals(buildName)))
                                .collect(toList());
        System.out.println(dormitoryManagerList.size());
//        for (int i = 0; i < dormitoryManagerList.size(); i++){
//
//        }
//        System.out.println( userInfoService.getAll().stream().filter(p -> (p.getRole() == Role.dormitoryManager && p.getBuildName().equals(buildName)))
//                .collect(toList()));

        return dormitoryManagerList;
    }


}
