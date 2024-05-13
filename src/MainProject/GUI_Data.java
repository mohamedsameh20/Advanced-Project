package MainProject;
import java.util.Collections;
import java.util.Date;

public class GUI_Data {

    static Store StoreDetails(){
        Store S = new Store("Store" , 0);
        try {
            Book b1 = new Book("Reference X",1200.0,54,"Manufacturer X",true,0.18,new Date(2024,5,26),"Writer X","Books","https://m.media-amazon.com/images/I/71BiadBg61L._SY466_.jpg");
            Book b2 = new Book("Tom Selleck",1450.0,42,"Manufacturer Y",false,0.15,new Date(2024,5,26),"Writer Y","Books","https://m.media-amazon.com/images/I/41ktKSxzPaL._SY445_SX342_.jpg");
            Book b3 = new Book("The women",1500.0,40,"Manufacturer Z",true,0.13,new Date(2024,5,26),"Writer Z","Books","https://m.media-amazon.com/images/I/5112eIqM9pL._SY445_SX342_.jpg");

            Device d1 = new Device("Samsung",18000,14,"Samsung",true,0.12,new Date(2024,5,26),"Galaxy S","Devices","https://m.media-amazon.com/images/I/71WcjsOVOmL._AC_SX679_.jpg");
            Device d2 = new Device("iphone",25000,6,"iphone",true,0.20,new Date(2024,5,26),"14 pro max","Devices","https://m.media-amazon.com/images/I/516DZ0n8ODL._AC_SX679_.jpg");

            Clothes c1 = new Clothes("French Toast",2500,14,"Zara",true,0.20,
                    new Date(2024,5,26),"Cotton","Red","Medium",6,8,10,"Clothes","https://m.media-amazon.com/images/I/81a1-ASyfmL._AC_SX466_.jpg");
            Clothes c2 = new Clothes("The Children's Place",1200,12,"Gucci",true,0.20,
                    new Date(2024,5,26),"Cotton","Green","Large",6,8,10,"Clothes","https://m.media-amazon.com/images/I/81frs3+YkIL._AC_SX385_.jpg");

            Furniture f1 = new Furniture("Wicker chair",12000,4,"Bme",true,0.17,
                    new Date(2024,5,26),"Satian","Dark Grey","Furniture","https://m.media-amazon.com/images/I/81YzMsQinEL._AC_SX569_.jpg");
            Furniture f2 = new Furniture("Mid Century sofa",15000,6,"Dewhut",true,0.08,
                    new Date(2024,5,26),"Fluffy Velvet Fabric","Gold","Furniture","https://m.media-amazon.com/images/I/815hAtHVpAL._AC_SX569_.jpg");

            Food fo1 = new Food("Hazelnut Spread",320,6,"Chosen",false,0,new Date(2024,5,26),new Date(2024,5,15),"Food","https://m.media-amazon.com/images/I/61KQRVPuwQL._SX569_.jpg");
            Food fo2 = new Food("Drizzle Sauces",230,2,"Chosen",false,0,new Date(2024,5,26),new Date(2024,5,15),"Food","https://m.media-amazon.com/images/I/81MrYyKfkWL._SX569_.jpg");

            if (S.getProducts() != null) {
                S.addProduct(b1);
                S.addProduct(b2);
                S.addProduct(b3);
                S.addProduct(d1);
                S.addProduct(d2);
                S.addProduct(c1);
                S.addProduct(c2);
                S.addProduct(f1);
                S.addProduct(f2);
                S.addProduct(fo1);
                S.addProduct(fo2);
            }

        }
        catch (Exception e) {e.printStackTrace();}
        return S;
    }
    static User UserDetails(){
        return new User("Mazen","habbeelnammed69@gmail.com",50000);
    }

}
