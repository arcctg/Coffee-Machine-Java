package machine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class old_CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[]{400, 540, 120, 9, 550};
        String action = "";
        boolean go = true;

        while (go) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");

            action = scanner.nextLine();

            switch (action) {
                case "buy" -> buy(arr);
                case "fill" -> fill(arr);
                case "take" -> take(arr);
                case "remaining" -> print(arr);
                case "exit" -> go = false;
            }
        }
    }

    public static void print(int[] arr) {
        System.out.printf("""
                        
                        The coffee machine has:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money
                        
                        """,
                arr[0], arr[1], arr[2], arr[3], arr[4]);
    }

    public static void buy(int[] arr) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String line = scanner.nextLine();

        if (line.contains("back")) {
            System.out.println();
            return;
        }

        System.out.println(check_make(arr, Integer.parseInt(line)));
    }

    public static void fill(int[] arr) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nWrite how many ml of water you want to add:");
        arr[0] += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        arr[1] += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        arr[2] += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        arr[3] += scanner.nextInt();

        System.out.println();
    }

    public static void take(int[] arr) {
        System.out.printf("\nI gave you $%s\n\n", arr[4]);
        arr[4] = 0;
    }

    public static String check_make(int[] arr, int coffee) {
        int[] arr1 = arr.clone();
        List<String> list = List.of("water", "milk", "coffee beans", "disposable cups");

        switch (coffee) {
            case 1 -> {
                arr1[0] -= 250;
                arr1[2] -= 16;
                arr1[3]--;
                arr1[4] += 4;
            }
            case 2 -> {
                arr1[0] -= 350;
                arr1[1] -= 75;
                arr1[2] -= 20;
                arr1[3]--;
                arr1[4] += 7;
            }
            case 3 -> {
                arr1[0] -= 200;
                arr1[1] -= 100;
                arr1[2] -= 12;
                arr1[3]--;
                arr1[4] += 6;
            }
        }

        if (Arrays.stream(arr1).allMatch(i -> i >= 0)) {
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
            
            return "I have enough resources, making you a coffee!\n";
        } else {
            StringBuilder not = new StringBuilder();

            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] < 0) {
                    not.append(not.length() != 0 ? ", " + list.get(i) : list.get(i));
                }
            }

            return String.format("Sorry, not enough %s!\n", not);
        }
    }
}