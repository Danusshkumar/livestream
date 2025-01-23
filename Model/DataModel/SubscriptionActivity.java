package Model.DataModel;

import Model.Database;

import java.time.LocalDateTime;

public class SubscriptionActivity {
    public Database.account accountType;
    public String accountHolder;
    public LocalDateTime time;

    public SubscriptionActivity(Database.account accountType, String accountHolder,LocalDateTime time) {
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.time = time;
    }
}
