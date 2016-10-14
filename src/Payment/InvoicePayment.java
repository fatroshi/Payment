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
    private final String CURRENCY = "SEK";

    public InvoicePayment(List<String> lines){
        this.lines = lines;
        this.setOpeningRecord();
        this.setEndRecord();
        super.setPaymentRecords(this.paymentQuantity,this.lines);
    }

    private void setOpeningRecord() {
        String tmp;
        String openingRecordStr = this.lines.get(0);

        // Set post type
        tmp = openingRecordStr.substring(PaymentService_.OPENING_RECORD_POST_TYPE.start(), PaymentService_.OPENING_RECORD_POST_TYPE.end());
        super.setOpeningRecordPostType(tmp);
        // Set account number
        tmp = openingRecordStr.substring(PaymentService_.ACOOUNT_NUMBER.start(), PaymentService_.ACOOUNT_NUMBER.end());
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

    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {

    }

    @Override
    public void payment(BigDecimal amount, String reference) {

    }

    @Override
    public void endPaymentBundle() {

    }
}
