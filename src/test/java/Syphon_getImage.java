import codeanticode.syphon.SyphonClient;
import processing.core.PApplet;
import processing.core.PImage;

public class Syphon_getImage extends PApplet{

    public static void main(String[] args) {
        PApplet.main("Syphon_getImage");
    }

    PImage img;

    SyphonClient client;

    public void settings(){
        size(1280, 720,P2D);
        //pixelDensity(displayDensity());
    }

    public void setup(){
        client = new SyphonClient(this);


    }

    public void draw(){
        background(0);
        if (client.newFrame()) {
            // The first time getImage() is called with
            // a null argument, it will initialize the PImage
            // object with the correct size.
            img = client.getImage(img); // load the pixels array with the updated image info (slow)
            //img = client.getImage(img, false); // does not load the pixels array (faster)
        }
        if (img != null) {
            image(img, 0, 0, width, height);
        }
    }

    public void keyPressed() {
        if (key == ' ') {
            client.stop();
        } else if (key == 'd') {
            println(client.getServerName());
        }
    }
    }
