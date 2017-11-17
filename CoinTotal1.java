import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class CoinTotal1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoinTotal1 extends Actor
{
    /**
     * Act - do whatever the CoinTotal1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    String coinsString;
    public Two two;
    public Endless end;
    GreenfootImage img;
    int coinsInt;
    boolean jojo = false;
    //GreenfootImage baseImage;
    public CoinTotal1(){
        coinsInt = 10;
        img =getImage();
        img.scale(160, 28);
        //setImage(baseImage);
    }

    public void act() 
    {
        List<Two> twoList = getObjectsInRange(600, Two.class);
        two = twoList.get(0);
        List<Endless> endList = getObjectsInRange(600, Endless.class);
        end = endList.get(0);
        if(Greenfoot.mouseClicked(this)){
            coinsString = Greenfoot.ask("How many coins? (enter a number greater than 0, defaults to 10)");
            coinsInt = parseWithDefault(coinsString, 10);
            if(coinsInt <= 0) coinsInt = 10;
            GreenfootSound select = new GreenfootSound("Select.wav");
            select.setVolume(75);
            select.play();
            if(coinsString != null){
                
                //GreenfootImage img = new GreenfootImage(Integer.toString(coinsInt), 30, Color.BLACK, Color.WHITE);
                //setImage(img);
            }
        }

    }    

    public int getCoins(){
        return coinsInt;
    }

    public String getString(){
        return coinsString;
    }

    public static int parseWithDefault(String number, int defaultVal) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
