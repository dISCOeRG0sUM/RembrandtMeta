import processing.core.PApplet;
import processing.core.PVector;

public class Gravitation extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Gravitation");
    }

    Mover[] movers = new Mover[10];
    AttractorTest a;

    public void settings() {
        size(1280, 720, P2D);
        pixelDensity(displayDensity());
    }

    public void setup() {

        for (int i = 0; i < movers.length; i++) {
            movers[i] = new Mover(this, random(0.1f, 2f), random(width), random(height));
        }
        a = new AttractorTest(this);

    }

    public void draw() {

        background(255);

        a.show();
        a.drag();
        a.hover(mouseX, mouseY);

        for (int i = 0; i < movers.length; i++) {
            PVector force = a.attract(movers[i]);
            movers[i].applyForce(force);

            movers[i].update();
            movers[i].display();
        }

    }

    public void mousePressed() {
        a.clicked(mouseX, mouseY);
    }

    public void mouseReleased() {
        a.stopDragging();
    }

}
