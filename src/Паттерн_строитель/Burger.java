// 7 ВАРИАНТ. Реализуйте паттерн "Строитель" для класса "Бургер" (Burger),
// позволяющий настраивать ингредиенты, размер порции и добавки.

package Паттерн_строитель;

// Класс, представляющий сам бургер
public class Burger {
    private String bread; // Тип хлеба
    private String meat;  // Тип мяса
    private String cheese; // Вид сыра
    private boolean hasLettuce; // Наличие салата
    private boolean hasTomato; // Наличие помидора
    private String size; // Размер порции

    // Конструктор, который будет использоваться в Строителе
    private Burger(BurgerBuilder builder) {
        this.bread = builder.bread;
        this.meat = builder.meat;
        this.cheese = builder.cheese;
        this.hasLettuce = builder.hasLettuce;
        this.hasTomato = builder.hasTomato;
        this.size = builder.size;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "bread='" + bread + '\'' +
                ", meat='" + meat + '\'' +
                ", cheese='" + cheese + '\'' +
                ", hasLettuce=" + hasLettuce +
                ", hasTomato=" + hasTomato +
                ", size='" + size + '\'' +
                '}';
    }

    // Статический внутренний класс Builder (строитель)
    public static class BurgerBuilder {
        private String bread; // Тип хлеба
        private String meat;  // Тип мяса
        private String cheese; // Вид сыра
        private boolean hasLettuce; // Наличие салата
        private boolean hasTomato; // Наличие помидора
        private String size; // Размер порции

        // Устанавливаем хлеб
        public BurgerBuilder bread(String bread) {
            this.bread = bread;
            return this;
        }

        // Устанавливаем мясо
        public BurgerBuilder meat(String meat) {
            this.meat = meat;
            return this;
        }

        // Устанавливаем сыр
        public BurgerBuilder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        // Добавляем салат
        public BurgerBuilder lettuce(boolean hasLettuce) {
            this.hasLettuce = hasLettuce;
            return this;
        }

        // Добавляем помидор
        public BurgerBuilder tomato(boolean hasTomato) {
            this.hasTomato = hasTomato;
            return this;
        }

        // Устанавливаем размер порции
        public BurgerBuilder size(String size) {
            this.size = size;
            return this;
        }

        // Метод для создания объекта Burger
        public Burger build() {
            return new Burger(this);
        }
    }
}

