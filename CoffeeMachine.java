package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    private final Scanner scanner = new Scanner(System.in);

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public int[] getAll() {
        return new int[]{water, milk, beans, cups, money};
    }

    public void work() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");

            switch (scanner.nextLine()) {
                case "buy" -> buy();
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> print();
                case "exit" -> System.exit(1);
            }
        }
    }

    public void buy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String line = scanner.nextLine();

        if (line.contains("back")) {
            System.out.println();
            return;
        }

        Coffee coffee;

        switch (Integer.parseInt(line)) {
            case 1 -> coffee = new Espresso();
            case 2 -> coffee = new Latte();
            case 3 -> coffee = new Cappuccino();
            default -> coffee = new Coffee();
        }

        System.out.println(coffee.make(this));
    }

    public void fill() {
        System.out.println("\nWrite how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cups += scanner.nextInt();

        scanner.nextLine();
        System.out.println();
    }

    public void take() {
        System.out.printf("\nI gave you $%s\n\n", money);
        money = 0;
    }

    public void print() {
        System.out.printf("""
                        
                        The coffee machine has:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money
                        
                        """,
                water, milk, beans, cups, money);
    }

    public void makeCoffee(Coffee coffee) {
        water -= coffee.getWater();
        milk -= coffee.getMilk();
        beans -= coffee.getBeans();
        cups--;
        money += coffee.getPrice();
    }
}
