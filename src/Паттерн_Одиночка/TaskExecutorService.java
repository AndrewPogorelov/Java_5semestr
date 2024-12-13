// 7 ВАРИАНТ. Создайте "Сервис управления потоками" для выполнения асинхронных задач в приложении.
package Паттерн_Одиночка;

import java.util.concurrent.*;

public class TaskExecutorService {

    // Объект для работы с пулом потоков
    private final ExecutorService executorService;

    public TaskExecutorService(int numberOfThreads) {
        // Создаём пул потоков с фиксированным количеством потоков
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    // Метод для выполнения задачи асинхронно
    public <T> Future<T> executeTask(Callable<T> task) {
        // Передаем задачу в пул потоков и возвращаем объект Future
        return executorService.submit(task);
    }

    // Метод для завершения работы сервиса и остановки всех потоков
    public void shutdown() {
        executorService.shutdown();  // Ожидаем завершения всех задач
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();  // Принудительное завершение, если задачи не завершились
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();  // Прерывание при ожидании
        }
    }

    public static void main(String[] args) {
        // Создаем сервис с 3 потоками в пуле
        TaskExecutorService taskExecutor = new TaskExecutorService(3);

        Callable<String> task = () -> {
            Thread.sleep(2000);  // Имитируем задержку
            return "Задача завершена";
        };

        Future<String> result = taskExecutor.executeTask(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        taskExecutor.shutdown();
    }
}

