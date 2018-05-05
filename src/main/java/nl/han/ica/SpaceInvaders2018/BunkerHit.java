package nl.han.ica.SpaceInvaders2018;

public class BunkerHit {
	private int x;
	private int y;
	private int impactWeight;				// 1 = laser, 2 = proton, 3 = photon, 4 = alien
	private int impactDirection;
	
	public BunkerHit(int x, int y, int impactWeight, int impactDirection) {
		this.x = x;
		this.y = y;
		this.impactWeight = impactWeight;
		this.impactDirection = impactDirection;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getImpactWeight() {
		return impactWeight;
	}

	public void setImpactWeight(int impactWeight) {
		this.impactWeight = impactWeight;
	}

	public int getImpactDirection() {
		return impactDirection;
	}

	public void setImpactDirection(int impactDirection) {
		this.impactDirection = impactDirection;
	}
	
}


/*package nl.han.ica.SpaceInvaders2018;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class BunkerHit extends AnimatedSpriteObject {*/
	//private int impactWeight;				// 1 = laser, 2 = proton, 3 = photon, 4 = alien
	//private int impactDirection;
	
//	public BunkerHit(float x, float y, int impactWeight, int impactDirection) {
	//	super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"/*"nl/han/ica/SpaceInvaders2018/sprites/BunkerHitSmall.png"*/), 1);
	//	this.impactWeight = impactWeight;
	//	this.impactDirection = impactDirection;
		//this.setWidth(20);
		//this.setHeight(20);
		//setZ(2.0f);
	//}

	//public int getImpactWeight() {
	//	return impactWeight;
//	}

//	public void setImpactWeight(int impactWeight) {
//		this.impactWeight = impactWeight;
//	}

///	public int getImpactDirection() {
//		return impactDirection;
//	}

///	public void setImpactDirection(int impactDirection) {
//		this.impactDirection = impactDirection;
//	}

//	@Override
//	public void update() {
		//nextFrame();
		// TODO Auto-generated method stub
		
	//}
	
//}*/
