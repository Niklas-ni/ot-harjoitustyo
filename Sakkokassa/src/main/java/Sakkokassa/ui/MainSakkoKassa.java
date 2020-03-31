package Sakkokassa.ui;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class MainSakkoKassa extends Application {
   @Override
    public void init() throws Exception {
    }
       @Override
    public void start(Stage primaryStage) {               
      Label teksti = new Label("Tekstielementti");

        FlowPane komponenttiryhma = new FlowPane();
        komponenttiryhma.getChildren().add(teksti);

        Scene nakyma = new Scene(komponenttiryhma);

        primaryStage.setScene(nakyma);
        primaryStage.show();
    
    }
    @Override
    public void stop() {
      // tee lopetustoimenpiteet täällä
      System.out.println("sovellus sulkeutuu");
    }
 public static void main(String[] args) {
        launch(args);
    }
    
}
