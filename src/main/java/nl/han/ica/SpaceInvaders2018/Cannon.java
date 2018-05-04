package nl.han.ica.SpaceInvaders2018;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class Cannon extends AttackCapableGameObject implements ICollidableWithGameObjects, IAlarmListener {
    private Sound shootSound;
    private Sound explosion;

    public Cannon(SpaceInvaders world, float x, float y, Sound shoot, Sound explosion) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Cannon.png"), 1, x, y, 47, 30, world);
        this.shootSound = shoot;
        this.explosion = explosion;
        world.pauseGame();
        startAlarm();
    }

    @Override
    public void update() {
        cleanUpProjectiles();
        if (getX() <= 290) {
            setxSpeed(0);
            setX(290);
        }
        if (getX() >= 990 - getWidth()) {
            setxSpeed(0);
            setX(990 - getWidth());
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;

        switch (keyCode) {
            case LEFT:
                setDirectionSpeed(270, speed);
                break;
            case RIGHT:
                setDirectionSpeed(90, speed);
                break;
        }

        if (key == ' ') {
            if (getTotalFriendlyProjectiles() == 0) {
                shootSound.cue(137);
            	shootSound.play();
                generateLaser(true);
            }
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        switch (keyCode) {
            case LEFT: case RIGHT:
                setxSpeed(0);
                break;
        }
    }
    
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    	for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	if(!((Projectile) g).getFriendly()) {
            		Projectile p = (Projectile) g;
            		AttackCapableGameObject k = p.getSource();
            		k.removeProjectile(p);
            		world.deleteGameObject(g);
            		explosion.cue(140);
            		explosion.play();
            		world.decreaseLives();
            		if (world.getLives() > 0) {
            			setVisible(false);
            			world.pauseGame();
            			newLifeAlarm();
            		}
            		else if (world.getLives() == 0) {
            			world.deleteGameObject(this);
            			System.out.println("Speler heeft verloren");
            			world.pauseGame();
            		}
            	}
            }
    	}
    }
    
    private void newLifeAlarm() {
        Alarm alarm=new Alarm("Use extra life", 1);
        alarm.addTarget(this);
        alarm.start();
    }

    private void startAlarm() {
        Alarm alarm=new Alarm("Pause game so it can render before play starts", 2);
        alarm.addTarget(this);
        alarm.start();
    }

	@Override
	public void triggerAlarm(String alarmName) {
		setVisible(true);
		world.resumeGame();	
	}
}
