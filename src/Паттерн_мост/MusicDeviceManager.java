// 7 ВАРИАНТ. Напишите программу для управления разными типами музыкальных устройств (плееры, колонки, наушники)
// с использованием паттерна "мост".
package Паттерн_мост;

// Интерфейс для реализации (Implementation)
interface DeviceImplementation {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

// Конкретная реализация для колонок
class Speaker implements DeviceImplementation {
    @Override
    public void turnOn() {
        System.out.println("Колонки включены.");
    }

    @Override
    public void turnOff() {
        System.out.println("Колонки выключены.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Громкость колонок установлена на: " + volume);
    }
}

// Конкретная реализация для наушников
class Headphones implements DeviceImplementation {
    @Override
    public void turnOn() {
        System.out.println("Наушники включены.");
    }

    @Override
    public void turnOff() {
        System.out.println("Наушники выключены.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Громкость наушников установлена на: " + volume);
    }
}

// Конкретная реализация для музыкальных плееров
class MusicPlayer implements DeviceImplementation {
    @Override
    public void turnOn() {
        System.out.println("Музыкальный плеер включён.");
    }

    @Override
    public void turnOff() {
        System.out.println("Музыкальный плеер выключен.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Громкость плеера установлена на: " + volume);
    }
}

// Абстракция (Abstraction)
abstract class DeviceController {
    protected DeviceImplementation device;

    public DeviceController(DeviceImplementation device) {
        this.device = device;
    }

    public abstract void powerOn();
    public abstract void powerOff();
    public abstract void adjustVolume(int volume);
}

// Расширенная абстракция
class AdvancedDeviceController extends DeviceController {

    public AdvancedDeviceController(DeviceImplementation device) {
        super(device);
    }

    @Override
    public void powerOn() {
        device.turnOn();
    }

    @Override
    public void powerOff() {
        device.turnOff();
    }

    @Override
    public void adjustVolume(int volume) {
        device.setVolume(volume);
    }
}

public class MusicDeviceManager {
    public static void main(String[] args) {
        // Создаем устройства
        DeviceImplementation speaker = new Speaker();
        DeviceImplementation headphones = new Headphones();
        DeviceImplementation musicPlayer = new MusicPlayer();

        // Создаем контроллеры
        DeviceController speakerController = new AdvancedDeviceController(speaker);
        DeviceController headphonesController = new AdvancedDeviceController(headphones);
        DeviceController playerController = new AdvancedDeviceController(musicPlayer);

        // Управляем устройствами
        speakerController.powerOn();
        speakerController.adjustVolume(30);
        speakerController.powerOff();

        headphonesController.powerOn();
        headphonesController.adjustVolume(50);
        headphonesController.powerOff();

        playerController.powerOn();
        playerController.adjustVolume(70);
        playerController.powerOff();
    }
}

