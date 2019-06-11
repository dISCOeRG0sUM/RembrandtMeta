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