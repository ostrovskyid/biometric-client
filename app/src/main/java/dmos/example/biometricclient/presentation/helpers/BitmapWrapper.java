package dmos.example.biometricclient.presentation.helpers;

import android.graphics.Bitmap;

import dmytro.o.github.ImageWrapper;

public class BitmapWrapper implements ImageWrapper {
    private final Bitmap bitmap;

    public BitmapWrapper(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        return bitmap.getPixel(x, y);
    }

    @Override
    public int[] getPixelData() {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        return pixels;
    }
}
