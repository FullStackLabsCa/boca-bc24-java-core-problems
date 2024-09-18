package practice.file_io;

import java.io.*;

// Define a Serializable class
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}

public class ObjectStreamExample {
    public static void main(String[] args) {
        String filePath = "person.dat";

        // Creating a Person object
        Person person = new Person("John Doe", 30);

        // Writing the Person object to a file
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(person);
            System.out.println("Object written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading the Person object from the file
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Person readPerson = (Person) objectInputStream.readObject();
            System.out.println("Read object: " + readPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}