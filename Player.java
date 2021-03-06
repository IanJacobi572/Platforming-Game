import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 
     *'Run' button gets pressed in the environment.
     */

    private int shootCount;
    private int jumpHeight;
    private int doubleJumpHeight;
    private int walkSpeed;
    private double fallSpeed ;
    GreenfootImage left;
    GreenfootImage right;
    private boolean inTheAir ;
    private double dX ;
    private boolean jumped;
    private double dY ;
    Platform platBelow;
    private boolean shootL;
    private boolean shootR;
    int bossTimer;
    private boolean shootUp;
    Platform previousPlatform;
    private boolean isLeft;
    private boolean canMoveL;
    private boolean canMoveR;
    private int groundHeight;
    private int sideWidth;
    private World myWorld;
    int worldHeight;
    int worldWidth;
    int invulnTime;
    private int health;
    boolean teleporting;
    int timer;
    boolean drunk;
    int coins;
    int oldLife;
    boolean sanic;
    public boolean greed = false;
    boolean gotLife;
    int lifeMult;
    public Player(int coinP, int healthP, boolean d, boolean t, boolean s){
        timer = 0;
        teleporting = t;
        coins = coinP;
        health = healthP;
        if(coinP == 500) greed = true;
        drunk =d;
        //n = 0;
        left = new GreenfootImage("wizL.png");
        right = new GreenfootImage("wizR.png");
        sanic = s;
        setImage(right);
        jumpHeight = 12;
        doubleJumpHeight = 10;
        walkSpeed = 5;
        if(d){
            walkSpeed = -5;
            right = new GreenfootImage("wizL.png");
            left = new GreenfootImage("wizR.png");
        }
        if(s) walkSpeed = 20;
        fallSpeed = .85;

        inTheAir = false;
        dX = 0;
        dY = 0;
        Platform platBelow;
        bossTimer = 0;
        canMoveL = true;
        canMoveR = true;
        groundHeight = getImage().getHeight()/2;
        sideWidth = getImage().getWidth()/2;
        gotLife = false;
        lifeMult = 1;
    }

    public void addedToWorld(World myWorld)
    {
        this.myWorld = myWorld;
        this.worldHeight = myWorld.getHeight();
        this.worldWidth = myWorld.getWidth();
        invulnTime = 0;
        //health = 3;
        //coins = 0;
    }

    public void act() 
    {

        powerUp();
        getKey();  
        move(); 
        fallBelow();
        hit();
        shootDirection();
        //if(inTheAir) fall();

        if(!inTheAir) jumped = false;
        pickUpCoins();
        checkForNewLife();
        shootCount++;
        if(invulnTime > 0) invulnTime--;
        bossTimer++;
        timer ++;
    }

    private void fallBelow(){
        if(previousPlatform !=null){
            if (getY() >= worldHeight - groundHeight && previousPlatform.getY() <= worldHeight){
                if(previousPlatform.getX() == 600 && !inTheAir){
                    setLocation(previousPlatform.getX()-10, previousPlatform.getY()-groundHeight);
                    takeDamage();
                }
                else if(previousPlatform.getX() <= 0){
                    setLocation(previousPlatform.getX()+10, previousPlatform.getY()-groundHeight);
                    takeDamage();
                }
                else{
                    setLocation(previousPlatform.getX(), previousPlatform.getY()-groundHeight);
                    takeDamage();
                }

            }
            else if(previousPlatform.getY() > 400 && getY() > 400) spawnAtTopPlat();
            else if(platBelow!= null){
                if(platBelow.getY() >= 400 ){
                    spawnAtTopPlat();
                }
            }
        }
    }

    private void spawnAtRandomPlat(){

        List<Platform> platsInRange = getObjectsInRange(500, Platform.class);
        if(platsInRange != null){ 
            int randomPlat = Greenfoot.getRandomNumber(platsInRange.size());
            Platform p = platsInRange.get(randomPlat);
            if(p!=previousPlatform && p.getY() < worldHeight){
                //takeDamage();

                if(p.getX() >= 600){
                    setLocation(p.getX()-10, p.getY()-groundHeight);

                }
                else if(p.getX() <= 0){
                    setLocation(p.getX()+10, p.getY()-groundHeight);

                }
                else{
                    setLocation(p.getX(), p.getY()-groundHeight);
                }
            }

        }
    }

    private void spawnAtTopPlat(){
        //Platform top = null;
        //Platform last = null;
        List<Platform> platsInRange = getObjectsInRange(700, Platform.class);
        Platform top = platsInRange.get(1);

        for(Platform p : platsInRange){

            if(p.getY() < top.getY())
            {
                top = p;
            }
        }
        if(top!=previousPlatform && top.getY() < worldHeight){
            takeDamage();

            if(top.getX() >= 600){
                setLocation(top.getX()-10, top.getY()-groundHeight);

            }
            else if(top.getX() <= 0){
                setLocation(top.getX()+10, top.getY()-groundHeight);

            }
            else{
                setLocation(top.getX(), top.getY()-groundHeight);
            }
        }

    }

    private void takeDamage(){  //Subtracts health and removes highest heart
        if(invulnTime == 0){
            health--;
            Greenfoot.playSound("Hit_Hurt.wav");
            invulnTime = 45;
        }
        List <Life> lives = getObjectsInRange(700,Life.class);
        for(Life l : lives){
            if(l.getLife() == health){
                getWorld().removeObject(l);
            }
        }
        if(health == 0){

            Greenfoot.setWorld(new GameOver());
        }
    }

    private void shoot(int speedX, int speedY){
        int leftHand = getX()-getImage().getWidth()/2;
        int rightHand = getX()+getImage().getWidth()/2;

        if ( shootCount >= 25){

            if(shootL){
                if(!drunk) setImage(left);
                else if(drunk) setImage(right);
                if(!shootUp){
                    getWorld().addObject(new Bullet(speedX, speedY), leftHand, getY());

                }
                else {
                    getWorld().addObject(new Bullet(speedX, speedY), leftHand, getY());
                }
            }
            if (shootR){
                if(!drunk)setImage(right);
                if(drunk) setImage(left);
                if(!shootUp){
                    getWorld().addObject(new Bullet(speedX, speedY), rightHand, getY());
                }
                else{
                    getWorld().addObject(new Bullet(speedX, speedY), rightHand, getY());
                }
                if(shootUp){}
            }
            if(shootUp && !shootL && !shootR){

                if(getImage() == right){
                    getWorld().addObject(new Bullet(speedX, speedY), rightHand, getY());
                }
                else if(getImage() == left){
                    getWorld().addObject(new Bullet(speedX, speedY), leftHand, getY());
                }
            }
            shootCount = 0;
            Greenfoot.playSound("Shoot.wav");
        }
    }

    private void run ()
    {
        if(isLeft && canMoveL)
            dX = walkSpeed * -1;
        else if(!isLeft && canMoveR)
            dX = walkSpeed;
    }

    private void stop()
    {
        dX = 0;
    }

    private void jump()
    {
        dY= jumpHeight;
        inTheAir = true;
        //jumped = false;
    }

    private void doubleJump(){
        dY = doubleJumpHeight;
        inTheAir = true;
        jumped = true;
    }

    private void fall()
    {
        dY -=fallSpeed;
        //inTheAir = true;
    }
    //checks for a platform and falls if it doesnt find one
    private void move()
    {
        double newX = getX() + dX;
        double newY = getY() - dY;
        if(getX() >= 600) newX = 0 + dX;
        else if(getX() <= 0) newX = 600 + dX;
        platBelow = (Platform) getOneObjectAtOffset(0,groundHeight +5,Platform.class);
        if(platBelow == null) platBelow = (Platform) getOneObjectAtOffset(0,groundHeight ,Platform.class);
        if (platBelow != null)
        {
            GreenfootImage platImage = platBelow.getImage();
            int w = (int) (platImage.getWidth()/2 * platBelow.getScale());
            previousPlatform = platBelow;
            if (dY <0){
                if(getX() < platBelow.getX()+w && getX() > platBelow.getX()-w){
                    dY = -1;
                    inTheAir = false;

                    int topOfPlat = platBelow.getY()- platImage.getHeight()/2;
                    newY = topOfPlat - groundHeight;
                }
            }
        }

        else {
            fall();
        }
        setLocation((int) newX, (int) newY);
    }

    //checks for wich key is down
    private void getKey()
    {
        Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        Boss b = (Boss) getOneIntersectingObject(Boss.class);
        if(Greenfoot.isKeyDown("A"))
        {
            isLeft = true;
            setImage(left);
            run();
        } else if (Greenfoot.isKeyDown("D"))
        {
            isLeft = false;
            setImage(right);
            run();
        }else if(e== null && b == null)
        {
            stop();
        }
        if (Greenfoot.getKey() == ("w"))
        {
            if(!inTheAir) jump();
            else if(inTheAir && !jumped) doubleJump();
        }
        else if( Greenfoot.getKey() == ("tab"))
        {
            if(!inTheAir) jump();
            else if(inTheAir && !jumped) doubleJump();
        }
        else if(Greenfoot.isKeyDown("s")){
            dY-=1;
        }

    }

    private void shootDirection(){
        if(Greenfoot.isKeyDown("left") && !drunk){
            shootL = true;
            shootR = false;
            if(Greenfoot.isKeyDown("up")){
                shootUp = true;
                shoot(-4,4);
            }
            else shoot(-4, 0);
            //shootUp = false;
        }
        else if(Greenfoot.isKeyDown("right") && !drunk){
            shootL = false;
            shootR = true;
            if(Greenfoot.isKeyDown("up")){
                shootUp = true;
                shoot(4,4);
            }
            else shoot(4, 0);
        }
        else if(Greenfoot.isKeyDown("right") && drunk){
            shootL = true;
            shootR = false;
            if(Greenfoot.isKeyDown("up")){
                shootUp = true;
                shoot(-4,4);
            }
            else shoot(-4, 0);
            //shootUp = false;
        }
        else if(Greenfoot.isKeyDown("left") && drunk){
            shootL = false;
            shootR = true;
            if(Greenfoot.isKeyDown("up")){
                shootUp = true;
                shoot(4,4);
            }
            else shoot(4, 0);
        }
        if(Greenfoot.isKeyDown("up")){
            shootUp = true;
            shootL = false;
            shootR = false;
            shoot(0, 4);
        }
        else shootUp = false;
    }

    public int getHealth(){
        return health;
    }

    private void hit(){
        Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        if(e!= null ){
            if(e.getX() < this.getX()){
                canMoveL = false;
                dX+=3;

            }
            else if(e.getX() >= getX()){
                canMoveR = false;
                dX-=3;

            }
            takeDamage();
        }
        else if( e == null){
            canMoveL = true;
            canMoveR = true;
        }
        Boss b = (Boss) getOneIntersectingObject(Boss.class);
        if(b!= null ){
            if(b.getX() < getX()){
                canMoveL = false;
                dX+=3;
                dY = 5;
                inTheAir = true;
            }
            else if(b.getX() >= getX()){
                canMoveR = false;
                dX-=3;
                dY = 5;
                inTheAir = true;

            }
            if(b.getY() > getY()) b.takeDamage(true);
            else if(getY() > b.getY()) takeDamage();
        }
        else if( b == null){
            canMoveL = true;
            canMoveR = true;
        }
        Noodle n = (Noodle) getOneIntersectingObject(Noodle.class);
        if(n != null){
            takeDamage();
            getWorld().removeObject(n);
        }
    }

    private void pickUpCoins(){
        Coin c = (Coin) getOneIntersectingObject(Coin.class);
        if(c!= null){
            getWorld().removeObject(c);
            coins++;
        }
    }

    public int getCoins(){
        return coins;
    }

    private void checkForNewLife(){
        oldLife = ((lifeMult-1) * 10) + 1;
        if(oldLife == coins) gotLife = false;
        if(coins == 10*lifeMult && !gotLife){
            gotLife = true;
            List <Life> lives = getObjectsInRange(700,Life.class);
            for(Life l : lives){
                if(l.getLife() == health - 1) {
                    getWorld().addObject(new Life(health), l.getX()+25, l.getY());
                }

            }
            health++;
            Greenfoot.playSound("NewLife.wav");
            lifeMult++;
        }
    }

    private void powerUp(){
        if(drunk && !sanic) walkSpeed = Greenfoot.getRandomNumber(5) *-1;
        else if(drunk && sanic){
            walkSpeed = Greenfoot.getRandomNumber(20) * -1;
        }
        if(teleporting && timer == 120){
            spawnAtRandomPlat();
            timer = 0;
        }
    }
}

