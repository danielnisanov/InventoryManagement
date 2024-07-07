package IO;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int lastByte;
    private int count;

    //Constructor
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.lastByte = -1;
        this.count = 0;
    }

    //Implement the write(int b) method
    @Override
    public void write(int b) throws IOException {
        if (b != lastByte) {
            if (count > 0) {
                out.write(lastByte);
                out.write(count);
            }
            lastByte = b;
            count = 1;
        } else {
            count++;
        }
    }

    //Implement the write(byte[] b) method
    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < b.length; i++) {
            write(b[i]);
        }
    }

    @Override
    public void close() throws IOException {
        if (count > 0) {
            out.write(lastByte);
            out.write(count);
        }
        out.close();
    }
}