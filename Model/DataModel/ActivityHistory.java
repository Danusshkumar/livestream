package Model.DataModel;

import Model.Database;

public class ActivityHistory {

    public Database.activity activityType;
    public String accountName;
    public String affectedMovie1;
    public String affectedMovie2;
    public String description;

    public ActivityHistory(Database.activity activityType, String accountName, String affectedMovie1, String affectedMovie2) {
        this.activityType = activityType;
        this.accountName = accountName;
        this.affectedMovie1 = affectedMovie1;
        this.affectedMovie2 = affectedMovie2;
        setDescription();
    }

    public void setDescription(){
        if(activityType == Database.activity.ADD){
            description = accountName + " added " + affectedMovie1;
        }
        else if(activityType == Database.activity.REMOVE){
            description = accountName + " removed " + affectedMovie1;
        }
        else if(activityType == Database.activity.EDIT){
            description = accountName + " edited " + affectedMovie1 + " into " + affectedMovie2;
        }
    }
}
