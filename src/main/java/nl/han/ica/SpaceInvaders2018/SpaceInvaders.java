package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;
import processing.sound.SoundFile;

public class SpaceInvaders extends GameEngine {
	private static final long serialVersionUID = 2790543985929323791L;
	private Sound shootSound;
	private Sound UFOShot;
	private Sound UFOTravel;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.SpaceInvaders2018.SpaceInvaders"});
    }

    @Override
    public void setupGame() {
        int gameWidth = 1280;
        int gameHeight = 800;
        initializeSound();

        createView(gameWidth, gameHeight);
        createDashboard(gameWidth, gameHeight);

        Cannon kanon= new Cannon(this, 700, 700, shootSound);
        Ruimteschip schip = new Ruimteschip(this, 850, 100, UFOShot, UFOTravel);
        AlienContainer alienContainer = new AlienContainer(this, 400, 170);
        generateAliens(alienContainer,11, 22, 22);

        addGameObject(kanon);
        addGameObject(schip);
        addGameObject(alienContainer);
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
    
    private void initializeSound() {
        shootSound = new Sound(this, "nl/han/ica/SpaceInvaders2018/media/shoot.mp3");
        UFOShot = new Sound(this, "nl/han/ica/SpaceInvaders2018/media/ufo_lowpitch.mp3");
        UFOTravel = new Sound(this, "nl/han/ica/SpaceInvaders2018/media/ufo_highpitch.mp3");
    }

    //TODO dit kan beter
    private void generateAliens(AlienContainer aliens, int nSmallAliens, int nMediumAliens, int nLargeAliens) {
        int columns = 11;
        int row = 0;
        int margeX = 35;
        int margeY = 35;
        int offset = 0;
        for (int j = 0; j < nSmallAliens ; j++) {
            Alien alien = new SmallAlien(this, aliens.getX()+offset*margeX, aliens.getY()+row*margeY);
            aliens.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nMediumAliens ; j++) {
            Alien alien = new MediumAlien(this, aliens.getX()+offset*margeX, aliens.getY()+row*margeY);
            aliens.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nLargeAliens ; j++) {
            Alien alien = new LargeAlien(this, aliens.getX()+offset*margeX, aliens.getY()+row*margeY);
            aliens.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
    }
}
