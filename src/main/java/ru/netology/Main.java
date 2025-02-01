package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
        compare(25);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = { 21, 1, 20, 23 };
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day + 4]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
        // Переходим к проверке запомненных значений
        if (memory[day] != 0) {
            return memory[day];
        }

        // Если day находится в стартовых значениях
        if (day <= 4) {
            return startNumbers[day - 1];
        }

        // Рекурсивное вычисление
        int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);

        // Вычисляем результат для текущего дня
        int result = (prev * prePrePrev) % 10 + 1;

        // Сохраняем результат в память и возвращаем результат
        memory[day] = result;
        return result;
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day + 4; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(day - 1);
    }
}