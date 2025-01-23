package Controller.FlowController;

import Model.Actors.SpecialAdmin;
import Model.Actors.Uploader;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class SpecialAdminFlowManager {

    public static void initSpecialAdminFlow(){
        SpecialAdmin account = (SpecialAdmin) Database.currentUser;
        if(account.isActivated == false){
            Printer.deactivatedAccount();
            return;
        }
        while(true){
            Printer.welcomeUser(account.name);
            Printer.portalWelcomePrinter("Special Admin");
            ListPrinter.choicePrinter(Database.specialAdminChoice);
            int choice = InputManager.getChoiceInput(Database.specialAdminChoice.size());
            switch(choice){
                case 0:
                    return;
                case 1:
                    account.viewContentList();
                    break;
                case 2:
                    account.viewContent();
                    break;
                case 3:
                    account.stream();
                    break;
                case 4:
                    account.add();
                    break;
                case 5:
                    account.delete();
                    break;
                case 6:
                    account.edit();
                    break;
                case 7:
                    account.logout();
                    return;
                case 8:
                    account.exit();
                    break;
            }
        }
    }
}
