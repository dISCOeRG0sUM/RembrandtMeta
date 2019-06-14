import processing.core.PApplet;
import processing.core.*;


public class Forces extends PApplet {


    public static void main(String[] args) {
        PApplet.main("Forces");
    }

    Mover[] movers = new Mover[20];

    public void settings() {
        size(1280, 720, P2D);
        pixelDensity(displayDensity());
    }

    public void setup() {

        for (int i = 0; i < movers.length; i++) {
            movers[i] = new Mover(this, random(1, 4), 0, 0);
        }

    }

    public void draw() {

        background(255);

        for (int i = 0; i < movers.length; i++) {

            PVector wind = new PVector(0.01f, 0.5f);
            PVector gravity = new PVector(0f, 0.1f * movers[i].mass);

            movers[i].applyForce(wind);
            movers[i].applyForce(gravity);

            movers[i].update();
            movers[i].display();
            movers[i].checkEdges();

        }
    }
}



// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com
