package ru.job4j.lsp.bad;

/**
 * Метод BadRepository.save определяет свои действия в зависимости от типа Entity. Легко ошибиться при
 * некорректном/неполном наборе веток, также нарушен OCP
 */
public class BadRepository {
    public void save(AbstractEntity entity) throws InterruptedException {
        if (entity instanceof AccountEntity) {
            // специфические действия для AccountEntity
            Thread.sleep(10);
        }
        if (entity instanceof RoleEntity) {
            // специфические действия для RoleEntity
            Thread.sleep(10);
        }
    }
}

abstract class AbstractEntity {
}

class AccountEntity extends AbstractEntity {
}

class RoleEntity extends AbstractEntity {
}