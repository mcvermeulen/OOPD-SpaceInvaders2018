package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import java.util.ArrayList;

/**
 * Superklasse van alle objecten die kunnen schieten
 */
public abstract class AttackCapableGameObject extends DestroyableGameObject {
    /**
     * ArrayList met alle door het object afgevuurde projectielen
     */
	private ArrayList<Projectile> projectiles = new ArrayList<>();
    
	/**
	 * Constructor
	 * @param sprite 		Afbeelding van het object
	 * @param totalFrames	Aantal frames waaruit de afbeeldng bestaat
	 * @param x				X-positie waarop het object getekend wordt
	 * @param y				Y-positie waarop het object getekend wordt
	 * @param sWidth		Breedte van de afbeelding
	 * @param sHeight		Hoogte van de afbeelding
	 * @param world			Referentie naar de hoofdmodule
	 */
    public AttackCapableGameObject(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
        super(sprite, totalFrames, x, y, sWidth, sHeight, world);
    }

    /**
     * Voegt een projectiel van de subklasse Laser toe aan de ArrayList
     * @param friendly		Friendly = door de speler afgevuurd, !Friendly = door de aliens afgevuurd
     */
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

    /**
     * Voegt een projectiel van de subklasse Plasma toe aan de ArrayList
     * <p>
     * De speler kan alleen maar Lasers afvuren, dus is het hier niet nodig om te controleren of het projectiel Friendly is
     */
    public void generatePlasma() {
        Plasma plasma = new Plasma(world,this);
        addProjectile(plasma, getY() + getHeight());
    }

    /**
     * Voegt een projectiel van de subklasse Proton toe aan de ArrayList
     * <p>
     * De speler kan alleen maar Lasers afvuren, dus is het hier niet nodig om te controleren of het projectiel Friendly is
     */
    public void generateProton() {
        Proton proton = new Proton(world,this);
        addProjectile(proton, getY() + getHeight());
    }

    private void addProjectile(Projectile p, float y) {
        projectiles.add(p);
        world.addGameObject(p, getX() + (getWidth()/2), y);
    }

    /**
     * Haalt het aantal projectielen uit de ArrayList op
     * @return		aantal projectielen
     */
    public int getTotalProjectiles() {
        return projectiles.size();
    }

    /**
     * Haalt het aantal projectielen uit de ArrayList op, met de waarde Friendly
     * @return		aantal projectielen met de waarde Friendly
     */
    public int getTotalFriendlyProjectiles() {
        int number = 0;
        for (Projectile p : projectiles) {
            if (p.friendly) {
                number++;
            }
        }
        return number;
    }

    /**
     * Haalt het aantal projectielen uit de ArrayList op, met de waarde !Friendly
     * @return		aantal projectielen met de waarde !Friendly
     */
    public int getTotalHostileProjectiles() {
        return getTotalProjectiles() - getTotalFriendlyProjectiles();
    }

    /**
     * Verwijdert een projectiel uit de ArrayList
     * @param p		projectiel
     */
    public void removeProjectile (Projectile p) {
        projectiles.remove(p);
        world.deleteGameObject(p);
    }

    /**
     * Verwijdert de projectielen die uit het beeld zijn uit de ArrayList
     */
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
