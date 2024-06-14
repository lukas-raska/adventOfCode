package year_2015.day_4;

import javax.xml.crypto.AlgorithmMethod;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmConstraints;
import java.security.AlgorithmParameterGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    private static final String INPUT = "yzbqklnj";
    private static final String TEST_1 = "abcdef";
    private static final String TEST_2 = "pqrstuv";


    public static void main(String[] args) throws NoSuchAlgorithmException {

        //282750 --> too high

        //test
//        System.out.println("Test 1 :");
//        System.out.println("--> secret key : " + TEST_1);
//        System.out.printf("--> number : " + getNumber(TEST_1));
//        System.out.println("\nTest 2 :");
//        System.out.println("--> secret key : " + TEST_2);
//        System.out.printf("--> number : " + getNumber(TEST_2));


        //solution
        System.out.println("The answer of the day 4:");
        System.out.println("\tPart one : " + getNumber(INPUT, "00000"));
        System.out.println("\tPart two : " + getNumber(INPUT, "000000"));


    }

    private static int getNumber(String input,
                                 String hashPrefix) throws NoSuchAlgorithmException {
        String hash = "";
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            hash = getMD5Hash(input + i);
            if (hash.startsWith(hashPrefix)) {
                return i;
            }
        }
        return -1;
    }

    private static String getMD5Hash(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append("%02x".formatted(b));
        }
        return sb.toString();
    }
}
