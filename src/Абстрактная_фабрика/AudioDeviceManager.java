// 7 ВАРИАНТ. Создайте приложение для управления разными типами аудиоустройств
// (наушники, колонки, микрофоны) с использованием абстрактной фабрики.
package Абстрактная_фабрика;

// Абстрактный продукт для аудиоустройств
interface AudioDevice {
    void play(); // Метод воспроизведения звука
    void configure(); // Метод настройки устройства
}

// Конкретный продукт: Наушники
class Headphones implements AudioDevice {
    @Override
    public void play() {
        System.out.println("Воспроизведение через наушники.");
    }

    @Override
    public void configure() {
        System.out.println("Настройка наушников.");
    }
}

// Конкретный продукт: Колонки
class Speakers implements AudioDevice {
    @Override
    public void play() {
        System.out.println("Воспроизведение через колонки.");
    }

    @Override
    public void configure() {
        System.out.println("Настройка колонок.");
    }
}

// Конкретный продукт: Микрофоны
class Microphone implements AudioDevice {
    @Override
    public void play() {
        System.out.println("Микрофон не поддерживает воспроизведение.");
    }

    @Override
    public void configure() {
        System.out.println("Настройка микрофона.");
    }
}

// Абстрактная фабрика для создания аудиоустройств
interface AudioDeviceFactory {
    AudioDevice createDevice(); // Метод создания устройства
}

// Конкретная фабрика для создания наушников
class HeadphonesFactory implements AudioDeviceFactory {
    @Override
    public AudioDevice createDevice() {
        return new Headphones();
    }
}

// Конкретная фабрика для создания колонок
class SpeakersFactory implements AudioDeviceFactory {
    @Override
    public AudioDevice createDevice() {
        return new Speakers();
    }
}

// Конкретная фабрика для создания микрофонов
class MicrophoneFactory implements AudioDeviceFactory {
    @Override
    public AudioDevice createDevice() {
        return new Microphone();
    }
}

// Приложение для управления аудиоустройствами
public class AudioDeviceManager {
    private AudioDeviceFactory factory;

    // Конструктор принимает конкретную фабрику
    public AudioDeviceManager(AudioDeviceFactory factory) {
        this.factory = factory;
    }

    // Метод для работы с устройством
    public void manageDevice() {
        AudioDevice device = factory.createDevice();
        device.configure();
        device.play();
    }

    public static void main(String[] args) {
        // Управление наушниками
        AudioDeviceManager headphonesManager = new AudioDeviceManager(new HeadphonesFactory());
        headphonesManager.manageDevice();

        // Управление колонками
        AudioDeviceManager speakersManager = new AudioDeviceManager(new SpeakersFactory());
        speakersManager.manageDevice();

        // Управление микрофоном
        AudioDeviceManager microphoneManager = new AudioDeviceManager(new MicrophoneFactory());
        microphoneManager.manageDevice();
    }
}
