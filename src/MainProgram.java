import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainProgram { // Justin
	// unchangeable screen resolution
	final static int SCREENX = 1024;
	final static int SCREENY = 768;

	public static void main(String[] args) throws IOException {
		EZ.initialize(SCREENX, SCREENY);// keep this here
		EZ.setBackgroundColor(new Color(0, 0, 0));// anti-Epilepsy
		int level = 0;
		float GameMode = 0f;
		EZImage nextS;
		EZImage EndG;
		EZImage Title;
		EZImage go;
		EZSound ost = EZ.addSound("menu.wav");
		EZSound music = EZ.addSound("game.wav");
		ost.loop();
		World w1 = null;
		World w2 = null;
		World w3 = null;
		level = 0;
		while (true) {
			// title screen loader
			if (GameMode == 0f) {
				Title = EZ.addImage("titleScreen.png", SCREENX / 2, SCREENY / 2);
				if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
					ost.stop();
					music.loop();
					EZ.removeAllEZElements();
					GameMode = 0.5f;
					level = 1;
				}
			}
			// level 1
			if (GameMode == 0.5f && level == 1) {
				music.play();
				w1 = new World("World1.txt", "castle.png");
				w1.generate();
				GameMode = 1f;
			}

			if (GameMode == 1f && level == 1) {
				w1.worldGo(5);
				if (w1.isGameOver()) {
					GameMode = 1.1f;
				}
				if (w1.reach()) {
					GameMode = 0.01f;
					w1.removeScan();
				}
			}

			if (GameMode == 1.1f) {
				music.pause();
				EZ.removeAllEZElements();
				GameMode = 999f;
			}

			// level 2
			if (GameMode == 0.5f && level == 2) {
				music.play();
				w2 = new World("World2.txt", "bowser.jpg");
				w2.generate();
				GameMode = 1f;
			}

			if (GameMode == 1f && level == 2) {
				w2.worldGo(7);
				if (w2.isGameOver()) {
					GameMode = 1.1f;
				}
				if (w2.reach()) {
					GameMode = 0.01f;
					w2.removeScan();
				}
			}

			// level 3
			if (GameMode == 0.5f && level == 3) {
				music.play();
				w3 = new World("World3.txt", "wor3.png");
				w3.generate();
				GameMode = 1f;
			}

			if (GameMode == 1f && level == 3) {
				w3.worldGo(10);
				if (w3.isGameOver()) {
					GameMode = 1.1f;
				}
				if (w3.reach()) {
					GameMode = 10000f;
					w3.removeScan();
				}
			}

			// extra loaders
			if (GameMode == 999f) {
				go = EZ.addImage("go.jpg", SCREENX / 2, SCREENY / 2);
				GameMode = 999.9f;
			}
			if (GameMode == 999.9f) {
				go = EZ.addImage("go.jpg", SCREENX / 2, SCREENY / 2);
				if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
					music.play();
					EZ.removeAllEZElements();
					GameMode = 0.5f;
				}
			}

			if (GameMode == 10000f) {
				EZ.removeAllEZElements();
				music.stop();
				EndG = EZ.addImage("congrats.png", SCREENX / 2, SCREENY / 2);
			}

			if (GameMode == 0.01f) {
				EZ.removeAllEZElements();
				nextS = EZ.addImage("next.jpg", SCREENX / 2, SCREENY / 2);
				music.pause();
				if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
					EZ.removeAllEZElements();
					level++;
					GameMode = 0.5f;
				}
			}

			// Do not move this EZ refresh
			EZ.refreshScreen();
		}
	}

}