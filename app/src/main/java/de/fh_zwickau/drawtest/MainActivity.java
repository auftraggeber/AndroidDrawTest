package de.fh_zwickau.drawtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final int STROKE_WIDTH = 10;
    final int PENCIL_COLOR = Color.rgb(255,0,0);

    private int getWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int getHeight(final int forWidth, final Drawable forDrawable) {
        final float ratio = (float) forDrawable.getIntrinsicHeight() / forDrawable.getIntrinsicWidth();

        System.out.println("Drawable ratio: " + ratio);

        return (int) (forWidth * ratio);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView view = findViewById(R.id.imageView2);
        final Drawable drawable = getResources().getDrawable(R.drawable._2_png);

        final int width = getWidth();
        final int height = getHeight(width, drawable);

        System.out.println("Screen width: " + width);
        System.out.println("Screen height: " + height);
        System.out.println("Resource width: " + drawable.getIntrinsicWidth());
        System.out.println("Resource height: " + drawable.getIntrinsicHeight());

        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);

        setDrawable(canvas, R.drawable._2_png);
        drawDemoArrow(canvas);

        view.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
    }

    /**
     * Zeichnet das Bild in den Hintergrund.
     * @param forCanvas Die Zeichenfläche, auf der gezeichnet wird.
     * @param drawable Die ID des Bildes.
     */
    private void setDrawable(final Canvas forCanvas, final int drawable) {
        final Drawable d = getResources().getDrawable(drawable);

        final int width = getWidth();
        final int height = getHeight(width, d);

        final Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), drawable);
        final Bitmap sizedImageBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false); // Dieser Schritt ist wichtig, dass das Bild skaliert wird.
        forCanvas.drawBitmap(sizedImageBitmap, 0, 0, null);
    }

    /**
     * Zeichnet den Demo-Pfeil
     * @param on Die Zeichenläche.
     */
    private void drawDemoArrow(final Canvas on) {
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStrokeWidth(STROKE_WIDTH);

        paint.setColor(PENCIL_COLOR);

        on.drawLine(380, 315, 690, 315, paint);
        on.drawCircle(380,315,15, paint);

        final Path arrow = new Path();
        arrow.moveTo(700,315);
        arrow.lineTo(665, 300);
        arrow.lineTo(665, 330);

        on.drawPath(arrow, paint);

    }
}