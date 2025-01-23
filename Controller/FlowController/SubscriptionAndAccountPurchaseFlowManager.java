package Controller.FlowController;

import Controller.AccountManager;
import Controller.GeneralProcessor;
import Model.Database;
import View.InputManager;
import View.ListPrinter;
import View.Printer;

public class SubscriptionAndAccountPurchaseFlowManager {
    public static void initFlow(){
        while(true){
            Printer.welcomeUser("User");
            Printer.portalWelcomePrinter("Subscription and Account Purchase");
            ListPrinter.choicePrinter(Database.purchaseAndSubscriptionChoice);
            int choice = InputManager.getChoiceInput(Database.purchaseAndSubscriptionChoice.size());
            switch(choice){
                case 0:
                    return;
                case 1:
                    purchaseAccount();
                    break;
                case 2:
                    getSubscription();
                    break;
                case 3:
                    return;
                case 4:
                    Database.auxUser.exit();
                    break;
            }
        }
    }

    public static void purchaseAccount(){
        ListPrinter.accountPlanChoicePrinter(Database.accountPlans);
        int choice = InputManager.getChoiceInput(Database.purchaseAndSubscriptionChoice.size());
        switch(choice){
            case 0:
                return;
            case 1:
                AccountManager.purchaseVIPUser();
                break;
            case 2:
                AccountManager.purchaseSpecialUser();
                break;
            case 3:
                GeneralProcessor.requestSpecialAdminAccount();
                break;
        }
    }

    public static void getSubscription(){
        ListPrinter.subscriptionPlanChoicePrinter(Database.subscriptionPlans);
        int choice = InputManager.getChoiceInput(Database.purchaseAndSubscriptionChoice.size());
        switch(choice){
            case 0:
                return;
            case 1:
                GeneralProcessor.getSubscription(Database.account.VIP_USER);
                break;
            case 2:
                GeneralProcessor.getSubscription(Database.account.SPECIAL_USER);
                break;
            case 3:
                GeneralProcessor.getSubscription(Database.account.SPECIAL_ADMIN);
                break;
        }
    }
}


