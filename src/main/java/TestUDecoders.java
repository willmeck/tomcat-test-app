import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.buf.EncodedSolidusHandling;
import org.apache.tomcat.util.buf.UDecoder;

import java.io.IOException;

public class TestUDecoders {

    public static void main (String[] args) throws IOException {
        String testString = "abc%2Bdef%2Bghi%2Fjkl";
        String expectedResult = "abc+def+ghi%2Fjkl";
        ByteChunk bc1 = new ByteChunk();
        bc1.append(testString.getBytes(), 0, testString.getBytes().length);
        ByteChunk bc2 = new ByteChunk();
        bc2.append(testString.getBytes(), 0, testString.getBytes().length);

        UDecoder uDecoder = new UDecoder();
        uDecoder.convert(bc1, EncodedSolidusHandling.PASS_THROUGH);

        UDecoderFixed uDecoderFixed = new UDecoderFixed();
        uDecoderFixed.convert(bc2, EncodedSolidusHandling.PASS_THROUGH);

        System.out.println("unfixed result: " + bc1.toString());
        System.out.println("Is unfixed result expected? " + bc1.toString().equalsIgnoreCase(expectedResult));

        System.out.println("fixed result: " + bc2.toString());
        System.out.println("Is fixed result expected? " + bc2.toString().equalsIgnoreCase(expectedResult));
    }
}
