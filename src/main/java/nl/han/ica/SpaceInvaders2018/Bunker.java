package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.ArrayList;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class Bunker extends DestroyableGameObject implements ICollidableWithGameObjects {
	
	private ArrayList<BunkerHit> hits = new ArrayList<>();

	public Bunker(float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Bunker.png"), 1, x, y, sWidth, sHeight, world);
		setZ(-1.0f);
	}

	// TODO Ik ben er nog niet helemaal uit hoe ik de schoten moet afbeelden. Op het moment laat ik zwarte cirkels tekenen over de 
	// impactzone, maar als ik de code plaats in update flikkert het beeld steeds. Als ik het teken in de draw flikkert het beeld
	// niet, maar loopt het hele programma wel soms vast en bovendien worden de ellipsen achter de bunker getekent in plaats van ervoor.
	@Override
	public void update() {
		/*if (hits.size() > 0) {
			for (BunkerHit h : hits) {
				drawBunkerHit(h.getX(), h.getY(), h.getImpactWeight(), h.getImpactDirection());
			}
		}*/
	}
	
	@Override
	public void draw(PGraphics g) {
		super.draw(g);
		if (hits.size() > 0) {
			for (BunkerHit h : hits) { // TODO: CH: het programma loopt bij mij soms vast op deze lijn, meestal als er al een flink aantal keer snel achter elkaar op de bunker is geschoten
				drawBunkerHit(h.getX(), h.getY(), h.getImpactWeight(), h.getImpactDirection());
			}
		}
	}
	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	Projectile p = (Projectile) g;
            	int impactAreaX = (int)p.getX();
            	int impactAreaY = (int)p.getY();
            	int projectileDirection = (int)p.getySpeed();
            	world.loadPixels();
            	int black = world.get(800, 90);
            	//System.out.println(world.get(impactAreaX, impactAreaY + projectileDirction));
            	//System.out.println(world.get(800, 130));
            	//System.out.println(black);
            	
            	while (world.get(impactAreaX, impactAreaY + projectileDirection) == black) {
            		impactAreaX = (int)p.getX();
                	impactAreaY = (int)p.getY();
                	
                	// we breken hier uit de lus als het projectiel helemaal voorbij de bunker is en geen niet-zwarte pixels heeft geraakt
                	// dus als er een gat helemaal door de bunker is geschoten
                	if (impactAreaY + projectileDirection < getY() || impactAreaY + projectileDirection > getY() + getHeight()) {
                		break;
                	}
            	}
            	
            	
            	if (world.get(impactAreaX, impactAreaY + projectileDirection) != black) { // hier stellen we vast of het geraakte stuk al schade heeft (dus zwart is)
            		int projectileWeight = determineProjectileWeight(p);
	            	addHit(impactAreaX, impactAreaY, projectileWeight, projectileDirection);
	            	System.out.println("Aantal bunkerhits is " + hits.size());
	            	AttackCapableGameObject k = p.getSource();
	        		k.removeProjectile(p);
	        		world.deleteGameObject(g);
            	}
            }
        }
	}
	
	private void addHit(int x, int y, int impactWeight, int impactDirection) {
		BunkerHit hit = new BunkerHit (x, y, impactWeight, impactDirection);
		hits.add(hit);
	}
	
	private void drawBunkerHit (int x, int y, int projectileWeight, int direction) {
		world.fill(255); // TODO als alles werkt (d.w.z. de hits niet meer achter een bunker worden getekent), kunnen we dit zwart maken
		world.noStroke();
		world.ellipseMode(CENTER);
		int impactZoneY = y;
		if (direction > 0) {
			impactZoneY = y + 35; // TODO: lengte van de laser. Let op, dit moet nog handiger worden aangepakt. Aangezien we de projectiles verwijderden kunnen we deze niet als parameter gebruiken, toch?
		}
		world.ellipse(x, impactZoneY, projectileWeight*10, projectileWeight*10);
	}
	
	private int determineProjectileWeight(Projectile p) {
		int weight = 0;
		if (p instanceof Laser) {
			weight = 1;
		}
		else if (p instanceof Plasma) {
			weight = 2;
		}
		else if (p instanceof Proton) {
			weight = 3;
		}
		return weight;
	}
	
}

