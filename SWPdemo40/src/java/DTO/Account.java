
package DTO;


public class Account {
    private String accountID;
    private String accountName;
    private String acpassword;
    private int acSession;

    public Account() {
    }

    public Account(String accountID, String accountName, String acpassword, int acSession) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.acpassword = acpassword;
        this.acSession = acSession;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAcpassword() {
        return acpassword;
    }

    public void setAcpassword(String acpassword) {
        this.acpassword = acpassword;
    }

    public int getAcSession() {
        return acSession;
    }

    public void setAcSession(int acSession) {
        this.acSession = acSession;
    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", accountName=" + accountName + ", acpassword=" + acpassword + ", acSession=" + acSession + '}';
    }

    
    
    
}
