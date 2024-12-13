package Паттерн_строитель;

public class Main {
    public static void main(String[] args) {
        // Строим бургер с помощью билдера
        Burger burger = new Burger.BurgerBuilder()
                .bread("Черный хлеб")
                .meat("Курица")
                .cheese("Чеддер")
                .lettuce(true)
                .tomato(true)
                .size("Большой")
                .build();

        System.out.println(burger);
    }
}
