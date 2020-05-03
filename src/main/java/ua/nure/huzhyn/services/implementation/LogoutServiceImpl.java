package ua.nure.huzhyn.services.implementation;

import ua.nure.huzhyn.services.LogoutService;

import javax.servlet.http.HttpServletRequest;

public class LogoutServiceImpl implements LogoutService {

    public LogoutServiceImpl() {
    }

    @Override
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        request.getSession(false);
    }
}
