package Model.DataModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginHistory {

    public static int autoId = 0;

    int loginId;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String accountName;
    public LocalDateTime loginTime;
    public LocalDateTime logoutTime;

    public LoginHistory(int loginId, String accountName, LocalDateTime loginTime) {
        this.loginId = loginId;
        this.accountName = accountName;
        this.loginTime = loginTime;
        this.logoutTime = null;
    }
}
