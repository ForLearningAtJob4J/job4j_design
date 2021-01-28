package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
    @XmlAttribute
    boolean onBalance;
    @XmlAttribute
    int value;
    @XmlAttribute
    String model;

    Person owner;

    @XmlElementWrapper(name = "previousOwners")
    @XmlElement(name = "owner")
    Person[] previousOwners;

    public Computer() {
    }

    public Computer(boolean onBalance, int value, String model, Person owner, Person[] previousOwners) {
        this.onBalance = onBalance;
        this.value = value;
        this.model = model;
        this.owner = owner;
        this.previousOwners = previousOwners;
    }

    @Override
    public String toString() {
        return "Person{"
                + "onBalance=" + onBalance
                + ", value=" + value
                + ", model=" + model
                + ", owner=" + owner
                + ", statuses=" + Arrays.toString(previousOwners)
                + '}';
    }
}
