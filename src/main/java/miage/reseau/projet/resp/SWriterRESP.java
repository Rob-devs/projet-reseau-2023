package miage.reseau.projet.resp;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import miage.reseau.projet.utils.LogBuilder;

/**
 * The SWriterRESP class is a Java helper class that writes different types of
 * content to an output stream based on their type.
 */
public class SWriterRESP {

    private BufferedOutputStream outputStream;

    public SWriterRESP(OutputStream outputStream) {
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    /**
     * This Java function writes different types of content to an output stream
     * based on their type.
     * 
     * @param content The parameter "content" is an object of type ContentRESP,
     *                which contains
     *                information about the type of data being written to an output
     *                stream. The method "write" takes
     *                this object as input and writes the data to the output stream
     *                based on the type of data.
     */
    public void write(ContentRESP content) throws Exception {

        if (!content.isTypeNull()) {
            outputStream.write(TypeRESP.getCharFromType(content.getType()));
        }

        TypeRESP type = content.getType();

        if (type == TypeRESP.STRING) {
            writeString(content);
        } else if (type == TypeRESP.STRING_BULK) {
            writeStringBulk(content);
        } else if (type == TypeRESP.INT) {
            writeInt(content);
        } else if (type == TypeRESP.ARRAY) {
            writeArray(content);
        } else if (type == TypeRESP.ERROR) {
            writeError(content);
        } else {
            throw new Exception(LogBuilder.getInvalidInputMessage());
        }

        outputStream.flush();
    }

    /**
     * This function writes the given data as bytes to an output stream.
     */
    private void writeData(String data) throws Exception {
        outputStream.write(data.getBytes());
    }

    /**
     * This function adds a carriage return and line feed to the output stream.
     */
    private void writeCRLF() throws Exception {
        writeData("\r\n");
    }

    /**
     * This Java function writes a string content to an output stream and adds a
     * carriage return and line feed at the end.
     * 
     * @throws Exception
     */
    private void writeString(ContentRESP content) throws Exception {
        writeData(((String) content.getContent()));
        writeCRLF();
    }

    /**
     * The function writes a string representation of a ContentRESP object to an
     * output stream and throws an IOException if an error occurs.
     * 
     * @param content The parameter "content" is of type ContentRESP, which is an
     *                object that contains
     *                information about a response to a client request. The
     *                writeError method takes this object as
     *                input and writes its contents as a string to an output stream.
     * @throws Exception
     */
    private void writeError(ContentRESP content) throws Exception {
        writeString(content);
    }

    /**
     * This Java function writes an integer value to an output stream and adds a
     * carriage return and line feed.
     * 
     * @param content The parameter "content" is an object of type ContentRESP,
     *                which contains the
     *                content to be written as an Integer.
     * @throws Exception
     */
    private void writeInt(ContentRESP content) throws Exception {
        writeData(((Integer) content.getContent()).toString());
        writeCRLF();
    }

    /**
     * This Java function writes a string to an output stream in bulk, including its
     * length and a carriage return line feed.
     * 
     * @param content The parameter "content" is an object of type ContentRESP,
     *                which contains the
     *                content to be written as a string to an output stream.
     * @throws Exception
     */
    private void writeStringBulk(ContentRESP content) throws Exception {
        String contentString = (String) content.getContent();

        writeData(contentString == null ? "-1" : Integer.toString(contentString.length()));
        writeCRLF();

        if (contentString != null) {
            writeData(contentString);
            writeCRLF();
        }
    }

    /**
     * The function writes an array of ContentRESP objects to an output stream.
     * 
     * @param content The parameter "content" is an object of type ContentRESP,
     *                which contains an array
     *                of ContentRESP objects.
     */
    private void writeArray(ContentRESP content) throws Exception {
        ContentRESP[] contentArray = (ContentRESP[]) content.getContent();

        writeData(contentArray == null ? "-1" : Integer.toString(contentArray.length));
        writeCRLF();

        if (contentArray != null) {
            for (int i = 0; i < contentArray.length; i++) {
                write(contentArray[i]);
            }
        }
    }
}