package MainProject;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;


public class  GUI_MainStore {

    @FXML static ChoiceBox<Product> cb = new ChoiceBox<Product>();
    static User U = GUI_Data.UserDetails();
    static Store S = GUI_Data.StoreDetails();
    @FXML static Label balance = new Label("Your Balance: " + U.getBalance());
    @FXML static Label balance_copy = new Label("Your Balance: " + U.getBalance());
    static int sceneX = 1200;
    static int sceneY = 1000;


    static Pane Menu(BorderPane pane,Stage primarystage, ArrayList<Scene> scenes){
    	VBox vbox = new VBox(20);
    	vbox.setStyle("-fx-background-color: #75DADA");
    	vbox.setPadding(new Insets(20,20,20,20));
    	vbox.setPrefWidth(200);
    	Label lb = new Label("Your Products");
    	lb.setFont(new Font("Arial",16));
    	lb.setStyle("-fx-text-fill: #014C4C");
    	vbox.getChildren().add(lb);
        ArrayList<Product> list = U.getShoppingCart().getCartProducts();
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
      cb.setOnAction(e->{
           if(cb.getSelectionModel().getSelectedItem()!= null){
           Scene product_page = new Scene(productInfo(cb.getSelectionModel().getSelectedItem(), primarystage, scenes), sceneX, sceneY);
           primarystage.setScene(product_page);
           }

       });
        cb.setItems(FXCollections.observableArrayList(list));
        vbox.getChildren().add(cb);

        ToggleGroup tg = new ToggleGroup();

        // create radioButtons
        RadioButton rb1 = new RadioButton("All");
        RadioButton rb2 = new RadioButton("Devices");
        RadioButton rb3 = new RadioButton("Books");
        RadioButton rb4 = new RadioButton("Furniture");
        RadioButton rb5 = new RadioButton("Clothes");
        RadioButton rb6 = new RadioButton("Food");

        // add radioButtons to toggle group
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);
        rb5.setToggleGroup(tg);
        rb6.setToggleGroup(tg);


