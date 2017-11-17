import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Two here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Two extends Actor
{
    /**
     * Act - do whatever the Two wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    GreenfootImage img;
    GreenfootImage baseImg;
    public Endless end;
    public CoinTotal1 coin;
    public Two(){
        //GreenfootImage.setFont(Font );
        
        img =getImage();
        img.scale(160, 28);
        baseImg = new GreenfootImage("2 min", 25, Color.BLACK, Color.WHITE);
        //setImage(baseImg);
    }

    public void act() 
    {
        List<Endless> endList = getObjectsInRange(600, Endless.class);
        end = endList.get(0);
        List<CoinTotal1> coinTotal1 =  getObjectsInRange(600, CoinTotal1.class);
        coin = coinTotal1.get(0);
        if(Greenfoot.mouseClicked(this)){
GreenfootSound select = new GreenfootSound("Select.wav");
            select.play();
            //setImage(img);
        }
        else if(Greenfoot.mouseClicked(end) || Greenfoot.mouseClicked(coin)){

            //setImage(baseImg);
        }
    }    

    public int getTimer(){
        return 7200;
    }
}
