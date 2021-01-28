package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Task {
    public static void main(String[] args) throws Exception {
        Computer computer = new Computer(false, 1000, "Micro-86M",
                new Person(true, 25, new Contact("11-111"), "Married", "Employed"),
                new Person[]{
                        new Person(false, 25, new Contact("11-112"), "Single", "Employed"),
                        new Person(true, 35, new Contact("11-113"), "Single", "Unemployed")
                });
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Computer.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(computer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для сериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Computer result = (Computer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        //marshaller.marshal(book, new File("./book.xml"));
    }
}
