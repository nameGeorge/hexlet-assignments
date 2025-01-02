package exercise;


// BEGIN
    public class MinThread extends Thread {
        int[] numbers;
        int min;

        public MinThread(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
             this.min = numbers[0];
            for (int i = 0; i < numbers.length; i++) {
                if (this.min > numbers[i]) {
                    this.min = numbers[i];
                }
            }
        }
}
// END
