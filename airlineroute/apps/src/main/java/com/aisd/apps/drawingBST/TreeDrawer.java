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
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;

public class TreeDrawer extends Application {

    BinarySearchTreeST<Integer, String> tree;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button();
        button.setText("Jestem przyciskiem!");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("dalej jestem przyciskiem :(");
            }
        });

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        setTree();
        tree.balanceTree();
        drawTree(graphicsContext, tree);
        //drawShapes(graphicsContext);


        StackPane root = new StackPane();
        //root.getChildren().add(button);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Tree Drawer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree(GraphicsContext gc, BinarySearchTreeST tree){
        List<BinarySearchTreeST.DrawableItem> drawableItems = new CustomArrayList<>();
        tree.getDrawableItemsList(drawableItems, 400, 30, 150, 50, 10);
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
        tree.put(11, "sześć");
        tree.put(15, "sześć");
    }

}
