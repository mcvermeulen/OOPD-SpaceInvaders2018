package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienContainer extends Alien implements ICollidableWithGameObjects {
    private SpaceInvaders world;
    protected int direction = 90;
    private ArrayList<Alien> aliens;
    private int allHostileProjectiles;

    public AlienContainer(SpaceInvaders world, float x, float y, Sound alienKilled) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"), 1, x, y, 0, 0, world, alienKilled);
        setVisible(false);
        this.world = world;
        aliens = new ArrayList<>();
        this.allHostileProjectiles = 0;

        setDirectionSpeed(direction, 2);
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    public void add(Alien alien) {
        aliens.add(alien);
        world.addGameObject(alien);
    }

    public void destroy(Alien alien) {
        aliens.remove(alien);
        world.deleteGameObject(alien);
    }
    
    public void cleanUpAliens() {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			Alien a = aliens.get(i);
			if (a.getHit()) {
    			world.increaseScore(a.getValue());
    			destroy(a);
			}
		}
    }
    
    public int giveAllHostileProjectiles() {
    	int number = 0;
    	for (Alien a : aliens) {
    		number += a.getTotalHostileProjectiles();
    	}
    	return number;
    }
    
    public void fireBack() {
    	if (aliens.size() > 0) { 	// dit stukje is nodig, omdat het spel anders vastloopt zodra de laatste alien is weggeschoten
	    	allHostileProjectiles = giveAllHostileProjectiles();
	    	if (allHostileProjectiles < 3) {
	    		Random rand = new Random();
	    		int fire = rand.nextInt(250); // bepaalt kans dat de aliens schieten. Dit getal kan later een variabele fireRate worden dat bv. hoger wordt naarmate er minder aliens zijn
	    		// hier later als we ook de andere twee projectiel typen hebben, nog een random gebruiken om te bepalen welk projectiel het wordt
	    		//System.out.println("Dit stuk werkt, en de waarde van fire is " + fire);
	    		//System.out.println(getTotalHostileProjectiles());
	    		System.out.println(allHostileProjectiles);
	    		if (fire <= 1) {
	        		int alien = rand.nextInt(aliens.size());
	        		Alien attackingAlien = aliens.get(alien);
	    			attackingAlien.fireLaser();
	    		}
	    	}
    	}
    }
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        /*for (GameObject g:collidedGameObjects) { 				// dit blijkt helemaal weg te kunnen
            if (g instanceof Projectile) {
            	if(((Projectile) g).getFriendly()) {
            		//Projectile p = (Projectile) g;
            		//AttackCapableGameObject k = p.getSource();
            		//k.removeProjectile(p);
            		//world.deleteGameObject(g);
            		for (int i = aliens.size() - 1; i >= 0; i--) {
            			Alien a = aliens.get(i);
            			if (a.getHit()) {
	            			world.increaseScore(a.getValue());
	            			destroy(a);
            			}
            		}
            	}
            }
        }*/
    }

    @Override
    public void update() {
    	fireBack();
    	// boundaries
        if (direction == 90 && calculateRight() >= 990) {
            direction = 270;
        }
        if (direction == 270 && calculateLeft() <= 290) {
            direction = 90;
        }
        setDirectionSpeed(direction, 1.5f);
        for (Alien alien: aliens) {
            alien.setDirectionSpeed(direction, 1.5f);
        }
        cleanUpAliens();
    }

    private int calculateRight() {
        float right = 0;
        for (Alien alien: aliens) {
            if (alien.getX() + alien.getWidth() > right) right = alien.getX() + alien.getWidth();
        }
        return Math.round(right);
    }
    
    private int calculateLeft() {
        float left = 990;
        for (Alien alien: aliens) {
            if (alien.getX() < left) left = alien.getX();
        }
        return Math.round(left);
    }
}
