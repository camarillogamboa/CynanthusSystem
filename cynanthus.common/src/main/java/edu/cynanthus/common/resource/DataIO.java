package edu.cynanthus.common.resource;

import java.io.*;
import java.util.Objects;

/**
 * El tipo Data io.
 */
public class DataIO implements Closeable, DataInput, DataOutput {

    /**
     * El Data input stream.
     */
    private final DataInputStream dataInputStream;
    /**
     * El Data output stream.
     */
    private final DataOutputStream dataOutputStream;

    /**
     * Instancia un nuevo Data io.
     *
     * @param dataInputStream  el data input stream
     * @param dataOutputStream el data output stream
     */
    public DataIO(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.dataInputStream = Objects.requireNonNull(dataInputStream);
        this.dataOutputStream = Objects.requireNonNull(dataOutputStream);
    }

    /**
     * Instancia un nuevo Data io.
     *
     * @param inputStream  el input stream
     * @param outputStream el output stream
     */
    public DataIO(InputStream inputStream, OutputStream outputStream) {
        this(
            new DataInputStream(Objects.requireNonNull(inputStream)),
            new DataOutputStream(Objects.requireNonNull(outputStream))
        );
    }

    /**
     * Read fully.
     *
     * @param b el b
     * @throws IOException el io exception
     */
    @Override
    public final void readFully(byte[] b) throws IOException {
        dataInputStream.readFully(b);
    }

    /**
     * Read fully.
     *
     * @param b   el b
     * @param off el off
     * @param len el len
     * @throws IOException el io exception
     */
    @Override
    public final void readFully(byte[] b, int off, int len) throws IOException {
        dataInputStream.readFully(b, off, len);
    }

    /**
     * Skip bytes int.
     *
     * @param n el n
     * @return el int
     * @throws IOException el io exception
     */
    @Override
    public final int skipBytes(int n) throws IOException {
        return dataInputStream.skipBytes(n);
    }

    /**
     * Read boolean boolean.
     *
     * @return el boolean
     * @throws IOException el io exception
     */
    @Override
    public final boolean readBoolean() throws IOException {
        return dataInputStream.readBoolean();
    }

    /**
     * Read byte byte.
     *
     * @return el byte
     * @throws IOException el io exception
     */
    @Override
    public final byte readByte() throws IOException {
        return dataInputStream.readByte();
    }

    /**
     * Read unsigned byte int.
     *
     * @return el int
     * @throws IOException el io exception
     */
    @Override
    public final int readUnsignedByte() throws IOException {
        return dataInputStream.readUnsignedByte();
    }

    /**
     * Read short short.
     *
     * @return el short
     * @throws IOException el io exception
     */
    @Override
    public final short readShort() throws IOException {
        return dataInputStream.readShort();
    }

    /**
     * Read unsigned short int.
     *
     * @return el int
     * @throws IOException el io exception
     */
    @Override
    public final int readUnsignedShort() throws IOException {
        return dataInputStream.readUnsignedShort();
    }

    /**
     * Read char char.
     *
     * @return el char
     * @throws IOException el io exception
     */
    @Override
    public final char readChar() throws IOException {
        return dataInputStream.readChar();
    }

    /**
     * Read int int.
     *
     * @return el int
     * @throws IOException el io exception
     */
    @Override
    public final int readInt() throws IOException {
        return dataInputStream.readInt();
    }

    /**
     * Read long long.
     *
     * @return el long
     * @throws IOException el io exception
     */
    @Override
    public final long readLong() throws IOException {
        return dataInputStream.readLong();
    }

    /**
     * Read float float.
     *
     * @return el float
     * @throws IOException el io exception
     */
    @Override
    public final float readFloat() throws IOException {
        return dataInputStream.readFloat();
    }

    /**
     * Read double double.
     *
     * @return el double
     * @throws IOException el io exception
     */
    @Override
    public final double readDouble() throws IOException {
        return dataInputStream.readDouble();
    }

    /**
     * Read line string.
     *
     * @return el string
     * @throws IOException el io exception
     */
    @Override
    public final String readLine() throws IOException {
        return dataInputStream.readLine();
    }

    /**
     * Read utf string.
     *
     * @return el string
     * @throws IOException el io exception
     */
    @Override
    public final String readUTF() throws IOException {
        return dataInputStream.readUTF();
    }

    /**
     * Write.
     *
     * @param b el b
     * @throws IOException el io exception
     */
    @Override
    public final void write(int b) throws IOException {
        dataOutputStream.write(b);
    }

    /**
     * Write.
     *
     * @param b el b
     * @throws IOException el io exception
     */
    @Override
    public final void write(byte[] b) throws IOException {
        dataOutputStream.write(b);
    }

    /**
     * Write.
     *
     * @param b   el b
     * @param off el off
     * @param len el len
     * @throws IOException el io exception
     */
    @Override
    public final void write(byte[] b, int off, int len) throws IOException {
        dataOutputStream.write(b, off, len);
    }

    /**
     * Write boolean.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeBoolean(boolean v) throws IOException {
        dataOutputStream.writeBoolean(v);
    }

    /**
     * Write byte.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeByte(int v) throws IOException {
        dataOutputStream.writeByte(v);
    }

    /**
     * Write short.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeShort(int v) throws IOException {
        dataOutputStream.writeShort(v);
    }

    /**
     * Write char.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeChar(int v) throws IOException {
        dataOutputStream.writeChar(v);
    }

    /**
     * Write int.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeInt(int v) throws IOException {
        dataOutputStream.writeInt(v);
    }

    /**
     * Write long.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeLong(long v) throws IOException {
        dataOutputStream.writeLong(v);
    }

    /**
     * Write float.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeFloat(float v) throws IOException {
        dataOutputStream.writeFloat(v);
    }

    /**
     * Write double.
     *
     * @param v el v
     * @throws IOException el io exception
     */
    @Override
    public final void writeDouble(double v) throws IOException {
        dataOutputStream.writeDouble(v);
    }

    /**
     * Write bytes.
     *
     * @param s el s
     * @throws IOException el io exception
     */
    @Override
    public final void writeBytes(String s) throws IOException {
        dataOutputStream.writeBytes(s);
    }

    /**
     * Write chars.
     *
     * @param s el s
     * @throws IOException el io exception
     */
    @Override
    public final void writeChars(String s) throws IOException {
        dataOutputStream.writeChars(s);
    }

    /**
     * Write utf.
     *
     * @param s el s
     * @throws IOException el io exception
     */
    @Override
    public final void writeUTF(String s) throws IOException {
        dataOutputStream.writeUTF(s);
    }

    /**
     * Close.
     *
     * @throws IOException el io exception
     */
    @Override
    public void close() throws IOException {
        dataInputStream.close();
        dataOutputStream.close();
    }

}
