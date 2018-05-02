package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

public class SpaceInvaders extends GameEngine {
	private static final long serialVersionUID = 2790543985929323791L;
	private Sound shootSound;
	private Sound UFOShot;
	private Sound UFOTravel;
	private Sound alienKilled;
	private Sound explosion;
	private TextObject dashboardText1;
	private TextObject dashboardHeaderText1;
	private TextObject dashboardPlayerLives1;
	private int scorePlayer1;
	private int livesPlayer1;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.SpaceInvaders2018.SpaceInvaders"});
    }

    @Override
    public void setupGame() {
        int gameWidth = 1280;
        int gameHeight = 800;
        scorePlayer1 = 0;
        livesPlayer1 = 3;
        initializeSound();

        createView(gameWidth, gameHeight);
        createDashboard(gameWidth, gameHeight);

        Ground grond = new Ground(715, 290, 990);
        Cannon kanon= new Cannon(this, 700, 680, shootSound, explosion);
        Ruimteschip schip = new Ruimteschip(this, 850, 130, UFOShot, UFOTravel);
        AlienContainer alienContainer = new AlienContainer(this, 400, 190, alienKilled);
        generateAliens(alienContainer, 11, 22, 22);

        addGameObject(grond);
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
        dashboardHeaderText1 = new Score("SCORE 1", 30);
        dashboardHeaderText1.setX(290);
        dashboardHeaderText1.setY(50);
        dashboard.addGameObject(dashboardHeaderText1);
        
        dashboardText1 = new Score(String.format("%06d", scorePlayer1), 30);
        dashboardText1.setX(290);
        dashboardText1.setY(90);
        dashboard.addGameObject(dashboardText1);
        
        dashboardPlayerLives1 = new PlayerLives(String.format("%01d", livesPlayer1), 25);
        dashboardPlayerLives1.setX(300);
        dashboardPlayerLives1.setY(715);
        dashboard.addGameObject(dashboardPlayerLives1);
        
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
        alienKilled = new Sound(this, "nl/han/ica/SpaceInvaders2018/media/invaderkilled.mp3");
        explosion = new Sound(this, "nl/han/ica/SpaceInvaders2018/media/explosion.mp3");
    }

    //TODO dit kan beter
    private void generateAliens(AlienContainer aliens, int nSmallAliens, int nMediumAliens, int nLargeAliens) {
        int columns = 11;
        int row = 0;
        int margeX = 35;
        int margeY = 35;
        int offset = 0;
        for (int j = 0; j < nSmallAliens ; j++) {
            Alien alien = new SmallAlien(this, aliens.getX()+offset*margeX, aliens.getY()+row*margeY, alienKilled);
            aliens.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nMediumAliens ; j++) {
            Alien alien = new MediumAlien(this, aliens.getX()+offset*margeX, aliens.getY()+row*margeY, alienKilled);
            aliens.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
        for (int j = 0; j < nLargeAliens ; j++) {
            Alien alien = new LargeAlien(this, aliens.getX()+offset*margeX, aliens.getY()+row*margeY, alienKilled);
            aliens.add(alien);
            offset++;
            if (j > 0 && (j+1) % columns == 0) {
                row++;
                offset = 0;
            }
        }
    }
    
    public void increaseScore(int score) {
        scorePlayer1 += score;
        refreshDasboardText();
    }
    
    public void decreaseLives() {
    	livesPlayer1--;
    	refreshDasboardText();
    }
    
    public int getLives() {
    	return livesPlayer1;
    }
    
    private void refreshDasboardText() {
    	dashboardText1.setText(String.format("%06d", scorePlayer1));
    	dashboardPlayerLives1.setText(String.format("%01d", livesPlayer1));
    }
}
