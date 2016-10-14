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
        this.setPaymentQuantity(Integer.parseInt(tmp));
        // Set date
        tmp = openingRecordStr.substring(PaymentService_.PAYMENT_DATE.start(), PaymentService_.PAYMENT_DATE.end());

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


    private void setPaymentRecords(){

        String postType;
        String amount;
        String reference;

        PaymentRecord paymentRecord;
        for(int i = 1; i < this.paymentQuantity + 1; i++ ){
            // Get info from line
            postType    = this.lines.get(i).substring(PaymentService_.PAYMENT_POST_TYPE.start(), PaymentService_.PAYMENT_POST_TYPE.start());
            amount      = this.lines.get(i).substring(PaymentService_.PAYMENT_AMOUNT.start(), PaymentService_.PAYMENT_AMOUNT.start());
            reference   = this.lines.get(i).substring(PaymentService_.REFERENCE.start(), PaymentService_.REFERENCE.start());
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
        String string = "";

        return string;
    }
}
