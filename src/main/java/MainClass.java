import business.DeliveryService;
import data.Serializator;
import presentation.Administrator;
import presentation.Client;
import presentation.LogIn;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        DeliveryService ds;/*=new DeliveryService();
        ds.importProd();
        Serializator.Serializare(ds);*/
        try {
             ds=Serializator.Deserializare();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        LogIn log = new LogIn(ds);
        //Administrator admin = new Administrator(ds);
        //Client client = new Client(ds,"costica");

    }

}
