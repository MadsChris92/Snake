package SnakeLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gamer on 16-02-2017.
 */
public class Snake implements GameObject{
    int length = 1;
    int x, y;
    boolean pickUpItem = false;

    ArrayList<Integer> posY = new ArrayList<>();


    ArrayList<Integer> posX = new ArrayList<>();

    public void setPickUpItem(boolean pickUpItem) {
        this.pickUpItem = pickUpItem;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;

        if(pickUpItem == true){
            posX.add(length-2,x);
            posY.add(length-2, y);
            pickUpItem = false;
        }else{
            posX.add(length-1,x);
            posY.add(length-1, y);
        }



        if(length < posX.size()){
            posX.remove(length);
            posY.remove(length);
        }

        System.out.println(posX.toString()+ " " + posY.toString());
    }


    public ArrayList<Integer> getPosY() {
        return posY;
    }

    public ArrayList<Integer> getPosX() {
        return posX;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void update() {

    }


}
