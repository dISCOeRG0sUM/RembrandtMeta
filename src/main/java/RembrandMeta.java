import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import themidibus.MidiBus;

public class RembrandMeta extends PApplet{

    public static void main(String[] args) {
        PApplet.main("RembrandMeta");
    }

    MidiBus myMidi;
    MetaBalls mBalls;

    //** Hintergrund Bilder
    PImage [] myPix;
    int indexPix = 0;
    int numOfPix = 5;

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

        // ** Midi
        // Gibt alle verfügbaren Midi Devices aus
        MidiBus.list();

        myMidi = new MidiBus(this,0,1);



        // ** Hintergrund Bilder laden
        myPix = new PImage[numOfPix];
        for (int i = 0; i < numOfPix; i++){
            myPix[i] = loadImage("Pix/" +i +".png", "png");
        }




    }

    public void draw(){

        background(0);

        // HIntergrund Bilder anzeigen
        indexPix = constrain(indexPix,0,numOfPix -1);
        image(myPix[indexPix],0,0);

        // Metaballs anzeigen
        mBalls.show();

        // füge Krafte hin zu
        mBalls.applyForces(wind);
        mBalls.applyForces(gravity);





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


    // Midi Eventhandler------------------------------------------------------------------------------------------------
    public void noteOn(int channel, int pitch, int velocity) {
        // Receive a noteOn
        println();
        println("Note On:");
        println("--------");
        println("Channel:" + channel);
        println("Pitch:" + pitch);
        println("Velocity:" + velocity);
    }

    public void noteOff(int channel, int pitch, int velocity) {
        // Receive a noteOff
        println();
        println("Note Off:");
        println("--------");
        println("Channel:"+channel);
        println("Pitch:"+pitch);
        println("Velocity:"+velocity);
    }

    public void controllerChange(int channel, int number, int value) {
        // Receive a controllerChange
        println();
        println("Controller Change:");
        println("--------");
        println("Channel:"+channel);
        println("Number:"+number);
        println("Value:"+value);

        switch (channel){
            case 0:
                switch (number){
                    case 20:
                        switch (value){
                            case 1:
                                mBalls.ballsR += 20;
                                break;
                            case 127:
                                mBalls.ballsR -= 20;
                                break;
                        }
                        break;
                    case 21:
                        switch (value){
                            case 1:
                                indexPix += 1;
                                println("intexPix: " +indexPix);
                                break;
                            case 127:
                                indexPix -= 1;
                                println("intexPix: " +indexPix);
                                break;
                        }
                        break;

                    case 16:
                        mBalls.ballsR = 10 * value; // für carens meady board
                        break;



                }
                break;
        }

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
            case 39:
                indexPix += 1;
                //println("intexPix: " +indexPix);
                break;
            case 37:
                indexPix -= 1;
                //println("intexPix: " +indexPix);
                break;
        }
    }

}
