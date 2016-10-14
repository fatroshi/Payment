package Payment;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        if(accountNumber.contains(" ")) {
            String segments[] = accountNumber.split(" ");
            this.openingRecord.setClearing(segments[0]);
            this.openingRecord.setAccountNumber(segments[1]);
        }else{
            this.openingRecord.setAccount(accountNumber);
        }

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

    public Date strToDate(String dateString, String dateFormat){
        Date date = null;

        try {
            date = new SimpleDateFormat(dateFormat).parse(trimWhiteSpace(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            return date;
        }
    }

    private String cleanDecimalNumbers(String strNumbers){
        String cleanNumbers = strNumbers;
        cleanNumbers = cleanNumbers.replace(" ","");
        cleanNumbers = cleanNumbers.replace(",",".");
        return cleanNumbers;
    }

    public BigDecimal strToBigDecimal(String str){
        String strToBigDecimal = this.cleanDecimalNumbers(str);
        BigDecimal bigDecimal = new BigDecimal(strToBigDecimal);
        return bigDecimal;
    }
}
