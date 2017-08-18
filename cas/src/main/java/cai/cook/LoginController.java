package cai.cook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by cai on 2017/8/15.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String remoteUser = request.getRemoteUser();
        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        int localPort = request.getLocalPort();
        System.out.println("localP"+localPort);
        System.out.println("remoteP"+request.getRemotePort());
        System.out.println("user"+remoteUser);
        System.out.println("host"+remoteHost);
        System.out.println("remoteAddr"+remoteAddr);
        System.out.println("url"+request.getRequestURL());
        boolean isLogin = false;
        String redirectUrl = request.getParameter("redirectUrl");
        System.out.println("redirectUrl"+redirectUrl);
        Cookie[] cookies = request.getCookies();
        String ssojwt="";

        URL url = new URL(redirectUrl);
        String host = url.getHost();
        int port = url.getPort();
        System.out.println("host+port"+host+port);
        String attch = "http://"+host+":"+port+"/cas/attch";

        if(cookies!=null){
        for(Cookie cookie : cookies){
            if("jwt".equals(cookie.getName())){
                isLogin = true;
                ssojwt = cookie.getValue();
                break;
            }
        }}
        if(isLogin){

            response.sendRedirect(attch+"?redirectUrl="+redirectUrl+"&jwt="+ssojwt);

        }else {
            //创立CAS的cookie
            String jwt = "caijwt";
            Cookie cookie = new Cookie("jwt",jwt);
            cookie.setPath("/");
            response.addCookie(cookie);

            //String attch = "http://s1.cai.com:8100/cas/attch";
            //redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            //重定向到CAS
            response.sendRedirect(attch+"?redirectUrl="+redirectUrl+"&jwt="+jwt);
        }
    }
}
