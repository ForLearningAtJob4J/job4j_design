package ru.job4j.ocp;

/**
 * при появлении новой сущности придется изменить метод
 */
public class BadRepository {
    public void save(AbstractEntity entity) throws InterruptedException {
        if (entity instanceof AccountEntity) {
            // специфические действия для AccountEntity
            Thread.sleep(1000);
        }
        if (entity instanceof RoleEntity) {
            // специфические действия для RoleEntity
            Thread.sleep(1000);
        }
    }
}

abstract class AbstractEntity {
}

class AccountEntity extends AbstractEntity {
}

class RoleEntity extends AbstractEntity {
}