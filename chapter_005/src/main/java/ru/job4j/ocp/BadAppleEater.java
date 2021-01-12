package ru.job4j.ocp;

/**
 * Если нужно будет впоследствии добавлять другие фрукты, то придется изменять код
 */
public class BadAppleEater {
    Apple apple;
    public void takeApple(Apple apple) {
        this.apple = apple;
    }

    public void eat() {
        if (apple != null) {
            System.out.println("Yam yam");
        }
    }
}

class Apple {
}