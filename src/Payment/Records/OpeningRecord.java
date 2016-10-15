package Payment.Records;



/**
 * Created by Elise on 2016-10-14.
 */
public class OpeningRecord {
    private String postType;
    private String clearing;
    private String account;
    private String accountNumber;

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getClearing() {
        return clearing;
    }

    public void setClearing(String clearing) {
        this.clearing = clearing;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString(){
        String infoAboutObject = "";
        infoAboutObject += "### Öppningspost\n";
        infoAboutObject += "Posttyp: "           + this.getPostType()       + "\t";
        infoAboutObject += "Clearingnummer: "    + this.getClearing()       + "\t";
        infoAboutObject += "Kontonummer: "       + this.getAccountNumber()  + "\t\n";
        return infoAboutObject;
    }

}
