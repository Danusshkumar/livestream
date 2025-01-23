import java.util.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

public class LiveStream {

    static int exit = 2;

    static List<String> additionalActorList = new ArrayList<String> ();

    static List<String> additionalActorListPwd = new ArrayList<String> ();

    static List<String> additionalActorNameList = new ArrayList<String> ();

    static int userLoginTimes = 0;

    static int uploaderLoginTimes = 0;

    static List<Integer> specUserLoginTimes = new ArrayList<Integer> ();


    static void generateActor(){
        int indexNumber = 4+additionalActorList.size();
        Scanner actorNameSc = new Scanner(System.in);
        System.out.print("Enter the name of new actor:");
        String actorName = actorNameSc.nextLine();
        System.out.print("Enter the password for new actor:");
        Scanner pwdSc1 = new Scanner(System.in);
        String pwd1 = pwdSc1.nextLine();
        System.out.print("Re-enter the password:");
        Scanner pwdSc2 = new Scanner(System.in);
        String pwd2 = pwdSc2.nextLine();
        if(pwd1.matches(pwd2)) {
            additionalActorListPwd.add(pwd1);
            additionalActorNameList.add(actorName);
            additionalActorList.add(indexNumber+" - "+actorName+" (special admin with reduced access)");
            specUserLoginTimes.add(0);
            System.out.println("new actor added successfully");
        }
        else {
            System.out.println("Password mismatch found! please try again");
        }

    }
    static void removeActor(){
        System.out.println("added actor's list:");
        for(int i = 0;i<additionalActorList.size();i++){
            System.out.println((i+1)+" - "+additionalActorNameList.get(i));
        }
        Scanner rmActSc = new Scanner(System.in);
        System.out.print("select the actor you want to remove:");
        String rmAct = rmActSc.nextLine();
        try {
            int rmActInt = Integer.parseInt(rmAct);
            if(Integer.parseInt(rmAct) <= additionalActorList.size()) {
                System.out.println(additionalActorNameList.get(rmActInt-1)+" is removed successfully from actor's list");
                additionalActorList.remove(rmActInt - 1);
                additionalActorNameList.remove(rmActInt - 1);
                additionalActorListPwd.remove(rmActInt - 1);
            }
            else{
                System.out.println("Invalid actor selected");
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid format");
        }

    }
    static void additionalActorPwdChange(){
        System.out.println("added actor's list:");
        for(int i = 0;i<additionalActorList.size();i++){
            System.out.println((i+1) +" - "+additionalActorNameList.get(i));
        }
        Scanner pwdChSc1 = new Scanner(System.in);
        Scanner pwdChSc2 = new Scanner(System.in);
        Scanner actorSc = new Scanner(System.in);
        System.out.print("select the actor whose password you want to change:");
        try {
            int actor =actorSc.nextInt();
            if (actor <= additionalActorList.size()) {
                System.out.print("Enter the new password for " + additionalActorNameList.get(actor - 1) + ":");
                String pwdCh1 = pwdChSc1.nextLine();
                System.out.print("Re-enter the password:");
                String pwdCh2 = pwdChSc2.nextLine();
                if (pwdCh1.matches(pwdCh2)) {
                    additionalActorListPwd.set(actor - 1, pwdCh1);
                    System.out.println("Password changed successfully");
                } else {
                    System.out.println("Password mismatch found! please try again");
                }
            } else {
                System.out.println("Invalid actor selected");
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid format");
        }

    }
    static void loginDetails(){
        System.out.println("\n --------------- login details ----------------");
        System.out.println("user - "+userLoginTimes+" times");
        System.out.println("uploader - "+uploaderLoginTimes+" times");
        for(int i = 0;i<additionalActorNameList.size();i++){
            System.out.println(additionalActorNameList.get(i)+" - "+specUserLoginTimes.get(i)+" times");
        }
    }

    static Uploader uploader = new DataBase();
    static User user = new DataBase();
    static Admin admin = new DataBase();
    static void actorShow(){
        System.out.println("\n\n     ---------------------------------      ");
        System.out.println("Welcome to Amazon Prime Video");
        System.out.println("I hacked this software and now it's mine");
        System.out.println("Don't try to enter into uploader or administrator's page");
        System.out.println("\nAdmin message: " + DataBase.adminMessage + "\n");
        System.out.println("1 - user");
        System.out.println("2 - uploader");
        System.out.println("3 - admin");
        for (String s : additionalActorList) {
            System.out.println(s);
        }
        System.out.println(4+additionalActorList.size()+" - exit");
        System.out.print("Select your actor:");
    }
    static void myUserFunc(){
        while(true) {
            user.userShow();
            Scanner choiceSc = new Scanner(System.in);
            try {
                var choice = choiceSc.nextInt();
                if (choice == 1) {
                     
                    System.out.println("Hey, viewing content list is restricted for user, view the content one by one");
                } else if (choice == 2) {
                    uploader.viewSpec();
                } else if(choice == 3){
                    uploader.openSpec("user",DataBase.isStreamingAllowedForUser);
                     
                } else if (choice == 4) {
                     
                    break;
                } else if (choice == 5) {
                    exit();
                    break;
                } else {
                     
                    System.out.println("Your option is wrong");
                }
            } catch (NumberFormatException e) {

                System.out.println("Invalid format");
            }
        }
    }
    static void myUploaderFunc(){
        Scanner pwd = new Scanner(System.in);
        System.out.print("Enter your password:");
        String password = pwd.nextLine();
        if(password.matches(DataBase.adminPwd)) {
            while (true) {
                uploader.uploaderShow();
                Scanner choiceSc = new Scanner(System.in);
                try {
                    var choice = choiceSc.nextInt();
                    if (choice == 1) {
                        uploader.add("Uploader");
                    } else if (choice == 2) {
                        uploader.deleteSpec("Uploader");
                    } else if (choice == 3) {
                         
                        break;
                    } else if (choice == 4) {
                        exit();
                        break;
                    } else {
                        System.out.println("  Your option is wrong");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                }
            }
        }
        else{
            System.out.println("No one is allowed to access the uploader's page except me");
        }
    }

    static void myAdminFunc(){
        Scanner pwd = new Scanner(System.in);
        System.out.print("Enter your password:");
        String password = pwd.nextLine();
        if(password.matches(DataBase.adminPwd)) {
            while (true) {
                admin.adminShow();
                Scanner choiceSc = new Scanner(System.in);
                try {
                    int choice = choiceSc.nextInt();
                    if (choice == 1) {
                        uploader.add("Admin");
                    } else if (choice == 2) {
                        uploader.deleteSpec("Admin");
                    } else if (choice == 3) {
                        uploader.viewAll();
                    } else if (choice == 4) {
                        uploader.viewSpec();
                    } else if (choice == 5) {
                        uploader.editSpec("Admin");
                    } else if (choice == 6) {
                        uploader.deleteAll();
                    } else if (choice == 7) {
                        uploader.hackAll();
                    } else if (choice == 8) {
                        uploader.setDefault();
                    } else if (choice == 9) {
                        uploader.emptyDb();
                    } else if (choice == 10) {
                        uploader.changePwd();
                    } else if (choice == 11) {
                        generateActor();
                    } else if (choice == 12) {
                        additionalActorPwdChange();
                    } else if (choice == 13) {
                        removeActor();
                    } else if (choice == 14) {
                        loginDetails();
                    } else if (choice == 15) {
                        uploader.contentManipulation();
                    } else if(choice == 16) {
                        uploader.showOnlyAvailableFile();
                        uploader.openSpec("admin",true);
                    } else if(choice == 17){
                        uploader.restrictContentStreaming();
                    } else if(choice == 18){
                        uploader.unRestrictContentStreaming();
                    } else if(choice == 19){
                        uploader.changeAdminMessage();
                    } else if (choice == 20) {
                         
                        break;
                    } else if (choice == 21) {
                        exit();
                        break;
                    } else {
                        System.out.println("  Your option is wrong");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                }
            }
        }
        else{
            System.out.println("I am the only admin, you're not allowed to enter");
        }
    }

    static void mySpecAdminFunc(int adminIndex){
        Scanner pwd = new Scanner(System.in);
        System.out.print("Enter your password:");
        String password = pwd.nextLine();
        if(password.matches(additionalActorListPwd.get(adminIndex))) {
            while (true) {
                uploader.adminSpecShow(additionalActorNameList.get(adminIndex));
                Scanner choiceSc = new Scanner(System.in);
                try {
                    var choice = choiceSc.nextInt();
                    if (choice == 1) {
                        uploader.add(additionalActorNameList.get(adminIndex));
                    } else if (choice == 2) {
                        uploader.deleteSpec(additionalActorNameList.get(adminIndex));
                    } else if (choice == 3) {
                        uploader.viewAll();
                    } else if (choice == 4) {
                        uploader.viewSpec();
                    } else if (choice == 5) {
                        uploader.editSpec(additionalActorNameList.get(adminIndex));
                    } else if (choice == 6) {
                        uploader.openSpec("special admin",DataBase.isStreamingAllowedForSpecialAdmin);
                    } else if (choice == 7) {
                         
                        break;
                    } else if (choice == 8) {
                        exit();
                        break;
                    } else {
                        System.out.println("  Your option is wrong");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format");
                }
            }
        }
        else {
            System.out.println("Your password is wrong.If you forget the password, please contact the administrator");
        }
    }

    static void exit(){
        exit = 1;
        System.out.println("\nThank you :)");
        System.out.println("A hacker's programme");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    static void myMainFunc(){
        uploader.defaultAdd();
        while(exit == 2){

            actorShow();
            Scanner actor = new Scanner(System.in);

            try {
                int myActor = actor.nextInt();

                 

                if (myActor == 1) {
                    myUserFunc();
                    userLoginTimes++;
                } else if (myActor == 2) {
                    myUploaderFunc();
                    uploaderLoginTimes++;
                } else if (myActor == 3) {
                    myAdminFunc();
                } else {
                    for (int i = 0; i < additionalActorList.size(); i++) {
                        if (myActor == 4 + i) {
                            mySpecAdminFunc(i);
                            int value = specUserLoginTimes.get(i);
                            specUserLoginTimes.set(i,++value);
                        }
                    }
                    if(myActor == 4+additionalActorList.size()){
                        exit();
                    }
                    else if (myActor > 3 + additionalActorList.size()) {
                        System.out.println("Your selection of page is wrong");
                    }
                }
            }
            catch(InputMismatchException e){
                 
                System.out.println("Invalid format");
            }
        }
        
    }
    public static void main(String[] args) {
        myMainFunc();
    }
}

interface User{
    void userShow();
}
interface Uploader{

    void defaultAdd();
    void viewSpec();
    void uploaderShow();
    void adminSpecShow(String name);
    void add(String person);
    void deleteSpec(String person);
    void editSpec(String person);
    void viewAll();
    void deleteAll();
    void hackAll();
    void setDefault();
    void emptyDb();
    void changePwd();
    void contentManipulation();
    void openSpec(String actor, boolean isAllowed);
    void showOnlyAvailableFile();
    void restrictContentStreaming();
    void unRestrictContentStreaming();
    void changeAdminMessage();
}

interface Admin extends Uploader{
    void adminShow();
    void editSpec(String person);
}

class DataBase implements User,Uploader,Admin{

    static boolean isStreamingAllowedForUser = true;
    static boolean isStreamingAllowedForSpecialAdmin = true;

    static String adminMessage = "default message";

    static String adminPwd = "adminPwd";
    List<Integer> pos = new ArrayList<Integer>(
        Arrays.asList()
    );
    List<String> name = new ArrayList<String>(
        Arrays.asList(
            "Anbe Sivam",
        "Asuran",
        "Bagheera",
        "Cobra",
        "Dasavathaaram",
        "Dear Comrade",
        "Dejavu",
        "Diary",
        "Geetha Govindam",
        "Hey Ram",
        "Karnan",
        "KGF Chapter 1",
        "KGF Chapter 2",
        "Love Today",
        "Lucy",
        "Madarasapattinam",
        "Naane Varuven",
        "Pathu Thala",
        "Ponniyin Selvan",
        "Pudhupettai",
        "Rocketry",
        "Sita Ramam",
        "Suzhal - The Vortex",
        "Tamizh Padam 2",
        "Thiruchitrambalam",
        "Uttama Villain",
        "Vaathi",
        "Vendhu Thanindhathu Kaadu",
        "Vettaiyaadu Vilaiyaadu 2006",
        "Viduthalai Part - 1",
        "Vikram",
        "Virumandi",
        "Visaranai",
        "Maamannan"
        )
    );

    List<String> fileLinks = new ArrayList<String>(
        Arrays.asList(
            "E:\\Anbe Sivam (2003).ts",
            "E:\\Asuran.ts",
            "E:\\Bagheera.ts",
            "E:\\Cobra.mp4",
            "E:\\Dasavathaaram.mkv",
            "E:\\Dear Comrade.mkv",
            "E:\\Dejavu.mp4",
            "E:\\Diary.mp4",
            "E:\\Geetha Govindam.mkv",
            "E:\\Hey Ram.mkv",
            "E:\\Karnan (2021) Tamil.mkv",
            "E:\\KGF Chapter 1.mkv",
            "E:\\KGF Chapter 2.mkv",
            "E:\\Love Today.ts",
            "E:\\Lucy.mkv",
            "E:\\Madrasapattinam.mkv",
            "E:\\Naane Varuven.ts",
            "E:\\Pathu Thala.mp4",
            "E:\\Ponniyin selvan.mkv",
            "E:\\Pudhupettai.mkv",
            "E:\\Rocketry.ts",
            "E:\\Sita Ramam.mp4",
            "E:\\Suzhal - THE VORTEX.mp4",
            "E:\\Tamizh Padam 2.mkv",
            "E:\\Thiruchitrambalam (2022).mkv",
            "E:\\Uttama Villain.mkv",
            "E:\\Vaathi.mkv",
            "E:\\Vendhu Thanindhathu Kaadu.mp4",
            "E:\\Vettaiyaadu Vilaiyaadu 2006.mkv",
            "E:\\Viduthalai Part - 1.mp4",
            "E:\\Vikram.mp4",
            "E:\\Virumandi.mkv",
            "E:\\Visaranai (2016).ts"
        )
    );

    List<String> manipulationRegistry = new ArrayList<String>();

    public void defaultAdd(){

        for(int i = 1;i<=34;i++)
            pos.add(i);

    }

    public void userShow(){
        System.out.println("\nYou're now in User portal");
        System.out.println("Hey, viewing content list is restricted for user, view the content one by one");
        System.out.println("select 1 to view content list");
        System.out.println("select 2 to view content by position number");
        System.out.println("select 3 to stream the content");
        System.out.println("select 4 to exit user portal");
        System.out.println("select 5 to exit the application");
        System.out.print("Select your option:");
    }
    public void uploaderShow(){
        System.out.println("\nYou're now in Uploader portal");
        System.out.println("select 1 to add content");
        System.out.println("select 2 to delete content by position number");
        System.out.println("select 3 to exit uploader's portal");
        System.out.println("select 4 to exit the application");
        System.out.print("Select your option:");
    }
    public void adminShow(){
        System.out.println("\nWelcome Admin");
        System.out.println("You're now in Admin portal");
        System.out.println("select 1 to add content");
        System.out.println("select 2 to delete content by position number");
        System.out.println("select 3 to view content list");
        System.out.println("select 4 to view content by position number");
        System.out.println("select 5 to edit content by position number");
        System.out.println("select 6 to delete all the content");
        System.out.println("select 7 to make all content hacked");
        System.out.println("select 8 to set all default content");
        System.out.println("select 9 to empty the whole database");
        System.out.println("select 10 to change the admin and uploader password");
        System.out.println("select 11 to add new admin with reduced access");
        System.out.println("select 12 to change password of added new admins");
        System.out.println("select 13 to remove the admin with reduced access");
        System.out.println("select 14 to view login activities (excluding admin's activity)");
        System.out.println("select 15 to view content manipulation (excluding admin's activity)");
        System.out.println("select 16 to stream the content");
        System.out.println("select 17 to restrict the content streaming");
        System.out.println("select 18 to unrestrict the content streaming");
        System.out.println("select 19 to change admin message");
        System.out.println("select 20 to exit admin portal");
        System.out.println("select 21 to exit the application");
        System.out.print("Select your option:");
    }
    public void adminSpecShow(String name){
        System.out.println("\nYou're now in "+name+" portal");
        System.out.println("select 1 to add content");
        System.out.println("select 2 to delete content by position number");
        System.out.println("select 3 to view content list");
        System.out.println("select 4 to view content by position number");
        System.out.println("select 5 to edit content by position number");
        System.out.println("select 6 to stream the content");
        System.out.println("select 7 to exit this portal");
        System.out.println("select 8 to exit the application");
        System.out.print("Select your option:");
    }
    public void viewAll(){
        System.out.println("Content currently available:");
        for(int i = 0;i<pos.size();i++){
            System.out.println(pos.get(i) +" - "+ name.get(i));
        }
    }
    public void add(String person){
        Scanner contentSc = new Scanner(System.in);
        System.out.print("Your content:");
        String content = contentSc.nextLine();
        this.pos.add(pos.size()+1);
        this.name.add(content);
        if(!Objects.equals(person, "Admin")){
            manipulationRegistry.add(person+" added "+content+" into the database");
        }
        System.out.println(content +" added successfully");
    }
    public void viewSpec(){
        Scanner position = new Scanner(System.in);
        System.out.print("Enter your position number:");
        String posNum = position.nextLine();
         
        try {
            try {
                System.out.println(pos.get(Integer.parseInt(posNum) - 1) + " - " + name.get(Integer.parseInt(posNum) - 1));
            }
            catch(NumberFormatException e){
                System.out.println("Invalid format");
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("  You selected the position number that is not in list");
        }
    }

    public void streamPreparation(int positionNumber){
            System.out.println("\nVerifying Subscription ...");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("\nAuthenticated with Open Source General Subscription ...");

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

            System.out.println("\nRequesting for movie " + name.get(positionNumber - 1) + " ...");

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

            System.out.println("\n" + name.get(positionNumber - 1) + " is Streaming now ...");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
    }

    
    public void unRestrictContentStreaming(){
        System.out.println("Unrestrict content streaming for :");
        System.out.println("1. Users");
        System.out.println("2. Special admins");
        Scanner sc = new Scanner(System.in);
        String restrictActorString = sc.nextLine();
        try{
            int restrictActor = Integer.parseInt(restrictActorString);
            if(restrictActor == 1){
                isStreamingAllowedForUser = true;
            }
            else if(restrictActor == 2){
                isStreamingAllowedForSpecialAdmin = true;
            }

            System.out.println("Content unrestricted successfully for " + ((restrictActor == 1 ) ? "user" : "special admin"));
        }
        catch(Exception e){
            System.out.println("Invalid format");
        }
    }


    public void restrictContentStreaming(){
        System.out.println("Restrict content streaming for :");
        System.out.println("1. Users");
        System.out.println("2. Special admins");
        Scanner sc = new Scanner(System.in);
        String restrictActorString = sc.nextLine();
        try{
            int restrictActor = Integer.parseInt(restrictActorString);
            if(restrictActor == 1){
                isStreamingAllowedForUser = false;
            }
            else if(restrictActor == 2){
                isStreamingAllowedForSpecialAdmin = false;
            }

            System.out.println("Content restricted successfully for " + ((restrictActor == 1 ) ? "user" : "special admin"));
        }
        catch(Exception e){
            System.out.println("Invalid format");
        }
    }

    public void openSpec(String actor, boolean isAllowed){

        if(actor == "user")
            if(isAllowed == false){
                System.out.println("Streaming content is temporarily restricted for users");
                return;
            }
        if(actor == "special admin")
            if(isAllowed == false){
                System.out.println("Streaming content is temporarily restricted for special admins");
                return;
            }


        Scanner position = new Scanner(System.in);
        System.out.print("Enter the position number of the content:");
        String posNum = position.nextLine();
            try {
            try {
                int positionNumber = Integer.parseInt(posNum);
                if(positionNumber >=1 && positionNumber <= 34){
                    
                    try {
                        // Open the file with the default media player

                        streamPreparation(positionNumber);

                        Desktop desktop = Desktop.getDesktop();
                        File file = new File(fileLinks.get(positionNumber - 1));
                        desktop.open(file);


                    } catch (IOException e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Movie not available");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid format");
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("You selected the position number that is not in list");
        }

            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.print("\nPress enter to continue ...");
            position.nextLine();

    }

    public void changeAdminMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Admin message: ");
        adminMessage = sc.nextLine();
        System.out.println("Admin message changed successfully");
    }

    public void showOnlyAvailableFile(){
        System.out.println("Content currently available:");
        for(int i = 0;i<34;i++){
            System.out.println(pos.get(i) +" - "+ name.get(i));
        }
    }
    public void deleteSpec(String person){
        System.out.print("Enter the position:");
        Scanner position = new Scanner(System.in);
        String posNum = position.nextLine();
        try {
            try {
                if(!Objects.equals(person, "Admin")){
                    manipulationRegistry.add(person+" deleted "+name.get(Integer.parseInt(posNum) - 1)+" from the database");
                }
                System.out.println(name.get(Integer.parseInt(posNum) - 1) + " deleted successfully");
                name.set(Integer.parseInt(posNum) - 1, "this content is unavailable");
            }
            catch(NumberFormatException e){
                System.out.println("Invalid format");
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("  You selected the position number that is not in list");
        }
    }
    public void editSpec(String person){
        Scanner position = new Scanner(System.in);
        Scanner contentSc = new Scanner(System.in);
        System.out.print("Enter the position:");
        String posNum = position.nextLine();
        System.out.print("Enter the content:");
        String content = contentSc.nextLine();
        try {
            try {
                if(!Objects.equals(person, "Admin")){
                    manipulationRegistry.add(person+" edited the content "+name.get(Integer.parseInt(posNum))+" into "+content);
                }
                name.set(Integer.parseInt(posNum) - 1, content);
                System.out.println("content edited successfully");
            }catch(NumberFormatException e){
                System.out.println("Invalid format");
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("  You selected the position number that is not in list");
        }
    }
    public void deleteAll(){
        name.replaceAll(ignored -> "this content is unavailable");
        System.out.println("All contents deleted successfully");
    }
    public void hackAll(){
        name.replaceAll(ignored -> "this content was hacked");
        System.out.println("All contents hacked successfully");
    }

    @Override
    public void setDefault() {
        pos.clear();
        name.clear();
        defaultAdd();
        System.out.println("Database now set to default successfully");
    }

    @Override
    public void emptyDb() {
        pos.clear();
        name.clear();
        System.out.println("Database deleted completely");
    }
    public void changePwd(){
        Scanner pwd1 = new Scanner(System.in);
        Scanner pwd2 = new Scanner(System.in);
        System.out.print("Enter your new password:");
        String adminPwd1 = pwd1.nextLine();
        System.out.print("Re-enter your password:");
        String adminPwd2 = pwd2.nextLine();
        if(adminPwd1.matches(adminPwd2)){
            adminPwd = adminPwd2;
            System.out.println("Your password was changed successfully");
        }
        else {
            System.out.println("Password mismatch found! please try again");
        }

    }
    public void contentManipulation(){
        System.out.println("\n ------------ Content manipulation details -----------");
        for (String s : manipulationRegistry) {
            System.out.println(s);
        }
        System.out.println();
    }
}


//https://www.youtube.com/watch?v=5o3fMLPY7qY











