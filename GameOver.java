import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    private boolean wongame;
    GreenfootSound dead;
    GreenfootSound fanfare;
    public GameOver(boolean won)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        if(MyWorld.bite.isPlaying()) MyWorld.bite.stop();
        GreenfootImage img = new GreenfootImage("WinGame.png");
        fanfare = new GreenfootSound("fanfare.mid");
        if(won){
            setBackground(img);
            fanfare.setVolume(100);
            fanfare.play();
        }
        else{
            dead = new GreenfootSound("Dead.wav");
            dead.setVolume(75);
            dead.play();
        }
    }

    public void act(){
        if(Greenfoot.mouseClicked(null)) {
            dead.stop();
            fanfare.stop();
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
