package View;
import Model.Actors.*;
import Model.Database;
import Controller.Converter;
import java.util.concurrent.TimeUnit;

public class Printer {

    public static void welcomePrinter(){
        System.out.println("\n\n     ---------------------------------      ");
        System.out.println("Welcome to LiveStream Version 1.0.2");
        System.out.println("Software Licence: All right reserved @ LiveStream@Admin");
        System.out.println("Uploader and Admin portal are prohibited for general usage");
        System.out.println("\nAdmin message: " + Database.adminMessage + "\n");
    }

    public static void portalWelcomePrinter(String portalName){
        System.out.println("You're in " + portalName + " portal");
    }

    public static void welcomeUser(String name){
        System.out.println("\nWelcome " + name);
    }

    public static void enterMovieName(){
        System.out.print("Enter the movie name: ");
    }


    public static void enterStreamableOrNot(){
        System.out.print("Enter whether the movie is streamable or not(Y\\N):");
    }

    public static void streamRestricted(){
        System.out.println("Streaming content is temporarily restricted for this account !!!");
    }

    public static void getMessage(){
        System.out.print("Enter your message: ");
    }

    public static void activationMessage(String activation){
        System.out.println(activation + " successful!!!");
    }

    public static void restrictionMessage(String restriction){
        System.out.println(restriction + " successful!!!");
    }

    public static void messageUpdateSuccessful(){
        System.out.println("Message updated successfully");
    }

    public static void enterPath(){
        System.out.print("Enter the movie path:");
    }

    public static void enterUserName(){
        System.out.print("Enter your Username: ");
    }
    public static void enterPassword(){
        System.out.print("Enter your password: ");
    }

    public static void enterConfirmPassword(){
        System.out.print("Re-enter your password: ");
    }

    public static void passwordUpdated(){
        System.out.println("Password updated successfully");
    }

    public static void paymentUnSuccessful(){
        System.out.println("Payment unsuccessful due to insufficient or extra amount. Try again");
    }

    public static void paymentSuccessful(){
        System.out.println("Payment successful !!!");
    }

    public static void accountCreatedSuccessfully(String userName,String password, String accountType){
        System.out.println(accountType + " account created successfully with the user name '"
                + userName + "' and password '" + password + "' !!!");
    }

    public static void accountActivated(String accountType, String userName){
        System.out.println("Subscription successful !!!\nYour " + accountType + " account with user name '" + userName + "' is now activated.");
    }
    public static void requestSent(){
        System.out.println("Your request for special admin account creation sent successfully. If verified, your account will be indexed");
    }

    public static void requestSpecialAdminPrint(){
        System.out.println(
                "Inorder to get special admin account, you have to write and " +
                        "send out a mail for your special admin account claim. " +
                        "We will charge $" + Database.accountPlans.get(2).price + " for your special admin request. " +
                        "Also note that, your desired user name and password should be included in your mail.");
    }

    public static void specialAdminRemoved(){
        System.out.println("Special admin get removed successfully !!!");
    }

    public static void specialAdminEdited(){
        System.out.println("Special admin edited successfully !!!");
    }

    public static void printGetReason(){
        System.out.print("Enter your mail and enter to proceed further: ");
    }

    public static void enterAmount(){
        System.out.print("Enter your amount: ");
    }

    public static void passwordMismatch(){
        System.out.println("Password mismatch found. Try again");
    }

    public static void userNameAlreadyAvailable(){
        System.out.println("user name is already available. Try again with new username");
    }

    public static void allContentHacked(){
        System.out.println("All content are hacked successfully");
    }

    public static void allContentDeleted(){
        System.out.println("All content are deleted successfully");
    }

    public static void allContentSetToDefault(){
        System.out.println("All content are now set to default successfully");
    }

    public static void contentListRestricted(){
        System.out.println("Viewing content list is restricted for users.\n" +
                "You are advised to view content one by one using position number feature.");
    }

    public static void printInvalidInputs(){
        System.out.println("Some of your input seems to be invalid. Try again");
    }

    public static void printNotInRange(){
        System.out.println("Your input is not in specified range. Try again");
    }

    public static void inputFormatException(){
        System.out.println("Your input is not in specified format. Try again");
    }

    public static void authFailed(){
        System.out.println("Authentication failed ! Your username or password is wrong. Try again");
    }

    public static void deactivatedAccount(){
        System.out.println("Your account is not yet activated. Kindly activate your account in Subscriptions and Account Purchase portal to continue. ");
    }

    public static void movieAddSuccessful(){
        System.out.println("Movie added successfully !!!");
    }

    public static void movieRemovedSuccessful(){
        System.out.println("Movie removed successfully !!!");
    }

    public static void movieEditSuccessful(){
        System.out.println("Movie edited successfully !!!");
    }

    public static void enterChoice(){
        System.out.print("Enter your choice:");
    }

    public static void enterItem(){
        System.out.print("Enter the item: ");
    }

    public static void notStreamable(){
        System.out.println("File got corrupted or it'll be not streamable");
    }

    public static void planUpdatedSuccessful(){
        System.out.println("Plan updated successful");
    }

    public static void printStreamingPreparation(int pos){
        System.out.println("\nVerifying Subscription ...");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String subscriptionPlan = null;
        Person p = Database.currentUser;
        if(p instanceof User)
            subscriptionPlan = "Open Source General";
        else if(p instanceof VIPUser)
            subscriptionPlan = "VIP User";
        else if(p instanceof SpecialUser)
            subscriptionPlan = "Special User";
        else if(p instanceof SpecialAdmin)
            subscriptionPlan = "Special Admin";

        System.out.println("\nAuthenticated with " + subscriptionPlan + " Subscription ...");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nPreparing for Streaming ...");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nRequesting for movie " + Database.movies.get(Converter.posToIdx(pos)) + " ...");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nStreaming process initiated ...");

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n" + Database.movies.get(Converter.posToIdx(pos)) + " is Streaming now ...");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void exitNote(){
        System.out.println("Thank you :)");
        System.out.println("A Hacker's programme");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
