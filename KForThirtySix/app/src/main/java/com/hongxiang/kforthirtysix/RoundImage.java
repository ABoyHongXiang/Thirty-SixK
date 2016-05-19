package com.hongxiang.kforthirtysix;

/**
 * Created by dllo on 16/5/17.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public class RoundImage extends ImageView{
    public RoundImage(Context context) {
        super(context);
    }

    public RoundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //绘制方法
    @Override
    protected void onDraw(Canvas canvas) {


        //需要显示的是圆形图片
        Drawable drawable = getDrawable();
        if (drawable != null) {
            //ImageView设置了图片属性
            //从它里面拿到设置的那张图片
            Bitmap bitmap = ((BitmapDrawable) drawable)
                    .getBitmap();
            //将它处理成圆形图片
            Bitmap out = getCircleBitmap(bitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, out.getWidth(),
                    out.getHeight());
            canvas.drawBitmap(out, rect, rect, paint);
        } else {
            super.onDraw(canvas);
        }

    }

    //生成一张圆形的Bitmap
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        int min = bitmap.getWidth() < bitmap.getHeight() ?
                bitmap.getHeight() : bitmap.getHeight();
        //先生成一张空的和目标Bitmap宽高相同的Bitmap
        Bitmap outBitmap =
                Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(),
                        Bitmap.Config.ARGB_8888);
        //新建一个画布,把目标Bitmap放进去,这样绘制的效果
        //就在outBitmap上了
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //绘制底层的圆
        canvas.drawCircle(min / 2
                , min / 2
                , bitmap.getWidth() / 2
                , paint);
        //设置画笔的叠放风格,为SRC_IN(显示前景的交集部分)
        paint.setXfermode(new PorterDuffXfermode(
                PorterDuff.Mode.SRC_IN));
        //再在其上绘制一张目标Bitmap
        Rect rect = new Rect(0, 0,
                bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;//输出画出的形状

    }
}