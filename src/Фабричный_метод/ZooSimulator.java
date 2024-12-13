// 7 ВАРИАНТ. Симулятор зоопарка с использованием фабричного метода
package Фабричный_метод;

import java.util.*;

// Интерфейс One.Animal представляет животное
interface Animal {
    void makeSound(); // Метод, который будет вызывать звуки животных
    String getName(); // Получение имени животного
}

// Класс One.Lion (Лев) реализует интерфейс One.Animal
class Lion implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Рррр! Я лев.");
    }

    @Override
    public String getName() {
        return "Лев";
    }
}

// Класс One.Elephant (Слон)
class Elephant implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Уууу! Я слон.");
    }

    @Override
    public String getName() {
        return "Слон";
    }
}

// Класс One.Monkey (Обезьяна)
class Monkey implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Ух-ух-ах-ах! Я обезьяна.");
    }

    @Override
    public String getName() {
        return "Обезьяна";
    }
}

// Фабрика животных
class AnimalFactory {
    // Фабричный метод для создания животных
    public static Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "лев":
                return new Lion();
            case "слон":
                return new Elephant();
            case "обезьяна":
                return new Monkey();
            default:
                throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        }
    }
}

// Главный класс симулятора зоопарка
public class ZooSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> zoo = new ArrayList<>(); // Список для хранения животных

        System.out.println("Добро пожаловать в симулятор зоопарка!");
        System.out.println("Введите команду: добавить (тип животного) / показать / выйти");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            if (parts[0].equalsIgnoreCase("добавить") && parts.length == 2) {
                try {
                    Animal animal = AnimalFactory.createAnimal(parts[1]);
                    zoo.add(animal);
                    System.out.println(parts[1] + " добавлен в зоопарк.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts[0].equalsIgnoreCase("показать")) {
                if (zoo.isEmpty()) {
                    System.out.println("Зоопарк пуст.");
                } else {
                    System.out.println("В зоопарке находятся:");
                    for (Animal animal : zoo) {
                        System.out.println("- " + animal.getName());
                        animal.makeSound();
                    }
                }
            } else if (parts[0].equalsIgnoreCase("выйти")) {
                System.out.println("Выход из симулятора зоопарка. До свидания!");
                break;
            } else {
                System.out.println("Неизвестная команда. Попробуйте ещё раз.");
            }
        }

        scanner.close();
    }
}
