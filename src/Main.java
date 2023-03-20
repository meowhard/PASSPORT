import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialized.txt"));
        Human human = new Human("Антон", "5411564");

        oos.writeObject(human);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serialized.txt"));
        Human desHuman = (Human) ois.readObject();

        System.out.println(desHuman.getName());
        System.out.println(desHuman.getPassportNumber());
        ois.close();
    }
}