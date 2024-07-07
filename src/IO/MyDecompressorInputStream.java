package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private int currentByte;
    private int count;

    //Constructor
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
        this.currentByte = -1;
        this.count = 0;
    }

    //Implement the read() method
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