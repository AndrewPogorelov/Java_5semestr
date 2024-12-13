// 7 ВАРИАНТ. Напишите систему для обработки изображений,
// где разные фильтры (черно-белый, сепия, резкость) используют общий шаблонный метод.
package Шаблонный_Метод;

import java.awt.*;
import java.awt.image.BufferedImage;

// Абстрактный класс для фильтров изображений
abstract class ImageFilter {
    // Шаблонный метод, который применяет фильтр
    public final BufferedImage applyFilter(BufferedImage image) {
        // Шаг 1: Создаем копию изображения для обработки
        BufferedImage filteredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        // Шаг 2: Применяем фильтр (общая логика, которая применяется ко всем фильтрам)
        applyFilterLogic(image, filteredImage);

        // Шаг 3: Дополнительная обработка, специфичная для конкретного фильтра
        customFilterLogic(filteredImage);

        return filteredImage;
    }

    // Шаблонный метод вызывает этот метод для реализации основной логики фильтра
    protected abstract void applyFilterLogic(BufferedImage original, BufferedImage filtered);

    // Этот метод будет специфичен для каждого фильтра, в нем могут быть дополнительные шаги
    protected abstract void customFilterLogic(BufferedImage filteredImage);
}

// Конкретный фильтр: Черно-белый
class BlackAndWhiteFilter extends ImageFilter {
    @Override
    protected void applyFilterLogic(BufferedImage original, BufferedImage filtered) {
        // Основная логика фильтра: преобразование в черно-белое
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                Color color = new Color(original.getRGB(x, y));
                int gray = (int) (color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11);
                Color grayColor = new Color(gray, gray, gray);
                filtered.setRGB(x, y, grayColor.getRGB());
            }
        }
    }

    @Override
    protected void customFilterLogic(BufferedImage filteredImage) {
        // Для черно-белого фильтра дополнительных шагов нет
    }
}

// Конкретный фильтр: Сепия
class SepiaFilter extends ImageFilter {
    @Override
    protected void applyFilterLogic(BufferedImage original, BufferedImage filtered) {
        // Основная логика фильтра: преобразование в сепию
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                Color color = new Color(original.getRGB(x, y));
                int r = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
                int g = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
                int b = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);

                // Ограничиваем значения, чтобы они оставались в пределах 0-255
                r = Math.min(255, r);
                g = Math.min(255, g);
                b = Math.min(255, b);

                Color sepiaColor = new Color(r, g, b);
                filtered.setRGB(x, y, sepiaColor.getRGB());
            }
        }
    }

    @Override
    protected void customFilterLogic(BufferedImage filteredImage) {
        // Для сепии дополнительных шагов нет
    }
}

// Конкретный фильтр: Увеличение резкости
class SharpenFilter extends ImageFilter {
    @Override
    protected void applyFilterLogic(BufferedImage original, BufferedImage filtered) {
        // Основная логика фильтра: увеличение резкости (применение маски резкости)
        float[] sharpenKernel = {
                0, -1, 0,
                -1, 5,-1,
                0, -1, 0
        };

        // Применение маски к каждому пикселю
        for (int y = 1; y < original.getHeight() - 1; y++) {
            for (int x = 1; x < original.getWidth() - 1; x++) {
                int rgb = applyKernel(original, x, y, sharpenKernel);
                filtered.setRGB(x, y, rgb);
            }
        }
    }

    @Override
    protected void customFilterLogic(BufferedImage filteredImage) {
        // Для увеличения резкости дополнительных шагов нет
    }

    // Метод для применения ядра (маски) к пикселям изображения
    private int applyKernel(BufferedImage image, int x, int y, float[] kernel) {
        int r = 0, g = 0, b = 0;

        int kernelIndex = 0;
        for (int ky = -1; ky <= 1; ky++) {
            for (int kx = -1; kx <= 1; kx++) {
                Color color = new Color(image.getRGB(x + kx, y + ky));
                r += color.getRed() * kernel[kernelIndex];
                g += color.getGreen() * kernel[kernelIndex];
                b += color.getBlue() * kernel[kernelIndex];
                kernelIndex++;
            }
        }

        // Ограничиваем значения цвета в пределах 0-255
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));

        return new Color(r, g, b).getRGB();
    }
}

// Главный класс для тестирования
public class ImageProcessingApp {
    public static void main(String[] args) {
        // Пример использования
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB); // Это тестовое изображение
        Graphics g = image.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 100, 100);  // Простое изображение красного цвета

        ImageFilter blackAndWhiteFilter = new BlackAndWhiteFilter();
        BufferedImage bwImage = blackAndWhiteFilter.applyFilter(image);

        ImageFilter sepiaFilter = new SepiaFilter();
        BufferedImage sepiaImage = sepiaFilter.applyFilter(image);

        ImageFilter sharpenFilter = new SharpenFilter();
        BufferedImage sharpenedImage = sharpenFilter.applyFilter(image);
    }
}

