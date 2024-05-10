package application;
import java.util.ArrayList;
import java.util.Date;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;


public class MainMenu extends Application {
	private StackPane root = new StackPane();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage)
    {
    	this.primaryStage = primaryStage;

    	VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setStyle("-fx-background-color: #E2FAFA; -fx-padding: 10;");
        Label title = new Label("Welcome to the Store");
        title.setFont(new Font("Arial", 24));
        Label message = new Label("Click the button below to enter the store.");
        message.setFont(new Font("Arial", 16));
        Button goToStoreButton = new Button("Go to Store");
        goToStoreButton.setFont(new Font("Arial", 18));
        goToStoreButton.setOnAction(e -> showStore());
        FadeTransition ft = new FadeTransition(Duration.millis(1000), welcomeLayout);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        welcomeLayout.getChildren().addAll(title, message, goToStoreButton);
        root.getChildren().add(welcomeLayout);
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showStore() {
    	 root.getChildren().clear();

    	    BorderPane pane = new BorderPane();
    	    pane.setStyle("-fx-background-color: #E2FAFA");
    	    User(pane, UserDetails());
    	    Menu(pane, UserDetails());
    	    Store(pane, StoreDetails());
    	    FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
    	    root.getChildren().add(pane);
    	    primaryStage.setTitle("Store");
    	    primaryStage.setWidth(900);
    	    primaryStage.setHeight(900);
    	    primaryStage.setAlwaysOnTop(false);
    	    primaryStage.setFullScreen(false);
    	}
    
    void Menu(BorderPane pane, User U){
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-background-color: #75DADA");
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setPrefWidth(200);
        Label lb = new Label("Your Products");
        lb.setStyle("-fx-text-fill: #014C4C; -fx-font-size: 16px;");
        lb.setFont(new Font("Bahnschrift Light",16));
        vbox.getChildren().add(lb);
        ArrayList<Product> list = U.getShoppingCart().getCartProducts();
        ComboBox<Product> cb = new ComboBox<Product>(FXCollections.observableArrayList(list));
        cb.setPromptText("Select Product");
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
        cb.setOnAction(e -> {
            Product selectedProduct = cb.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
            	FadeTransition ft = new FadeTransition(Duration.millis(1000), vbox);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Product Details");
                alert.setHeaderText(null);
                alert.setContentText("Product Name: " + selectedProduct.getProductName() + "\n" +
                                     "Price: " + selectedProduct.getPrice() + "\n" +
                                     "Manufacturer: " + selectedProduct.getManufacturer());
                alert.showAndWait();
            }
        });
        vbox.getChildren().add(cb);
        pane.setLeft(vbox);
    }



    void User(BorderPane pane, User U){
        HBox hbox = new HBox(120);
        hbox.setStyle("-fx-background-color: #13A3A3");
        hbox.setPadding(new Insets(20,20,20,20));
        hbox.setPrefWidth(200);
        Label name = new Label("Hi, " + U.getName());
        name.setFont(new Font("Bahnschrift Light",16));
        name.setStyle("-fx-text-fill: #DFF8F8; -fx-font-size: 20px;");
        Label balance = new Label("Your Balance: " + U.getBalance());
        balance.setFont(new Font("Bahnschrift Light",16));
        balance.setStyle("-fx-text-fill: #DFF8F8; -fx-font-size: 20px;");
        Button gtc = new Button("Go to Cart");
        hbox.getChildren().addAll(name,balance,gtc);
        pane.setTop(hbox);
        
        String originalPaneStyle = pane.getStyle();
        String originalHboxStyle = hbox.getStyle();
        String originalBalanceStyle = balance.getStyle();

        Button darkModeButton = new Button("Dark Mode");
        darkModeButton.setOnAction(e -> {
            String darkModeStyles = "-fx-background-color: #2b2b2b; "
                    + "-fx-text-fill: #f0f0f0; "
                    + "-fx-base: #3c3f41;";

            if (pane.getStyle().equals(darkModeStyles)) {
            	FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
                pane.setStyle(originalPaneStyle);
                hbox.setStyle(originalHboxStyle);
                balance.setStyle(originalBalanceStyle);
                darkModeButton.setText("Dark Mode");
            } else {
            	 FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
                 ft.setFromValue(0.0);
                 ft.setToValue(1.0);
                 ft.play();
                pane.setStyle(darkModeStyles);
                hbox.setStyle("-fx-background-color: #2b2b2b;");
                balance.setStyle("-fx-text-fill: #f0f0f0; -fx-font-size: 20px;");
                darkModeButton.setText("Light Mode");
            }
        });
        hbox.getChildren().add(darkModeButton);
     
    }

    void Store(BorderPane pane, Store S){
        FlowPane flow = new FlowPane();
        flow.setOrientation(Orientation.HORIZONTAL);
        flow.setHgap(20);
        flow.setVgap(20);
        flow.setPadding(new Insets(40,40,40,40));
        Cell C = new Cell();
        Cell C2 = new Cell();
        Cell C3 = new Cell();
        Cell C4 = new Cell();
        Cell C5 = new Cell();
        Cell C6 = new Cell();
        Cell C7 = new Cell();
        C.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C.add_name("Iphone");
        C2.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C2.add_name("Iphone");
        C3.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C3.add_name("Iphone");
        C4.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C4.add_name("Iphone");
        C5.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C5.add_name("Iphone");
        C6.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C6.add_name("Iphone");
        C7.set_image("https://i5.walmartimages.com/seo/Restored-Apple-iPhone-14-Pro-Max-Carrier-Unlocked-256GB-Deep-Purple-" +
                "MQ8W3LL-A-Refurbished_cb8f75e5-1b8e-4c06-9776-0d995a314ada.88ab53492f6fe7e653033585616419b1.jpeg");
        C7.add_name("Iphone");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(flow);
        pane.setCenter(scrollPane);
        flow.getChildren().addAll(C,C2,C3,C4,C5,C6,C7);

        HBox hbox = new HBox(120);
        hbox.setStyle("-fx-background-color: #D0FCFC");
        hbox.setPadding(new Insets(20,20,20,20));
        hbox.setPrefWidth(200);
        Label name = new Label("Hi, " + S.getStoreName());
        name.setStyle("-fx-text-fill: #2081C3; -fx-font-size: 20px;");
        Label balance = new Label("Your Balance: " + S.getBalance());
        balance.setStyle("-fx-text-fill: #2081C3; -fx-font-size: 20px;");
        Button gtc = new Button("Go to Cart");
        Button refresh = new Button("Refresh");
        refresh.setVisible(true);
        refresh.setOnAction(e -> {
            balance.setText("Store Balance: " + S.getBalance());
        });
        hbox.getChildren().addAll(name,balance,gtc,refresh);
    }

	static class Cell extends Pane{
        public Cell(){
            setStyle("-fx-text-fill: #2081C3;-fx-background-color: #AFFFFF");
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
            lb.setStyle("-fx-text-fill: #2081C3; -fx-font-size: 20px;");
            lb.layoutXProperty().bind(this.widthProperty().divide(2.6));
            lb.layoutYProperty().bind(this.heightProperty().divide(1.4));
            this.getChildren().add(lb);

        }
   }


    User UserDetails(){
        User U = new User("Habeel Nameed","habbeelnammed69@gmail.com",6969);
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
            if (S.getProductsList() != null) {
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
