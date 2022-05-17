package data;

import business.DeliveryService;

import java.io.*;

public class Serializator {
    public static void Serializare(DeliveryService ds) throws IOException {
        String file = "serializare.txt";
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(f);
        os.writeObject(ds);
        os.close();
        f.close();
    }

    public static DeliveryService Deserializare() throws IOException, ClassNotFoundException {
        String file = "serializare.txt";
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream is = new ObjectInputStream(f);
        DeliveryService ds = (DeliveryService) is.readObject();
        is.close();
        f.close();
        return ds;
    }
}
