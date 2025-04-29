package utils;

public class UserFunctions {

    public static String getTimeFromShift(int shift){
        return switch (shift) {
            case 0 -> "00:00-08:00";
            case 1 -> "08:00-16:00";
            case 2 -> "16:00-24:00";
            default -> "00:00-00:00";
        };
    }
}
