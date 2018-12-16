package exe.weazy.enhanceit;

public class Color {
    private int r, g, b;

    public Color() {

    }

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void editColor(int deltaR, int deltaG, int deltaB) {
        r += deltaR;
        if (r > 255) r = 255;
        if (r < 0) r = 0;

        g += deltaG;
        if (g > 255) g = 255;
        if (g < 0) g = 0;

        b += deltaB;
        if (b > 255) b = 255;
        if (b < 0) b = 0;
    }
}
