import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static final long KYLO_BYTE = 1_048;
    private static final long MEGA_BYTE = KYLO_BYTE * 1024;
    private static final long GYGA_BYTE = MEGA_BYTE * 1024;
    private static final long TERA_BYTE = GYGA_BYTE * 1024;
    private static HashMap<Character, Integer>
            char2multiplier = getMultipliers();


    public static String getHumanReadableSize(long size) {
//        //TODO это я писал и в теории оно работало, но по факту выдает
//        //TODO некорректные данные с округлением. Надеюсь ты вырастешь
//        //TODO умным программистом, который это поймет и исправит. На дворе
//        //TODO 3 часа ночи, я устал и не могу найти способ это исправить.
//
//
//        StringBuilder builder = new StringBuilder();
//        if (size <= KYLO_BYTE && size > 0) {
//            builder.append(size).append("B");
//        }
//        if (size >= KYLO_BYTE && size <= MEGA_BYTE) {
//            size = Math.round(size) / KYLO_BYTE;
//            builder.append(size).append("Kb");
//        }
//        if (size >= MEGA_BYTE && size <= GYGA_BYTE) {
//            size = size / MEGA_BYTE;
//            builder.append(size).append("Mb");
//        }
//        if (size >= GYGA_BYTE && size <= TERA_BYTE) {
//            size = size / GYGA_BYTE;
//            builder.append(size).append("Gb");
//        }
//        return builder.toString();


        //TODO метод Пилипенко

        for (int i = 0; i < sizeMultipliers.length; i++) {
            double value = ((double) size) / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value *100)/100. + " " + sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Very big";
    }

    public static long getSizeFromHumanReadable(String size) {
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(
                size.replaceAll("[^0-9]", "")
        );
        return length;
    }

    private static HashMap<Character, Integer> getMultipliers() {

        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++) {
            char2multiplier.put(
                    sizeMultipliers[i],
                    (int) Math.pow(1024, i)
            );
        }
        return char2multiplier;
    }
}
