// es kommt keine fehler Meldug aber an scheinen sendet der server nicht.

import processing.core.PApplet;
import codeanticode.syphon.*;

public class SyphonSend extends PApplet{

    public static void main(String[] args) {
        PApplet.main("SyphonSend");
    }

    SyphonServer server;

    public void settings(){
        size(1280, 720,P2D);
        pixelDensity(displayDensity());
    }

    public void setup(){
        // Create syhpon server to send frames out.
        server = new SyphonServer(this, "Processing Syphon");
    }



    public void draw(){
        background(127);
        fill(0);
        ellipse(width/2,height/2,100,100);
    }


}
