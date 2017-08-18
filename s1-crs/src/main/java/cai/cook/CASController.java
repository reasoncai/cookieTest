package cai.cook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cai on 2017/8/15.
 */
@Controller
public class CASController {
    @RequestMapping("/cas/attch")
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String remoteUser = request.getRemoteUser();
        String remoteHost = request.getRemoteHost();
        String redirectUrl = request.getParameter("redirectUrl");
        String jwt = request.getParameter("jwt");
        System.out.println(remoteUser+remoteHost+redirectUrl+jwt);
            Cookie cookie = new Cookie("jwt",jwt);
        cookie.setPath("/");
        System.out.println(redirectUrl);
        response.addCookie(cookie);
        //重定向到CAS
        response.sendRedirect(redirectUrl);
    }
}
