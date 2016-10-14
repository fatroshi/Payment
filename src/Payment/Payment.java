package Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elise on 2016-10-14.
 */
public class Payment {
    private OpeningRecord openingRecord;
    private List<PaymentRecord> paymentRecords;

    public Payment(){
        this.openingRecord = new OpeningRecord();
        this.paymentRecords = new ArrayList<>();
    }

    public void setOpeningRecordPostType(String postType){
        this.openingRecord.setPostType(postType);
    }

    public void setAccountNumber(String accountNumber){
        this.openingRecord.setAccount(accountNumber);
    }


    public OpeningRecord getOpeningRecord() {
        return openingRecord;
    }

    public void setOpeningRecord(OpeningRecord openingRecord) {
        this.openingRecord = openingRecord;
    }

    public List<PaymentRecord> getPaymentRecords() {
        return paymentRecords;
    }

    public void setPaymentRecords(List<PaymentRecord> paymentRecords) {
        this.paymentRecords = paymentRecords;
    }

    public String trimWhiteSpace(String string){
        return string.replace(" ", "");
    }

    public void addPaymentRecord(PaymentRecord paymentRecord){
        this.paymentRecords.add(paymentRecord);
    }


    private String cleanDecimalNumbers(String strNumbers){
        String cleanNumbers = strNumbers;
        cleanNumbers = cleanNumbers.replace(",",".");
        cleanNumbers = cleanNumbers.replace(" ","");
        return cleanNumbers;
    }

    public BigDecimal strToBigDecimal(String str){
        String strToBigDecimal = this.cleanDecimalNumbers(str);
        BigDecimal bigDecimal = new BigDecimal(strToBigDecimal);
        return bigDecimal;
    }
}
