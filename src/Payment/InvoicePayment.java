package Payment;

import itello.example.payments.PaymentReceiver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Elise on 2016-10-14.
 */
public class InvoicePayment extends Payment implements PaymentReceiver{

    private String endPostType;
    private int paymentQuantity;
    private List<String> lines;
    private BigDecimal amount;
    private String currency = "SEK";

    public InvoicePayment(List<String> lines){
        this.lines = lines;
        this.setOpeningRecord();
        this.setEndRecord();
        this.setPaymentRecords();
    }

    private void setOpeningRecord() {
        String tmp;
        String openingRecordStr = this.lines.get(0);

        // Set post type
        tmp = openingRecordStr.substring(InvoicePayment_.OPENING_RECORD_POST_TYPE.start(), InvoicePayment_.OPENING_RECORD_POST_TYPE.end());
        super.setOpeningRecordPostType(tmp);
        // Set clearing number
        tmp = openingRecordStr.substring(InvoicePayment_.CLEARING.start(), InvoicePayment_.CLEARING.end());
        super.setClearing(tmp);
        // Set account number
        tmp = openingRecordStr.substring(InvoicePayment_.ACCOUNT_NUMBER.start(), InvoicePayment_.ACCOUNT_NUMBER.end());
        super.setAccountNumber(tmp);
    }

    private void setEndRecord(){
        String tmp;
        String EndRecordStr = this.lines.get(this.lines.size()-1);
        // Set post type
        tmp = EndRecordStr.substring(InvoicePayment_.END_POST_POST_TYPE.start(), InvoicePayment_.END_POST_POST_TYPE.end());
        this.endPostType = tmp;
        // Set amount
        tmp = EndRecordStr.substring(InvoicePayment_.TOTAL_AMOUNT.start(), InvoicePayment_.TOTAL_AMOUNT.end());
        this.setAmount(super.strToBigDecimal(tmp));
        // Set paymentQuantity
        tmp = EndRecordStr.substring(InvoicePayment_.QUANTITY.start(), InvoicePayment_.QUANTITY.end());
        tmp = trimWhiteSpace(tmp);
        this.setPaymentQuantity(Integer.parseInt(tmp));

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
            postType    = line.substring(InvoicePayment_.PAYMENT_POST_TYPE.start(), InvoicePayment_.PAYMENT_POST_TYPE.end());
            amount      = line.substring(InvoicePayment_.PAYMENT_AMOUNT.start(), InvoicePayment_.PAYMENT_AMOUNT.end());
            reference   = line.substring(InvoicePayment_.REFERENCE.start(), InvoicePayment_.REFERENCE.end());
            // Create PaymentRecord object
            paymentRecord = new PaymentRecord(postType,super.strToBigDecimal(amount),reference);
            // Add to list
            super.addPaymentRecord(paymentRecord);
        }
    }

    public String getEndPostType() {
        return endPostType;
    }

    public void setEndPostType(String endPostType) {
        this.endPostType = endPostType;
    }

    public int getPaymentQuantity() {
        return paymentQuantity;
    }

    public void setPaymentQuantity(int paymentQuantity) {
        this.paymentQuantity = paymentQuantity;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        info += "Betalningsdatum: " + "SAKNAS" + "";
        info += "\nValuta: "          + this.getCurrency() + "\n";
        info += "### Betalningspost\n";
        for(PaymentRecord payment: super.getPaymentRecords()){
            info += payment.toString();
        }

        return info;
    }
}
