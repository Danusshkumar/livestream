package Model.Actors;

import Controller.Converter;
import Controller.GeneralProcessor;
import Model.DataModel.LoginHistory;
import View.InputManager;
import Model.Database;
import View.ListPrinter;
import View.Printer;

import java.time.LocalDateTime;

public class Person {

    public int loginId; //login id will be assigned for every login
    public String name;
    public String password;
    public boolean isActivated;

    public boolean isStreamRestricted;


    public void setStreamRestricted(boolean streamRestricted) {
        isStreamRestricted = streamRestricted;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Person(String name, String password, Boolean streamRestricted) {
        this.name = name;
        this.password = password;
        setStreamRestricted(streamRestricted);
    }

    @Override
    public String toString() {
        return  name + "( " + (isActivated ? "Activated" : "Deactivated") +  " )";
    }

    public String toStringNameFormat(){
        return name;
    }

    public void viewContentList(){
        ListPrinter.listContent();
    }

    public void viewContent(){
        int pos = InputManager.getChoiceInput(Database.movieCount);
        if(pos == 0)
            return;
        System.out.println(Database.movies.get(Converter.posToIdx(pos)));
    }

    public void stream(){
        if(Database.currentUser.isStreamRestricted){
            Printer.streamRestricted();
            return;
        }
        int pos = InputManager.getItemInput(Database.movieCount);
        if(pos == 0)
            return;
        Printer.printStreamingPreparation(pos);
        GeneralProcessor.streamNow(pos);
    }

    public void logout(){
        for(int i = 0;i<Database.loginHistories.size();i++){
            LoginHistory history = Database.loginHistories.get(i);
            if(history.getLoginId() == Database.currentUser.loginId){
                history.setLogoutTime(LocalDateTime.now());
            }
        }
        Database.currentUser = null;
    }

    public void exit(){
        Printer.exitNote();
        System.exit(0);
    }

}
