package Controller;

import Model.Actors.*;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class AccountManager {

    public static User getUserAccount() {
        return new User("Anonymous user", "no password", Database.streamRestrictionForUser);
    }

    public static Uploader getUploaderAccount() {
        String password = InputManager.getPassword();
        boolean isAuthenticated = AuthManager.uploaderAuth("Uploader", password);
        if (isAuthenticated)
            return Database.uploader;
        Printer.authFailed();
        return null;
    }

    public static Admin getAdminAccount(){
        String password = InputManager.getPassword();
        boolean isAuthenticated = AuthManager.adminAuth("Admin",password);
        if(isAuthenticated)
            return Database.admin;
        Printer.authFailed();
        return null;
    }

    public static Person getSpecialAdminAccount(){
        String userName = GeneralProcessor.getSpecialAdminUserName();
        if(userName == null){
            return null;
        }
        String password = InputManager.getPassword();
        return AuthManager.getAccount(userName,password,Database.account.SPECIAL_ADMIN);
    }

    public static Person getSpecifiedAccount(Database.account accountType){
        String userName = (accountType == Database.account.VIP_USER) ? GeneralProcessor.getVIPUserName() : InputManager.getUserName();
        if(userName == null){
            return null;
        }
        String password = InputManager.getPassword();
        return AuthManager.getAccount(userName,password,accountType);
    }

    public static String[] getUserCredentials() {
        String userName = InputManager.getUserName();
        String password = InputManager.getPassword();
        String confirmedPassword = InputManager.confirmPassword();
        boolean isPasswordMatch = AuthManager.verifyPassword(password, confirmedPassword);
        if (!isPasswordMatch) {
            return null;
        }
        return new String[]{userName, password};
    }


    public static VIPUser purchaseVIPUser() {
        String[] userCredentials = getUserCredentials();
        if (userCredentials == null) {
            Printer.passwordMismatch();
            return null;
        }
        boolean isNotAvailable = AuthManager.availableUserId(userCredentials[0], userCredentials[1], Database.account.VIP_USER);
        if (isNotAvailable == false) {
            Printer.userNameAlreadyAvailable();
            return null;
        }

        boolean isPaymentSuccessful = AuthManager.doPayment(Database.accountPlans.get(Database.account.VIP_USER.ordinal()).price);
        if(isPaymentSuccessful) {
            VIPUser user = new VIPUser(userCredentials[0], userCredentials[1]);
            Database.vipUsers.add(user);
            Printer.accountCreatedSuccessfully(userCredentials[0], userCredentials[1], "VIP");
            return user;
        }
        return null;
    }

    public static SpecialUser purchaseSpecialUser() {
        String[] userCreds = getUserCredentials();
        if (userCreds == null) {
            Printer.passwordMismatch();
            return null;
        }
        boolean isNotAvailable = AuthManager.availableUserId(userCreds[0], userCreds[1], Database.account.SPECIAL_USER);
        if (isNotAvailable == false) {
            Printer.userNameAlreadyAvailable();
            return null;
        }
        boolean isPaymentSuccessful = AuthManager.doPayment(Database.accountPlans.get(Database.account.SPECIAL_USER.ordinal()).price);
        if(isPaymentSuccessful) {
            SpecialUser user = new SpecialUser(userCreds[0], userCreds[1]);
            Database.specialUsers.add(user);
            Printer.accountCreatedSuccessfully(userCreds[0], userCreds[1], "Special User");
            return user;
        }
        return null;
    }


    public static SpecialAdmin purchaseSpecialAdmin() {
        String[] userCredentials = getUserCredentials();
        if (userCredentials == null) {
            Printer.passwordMismatch();
            return null;
        }
        boolean isNotAvailable = AuthManager.availableUserId(userCredentials[0], userCredentials[1], Database.account.SPECIAL_ADMIN);
        if (isNotAvailable == false) {
            Printer.userNameAlreadyAvailable();
            return null;
        }
        SpecialAdmin user = new SpecialAdmin(userCredentials[0], userCredentials[1]);
        Database.specialAdmins.add(user);
        return user;
    }

    public static void editSpecialAdminAccount(SpecialAdmin account){
        System.out.println("Enter the below details to update the account: ");
        String name =  InputManager.getUserName();
        String password = InputManager.getPassword();
        account.name = name;
        account.password = password;
    }

    public static void removeSpecialAdmin(){
        ListPrinter.accountPrinter(Database.specialAdmins);
        int choice = InputManager.getChoiceInput(Database.specialAdmins.size());
        if(choice == 0) return;
        Database.specialAdmins.remove(Converter.posToIdx(choice));
        Printer.specialAdminRemoved();
    }

    public static void editSpecialAdmin(){
        ListPrinter.accountPrinter(Database.specialAdmins);
        int choice = InputManager.getChoiceInput(Database.specialAdmins.size());
        if(choice == 0) return;
        editSpecialAdminAccount(Database.specialAdmins.get(Converter.posToIdx(choice)));
        Printer.specialAdminEdited();
    }
}
