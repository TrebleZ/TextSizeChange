package fontsliderbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
public class Bar {

    private Paint mBarPaint;
    private Paint mTextPaint;

    private final float mLeftX;
    private final float mRightX;
    private final float mY;
    private final float mPadding;

    private int mSegments;
    private float mTickDistance;
    private final float mTickHeight;
    private final float mTickStartY;
    private final float mTickEndY;
    private  int mtextSize;

    public Bar(float x, float y, float width, int tickCount, float tickHeight,
               float barWidth, int barColor,int textColor, int textSize, int padding) {

        mLeftX = x;
        mRightX = x + width;
        mY = y;
        mPadding = padding;
        mtextSize=textSize;

        mSegments = tickCount - 1;
        mTickDistance = width / mSegments;
        mTickHeight = tickHeight;
        mTickStartY = mY - mTickHeight / 2f;
        mTickEndY = mY + mTickHeight / 2f;

        mBarPaint = new Paint();
        mBarPaint.setColor(barColor);
        mBarPaint.setStrokeWidth(barWidth);
        mBarPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        drawLine(canvas);
        drawTicks(canvas);
    }

    public float getLeftX() {
        return mLeftX;
    }

    public float getRightX() {
        return mRightX;
    }
    //获取标尺最近的刻度 通过圆所在的位置
    public float getNearestTickCoordinate(Thumb thumb) {
        final int nearestTickIndex = getNearestTickIndex(thumb);
        final float nearestTickCoordinate = mLeftX + (nearestTickIndex * mTickDistance);
        return nearestTickCoordinate;
    }

    //获取标尺最近的刻度 通过下标
    public float getNearestTickCoordinate(int index) {
        final int nearestTickIndex = index;
        final float nearestTickCoordinate = mLeftX + (nearestTickIndex * mTickDistance);
        return nearestTickCoordinate;
    }


    public int getNearestTickIndex(Thumb thumb) {
        return getNearestTickIndex(thumb.getX());
    }

    public int getNearestTickIndex(float x) {
        return (int) ((x - mLeftX + mTickDistance / 2f) / mTickDistance);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(mLeftX, mY, mRightX, mY, mBarPaint);
    }

    private void drawTicks(Canvas canvas) {
        for (int i = 0; i <= mSegments; i++) {
            final float x = i * mTickDistance + mLeftX;
            canvas.drawLine(x, mTickStartY, x, mTickEndY, mBarPaint);
            //绘制头尾 A 以及标准
            String text="";
           if(i==0){
               text="A";
               mTextPaint.setTextSize(mtextSize*0.9f);
           }
            if(i==1){
                text="标准";
                mTextPaint.setTextSize(mtextSize);
            }
            if(i==mSegments){
                 text="A";
                 mTextPaint.setTextSize(mtextSize*1.4f);
            }
            if(!TextUtils.isEmpty(text)) {
                canvas.drawText(text, x - getTextWidth(text) / 2, mTickStartY - mPadding, mTextPaint);
            }
        }
    }

    float getTextWidth(String text) {
        return mTextPaint.measureText(text);
    }

    public void destroyResources() {
        if(null != mBarPaint) {
            mBarPaint = null;
        }
        if(null != mTextPaint) {
            mTextPaint = null;
        }
    }
}