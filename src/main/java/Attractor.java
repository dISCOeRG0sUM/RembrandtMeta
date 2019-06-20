import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Attractor {
    PApplet p;

    // hier müssen die globale Variablen etc. deklariert werden
    float mass;
    float G;
    PVector position;
    boolean dragging = false;
    boolean rollover = false;
    PVector dragOffset;


    // Contrutor
    Attractor(PApplet parent) {
        p = parent;

        position = new PVector(p.width / 2, p.height / 2);
        mass = 20;
        G = 1;
        dragOffset = new PVector(0f, 0f);
    }

    public PVector attract(Ball b) {
        PVector force = PVector.sub(position, b.pos);
        float d = force.mag();
        d = PApplet.constrain(d, 5f, 25f);
        force.normalize();
        float strength = (G * mass * b.mass) / (d * d);
        force.mult(strength);
        return force;
    }

    public void show() {
        p.ellipseMode(PConstants.CENTER);
        p.strokeWeight(2);
        p.stroke(0);
        if (dragging) p.fill(50);
        else if (rollover) p.fill(100);
        else p.fill(150, 200);
        p.ellipse(position.x, position.y, mass * 2, mass * 2);
    }

    // The methods below are for mouse interaction
    public void clicked(int mx, int my) {
        float d = PApplet.dist(mx, my, position.x, position.y);
        if (d < mass) {
            dragging = true;
            dragOffset.x = position.x - mx;
            dragOffset.y = position.y - my;
        }
    }

    public void hover(int mx, int my) {
        float d = PApplet.dist(mx, my, position.x, position.y);
        rollover = d < mass;
    }

    public void stopDragging() {
        dragging = false;
    }

    public void drag() {
        if (dragging) {
            position.x = p.mouseX + dragOffset.x;
            position.y = p.mouseY + dragOffset.y;
        }
    }

}
