// 7 ВАРИАНТ.Создайте приложение для управления задачами,
// которое позволяет сохранять и восстанавливать список задач и их статус выполнения.
package Паттерн_снимок;

import java.io.*;
import java.util.*;

public class TaskManager {

    private static List<Task> tasks = new ArrayList<>();

    static class Task {
        private String description; // Описание задачи
        private boolean isCompleted; // Статус выполнения задачи

        public Task(String description) {
            this.description = description;
            this.isCompleted = false;
        }

        // Метод для получения описания задачи
        public String getDescription() {
            return description;
        }

        // Метод для установки статуса задачи как выполненной
        public void markAsCompleted() {
            this.isCompleted = true;
        }

        // Метод для получения статуса задачи
        public boolean isCompleted() {
            return isCompleted;
        }

        // Метод для представления задачи в виде строки
        @Override
        public String toString() {
            return description + (isCompleted ? " (Выполнена)" : " (Не выполнена)");
        }
    }

    // Метод для добавления новой задачи в список
    public static void addTask(String description) {
        tasks.add(new Task(description));
    }

    // Метод для изменения статуса задачи
    public static void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
        } else {
            System.out.println("Задача с таким индексом не найдена!");
        }
    }

    // Метод для отображения всех задач
    public static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i));
            }
        }
    }

    // Метод для сохранения списка задач в файл
    public static void saveTasksToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    // Метод для загрузки списка задач из файла
    public static void loadTasksFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadTasksFromFile("tasks.dat"); // Загружаем задачи из файла при старте

        addTask("Сделать домашку");
        addTask("Купить продукты");

        showTasks();

        markTaskAsCompleted(0);

        showTasks();

        saveTasksToFile("tasks.dat");
    }
}

