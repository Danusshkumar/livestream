package Model.Actors;
import Controller.Converter;
import Controller.GeneralProcessor;
import Model.DataModel.ActivityHistory;
import View.InputManager;
import Model.DataModel.Movie;
import Model.Database;
import View.Printer;

import java.util.ArrayList;

public class Uploader extends Person {

    public Uploader(String name, String password) {
        super(name, password, false);
    }

    public void add(){
        ArrayList inputs = InputManager.getMovie();
        if(inputs == null){
            Printer.printInvalidInputs();
            return;
        }
        Movie movie = new Movie((Integer)inputs.get(0),(String)inputs.get(1),(boolean)inputs.get(2),(String)inputs.get(3));
        Database.movies.add(movie);
        Database.movieCount += 1;
        ActivityHistory activityHistory = new ActivityHistory(Database.activity.ADD,Database.currentUser.name,movie.name,null);
        Database.activityHistories.add(activityHistory);
        Printer.movieAddSuccessful();
    }

    public void delete(){
        int pos = InputManager.getItemInput(Database.movieCount);
        if(pos == 0)
            return;
        try {
            Movie movie = Database.movies.get(Converter.posToIdx(pos));
            ActivityHistory activityHistory = new ActivityHistory(Database.activity.REMOVE,Database.currentUser.name,movie.name,null);
            Database.activityHistories.add(activityHistory);
            movie.name = "Deleted content";
            movie.path = "no path";
            Printer.movieRemovedSuccessful();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
