package kassaui;

import domain.Payboxservice;
import dao.SqlCashboxdao;
import dao.SqlPlayerdao;
import domain.Player;
import domain.PlayerService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
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

    private PlayerService playerservice;
    private Payboxservice payboxservice;
    private Scene paytableScene;
    private Scene newCashBoxScene;
    private Scene loginScene;
    private Scene AdminScene;

    ListView<String> playersammounts = new ListView<>();
    ListView<String> playersammounts1 = new ListView<>();

    private Label menuLabel = new Label();
    private Label menuLabel2 = new Label();

    @Override
    public void init() throws Exception {

        SqlCashboxdao Start = new SqlCashboxdao();
        Payboxservice payBoxStart = new Payboxservice(Start);
        this.payboxservice = payBoxStart;

    }

    public ObservableList<String> makeList(String name) throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (String playersammounts : playerservice.getAll(name)) {
            items.add(playersammounts);
        }
        return items;
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
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
            try {
                if (payboxservice.login(usernameInput.getText().toUpperCase())) {
                    loginMessage.setText("");

                    SqlPlayerdao loggedinUser = new SqlPlayerdao(usernameInput.getText());
                    PlayerService loggedIn = new PlayerService(loggedinUser);
                    this.playerservice = loggedIn;
                    playersammounts.setItems(makeList(usernameInput.getText()));
                    playersammounts1.setItems(makeList(usernameInput.getText()));
                    menuLabel2.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                    menuLabel.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
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
        PasswordField newPasswordInput = new PasswordField();
        newPasswordInput.setPromptText("Your password");
        Label newNameLabel = new Label("Password");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newPasswordInput);
        Label userCreationMessage = new Label();
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));
        createNewUserButton.setOnAction(e
                -> {
             try {
            String username = newUsernameInput.getText();
            String Password = newPasswordInput.getText();
            if (username.isBlank() || Password.isBlank() || username.length() <= 2 || Password.length() < 4) {
                userCreationMessage.setText("TeamName or Password too short. Or TeamName starts with number");
                userCreationMessage.setTextFill(Color.RED);
            }
            
            else if (payboxservice.createUser(username, Password)) {
                    userCreationMessage.setText("");
                    newUsernameInput.setText("");
                    newPasswordInput.setText("");
                    loginMessage.setText("new CashBox created");
                    loginMessage.setTextFill(Color.GREEN);
                    primaryStage.setScene(loginScene);
            } else {
                    userCreationMessage.setText("Team Already Made Or starts with number");
                    userCreationMessage.setTextFill(Color.RED);

                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSakkoKassa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
        );

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);
        newCashBoxScene = new Scene(newUserPane, 400, 400);
        ScrollPane CashBoxpaytableScollbar = new ScrollPane();
        BorderPane mainPanepaybox = new BorderPane(CashBoxpaytableScollbar);
        paytableScene = new Scene(mainPanepaybox, 400, 550);
        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("Logout");
        menuPane.getChildren()
                .addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e -> {
            primaryStage.setScene(loginScene);
        }
        );
        HBox createForm = new HBox(10);
        Button Password = new Button("Admin");
        PasswordField PasswordInput = new PasswordField();
        PasswordInput.setPromptText("Password");
        createForm.getChildren().addAll(PasswordInput, Password);
        Label PasswordMessage = new Label();
        Password.setOnAction(e -> {
            if (PasswordInput.getText().equals(payboxservice.getLoggedUser().getPassword())) {
                try {
                    menuLabel2.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                    menuLabel.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                    primaryStage.setScene(AdminScene);
                    PasswordInput.setText("");
                    PasswordInput.setPromptText("Password");
                } catch (SQLException ex) {
                    Logger.getLogger(MainSakkoKassa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                PasswordInput.setText("");
                PasswordInput.setPromptText("Wrong Password");
            }
        }
        );
        mainPanepaybox.setCenter(playersammounts1);
        mainPanepaybox.setBottom(createForm);
        mainPanepaybox.setTop(menuPane);

        ScrollPane CashBoxAdminScollbar = new ScrollPane();
        BorderPane mainAdminPane = new BorderPane(CashBoxAdminScollbar);
        AdminScene = new Scene(mainAdminPane, 400, 550);

        HBox menuAdminPane = new HBox(10);
        Region menuAdminSpacer = new Region();

        HBox.setHgrow(menuAdminSpacer, Priority.ALWAYS);
        Button BackAdminButton = new Button("Back");

        menuAdminPane.getChildren().addAll(menuLabel2, menuAdminSpacer, BackAdminButton);
        BackAdminButton.setOnAction(e -> {
            primaryStage.setScene(paytableScene);
            try {
                menuLabel.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                menuLabel2.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                playersammounts.setItems(makeList(payboxservice.getLoggedUser().getName()));
                playersammounts1.setItems(makeList(payboxservice.getLoggedUser().getName()));

            } catch (SQLException ex) {
                Logger.getLogger(MainSakkoKassa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        HBox createAdminForm = new HBox(2);
        Button playerAmount = new Button("Add");
        TextField AdminPlayer = new TextField();
        TextField AdminAmmount = new TextField();
        AdminPlayer.setMaxWidth(100);
        AdminAmmount.setMaxWidth(100);
        AdminPlayer.setPromptText("Player name");
        AdminAmmount.setPromptText("Player amount");
        createAdminForm.getChildren().addAll(AdminPlayer, AdminAmmount, playerAmount);
        playerAmount.setOnAction(e -> {
            try {
                if (AdminPlayer.getText().isBlank() || AdminAmmount.getText().isBlank() || AdminAmmount.getText().substring(0, 1).matches("[a-zA-Z]")) {
                    AdminAmmount.setText("");
                    AdminAmmount.setPromptText("Need a Number");
                } else {
                    playerservice.addPlayer(new Player(AdminPlayer.getText().toUpperCase().strip(), Integer.parseInt(AdminAmmount.getText())), payboxservice.getLoggedUser().getName());
                    AdminPlayer.setText("");
                    AdminAmmount.setText("");
                    AdminPlayer.setPromptText("Player name");
                    AdminAmmount.setPromptText("Player amount");
                    playersammounts1.setItems(makeList(payboxservice.getLoggedUser().getName()));
                    playersammounts.setItems(makeList(payboxservice.getLoggedUser().getName()));
                    menuLabel2.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                    menuLabel.setText("Team: " + payboxservice.getLoggedUser().getName() + ":s CashBox Missing: " + playerservice.getSumfromTable(payboxservice.getLoggedUser().getName()) + " Money in Bank: " + playerservice.getSumfromAlLTimeTable(payboxservice.getLoggedUser().getName()));
                }

            } catch (SQLException ex) {
                Logger.getLogger(MainSakkoKassa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        mainAdminPane.setCenter(playersammounts);
        mainAdminPane.setBottom(createAdminForm);
        mainAdminPane.setTop(menuAdminPane);

        primaryStage.setTitle("PayTables");
        primaryStage.setScene(loginScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e
                -> {
            System.out.println("Ciao");
        }
        );
    }

    ;

        @Override
    public void stop() throws SQLException {
    }

    public static void main(String[] args) throws SQLException, Exception {
        launch(args);

    }

}
