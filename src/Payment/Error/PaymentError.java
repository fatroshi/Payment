package Payment.Error;

import Payment.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elise on 2016-10-15.
 */
public class PaymentError {
    private List<String> openingRecord;
    private List<String> paymentRecord;
    private List<String> endRecord;

    private boolean hasOpnningError;
    private boolean hasPaymentError;
    private boolean hasEndError;

    public PaymentError(){
        this.openingRecord  = new ArrayList<>();
        this.paymentRecord  = new ArrayList<>();
        this.endRecord      = new ArrayList<>();
    }

    private String addOpening(String error){
        this.openingRecord.add(error);
        return error;
    }

    private String addPayment(String error){
        this.paymentRecord.add(error);
        return error;
    }

    private String addEnd(String error){
        this.endRecord.add(error);
        return error;
    }

    public List<String> getOpeningRecord() {
        return openingRecord;
    }

    public void setOpeningRecord(List<String> openingRecord) {
        this.openingRecord = openingRecord;
    }

    public List<String> getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(List<String> paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public List<String> getEndRecord() {
        return endRecord;
    }

    public void setEndRecord(List<String> endRecord) {
        this.endRecord = endRecord;
    }

    public boolean isHasOpnningError() {
        return hasOpnningError;
    }

    public void setHasOpnningError(boolean hasOpnningError) {
        this.hasOpnningError = hasOpnningError;
    }

    public boolean isHasPaymentError() {
        return hasPaymentError;
    }

    public void setHasPaymentError(boolean hasPaymentError) {
        this.hasPaymentError = hasPaymentError;
    }

    public boolean isHasEndError() {
        return hasEndError;
    }

    public void setHasEndError(boolean hasEndError) {
        this.hasEndError = hasEndError;
    }

    public Boolean hasErrors(){
        boolean hasError = false;
        if(this.openingRecord.size() != 0 || this.paymentRecord.size() != 0 || this.endRecord.size() != 0){
            hasError = true;
        }
        return hasError;
    }

    public String getErrors(){
        String errors = "\nERROR: \n";

        if(this.openingRecord.size() != 0) {
            errors += "# Öppningspost";
            for (String error : openingRecord) {
                errors += "\n" + error;
            }
        }

        if(this.paymentRecord.size() != 0) {
            errors += "# Betalningspost";
            for (String error : paymentRecord) {
                errors += "\n" + error;
            }
        }

        if(this.paymentRecord.size() != 0) {
            errors += "# Slutpost";
            for (String error : endRecord) {
                errors += "\n" + error;
            }
        }

        return errors;
    }
}
