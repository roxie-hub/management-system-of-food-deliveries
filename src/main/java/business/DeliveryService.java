package business;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toMap;
/** @invariant mItems != null*/

public class DeliveryService implements IDeliveryServiceProcessing, Serializable {

    private ArrayList<MenuItem> mItem;
    private HashMap<Order, ArrayList<MenuItem>> hash;

    public DeliveryService(){
        this.mItem = new ArrayList<MenuItem>();
        this.hash = new HashMap<Order,ArrayList<MenuItem>>();
    }

    @Override
    public void importProd() {
        try {
            Stream<String> stream = Files.lines(Paths.get("products.csv"));
            this.mItem = (ArrayList<MenuItem>) stream.skip(1).map(line->{
                String[] bucatele = line.split(",");
                MenuItem mi = new MenuItem(bucatele[0],Float.parseFloat(bucatele[1]),Integer.parseInt(bucatele[2]),Integer.parseInt(bucatele[3]),Integer.parseInt(bucatele[4]),Integer.parseInt(bucatele[5]),Integer.parseInt(bucatele[6]));
                return mi;
            }).distinct().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(MenuItem mi) {
        assert mi!=null;
        mItem.add(mi);
        assert mItem.contains(mi);
    }

    @Override
    public void deleteItem(MenuItem mi) {
        assert mi!=null;
        MenuItem x=null;
        for(MenuItem i : mItem){
            if(i.compareTo(mi)==0) x=i;
        }
        mItem.remove(x);
        assert !mItem.contains(x);
    }

    @Override
    public void modifyItem(MenuItem mi,MenuItem miu) {
        assert mi!=null;
        assert miu!=null;
        for(MenuItem i : mItem){
            if(i.equals(mi)==true) {
                i.setName(miu.getName());
                i.setCalories(miu.getCalories());
                i.setFat(miu.getFat());
                i.setPrice(miu.getPrice());
                i.setProtein(miu.getProtein());
                i.setRating(miu.getRating());
                i.setSodium(miu.getSodium());
            }
        }

    }

    @Override
    public void createCompProd(String str, ArrayList<MenuItem> mi) {
        assert mi!=null;
        assert str!=null;
        CompositeProduct cp = new CompositeProduct(str,mi);
        mItem.add(cp);

    }

    @Override
    public ArrayList<Order> timeInterval(int start, int end) {
        assert start!=0;
        assert end!=0;
        ArrayList<Order> ord = getOrderKeys();
        ArrayList<Order> ord1 = (ArrayList<Order>) ord.stream().filter(i->
        i.getOrderDate().getHours()>=start).filter(i-> i.getOrderDate().getHours()<=end).collect(Collectors.toList());
        return ord1;
    }

    @Override
    public ArrayList<MenuItem> moreThenMyNumber(int x) {
        assert x!=0;
        ArrayList<MenuItem> m = getMIs();
        ArrayList<MenuItem> total;
        total = (ArrayList<MenuItem>) m.stream().filter(i->Collections.frequency(m,i)>=x).distinct().collect(Collectors.toList());
        return total;
    }

    @Override
    public ArrayList<String> moreThenMyNumberAndMyValue(int x, int y) {
        assert x!=0;
        assert y!=0;
        ArrayList<Order> m = getOrderKeys();
        ArrayList<Order> m1 = (ArrayList<Order>) m.stream().filter(i->i.getPrice()>=y).collect(Collectors.toList());
        ArrayList<String> str = (ArrayList<String>) m1.stream().map(i->i.getClientId()).collect(Collectors.toList());
        ArrayList<String> str1 = (ArrayList<String>) str.stream().distinct().collect(Collectors.toList());
        ArrayList<String> s = (ArrayList<String>) str1.stream().filter(i->Collections.frequency(str,i)>=x).collect(Collectors.toList());
        return s;
    }

    @Override
    public HashMap<MenuItem,Integer> inMyDay(int day) {
        assert day!=0;
        HashMap<MenuItem, Integer> m = null;
        ArrayList<Order> o = getOrderKeys();
        ArrayList<Order> o1 = (ArrayList<Order>) o.stream().filter(i -> i.getOrderDate().getDate() == day).collect(Collectors.toList());
        final ArrayList<MenuItem> p = new ArrayList<>();
        ArrayList<MenuItem> p1 = null;
        o1.stream().forEach(i->p.addAll(hash.get(i)));
        p1 = (ArrayList<MenuItem>) p.stream().distinct().collect(Collectors.toList());
        m = (HashMap<MenuItem, Integer>) p1.stream().collect(toMap(i->i, x->Collections.frequency(p, x)));
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodByName(ArrayList<MenuItem> prod, String name) {
        assert prod!=null;
        assert name!=null;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getName().contains(name)).collect(Collectors.toList());
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodByRating(ArrayList<MenuItem> prod, float rating) {
        assert prod!=null;
        assert rating!=0.0;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getRating()==rating).collect(Collectors.toList());
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodByCals(ArrayList<MenuItem> prod, int calories) {
        assert prod!=null;
        assert calories!=0;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getCalories()==calories).collect(Collectors.toList());
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodByProtein(ArrayList<MenuItem> prod, int protein) {
        assert prod!=null;
        assert protein!=0;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getProtein()==protein).collect(Collectors.toList());
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodByFat(ArrayList<MenuItem> prod, int fat) {
        assert prod!=null;
        assert fat!=0;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getFat()==fat).collect(Collectors.toList());
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodBySodium(ArrayList<MenuItem> prod, int sodium) {
        assert prod!=null;
        assert sodium!=0;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getSodium()==sodium).collect(Collectors.toList());
        return m;
    }

    @Override
    public ArrayList<MenuItem> prodByPrice(ArrayList<MenuItem> prod, int price) {
        assert prod!=null;
        assert price!=0;
        ArrayList<MenuItem> m = (ArrayList<MenuItem>) prod.stream().filter(i->i.getPrice()==price).collect(Collectors.toList());
        return m;
    }

    @Override
    public void addToHash(ArrayList<MenuItem> m, Order o) {
        assert m!=null;
        assert o!=null;
        hash.put(o,m);
        System.out.println(o.toString());
        for(MenuItem i:m){
            System.out.println(i.toString());
        }
        assert hash.containsKey(o);
        assert hash.containsValue(m);
    }

    public ArrayList<MenuItem> getMi() {
        return mItem;
    }

    public ArrayList<Order> getOrderKeys(){
        return new ArrayList<Order>(hash.keySet());
    }

    public ArrayList<MenuItem> getMIs(){
        ArrayList<MenuItem> m=new ArrayList<MenuItem>();
        for(ArrayList<MenuItem> a: hash.values()){
            for (MenuItem i : a){
                m.add(i);
            }
        }
        return m;
    }

    public HashMap<Order, ArrayList<MenuItem>> getHash() {
        return hash;
    }
}
