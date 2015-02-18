package com.bootstrap.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class PicassoUtils {

  public static class CircleTransformation implements Transformation {

    String key;

    public CircleTransformation() {
      this.key = "tr_" + this.hashCode();
    }

    @Override
    public Bitmap transform(Bitmap source) {
      int size = Math.min(source.getWidth(), source.getHeight());

      int valueX = (source.getWidth() - size) / 2;
      int valueY = (source.getHeight() - size) / 2;

      Bitmap squaredBitmap = Bitmap.createBitmap(source, valueX, valueY, size, size);
      if (squaredBitmap != source) {
        source.recycle();
      }

      Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

      Canvas canvas = new Canvas(bitmap);
      Paint paint = new Paint();
      BitmapShader shader = new BitmapShader(squaredBitmap,
        BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
      paint.setShader(shader);
      paint.setAntiAlias(true);

      float radius = size / 2f;
      canvas.drawCircle(radius, radius, radius, paint);

      squaredBitmap.recycle();
      return bitmap;
    }

    @Override
    public String key() {
      return key;
    }

  }
}
