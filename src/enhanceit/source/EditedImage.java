package enhanceit.source;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

import java.awt.image.BufferedImage;

public class EditedImage {
    private static MarvinImage marvinImage;

    private EditedImage() {

    }

    public static synchronized MarvinImage getMarvinImage() {
        if (marvinImage == null) {
            marvinImage = new MarvinImage();
        }
        return marvinImage;
    }

    public static synchronized BufferedImage getBufferedImage() {
        if (marvinImage == null) {
            marvinImage = new MarvinImage();
        }
        return marvinImage.getBufferedImageNoAlpha();
    }

    public static synchronized void initializeImage(String path) {
        if (marvinImage == null) {
            marvinImage = MarvinImageIO.loadImage(path);
        }
    }

    public static synchronized void setImage(MarvinImage mImage) {
        MarvinImage.copyColorArray(mImage, marvinImage);
    }
}
