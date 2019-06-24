import processing.core.PApplet;
import oscP5.*;

public class oscRecive extends PApplet{

    public static void main(String[] args) {
        PApplet.main("oscRecive");
    }

    OscP5 oscP5;

    public void settings(){
        size(1280, 720,P2D);
        pixelDensity(displayDensity());
    }

    public void setup(){

        /* start oscP5, listening for incoming messages at port 12000 */
        oscP5 = new OscP5(this,12000);

    }

    public void draw(){

    }

    public void oscEvent(OscMessage theOscMessage) {
        /* check if theOscMessage has the address pattern we are looking for. */

        if(theOscMessage.checkAddrPattern("/test")==true) {
            /* check if the typetag is the right one. */
            if(theOscMessage.checkTypetag("ifs")) {
                /* parse theOscMessage and extract the values from the osc message arguments. */
                int firstValue = theOscMessage.get(0).intValue();
                float secondValue = theOscMessage.get(1).floatValue();
                String thirdValue = theOscMessage.get(2).stringValue();
                print("### received an osc message /test with typetag ifs.");
                println(" values: "+firstValue+", "+secondValue+", "+thirdValue);
                return;
            }
        }
        println("### received an osc message. with address pattern "+theOscMessage.addrPattern());
    }

}