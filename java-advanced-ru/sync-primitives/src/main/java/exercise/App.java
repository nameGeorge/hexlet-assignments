package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList numbers = new SafetyList();

        ListThread listThread = new ListThread(numbers);
        ListThread listThread2 = new ListThread(numbers);

        listThread.start();
        listThread2.start();
        try {
            listThread.join();
            listThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(numbers.getSize());

        // END
    }
}

