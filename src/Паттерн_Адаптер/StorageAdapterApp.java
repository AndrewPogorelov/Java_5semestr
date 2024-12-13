// 7 ВАРИАНТ. Создайте приложение для чтения и записи данных на различные типы внешних устройств хранения
// (USB-накопители, SD-карты) с использованием адаптеров.
package Паттерн_Адаптер;

import java.io.*;
import java.util.*;

// Интерфейс для работы с внешними устройствами хранения
interface StorageDevice {
    void writeData(String data) throws IOException;
    String readData() throws IOException;
}

// Реализация для USB-накопителей
class USBDrive implements StorageDevice {
    private File file;

    public USBDrive(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void writeData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        }
    }

    @Override
    public String readData() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}

// Реализация для SD-карт
class SDCard implements StorageDevice {
    private File file;

    public SDCard(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void writeData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        }
    }

    @Override
    public String readData() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}

// Главный класс приложения
public class StorageAdapterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите устройство хранения:");
        System.out.println("1. USB-накопитель");
        System.out.println("2. SD-карта");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        System.out.println("Введите путь к файлу на устройстве:");
        String filePath = scanner.nextLine();

        StorageDevice device;

        switch (choice) {
            case 1:
                device = new USBDrive(filePath);
                break;
            case 2:
                device = new SDCard(filePath);
                break;
            default:
                System.out.println("Неверный выбор устройства.");
                return;
        }

        System.out.println("Выберите действие:");
        System.out.println("1. Записать данные");
        System.out.println("2. Прочитать данные");
        int action = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        try {
            switch (action) {
                case 1:
                    System.out.println("Введите данные для записи:");
                    String data = scanner.nextLine();
                    device.writeData(data);
                    System.out.println("Данные успешно записаны.");
                    break;
                case 2:
                    String content = device.readData();
                    System.out.println("Содержимое файла:");
                    System.out.println(content);
                    break;
                default:
                    System.out.println("Неверный выбор действия.");
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}

