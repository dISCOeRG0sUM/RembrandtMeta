import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PApplet.dist;
import static processing.core.PConstants.ARGB;

public class MetaBalls{
    PApplet parent;

    // hier müssen die globale Variablen etc. deklariert werden
    Ball[] balls = new Ball[5];
    float ballsR = 800;
    PImage img;

    // Contrutor
    MetaBalls(PApplet p) {
        parent = p;

        for (int i = 0; i < balls.length; i++){
            balls[i] = new Ball(parent,parent.random(parent.width),parent.random(parent.height));
        }

        img = parent.createImage(parent.width,parent.height,ARGB);

    }


    public void show() {


        img.loadPixels();
        for (int x = 0; x < parent.width; x++) {
            for (int y = 0; y < parent.height; y++) {
                int index = x + y * parent.width;
                float sum = 0;
                for (Ball b : balls) {
                    float d = dist(x, y, b.pos.x, b.pos.y);
                    sum += ballsR / d;
                }
                img.pixels[index] = parent.color(255,255,255, sum);
            }
        }
        img.updatePixels();

        parent.image(img,0,0);

        for (Ball b : balls) {
            b.update();
        }

    }
}