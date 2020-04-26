package ua.nure.huzhyn.services.implementation;

import javax.servlet.http.HttpServletRequest;

public class LogoutService {

    public LogoutService() {
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        request.getSession(false);
    }
}
