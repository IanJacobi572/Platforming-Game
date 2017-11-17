import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Endless here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Endless extends Actor
{
    /**
     * Act - do whatever the Endless wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Two two;
    public CoinTotal1 coin;
    GreenfootImage img;
    GreenfootImage baseImg;
    public Endless(){
        img =getImage();
        img.scale(160, 28);
        //setImage(baseImg);
    }

    public void act() 
    {
        List<Two> twoList = getObjectsInRange(600, Two.class);
        two = twoList.get(0);
        List<CoinTotal1> coinList =  getObjectsInRange(600, CoinTotal1.class);
        coin = coinList.get(0);
        if(Greenfoot.mouseClicked(this)){
            //Greenfoot.setWorld(new MyWorld(-1));
            GreenfootSound select = new GreenfootSound("Select.wav");
            select.setVolume(75);
            select.play();
            //GreenfootImage img = new GreenfootImage("Endless", 30, Color.BLACK, Color.WHITE);
            //setImage(img);
        }
        else if(Greenfoot.mouseClicked(two) || Greenfoot.mouseClicked(coin) ){
            //GreenfootImage img = new GreenfootImage("Endless", 20, Color.BLACK, Color.WHITE);
            //setImage(baseImg);
        }
    }    
}
