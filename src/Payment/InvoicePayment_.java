package Payment;

/**
 * Created by Elise on 2016-10-14.
 */
public enum InvoicePayment_ {
    OPENING_RECORD_POST_TYPE(0,2),
    CLEARING(10,14),
    ACOUNT(14,24),

    INVOICE_PAYMENT_POST_TYPE(0,2),
    AMOUNT(2,22),
    REFERENCE(40,65),

    END_POST_POST_TYPE(0,2),
    END_POST_AMOUNT(2,22),
    END_POST_QUANTITY(30,38);

    private int startPosition;
    private int endPosition;


    InvoicePayment_(int start, int end){
        this.startPosition  = start;
        this.endPosition   = end;
    }

    public int start(){
        return this.startPosition;
    }

    public int end(){
        return this.endPosition;
    }
}
