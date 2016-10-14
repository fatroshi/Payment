package Payment;

/**
 * Created by Elise on 2016-10-14.
 */
public class OpeningRecord {
    private String postType;
    private String clearingNumber;
    private String accountNumber;


    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getClearingNumber() {
        return clearingNumber;
    }

    public void setClearingNumber(String clearingNumber) {
        this.clearingNumber = clearingNumber;
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
        infoAboutObject += "Öppningspost\n";
        infoAboutObject += "Posttyp: "           + this.getPostType()        + "\t";
        infoAboutObject += "Clearingnummer: "    + this.getClearingNumber()  + "\t";
        infoAboutObject += "Kontonummer: "       + this.getAccountNumber()   + "\t";
        return infoAboutObject;
    }
}
