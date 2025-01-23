package View;
import Controller.Converter;
import Model.Actors.Person;
import Model.DataModel.ActivityHistory;
import Model.DataModel.LoginHistory;
import Model.DataModel.SubscriptionActivity;
import Model.Database;
import java.util.ArrayList;

public class ListPrinter {
    public static void listPortal(){
        for(int i = 0;i< Database.portal.size();i++){
            System.out.println(Converter.idxToPos(i) + " - " + Database.portal.get(i));
        }
        System.out.println();
    }

    public static void requestPrinter(ArrayList<String> requests){
        System.out.println("Special admin requests: ");
        for(int i = 0;i<requests.size();i++){
            System.out.println(Converter.idxToPos(i) + ". " + requests.get(i));
        }
    }

    public static void choicePrinter(ArrayList<String> choice){
        for(int i = 0;i<choice.size();i++){
            System.out.println(choice.get(i));
        }
    }

    public static void accountTypePrinter(ArrayList<String> accountTypes){
        System.out.println("Select the account: ");
        for(int i = 0;i<accountTypes.size();i++){
            System.out.println(Converter.idxToPos(i) + " - " + accountTypes.get(i));
        }
    }

    public static void listContent(){
        System.out.println("Content currently available:");
        for(int i = 0; i< Database.movies.size(); i++) {
            System.out.println(Database.movies.get(i));
        }
    }

    public static void accountPrinter(ArrayList accounts){
        System.out.println("List of accounts: \n");
        for(int i = 0;i<accounts.size();i++){
            System.out.println(Converter.idxToPos(i) + " - " + accounts.get(i).toString());
        }
    }

    public static void accountPrinterNameFormat(ArrayList<Person> accounts){
        System.out.println("List of accounts:\n");
        for(int i = 0;i<accounts.size();i++){
            System.out.println(Converter.idxToPos(i) + " - " + accounts.get(i).toStringNameFormat());
        }
    }

    public static void listVIPUserName(){
        for(int i = 0;i<Database.vipUsers.size();i++){
            System.out.println(Converter.idxToPos(i) + " - " + Database.vipUsers.get(i).name + "( " + (Database.vipUsers.get(i).isActivated() ? "Activated" : "Deactivated") +  " )");
        }
    }

    public static void listSpecialAdminUserName(){
        for(int i = 0;i<Database.specialAdmins.size();i++){
            System.out.println(Converter.idxToPos(i) + " - " + Database.specialAdmins.get(i).name + "( " + (Database.specialAdmins.get(i).isActivated() ? "Activated" : "Deactivated") +  " )");
        }
    }

    public static void listLoginHistories(){
        System.out.println("Login Details:");
        System.out.println("--------------");
        for(int i = 0;i<Database.loginHistories.size();i++){
            LoginHistory loginHistory = Database.loginHistories.get(i);
            System.out.println("Account name: " + loginHistory.accountName);
            System.out.println("Login time: " + loginHistory.loginTime.format(Database.formatter));
            System.out.println("Logout time: " + ((loginHistory.logoutTime != null) ? loginHistory.logoutTime.format(Database.formatter) : "nil"));
            System.out.println();
        }
    }

    public static void listActivityHistory(){
        System.out.println("Activity details:");
        System.out.println("-----------------");
        for(int i = 0;i<Database.activityHistories.size();i++){
            ActivityHistory activityHistory = Database.activityHistories.get(i);
            String activityName = Converter.activityTypeToString(activityHistory.activityType);
            System.out.println("Activity type: " + activityName);
            System.out.println("Account holder: " + activityHistory.accountName);
            System.out.println("Affected movie 1: " + activityHistory.affectedMovie1);
            System.out.println("Affected movie 2: " + activityHistory.affectedMovie2);
            System.out.println("Description: " + activityHistory.description);
            System.out.println();
        }
    }

    public static void listSubscriptionActivity(){
        System.out.println("Subscription Activities:");
        System.out.println("------------------------");
        for(int i = 0;i<Database.subscriptionActivities.size();i++){
            SubscriptionActivity subscriptionActivity = Database.subscriptionActivities.get(i);
            System.out.println();
            System.out.println("Account type: " + Converter.accountTypeToString(subscriptionActivity.accountType));
            System.out.println("Account holder: " + subscriptionActivity.accountHolder);
            System.out.println("Subscribed time: " + subscriptionActivity.time.format(Database.formatter));
            System.out.println();
        }
    }

    public static void listUploaderAndAdmin(){
        System.out.println("1 - Uploader");
        System.out.println("2 - Admin");
    }

    public static void accountPlanChoicePrinter(ArrayList<Database.AccountPlans> choice){
        for(int i = 0;i<choice.size();i++){
            System.out.print(Converter.idxToPos(i) + " - " + choice.get(i).accountType + " - ");
            System.out.println(choice.get(i).isFree ? "Free" : ("$ " + choice.get(i).price));
        }

    }

    public static void subscriptionPlanChoicePrinter(ArrayList<Database.SubscriptionPlans> choice){
        for(int i = 0;i<choice.size();i++){
            System.out.print(Converter.idxToPos(i) + " - " + choice.get(i).accountType + " - ");
            System.out.println(choice.get(i).isFree ? "Free" : ("$ " + choice.get(i).price));
        }
        System.out.println("All subscription plans will be valid for one week");
    }
    
    public static void accountRelatedPlans(){
        System.out.println("1 - Account creation");
        System.out.println("2 - Subscription");
    }

}
