import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int timer = 0;
    boolean leftBound;
    boolean wasLeft;
    int moveTimer = 0;
    boolean ready;
    boolean rest;
    boolean platLevel;
    int health = 50;
    boolean rightBound;
    boolean upperBound;
    boolean isCharging = false;
    public int height = getImage().getHeight();
    public boolean justCharged = true;
    int i = 0;
    public Boss(){
        GreenfootImage img = getImage();
        //img.scale(200, 200);
    }

    public void act() 
    {
        //fire();// Add your action code here.
        //if(timer > 0) 
        if(health > 50)attackWithSelf();
        else {
            attackWithNoodles();
        }
        timer --;
        moveVertically();
    }   

    public void moveVertically(){
        if(getX() == 550) {rightBound = true;
            leftBound = false;
        }
        else if( getX() == 50){
            rightBound = false;

            leftBound = true;

        }
        if(getY() <= 150) upperBound = true;
        else if(getY() >= 400) upperBound = false;
        if(!isCharging && upperBound) setLocation(getX(), getY()+1);
        else if(!isCharging && !upperBound) setLocation(getX(), getY()-1);
    }

    public void attackWithNoodles(){
        if(getY() == 395-height || getY() == 200-height || getY() == 300-height) platLevel = true;
        else platLevel = false;
        if(timer <= 0){
            getWorld().addObject(new Noodle(-4,0), getX(), getY());
            getWorld().addObject(new Noodle(-4,1), getX(), getY());
            getWorld().addObject(new Noodle(-4,-1), getX(), getY());
            timer = 120;
            if(Greenfoot.getRandomNumber(100) < 3) setLocation(50, getY());
        }
        
    }

    public void attackWithSelf(){
        if(timer <= 0) {
            justCharged = false;
            if(!ready && rightBound){
                turn(-90);
                ready = true;
            }
            else if(!ready && leftBound){
                turn(90);
                ready = true;
            }
        }
        else {
            justCharged = true;
            if(ready && leftBound){
                turn(90);
                ready = false;
            }
            if(ready && rightBound){
                turn(-90);
                ready = false;
            }
        }

        
        if(getY() == 300-height && !justCharged && rightBound){
            //turn(90);
            setLocation(getX()-5, getY());
            isCharging = true;
            if(getX() == 55){
                timer = 120;
                setLocation(getX()-5, getY());
            }
        }
        else if(getY() == 300 -height && !justCharged && leftBound){
            setLocation(getX()+5, getY());
            isCharging = true;
            if(getX() == 545){
                timer = 120;
                setLocation(getX()+5, getY());
            }
        }
        else if(getY() == 200-height && !justCharged && rightBound){
            setLocation(getX()-5, getY());

            isCharging = true;
            if(getX() == 55){
                timer = 120;
                setLocation(getX()-5, getY());
            }
        }
        else if(getY() == 200-height && !justCharged && leftBound){
            setLocation(getX()+5, getY());
            isCharging = true;
            if(getX() == 545){
                timer = 120;
                setLocation(getX()+5, getY());
            }
        }
        else if(getY() == 395-height && !justCharged && rightBound){
            setLocation(getX()-5, getY());

            isCharging = true;
            if(getX() == 55){
                timer = 120;
                setLocation(getX()-5, getY());
            }
        }
        else if(getY() == 395-height && !justCharged && leftBound){
            setLocation(getX()+5, getY());
            isCharging = true;
            if(getX() == 545){
                timer = 120;
                setLocation(getX()+5, getY());
            }
        }

        else isCharging = false;
    }
}

