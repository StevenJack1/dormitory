package cn.stevenjack.dormitory.Controller.Admin;

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

import static cn.stevenjack.dormitory.Utils.SHAUtils.getSHA_256;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Controller
@RequestMapping("/DormitoryManagerManagement")
public class DormitoryManagerManagementController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DormitoryManagementService dormitoryManagementService;
    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/DormitoryManagerManagement";
    }

    /**
     * 创建宿舍管理员
     * @param dormitoryManager
     * @return
     */
    @ResponseBody
    @PostMapping("/createDormitoryManager")
    public ResponseEntity<Void> create(@RequestBody @Valid User dormitoryManager){
        dormitoryManager.setDeleted(false);
        dormitoryManager.setPayment(false);
        String password = getSHA_256(dormitoryManager.getPassWord() + dormitoryManager.getUserName());
        dormitoryManager.setPassWord(password);
        dormitoryManager.setRole(Role.dormitoryManager);
        userInfoService.save(dormitoryManager);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * 分页获取宿舍管理员
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getDormitoryManager/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<User> getStudentList(@PathVariable int pageNumber,
                                            @PathVariable int pageSize){
        return userInfoService.getByRole(Role.dormitoryManager,pageNumber,pageSize);
    }

    // 获取信息
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getInfo/id/{id}")
    public User getInfo(@PathVariable String id){
        return userInfoService.getById(id);
    }

    // 修改
    @ResponseBody
    @PostMapping("/modifyDormitoryManager")
    public ResponseEntity<Void> modifyStudent(@RequestBody @Valid User dormitoryManager){
        User user = userInfoService.getById(dormitoryManager.getUserName());

        user.setStudentOrDormitoryNumber(dormitoryManager.getStudentOrDormitoryNumber());
        user.setAge(dormitoryManager.getAge());
        user.setNativePlace(dormitoryManager.getNativePlace());
        user.setPhoneNumber(dormitoryManager.getPhoneNumber());
        user.setName(dormitoryManager.getName());
        user.setSex(dormitoryManager.isSex());
        user.setBuildName(dormitoryManager.getBuildName());
        userInfoService.saveOrUpdate(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //删除
    @RequiresRoles("admin")
    @DeleteMapping("/deleteDormitoryManager/id/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id){
        if(userInfoService.getById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            userInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