        vbox.getChildren().addAll(rb1,rb2,rb3,rb4,rb5,rb6);
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {

                RadioButton rb = (RadioButton)tg.getSelectedToggle();
                if (rb != null) {
                    String s = rb.getText();
                    switch (s) {
                        case "All":       pane.setCenter(store(primarystage,scenes));
                                          break;
                        case "Devices":   pane.setCenter(sorted_store("Devices",primarystage,scenes));
                                          break;
                        case "Books":     pane.setCenter(sorted_store("Books",primarystage,scenes));
                                          break;
                        case "Furniture": pane.setCenter(sorted_store("Furniture",primarystage,scenes));
                                          break;
                        case "Clothes":   pane.setCenter(sorted_store("Clothes",primarystage,scenes));
                                          break;
                        case "Food":      pane.setCenter(sorted_store("Food",primarystage,scenes));
                                          break;
                    }

                }
            }
        });

        return vbox;
    }

    static Pane user(BorderPane pane,Stage primarystage, ArrayList<Scene> scenes){
    	HBox hbox = new HBox(120);
    	hbox.setStyle("-fx-background-color: #13A3A3");
    	hbox.setPadding(new Insets(20,20,20,20));
    	hbox.setPrefWidth(200);
    	Label name = new Label("Hi, " + U.getName());
    	name.setStyle("-fx-text-fill: #DFF8F8; -fx-font-size: 20px;");

        balance.setStyle("-fx-text-fill: #DFF8F8; -fx-font-size: 20px;");
        Button gtc = new Button("Go to Cart");
        gtc.setOnAction(e -> {
            Scene cartPage = new Scene(cart_page(primarystage,scenes),sceneX,sceneY);
            primarystage.setScene(cartPage);
        } );
        hbox.getChildren().addAll(name,balance,gtc);
        String originalPaneStyle = pane.getStyle();
        String originalHboxStyle = hbox.getStyle();
        String originalBalanceStyle = balance.getStyle();

        Button darkModeButton = new Button("Dark Mode");
        darkModeButton.setOnAction(e -> {
            String darkModeStyles = "-fx-background-color: #2b2b2b; "
                    + "-fx-text-fill: #f0f0f0; "
                    + "-fx-base: #3c3f41;";

            if (pane.getStyle().equals(darkModeStyles)) {
                FadeTransition ft = new FadeTransition(Duration.millis(500), pane);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
                pane.setStyle(originalPaneStyle);
                hbox.setStyle(originalHboxStyle);
                balance.setStyle(originalBalanceStyle);
                darkModeButton.setText("Dark Mode");
            } else {
                FadeTransition ft = new FadeTransition(Duration.millis(500), pane);
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
        return hbox;
    }

    static ScrollPane store(Stage primarystage, ArrayList<Scene> scenes){
        FlowPane flow = new FlowPane();
        flow.setOrientation(Orientation.HORIZONTAL);
        flow.setHgap(20);
        flow.setVgap(20);
        flow.setPadding(new Insets(40,40,40,40));

        final ScrollPane scroll = new ScrollPane();

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(flow);
        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                flow.setPrefWidth(bounds.getWidth());
                flow.setPrefHeight(bounds.getHeight());
            }
        });

        flow.setStyle("-fx-background-color: #0000;");
        for(int i = 0; i<S.getProducts().length;i++){
            Cell C = new Cell(S.getProducts()[i] , primarystage,scenes);
            C.set_image(S.getProducts()[i].getImage_url());
            flow.getChildren().add(C);
        }
        return scroll;
    }

    static ScrollPane sorted_store(String s,Stage primarystage, ArrayList<Scene> scenes){
        FlowPane flow = new FlowPane();
        flow.setOrientation(Orientation.HORIZONTAL);
        flow.setHgap(20);
        flow.setVgap(20);
        flow.setPadding(new Insets(40,40,40,40));

        final ScrollPane scroll = new ScrollPane();

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(flow);
        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                flow.setPrefWidth(bounds.getWidth());
                flow.setPrefHeight(bounds.getHeight());
            }
        });

        flow.setStyle("-fx-background-color: #dff8f8;");
        for(int i = 0; i<S.getProducts().length;i++){
            if(Objects.equals(S.getProducts()[i].get_genre(), s)){
                Cell C = new Cell(S.getProducts()[i] , primarystage,scenes);
                C.set_image(S.getProducts()[i].getImage_url());
                flow.getChildren().add(C);
            }
        }
        return scroll;
    }

    static Pane productInfo(Product P,Stage primarystage, ArrayList<Scene> scenes){
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #E2FAFA;-fx-text-fill: #483d8b; -fx-font-size: 20px;");
        grid.setPadding(new Insets(40,40,40,40));
        grid.setHgap(40);
        grid.setVgap(40);

        // image handling
        if(P != null) {
            Image image = new Image(P.getImage_url());
            ImageView iv = new ImageView(image);
            iv.setFitWidth(200);
            iv.setPreserveRatio(true);
            grid.add(iv, 0, 0, 1, 1);
        }
        // Labels of names
        Label name = new Label("Name: ");
        Label price = new Label("Price: ");
        Label stock = new Label("In Stock: ");
        Label id = new Label("ID: ");
        Label manufacturer = new Label("Manufacturer: ");
        Label discount = new Label("Discount: ");
        Label discount_date = new Label("Discount Expire In: ");

        //Buttons handling
        Button back = new Button("Back");
        Button buy = new Button("BUY");
        Button add = new Button("ADD TO CART");
        back.setVisible(true);
        
        buy.setOnAction(e -> {
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setHeaderText("CONFIRMATION");
                    confirmation.setContentText("Confirm the purchase of this product!! ");
                    Optional<ButtonType> result = confirmation.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        if(U.getBalance()<P.getPrice()){
                            Alert warning = new Alert(Alert.AlertType.WARNING);
                            warning.setHeaderText("WARNING");
                            warning.setContentText("Insufficient Balance!!\n" + "Please charge up your balance ");
                            warning.showAndWait();
                        }
                        else{
                        S.sell( P ,U);
                        balance.setText("Your Balance: " + Double.toString(U.getBalance()));
                        Alert done = new Alert(Alert.AlertType.INFORMATION);
                        done.setHeaderText("Done");
                        done.setContentText("Product purchased successfully!! ");
                        done.showAndWait();
                        }
                    }
                });
        add.setOnAction(e -> {
            U.getShoppingCart().addProducts(P);
            cb.setItems(FXCollections.observableArrayList(U.getShoppingCart().getCartProducts()));
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText("INFO");
            info.setContentText("Added to cart!!");
            info.showAndWait();
        });
        ButtonBar btnBar = new ButtonBar();
        btnBar.getButtons().addAll(buy,add);
        back.setOnAction(e -> primarystage.setScene(scenes.get(0)));

        //Adding buttons to the grid pane
        grid.add(back,2,0,1,1);
        grid.add(btnBar,1,0);
        // Labels of Product's names
        if(P != null) {
            Label P_name = new Label(P.getProductName());
            Label P_price = new Label(Double.toString(P.getPrice()));
            Label P_stock = new Label(Integer.toString(P.getNumberOfAvailable()));
            Label P_id = new Label(P.getiD());
            Label P_manufacturer = new Label(P.getManufacturer());
            Label P_discount = new Label(Double.toString(P.getDiscountValue() * 100) + "%");
            DateFormat df = new SimpleDateFormat();
            Label P_discount_date = new Label(df.format(P.getDiscountExpiry()));

            //Adding labels to the grid pane
            grid.add(name, 0, 1, 1, 1);
            grid.add(price, 0, 2, 1, 1);
            grid.add(stock, 0, 3, 3, 1);
            grid.add(manufacturer, 0, 4, 1, 1);
            grid.add(id, 0, 5, 1, 1);
            if (P.getHasDiscount()) {
                grid.add(discount, 2, 1, 1, 1);
                grid.add(discount_date, 2, 2, 1, 1);
            }
            switch (P.get_genre()){
                case "Devices":     Label model = new Label("Model: ");
                                    grid.add(model, 0, 6, 1, 1);
                    break;
                case "Books":       Label writer = new Label("Writer: ");
                                    grid.add(writer, 0, 6, 1, 1);
                    break;
                case "Furniture":   Label F_material = new Label("Material: ");
                                    Label F_color = new Label("Color: ");
                                    grid.add(F_material, 0, 6, 1, 1);
                                    grid.add(F_color, 0, 7, 1, 1);
                    break;
                case "Clothes":     Label C_material = new Label("Material: ");
                                    Label C_color = new Label("Color: ");
                                    Label fitting = new Label("Fitting: ");
                                    Label Sizes = new Label("Available sizes: ");
                                    grid.add(C_material, 0, 6, 1, 1);
                                    grid.add(C_color, 0, 7, 1, 1);
                                    grid.add(fitting, 2, 6, 1, 1);
                                    grid.add(Sizes, 2, 7, 1, 1);
                    break;
                case "Food":        Label expire = new Label("Expiry date: ");
                                    grid.add(expire, 0, 6, 1, 1);
                    break;

            }

            grid.add(P_name, 1, 1, 1, 1);
            grid.add(P_price, 1, 2, 1, 1);
            grid.add(P_stock, 1, 3, 1, 1);
            grid.add(P_manufacturer, 1, 4, 1, 1);
            grid.add(P_id, 1, 5, 1, 1);
            if (P.getHasDiscount()) {
                grid.add(P_discount, 3, 1, 1, 1);
                grid.add(P_discount_date, 3, 2, 1, 1);
            }

            switch (P.get_genre()){
                case "Devices":     Device d = (Device) P;
                                    Label model = new Label(d.getModel());
                                    grid.add(model, 1, 6, 1, 1);
                    break;
                case "Books":       Book b = (Book) P;
                                    Label writer = new Label(b.getWriter());
                                    grid.add(writer, 1, 6, 1, 1);
                    break;
                case "Furniture":   Furniture f = (Furniture) P;
                                    Label F_material = new Label(f.getMaterial());
                                    Label F_color = new Label(f.getColor());
                                    grid.add(F_material, 1, 6, 1, 1);
                                    grid.add(F_color, 1, 7, 1, 1);
                    break;
                case "Clothes":     Clothes c = (Clothes) P;
                                    Label C_material = new Label(c.getMaterial());
                                    Label C_color = new Label(c.getColor());
                                    Label fitting = new Label(c.getFitting());
                                    Label Sizes = new Label(c.getSize('s') +" ,"+ c.getSize('m') +" ,"+ c.getSize('l') );
                                    grid.add(C_material, 1, 6, 1, 1);
                                    grid.add(C_color, 1, 7, 1, 1);
                                    grid.add(fitting, 3, 6, 1, 1);
                                    grid.add(Sizes, 3, 7, 1, 1);
                    break;
                case "Food":        Food fo = (Food)P;
                                    Label expire = new Label(fo.getExpiryDate());
                                    grid.add(expire, 1, 6, 1, 1);
                    break;
            }


        }
        return grid;
    }

    static class Cell extends Pane {
        public Cell( Product P, Stage primarystage, ArrayList<Scene> scenes){
            setStyle("-fx-text-fill: #483d8b;-fx-background-color: #DFF8F8");
            this.setPrefSize(200,350);
            Label lb = new Label(P.getProductName());
            Label price = new Label("$"+Double.toString(P.getPrice()));
            lb.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 20px;");
            price.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 18px;");
            price.setAlignment(Pos.CENTER);

            lb.layoutXProperty().bind(this.widthProperty().subtract(lb.widthProperty()).divide(2));
            lb.layoutYProperty().bind(this.heightProperty().divide(1.4));
            price.layoutXProperty().bind(this.widthProperty().divide(3.5));
            price.layoutYProperty().bind(this.heightProperty().divide(1.25));

            lb.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override public void handle(MouseEvent e) {
                    lb.setScaleX(1.5);
                    lb.setScaleY(1.5);
                }
            });
            lb.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override public void handle(MouseEvent e) {
                    Scene product_page = new Scene( productInfo(P,primarystage,scenes), sceneX, sceneY);
                    primarystage.setScene(product_page);
                }
            });

            lb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    lb.setScaleX(1);
                    lb.setScaleY(1);
                }
            });
            this.getChildren().addAll(lb,price);

            Button add = new Button("Add to Cart");
            Button buy = new Button("Buy");
            buy.setOnAction(e -> {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setHeaderText("CONFIRMATION");
                confirmation.setContentText("Confirm the purchase of this product!! ");
                Optional<ButtonType> result = confirmation.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if(U.getBalance()<P.getPrice()){
                        Alert warning = new Alert(Alert.AlertType.WARNING);
                        warning.setHeaderText("WARNING");
                        warning.setContentText("Insufficient Balance!!\n" + "Please refile your balance ");
                        warning.showAndWait();
                    }
                    else{
                    S.sell(P,U);
                    balance.setText("Your Balance: " +U.getBalance());
                    balance_copy.setText("Your Balance: " +U.getBalance());

                    cb.setItems(FXCollections.observableArrayList(U.getShoppingCart().getCartProducts()));
                    Alert done = new Alert(Alert.AlertType.INFORMATION);
                    done.setHeaderText("Done");
                    done.setContentText("Product purchased successfully!! ");
                    done.showAndWait();
                    }
                }
            });
            add.setOnAction(e -> {
                U.getShoppingCart().addProducts(P);
                cb.setItems(FXCollections.observableArrayList(U.getShoppingCart().getCartProducts()));
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setHeaderText("INFO");
                info.setContentText(P.getProductName() + " Added to cart!!");
                info.showAndWait();
            });
            HBox hbox = new HBox(25,add,buy);
            hbox.layoutXProperty().bind(this.widthProperty().divide(5.5));
            hbox.layoutYProperty().bind(this.heightProperty().divide(1.1));
            this.getChildren().add(hbox);
        }
        public Cell( Product P, Stage primarystage, ArrayList<Scene> scenes, String justToUseDiffConstructor){
            setStyle("-fx-text-fill: #483d8b;-fx-background-color: #DFF8F8");
            this.setPrefSize(200,350);
            Label lb = new Label(P.getProductName());
            lb.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 20px;");
            lb.layoutXProperty().bind(this.widthProperty().divide(4));
            lb.layoutYProperty().bind(this.heightProperty().divide(1.4));
            lb.setOnMouseEntered(new EventHandler<MouseEvent>(){
                @Override public void handle(MouseEvent e) {
                    lb.setScaleX(1.5);
                    lb.setScaleY(1.5);
                }
            });
            lb.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override public void handle(MouseEvent e) {
                    Scene product_page = new Scene( productInfo(P,primarystage,scenes), sceneX, sceneY);
                    primarystage.setScene(product_page);
                }
            });

            lb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    lb.setScaleX(1);
                    lb.setScaleY(1);
                }
            });
            this.getChildren().add(lb);
            Button remove = new Button("Remove");
            Button buy = new Button("Buy");
            buy.setOnAction(e -> {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setHeaderText("CONFIRMATION");
                confirmation.setContentText("Confirm the purchase of this product!! ");
                Optional<ButtonType> result = confirmation.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if(U.getBalance()<P.getPrice()){
                        Alert warning = new Alert(Alert.AlertType.WARNING);
                        warning.setHeaderText("WARNING");
                        warning.setContentText("Insufficient Balance!!\n" + "Please refile your balance ");
                        warning.showAndWait();
                    }
                    else{
                        S.sell(P,U);
                        U.getShoppingCart().removeProduct(P);
                        balance.setText("Your Balance: " +U.getBalance());
                        balance_copy.setText("Your Balance: " +U.getBalance());
                        cb.setItems(FXCollections.observableArrayList(U.getShoppingCart().getCartProducts()));
                        Scene updated_cart = new Scene(cart_page(primarystage,scenes),sceneX,sceneY);
                        primarystage.setScene(updated_cart);
                        Alert done = new Alert(Alert.AlertType.INFORMATION);
                        done.setHeaderText("Done");
                        done.setContentText("Product purchased successfully!! ");
                        done.showAndWait();
                    }
                }
            });
            remove.setOnAction(e -> {
                U.getShoppingCart().removeProduct(P);
                cb.setItems(FXCollections.observableArrayList(U.getShoppingCart().getCartProducts()));
                Scene updated_cart = new Scene(cart_page(primarystage,scenes),sceneX,sceneY);
                primarystage.setScene(updated_cart);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setHeaderText("INFO");
                info.setContentText(P.getProductName() + " Removed from cart!!");
                info.showAndWait();
            });
            HBox hbox = new HBox(25,remove,buy);
            hbox.layoutXProperty().bind(this.widthProperty().divide(5.5));
            hbox.layoutYProperty().bind(this.heightProperty().divide(1.1));
            this.getChildren().add(hbox);
        }

        void set_image(String url) {
            Image image = new Image(url);
            ImageView iv =new ImageView(image);
            iv.setFitWidth(200);
            iv.setFitHeight(250);
            this.getChildren().add(iv);
        }
    }

    static Pane cart_page(Stage primarystage, ArrayList<Scene> scenes){
        BorderPane bord = new BorderPane();
        bord.setStyle("-fx-background-color: #E2FAFA;-fx-text-fill: #483d8b;");
        bord.setPadding(new Insets(40,40,40,40));
        HBox hbox = new HBox(100);
        hbox.setStyle("-fx-background-color: #E2FAFA;-fx-text-fill: #483d8b; -fx-font-size: 20px;");
        Label lb = new Label("My Products: ");
        lb.setFont(new Font("Arial",20));
        lb.setAlignment(Pos.CENTER);
        Button checkout = new Button("Check Out");
        Button back = new Button("Back");
        back.setOnAction(e -> primarystage.setScene(scenes.get(0)));

        checkout.setOnAction(e -> {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setHeaderText("CONFIRMATION");
            confirmation.setContentText("Confirm the purchase of All products!! ");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (U.getBalance() < U.totalPrice()) {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setHeaderText("WARNING");
                    warning.setContentText("Insufficient Balance!!\n" + "Please charge up your balance ");
                    warning.showAndWait();
                } else {
                    if(S.sell(U)){
                        balance.setText("Your Balance: " + Double.toString(U.getBalance()));
                        balance_copy.setText("Your Balance: " +Double.toString(U.getBalance()));
                        Scene updated_cart = new Scene(cart_page(primarystage,scenes),sceneX,sceneY);
                        primarystage.setScene(updated_cart);

                        cb.setItems(FXCollections.observableArrayList(U.getShoppingCart().getCartProducts()));
                        Alert done = new Alert(Alert.AlertType.INFORMATION);
                        done.setHeaderText("Done");
                        done.setContentText("Products purchased successfully!! ");
                        done.showAndWait();
                    }else{Alert warning = new Alert(Alert.AlertType.WARNING);
                        warning.setHeaderText("WARNING");
                        warning.setContentText("Your cart is empty!!");
                        warning.showAndWait();}


                }
            }
        });
        ButtonBar btBar = new ButtonBar();
        btBar.getButtons().addAll(checkout,back);
        balance_copy.setStyle("-fx-text-fill: #483d8b; -fx-font-size: 20px;");
        hbox.getChildren().addAll(lb,balance_copy,btBar);
        bord.setTop(hbox);
        FlowPane flow = new FlowPane();
        flow.setStyle("-fx-background-color: #dff8f8");
        flow.setOrientation(Orientation.HORIZONTAL);
        flow.setHgap(20);
        flow.setVgap(20);
        flow.setPadding(new Insets(40,40,40,40));

        ArrayList<Product> cartProducts = U.getShoppingCart().getCartProducts();

        if(cartProducts.isEmpty()){
            Label lb2 = new Label("Your cart is Empty!!");
            lb2.setStyle("-fx-background-color: #E2FAFA;-fx-text-fill: #483d8b; -fx-font-size: 20px;");
            bord.setCenter(lb2);
        }
        else{
        for (int i = 0;i<cartProducts.size();i++) {
            Cell C = new Cell(cartProducts.get(i), primarystage, scenes,"");
            C.set_image(cartProducts.get(i).getImage_url());
            flow.getChildren().add(C);
        }
        bord.setCenter(flow);

        }
       return bord;
    }



}
