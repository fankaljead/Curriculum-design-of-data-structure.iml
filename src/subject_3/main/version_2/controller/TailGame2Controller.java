package subject_3.main.version_2.controller;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import subject_3.main.version_1.model.TailGame;
import subject_3.main.version_2.view.ShowAnswers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Zhou Xianghui
 * Time: 2017/12/7 20:39
 * Description: 硬币问题 控制类 版本2
 */
public class TailGame2Controller implements Initializable{

    protected static final String PATH = "../../coin/";
    protected static final String SUFFIX = ".jpg";
    protected int rowNum = 4;
    protected int columnNum = 4;
    protected ImageView imageView;
    protected char[] inputFormCoins = new char[rowNum * columnNum];

    @FXML
    protected GridPane showCoins;
    @FXML
    protected JFXSlider selectColumnNum;
    @FXML
    protected JFXSlider selectRowNum;

    protected TailGame tailGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectColumnNum.setValue(4);
        selectRowNum.setValue(4);
        startGame();

        //选择行的事件监听
        selectRowNum.valueProperty().addListener((observable, oldValue, newValue) -> {
            startGame();
        });

        //选择列的事件监听
        selectColumnNum.valueProperty().addListener((observable, oldValue, newValue) -> {
            startGame();
        });


    }

    @FXML
    protected void startAction(MouseEvent e){
        initialInput();
        startGame();
    }


    //查看解的情况
    @FXML
    protected void checkAnswersAction(MouseEvent e) throws Exception {

        tailGame = new TailGame(this.rowNum, this.columnNum);

        ShowAnswers showAnswers = new ShowAnswers(tailGame, inputFormCoins);
        showAnswers.start(new Stage());
    }

    //选取要求解的情况 -> 输入 点击硬币
    @FXML
    protected void selectAction(MouseEvent e){
        String source1 = e.getSource().toString(); //yields complete string
        String source2 = e.getPickResult().getIntersectedNode().getId(); //获取点击的节点id returns JUST the id of the object that was clicked

        imageView = (ImageView) (showCoins.getScene().lookup("#" + source2));
        if(inputFormCoins[Integer.valueOf(source2)] == 'H'){
            imageView.setImage(new Image(getClass().getResource(PATH + 'T' + SUFFIX).toExternalForm()));
            inputFormCoins[Integer.valueOf(source2)] = 'T';
        }else {
            imageView.setImage(new Image(getClass().getResource(PATH + 'H' + SUFFIX).toExternalForm()));
            inputFormCoins[Integer.valueOf(source2)] = 'H';
        }
        tailGame = new TailGame(this.rowNum, this.columnNum);

    }




    protected void startGame(){
//        tailGame = new TailGame(this.rowNum, this.columnNum);
        initialInput();
        this.rowNum = (int)selectRowNum.getValue();
        this.columnNum = (int)selectColumnNum.getValue();


        showCoins.getChildren().clear();
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.columnNum; j++) {
                Image image = new Image(getClass().getResource(PATH + 'H' + SUFFIX).toExternalForm());//设置图片
                ImageView imageView = new ImageView(image);
                imageView.setId(String.valueOf(i * this.rowNum + j));
                showCoins.add(imageView, j, i);
            }
        }
    }



    //初始化输入
    protected void initialInput(){
        for (int i = 0; i < inputFormCoins.length; i++) {
            inputFormCoins[i] = 'H';
        }
    }

}
