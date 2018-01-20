package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sample.buildings.*;
import sample.save.CareTaker;
import sample.save.Originator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private Button goldMineButton;

    @FXML
    private Button loggersLodgeButton;

    @FXML
    private Button lumberMillButton;

    @FXML
    private Button mintButton;

    @FXML
    private Button quarryButton;

    @FXML
    private Button reverseButton;

    @FXML
    private Button endTurnButton;

    @FXML
    private Label moneyLabel;

    @FXML
    private Text currSelectedBuilding;

    @FXML
    private Text totalIncome;

    @FXML
    private Text turnCounter;

    @FXML
    private GridPane boardPane;

    private List<Button> buttonList;
    private List<Button> board;
    private int rowNum;
    private int colNum;
    private Player player;
    private Originator originator;
    private CareTaker careTaker;


    public MainController()
    {
        this.colNum = 6;
        this.rowNum = 6;
        this.player = new Player(colNum*rowNum);
        buttonList = new ArrayList<>();
        board = new ArrayList<>();
        originator = new Originator();
        careTaker = new CareTaker();
        originator.setPlayer((Player) DeepCopy.copy(player));
    }

    private void initBoard()
    {
        boardPane.getChildren().clear();

        for(int i = 0 ; i < rowNum; ++i)
        {
            for(int j = 0; j < colNum; j ++)
            {
                Building building = player.getBuilding(i,j);
                BoardButton button = new BoardButton(i,j);

                if(building != null)
                    button.setText(building.toString());

                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(button);
                AnchorPane.setBottomAnchor(button,0.0);
                AnchorPane.setLeftAnchor(button,0.0);
                AnchorPane.setRightAnchor(button,0.0);
                AnchorPane.setTopAnchor(button,0.0);
                boardPane.add(anchorPane, i,j);

                button.setOnAction(event -> {
                    if(button.getText().equals("") && !currSelectedBuilding.getText().equals("None"))
                    {
                        if(isBuildingAllowed(currSelectedBuilding.getText()))
                        {
                            button.setText(currSelectedBuilding.getText());
                            createBuilding(currSelectedBuilding.getText(), button);
                        }

                    }

                });
            }
        }
    }

    private void createBuilding(String buildingType, BoardButton button)
    {
        Building building;
        switch (buildingType)
        {
            case "Kamieniołom":
               building = new Quarry(button.getPosition());
               player.addBuilding(building);
               updateView();
                break;
            case "Mennica":
                building = new Mint(button.getPosition());
                player.addBuilding(building);
                updateView();
                break;
            case "Tartak":
                building = new LumberMill(button.getPosition());
                player.addBuilding(building);
                updateView();
                break;
            case "Chatka Drwala":
                building = new LoggersLodge(button.getPosition());
                player.addBuilding(building);
                updateView();
                break;
            case "Kopalnia złota":
                building = new GoldMine(button.getPosition());
                player.addBuilding(building);
                updateView();
                break;
        }
    }

    private boolean isBuildingAllowed(String buildingName) {
        boolean result = false;
        switch (buildingName)
        {
            case "Kamieniołom":
                if(player.getMoney() >= 500)
                    result = true;
                break;
            case "Mennica":
                if(player.getMoney() >= 10000 && player.isBuildingCorrect())
                    result = true;
                break;
            case "Tartak":
                if(player.getMoney() >= 3000)
                    result = true;
                break;
            case "Chatka Drwala":
                if(player.getMoney() >= 1500)
                    result = true;
                break;
            case "Kopalnia złota":
                if(player.getMoney() >= 5000 && player.isBuildingCorrect())
                    result = true;
                break;
        }

        return result;
    }

    private void initBuildingButtons()
    {
        buttonList.add(quarryButton);
        buttonList.add(goldMineButton);
        buttonList.add(loggersLodgeButton);
        buttonList.add(lumberMillButton);
        buttonList.add(mintButton);

        for(Button button : buttonList)
        {
            button.setOnAction(event -> currSelectedBuilding.setText(button.getText()));
        }
    }

    private void updateView()
    {
        moneyLabel.setText(String.valueOf(player.getMoney()));
        totalIncome.setText(String.valueOf(player.getIncome()));
        turnCounter.setText(String.valueOf(player.getTurnCounter()));

        if(player.getMoney() >= 10000 && player.isBuildingCorrect())
            mintButton.setDisable(false);
        else
            mintButton.setDisable(true);

        if(player.getMoney() >= 5000 && player.isBuildingCorrect())
            goldMineButton.setDisable(false);
        else
            goldMineButton.setDisable(true);

        if(player.getMoney() >= 3000)
            lumberMillButton.setDisable(false);
        else
            lumberMillButton.setDisable(true);

        if(player.getMoney() >= 500)
            quarryButton.setDisable(false);
        else
            quarryButton.setDisable(true);

        if(player.getMoney() >= 1500)
            loggersLodgeButton.setDisable(false);
        else
            loggersLodgeButton.setDisable(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateView();
        initBoard();
        initBuildingButtons();

        endTurnButton.setOnAction(event -> {
            careTaker.add(originator.save());
            player.collectIncome();
            updateView();
            originator.setPlayer((Player) DeepCopy.copy(player));
        });

        reverseButton.setOnAction(event -> {
            try {
                originator.restore(careTaker.get());
                player = (Player) DeepCopy.copy(originator.getPlayer());
                updateView();
                initBoard();
            }
            catch (NoSuchElementException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nie można cofnac", ButtonType.OK);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            }
        });

    }

    private class BoardButton extends Button
    {
        private Position position;

        private BoardButton(int xPos, int yPos)
        {
            position = new Position(xPos,yPos);
        }

        private Position getPosition() {
            return position;
        }
    }
}
