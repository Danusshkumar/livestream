package Controller.FlowController;

import Controller.AccountManager;
import Controller.AuthManager;
import Controller.InitialisationManager;
import Model.Actors.Person;
import Model.Actors.SpecialUser;
import Model.Actors.Uploader;
import Model.Actors.VIPUser;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

import java.sql.Connection;

public class FlowManager {
    public static void initFlow(){
        InitialisationManager.initDB();
        while(true){
            Printer.welcomePrinter();
            ListPrinter.listPortal();
            int choice = InputManager.getChoiceInput(Database.portal.size());
            if(choice == 0)
                continue;
            portalActivator(choice);
        }
    }

    public static void portalActivator(int choice){
        switch (choice){
            case 1:
                AuthManager.authenticateUser();
                break;
            case 2:
                AuthManager.authenticateUploader();
                break;
            case 3:
                AuthManager.authenticateAdmin();
                break;
            case 4:
                AuthManager.authenticateSpecifiedAccount(Database.account.VIP_USER);
                break;
            case 5:
                AuthManager.authenticateSpecifiedAccount(Database.account.SPECIAL_USER);
                break;
            case 6:
                AuthManager.authenticateSpecialAdmin();
                break;
            case 7:
                AuthManager.authenticateToSubscriptionPortal();
                break;
            case 8:
                Database.auxUser.exit();
                break;
        }
    }

    public static void main(String[] args){
        initFlow();
    }
}
