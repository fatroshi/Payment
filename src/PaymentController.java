import Payment.PaymentService;
import Payment.ReadFile;
import java.util.List;

/**
 * Created by Elise on 2016-10-14.
 */
public class PaymentController {
    private ReadFile readFile;

    public PaymentController (){

    }

    public void performTask(String filePath){
        String segment[] = filePath.split("_");
        String task = segment[segment.length -1];

        switch (task){
            case "betalningsservice.txt":
                PaymentService paymentService = new PaymentService(getFileContent(filePath,"ISO-8859-1"));
                System.out.println(paymentService.toString());
                break;
            case "inbetalningstjansten.txt":
                break;
            default:
                break;
        }

    }

    public List<String> getFileContent(String path, String charset){
        this.readFile = new ReadFile(path, charset);
        return readFile.getLines();
    }


}