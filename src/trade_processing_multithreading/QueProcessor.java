package trade_processing_multithreading;

import java.util.concurrent.LinkedBlockingDeque;

public interface QueProcessor {
    void processAndInsertToDB(String trade);
}
