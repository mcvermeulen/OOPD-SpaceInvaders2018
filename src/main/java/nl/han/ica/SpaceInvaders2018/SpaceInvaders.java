package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

public class SpaceInvaders extends GameEngine {
	private static final long serialVersionUID = 2790543985929323791L;

	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.SpaceInvaders2018.SpaceInvaders"});
    }

    @Override
    public void setupGame() {
        createView(1280, 800);
        createDashboard(1280, 800);

        Cannon kanon= new Cannon();
        Alien alien1 = new SmallAlien();
        Alien alien2 = new MediumAlien();
        Alien alien3 = new MediumAlien();
        Alien alien4 = new LargeAlien();
        Alien alien5 = new LargeAlien();
        Ruimteschip schip = new Ruimteschip();

        addGameObject(kanon, 700, 700);
        addGameObject(schip, 850,100);
        addGameObject(alien1, 850,170);
        addGameObject(alien2, 850,200);
        addGameObject(alien3, 850,230);
        addGameObject(alien4, 850,260);
        addGameObject(alien5, 850,290);
    }

    @Override
    public void update() {

    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        Sprite backgroundImg = new Sprite("nl/han/ica/SpaceInvaders2018/media/background-1280x800.png");
        dashboard.setBackgroundImage(backgroundImg);
        addDashboard(dashboard);
    }
    private void createView(int viewWidth, int viewHeight) {
        View view = new View(viewWidth, viewHeight);
        view.setBackground(0,0,0);

        size(viewWidth, viewHeight);
        setView(view);
    }
}
