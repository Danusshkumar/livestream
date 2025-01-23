package Model.Actors;

import View.Printer;

public class User extends Person {
    public User(String name, String password, Boolean streamRestriction) {
        super(name,password, streamRestriction);
    }

    @Override
    public void viewContentList() {
        Printer.contentListRestricted();
    }
}
