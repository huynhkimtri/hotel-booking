/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import trihk.hotelbooking.dao.UserDAO;
import trihk.hotelbooking.dao.UserRoleDAO;
import trihk.hotelbooking.entity.User;
import trihk.hotelbooking.entity.UserRole;
import trihk.hotelbooking.helper.PasswordHelper;

/**
 *
 * @author TriHuynh
 */
public class UserService {

    public User signIn(String email, String password) {
        UserDAO dao = new UserDAO();
        String pwsEncrypt = PasswordHelper.encryptSHA256(password);
        User account = dao.getByUsernameAndPassword(email, pwsEncrypt);
        return account;
    }

    public User signUp(User account, int roleId) {
        UserDAO dao = new UserDAO();
        String pwsEncrypt = PasswordHelper.encryptSHA256(account.getPassword());
        account.setPassword(pwsEncrypt);
        UserRoleDAO roleDao = new UserRoleDAO();
        UserRole role = roleDao.getById(roleId);
        account.setRoleId(role);
        return dao.insert(account);
    }
}
