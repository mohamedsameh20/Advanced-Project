package MainProject;

//import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;
import java.util.ArrayList;



public class GUI_MainMenu extends Application {

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #E2FAFA");

        ArrayList<Scene> scenes = new ArrayList<Scene>();
        Pane root = welcome_page(pane,primaryStage,scenes);
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Welcome"); // Set the stage title
        primaryStage.setScene(scene); // Put scene in stage
        primaryStage.show();



    }
    void MainStore(BorderPane pane,Stage primaryStage, ArrayList<Scene> scenes){
        pane.setTop(GUI_MainStore.user(pane,primaryStage, scenes));
        pane.setLeft(GUI_MainStore.Menu(pane,primaryStage,scenes));
        pane.setCenter(GUI_MainStore.store(primaryStage,scenes));
        Scene Home = new Scene( pane, 1200, 1000);
        scenes.add(Home);
    }

    Pane welcome_page(BorderPane pane,Stage primaryStage, ArrayList<Scene> scenes){
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setStyle("-fx-background-color: #E2FAFA; -fx-padding: 10;");
        Label title = new Label("Welcome to the Store");
        title.setFont(new Font("Arial", 24));
        Label message = new Label("Click the button below to enter the store.");
        message.setFont(new Font("Arial", 16));
        Button goToStoreButton = new Button("Go to Store");
        goToStoreButton.setFont(new Font("Arial", 18));
        goToStoreButton.setOnAction(e -> {
            MainStore(pane,primaryStage,scenes);
            primaryStage.setScene(scenes.get(0));
        });
        FadeTransition ft = new FadeTransition(Duration.millis(500), welcomeLayout);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        welcomeLayout.getChildren().addAll(title, message, goToStoreButton);
        StackPane root = new StackPane();
        root.getChildren().add(welcomeLayout);
        return root;
    }


}
