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

    /**
     * 添加宿舍
     * @param buildName
     * @param dormitoryName
     * @param dormitoryType
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/createDormitory/buildName/{buildName}/dormitoryName/{dormitoryName}/dormitoryType/{dormitoryType}")
    public ResponseEntity<Void> createDormitory(@PathVariable String buildName,
                                                @PathVariable String dormitoryName,
                                                @PathVariable String dormitoryType){
        if (dormitoryType.equals("普通类")){
            DormitoryInfo dormitoryInfo = new DormitoryInfo(buildName, dormitoryName, dormitoryType, 4,400, null);
            dormitoryManagementService.save(dormitoryInfo);
        } else{
            DormitoryInfo dormitoryInfo = new DormitoryInfo(buildName, dormitoryName, dormitoryType, 4,800, null);
            dormitoryManagementService.save(dormitoryInfo);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 分页获取宿舍
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getDormitory/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<DormitoryInfo> getDormitory(@PathVariable int pageNumber,
                                                    @PathVariable int pageSize){
        return dormitoryManagementService.getListByPage(pageNumber,pageSize);
    }

    /**
     * 删除宿舍
     * @param dormitoryInfoId
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @DeleteMapping("/delete/dormitoryInfoId/{dormitoryInfoId}")
    public ResponseEntity<Void> delete(@PathVariable int dormitoryInfoId){
        dormitoryManagementService.deleteById(dormitoryInfoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 修改宿舍信息
     * @param dormitoryInfoId
     * @param buildName
     * @param dormitoryName
     * @param dormitoryType
     * @return
     */
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

    /**
     * 获取一个宿舍信息
     * @param dormitoryInfoId
     * @return
     */
    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/getTheDormitory/dormitoryInfoId/{dormitoryInfoId}")
    public DormitoryInfo getTheDormitory(@PathVariable int dormitoryInfoId){
        return dormitoryManagementService.getById(dormitoryInfoId);
    }
}
