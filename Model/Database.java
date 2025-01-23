package Model;

import Model.Actors.*;
import Model.DataModel.ActivityHistory;
import Model.DataModel.LoginHistory;
import Model.DataModel.Movie;
import Model.DataModel.SubscriptionActivity;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    public enum account {
        VIP_USER,
        SPECIAL_USER,
        SPECIAL_ADMIN,
        USER,
        UPLOADER,
        ADMIN,
    }

    public enum activity {
        ADD,
        EDIT,
        REMOVE
    }

    //format
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //explicitly defined data
    public static int revenue = 0;

    public static int movieCount = 0;

    public static Person currentUser = null;
    public static Uploader auxUser = new Uploader("anonymous user","no password");

    public static User simpleUser = new User("anonymous user","no password", true);

    public static Boolean streamRestrictionForUser = true;
    public static String adminMessage = "No admin message";

    //data models
    public static ArrayList<Movie> movies = new ArrayList<>();
    public static ArrayList<ActivityHistory> activityHistories = new ArrayList<>();
    public static ArrayList<LoginHistory> loginHistories = new ArrayList<>();
    public static ArrayList<SubscriptionActivity> subscriptionActivities = new ArrayList<>();

    //requests
    public static ArrayList<String> specialAdminRequests = new ArrayList<>();

    //portals
    public static ArrayList<String> portal = new ArrayList<>();


    //account sections
    public static ArrayList<String> accountTypes = new ArrayList<>();
    public static ArrayList<String> accountTypesWithUser = new ArrayList<>();

    public static Admin admin = new Admin("Admin", "password");
    public static Uploader uploader = new Uploader("Uploader", "password");
    public static ArrayList<SpecialUser> specialUsers = new ArrayList<>();
    public static ArrayList<VIPUser> vipUsers = new ArrayList<>();
    public static ArrayList<SpecialAdmin> specialAdmins = new ArrayList<>();

    //prices
    public class AccountPlans {
        public String accountType;
        public boolean isFree;
        public int price;

        public AccountPlans(String accountType, boolean isFree, int price) {
            this.accountType = accountType;
            this.isFree = isFree;
            this.price = price;
        }
    }
    public class SubscriptionPlans {

        public String accountType;
        public boolean isFree;
        public int price;

        public SubscriptionPlans(String accountType, boolean isFree, int price) {
            this.accountType = accountType;
            this.isFree = isFree;
            this.price = price;
        }
    }

    public static ArrayList<Database.AccountPlans> accountPlans = new ArrayList<>();
    public static ArrayList<Database.SubscriptionPlans> subscriptionPlans = new ArrayList<>();


    //portal choices
    public static ArrayList<String> userChoice = new ArrayList<String>();
    public static ArrayList<String> uploaderChoice = new ArrayList<String>();
    public static ArrayList<String> adminChoice = new ArrayList<String>();
    public static ArrayList<String> specialUserChoice = new ArrayList<String>();

    public static ArrayList<String> specialAdminChoice = new ArrayList<String>();
    public static ArrayList<String> purchaseAndSubscriptionChoice = new ArrayList<>();

    public static ArrayList<String> specialAdminManipulationChoice = new ArrayList<>();
}
