package business;

public class BaseProduct extends MenuItem {

    public BaseProduct(String name,float rating, int calories, int protein, int fat, int sodium, int price) {
        super(name,rating, calories, protein, fat, sodium,price);
    }
}