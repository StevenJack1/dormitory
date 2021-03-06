package cn.stevenjack.dormitory.Controller.Admin;

import cn.stevenjack.dormitory.Model.DormitoryInfo;
import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.DormitoryManagementService;
import cn.stevenjack.dormitory.Service.UserInfoService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.batik.svggen.font.table.LigatureSet;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static cn.stevenjack.dormitory.Utils.SHAUtils.getSHA_256;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Controller
@RequestMapping("/StudentManagement")
public class StudentManagementController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DormitoryManagementService dormitoryManagementService;

    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/StudentManagement";
    }

    /**
     * 创建学生
     * @param student
     * @return
     */
    @ResponseBody
    @PostMapping("/createStudent")
    public ResponseEntity<Void> create(@RequestBody @Valid User student){
        student.setDeleted(false);
        student.setPayment(false);
        String password = getSHA_256(student.getPassWord() + student.getUserName());
        student.setPassWord(getSHA_256(password));
        student.setDormitoryInfo(dormitoryManagementService.getByBuildNumberAndDormitoryNumber(student.getDormitoryInfo().getBuildNumber(),student.getDormitoryInfo().getDormitoryNumber()));
        student.setRole(Role.student);
        userInfoService.save(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * 分页获取学生
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getStudentList/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<User> getStudentList(@PathVariable int pageNumber,
                                                   @PathVariable int pageSize){
        return userInfoService.getByRole(Role.student,pageNumber,pageSize);
    }

    // 获取信息
    @ResponseBody
    @GetMapping("/getInfo/id/{id}")
    public User getInfo(@PathVariable String id){
        return userInfoService.getById(id);
    }

    // 修改
    @ResponseBody
    @PostMapping("/modifyStudent")
    public ResponseEntity<Void> modifyStudent(@RequestBody @Valid User student){
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
        user.setDormitoryInfo(dormitoryManagementService.getByBuildNumberAndDormitoryNumber(student.getDormitoryInfo().getBuildNumber(),student.getDormitoryInfo().getDormitoryNumber()));

        userInfoService.saveOrUpdate(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //删除
    @DeleteMapping("/deleteStudent/id/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id){
        if(userInfoService.getById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            userInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
