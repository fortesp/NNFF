import nn.Network;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/*
    NNFF v0.1
    Author: Pedro Fortes (c) 2018
    https://github.com/fortesp

 */

public class Main {

    public static float[][] readImagePixels(String pathname) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathname));
        } catch (Exception e) {
            System.out.println("Error reading image.");
            System.exit(0);
        }

        int w = img.getWidth();
        int h = img.getHeight();

        float[][] mat = new float[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                int pixel = img.getRGB(j, i);

                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                int rgb = (red + green + blue)/3;

                mat[i][j] = rgb;
            }

        }
        return mat;
    }


    public static float[][] readImagePixelsNormalized(String pathname) {

        //return Network.normalizeInputData(readImagePixels(pathname));
        return readImagePixels(pathname);
    }


    public static void main(String[] args) {

        System.out.print("Reading images...");

        float[][] inputValues1 = Main.readImagePixelsNormalized("img/test1.jpg");
        float[][] inputValues2 = Main.readImagePixelsNormalized("img/test2.jpg");
        float[][] inputValues3 = Main.readImagePixelsNormalized("img/test3.jpg");
        float[][] inputValues3_2 = Main.readImagePixelsNormalized("img/test3_2.jpg");
        float[][] inputValues4 = Main.readImagePixelsNormalized("img/test4.jpg");
        float[][] inputValues5 = Main.readImagePixelsNormalized("img/test5.jpg");

        System.out.println(" Done.");

        int totalInputCells  = inputValues1.length * inputValues1[0].length;
        int totalHiddenCells1 = (int)(totalInputCells * 0.6); //60% of the total
        int totalHiddenCells2 = (int)(totalInputCells * 0.30); //30% of the total
        int totalOutputCells = 5;

        int epochs = 5;
        int totalBatches = 5;
        int iterations = 250;

        float[] target1 = new float[]{1, 0, 0, 0, 0};
        float[] target2 = new float[]{0, 1, 0, 0, 0};
        float[] target3 = new float[]{0, 0, 1, 0, 0};
        float[] target4 = new float[]{0, 0, 0, 1, 0};
        float[] target5 = new float[]{0, 0, 0, 0, 1};


        Network network = new Network(inputValues1, totalHiddenCells1, totalHiddenCells2, totalOutputCells);


        System.out.println("Neural Network initialized.");
        System.out.println("Total INPUT cells: "  + totalInputCells);
        System.out.println("Total HIDDEN cells #1: " + totalHiddenCells1);
        System.out.println("Total HIDDEN cells #2: " + totalHiddenCells2);
        System.out.println("Total OUTPUT cells: " + totalOutputCells);
        System.out.println("\nEpochs: " + epochs);
        System.out.println("Batches: " + totalBatches);
        System.out.println("Iterations: " + iterations);


        for(int e = 0; e < epochs; e++) {
            System.out.println("\n\nEpoch #" + (e + 1));
            System.out.print("Learning...");
            for (int k = 0; k < iterations; k++) {
                network.learn(inputValues1, target1);
                network.learn(inputValues2, target2);
                network.learn(inputValues3, target3);
                network.learn(inputValues4, target4);
                network.learn(inputValues5, target5);
            }
            System.out.println(" Done.");

            System.out.println("Firing...\n");

            Main.testFire(network, inputValues1, target1);
            Main.testFire(network, inputValues2, target2);
            Main.testFire(network, inputValues3, target3);
            Main.testFire(network, inputValues3_2, target3);
            Main.testFire(network, inputValues4, target4);
            Main.testFire(network, inputValues5, target5);
        }

    }


    static void testFire(Network network, float[][] inputValues, float[] targets) {

        // https://www.youtube.com/watch?v=83Rf5o5IGqg
        float[] outputs = network.feedForward(inputValues);

        System.out.print("Target: ");

        int i;
        for( i = 0; i < targets.length; i++) {
          if(targets[i] == 1) { System.out.println((i+1)); break; }
        }

        float max_output = -1;
        int m_index = 0;
        System.out.println("Output: ");
        for(int ii = 0; ii < outputs.length; ii++) {
            if(outputs[ii]  > max_output) {
                max_output = outputs[ii];
                m_index = ii;
            }
            System.out.println(" (" + outputs[ii] + ") " + Math.round(outputs[ii] * 100) + "% likely to be a " + (ii+1) + "");
        }
        if(i == m_index) System.out.println("PASSED."); else System.out.println("FAILED.");
        System.out.println();

    }

}
