import processing.core.PApplet;
import processing.core.PVector;

public class Ball {
    PApplet parent;

    // hier müssen die globale Variablen etc. deklariert werden
    PVector pos;
    PVector vel;
    float r;

    // Contrutor
    Ball(PApplet p, float x, float y) {
        parent = p;

        pos = new PVector(x,y);
        vel = PVector.random2D();
        vel.mult(parent.random(2,5));
        r = 40;

    }

    public void update() {
        pos.add(vel);

        if (pos.x < 0 || pos.x > parent.width){
            vel.x *= -1;
        }

        if (pos.y < 0 || pos.y > parent.height){
            vel.y *= -1;
        }
    }


    public void show() {
        parent.noFill();
        parent.stroke(255);
        parent.ellipse(pos.x,pos.y,r*2,r*2);
    }

}