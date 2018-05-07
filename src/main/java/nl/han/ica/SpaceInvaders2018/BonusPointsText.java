package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;

/**
 * Klasse waarmee tijdelijke tekst wordt afgebeeld, zoals de bonuspunten die een ruimteschip geeft als het geraakt wordt
 */
public class BonusPointsText extends TextObject implements IAlarmListener {
	
	/**
	 * Referentie naar de hoofdmodule
	 */
	private SpaceInvaders world;
	
	/**
	 * Hoeveel seconden het object blijft bestaan
	 */
	private int lifespan;

	/**
	 * Constructor
	 * @param text		tekst (de bonuspunten)
	 * @param fontSize	lettergrootte
	 * @param world		Referentie naar de hoofdmodule
	 */
	public BonusPointsText(String text, int fontSize, SpaceInvaders world) {
		super(text, fontSize);
		this.world = world;
		this.lifespan = 2;
		setySpeed(-0.2f);
		lifespanAlarm();
	}
	
	/**
	 * Alternatieve constructor
	 * @param text		tekst (de bonuspunten)
	 * @param fontSize	lettergrootte
	 * @param lifespan	Hoeveel seconden het object blijft bestaan
	 * @param world		Referentie naar de hoofdmodule
	 */
	public BonusPointsText(String text, int fontSize, int lifespan, SpaceInvaders world) {
		super(text, fontSize);
		this.world = world;
		this.lifespan = lifespan;
		setySpeed(-0.2f);
		lifespanAlarm();
	}
	
	/**
	 * Zet alarm voor hoe lang de tekst zichtbaar moet blijven
	 */
	private void lifespanAlarm() {
        Alarm alarm=new Alarm("Lifespan of this TextObject", lifespan);
        alarm.addTarget(this);
        alarm.start();
    }

	/**
	 * Verwijdert dit object
	 */
	@Override
	public void triggerAlarm(String alarmName) {
		world.deleteGameObject(this);
	}

	
}
