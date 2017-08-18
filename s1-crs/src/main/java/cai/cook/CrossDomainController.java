package cai.cook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by cai on 2017/8/15.
 */
@Controller
@RequestMapping("/cross")
public class CrossDomainController {
    @RequestMapping("/info")
    @ResponseBody
    public void showInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        System.out.println("uri"+requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("url"+requestURL);
        boolean isLogin = false;
        HashMap<String, String> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        for(Cookie cookie : cookies){
            if("jwt".equals(cookie.getName())){
                isLogin = true;
                map.put("jwt",cookie.getValue());
                break;
            }
        }
        }
        if(isLogin){
            map.put("login","success2");
            PrintWriter writer = response.getWriter();
            writer.println(map.toString());
            writer.flush();
        }else {
            //String redirectUrl = "http://s1.crs.com:8100/cross/info";
            String redirectUrl = requestURL.toString();
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            //重定向到CAS
            System.out.println("开始同定向到："+redirectUrl);
            response.sendRedirect("http://cas.sso.com:8000/login?redirectUrl="+redirectUrl);
        }
    }
}
