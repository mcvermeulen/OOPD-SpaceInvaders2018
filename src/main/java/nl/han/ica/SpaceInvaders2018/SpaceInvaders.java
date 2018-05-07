package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Vector;

public class SpaceInvaders extends GameEngine {
    private static GameState gameState = GameState.START;
	private static final long serialVersionUID = 2790543985929323791L;
	private Sound shootSound, UFOShot, UFOTravel, alienKilled, explosion;
	private TextObject dashboardText1, dashboardPlayerLives1, dashboardHighscore;
	private int highscore, scorePlayer1, livesPlayer1, playfieldWidth, playfieldHeight;
	private IPersistence persistence;
    private ArrayList<Level> levels;
    private Level currentLevel;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.SpaceInvaders2018.SpaceInvaders"});
    }

    @Override
    public void setupGame() {
        int gameWidth = 1280;
        int gameHeight = 800;
        playfieldHeight = playfieldWidth = 700;
        initializeSound();
        inititializeLevels();
        
        createView(gameWidth, gameHeight);
        createDashboard(gameWidth, gameHeight);

        Startscreen start = new Startscreen(this);
        addGameObject(start);
    }

    public void newGame() {
        setGameState(GameState.INPROGRESS);
        highscore = 0;
        scorePlayer1 = 0;
        livesPlayer1 = 3;

        Ground grond = new Ground(this, 715);
        Bunker bunker1 = new Bunker(350, 600, 71, 48, this);
        Cannon kanon= new Cannon(this, 700, 680, shootSound, explosion);
        Ruimteschip schip = new Ruimteschip(this, 850, 130, UFOShot, UFOTravel);
        AlienContainer alienContainer = new AlienContainer(this, 400, currentLevel.getStartPositionAliens(), alienKilled, 11, 22, 22);

        addGameObject(grond);
        addGameObject(bunker1);
        addGameObject(kanon);
        addGameObject(schip);
        addGameObject(alienContainer);

        deleteAllDashboards();
        createDashboard(getWidth(), getHeight());
        initializePersistence();
    }

    @Override
    public void update() {

    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        Sprite backgroundImg = new Sprite("nl/han/ica/SpaceInvaders2018/media/background-1280x800.png");
        dashboard.setBackgroundImage(backgroundImg);

        if (gameState != GameState.START) {
            addDashboardText(dashboard);
        }
        addDashboard(dashboard);
    }

    private void addDashboardText(Dashboard dashboard) {
        TextObject dashboardHeaderText1;
        TextObject dashboardHeaderTextHighscore;

        dashboardHeaderText1 = new TextObject("SCORE 1", 30);
        dashboardHeaderText1.setForeColor(255, 255, 255, 255);
        dashboardHeaderText1.setX(295);
        dashboardHeaderText1.setY(80);
        dashboard.addGameObject(dashboardHeaderText1);

        dashboardHeaderTextHighscore = new TextObject("HI-SCORE", 30);
        dashboardHeaderTextHighscore.setForeColor(255, 255, 255, 255);
        dashboardHeaderTextHighscore.setX(600);
        dashboardHeaderTextHighscore.setY(80);
        dashboard.addGameObject(dashboardHeaderTextHighscore);

        dashboardText1 = new TextObject(String.format("%06d", scorePlayer1), 30);
        dashboardText1.setForeColor(255, 255, 255, 255);
        dashboardText1.setX(295);
        dashboardText1.setY(115);
        dashboard.addGameObject(dashboardText1);

        dashboardHighscore = new TextObject(String.format("%06d", highscore), 30);
        dashboardHighscore.setForeColor(255, 255, 255, 255);
        dashboardHighscore.setX(600);
        dashboardHighscore.setY(115);
        dashboard.addGameObject(dashboardHighscore);

        dashboardPlayerLives1 = new PlayerLives(String.format("%01d", livesPlayer1), 25, this);
        dashboardPlayerLives1.setForeColor(255, 255, 255, 255);
        dashboardPlayerLives1.setX(295);
        dashboardPlayerLives1.setY(717);
        dashboard.addGameObject(dashboardPlayerLives1);
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
    
    private void initializePersistence() {
        persistence = new FilePersistence("main/java/nl/han/ica/SpaceInvaders2018/media/highscore.txt");
        if (persistence.fileExists()) {
            highscore = Integer.parseInt(persistence.loadDataString());
            refreshDasboardText();
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
    
    public void increaseLives() {
    	livesPlayer1++;
    	refreshDasboardText();
    }
    
    public int getLives() {
    	return livesPlayer1;
    }
    
    public int getScore() {
    	return scorePlayer1;
    }
    
    private void refreshDasboardText() {
        if (gameState != GameState.START) {
            dashboardHighscore.setText(String.format("%06d", highscore));
            dashboardText1.setText(String.format("%06d", scorePlayer1));
            dashboardPlayerLives1.setText(String.format("%01d", livesPlayer1));
        }
    }

    private void inititializeLevels() {
        levels = new ArrayList<>();
        int startPosition = 190;
        int marge = 25;
        for (int i = 0; i < 10; i++) {
            levels.add(new Level(i + 1, startPosition + i * marge));
        }
        currentLevel = levels.get(0);
    }

    public void increaseLevel() {
        if (currentLevel.getLevelNumber() <= 9) {
            currentLevel = levels.get(currentLevel.getLevelNumber());
        } else {
            currentLevel = levels.get(0);
        }

        addGameObject(new AlienContainer(this, 400, currentLevel.getStartPositionAliens(), alienKilled, 11, 22, 22));
        System.out.println("Je speelt nu level " + currentLevel.getLevelNumber());
    }
    
    public void updateHighscore() {
    	if (scorePlayer1 > highscore) {
    		persistence.saveData(Integer.toString(scorePlayer1));
    	}
    }

    public int getPlayfieldWidth() {
        return playfieldWidth;
    }

    public int getPlayfieldHeight() {
        return playfieldHeight;
    }

    public static void setGameState(GameState gameState) {
        SpaceInvaders.gameState = gameState;
    }

    public static GameState getGameState() {
        return gameState;
    }
}
