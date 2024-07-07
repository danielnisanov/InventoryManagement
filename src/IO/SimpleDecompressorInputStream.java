package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;
    private int currentByte = -1;
    private int count = 0;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        if (count == 0) {
            currentByte = in.read();
            if (currentByte == -1) {
                return -1;
            }
            count = in.read();
        }
        count--;
        return currentByte;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}