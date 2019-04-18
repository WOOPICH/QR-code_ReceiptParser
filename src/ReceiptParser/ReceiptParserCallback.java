package ReceiptParser;

public interface ReceiptParserCallback {

    void onResult(ReceiptInfo info);

    void onFailure();
}
