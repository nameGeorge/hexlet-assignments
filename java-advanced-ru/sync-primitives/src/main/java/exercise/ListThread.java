package exercise;

// BEGIN
public class ListThread extends Thread {
    SafetyList numbers;

    public ListThread(SafetyList numbers) {
        this.numbers = numbers;
    }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.numbers.add(i);
            }
        }


}
// END
