public class Player { // Nick
	// public int state = 0;
	public double x = 0;
	public double y = 0;
	public EZImage image;

	public Player(String imagename, int xpos, int ypos) { // Constructor of player class
		x = xpos;
		y = ypos;
		image = EZ.addImage(imagename, xpos, ypos);
	}

	public void move(double xpos, double ypos) { // moves the player image
		x = xpos + xpos;
		y = ypos + ypos;
		image.translateBy(xpos, ypos);
	}

	public void kill() { // remove player image
		EZ.removeEZElement(image);
	}
}
