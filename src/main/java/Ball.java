import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.dist;

public class Ball {
    PApplet parent;

    // hier müssen die globale Variablen etc. deklariert werden
    PVector pos; //position
    PVector vel; //velocety
    PVector acc; //acceleration;
    float r;
    float mass;
    int erweiterung = 100; // wie weit die bälle das Display verlassen können

    boolean over = false;

    // Contrutor
    Ball(PApplet p, float m, float x, float y) {
        parent = p;

        mass = m;
        pos = new PVector(x, y);
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);
        r = 50;

    }

    public void applyFoce(PVector force) {
        PVector f = PVector.div(force, mass);
        acc.add(f);
    }


    public void update() {
        vel.add(acc);
        pos.add(vel);
        acc.mult(0);
    }

    public void checkbounce(PVector center, float distence) {

        float d = dist(pos.x, pos.y, center.x, center.y);


        if (d > distence && !over) {
            vel.mult(-1);
            over = true;
        } else if (d < distence && over) {
            over = false;
        }

        // ball bounce
        /*if (pos.x < 0 - erweiterung || pos.x > parent.width + erweiterung) {
            vel.x *= -1;
        }

        if (pos.y < 0 - erweiterung || pos.y > parent.height + erweiterung) {
            vel.y *= -1;
        }
*/

    }


    public void show() {
        parent.noFill();
        parent.stroke(255);
        parent.ellipse(pos.x, pos.y, r * 2, r * 2);
    }

}