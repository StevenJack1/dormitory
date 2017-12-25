package cn.stevenjack.dormitory.Controller.DormitoryManager;

import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * Created by StevenJack on 2017/12/25.
 */
@Controller
@RequestMapping("/DormitoryAccount")
public class DormitoryAccountController {
    @Autowired
    private UserInfoService userInfoService;

    @RequiresRoles("dormitoryManager")
    @PostMapping("/modify/name/{name}/age/{age}/phoneNumber/{phoneNumber}/buildName/{buildName}/nativePlace/{nativePlace}/studentOrDormitoryNumber/{studentOrDormitoryNumber}/sex{sex}")
    public ResponseEntity<Void> modify(@PathVariable String name,
                                       @PathVariable int age,
                                       @PathVariable String phoneNumber,
                                       @PathVariable String buildName,
                                       @PathVariable String nativePlace,
                                       @PathVariable String studentOrDormitoryNumber,
                                       @PathVariable String sex
                                       ){
        if(name != null && buildName != null && nativePlace != null && phoneNumber != null && studentOrDormitoryNumber != null && sex != null){
            String userName = getSubject().getPrincipal().toString();
            User user=userInfoService.getById(userName);
            user.setName(name);
            user.setAge(age);
            user.setBuildName(buildName);
            user.setPhoneNumber(phoneNumber);
            user.setNativePlace(nativePlace);
            user.setStudentOrDormitoryNumber(studentOrDormitoryNumber);
            user.setPayment(false);
            user.setDeleted(false);
            if (sex.equals("男")){
                user.setSex(true);
            } else{
                user.setSex(false);
            }
            userInfoService.save(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }


    }
}
