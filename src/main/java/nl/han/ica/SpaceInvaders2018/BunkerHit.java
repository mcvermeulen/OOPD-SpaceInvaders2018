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
