import blobDetection.*;
import processing.core.PApplet;
import processing.core.PImage;

/*
Beschreibung

Das ist ein ver such von Blubditection in Processing Bzw Java
für Processing versuche ich diese Lib zu benutzen
http://www.v3ga.net/processing/BlobDetection/index-page-documentation.html

Die alternativer wäre OpenCV für Java da opencv für Processing nicht das unter stütztt was ich will.
https://docs.opencv.org/2.4/doc/tutorials/introduction/desktop_java/java_dev_intro.html



gibt zur zeit immer ein error...

/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=53572:/Applications/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/tools.jar:/Users/jendrik/Documents/Processing/Lichtpiraten/RembrandtMeta/target/test-classes:/Users/jendrik/Documents/Processing/Lichtpiraten/RembrandtMeta/target/classes:/Users/jendrik/.m2/repository/org/processing/core/3.3.7/core-3.3.7.jar:/Users/jendrik/.m2/repository/org/processing/video/3.3.7/video-3.3.7.jar:/Users/jendrik/.m2/repository/com/googlecode/gstreamer-java/gstreamer-java/1.5/gstreamer-java-1.5.jar:/Users/jendrik/.m2/repository/net/java/dev/jna/platform/3.4.0/platform-3.4.0.jar:/Users/jendrik/.m2/repository/com/apple/AppleJavaExtensions/1.4/AppleJavaExtensions-1.4.jar:/Users/jendrik/.m2/repository/org/eclipse/swt/carbon/macosx/3.3.0-v3346/macosx-3.3.0-v3346.jar:/Users/jendrik/.m2/repository/net/java/dev/jna/jna/4.0.0/jna-4.0.0.jar:/Users/jendrik/.m2/repository/org/processing/pde/3.3.7/pde-3.3.7.jar:/Users/jendrik/.m2/repository/org/processing/java-mode/3.3.7/java-mode-3.3.7.jar:/Users/jendrik/.m2/repository/org/processing/net/3.3.7/net-3.3.7.jar:/Users/jendrik/.m2/repository/org/processing/pdf/3.3.7/pdf-3.3.7.jar:/Users/jendrik/.m2/repository/com/itextpdf/itextpdf/5.5.6/itextpdf-5.5.6.jar:/Users/jendrik/.m2/repository/org/processing/serial/3.3.7/serial-3.3.7.jar:/Users/jendrik/Documents/Processing/libraries/themidibus/library/themidibus.jar:/Applications/Processing.app/Contents/Java/core/library/core.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-linux-i586.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-linux-amd64.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-linux-i586.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-windows-i586.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-linux-amd64.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-linux-aarch64.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-linux-armv6hf.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-windows-amd64.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-windows-i586.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-linux-aarch64.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-linux-armv6hf.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-windows-amd64.jar:/Applications/Processing.app/Contents/Java/core/library/jogl-all-natives-macosx-universal.jar:/Applications/Processing.app/Contents/Java/core/library/gluegen-rt-natives-macosx-universal.jar:/Users/jendrik/Documents/Processing/libraries/blobDetection/library/blobDetection.jar BlobDetaktion_Test_v3ga
java.lang.NullPointerException
	at BlobDetaktion_Test_v3ga.setup(BlobDetaktion_Test_v3ga.java:39)
	at processing.core.PApplet.handleDraw(PApplet.java:2401)
	at processing.awt.PSurfaceAWT$12.callDraw(PSurfaceAWT.java:1557)
	at processing.core.PSurfaceNone$AnimationThread.run(PSurfaceNone.java:316)

 */

public class BlobDetaktion_Test_v3ga extends PApplet{

    public static void main(String[] args) {
        PApplet.main("BlobDetaktion_Test_v3ga");
    }

    BlobDetection theBlobs;
    PImage img;

    public void settings(){
        size(1280, 720);
        //pixelDensity(displayDensity());


    }

    public void setup(){
        theBlobs = new BlobDetection(width,height);
        theBlobs.setThreshold(0.30f);
        theBlobs.computeBlobs(img.pixels);

        img = loadImage("Pix/Blob_test.png");
    }

    public void draw(){
        image(img,0,0);
        drawBlobsAndEdges(true,true);

    }

    // ab hir ist erst mal nur rein kopiert.
    public void drawBlobsAndEdges(boolean drawBlobs, boolean drawEdges) {
        noFill();
        Blob b;
        EdgeVertex eA, eB;
        for (int n=0 ; n<theBlobs.getBlobNb() ; n++)
        {
            b=theBlobs.getBlob(n);
            if (b!=null)
            {
                // Edges
                if (drawEdges)
                {
                    strokeWeight(2);
                    stroke(0, 255, 0);
                    for (int m=0;m<b.getEdgeNb();m++)
                    {
                        eA = b.getEdgeVertexA(m);
                        eB = b.getEdgeVertexB(m);
                        if (eA !=null && eB !=null)
                            line(
                                    eA.x*width, eA.y*height,
                                    eB.x*width, eB.y*height
                            );
                    }
                }

                // Blobs
                if (drawBlobs)
                {
                    strokeWeight(1);
                    stroke(255, 0, 0);
                    rect(
                            b.xMin*width, b.yMin*height,
                            b.w*width, b.h*height
                    );
                }
            }
        }
    }

}