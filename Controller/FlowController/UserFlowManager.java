package Controller.FlowController;

import Model.Actors.User;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class UserFlowManager {
    static public void initUserFlow(){
        User user = (User) Database.currentUser;
        while(true){
            Printer.welcomeUser("User");
            Printer.portalWelcomePrinter("User");
            ListPrinter.choicePrinter(Database.userChoice);
            int choice = InputManager.getChoiceInput(Database.userChoice.size());
            switch(choice){
                case 0:
                    return;
                case 1:
                    user.viewContentList();
                    break;
                case 2:
                    user.viewContent();
                    break;
                case 3:
                    user.stream();
                    break;
                case 4:
                    user.logout();
                    return;
                case 5:
                    user.exit();
                    break;
            }
        }
    }
}
