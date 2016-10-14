package Payment;

import itello.example.payments.PaymentReceiver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Elise on 2016-10-14.
 */




public class PaymentService extends Payment implements PaymentReceiver{

    private List<String> lines;

    public PaymentService(List<String> lines){
        this.lines = lines;

    }

    public void setOpeningRecord(){
        String tmp = "";
        String openingRecordStr = this.lines.get(0);

        tmp = openingRecordStr.substring(PaymentService_.ACOOUNT_NUMBER.start(), PaymentService_.ACOOUNT_NUMBER.end());
        super.setAccountNumber(tmp);


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
