package SnakeGUI;


import SnakeLogic.GameObject;
import SnakeLogic.Snake;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.*;

public class Controller {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private double fieldHeight;
    private double fieldWidth;
    private int width = 30;
    private int height = 20;
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =300;
    private int X;
    private int Y;
    private KeyCode keyPressed = KeyCode.BACK_SPACE;
    Snake snake = new Snake();

    ArrayList<Item> items = new ArrayList<Item>();

    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
        getRandomPosition();
        drawCanvas();
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize()
    {
                AddItems();

        calculateFields();
        getRandomPosition();
        //drawCanvas();

        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now)
            {
                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);
                }             }
        }.start();
    }

    private void AddItems() {
        items.add(new Item(Color.GREEN, 3,3));
        items.add(new Item(Color.RED, 12,9));


    }

    public void keyPressed(KeyCode keyCode)
    {
        this.keyPressed = keyCode;
    }

    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now)
    {
        switch (keyPressed)
        {
            case DOWN:
                this.Y++;
                break;
            case LEFT:
                this.X--;
                break;
            case RIGHT:
                this.X++;
                break;
            case UP:
                this.Y--;
                break;
        }
        //getRandomPosition();
        drawCanvas();
        //System.out.println(now);

    }

    /**
     * Get a random position
     */

    private void getRandomPosition() {
        this.X = random.nextInt(width);
        this.Y = random.nextInt(height);

        snake.setPosition(this.X, this.Y);
    }

    /**
     * Calculate height and width of each field
     */
    private void calculateFields() {
        this.fieldHeight = canvas.getHeight() / this.height;
        this.fieldWidth = canvas.getWidth() / this.width;
    }

    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        g.clearRect(0,0,width*fieldWidth ,height*fieldHeight);
/*
        // draw all fields
        g.setFill(Color.BLUE);
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                g.fillRoundRect(i*fieldWidth, j*fieldHeight, fieldWidth,fieldHeight, 5, 5);
            }
        }
*/
        // draw items
        for (Item item : items)
        {
            g.setFill(item.getColor());
            g.fillRoundRect(item.getX() * fieldWidth, item.getY() * fieldHeight, fieldWidth, fieldHeight, 5,5);
        }

        // draw 'player'

        if(items.get(0).getX() == this.X && items.get(0).getY() == this.Y){
            snake.setLength(snake.getLength()+1);
            snake.setPickUpItem(true);
        }

        for (int i = 0; i < snake.getLength(); i++) {
            snake.setPosition(this.X, this.Y);
            g.setFill(Color.WHITE);
            g.fillRoundRect(snake.getPosX().get(i) * fieldWidth, snake.getPosY().get(i) * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        }

        /*
        g.setFill(Color.WHITE);
        g.fillRoundRect(this.X * fieldWidth, this.Y * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        */
    }
}
