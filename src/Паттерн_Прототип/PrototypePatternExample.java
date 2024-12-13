// 7 ВАРИАНТ. Создайте систему на основании паттерна "Прототип" управления элементами интерфейса,
// позволяющую клонировать элементы для создания пользовательских шаблонов.
package Паттерн_Прототип;

import java.util.HashMap;
import java.util.Map;

// Интерфейс прототипа
interface GUIElement extends Cloneable {
    GUIElement clone();
    void draw();
}

// Конкретные элементы интерфейса
class Button implements GUIElement {
    private String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public GUIElement clone() {
        return new Button(this.label);
    }

    @Override
    public void draw() {
        System.out.println("Button: " + label);
    }
}

class TextField implements GUIElement {
    private String text;

    public TextField(String text) {
        this.text = text;
    }

    @Override
    public GUIElement clone() {
        return new TextField(this.text);
    }

    @Override
    public void draw() {
        System.out.println("TextField: " + text);
    }
}

// Менеджер прототипов, который управляет клонированием
class GUIElementManager {
    private Map<String, GUIElement> prototypes = new HashMap<>();

    public void addPrototype(String key, GUIElement element) {
        prototypes.put(key, element);
    }

    public GUIElement getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}

public class PrototypePatternExample {
    public static void main(String[] args) {
        GUIElementManager manager = new GUIElementManager();

        manager.addPrototype("button", new Button("Submit"));
        manager.addPrototype("textField", new TextField("Enter name"));

        GUIElement button1 = manager.getPrototype("button");
        button1.draw();

        GUIElement textField1 = manager.getPrototype("textField");
        textField1.draw();

        GUIElement button2 = manager.getPrototype("button");
        button2.draw();

        GUIElement textField2 = manager.getPrototype("textField");
        textField2.draw();
    }
}

