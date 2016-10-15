package Payment.Enum;

import Payment.Payment;

/**
 * Created by Elise on 2016-10-14.
 */
public enum InvoicePayment_ {
    OPENING_RECORD_POST_TYPE(0,2),
    CLEARING(10,14),
    ACCOUNT_NUMBER(14,24),
    ACCOUNT(10,24),

    PAYMENT_POST_TYPE(0,2),
    PAYMENT_AMOUNT(2,22),
    REFERENCE(40,65),

    END_POST_POST_TYPE(0,2),
    TOTAL_AMOUNT(2,22),
    QUANTITY(30,38);

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
