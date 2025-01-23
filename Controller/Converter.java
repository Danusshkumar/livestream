package Controller;

import Model.Database;

public class Converter {
    
    public static int posToIdx(int pos){
        return pos - 1;
    }

    public static int idxToPos(int idx){
        return idx+1;
    }

    public static String activityTypeToString(Database.activity activity){
        if(activity == Database.activity.ADD){
            return "add";
        }
        else if(activity == Database.activity.EDIT){
            return "edit";
        }
        else if(activity == Database.activity.REMOVE){
            return "remove";
        }
        else {
            return null;
        }
    }


    public static String accountTypeToString(Database.account account){
        if(account == Database.account.VIP_USER){
            return "VIP User";
        }
        else if(account == Database.account.SPECIAL_USER){
            return "Special User";
        }
        else if(account == Database.account.SPECIAL_ADMIN){
            return "Special Admin";
        }
        else if(account == Database.account.USER){
            return "User";
        }
        else if(account == Database.account.UPLOADER){
            return "Uploader";
        }
        else if(account == Database.account.ADMIN){
            return "Admin";
        }
        else {
            return null;
        }
    }

}
