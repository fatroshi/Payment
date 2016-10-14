/**
 * Created by Elise on 2016-10-14.
 */
public class Main {

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile("resources/Exempelfil_betalningsservice.txt", "ISO-8859-1");
        PaymentService ps = new PaymentService(readFile.getLines());

        System.out.println(ps.toString());


    }
}
