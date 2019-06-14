import processing.core.PApplet;
import processing.core.PVector;

public class Mover{
    PApplet p;

    // hier müssen die globale Variablen etc. deklariert werden
    PVector position;
    PVector velocity;
    PVector acceleration;
    float mass;


    // Contrutor
    Mover(PApplet parent, float m, float x , float y) {
        p = parent;
        mass = m;
        position = new PVector(x,y);
        velocity = new PVector(0,0);
        acceleration = new PVector(0,0);
    }


    void applyForce(PVector force) {
        PVector f = PVector.div(force,mass);
        acceleration.add(f);
    }

    void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);
    }

    void display() {
        p.stroke(0);
        p.strokeWeight(2);
        p.fill(0,127);
        p.ellipse(position.x,position.y,mass*16,mass*16);
    }

    void checkEdges() {

        if (position.x > p.width) {
            position.x = p.width;
            velocity.x *= -1;
        } else if (position.x < 0) {
            velocity.x *= -1;
            position.x = 0;
        }

        if (position.y > p.height) {
            velocity.y *= -1;
            position.y = p.height;
        }

    }
}

// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com


