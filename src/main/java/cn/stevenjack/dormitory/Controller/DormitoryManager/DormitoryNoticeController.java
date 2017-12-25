package cn.stevenjack.dormitory.Controller.DormitoryManager;

import cn.stevenjack.dormitory.Model.ScheduleInfo;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.ScheduleManagementService;
import cn.stevenjack.dormitory.Service.SortClassService;
import cn.stevenjack.dormitory.Service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * Created by StevenJack on 2017/12/25.
 */
@Controller
@RequestMapping("/DormitoryNotice")
public class DormitoryNoticeController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ScheduleManagementService scheduleManagementService;

    @RequiresRoles("dormitoryManager")
    @GetMapping("/")
    public String index() {
        return "DormitoryManager/DormitoryNotice";
    }

    @RequiresRoles("dormitoryManager")
    @ResponseBody
    @GetMapping("/getNoticeList")
    public List<ScheduleInfo> getNoticeList(){
        String userName = getSubject().getPrincipal().toString();

        List<ScheduleInfo> scheduleInfoList = scheduleManagementService.getAll().stream().filter(p -> (p.getUser().getUserName().equals(userName)))
                .collect(toList());
        SortClassService sortClass = new SortClassService();
        Collections.sort(scheduleInfoList,sortClass);
//        for (int i = 0;i < scheduleInfoList.size();i++){
//            System.out.println(scheduleInfoList.get(i).getWorkTime());
//        }
        return scheduleInfoList;
    }
}
