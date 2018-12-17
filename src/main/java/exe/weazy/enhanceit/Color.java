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
        this.r += deltaR;
        if (this.r > 255) this.r = 255;
        if (this.r < 0) this.r = 0;

        this.g += deltaG;
        if (this.g > 255) this.g = 255;
        if (this.g < 0) this.g = 0;

        this.b += deltaB;
        if (this.b > 255) this.b = 255;
        if (this.b < 0) this.b = 0;
    }
}
