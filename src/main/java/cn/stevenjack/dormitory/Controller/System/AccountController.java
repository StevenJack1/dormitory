package cn.stevenjack.dormitory.Controller.System;


import cn.stevenjack.dormitory.Model.Role;
import cn.stevenjack.dormitory.Model.User;
import cn.stevenjack.dormitory.Service.UserInfoService;
import cn.stevenjack.dormitory.Service.UserService;
import cn.stevenjack.dormitory.Utils.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import static cn.stevenjack.dormitory.Utils.LogUtils.LogToDB;
import static cn.stevenjack.dormitory.Utils.SHAUtils.getSHA_256;


@SuppressWarnings({"JavaDoc", "unused"})
@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 转向登录界面
     *
     * @return 登录界面
     */
    @GetMapping("/Account/Login")
    public String LoginPage() {
        return "/Account/login";
    }

    /**
     * 转向注册界面
     *
     * @return
     */
    @GetMapping("/Account/Register")
    public String RegisterPage() {
        return "/Account/Register/index";
    }

    @GetMapping("/Account/Register/Success")
    public String RegisterPageSuccess() {
        return "/Account/Register/success";
    }

    /**
     * 检查用户名是否重复
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @GetMapping("/Account/userName/{userName}")
    public ResponseEntity<Void> userSearch(@PathVariable String userName) {
        if (userInfoService.isExist(userName)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    /**
     * 注册用户
     * @param userName
     * @param phoneNumber
     * @param sex
     * @param password1
     * @param httpSession
     * @return
     * @throws Exception
     */
    @PostMapping("/Account/Register")
    public String createUser(@RequestParam("userName") String userName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("sex") String sex,
                             @RequestParam("password1") String password1,
                             HttpSession httpSession) throws Exception {
        if(userName != null && phoneNumber != null && password1 != null){
            User user = new User();
            user.setUserName(userName);
            user.setPhoneNumber(phoneNumber);
            String password = getSHA_256(userName + password1);
            user.setPassWord(getSHA_256(password));
            if(sex.equals("0")){
               user.setSex(false);
            } else {
                user.setSex(true);
            }
//            user.setRole(Role.student);
//            user.setAge(1);
//            user.setCollege("");
            userInfoService.save(user);
            return "redirect:/Account/Register/Success";
        }
        return "redirect:/Account/Register";

    }


    /**
     * 接收用户登录传参，判断是否登陆成功
     *
     * @param UserName
     * @param Password
     * @param validateCode
     * @param RememberMe
     * @param session
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    @PostMapping("/Account/Login")
    public String Login(@RequestParam("UserName") String UserName,
                        @RequestParam("Password") String Password,
                        @RequestParam("validateCode") String validateCode,
                        @RequestParam(value = "RememberMe", required = false) String RememberMe,
                        HttpSession session) {
        String code = (String) session.getAttribute("validateCode");
        if (code == null || StringUtils.isEmpty(validateCode) || !StringUtils.equals(code.toLowerCase(), validateCode.toLowerCase())) {
            //登陆失败清除session的验证码，以防暴力破解
            session.removeAttribute("validateCode");
            return "redirect:/Account/Login";
        }

        UsernamePasswordToken token = null;
        try {
            Subject user = SecurityUtils.getSubject();
            token = new UsernamePasswordToken(UserName.trim(), getSHA_256(Password));
            if (RememberMe != null && RememberMe.equals("on"))
                token.setRememberMe(true);
            user.login(token);
            //得到所有Role
            Role role = userService.getById(UserName).getRole();
            System.out.println(role);
            if (Role.admin == role || Role.student == role || Role.dormitoryManager == role) {
                return "redirect:/";
            } else {
                return "redirect:/Account/Login";
            }
        } catch (Exception e) {
            LogToDB(e);
            if (token != null) {
                token.clear();
            }
            return "redirect:/Account/Login";
        } finally {
            session.removeAttribute("validateCode");
        }
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/Account/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("image/jpeg");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIXED, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/Account/LogOut")
    public String LogOut() {
        SecurityUtils.getSubject().logout();
        return "redirect:/Account/Login";
    }
}
