package Controller;

import Model.Actors.*;
import Model.DataModel.SubscriptionActivity;
import View.InputManager;
import View.ListPrinter;
import View.Printer;
import Model.Database;
// import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GeneralProcessor {

    public static void streamNow(int pos) {
        try {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(Database.movies.get(Converter.posToIdx(pos)).path);
            desktop.open(file);
        } catch (Exception e) {
            Printer.notStreamable();
        }
    }

    public static String getSpecialAdminUserName(){
        ListPrinter.listSpecialAdminUserName();
        int pos = InputManager.getChoiceInput(Database.specialAdmins.size());
        if(pos != 0){
            int index = Converter.posToIdx(pos);
            return Database.specialAdmins.get(index).name;
        }
        return null;
    }

    public static String getVIPUserName() {
        ListPrinter.listVIPUserName();
        int pos = InputManager.getChoiceInput(Database.vipUsers.size());
        if (pos != 0) {
            int index = Converter.posToIdx(pos);
            return Database.vipUsers.get(index).name;
        }
        return null;
    }


    public static void requestSpecialAdminAccount() {
        Printer.requestSpecialAdminPrint();
        String reason = InputManager.getReason();
        boolean isPaymentSuccessful = AuthManager.doPayment(Database.accountPlans.get(Database.account.SPECIAL_ADMIN.ordinal()).price);
        if (isPaymentSuccessful) {
            Database.specialAdminRequests.add(reason);
            Printer.requestSent();
        }
    }

    public static void getSubscription(Database.account accountType) {
        String userName = InputManager.getUserName();
        String password = InputManager.getPassword();
        boolean paymentSuccessful = false;
        Person user = AuthManager.getAccount(userName, password, accountType);
        if (user != null) {
            paymentSuccessful = AuthManager.doPayment(Database.subscriptionPlans.get(accountType.ordinal()).price);
            user.setActivated(paymentSuccessful);
            if (paymentSuccessful) {
                SubscriptionActivity subscriptionActivity = new SubscriptionActivity(accountType,user.name, LocalDateTime.now());
                Database.subscriptionActivities.add(subscriptionActivity);
                Printer.accountActivated(Database.accountTypes.get(accountType.ordinal()), userName);
            }
        }
    }

    public static void accountPlanUpdator(ArrayList<Database.AccountPlans> plans) {
        ListPrinter.accountPlanChoicePrinter(Database.accountPlans);
        int choice = InputManager.getChoiceInput(plans.size());
        if (choice == 0) return;

        int amt = -1;
        amt = InputManager.getAmount();
        if (amt == -1) return;

        plans.get(Converter.posToIdx(choice)).price = amt;
        if (amt == 0) plans.get(Converter.posToIdx(choice)).isFree = true;
        else plans.get(Converter.posToIdx(choice)).isFree = false;
        Printer.planUpdatedSuccessful();
    }

    public static void subscriptionPlanUpdator(ArrayList<Database.SubscriptionPlans> plans){
        ListPrinter.subscriptionPlanChoicePrinter(Database.subscriptionPlans);
        int choice = InputManager.getChoiceInput(plans.size());
        if (choice == 0) return;

        int amt = -1;
        amt = InputManager.getAmount();
        if (amt == -1) return;

        plans.get(Converter.posToIdx(choice)).price = amt;
        if (amt == 0) plans.get(Converter.posToIdx(choice)).isFree = true;
        else plans.get(Converter.posToIdx(choice)).isFree = false;
        Printer.planUpdatedSuccessful();
    }

    public static void accountStreamRestrictionManipulator(Person person){
        if(person instanceof User){
            System.out.println("1 - Restrict Streaming for user\n2 - Unrestrict Streaming for user");
            int choice = InputManager.getChoiceInput(2);
            if(choice == 0) return;
            if(choice == 1){
                Database.streamRestrictionForUser = true;
                Printer.restrictionMessage("Restricted");
            }
            else if(choice == 2){
                Database.streamRestrictionForUser = false;
                Printer.restrictionMessage("Un restricted");
            }
        }
        else {
            person.setStreamRestricted(true);
            System.out.println(person.name + " ( " + (person.isStreamRestricted ? "Restricted" : "Unrestricted") + " )");
            System.out.println("1 - Restrict\n2 - Unrestrict");
            int choice = InputManager.getChoiceInput(2);
            if(choice == 0) return;
            if(choice == 1){
                person.setStreamRestricted(true);
                Printer.restrictionMessage("Restricted");
            }
            else if(choice == 2){
                person.setStreamRestricted(false);
                Printer.restrictionMessage("Un restricted");
            }
        }


    }

    public static void streamRestrictionManipulator(Database.account accountType){
        if(accountType == Database.account.VIP_USER) {
            ArrayList<VIPUser> accountList = Database.vipUsers;
            ListPrinter.accountPrinter(accountList);
            int choice = InputManager.getChoiceInput(accountList.size());
            if(choice == 0) return;
            accountStreamRestrictionManipulator(accountList.get(Converter.posToIdx(choice)));
        }
        else if(accountType == Database.account.SPECIAL_USER) {
            ArrayList<SpecialUser> accountList = Database.specialUsers;
            ListPrinter.accountPrinter(accountList);
            int choice = InputManager.getChoiceInput(accountList.size());
            if(choice == 0) return;
            accountStreamRestrictionManipulator(accountList.get(Converter.posToIdx(choice)));
        }
        else if(accountType == Database.account.SPECIAL_ADMIN) {
            ArrayList<SpecialAdmin> accountList = Database.specialAdmins;
            ListPrinter.accountPrinter(accountList);
            int choice = InputManager.getChoiceInput(accountList.size());
            if(choice == 0) return;
            accountStreamRestrictionManipulator(accountList.get(Converter.posToIdx(choice)));
        }
        else if(accountType == Database.account.USER){
            accountStreamRestrictionManipulator(Database.simpleUser);
        }
    }

    public static void accountActivationManipulator(Person person){
        System.out.println(person.name + " ( " + (person.isActivated() ? "Activated" : "Deactivated") + " )");
        System.out.println("1 - Activate\n2 - Deactivate");
        int choice = InputManager.getChoiceInput(2);
        if(choice == 0) return;
        if(choice == 1){
            person.setActivated(true);
            Printer.activationMessage("Activation");
        }
        else if(choice == 2){
            person.setActivated(false);
            Printer.activationMessage("Deactivation");
        }
    }

    public static void activationManipulator(Database.account accountType){
        if(accountType == Database.account.VIP_USER) {
            ArrayList<VIPUser> accountList = Database.vipUsers;
            ListPrinter.accountPrinter(accountList);
            int choice = InputManager.getChoiceInput(accountList.size());
            if(choice == 0) return;
            accountActivationManipulator(accountList.get(Converter.posToIdx(choice)));
        }
        else if(accountType == Database.account.SPECIAL_USER) {
            ArrayList<SpecialUser> accountList = Database.specialUsers;
            ListPrinter.accountPrinter(accountList);
            int choice = InputManager.getChoiceInput(accountList.size());
            if(choice == 0) return;
            accountActivationManipulator(accountList.get(Converter.posToIdx(choice)));
        }
        else if(accountType == Database.account.SPECIAL_ADMIN) {
            ArrayList<SpecialAdmin> accountList = Database.specialAdmins;
            ListPrinter.accountPrinter(accountList);
            int choice = InputManager.getChoiceInput(accountList.size());
            if(choice == 0) return;
            accountActivationManipulator(accountList.get(Converter.posToIdx(choice)));
        }
    }

    public static void activityRegistry(){

    }

}
