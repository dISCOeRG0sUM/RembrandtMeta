//import codeanticode.syphon.SyphonServer;
import processing.core.PApplet;
import themidibus.MidiBus;

public class RembrandMeta1 extends PApplet{

    public static void main(String[] args) {
        PApplet.main("RembrandMeta1");
    }

    MidiBus myMidi;
    MetaBalls mBalls;
//    SyphonServer serverSyphone;



    public void settings(){
        size(1024, 768, P2D);
        //  pixelDensity(displayDensity());
    }

    public void setup(){


        // die Metaballs werden initialisiert
        mBalls = new MetaBalls(this);

        // ** Midi
        // Gibt alle verfügbaren Midi Devices aus
        MidiBus.list();

        myMidi = new MidiBus(this,0,1);
        //**

        // ** Syphon
 //       serverSyphone = new SyphonServer(this, "RembrandMeta1");
        //**

    }

    public void draw(){
        // send via Syphon begin----------------------------------------------------------------------------------------
        background(0);

        mBalls.show();


        //!!!!!!irgend was will hier noch nicht https://github.com/Syphon/Processing/issues/18
        //serverSyphone.sendScreen();
        // send via Syphon end------------------------------------------------------------------------------------------

        // hier begint das Interface------------------------------------------------------------------------------------

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
                                mBalls.ballsR += 1;
                                break;
                            case 127:
                                mBalls.ballsR -= 1;
                                break;
                        }
                        break;
                    case 16:
                        mBalls.ballsR = 10 * value;

                }
                break;
        }

    }

}
