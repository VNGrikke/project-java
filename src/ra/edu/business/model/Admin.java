package ra.edu.business.model;

public class Admin {
    int accountId;
    String accountName;
    String password;

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {}

    public Admin(int accountId, String accountName, String password) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
    }

}
