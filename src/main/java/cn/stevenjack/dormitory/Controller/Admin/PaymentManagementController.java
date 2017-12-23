package cn.stevenjack.dormitory.Controller.Admin;

import cn.stevenjack.dormitory.Model.DormitoryInfo;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.UserInfoService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Controller
@RequestMapping("/PaymentManagement")
public class PaymentManagementController {

    @Autowired
    private UserInfoService userInfoService;

    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/PaymentManagement";
    }

    /**
     * 通过college获取用户
     * @param college
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getInfo/college/{college}/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<User> getDormitory(@PathVariable String college,
                                          @PathVariable int pageNumber,
                                          @PathVariable int pageSize){
        return userInfoService.getListByCollege(college,pageNumber,pageSize);
    }

    /**
     * 缴费
     * @param studentId
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/isPayment/studentId/{studentId}")
    public ResponseEntity<Void> isPayment(@PathVariable String studentId){
        User student = userInfoService.getById(studentId);
        student.setPayment(true);
        student.setPaymentTime(new Date());
        userInfoService.saveOrUpdate(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
