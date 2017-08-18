package cai.cook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cai on 2017/8/15.
 */
@Controller
@RequestMapping("/cookie")
public class CookieController {
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("token", "cairuisen");
        cookie.setPath("/");
        cookie.setDomain("cai.com");
        //cookie.setMaxAge(10); //设置cookie的过期时间是10s
        response.addCookie(cookie);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Map get(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null==cookies) {
            System.out.println("没有cookie=========");
        } else {
            for(Cookie cookie : cookies){
                map.put(cookie.getName(),cookie.getValue());
            }
        }
        return map;
    }
}
