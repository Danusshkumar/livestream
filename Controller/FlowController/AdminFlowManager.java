package Controller.FlowController;

import Model.Actors.Admin;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class AdminFlowManager {
    public static void initAdminFlow(){
        Admin admin = (Admin) Database.currentUser;
        while(true){
            Printer.welcomeUser(admin.name);
            Printer.portalWelcomePrinter("Admin");
            ListPrinter.choicePrinter(Database.adminChoice);
            int choice = InputManager.getChoiceInput(Database.adminChoice.size());
            switch(choice){
                case 0:
                    return;
                case 1:
                    admin.viewContentList();
                    break;
                case 2:
                    admin.viewContent();
                    break;
                case 3:
                    admin.stream();
                    break;
                case 4:
                    admin.add();
                    break;
                case 5:
                    admin.delete();
                    break;
                case 6:
                    admin.edit();
                    break;
                case 7:
                    admin.deleteAll();
                    break;
                case 8:
                    admin.hackAll();
                    break;
                case 9:
                    admin.setDefault();
                    break;
                case 10:
                    admin.changePwd();
                    break;
                case 11:
                    admin.viewAccounts();
                    break;
                case 12:
                    admin.updatePlans();
                    break;
                case 13:
                    admin.manipulateActivationStatusOfAccounts();
                    break;
                case 14:
                    admin.manipulateSpecialAdmins();
                    break;
                case 15:
                    admin.viewRevenue();
                    break;
                case 16:
                    admin.viewLogins();
                    break;
                case 17:
                    admin.viewActivities();
                    break;
                case 18:
                    admin.viewSubscriptionActivities();
                    break;
                case 19:
                    admin.manipulateStreamRestrictions();
                    break;
                case 20:
                    admin.changeAdminMessage();
                    break;
                case 21:
                    admin.logout();
                    return;
                case 22:
                    admin.exit();
                    break;
            }
        }
    }
}
