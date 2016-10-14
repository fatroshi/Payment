import java.math.BigDecimal;

/**
 * Created by Elise on 2016-10-14.
 */
public class PaymentRecord {
    private String postType;
    private BigDecimal amount;
    private String reference;

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
