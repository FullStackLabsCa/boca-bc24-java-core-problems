package trade.processing.multithreading.utility;

import java.io.File;

public interface FilesWriter {
    void chunkFileWriter(String fileLine, File filepath);
}
