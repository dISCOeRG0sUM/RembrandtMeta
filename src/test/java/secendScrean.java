// http://lagers.org.uk/g4p/guides/g02-windows.html

import g4p_controls.GWinData;
import g4p_controls.GWindow;
import processing.core.PApplet;

public class secendScrean extends PApplet{

    public static void main(String[] args) {
        PApplet.main("secendScrean");
    }

    GWindow window;

    public void settings(){
        size(1280, 720,P2D);
        //pixelDensity(displayDensity());
    }

    public void setup(){
        window = GWindow.getWindow(this,"Controls",100,50,800, 600,P2D);
        window.addDrawHandler(this,"windowDraw");

    }

    public void draw(){
        background(0);
        ellipse(50, 50, 10, 10);

    }

    public void windowDraw(PApplet app, GWinData data){
        app.background(255);
        app.fill(0);
        app.ellipse(50,50,10,10);
    }


}