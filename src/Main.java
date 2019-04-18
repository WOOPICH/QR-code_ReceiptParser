import ReceiptParser.ReceiptInfo;
import ReceiptParser.ReceiptParserCallback;
import ReceiptParser.ReceiptParserUtil;

public class Main {

    public static void main(String[] args) {
        ReceiptParserUtil.parse("t=20190327T205345&s=259.00&fn=9288000100014806&i=164134&fp=1688606736&n=1", //Дальше тестовый колбэк
                new ReceiptParserCallback() {
            @Override
            public void onResult(ReceiptInfo info) {
                System.out.println(info.getDateTime());
                System.out.println(info.getAmount());
                System.out.println(info.getFn());
                System.out.println(info.getI());
                System.out.println(info.getFp());
                System.out.println(info.getN());
            }

            @Override
            public void onFailure() {
                System.out.println("hah_mda");
            }
        });
    }
}
