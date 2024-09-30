package trade_processing_multithreading;

import java.io.File;

public interface FilesWriter {
    void chunkFileWriter(String fileLine, File filepath);
}
