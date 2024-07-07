package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int lastByte;
    private int count;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.lastByte = -1;
        this.count = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (b != lastByte) {
            if (count > 0) {
                out.write(count);
                count = 0;
            }
            out.write(b);
            lastByte = b;
        }
        count++;
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (byte value : b) {
            write(value);
        }
        if (count > 0) {
            out.write(count);
        }
    }

    @Override
    public void close() throws IOException {
        if (count > 0) {
            out.write(count);
        }
        out.close();
    }
}
