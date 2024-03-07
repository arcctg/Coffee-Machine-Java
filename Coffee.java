package machine;

import java.util.Arrays;
import java.util.List;

public class Coffee {
    protected int water;
    protected int milk;
    protected int beans;
    protected int price;

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getPrice() {
        return price;
    }

    public String make(CoffeeMachine machine) {
        int[] arr = machine.getAll();
        List<String> list = List.of("water", "milk", "coffee beans", "disposable cups");

        arr[0] -= water;
        arr[1] -= milk;
        arr[2] -= beans;
        arr[3]--;
        arr[4] += price;

        if (Arrays.stream(arr).allMatch(i -> i >= 0)) {
            machine.makeCoffee(this);
            return "I have enough resources, making you a coffee!\n";
        } else {
            StringBuilder not = new StringBuilder();

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 0) {
                    not.append(!not.isEmpty() ? ", " + list.get(i) : list.get(i));
                }
            }

            return String.format("Sorry, not enough %s!\n", not);
        }
    }
}
