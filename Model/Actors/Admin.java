package Model.Actors;

import Controller.*;
import Model.DataModel.ActivityHistory;
import View.InputManager;
import Model.DataModel.Movie;
import Model.Database;
import View.ListPrinter;
import View.Printer;


import java.util.ArrayList;
import java.util.List;

class BasicAdmin extends Person {


    public BasicAdmin(String name, String password) {
        super(name, password, false);
    }

    @Override
    public void stream() {
        if(Database.currentUser.isStreamRestricted){
            Printer.streamRestricted();
            return;
        }
        super.viewContentList();
        super.stream();
    }

    public void add(){
        Database.auxUser.add();
    }

    public void delete(){
        Database.auxUser.delete();
    }

    public void edit(){
        Database.auxUser.viewContentList();
        int pos = InputManager.getChoiceInput(Database.movies.size());
        ArrayList inputs = InputManager.getMovie();
        if(inputs == null){
            Printer.printInvalidInputs();
            return;
        }
        int index = Converter.posToIdx(pos);
        Movie newMovie = new Movie(pos,(String)inputs.get(1),(boolean)inputs.get(2),(String)inputs.get(3));
        try {
            Movie oldMovie = Database.movies.get(index);
            Database.movies.set(index,newMovie);
            ActivityHistory activityHistory = new ActivityHistory(Database.activity.EDIT,Database.currentUser.name, oldMovie.name, newMovie.name);
            Database.activityHistories.add(activityHistory);
            Printer.movieEditSuccessful();
        }
        catch(Exception e){
            ExceptionManager.inputNotInRange();
        }

    }

}

class SuperNaturalPower extends BasicAdmin {
    public SuperNaturalPower(String name, String password) {
        super(name, password);
    }

    public void deleteAll(){
        for(int i = 0;i<Database.movies.size();i++){
            Database.movies.get(i).name = "Deleted content";
            Database.movies.get(i).path = "No path";
        }
        Printer.allContentDeleted();
    }

    public void hackAll(){
        for(int i = 0;i<Database.movieCount;i++){
            Database.movies.get(i).name = "Movie hacked";
            Database.movies.get(i).path = "No path";
        }
        Printer.allContentHacked();
    }

    public void setDefault(){
        Database.movies.clear();
        InitialisationManager.initDB();
        Printer.allContentSetToDefault();
    }
}

class AccountControl extends SuperNaturalPower {

    public AccountControl(String name, String password) {
        super(name, password);
    }

    public void changePwd(){
        ListPrinter.listUploaderAndAdmin();
        int choice = InputManager.getChoiceInput(2);
        if(choice == 0)
            return;
        String password = InputManager.getPassword();
        String confirmPassword = InputManager.confirmPassword();
        boolean isPasswordMatch = AuthManager.verifyPassword(password,confirmPassword);
        if(isPasswordMatch) {
            if(choice == 1)
                Database.uploader.password = password;
            else
                Database.admin.password = password;
            Printer.passwordUpdated();
            return;
        }
        Printer.passwordMismatch();
    }

    public void viewAccounts(){
        ListPrinter.accountPrinter(Database.accountTypes);
        int choice = InputManager.getChoiceInput(Database.accountTypes.size());
        if(choice == 0){
            return;
        }
        switch(choice){
            case 0:
                return;
            case 1:
                ListPrinter.accountPrinter(Database.vipUsers);
                return;
            case 2:
                ListPrinter.accountPrinter(Database.specialUsers);
                return;
            case 3:
                ListPrinter.accountPrinter(Database.specialAdmins);
                return;
        }
    }

    public void updatePlans(){
        ListPrinter.accountRelatedPlans();
        int choice = InputManager.getChoiceInput(2);
        switch(choice){
            case 0:
                return;
            case 1:
                GeneralProcessor.accountPlanUpdator(Database.accountPlans);
                break;
            case 2:
                GeneralProcessor.subscriptionPlanUpdator(Database.subscriptionPlans);
                break;
        }
    }

    public void manipulateActivationStatusOfAccounts(){
        ListPrinter.accountTypePrinter(Database.accountTypes);
        int choice = InputManager.getItemInput(Database.accountTypes.size());
        switch(choice){
            case 0:
                return;
            case 1:
                GeneralProcessor.activationManipulator(Database.account.VIP_USER);
                break;
            case 2:
                GeneralProcessor.activationManipulator(Database.account.SPECIAL_USER);
                break;
            case 3:
                GeneralProcessor.activationManipulator(Database.account.SPECIAL_ADMIN);
                break;
        }
    }

    public void viewSpecialAdminRequests(){
        ListPrinter.requestPrinter(Database.specialAdminRequests);
    }

    public void manipulateSpecialAdmins(){
        ListPrinter.choicePrinter(Database.specialAdminManipulationChoice);
        int choice = InputManager.getChoiceInput(Database.specialAdminManipulationChoice.size());
        switch(choice){
            case 0:
                return;
            case 1:
                viewSpecialAdminRequests();
                break;
            case 2:
                AccountManager.purchaseSpecialAdmin();
                break;
            case 3:
                AccountManager.removeSpecialAdmin();
                break;
            case 4:
                AccountManager.editSpecialAdmin();
                break;
            case 5:
        }
    }
}

public class Admin extends AccountControl {

    public Admin(String name, String password) {
        super(name, password);
    }

    public void viewRevenue(){
        System.out.println("Revenue: $ " + Database.revenue);
    }

    public void viewLogins(){
        ListPrinter.listLoginHistories();
    }

    public void viewActivities(){
        ListPrinter.listActivityHistory();
    }

    public void viewSubscriptionActivities(){
        ListPrinter.listSubscriptionActivity();
    }

    public void manipulateStreamRestrictions(){
        ListPrinter.accountTypePrinter(Database.accountTypesWithUser);
        int choice = InputManager.getItemInput(Database.accountTypesWithUser.size());
        switch(choice){
            case 0:
                return;
            case 1:
                GeneralProcessor.streamRestrictionManipulator(Database.account.VIP_USER);
                break;
            case 2:
                GeneralProcessor.streamRestrictionManipulator(Database.account.SPECIAL_USER);
                break;
            case 3:
                GeneralProcessor.streamRestrictionManipulator(Database.account.SPECIAL_ADMIN);
                break;
            case 4:
                GeneralProcessor.streamRestrictionManipulator(Database.account.USER);
                break;
        }
    }

    public void changeAdminMessage(){
        String message = InputManager.getMessage();
        Database.adminMessage = message;
        Printer.messageUpdateSuccessful();
    }
}