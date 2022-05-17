package business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{
   private ArrayList<MenuItem> menu;
    public CompositeProduct(String name, ArrayList<MenuItem> menu ) {
        super(name,medieRating(menu),sumCalories(menu),sumProteins(menu),sumFat(menu),sumSodium(menu), sumPrice(menu));
        this.menu=menu;
    }
    private static int sumPrice(ArrayList<MenuItem> menu){
        int p=0;
        for(MenuItem mi: menu){
            p+=mi.getPrice();
        }
        return p;
    }

    private static int sumCalories(ArrayList<MenuItem> menu){
        int c=0;
        for(MenuItem mi: menu){
            c+=mi.getCalories();
        }
        return c;
    }

    private static int sumProteins(ArrayList<MenuItem> menu){
        int p=0;
        for(MenuItem mi: menu){
            p+=mi.getProtein();
        }
        return p;
    }

    private static int sumFat(ArrayList<MenuItem> menu){
        int f=0;
        for(MenuItem mi: menu){
            f+=mi.getFat();
        }
        return f;
    }
    private static int sumSodium(ArrayList<MenuItem> menu){
        int s=0;
        for(MenuItem mi: menu){
            s+=mi.getSodium();
        }
        return s;
    }

    private static float medieRating(ArrayList<MenuItem> menu){
        float s=0;
        for(MenuItem mi: menu){
            s+=mi.getRating();
        }
        s=s/menu.size();
        return s;
    }
}
