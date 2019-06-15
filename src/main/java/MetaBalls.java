import processing.core.PApplet;

import static processing.core.PApplet.dist;
import static processing.core.PApplet.println;

public class MetaBalls {
    PApplet parent;

    // hier müssen die globale Variablen etc. deklariert werden
    Ball[] balls = new Ball[3];
    float ballsR = 200;


    // Contrutor
    MetaBalls(PApplet p) {
        parent = p;

        for (int i = 0; i < balls.length; i++){
            balls[i] = new Ball(parent,parent.random(parent.width),parent.random(parent.height));
        }

    }


    public void show() {

        parent.loadPixels();
        for (int x = 0; x < parent.width; x++) {
            for (int y = 0; y < parent.height; y++) {
                int index = x + y * parent.width;
                float sum = 0;
                for (Ball b : balls) {
                    float d = dist(x, y, b.pos.x, b.pos.y);
                    sum += ballsR * b.r / d;
                    println(sum);
                }
                parent.pixels[index] = parent.color(255,255,255,sum); // warum will Alfa nicht funktionieren?
            }
        }

        parent.updatePixels();

        for (Ball b : balls) {
            b.update();
        }

    }
}