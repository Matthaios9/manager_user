package admin.service;

import base.entity.User;

public interface AuthService {
    void authAdmin(User user, String endpoint);

    boolean checkAuth(User user, String endpoint);

    boolean checkAuth(String endpoint);
}
