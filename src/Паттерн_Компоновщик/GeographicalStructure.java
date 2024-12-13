// 7 ВАРИАНТ. Создайте систему управления географическими объектами по паттерну "Компоновщик",
// такими как страны, регионы и города, объединяя их в иерархическую структуру.
package Паттерн_Компоновщик;

import java.util.ArrayList;
import java.util.List;

// Абстрактный класс для географических объектов
abstract class GeographicalObject {
    protected String name;

    public GeographicalObject(String name) {
        this.name = name;
    }

    public abstract void displayInfo(String indent);
}

class Country extends GeographicalObject {
    public Country(String name) {
        super(name);
    }

    @Override
    public void displayInfo(String indent) {
        System.out.println(indent + "Страна: " + name);
    }
}

class Region extends GeographicalObject {
    private List<GeographicalObject> cities = new ArrayList<>();

    public Region(String name) {
        super(name);
    }

    public void addCity(GeographicalObject city) {
        cities.add(city);
    }

    @Override
    public void displayInfo(String indent) {
        System.out.println(indent + "Регион: " + name);
        for (GeographicalObject city : cities) {
            city.displayInfo(indent + "  "); // Отступ для городов
        }
    }
}

class City extends GeographicalObject {
    public City(String name) {
        super(name);
    }

    @Override
    public void displayInfo(String indent) {
        System.out.println(indent + "Город: " + name);
    }
}

public class GeographicalStructure {
    public static void main(String[] args) {

        GeographicalObject city1 = new City("Москва");
        GeographicalObject city2 = new City("Воронеж");

        GeographicalObject region = new Region("Центральный регион");
        ((Region) region).addCity(city1);
        ((Region) region).addCity(city2);

        GeographicalObject country = new Country("Россия");
        ((Country) country).displayInfo("");
        ((Region) region).displayInfo("  ");
    }
}

