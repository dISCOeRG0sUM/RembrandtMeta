import codeanticode.syphon.SyphonClient;
import codeanticode.syphon.SyphonServer;
import netP5.*;
import oscP5.*;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;


public class RembrandMeta extends PApplet {

    public static void main(String[] args) {
        PApplet.main("RembrandMeta");
    }

    MetaBalls mBalls;
    SyphonClient client;
    SyphonServer server;
    PImage img;

    PGraphics sendSypongImg;
    OscP5 oscP5;
    NetAddress myRemoteLocation;

    //GUI
    UserInterface gui;


    // für dir Forces
    float masse = 1; //masse der Metaballs
    PVector wind = new PVector(0.1f, 0, 5f);
    PVector gravity = new PVector(0f, 0.1f * masse);

    // Attractor
    Attractor a;


    public void settings() {
        size(1024, 768, P2D);
        //  pixelDensity(displayDensity());
    }

    public void setup() {

        // gui test
        gui = new UserInterface(this);

        // die Metaballs werden initialisiertÅ
        mBalls = new MetaBalls(this);

        // Start OSC listening for incoming massages at port XXXX
        // interesant OSC muss vor Syphone im setup stehen ansosten geht Syphone Client nicht.
        oscP5 = new OscP5(this, 9000);
        // Def die den OSC Emfpänger
        myRemoteLocation = new NetAddress("192.168.178.23",8000);

        // Syphong
        client = new SyphonClient(this);
        server = new SyphonServer(this, "Processing Syphon");

        sendSypongImg = createGraphics(width, height, P2D);

        // Attractor
        a = new Attractor(this);


    }

    public void draw() {

        sendSypongImg.beginDraw();
        sendSypongImg.background(0);

        // ** Metaballs anzeigen
        sendSypongImg.image(mBalls.show(), 0, 0);
        mBalls.update();

        sendSypongImg.endDraw();
        image(sendSypongImg, 0, 0);

        // ** Syphon send imag
        server.sendImage(sendSypongImg);

        // ** füge Krafte hin zu
        //mBalls.applyForces(wind);
        //mBalls.applyForces(gravity);

        //** Attractor kräfte hinzufügen
        for (int i = 0; i < mBalls.anzahl; i++) {
            PVector aForce = a.attract(mBalls.balls[i]);
            mBalls.balls[i].applyFoce(aForce);
            mBalls.balls[i].update();
        }


        // ** Syphon get image
        if (client.newFrame()) {
            img = client.getImage(img, false); // does not load the pixels array (faster)
        }
        if (img != null) {
            tint(255, 120);
            image(img, 0, 0, width, height);
        }

        // show Attractor
        a.show();
        a.drag();
        a.hover(mouseX, mouseY);


        //** FrameRate kontrolle und Warnung
        if (frameRate < 30) {
            fill(255, 0, 0);
        } else {
            fill(255);
        }
        textSize(14);
        text("FrameRate: " + frameRate, 20, height - 20);
    }


    public void keyPressed() {
        println("keyCode: " + keyCode);

        switch (keyCode) {
            case 38:
                mBalls.ballsR += 100;
                break;
            case 40:
                mBalls.ballsR -= 100;
                break;

        }
    }

    public void mousePressed() {
        a.clicked(mouseX, mouseY);
    }

    public void mouseReleased() {
        a.stopDragging();

        OscMessage myMessage = new OscMessage("/1/xy1");
        myMessage.add(a.position.x/width);
        myMessage.add(a.position.y/height);
        println(myMessage);
        oscP5.send(myMessage, myRemoteLocation);
    }

    public void oscEvent(OscMessage theOscMessage) {


        if (theOscMessage.checkAddrPattern("/1/xy1")) {
            if (theOscMessage.checkTypetag("ff")) {
                a.position.x = (theOscMessage.get(0).floatValue()) * width;
                a.position.y = (theOscMessage.get(1).floatValue()) * height;

            }
        }


        if (theOscMessage.checkAddrPattern("/1/fader1")) {
            if (theOscMessage.checkTypetag("f")) {
                mBalls.ballsR = (theOscMessage.get(0).floatValue())*20000;
            }
        }


        //print the address pattern and the typetag of the received OscMessage
        //print("### received an osc message.");
        //print(" addrpattern: " + theOscMessage.addrPattern());
        //println(" typetag: " + theOscMessage.typetag());
    }


}
