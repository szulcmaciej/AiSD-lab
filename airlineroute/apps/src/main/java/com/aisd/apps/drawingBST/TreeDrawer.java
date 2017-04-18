package com.aisd.apps.drawingBST;/**
 * Created by Lenovo on 2017-04-18.
 */

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.datastructure.symbolTable.BinarySearchTreeST;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

public class TreeDrawer extends Application {

    BinarySearchTreeST<Integer, String> tree;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private StackPane root;
    private Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Button newTreeButton = new Button();
        newTreeButton.setText("Nowe drzewo");
        newTreeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setRandomTree();
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawTree(graphicsContext, tree);
            }
        });

        Button balanceTreeButton = new Button();
        balanceTreeButton.setText("Wyważanie drzewa");
        balanceTreeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tree.balanceTree();
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawTree(graphicsContext, tree);
            }
        });

        canvas = new Canvas(800, 600);
        graphicsContext = canvas.getGraphicsContext2D();
        //setTree();
        setRandomTree();
        tree.balanceTree();
        drawTree(graphicsContext, tree);
        //drawShapes(graphicsContext);

        root = new StackPane();
        //Pane root = new Pane();

        newTreeButton.setTranslateX(-320);
        newTreeButton.setTranslateY(-270);
        newTreeButton.setMinWidth(120);

        balanceTreeButton.setTranslateX(-320);
        balanceTreeButton.setTranslateY(-240);
        balanceTreeButton.setMinWidth(120);

        root.getChildren().add(canvas);
        root.getChildren().add(newTreeButton);
        root.getChildren().add(balanceTreeButton);

        scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Tree Drawer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setEverythingUpAndDraw(){

    }

    private void drawTree(GraphicsContext gc, BinarySearchTreeST tree){
        List<BinarySearchTreeST.DrawableItem> drawableItems = new CustomArrayList<>();
        tree.getDrawableItemsList(drawableItems, 400, 30, 180, 50, 10);
        gc.setTextAlign(TextAlignment.CENTER);

        for(BinarySearchTreeST.DrawableItem drawableItem : drawableItems){
            if(drawableItem instanceof BinarySearchTreeST.DrawableLine){
                BinarySearchTreeST.DrawableLine line = (BinarySearchTreeST.DrawableLine) drawableItem;
                gc.strokeLine(line.getxOrigin(), line.getyOrigin(), line.getxEnd(), line.getyEnd());
            }
            else if(drawableItem instanceof BinarySearchTreeST.DrawableString){
                BinarySearchTreeST.DrawableString s = (BinarySearchTreeST.DrawableString) drawableItem;
                gc.strokeText(s.getString(), s.getxOrigin(), s.getyOrigin());
            }
        }
    }

    private void setTree(){
        tree = new BinarySearchTreeST<>();
        tree.put(8, "dziesięć");
        tree.put(2, "dwa");
        tree.put(15, "dwanaście");
        tree.put(14, "dziesięć");
        tree.put(3, "trzy");
        tree.put(30, "trzydzieści");
        tree.put(4, "cztery");
        tree.put(5, "pięć");
        tree.put(6, "sześć");
        tree.put(21, "sześć");
        tree.put(13, "sześć");
        tree.put(50, "sześć");
        tree.put(50, "sześć");
        tree.put(30, "sześć");
        tree.put(10, "sześć");
        tree.put(15, "sześć");
        tree.put(70, "sześć");
        tree.put(15, "sześć");
        tree.put(9, "sześć");
        //tree.put(11, "sześć");
        tree.put(15, "sześć");
        //tree.put(35, "sześć");
        tree.put(38, "sześć");
        //tree.put(51, "sześć");
    }

    private void setRandomTree(){
        tree = new BinarySearchTreeST<>();
        Random random = new Random();
        int treeSize = random.nextInt(100) + 1;
        for(int i = 0; i < treeSize; i++){
            tree.put(random.nextInt(1000), "nic");
        }
    }

}
