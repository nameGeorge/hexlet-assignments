package exercise;

import java.util.List;
import java.util.ArrayList;

class SafetyList {

    private List<Integer> numbers = new ArrayList<>();

    // BEGIN
    public synchronized void add(int value) {
        this.numbers.add(value);
    }

    public int get(int index) {
        return this.numbers.get(index);
    }

    public int getSize() {
        return this.numbers.size();
    }
    // END
}
