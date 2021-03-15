package cn.edu.brs._2021.controller.web.user;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.utility.JsonUtil;
import cn.edu.brs._2021.service.utility.RedisUtil;
import cn.edu.brs._2021.service.web.Login;
import cn.edu.brs._2021.service.web.UserRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/web/activity")
public class ActivityController {
    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findAll(HttpServletRequest request)
    {
        return JsonUtil.generateObjectJson(UserRequestHandler.findActivity());
    }

    @RequestMapping(value = "/findAllJoinable", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findAllJoinable(HttpServletRequest request, HttpServletResponse response)
    {
        //TODO: 删了
        HttpSession session = request.getSession(Boolean.TRUE);
        String sessionId = session.getId();
        session.setAttribute("login", Boolean.TRUE);
        RedisUtil.setKey(sessionId, "20130702358", 7 * 24 * 60 * 60);
        Cookie cookie = new Cookie("sessionId", sessionId);
        response.addCookie(cookie);
        return JsonUtil.generateObjectJson(UserRequestHandler.findJoinableActivity());
    }

    @RequestMapping(value = "/registerActivity", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String registerActivity(@RequestBody HashMap<String, String> paramMap, HttpServletRequest request)
    {
        //long studentId = Long.parseLong((String) request.getSession().getAttribute("studentId"));
        //TODO: 解决跨域问题后删除。
        long studentId = 20181015519L;
        return JsonUtil.generateObjectJson(UserRequestHandler.joinActivity(studentId
        , Integer.parseInt(paramMap.get("activityId"))));
    }

}
