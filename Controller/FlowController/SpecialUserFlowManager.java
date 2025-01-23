package Controller.FlowController;

import Model.Actors.SpecialUser;
import Model.Actors.VIPUser;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class SpecialUserFlowManager {
    public static void initSpecialUserFlow(){
        SpecialUser user = (SpecialUser) Database.currentUser;
        if(user.isActivated == false){
            Printer.deactivatedAccount();
            return;
        }
        while(true){
            Printer.welcomeUser(user.name);
            Printer.portalWelcomePrinter(user instanceof VIPUser ? "VIPUser" : "SpecialUser");
            ListPrinter.choicePrinter(Database.specialUserChoice);
            int choice = InputManager.getChoiceInput(Database.specialUserChoice.size());
            switch (choice){
                case 0:
                    return;
                case 1:
                    user.stream();
                    break;
                case 2:
                    user.logout();
                    return;
                case 3:
                    user.exit();
                    break;
            }
        }
    }
}
