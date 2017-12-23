package cn.stevenjack.dormitory.Controller.Admin;

import cn.stevenjack.dormitory.Model.DormitoryInfo;
import cn.stevenjack.dormitory.Service.DormitoryManagementService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by StevenJack on 2017/12/23.
 */
@Controller
@RequestMapping("/DormitoryManagement")
public class DormitoryManagementController {
    @Autowired
    private DormitoryManagementService dormitoryManagementService;

    @RequiresRoles("admin")
    @GetMapping("/")
    public String index() {
        return "Admin/DormitoryManagement";
    }

    @RequiresRoles("admin")
    @PostMapping("/createDormitory/buildName/{buildName}/dormitoryName/{dormitoryName}/dormitoryType/{dormitoryType}")
    public ResponseEntity<Void> createDormitory(@PathVariable String buildName,
                                                @PathVariable String dormitoryName,
                                                @PathVariable String dormitoryType){
        DormitoryInfo dormitoryInfo = new DormitoryInfo(buildName, dormitoryName, dormitoryType, 4, null);
        dormitoryManagementService.save(dormitoryInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getDormitory/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<DormitoryInfo> getDormitory(@PathVariable int pageNumber,
                                                    @PathVariable int pageSize){
        return dormitoryManagementService.getListByPage(pageNumber,pageSize);
    }

    @RequiresRoles("admin")
    @ResponseBody
    @DeleteMapping("/delete/dormitoryInfoId/{dormitoryInfoId}")
    public ResponseEntity<Void> delete(@PathVariable int dormitoryInfoId){
        dormitoryManagementService.deleteById(dormitoryInfoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequiresRoles("admin")
    @PostMapping("/modify/dormitoryInfoId/{dormitoryInfoId}/buildName/{buildName}/dormitoryName/{dormitoryName}/dormitoryType/{dormitoryType}")
    public ResponseEntity<Void> createDormitory(@PathVariable int dormitoryInfoId,
                                                @PathVariable String buildName,
                                                @PathVariable String dormitoryName,
                                                @PathVariable String dormitoryType){
        DormitoryInfo dormitoryInfo = dormitoryManagementService.getById(dormitoryInfoId);
        dormitoryInfo.setDormitoryType(dormitoryType);
        dormitoryInfo.setBuildNumber(buildName);
        dormitoryInfo.setDormitoryNumber(dormitoryName);
        dormitoryManagementService.saveOrUpdate(dormitoryInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getTheDormitory/dormitoryInfoId/{dormitoryInfoId}")
    public DormitoryInfo getTheDormitory(@PathVariable int dormitoryInfoId){
        return dormitoryManagementService.getById(dormitoryInfoId);
    }
}
