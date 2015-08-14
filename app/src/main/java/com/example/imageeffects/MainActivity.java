package com.example.imageeffects;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    ImageView imageView;
    ImageView imageView2;
    Drawable iverson;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView_show);
        imageView2 = (ImageView) findViewById(R.id.imageView_frame);
        iverson = getResources().getDrawable(R.drawable.ai);
        bitmap = ((BitmapDrawable) iverson).getBitmap();
        Bitmap newPhoto = invertImage(bitmap);
        imageView.setImageBitmap(newPhoto);

        Drawable[] layers = new Drawable[2];
        layers[0] = getResources().getDrawable(R.drawable.ai);
        layers[1] = getResources().getDrawable(R.drawable.frame);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        imageView2.setImageDrawable(layerDrawable);

        MediaStore.Images.Media.insertImage(getContentResolver(),newPhoto,"iverson","inverted");

    }

    public static Bitmap invertImage(Bitmap original) {

        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(),original.getHeight(),original.getConfig());

        int A,R,G,B;
        int pixelColor;
        int width = original.getWidth();
        int height = original.getHeight();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixelColor = original.getPixel(x,y);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                finalImage.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return finalImage;
    }

}
