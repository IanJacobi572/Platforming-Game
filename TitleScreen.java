import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    Two t = new Two();
    Endless e = new Endless();
    CoinTotal1 c = new CoinTotal1();
    int coinsToEnd = 0;
    //StringInputBox text = new StringInputBox();
    int timer;
    //public boolean meme;
    GifImage rickRoll;
    GifImage backgroundGif;
    int coinPlayer = 0;
    int healthPlayer = 3;
    boolean drunk = false;
    boolean chosen;
    boolean sanic = false;
    public static GreenfootSound rick = new GreenfootSound("RickRoll.mp3");
    public static GreenfootSound rero = new GreenfootSound("Rero.mp3");
    public static GreenfootSound johnCena = new GreenfootSound("JohnCenaMidi.mp3");
    boolean teleporting;
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        backgroundGif = new GifImage("ba3.gif");
        //rickRoll = new GifImage("rickroll.gif");
        addObject(c,300,235);
        addObject(t, 125, 105);
        addObject(e, 455, 105);
        //addObject(text, 300, 300);
        Greenfoot.start();
        prepare();
    }

    public void act(){
        
        if(Greenfoot.mouseClicked(t)){
            timer = t.getTimer();
            coinsToEnd = -1;
            chosen = true;
        }
        else if(Greenfoot.mouseClicked(e)){
            timer = -1;
            coinsToEnd = -1;
            chosen = true;
        }
        else if(Greenfoot.mouseClicked(c)){
            timer = -1;
            chosen = true;
            
            coinsToEnd = c.getCoins();
        }
        if(c.getString() != null){
            if(c.getString().equals("jojo")){
                GreenfootImage img = backgroundGif.getCurrentImage();
                img.scale(600, 400);
                setBackground(img);
                if(!rero.isPlaying()) rero.play();
                //meme = true;
            }
            else if(c.getString().equals("nevergonnagiveyouup")){
                
                if(!rick.isPlaying()) rick.play();
                
            }
            else if(c.getString().equals("youcantseeme")){
                if(!johnCena.isPlaying()) johnCena.play();
            }
            if(c.getString().equals("crackacoldone")) drunk = true;
            if(c.getString().equals("greedisgood")) coinPlayer = 500;
            if(c.getString().equals("healthyliving")) healthPlayer = 5;
            if(c.getString().equals("godly")) healthPlayer = 10;
            if(c.getString().equals("bonehurtingjuice")) healthPlayer = 1;
            if(c.getString().equals("whereami")) teleporting = true;
            if(c.getString().equals("killme"))Greenfoot.setWorld(new GameOver());
            if(c.getString().equals("sanic")) sanic = true;
        }
        if(Greenfoot.getKey() == ("enter") && chosen){
            Greenfoot.setWorld(new MyWorld(timer, coinsToEnd,  coinPlayer,  healthPlayer,  drunk, teleporting, sanic));
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}

