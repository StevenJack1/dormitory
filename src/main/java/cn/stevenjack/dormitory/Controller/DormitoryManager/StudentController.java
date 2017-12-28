package cn.stevenjack.dormitory.Controller.DormitoryManager;

import cn.stevenjack.dormitory.Model.DormitoryInfo;
import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.DormitoryManagementService;
import cn.stevenjack.dormitory.Service.UserInfoService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * Created by StevenJack on 2017/12/25.
 */
@Controller
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DormitoryManagementService dormitoryManagementService;


    @RequiresRoles("dormitoryManager")
    @GetMapping("/")
    public String index() {
        return "DormitoryManager/Student";
    }

    /**
     * 分页获取学生
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("dormitoryManager")
    @ResponseBody
    @GetMapping("/getStudentList/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<User> getStudentList(@PathVariable int pageNumber,
                                            @PathVariable int pageSize){

        return userInfoService.getByRole(Role.student,pageNumber,pageSize);
    }

    @ResponseBody
    @GetMapping("/getInfo")
    public User getInfo(){
        String userName = getSubject().getPrincipal().toString();
        return userInfoService.getById(userName);
    }

    // 修改
    @ResponseBody
    @PostMapping("/modifyStudent")
    public ResponseEntity<Void> modifyStudent(@RequestBody @Valid User student){
        String userName = getSubject().getPrincipal().toString();
        User dormitory=userInfoService.getById(userName);

        User user = userInfoService.getById(student.getUserName());

        user.setStudentOrDormitoryNumber(student.getStudentOrDormitoryNumber());
        user.setAge(student.getAge());
        user.setNativePlace(student.getNativePlace());
        user.setPhoneNumber(student.getPhoneNumber());
        user.setProfession(student.getProfession());
        user.setClassNumber(student.getClassNumber());
        user.setCollege(student.getCollege());
        user.setName(student.getName());
        user.setSex(student.isSex());

        user.setDormitoryInfo(dormitoryManagementService.getByBuildNumberAndDormitoryNumber(dormitory.getBuildName(),student.getDormitoryInfo().getDormitoryNumber()));

        userInfoService.saveOrUpdate(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
