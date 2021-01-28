package ru.job4j.lsp.bad;

/**
 * При такой абстракции получим неприятность при использовании ссылки на базовый объект, но которая будет ссылаться на
 * Penguin, также еще нарушен ISP - Penguin зависит от метода, который он не использует и вынужден перекрывать его
 */
public class Penguin implements Bird {
    @Override
    public void fly() {
        throw new IllegalArgumentException("Can not fly");
    }
}

class Eagle implements Bird {
    @Override
    public void fly() {
        System.out.println("Do fly");
    }
}

interface Bird {
    void fly();
}