import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossHealth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossHealth extends Actor
{
    /**
     * Act - do whatever the BossHealth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BossHealth(int width){
    GreenfootImage img = getImage();
    if(width == 10){
    img.scale(400,100);
    }else if (width == 9){
    img.scale(360,100);
    }else if ( width ==8){
    img.scale(320,100);
    }else if (width ==7){
    img.scale(280,100);
    }else if (width ==6){
    img.scale(240,100);
    }else if (width == 5){
    img.scale(200,100);
    }else if(width == 4){
    img.scale(160,100);
    }else if (width == 3){
    img.scale(120,100);
    }else if (width == 2){
    img.scale(80,100);
    }else if (width ==1){
    img.scale(40,100);
    }
    
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