/*package nl.han.ica.SpaceInvaders2018;

import java.util.List;
import java.util.ArrayList;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

public class Bunker extends DestroyableGameObject implements ICollidableWithGameObjects {
	
	private ArrayList<BunkerHit> hits = new ArrayList<>();

	public Bunker(float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
		super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Bunker.png"), 1, x, y, sWidth, sHeight, world);
		setZ(-1.0f);
	}*/

	// TODO Ik ben er nog niet helemaal uit hoe ik de schoten moet afbeelden. Op het moment laat ik zwarte cirkels tekenen over de 
	// impactzone, maar als ik de code plaats in update flikkert het beeld steeds. Als ik het teken in de draw flikkert het beeld
	// niet, maar loopt het hele programma wel soms vast en bovendien worden de ellipsen achter de bunker getekent in plaats van ervoor.
	/*@Override
	public void update() {
		//nextFrame();
		if (hits.size() > 0) {
			for (BunkerHit h : hits) {
				drawBunkerHit((int)h.getX(), (int)h.getY(), h.getImpactWeight(), h.getImpactDirection());
			}
		}
	}*/
	
	/*@Override
	public void draw(PGraphics g) {
		super.draw(g);
		if (hits.size() > 0) {
			for (BunkerHit h : hits) { // TODO: CH: het programma loopt bij mij soms vast op deze lijn, meestal als er al een flink aantal keer snel achter elkaar op de bunker is geschoten
				drawBunkerHit((int)h.getX(), (int)h.getY(), h.getImpactWeight(), h.getImpactDirection());
			}
		}
	}*/
	/*
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g:collidedGameObjects) {
            if (g instanceof Projectile) {
            	Projectile p = (Projectile) g;
            	int impactAreaX = (int)p.getX();
            	int impactAreaY = (int)p.getY();
            	int projectileDirection = (int)p.getySpeed();
            	int projectileWeight = determineProjectileWeight(p);
            	addHit(impactAreaX, impactAreaY, projectileWeight, projectileDirection);
            	//world.loadPixels();
            	//int black = world.get(800, 90);
            	
            	//System.out.println(world.get(impactAreaX, impactAreaY + projectileDirction));
            	//System.out.println(world.get(800, 130));
            	//System.out.println(black);
            	//System.out.println(p.getX());
            	//System.out.println(p.getY());
            	//System.out.println(projectileWeight);
            	
            	/*
            	while (world.get(impactAreaX, impactAreaY + projectileDirection) == black) {
            		impactAreaX = (int)p.getX();
                	impactAreaY = (int)p.getY();
                	
                	// we breken hier uit de lus als het projectiel helemaal voorbij de bunker is en geen niet-zwarte pixels heeft geraakt
                	// dus als er een gat helemaal door de bunker is geschoten
                	if (impactAreaY + projectileDirection < getY() || impactAreaY + projectileDirection > getY() + getHeight()) {
                		break;
                	}
            	}*/
            	
            	
            	//if (world.get(impactAreaX, impactAreaY + projectileDirection) != black) { // hier stellen we vast of het geraakte stuk al schade heeft (dus zwart is)
            		//int projectileWeight = determineProjectileWeight(p);
	            	//addHit(impactAreaX, impactAreaY, projectileWeight, projectileDirection);
	            	//System.out.println("Aantal bunkerhits is " + hits.size());
	//            	AttackCapableGameObject k = p.getSource();
	//        		k.removeProjectile(p);
	//        		world.deleteGameObject(g);
            	//}
     //       }
 //       }
//	}*/
	/*
	private void addHit(int x, int y, int impactWeight, int impactDirection) {
		BunkerHit hit = new BunkerHit (x, y, impactWeight, impactDirection);
		hits.add(hit);
		world.addGameObject(hit);
		System.out.println(hits.size());*/
		//System.out.println(getZ());
	//}
	
	/*
	private void drawBunkerHit (int x, int y, int projectileWeight, int direction) {
		
		
	//	world.fill(255); // TODO als alles werkt (d.w.z. de hits niet meer achter een bunker worden getekent), kunnen we dit zwart maken
	//	world.noStroke();
	//	world.ellipseMode(CENTER);
		int impactZoneY = y;
		if (direction > 0) {
			impactZoneY = y + 35; // TODO: lengte van de laser. Let op, dit moet nog handiger worden aangepakt. Aangezien we de projectiles verwijderden kunnen we deze niet als parameter gebruiken, toch?
		}
		world.ellipse(x, impactZoneY, projectileWeight*10, projectileWeight*10);
	}*/
	
	//private int determineProjectileWeight(Projectile p) {
	//	int weight = 0;
	//	if (p instanceof Laser) {
	//		weight = 1;
	//	}
	//	else if (p instanceof Plasma) {
	//		weight = 2;
	//	}
	//	else if (p instanceof Proton) {
	//		weight = 3;
	//	}
	//	return weight;
//	}
	
//}/*
