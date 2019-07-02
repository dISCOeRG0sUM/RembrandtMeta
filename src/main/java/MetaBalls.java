import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PApplet.dist;
import static processing.core.PConstants.ARGB;

//damit ich die MetaBalls wieder zum laufen bekomme

public class MetaBalls{
    PApplet parent;

    int anzahl = 5;
    Ball[] balls = new Ball[anzahl];
    float ballsR = 800;
    PImage img;
    float sum;


    // Contrutor
    MetaBalls(PApplet p) {
        parent = p;

        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(parent, parent.random(1, 4), parent.random(parent.width), parent.random(parent.height));
        }

        img = parent.createImage(parent.width, parent.height, ARGB);



    }




    public PImage show() {


        img.loadPixels();
        for (int x = 0; x < parent.width; x++) {
            for (int y = 0; y < parent.height; y++) {
                int index = x + y * parent.width; // lauf jeden pixel ab
                float sum = 0;
                for (Ball b : balls) {
                    float d = dist(x, y, b.pos.x, b.pos.y);
                    sum += ballsR / (d*d*d);
                }
                img.pixels[index] = parent.color(255, 255, 255, sum);
            }
        }

        img.updatePixels();


        return img;
    }

    public void update() {
        for (Ball b : balls) {
            b.update();
        }
    }

}