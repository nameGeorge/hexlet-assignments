package exercise;

import java.lang.Thread;

// BEGIN
public class MaxThread extends Thread {
    int[] numbers;
    int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        this.max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (this.max < numbers[i]) {
                this.max = numbers[i];
            }
        }
    }
}
// END
