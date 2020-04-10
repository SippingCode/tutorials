package com.baeldung.manuallogout;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BasicAuthController {

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session;
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "redirect:/login?logout";
    }
}
