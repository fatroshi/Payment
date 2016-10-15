package Payment;

import Payment.Enum.InvoicePayment_;
import Payment.Records.PaymentRecord;
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
        this.sendPayments();
    }

    /**
     * Set values for opening record, information is taken from the first line of the file
     * which corresponds to the first element in List<String> lines.
     */
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

    /**
     * Set values for the ending record.
     * Information is taken from the last line of the file
     * which corresponds to the last element in List<String> lines.
     */
    private void setEndRecord(){
        String tmp;
        String EndRecordStr = this.lines.get(this.lines.size()-1);

        // Set post type
        tmp = EndRecordStr.substring(InvoicePayment_.END_POST_POST_TYPE.start(), InvoicePayment_.END_POST_POST_TYPE.end());
        this.endPostType = tmp;

        // Set amount
        tmp = EndRecordStr.substring(InvoicePayment_.TOTAL_AMOUNT.start(), InvoicePayment_.TOTAL_AMOUNT.end());
        BigDecimal tmpAmount = super.strToBigDecimal(tmp);
        tmpAmount = tmpAmount.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        this.setAmount(tmpAmount);

        // Set paymentQuantity
        tmp = EndRecordStr.substring(InvoicePayment_.QUANTITY.start(), InvoicePayment_.QUANTITY.end());
        tmp = trimWhiteSpace(tmp);
        this.setPaymentQuantity(Integer.parseInt(tmp));

    }

    /**
     * Set values for payment records.
     * Information is taken from the lines in the file, except the first and last line.
     */
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
            BigDecimal tmpAmount = super.strToBigDecimal(amount);
            tmpAmount = tmpAmount.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
            paymentRecord = new PaymentRecord(postType,tmpAmount,reference);

            // Add to list
            super.addPaymentRecord(paymentRecord);
        }
    }

    /**
     * Send payments by calling the implemented methods from interface PaymentReceiver.
     */
    public void sendPayments(){
        // Start payment
        this.startPaymentBundle(super.getOpeningRecord().getAccountNumber(), new Date(), this.currency);

        // Sen each payment
        for(PaymentRecord payment: super.getPaymentRecords()){
            if(!payment.isSend()) {
                payment(payment.getAmount(), payment.getReference());
                payment.setSend(true);
            }
        }
        // All payments are send
        this.endPaymentBundle();
    }

    /**
     * Get post type of ending record
     * @return String - post type
     */
    public String getEndPostType() {
        return endPostType;
    }

    /**
     * Set post type for ending record
     * @param endPostType string value as post type
     */
    public void setEndPostType(String endPostType) {
        this.endPostType = endPostType;
    }

    /**
     * Get quantity of total payments
     * @return int - quantity of payments
     */
    public int getPaymentQuantity() {
        return paymentQuantity;
    }

    /**
     * Set quantity for total payments
     * @param paymentQuantity int value as quantity value
     */
    public void setPaymentQuantity(int paymentQuantity) {
        this.paymentQuantity = paymentQuantity;
    }

    /**
     * The content of the file, each element represents a line i the file and ordered by ASC.
     * @return list<String>
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Set lines, each line corresponds to each line in the file.
     * @param lines file content
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Get total sum for all payment records.
     * @return amount representing the total sum of all payments.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Set total sum for all payments.
     * @param amount representing the total sum of all payments.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Get currency for the payment
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }


    /**
     * Set currency for the payment.
     * @param currency string value.
     */
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

    /**
     * Information about the opening record and payments
     * @return string
     */
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
