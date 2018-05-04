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
    private float speed;
    private int destroyed = 0;

    public AlienContainer(SpaceInvaders world, float x, float y, Sound alienKilled) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"), 1, x, y, 0, 0, world, alienKilled);
        setVisible(false);
        this.world = world;
        aliens = new ArrayList<>();
        this.allHostileProjectiles = 0;
        this.speed = 0.7f;

//        setDirectionSpeed(direction, 2); // waarom is deze nodig? Overschrijf je dit niet verderop?
    }
    
    public void setBaseGroupSpeed(float speed) {
    	this.speed = speed;
    }
    
    public float getBaseGroupSpeed() {
    	return speed;
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
    	if (aliens.size() > 0) {
	    	allHostileProjectiles = giveAllHostileProjectiles();
	    	if (allHostileProjectiles < 3) {
	    		Random rand = new Random();
	    		int fire = rand.nextInt(250); // bepaalt kans dat de aliens schieten. Dit getal kan later een variabele fireRate worden dat bv. hoger wordt naarmate er minder aliens zijn
	    		// hier later als we ook de andere twee projectiel typen hebben, nog een random gebruiken om te bepalen welk projectiel het wordt
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
    }

    @Override
    public void update() {
    	fireBack();
    	updateCurrentGroupSpeed();
    	// boundaries
        if (direction == 90 && calculateRight() >= 990) {
        	dropAsGroup();
            direction = 270;
        }
        if (direction == 270 && calculateLeft() <= 290) {
        	dropAsGroup();
            direction = 90;
        }
        setDirectionSpeed(direction, speed);
        for (Alien alien: aliens) {
            alien.setDirectionSpeed(direction, speed);
        }
        cleanUpAliens();
    }
    
    private void dropAsGroup() {
    	for (int i = aliens.size()-1; i>=0; i--) {
    		Alien a = aliens.get(i);
    		//System.out.println(a.getY() + getHeight());
    		if (a.getY() + getHeight() >= 672) {
    			for (Alien alien: aliens) {
    				alien.dropToRowBelow();
    			}
    			System.out.println("Speler heeft verloren");
    			world.pauseGame();
    			break;
    		}
    		else {
    			a.dropToRowBelow();
    		}
    	}
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
    
    
    private void updateCurrentGroupSpeed() {
    	int destroyedAliens = 55 - aliens.size();
    	if (this.destroyed != destroyedAliens) {
    		speed *= 1.025f;
    		this.destroyed = destroyedAliens;
    	}
    }

    //TODO dit kan beter
    public void generateAliens(SpaceInvaders world, int nSmallAliens, int nMediumAliens, int nLargeAliens) {
        int columns = 11;
        int row = 0;
        int margeX = 35;
        int margeY = 35;
        int offset = 0;
        for (int j = 0; j < nSmallAliens ; j++) {
            Alien alien = new SmallAlien(world, this.getX()+offset*margeX, this.getY()+row*margeY, alienKilled);
            this.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nMediumAliens ; j++) {
            Alien alien = new MediumAlien(world, this.getX()+offset*margeX, this.getY()+row*margeY, alienKilled);
            this.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nLargeAliens ; j++) {
            Alien alien = new LargeAlien(world, this.getX()+offset*margeX, this.getY()+row*margeY, alienKilled);
            this.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
    }
}
