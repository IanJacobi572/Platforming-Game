import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
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
    int invulnTimer = 0;
    boolean wasLeft;
    int shootTimer = 0;
    boolean ready;
    //boolean rest;
    //boolean platLevel;
    public int health = 100;
    BossHealth boss;
    boolean rightBound;
    boolean upperBound;
    boolean isCharging = false;
    public int height = getImage().getHeight();
    public boolean justCharged = true;
    GreenfootImage move;
    GreenfootImage attack;
    int i = 0;
    Player main;
    public Boss(Player p){
        attack = new GreenfootImage("NUDL COP-2.png");
        move = getImage();
        main = p;
        //setHealthBar();
        //img.scale(200, 200);
    }

    public void addedToWorld(World BossWorld){
        getWorld().addObject(new BossHealth(10),360,50);
    }

    public void act() 
    {
        //fire();// Add your action code here.
        //if(timer > 0) 
        if(health <= 40){
            attackWithNoodles(80);
            attackWithSelf(100);
        }
        else if(health<= 70 && health > 40) attackWithSelf(45);
        else if( health <= 100 && health >70){
            attackWithNoodles(60);
            //attackWithSelf();
        }
        timer --;
        shootTimer--;
        invulnTimer--;
        moveVertically();
        takeDamage(false);
        //setHealthBar();
    }   

    public void moveVertically(){
        if(getX() == 550) {rightBound = true;
            leftBound = false;
        }
        else if( getX() == 50){
            rightBound = false;

            leftBound = true;

        }
        if(main.getY()-getY() >= 100) upperBound = true;
        else if(main.getY()-getY() <= -100) upperBound = false;
        if(!isCharging && upperBound) setLocation(getX(), getY()+1);
        else if(!isCharging && !upperBound) setLocation(getX(), getY()-1);
    }

    public void attackWithNoodles(int t){

        if(shootTimer <= 0 && rightBound){
            getWorld().addObject(new Noodle(-4,0), getX(), getY());
            getWorld().addObject(new Noodle(-4,1), getX(), getY());
            getWorld().addObject(new Noodle(-4,-1), getX(), getY());
            shootTimer = t;
            //if(Greenfoot.getRandomNumber(100) < 3) setLocation(50, getY());
        }
        if(shootTimer <= 0 && leftBound){
            getWorld().addObject(new Noodle(4,0), getX(), getY());
            getWorld().addObject(new Noodle(4,1), getX(), getY());
            getWorld().addObject(new Noodle(4,-1), getX(), getY());
            shootTimer = t;
            //if(Greenfoot.getRandomNumber(100) < 3) setLocation(50, getY());
        }
    }

    public void attackWithSelf(int t){
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
        /*if(getY() == 395-height || getY() == 200-height || getY() == 300-height) platLevel = true;
        else platLevel = false;*/
        if(!justCharged){
            if(main.getY()-getY() < 10 && main.getY() - getY() > -10 && rightBound){
                setLocation(getX()-10, getY());
                setImage(attack);
                isCharging = true;
                if(getX() == 60){
                    timer = t;
                    
                    setImage(move);
                    setLocation(getX()-10, getY());
                }
            }
            else if(isCharging && rightBound){
                setLocation(getX()-10, getY());
                isCharging = true;
                setImage(attack);
                if(getX() == 60){
                    setImage(move);
                    
                    timer = t;
                    setLocation(getX()-10, getY());
                }
            }
            else if(main.getY()-getY() < 10 && main.getY() - getY() > -10 && leftBound){
                setLocation(getX()+10, getY());
                isCharging = true;
                setImage(attack);
                if(getX() == 540){
                    setImage(move);
                    timer = t;
                    setLocation(getX()+10, getY());
                }
            }
            else if(isCharging && leftBound){
                setLocation(getX()+10, getY());
                setImage(attack);
                if(getX() == 540){
                    setImage(move);
                    timer = t;
                    setLocation(getX()+10, getY());
                }
            }

        }
        else isCharging = false;
    }

    public void takeDamage(boolean playerAbove){
        Bullet b = (Bullet) getOneIntersectingObject(Bullet.class);
        if (b != null && !isCharging){
            if(invulnTimer <= 0){
                health = health - 10;
                invulnTimer = 45;
            }
            getWorld().removeObject(b);
        }
        else if(playerAbove && isCharging){
            if(invulnTimer <= 0){
                health = health - 10;
                invulnTimer = 45;
            }
        }
        if(health == 0) Greenfoot.setWorld(new GameOver(true));
        setHealthBar();
    }

    public void setHealthBar(){
        List<BossHealth> b = getObjectsInRange(700,BossHealth.class);
        if(b.size() != 0) boss = b.get(0);

        if (health == 90){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(9),360,50);
        }else if (health == 80){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(8),360,50);
        }else if (health == 70){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(7),360,50);
        }else if (health == 60){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(6),360,50);
        }else if(health == 50){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(5),360,50);
        }else if (health==40){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(4),360,50);
        }else if (health == 30){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(3),360,50);        
        }else if (health == 20){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(2),360,50);
        }else if (health == 10){
            getWorld().removeObject(boss);
            getWorld().addObject(new BossHealth(1),360,50);
        }
    }
}

