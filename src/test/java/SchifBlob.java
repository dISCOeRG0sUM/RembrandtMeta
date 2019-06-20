import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;


public class SchifBlob {
    PApplet p;

    // hier müssen die globale Variablen etc. deklariert werden
    float minx;
    float miny;
    float maxx;
    float maxy;

    ArrayList<PVector> points;


    // Contrutor
    SchifBlob(PApplet parent, float x, float y) {
        p = parent;

        minx = x;
        miny = y;
        maxx = x;
        maxy = y;
        points = new ArrayList<PVector>();
        points.add(new PVector(x, y));
    }

    public void show() {
        p.stroke(0);
        p.fill(255);
        p.strokeWeight(2);
        p.rectMode(p.CORNERS);
        p.rect(minx, miny, maxx, maxy);

        for (PVector v : points) {
            //stroke(0, 0, 255);
            //point(v.x, v.y);
        }
    }


    public void add(float x, float y) {
        points.add(new PVector(x, y));
        minx = PApplet.min(minx, x);
        miny = PApplet.min(miny, y);
        maxx = PApplet.max(maxx, x);
        maxy = PApplet.max(maxy, y);
    }

    public float size() {
        return (maxx-minx)*(maxy-miny);
    }

    public boolean isNear(float x, float y, float distThreshold) {

        // The Rectangle "clamping" strategy
        // float cx = max(min(x, maxx), minx);
        // float cy = max(min(y, maxy), miny);
        // float d = distSq(cx, cy, x, y);

        // Closest point in blob strategy
        float d = 10000000;
        for (PVector v : points) {
            float tempD = distSq(x, y, v.x, v.y);
            if (tempD < d) {
                d = tempD;
            }
        }

        return d < distThreshold * distThreshold;
    }

    // Custom distance functions w/ no square root for optimization
    public float distSq(float x1, float y1, float x2, float y2) {
        float d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        return d;
    }


    public float distSq(float x1, float y1, float z1, float x2, float y2, float z2) {
        float d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1);
        return d;

    }
}