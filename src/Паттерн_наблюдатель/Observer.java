// 7 ВАРИАНТ. Напишите систему для отслеживания изменений в онлайн-новостях, где пользователи могут подписаться
// на уведомления о новых статьях по определенным темам.
package Паттерн_наблюдатель;

import java.util.*;

// Класс, представляющий одну новостную статью
class Article {
    private String title;
    private String content;
    private String topic;

    public Article(String title, String content, String topic) {
        this.title = title;
        this.content = content;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTopic() {
        return topic;
    }
}

// Интерфейс для уведомления пользователей о новых статьях
interface Subscriber {
    void notify(Article article);
}

// Класс, представляющий пользователя, подписанного на уведомления
class User implements Subscriber {
    private String name;
    private List<String> subscribedTopics;

    public User(String name) {
        this.name = name;
        this.subscribedTopics = new ArrayList<>();
    }

    // Метод подписки на тему
    public void subscribeToTopic(String topic) {
        if (!subscribedTopics.contains(topic)) {
            subscribedTopics.add(topic);
        }
    }

    // Метод отписки от темы
    public void unsubscribeFromTopic(String topic) {
        subscribedTopics.remove(topic);
    }

    @Override
    public void notify(Article article) {
        if (subscribedTopics.contains(article.getTopic())) {
            System.out.println("Уведомление для " + name + ": Новая статья по теме \"" + article.getTopic() + "\": " + article.getTitle());
        }
    }
}

// Класс, представляющий систему отслеживания новостей
class NewsTracker {
    private List<Article> articles;
    private Map<String, List<Subscriber>> topicSubscribers;

    public NewsTracker() {
        this.articles = new ArrayList<>();
        this.topicSubscribers = new HashMap<>();
    }

    // Метод добавления новой статьи
    public void addArticle(Article article) {
        articles.add(article);
        notifySubscribers(article);
    }

    // Метод подписки пользователя на тему
    public void subscribe(String topic, Subscriber subscriber) {
        topicSubscribers.putIfAbsent(topic, new ArrayList<>());
        topicSubscribers.get(topic).add(subscriber);
    }

    // Метод отписки пользователя от темы
    public void unsubscribe(String topic, Subscriber subscriber) {
        List<Subscriber> subscribers = topicSubscribers.get(topic);
        if (subscribers != null) {
            subscribers.remove(subscriber);
        }
    }

    // Уведомление подписчиков о новой статье
    private void notifySubscribers(Article article) {
        List<Subscriber> subscribers = topicSubscribers.get(article.getTopic());
        if (subscribers != null) {
            for (Subscriber subscriber : subscribers) {
                subscriber.notify(article);
            }
        }
    }
}

// Основной класс для тестирования системы
public class Observer {
    public static void main(String[] args) {
        // Создаем систему отслеживания новостей
        NewsTracker newsTracker = new NewsTracker();

        // Создаем пользователей
        User user1 = new User("Алексей");
        User user2 = new User("Мария");

        // Пользователи подписываются на темы
        user1.subscribeToTopic("Технологии");
        user2.subscribeToTopic("Спорт");

        // Регистрируем подписчиков в системе
        newsTracker.subscribe("Технологии", user1);
        newsTracker.subscribe("Спорт", user2);

        // Добавляем новые статьи
        newsTracker.addArticle(new Article("Новый смартфон", "Описание нового смартфона.", "Технологии"));
        newsTracker.addArticle(new Article("Футбольный матч", "Результаты последнего матча.", "Спорт"));
    }
}

