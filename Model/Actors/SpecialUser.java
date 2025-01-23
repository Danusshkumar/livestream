package Model.Actors;

import Model.Database;
import View.Printer;

public class SpecialUser extends Person {

    public SpecialUser(String name,String password){
        super(name,password, false);
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
}
