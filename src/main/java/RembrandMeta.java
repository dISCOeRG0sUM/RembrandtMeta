import codeanticode.syphon.SyphonClient;
import codeanticode.syphon.SyphonServer;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;



public class RembrandMeta extends PApplet{

    public static void main(String[] args) {
        PApplet.main("RembrandMeta");
    }

    MetaBalls mBalls;
    SyphonClient client;
    SyphonServer server;
    PImage img;

    PGraphics sendSypongImg;


    // für dir Forces
    float masse = 1; //masse der Metaballs
    PVector wind = new PVector(0.1f, 0,5f);
    PVector gravity = new PVector(0f,0.1f * masse);



    public void settings(){
        size(1024, 768, P2D);
        //  pixelDensity(displayDensity());
    }

    public void setup(){


        // die Metaballs werden initialisiertÅ
        mBalls = new MetaBalls(this);

        // ** Syphong
        client = new SyphonClient(this);
        server = new SyphonServer(this, "Processing Syphon");

        sendSypongImg = createGraphics(width, height,P2D);



    }

    public void draw(){

        sendSypongImg.beginDraw();
        sendSypongImg.background(0);

        // ** Metaballs anzeigen
        sendSypongImg.image(mBalls.show(), 0, 0);  // wie kann ich das hier in das sendSyponImag bekommen?
        mBalls.update();

        sendSypongImg.endDraw();
        image(sendSypongImg,0,0);

        // ** Syphon send imag
        server.sendImage(sendSypongImg);

        // ** füge Krafte hin zu
        mBalls.applyForces(wind);
        mBalls.applyForces(gravity);


        // ** Syphon get image
        if (client.newFrame()) {
            // The first time getImage() is called with
            // a null argument, it will initialize the PImage
            // object with the correct size.
            //img = client.getImage(img); // load the pixels array with the updated image info (slow)
            img = client.getImage(img, false); // does not load the pixels array (faster)
        }
        if (img != null) {
            tint(255,120);
            image(img, 0, 0, width, height);
        }


        //** FrameRate kontrolle und Warnung
        if (frameRate < 30){
            fill(255,0,0);
        }else {
            fill(255);
        }
        textSize(14);
        text("FrameRate: " + frameRate, 20, height - 20);
        // **
    }




    public void keyPressed(){
        println("keyCode: " +keyCode);

        switch (keyCode){
            case 38:
                mBalls.ballsR += 100;
                break;
            case 40:
                mBalls.ballsR -= 100;
                break;

                /*
            case 39:
                indexPix += 1;
                //println("intexPix: " +indexPix);
                break;
            case 37:
                indexPix -= 1;
                //println("intexPix: " +indexPix);
                break;

                 */
        }
    }

}
