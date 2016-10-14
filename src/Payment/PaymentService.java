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
