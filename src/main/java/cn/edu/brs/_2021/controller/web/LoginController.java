package cn.edu.brs._2021.controller.web;

import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.utility.JsonUtil;
import cn.edu.brs._2021.service.utility.RedisUtil;
import cn.edu.brs._2021.service.web.Login;
import com.mysql.cj.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/web")
public class LoginController {

    /**
     * 验证账户密码是否存在。通过验证的账户会在 response 中同时提供 7 天的 cookie。
     * @param loginMap 封装的 JSON，必须有 studentId 和 password。
     * @return code: -1（账号密码无记录） 1（验证成功）
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public @ResponseBody String login(@RequestBody HashMap<String, String> loginMap, HttpServletRequest request, HttpServletResponse response)
    {
        User paramUser = new User().setStudentId(Long.parseLong(loginMap.get("studentId"))).setPassword(loginMap.get("password"));
        boolean isSuccess = Login.validate(paramUser);
        if (isSuccess) {
            HttpSession session = request.getSession(Boolean.TRUE);
            session.setAttribute("studentId", loginMap.get("studentId"));
        }
        return isSuccess ? JsonUtil.generateNormalJson(null) : JsonUtil.generateErrorJson(null);
    }

}
