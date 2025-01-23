package Controller;

import Controller.FlowController.*;
import Model.Actors.*;
import Model.DataModel.LoginHistory;
import Model.Database;
import View.InputManager;
import View.Printer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AuthManager {

    public static void storeLoginRegistry(){
        LoginHistory currentLogin = new LoginHistory(LoginHistory.autoId++,Database.currentUser.name, LocalDateTime.now());
        Database.currentUser.loginId = LoginHistory.autoId - 1;
        Database.loginHistories.add(currentLogin);
    }

    public static void authenticateUser(){
        Database.currentUser = AccountManager.getUserAccount();
        AuthManager.storeLoginRegistry();
        UserFlowManager.initUserFlow();
    }

    public static void authenticateUploader(){
        Uploader uploader = AccountManager.getUploaderAccount();
        if(uploader != null) {
            Database.currentUser = uploader;
            AuthManager.storeLoginRegistry();
            UploaderFlowManager.initUploaderFlow();
        }
    }

    public static void authenticateAdmin(){
        Admin admin = AccountManager.getAdminAccount();
        if(admin != null){
            Database.currentUser = admin;
            AuthManager.storeLoginRegistry();
            AdminFlowManager.initAdminFlow();
        }
    }

    public static void authenticateSpecialAdmin(){
        Database.currentUser = AccountManager.getSpecialAdminAccount();
        if(Database.currentUser != null){
            AuthManager.storeLoginRegistry();
            SpecialAdminFlowManager.initSpecialAdminFlow();
        }
    }

    public static void authenticateSpecifiedAccount(Database.account account){
        Database.currentUser = AccountManager.getSpecifiedAccount(account);
        if(Database.currentUser != null){
            AuthManager.storeLoginRegistry();
            SpecialUserFlowManager.initSpecialUserFlow();
        }
    }

    public static void authenticateToSubscriptionPortal(){
        SubscriptionAndAccountPurchaseFlowManager.initFlow();
    }

    public static Person getAccount(String userName,String password, Database.account accountType){
        ArrayList userList;
        if(accountType == Database.account.VIP_USER){
            userList = Database.vipUsers;
        } else if(accountType == Database.account.SPECIAL_USER){
            userList = Database.specialUsers;
        } else if(accountType == Database.account.SPECIAL_ADMIN){
            userList = Database.specialAdmins;
        }
        else
            userList = new ArrayList();

        for(int i = 0;i<userList.size();i++){
            Person person = (Person)userList.get(i);
            if(userName.equals(person.name) && password.equals(person.password)) {
                return person;
            }
        }     
        Printer.authFailed();
        return null;
    }

    public static boolean uploaderAuth(String name, String password){
        return name.equals(Database.uploader.name) && password.equals(Database.uploader.password);
    }

    public static boolean adminAuth(String name, String password){
        return name.equals(Database.admin.name) && password.equals(Database.admin.password);
    }

    public static boolean verifyPassword(String password1,String password2){
        return password1.equals(password2);
    }

    public static boolean doPayment(int givenAmount){
        int amount = InputManager.getAmount();
        if(amount != givenAmount) {
            Printer.paymentUnSuccessful();
            return false;
        }
        Database.revenue += amount;
        Printer.paymentSuccessful();
        return true;
    }

    public static boolean availableUserId(String username,String password, Database.account accountType){
        ArrayList userList;
        if(accountType == Database.account.VIP_USER){
            userList = Database.vipUsers;
        } else if(accountType == Database.account.SPECIAL_USER){
            userList = Database.specialUsers;
        } else if(accountType == Database.account.SPECIAL_ADMIN){
            userList = Database.specialAdmins;
        }
        else
            userList = new ArrayList();

        for(int i = 0;i<userList.size();i++){
            Person person = (Person)userList.get(i);
            if(username.equals(person.name))
                return false;
        }
        return true;
    }
}
