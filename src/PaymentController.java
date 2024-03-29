import Payment.InvoicePayment;
import Payment.PaymentService;
import Payment.ReadFile;
import java.util.List;

/**
 * Created by Elise on 2016-10-14.
 */
public class PaymentController {

    public PaymentController (){

    }

    public static void performPaymentTask(String filePath){
        String segment[] = filePath.split("_");
        String task = segment[segment.length -1];

        switch (task){
            case "betalningsservice.txt":
                PaymentService paymentService = new PaymentService(getFileContent(filePath,"ISO-8859-1"));
                System.out.println(paymentService.toString());
                break;
            case "inbetalningstjansten.txt":
                InvoicePayment invoicePayment = new InvoicePayment(getFileContent(filePath, "ISO-8859-1"));
                System.out.println(invoicePayment.toString());
                break;
            default:
                System.out.println("ERROR: No Class found for handling the file: \n\"" + filePath + "\"");
                break;
        }

    }

    public static List<String> getFileContent(String path, String charset){
        ReadFile readFile = new ReadFile(path, charset);
        return readFile.getLines();
    }


}
