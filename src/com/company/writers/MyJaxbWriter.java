package com.company.writers;

import com.company.writers.jaxbextraclasses.TaskResult;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Класс, описывающий сущность, которая записывает результаты заданий в XML файл, используя библиотеку JAXB.
 */
public class MyJaxbWriter {

    /**
     * Путь к директории с выходными файлами.
     */
    private static final String DIRECTORY = "src\\com\\company\\Files\\Output";

    /**
     * Записывает в файл результат задания.
     *
     * @param result результат задания
     * @param fileName название выходного файла
     */
    public void write(TaskResult result, String fileName) {

        File file = new File(DIRECTORY, fileName);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(result.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.marshal(result, file);

        } catch (JAXBException e) {
            System.err.println("Выброшено JAXBException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }
    }
}
