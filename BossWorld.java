import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossWorld extends World
{

    /**
     * Constructor for objects of class BossWorld.
     * 
     */
    public BossWorld(Player p)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        addObject(new Platform(0, 0, 2), 450, 300);
        addObject(new Platform(0, 0, 2), 150, 300);
        addObject(new Platform(0, 0, 5), 300, 395);
        addObject(new Platform(0, 0, 1.4), 300, 200);
        addObject(p, 300, 130);
        if(p.drunk)addObject(new Boss(p), 50, 395);
        addObject(new Boss(p), 550, 150);
        //addObject(new BossHealth(10), 300, 50);
        int n = 0;
        for(int i = 0; i < p.getHealth(); i++){
            Life l = new Life(i);
            addObject(l, 30+n, 20);
            n+=25;
        }
        String coin = "Coins: ";
        if(p.greed) coin = "Gold: ";
        Counter actCounter = new Counter(coin);
        addObject(actCounter, 540, 20);
        actCounter.setValue(p.getCoins());
    }
}
