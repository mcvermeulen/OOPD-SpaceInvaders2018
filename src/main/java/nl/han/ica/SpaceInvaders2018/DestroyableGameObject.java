package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public abstract class DestroyableGameObject extends AnimatedSpriteObject implements IAlarmListener {
	protected SpaceInvaders world;
	private Explosion explosion;

    public DestroyableGameObject(Sprite sprite, int totalFrames, float x, float y, int sWidth, int sHeight, SpaceInvaders world) {
        super(sprite, totalFrames);
        if (x > 0) this.x = x;
        if (y > 0) this.y = y;
        this.setWidth(sWidth);
        this.setHeight(sHeight);
        setCurrentFrameIndex(0);
        this.world = world;
        explosion = new Explosion();
    }

    public void explode() {
        world.addGameObject(explosion, getX(), getY());
        explosionAlarm();
    }

    private void explosionAlarm() {
        Alarm alarm=new Alarm("remove explosion", 0.3);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        world.deleteGameObject(explosion);
    }
     
}
