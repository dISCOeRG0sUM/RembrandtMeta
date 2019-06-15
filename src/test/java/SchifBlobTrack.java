import processing.core.PApplet;
import processing.video.Capture;
import java.util.ArrayList;

public class SchifBlobTrack extends PApplet {

    public static void main(String[] args) {
        PApplet.main("SchifBlobTrack");
    }

    Capture video;

    int trackColor;
    float threshold = 25;
    float distThreshold = 50;

    ArrayList<SchifBlob> blobs = new ArrayList<SchifBlob>();

    public void settings() {
        size(1280, 720, P2D);
        pixelDensity(displayDensity());
    }

    public void setup() {
        String[] cameras = Capture.list();
        printArray(cameras);
        video = new Capture(this, 640, 360);
        video.start();
        trackColor = color(255, 0, 0);
    }

    public void captureEvent(Capture video) {
        video.read();
    }

    public void keyPressed() {
        if (key == 'a') {
            distThreshold += 5;
        } else if (key == 'z') {
            distThreshold -= 5;
        }
        if (key == 's') {
            threshold += 5;
        } else if (key == 'x') {
            threshold -= 5;
        }


        println(distThreshold);
    }

    public void draw() {
        video.loadPixels();
        image(video, 0, 0);

        blobs.clear();


        // Begin loop to walk through every pixel
        for (int x = 0; x < video.width; x++) {
            for (int y = 0; y < video.height; y++) {
                int loc = x + y * video.width;
                // What is current color
                int currentColor = video.pixels[loc];
                float r1 = red(currentColor);
                float g1 = green(currentColor);
                float b1 = blue(currentColor);
                float r2 = red(trackColor);
                float g2 = green(trackColor);
                float b2 = blue(trackColor);

                float d = distSq(r1, g1, b1, r2, g2, b2);

                if (d < threshold * threshold) {

                    boolean found = false;
                    for (SchifBlob b : blobs) {
                        if (b.isNear(x, y,distThreshold)) {
                            b.add(x, y);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        float fx = (float) x; // convert int to float
                        float fy = (float) y; // convert int to float
                        SchifBlob b = new SchifBlob(this,fx,fy);
                        blobs.add(b);
                    }
                }
            }
        }

        for (SchifBlob b : blobs) {
            if (b.size() > 500) {
                b.show();
            }
        }

        textAlign(RIGHT);
        fill(0);
        text("distance threshold: " + distThreshold, width - 10, 25);
        text("color threshold: " + threshold, width - 10, 50);
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

    public void mousePressed() {
        // Save color where the mouse is clicked in trackColor variable
        int loc = mouseX + mouseY * video.width;
        trackColor = video.pixels[loc];

    }
}



