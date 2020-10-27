/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.service;

import java.util.Date;
import trihk.hotelbooking.dao.AccountDAO;
import trihk.hotelbooking.dao.AccountRoleDAO;
import trihk.hotelbooking.entity.Account;
import trihk.hotelbooking.entity.AccountRole;
import trihk.hotelbooking.helper.PasswordHelper;

/**
 *
 * @author TriHuynh
 */
public class AccountService {

    public Account signIn(String email, String password) {
        AccountDAO dao = new AccountDAO();
        String pwsEncrypt = PasswordHelper.encryptSHA256(password);
        Account account = dao.getByAccountnameAndPassword(email, pwsEncrypt);
        return account;
    }

    public Account signUp(Account account, int roleId) {
        AccountDAO dao = new AccountDAO();
        String pwsEncrypt = PasswordHelper.encryptSHA256(account.getPassword());
        account.setPassword(pwsEncrypt);
        AccountRoleDAO roleDao = new AccountRoleDAO();
        AccountRole role = roleDao.getById(roleId);
        account.setRoleId(role);
        return dao.insert(account);
    }
}
