package year_2015.day_7;

public class BitwiseUtils {

    public static int not(int signal) {
        return ~signal & 0xFFFF;
    }

    public static int and(int signal1, int signal2){
        return (signal1 & signal2) & 0xFFFF;
    }

    public static int or(int signal1, int signal2){
        return (signal1 | signal2) & 0xFFFF;
    }

    public static int leftShift(int signal, int shift){
        return (signal << shift) & 0xFFFF;
    }

    public static int rightShift (int signal, int shift){
        return (signal >> shift) & 0xFFFF;
    }

}
