package cn.stevenjack.dormitory.Controller.DormitoryManager;

import cn.stevenjack.dormitory.Model.DormitoryInfo;
import cn.stevenjack.dormitory.Model.VisitorsInfo;
import cn.stevenjack.dormitory.Service.VisitorService;
import cn.stevenjack.dormitory.Utils.PageResults;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by StevenJack on 2017/12/25.
 */
@Controller
@RequestMapping("/Visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @RequiresRoles("dormitoryManager")
    @GetMapping("/")
    public String index() {
        return "DormitoryManager/Visitor";
    }


    /**
     * 存储访客对象
     * @param buildName
     * @param visitorName
     * @param otherName
     * @param cause
     * @param end
     * @return
     */
    @RequiresRoles("dormitoryManager")
    @PostMapping("/create/buildName/{buildName}/visitorName/{visitorName}/otherName/{otherName}/cause/{cause}/end/{end}")
    public ResponseEntity<Void> createDormitory(@PathVariable String buildName,
                                                @PathVariable String visitorName,
                                                @PathVariable String otherName,
                                                @PathVariable String cause,
                                                @PathVariable String end){
        VisitorsInfo visitorsInfo = new VisitorsInfo(buildName,visitorName,otherName,cause,end);
        visitorsInfo.setBegin(new Date());
        visitorService.save(visitorsInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 获取来访人员列表
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequiresRoles("dormitoryManager")
    @ResponseBody
    @GetMapping("/getVisitor/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageResults<VisitorsInfo> getVisitor(@PathVariable int pageNumber,
                                                   @PathVariable int pageSize){
        return visitorService.getListByPage(pageNumber,pageSize);
    }

    /**
     * 删除记录
     * @param visitorInfoId
     * @return
     */
    @RequiresRoles("dormitoryManager")
    @ResponseBody
    @DeleteMapping("/delete/visitorInfoId/{visitorInfoId}")
    public ResponseEntity<Void> delete(@PathVariable int visitorInfoId){
        visitorService.deleteById(visitorInfoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 获取单个信息
     * @param visitorInfoId
     * @return
     */
    @RequiresRoles("dormitoryManager")
    @ResponseBody
    @GetMapping("/getTheVisitor/visitorInfoId/{visitorInfoId}")
    public VisitorsInfo getTheDormitory(@PathVariable int visitorInfoId){
        return visitorService.getById(visitorInfoId);
    }


    /**
     * 修改访问人员信息
     * @param visitorInfoId
     * @return
     */
    @RequiresRoles("dormitoryManager")
    @PostMapping("/modify/visitorInfoId/{visitorInfoId}/modifyBuildName/{modifyBuildName}/modifyVisitorName/{modifyVisitorName}/modifyOtherName/{modifyOtherName}/modifyCause/{modifyCause}/modifyEnd/{modifyEnd}")
    public ResponseEntity<Void> createDormitory(@PathVariable int visitorInfoId,
                                                @PathVariable String modifyBuildName,
                                                @PathVariable String modifyVisitorName,
                                                @PathVariable String modifyOtherName,
                                                @PathVariable String modifyCause,
                                                @PathVariable String modifyEnd){
        VisitorsInfo visitorsInfo = visitorService.getById(visitorInfoId);
        visitorsInfo.setBuildName(modifyBuildName);
        visitorsInfo.setCause(modifyCause);
        visitorsInfo.setEnd(modifyEnd);
        visitorsInfo.setVisitorName(modifyVisitorName);
        visitorsInfo.setOtherName(modifyOtherName);
        visitorService.saveOrUpdate(visitorsInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
