package kassaui;

import domain.Kassa;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainSakkoKassa extends Application {

    private Scene paytableScene;
    private Scene newCashBoxScene;
    private Scene loginScene;

    private VBox todoNodes;

    private Label menuLabel = new Label();

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) {
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Team Name");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("login");
        Button createButton = new Button("create new Team CashBox");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText("Team: " + username + ":s CashBox Situation");

            try {
                if (domain.Kassa.isacashbox(username)) {
                    loginMessage.setText("");
                    primaryStage.setScene(paytableScene);
                    usernameInput.setText("");
                } else {
                    loginMessage.setText("No Such Team");
                    loginMessage.setTextFill(Color.RED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSakkoKassa.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newCashBoxScene);
        });

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 300, 250);
        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Team Name");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newPasswordInput = new TextField();
        Label newNameLabel = new Label("Password");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newPasswordInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String Password = newPasswordInput.getText();

            if (username.length() == 2 || Password.length() < 4) {
                userCreationMessage.setText("TeamName or Password too short. Team name > 2, password > 4");
                userCreationMessage.setTextFill(Color.RED);
            } else try {
                if (Kassa.newcashboxpassword(username, Password)) {
                    userCreationMessage.setText("");
                    loginMessage.setText("new CashBox created");
                    loginMessage.setTextFill(Color.GREEN);
                    primaryStage.setScene(loginScene);
                } else {
                    userCreationMessage.setText("Team Already Made");
                    userCreationMessage.setTextFill(Color.RED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSakkoKassa.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);

        newCashBoxScene = new Scene(newUserPane, 300, 250);
        ScrollPane CashBoxScollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(CashBoxScollbar);
        paytableScene = new Scene(mainPane, 300, 250);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e -> {
            primaryStage.setScene(loginScene);
        });

        HBox createForm = new HBox(10);
        Button Password = new Button("Password");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField PasswordInput = new TextField();
        createForm.getChildren().addAll(PasswordInput, spacer, Password);

        todoNodes = new VBox(10);
        todoNodes.setMaxWidth(280);
        todoNodes.setMinWidth(280);

        CashBoxScollbar.setContent(todoNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        Password.setOnAction(e -> {
        });

        primaryStage.setTitle("SakkoKassa");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");

        });
    }

    @Override
    public void stop() {
        // tee lopetustoimenpiteet täällä
        System.out.println("sovellus sulkeutuu");
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

}
