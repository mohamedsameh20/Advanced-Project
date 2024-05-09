
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.util.StringConverter;
import java.util.ArrayList;
import java.util.Date;


public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #ff7f50");
        User(pane,UserDetails());
        Menu(pane,UserDetails());
        Store(pane,StoreDetails());

        Scene scene = new Scene( pane, 800, 800);
        primaryStage.setTitle("Store"); // Set the stage title
        primaryStage.setScene(scene); // Put scene in stage
        primaryStage.show();

    }

    void Menu(BorderPane pane, User U){
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-background-color: #ff6347");
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setPrefWidth(200);
        Label lb = new Label("Your Products");
        lb.setFont(new Font("Arial",16));
        vbox.getChildren().add(lb);

        ArrayList<Product> list = U.getShoppingCart().getCartProducts();
        ComboBox<Product> cb = new ComboBox<Product>(FXCollections.observableArrayList(list));
        cb.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product product) {
                if (product != null){
                    return product.getProductName();}
                return "";
            }
            @Override
            public Product fromString(String s) {
                return null;
            }
        });
        vbox.getChildren().add(cb);
        pane.setLeft(vbox);
    }



    void User(BorderPane pane, User U){
        HBox hbox = new HBox(120);
        hbox.setStyle("-fx-background-color: #dc143c");
        hbox.setPadding(new Insets(20,20,20,20));
        hbox.setPrefWidth(200);
        Label name = new Label("Hi, " + U.getName());
        name.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 20px;");
        Label balance = new Label("Your Balance: " + U.getBalance());
        balance.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 20px;");
        Button gtc = new Button("Go to Cart");
        hbox.getChildren().addAll(name,balance,gtc);
        pane.setTop(hbox);
    }

    void Store(BorderPane pane, Store S){
        FlowPane flow = new FlowPane();
        flow.setOrientation(Orientation.HORIZONTAL);
        flow.setHgap(20);
        flow.setVgap(20);
        flow.setPadding(new Insets(40,40,40,40));
        Cell C = new Cell();
        Cell C2 = new Cell();
        C.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C.add_name("Iphone");
        C2.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C2.add_name("Iphone");
        flow.getChildren().addAll(C,C2);
        pane.setCenter(flow);


    }

    static class Cell extends Pane{
        public Cell(){
            setStyle("-fx-text-fill: #483d8b;-fx-background-color: #ff6347");
            this.setPrefSize(200,350);
            Button add = new Button("Add to Cart");
            Button buy = new Button("Buy");
            HBox hbox = new HBox(25,add,buy);
            hbox.layoutXProperty().bind(this.widthProperty().divide(5.5));
            hbox.layoutYProperty().bind(this.heightProperty().divide(1.1));
            this.getChildren().add(hbox);

        }

        void set_image(String url) {
            Image image = new Image(url);
            ImageView iv =new ImageView(image);
            iv.setFitWidth(200);
            iv.setPreserveRatio(true);
            this.getChildren().add(iv);
        }
        void add_name(String name){
            Label lb = new Label(name);
            lb.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 20px;");
            lb.layoutXProperty().bind(this.widthProperty().divide(2.6));
            lb.layoutYProperty().bind(this.heightProperty().divide(1.4));
            this.getChildren().add(lb);

        }
   }



    User UserDetails(){
        User U = new User("Habbeel Nammed","habbeelnammed69@gmail.com",6969);
        try {
            Book b1 = new Book("Reference X",1200.0,54,"Manufacturer X",true,0.18,new Date(),"Writer X");
            Book b2 = new Book("Reference Y",1450.0,42,"Manufacturer Y",true,0.15,new Date(),"Writer Y");
            Device d1 = new Device("Mobile Phone",32500,14,"Samsung",true,0.12,new Date(),"Galaxy S");
            U.getShoppingCart().addProducts(b1);
            U.getShoppingCart().addProducts(b2);
            U.getShoppingCart().addProducts(d1);
        }
        catch (Exception e) {e.printStackTrace();}
        return  U;
    }

    Store StoreDetails(){
        Store S = new Store("Book Store" , 0);
        try {
            Book b1 = new Book("Reference X",1200.0,54,"Manufacturer X",true,0.18,new Date(),"Writer X");
            Book b2 = new Book("Reference Y",1450.0,42,"Manufacturer Y",true,0.15,new Date(),"Writer Y");
            Book b3 = new Book("Reference Z",1500.0,40,"Manufacturer Z",true,0.13,new Date(),"Writer Z");
            Device d1 = new Device("Samsung",32500,14,"Samsung",true,0.12,new Date(),"Galaxy S");
            Device d2 = new Device("iphone",35000,6,"iphone",true,0.20,new Date(),"14 pro max");
            if (S.getProducts() != null) {
                S.addProduct(b1);
                S.addProduct(b2);
                S.addProduct(b3);
                S.addProduct(d1);
                S.addProduct(d2);
            }
        }
        catch (Exception e) {e.printStackTrace();}
        return S;
    }



}
