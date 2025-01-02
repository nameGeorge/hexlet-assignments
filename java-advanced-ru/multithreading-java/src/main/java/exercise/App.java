package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");


    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);
        minThread.start();
        maxThread.start();
        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
        return Map.of("min", minThread.min, "max", maxThread.max);
    }
    // END
}
