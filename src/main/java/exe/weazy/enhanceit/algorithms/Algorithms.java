package exe.weazy.enhanceit.algorithms;

import exe.weazy.enhanceit.Color;
import marvin.image.MarvinImage;
import org.marvinproject.image.color.brightnessAndContrast.BrightnessAndContrast;
import org.marvinproject.image.restoration.noiseReduction.NoiseReduction;

public class Algorithms {
    private Algorithms() {

    }

    public static MarvinImage blackAndWhite(MarvinImage image) {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getIntComponent0(y, x), image.getIntComponent1(y, x), image.getIntComponent2(y, x));

                int mid = (color.getR() + color.getG() + color.getB()) / 3;
                image.setIntColor(y, x, image.getAlphaComponent(y, x), mid, mid, mid);
            }
        }

        return image;
    }

    public static MarvinImage colorFilter(MarvinImage image, int deltaR, int deltaG, int deltaB) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getIntComponent0(y, x), image.getIntComponent1(y, x), image.getIntComponent2(y, x));

                color.editColor(deltaR, deltaG, deltaB);

                image.setIntColor(y, x, image.getAlphaComponent(y, x), color.getR(), color.getG(), color.getB());
            }
        }

        return image;
    }

    public static MarvinImage brightnessAndContrast(MarvinImage image, int deltaBrightness, int deltaContrast) {
        BrightnessAndContrast bac = new BrightnessAndContrast();
        MarvinImage resultImage = new MarvinImage(image.getBufferedImageNoAlpha());

        bac.load();
        bac.setAttribute("brightness", deltaBrightness);
        bac.setAttribute("contrast", deltaContrast);

        bac.process(image, resultImage);

        return resultImage;
    }

    public static MarvinImage removeNoise(MarvinImage image) {
        NoiseReduction noiseReduction = new NoiseReduction();

        noiseReduction.load();
        noiseReduction.process(image, image);

        return image;
    }

    public static MarvinImage auto(MarvinImage image) {
        int deltaBrightness, deltaContrast;

        if (isDarkImage(image)) {
            deltaBrightness = 10;
            deltaContrast = 10;
        }
        else {
            deltaBrightness = -10;
            deltaContrast = -10;
        }

        image = Algorithms.brightnessAndContrast(image, deltaBrightness, deltaContrast);

        int deltaR = -4;
        int deltaG = 2;
        int deltaB = 3;

        image = Algorithms.colorFilter(new MarvinImage(image.getBufferedImageNoAlpha()), deltaR, deltaG, deltaB);

        return image;
    }

    private static boolean isDarkImage(MarvinImage image) {
        int dark = 0, light = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getIntComponent0(y, x), image.getIntComponent1(y, x), image.getIntComponent2(y, x));
                int mid = (color.getR() + color.getG() + color.getB()) / 3;

                if (mid >= 128) light++;
                else dark++;
            }
        }

        if (dark >= light) return true;
        else return false;
    }
}
