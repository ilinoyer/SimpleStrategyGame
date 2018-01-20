package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import sample.buildings.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    Button goldMineButton;

    @FXML
    Button loggersLodgeButton;

    @FXML
    Button lumberMillButton;

    @FXML
    Button mintButton;

    @FXML
    Button quarryButton;

    @FXML
    Button reverseButton;

    @FXML
    Button endTurnButton;

    @FXML
    Label moneyLabel;

    @FXML
    Text currSelectedBuilding;

    @FXML
    Text totalIncome;

    @FXML
    Text turnCounter;

    @FXML
    GridPane boardPane;

    private List<Button> buttonList;
    private List<Button> board;
    private int rowNum;
    private int colNum;
    private Player player;


    public Controller()
    {
        this.colNum = 6;
        this.rowNum = 6;
        this.player = new Player(colNum*rowNum);
        buttonList = new ArrayList<>();
        board = new ArrayList<>();

    }

    private void initBoard()
    {
        for(int i = 0 ; i < rowNum; ++i)
        {
            for(int j = 0; j < colNum; j ++)
            {
                BoardButton button = new BoardButton(i,j);
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
            player.collectIncome();
            updateView();
        });

    }

    private class BoardButton extends Button
    {
        private Position position;

        public BoardButton(int xPos, int yPos)
        {
            position = new Position(xPos,yPos);
        }

        public Position getPosition() {
            return position;
        }
    }
}
