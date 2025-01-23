package Controller;

import Model.DataModel.Movie;
import Model.Database;

// import javax.xml.crypto.Data;
import java.util.*;

public class InitialisationManager {

    static List<String> movieNames = new ArrayList<>(Arrays.asList("Anbe Sivam", "Asuran", "Bagheera", "Cobra",
            "Dasavathaaram", "Dear Comrade", "Dejavu", "Diary", "Geetha Govindam", "Hey Ram", "Karnan",
            "KGF Chapter 1", "KGF Chapter 2", "Love Today", "Lucy", "Madarasapattinam", "Naane Varuven",
            "Pathu Thala", "Ponniyin Selvan", "Pudhupettai", "Rocketry", "Sita Ramam", "Suzhal - The Vortex",
            "Tamizh Padam 2", "Thiruchitrambalam", "Uttama Villain", "Vaathi", "Vendhu Thanindhathu Kaadu",
            "Vettaiyaadu Vilaiyaadu 2006", "Viduthalai Part - 1", "Vikram", "Virumandi", "Visaranai",
            "Maamannan"));
    static List<String> fileLinks = new ArrayList<>(Arrays.asList("E:\\Anbe Sivam (2003).ts", "E:\\Asuran.ts",
            "E:\\Bagheera.ts", "E:\\Cobra.mp4", "E:\\Dasavathaaram.mkv", "E:\\Dear Comrade.mkv",
            "E:\\Dejavu.mp4", "E:\\Diary.mp4", "E:\\Geetha Govindam.mkv", "E:\\Hey Ram.mkv",
            "E:\\Karnan (2021) Tamil.mkv", "E:\\KGF Chapter 1.mkv", "E:\\KGF Chapter 2.mkv",
            "E:\\Love Today.ts", "E:\\Lucy.mkv", "E:\\Madrasapattinam.mkv", "E:\\Naane Varuven.ts",
            "E:\\Pathu Thala.mp4", "E:\\Ponniyin selvan.mkv", "E:\\Pudhupettai.mkv", "E:\\Rocketry.ts",
            "E:\\Sita Ramam.mp4", "E:\\Suzhal - THE VORTEX.mp4", "E:\\Tamizh Padam 2.mkv", "" +
                    "E:\\Thiruchitrambalam (2022).mkv", "E:\\Uttama Villain.mkv", "E:\\Vaathi.mkv",
            "E:\\Vendhu Thanindhathu Kaadu.mp4", "E:\\Vettaiyaadu Vilaiyaadu 2006.mkv",
            "E:\\Viduthalai Part - 1.mp4", "E:\\Vikram.mp4", "E:\\Virumandi.mkv"));

    static public void initDB() {
        //34
        for (int i = 0; i < movieNames.size(); i++) {
            Database.movies.add(new Movie(i + 1, movieNames.get(i), true, fileLinks.get(i)));
        }
        Database.movieCount = Database.movies.size();
        initPortal();
        initChoices();
    }

    public static void initChoices() {

        Database.userChoice = new ArrayList<>(Arrays.asList(
                "select 1 to view content list",
                "select 2 to view content by position number",
                "select 3 to stream the content",
                "select 4 to exit user portal",
                "select 5 to exit the application"
        ));

        Database.uploaderChoice = new ArrayList<>(Arrays.asList(
                "select 1 to view content list",
                "select 2 to view content by position number",
                "select 3 to stream the content",
                "select 4 to add the content",
                "select 5 to delete the content",
                "select 6 to exit user portal",
                "select 7 to exit the application"
        ));

        Database.purchaseAndSubscriptionChoice = new ArrayList<>(Arrays.asList(
                "select 1 to purchase account",
                "select 2 to get subscription for account",
                "select 3 to exit purchase and subscription portal",
                "select 4 to exit the application"
        ));

        Database.specialUserChoice = new ArrayList<>(Arrays.asList(
                "select 1 to stream the content",
                "select 2 to exit the VIP User/Special User portal",
                "select 3 to exit the application"
        ));

        Database.specialAdminChoice = new ArrayList<>(Arrays.asList(
                "select 1 to view content list",
                "select 2 to view content by position number",
                "select 3 to stream the content",
                "select 4 to add the content",
                "select 5 to delete the content",
                "select 6 to edit the content",
                "select 7 to exit special admin portal",
                "select 8 to exit the application"
        ));

        Database.adminChoice = new ArrayList<>(Arrays.asList(
                "select 1 to view content list",
                "select 2 to view content list by position number",
                "select 3 to stream the content",
                "select 4 to add the content",
                "select 5 to delete the content",
                "select 6 to edit the content",
                "select 7 to delete all the content",
                "select 8 to hack all the content",
                "select 9 to set the database default",
                "select 10 to change admin or uploader password",
                "select 11 to view accounts",
                "select 12 to update plans",
                "select 13 to manipulate the activation status of accounts",
                "select 14 to manipulate special admins",
                "select 15 to view revenue",
                "select 16 to view login details",
                "select 17 to view activities",
                "select 18 to view subscription activities",
                "select 19 to manipulate stream restrictions",
                "select 20 to change admin message",
                "select 21 to exit admin portal",
                "select 22 to exit the application"
        ));

        Database.specialAdminManipulationChoice = new ArrayList<>(Arrays.asList(
                "select 1 to view special admin requests",
                "select 2 to add special admins",
                "select 3 to remove special admins",
                "select 4 to edit special admins",
                "select 5 to go back"
        ));

        Database db = new Database();
        Database.accountPlans = new ArrayList<Database.AccountPlans>(Arrays.asList(
                db.new AccountPlans("VIP User",true,0),
                db.new AccountPlans("Special User",true,0),
                db.new AccountPlans("Special Admin",false,100000)
        ));

        Database.subscriptionPlans = new ArrayList<Database.SubscriptionPlans>(Arrays.asList(
                db.new SubscriptionPlans("VIP User",false,1000),
                db.new SubscriptionPlans("Special User",false,500),
                db.new SubscriptionPlans("Special Admin",false,50000)
        ));

        Database.accountTypes = new ArrayList<>(Arrays.asList(
                "VIP Users",
                "Special Users",
                "Special Admins"
        ));

        Database.accountTypesWithUser = new ArrayList<>(Arrays.asList(
                "VIP Users",
                "Special Users",
                "Special Admins",
                "User"
        ));

    }

    public static void initPortal() {
        Database.portal = new ArrayList<>(Arrays.asList(
                "User",
                "Uploader",
                "Admin",
                "VIP User",
                "Special User",
                "Special Admin",
                "Subscriptions and Account Purchase",
                "Exit"
        ));
    }
}
