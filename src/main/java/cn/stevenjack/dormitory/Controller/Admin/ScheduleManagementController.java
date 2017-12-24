package cn.stevenjack.dormitory.Controller.Admin;

import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.ScheduleInfo;
import cn.stevenjack.dormitory.Model.ScheduleStatus;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.DormitoryManagementService;
import cn.stevenjack.dormitory.Service.ScheduleManagementService;
import cn.stevenjack.dormitory.Service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return dormitoryManagerList;
    }


    /**
     *  根据日期和用户过滤出
     * @param userName
     * @param monday
     * @param tuesday
     * @param wednesday
     * @param thursday
     * @param friday
     * @param saturday
     * @param sunday
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getSchedule/userName/{userName}/monday/{monday}/tuesday/{tuesday}/wednesday/{wednesday}/thursday/{thursday}/friday/{friday}/saturday/{saturday}/sunday/{sunday}")
    public List<ScheduleInfo> getSchedule(@PathVariable String userName,
                                                  @PathVariable String monday,
                                                  @PathVariable String tuesday,
                                                  @PathVariable String wednesday,
                                                  @PathVariable String thursday,
                                                  @PathVariable String friday,
                                                  @PathVariable String saturday,
                                                  @PathVariable String sunday){
        List<ScheduleInfo> scheduleInfoList = userInfoService.getById(userName).getScheduleInfoList().
                                            stream().
                                            filter(p -> (p.getWorkTime().equals(monday) ||
                                                    p.getWorkTime().equals(tuesday) ||
                                                    p.getWorkTime().equals(wednesday) ||
                                                    p.getWorkTime().equals(thursday) ||
                                                    p.getWorkTime().equals(friday) ||
                                                    p.getWorkTime().equals(saturday) ||
                                                    p.getWorkTime().equals(sunday)))
                                            .collect(toList());
        return scheduleInfoList;
    }

    /**
     * 增加或者修改排班时间及事件
     * @param weekDay
     * @param userName
     * @param scheduleStatus
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/modify/weekDay/{weekDay}/userName/{userName}/scheduleStatus/{scheduleStatus}")
    public ResponseEntity<Void> modify(@PathVariable String weekDay,
                                                @PathVariable String userName,
                                                @PathVariable String scheduleStatus){
        List<ScheduleInfo> scheduleInfoList = scheduleManagementService.getAll();
        Boolean flag = false;
        for (int i = 0;i < scheduleInfoList.size();i++){
            if (scheduleInfoList.get(i).getWorkTime().equals(weekDay) && scheduleInfoList.get(i).getUser().getUserName().equals(userName)){
                flag = true;
            }
        }
        if (flag == true){
            ScheduleInfo scheduleInfo = scheduleManagementService.getByWorkTimeAndUser(weekDay, userInfoService.getById(userName));
            scheduleInfo.setWorkTime(weekDay);
            scheduleInfo.setScheduleStatus(ScheduleStatus.valueOf(scheduleStatus));
            scheduleManagementService.saveOrUpdate(scheduleInfo);
        } else {
            ScheduleInfo scheduleInfo = new ScheduleInfo(weekDay,ScheduleStatus.valueOf(scheduleStatus),userInfoService.getById(userName));
            scheduleManagementService.save(scheduleInfo);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
