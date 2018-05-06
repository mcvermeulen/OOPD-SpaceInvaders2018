package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import java.util.ArrayList;

public abstract class AttackCapableGameObject extends DestroyableGameObject {
    private ArrayList<Projectile> projectiles = new ArrayList<>(); // NB. alleen het attackCapableObect zelf kan hier dus bij. Voor de aliens moet de container hiervan gebruik maken, anders gaat het mis
    
    public AttackCapableGameObject(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
        super(sprite, totalFrames, x, y, sWidth, sHeight, world);
    }

    public void generateLaser(boolean friendly) {
        Laser laser = new Laser (world, friendly, this);
        float y;
        if (friendly) {
        	y = getY() - getHeight();
        }
        else {
        	y = getY() + getHeight();
        }
        addProjectile(laser, y);
    }

    public void generatePlasma() {
        Plasma plasma = new Plasma(world,this);
        addProjectile(plasma, getY() + getHeight());
    }

    public void generateProton() {
        Proton proton = new Proton(world,this);
        addProjectile(proton, getY() + getHeight());
    }

    private void addProjectile(Projectile p, float y) {
        projectiles.add(p);
        world.addGameObject(p, getX() + (getWidth()/2), y);
    }

    public int getTotalProjectiles() {
        return projectiles.size();
    }

    public int getTotalFriendlyProjectiles() {
        int number = 0;
        for (Projectile p : projectiles) {
            if (p.friendly) {
                number++;
            }
        }
        return number;
    }

    public int getTotalHostileProjectiles() {
        return getTotalProjectiles() - getTotalFriendlyProjectiles();
    }

    public void removeProjectile (Projectile p) {
        projectiles.remove(p);
        world.deleteGameObject(p);
    }

    public void cleanUpProjectiles() {
        for (int i = projectiles.size()-1; i >=0; i--) {
            Projectile p = projectiles.get(i);
            if (p.getOutOfBounds()) {
                removeProjectile(p);
                world.deleteGameObject(p);
            }
        }
    }
    
}
