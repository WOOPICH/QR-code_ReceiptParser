package ReceiptParser;

import java.time.LocalDateTime;

public class ReceiptInfo {
    private LocalDateTime dateTime;     //Дата и время
    private long amount;                //Сумма в копейках
    private long fn;                    //Номер кассового аппарата
    private long i;                     //Фискальный признак документа
    private long fp;                    //Номер фискального документа
    private int n;                      //Признак расчетной операции

    public ReceiptInfo(LocalDateTime dateTime, long amount, long fn, long i, long fp, int n) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.fn = fn;
        this.i = i;
        this.fp = fp;
        this.n = n;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public long getFn() {
        return fn;
    }

    public long getI() {
        return i;
    }

    public long getFp() {
        return fp;
    }

    public int getN() {
        return n;
    }
}
