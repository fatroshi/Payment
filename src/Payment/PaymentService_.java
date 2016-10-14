package Payment;

/**
 * Created by Elise on 2016-10-14.
 */
public enum PaymentService_ {

    OPENING_RECORD(0,1),
    ACOOUNT_NUMBER(1,16),
    AMOUNT(16,30),
    QUANTITY(30,40),
    PAYMENT_DATE(40,48),
    CURRENCY(48,51),

    PAYMENT_AMOUNT(1,15),
    REFERENCE(15,50);

    private int startPosition;
    private int endPosition;


    PaymentService_(int start, int end){
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
