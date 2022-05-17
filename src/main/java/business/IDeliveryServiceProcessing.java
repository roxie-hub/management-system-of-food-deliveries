package business;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDeliveryServiceProcessing {
    void importProd();
    void addItem(MenuItem mi);
    void deleteItem(MenuItem mi);
    void modifyItem(MenuItem mi,MenuItem miu);
    void createCompProd(String str,ArrayList<MenuItem> mi);
    ArrayList<Order> timeInterval(int start, int end);
    ArrayList<MenuItem> moreThenMyNumber(int x);
    ArrayList<String> moreThenMyNumberAndMyValue(int x, int y);
    HashMap<MenuItem,Integer> inMyDay(int day);
    ArrayList<MenuItem> prodByName(ArrayList<MenuItem> prod, String name);
    ArrayList<MenuItem> prodByRating(ArrayList<MenuItem> prod, float rating);
    ArrayList<MenuItem> prodByCals(ArrayList<MenuItem> prod, int calories);
    ArrayList<MenuItem> prodByProtein(ArrayList<MenuItem> prod, int protein);
    ArrayList<MenuItem> prodByFat(ArrayList<MenuItem> prod, int fat);
    ArrayList<MenuItem> prodBySodium(ArrayList<MenuItem> prod, int sodium);
    ArrayList<MenuItem> prodByPrice(ArrayList<MenuItem> prod, int price);
    void addToHash(ArrayList<MenuItem> m, Order o);
}
