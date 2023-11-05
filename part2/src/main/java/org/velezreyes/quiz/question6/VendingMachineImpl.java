package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private static VendingMachineImpl instance;

    private Map<String, Drink> drinks = new HashMap<>();
    private int balance = 0;

    private VendingMachineImpl() {
        drinks.put("ScottCola", new DrinkImpl("ScottCola", true));
        drinks.put("KarenTea", new DrinkImpl("KarenTea", false));
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachineImpl();
        }
        return instance;
    }

    @Override
    public void insertQuarter() {
        balance += 25;
    }

    @Override
    public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
        if (!drinks.containsKey(name)) {
            throw new UnknownDrinkException();
        }

        Drink selectedDrink = drinks.get(name);
        if (balance < 75) {
            throw new NotEnoughMoneyException();
        }

        balance -= 75;
        return selectedDrink;
    }

    private class DrinkImpl implements Drink {
        private String name;
        private boolean fizzy;

        public DrinkImpl(String name, boolean fizzy) {
            this.name = name;
            this.fizzy = fizzy;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isFizzy() {
            return fizzy;
        }
    }
}
