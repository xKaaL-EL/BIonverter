import java.awt.*;
import java.awt.image.PixelGrabber;
import java.io.PrintWriter;

//Class containing methods and fields that converts digital image to binary(1,0) form.
public class Converter {
    private void biOnvert(String infile) {
        infile = infile.toLowerCase();
        Image image = Toolkit.getDefaultToolkit().getImage(infile);

        try {

            String outfile = infile.replace(".jpg", ".txt");
            PrintWriter out = new PrintWriter(outfile);
            PixelGrabber grabber = new PixelGrabber(image, 0, 0, -1, -1, false);

            if (grabber.grabPixels()) {
                //Assigning the height and the width of the digital image.
                int width = grabber.getWidth();
                int heigth = grabber.getHeight();

                int[] binaryMatrix = (int[]) grabber.getPixels();
                int loopstatus = 1;
                int output;

                int threshold = 12500000;
                //Digital Image Details.
                out.println("Input file   : " + infile);
                out.println("Output file  : " + outfile);
                out.println("Image width    : " + width);
                out.println("Image height   : " + heigth);
                out.println();
                //For-loop for writing binaries in Text File.
                for (int i = 0; i < width * heigth; i++) {
                    // white
                    if (binaryMatrix[i] == 16777215) {
                        output = 1;
                    }
                    // black
                    else if (binaryMatrix[i] == 0) {
                        output = 0;
                    }
                    // value that are not white/black.
                    else if (binaryMatrix[i] < threshold) {
                        output = 0;
                    } else {
                        output = 1;
                    }

                    out.print(output);

                    if (width == (i + 1) / loopstatus) {
                        out.println();
                        loopstatus++;
                    }
                }
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Driver method to run the converter.
    public void driverXD(String pathway) {
        biOnvert(pathway);
    }
}
