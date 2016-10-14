package Payment;

import itello.example.payments.PaymentReceiver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Elise on 2016-10-14.
 */

public class PaymentService extends Payment implements PaymentReceiver{
    private BigDecimal amount;
    private List<String> lines;
    private int paymentQuantity;
    private String currency;
    private Date paymentDate;


    public PaymentService(List<String> lines){
        this.lines = lines;
        this.setOpeningRecord();                    // Init. and fetch information for OpeningRecord
        this.setPaymentRecords();
        this.sendPayments();
    }

    private void setOpeningRecord(){
        String tmp;
        String openingRecordStr = this.lines.get(0);

        // Set post type
        tmp = openingRecordStr.substring(PaymentService_.OPENING_RECORD_POST_TYPE.start(), PaymentService_.OPENING_RECORD_POST_TYPE.end());
        super.setOpeningRecordPostType(tmp);
        // Set account number
        tmp = openingRecordStr.substring(PaymentService_.ACOOUNT_NUMBER.start(), PaymentService_.ACOOUNT_NUMBER.end());
        super.setAccountNumber(tmp);
        // Set amount
        tmp = openingRecordStr.substring(PaymentService_.TOTAL_AMOUNT.start(), PaymentService_.TOTAL_AMOUNT.end());
        this.setAmount(super.strToBigDecimal(tmp));
        // Set paymentQuantity
        tmp = openingRecordStr.substring(PaymentService_.QUANTITY.start(), PaymentService_.QUANTITY.end());
        tmp = trimWhiteSpace(tmp);
        this.setPaymentQuantity(Integer.parseInt(tmp));
        // Set date
        tmp = openingRecordStr.substring(PaymentService_.PAYMENT_DATE.start(), PaymentService_.PAYMENT_DATE.end());
        this.paymentDate = super.strToDate(tmp,"yyyyMMdd");
        // Set Currency
        tmp = openingRecordStr.substring(PaymentService_.CURRENCY.start(), PaymentService_.CURRENCY.end());
        this.setCurrency(tmp);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getPaymentQuantity() {
        return paymentQuantity;
    }

    public void setPaymentQuantity(int paymentQuantity) {
        this.paymentQuantity = paymentQuantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    private void setPaymentRecords(){
        String postType;
        String amount;
        String reference;
        PaymentRecord paymentRecord;

        String line;

        for(int i = 1; i < this.paymentQuantity + 1; i++ ){
            line = this.lines.get(i);
            // Get info from line
            postType    = line.substring(PaymentService_.PAYMENT_POST_TYPE.start(), PaymentService_.PAYMENT_POST_TYPE.end());
            amount      = line.substring(PaymentService_.PAYMENT_AMOUNT.start(), PaymentService_.PAYMENT_AMOUNT.end());
            reference   = line.substring(PaymentService_.REFERENCE.start(), PaymentService_.REFERENCE.end());
            // Create PaymentRecord object
            paymentRecord = new PaymentRecord(postType,super.strToBigDecimal(amount),reference);
            // Add to list
            super.addPaymentRecord(paymentRecord);
        }
    }


    public void sendPayments(){

        this.startPaymentBundle(super.getOpeningRecord().getAccountNumber(), this.paymentDate, this.currency);

        for(PaymentRecord payment: super.getPaymentRecords()){
            if(!payment.isSend()) {
                payment(payment.getAmount(), payment.getReference());
                payment.setSend(true);
            }
        }
        // All payments are send
        this.endPaymentBundle();
    }

    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {

    }

    @Override
    public void payment(BigDecimal amount, String reference) {

    }

    @Override
    public void endPaymentBundle() {

    }

    @Override
    public String toString(){
        String info = "";
        info += super.getOpeningRecord().toString();
        info += "Betalningsdatum: " + this.getPaymentDate() + "";
        info += "\nValuta: "          + this.getCurrency() + "\n";
        info += "### Betalningspost\n";
        for(PaymentRecord payment: super.getPaymentRecords()){
            info += payment.toString();
        }

        return info;
    }
}
