package miage.reseau.projet.resp;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import miage.reseau.projet.utils.LogBuilder;

/**
 * The SReaderRESP class reads data from an input stream in the RESP protocol
 * and returns a ContentRESP object containing the type and content of the data.
 */
public class SReaderRESP {

    private final BufferedInputStream inputStream;

    public SReaderRESP(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
    }

    /**
     * This Java function reads data from an input stream and returns a ContentRESP
     * object containing the type and content of the data.
     * 
     * @return The method `readData()` is returning an object of type `ContentRESP`.
     */
    public ContentRESP readData() throws Exception {

        ContentRESP content = new ContentRESP();
        content.setType(TypeRESP.getTypeFromChar((char) inputStream.read()));

        TypeRESP type = content.getType();

        if (type == TypeRESP.STRING || type == TypeRESP.ERROR) {
            content.setContent(readTextUntilCRLF());
        } else if (type == TypeRESP.STRING_BULK) {
            readStringBulk(content);
        } else if (type == TypeRESP.INT) {
            content.setContent(Integer.parseInt(readTextUntilCRLF()));
        } else if (type == TypeRESP.ARRAY) {
            readArray(content);
        } else {
            throw new Exception(LogBuilder.getInvalidInputMessage());
        }

        return content;
    }

    /**
     * This function reads two bytes from an input stream and checks if they
     * represent a CRLF (carriage return and line feed) sequence.
     */
    private void readCRLF() throws Exception {

        byte[] bytes = inputStream.readNBytes(2);

        for (int i = 0; i < bytes.length; i++) {
            byte data = bytes[i];
            if ((char) data != '\n' && (char) data != '\r') {
                throw new Exception("Expected CRLF but found: " + (char) data);
            }
        }
    }

    /**
     * This function reads text from an input stream until it reaches a carriage
     * return and line feed character and returns the text as a string.
     * 
     * @return The method is returning a String that contains all the characters
     *         read from the input stream until a carriage return character is
     *         encountered. The carriage return character is not included in the
     *         returned String.
     */
    private String readTextUntilCRLF() throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int data;
        while ((data = inputStream.read()) != -1 && data != '\r') {
            outputStream.write(data);
        }
        inputStream.read();

        return outputStream.toString();
    }

    /**
     * This Java function reads an array of ContentRESP objects from a stream.
     * 
     * @param content an object of type ContentRESP, which contains the content to
     *                be read into an array.
     */
    private void readArray(ContentRESP content) throws Exception {

        String lengthString = readTextUntilCRLF();
        int length = lengthString.equals("-1") ? -1 : Integer.parseInt(lengthString);

        if (length == -1) {
            content.setContent(null);
        } else {
            ContentRESP[] contentArray = new ContentRESP[length];

            for (int i = 0; i < length; i++) {
                contentArray[i] = readData();
            }

            content.setContent(contentArray);
        }
    }

    private void readStringBulk(ContentRESP content) {
    }
}