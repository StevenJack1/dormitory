package cn.stevenjack.dormitory.Controller.Student;

import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: StevenJack
 * Date: ${DATA}
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/StudentAccount")
public class StudentAccountController {
    @Autowired
    private UserInfoService userInfoService;

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

        userInfoService.saveOrUpdate(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
