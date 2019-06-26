import codeanticode.syphon.SyphonClient;
import codeanticode.syphon.SyphonServer;
import controlP5.*;
import netP5.*;
import oscP5.*;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

// branch Test

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

    //controllparamter
    float ballSize;
    float mass;
    float friction;
    boolean frictionOn = false;
    float sizeM;
    float bounceSize;
    boolean bounceOn;
    int aColorOscIn = color(0, 255, 0);
    int fColorOscIn = color(0, 200, 0);
    int bColorOscIn = color(0, 150, 0);


    //GUI
    ControlP5 gui;


    // für dir Forces
    //float masse = 1; //masse der Metaballs
    //PVector wind = new PVector(0.1f, 0, 5f);
    //PVector gravity = new PVector(0f, 0.1f * masse);

    // Attractor
    Attractor a;


    public void settings() {
        size(1024, 768, P2D);
        //  pixelDensity(displayDensity());
    }

    public void setup() {

        // Start OSC listening for incoming massages at port XXXX
        // interesant OSC muss vor Syphone im setup stehen ansosten geht Syphone Client nicht.
        oscP5 = new OscP5(this, 9000);
        // Def die den OSC Emfpänger
        myRemoteLocation = new NetAddress("172.16.42.156", 8000);


        // GUI test
        gui = new ControlP5(this);

        gui.addSlider("ballSize")
                .setPosition(50, 20)
                .setSize(200, 20)
                .setRange(0f, 1f)
                .setValue(0.3f)
                .setLabel("BallSize")
                .setId(1)
        ;

        gui.addButton("bBallSize")
                .setLabel("")
                .setValue(0)
                .setPosition(15, 20)
                .setSize(20, 20)
                .setSwitch(true)
                .setId(2)
                .setColorActive(aColorOscIn)
                .setColorForeground(fColorOscIn)
                .setColorBackground(bColorOscIn)
        ;


        gui.addSlider("mass")
                .setPosition(50, 100)
                .setSize(200, 20)
                .setRange(0f, 1f)
                .setValue(0.3f)
                .setLabel("Mass")
                .setId(3)
        ;

        gui.addButton("bMass")
                .setLabel("")
                .setValue(0)
                .setPosition(15, 100)
                .setSize(20, 20)
                .setSwitch(true)
                .setId(4)
                .setColorActive(aColorOscIn)
                .setColorForeground(fColorOscIn)
                .setColorBackground(bColorOscIn)
        ;

        // der mutiplikator für de BallSize
        gui.addSlider("sizeM")
                .setPosition(50, 60)
                .setSize(200, 20)
                .setRange(2000f, 20000f)
                .setValue(0.003f)
                .setLabel("SizeM")
                .setId(5)
        ;

        gui.addButton("bSizeM")
                .setLabel("")
                .setValue(0)
                .setPosition(15, 60)
                .setSize(20, 20)
                .setSwitch(true)
                .setId(6)
                .setColorActive(aColorOscIn)
                .setColorForeground(fColorOscIn)
                .setColorBackground(bColorOscIn)
        ;

        gui.addSlider("friction")
                .setPosition(50, 140)
                .setSize(200, 20)
                .setRange(0f, 1f)
                .setValue(0.003f)
                .setLabel("Friction")
                .setId(7)
        ;

        gui.addButton("bFriction")
                .setLabel("")
                .setValue(0)
                .setPosition(15, 140)
                .setSize(20, 20)
                .setSwitch(true)
                .setId(8)
        ;

        gui.addSlider("bounce")
                .setPosition(50, 180)
                .setSize(200, 20)
                .setRange(0f, 1f)
                .setValue(0.003f)
                .setLabel("Bounce")
                .setId(9)
                .setValue(1)
        ;

        gui.addButton("bBounce")
                .setLabel("")
                .setValue(1)
                .setPosition(15, 180)
                .setSize(20, 20)
                .setSwitch(true)
                .setId(10)
                .setOn()
        ;


        friction = 0.0f;


        // die Metaballs werden initialisiertÅ
        mBalls = new MetaBalls(this);


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


        //** Attractor kräfte hinzufügen
        for (int i = 0; i < mBalls.anzahl; i++) {
            PVector aForce = a.attract(mBalls.balls[i]);

            // add Friction
            if (frictionOn) {
                PVector frivtionV = mBalls.balls[i].vel.get();
                frivtionV.mult(-1);
                frivtionV.normalize();
                frivtionV.mult(friction);
                mBalls.balls[i].applyFoce(frivtionV);
                //println(frivtionV);
            }
            mBalls.balls[i].applyFoce(aForce);
            if (bounceOn) {
                mBalls.balls[i].checkbounce(a.position, bounceSize * (width + 50));
            }
            mBalls.balls[i].update();
        }


        // ** Syphon get image
        if (client.newFrame()) {
            img = client.getImage(img, false); // does not load the pixels array (faster)
        }
        if (img != null) {
            tint(255, 60);
            image(img, 0, 0, width, height);
        }

        // show Attractor
        a.show();
        a.drag();
        a.hover(mouseX, mouseY);

        // parameter update
        mBalls.ballsR = ballSize * sizeM;
        a.mass = mass * 100;


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
                ballSize += 100;
                break;
            case 40:
                ballSize -= 100;
                break;

        }
    }

    public void mousePressed() {
        a.clicked(mouseX, mouseY);
    }

    public void mouseReleased() {
        a.stopDragging();

        OscMessage myMessage = new OscMessage("/1/xy1");
        myMessage.add(a.position.x / width);
        myMessage.add(a.position.y / height);
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
                ballSize = theOscMessage.get(0).floatValue();


                gui.getController("ballSize")
                        .setValue(ballSize)
                ;
            }
        }

        if (theOscMessage.checkAddrPattern("/1/fader2")) {
            if (theOscMessage.checkTypetag("f")) {
                mass = theOscMessage.get(0).floatValue();


                gui.getController("mass")
                        .setValue(mass)
                ;
            }
        }


        //print the address pattern and the typetag of the received OscMessage
        print("### received an osc message.");
        print(" addrpattern: " + theOscMessage.addrPattern());
        println(" typetag: " + theOscMessage.typetag());
    }


    public void controlEvent(ControlEvent theEvent) {

        //println("got a control event from controller with id " + theEvent.getController().getId());


        switch (theEvent.getController().getId()) {
            case (1):

                ballSize = (theEvent.getController().getValue());
                OscMessage myMessage = new OscMessage("/1/fader1");
                myMessage.add(ballSize);
                println(myMessage);
                oscP5.send(myMessage, myRemoteLocation);
                break;
            case (3):
                mass = (theEvent.getController().getValue());
                OscMessage myMessage2 = new OscMessage("/1/fader2");
                myMessage2.add(mass);
                println(myMessage2);
                oscP5.send(myMessage2, myRemoteLocation);
                break;

            case (7):
                friction = (theEvent.getController().getValue());
                friction /= 10;
                //OscMessage myMessage3 = new OscMessage("/1/fader3");
                //myMessage3.add(gravety);
                //println(myMessage3);
                //oscP5.send(myMessage3, myRemoteLocation);
                break;

            case (8):
                frictionOn = !frictionOn;
                break;

            case (9):
                bounceSize = (theEvent.getController().getValue());
                break;

            case (10):
                bounceOn = !bounceOn;
                break;


        }
    }


}
