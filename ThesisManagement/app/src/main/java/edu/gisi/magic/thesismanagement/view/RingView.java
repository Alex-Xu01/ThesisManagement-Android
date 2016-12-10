package edu.gisi.magic.thesismanagement.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import edu.gisi.magic.thesismanagement.R;

/**
 * Created by AlexXu on 2016/12/9.
 */

public class RingView extends TextView {

    /**
     * 环的颜色
     */
    public static final int RingColor = Color.parseColor("#f05a4b");//#00C2C4

    /**
     * 进度条的颜色
     */
    public static final int PecentColor = Color.parseColor("#1bca86");

    public RingView(Context context) {
        super(context);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 是否第一次
     */
    private boolean init = false;
    /**
     * 背景
     */
    private static final int BackGround = Color.parseColor("#FF0000");
    /**
     * 已经完成的颜色
     */
    private static final int CircleColor = Color.YELLOW;

    /**
     * 完成扇形角度
     */
    private static final float startAngle = 270;
    /**
     * 扇形中心点X轴
     */
    private float content_X;
    /**
     * 扇形中心点Y轴
     */
    private float content_Y;
    /**
     * 环形外半径
     */
    private float bigRadius;
    /**
     * 环形内半径
     */
    private float smallRadius;
    /**
     * 默认终点角度
     */
    private float SweepAngle = 270;
    /**
     * 控件宽
     */
    private int width;
    /**
     * 控件高
     */
    private int height;
    /**
     * 文件显示的文本
     */
    private String text;
    private static final int TEXTSIZE = 120;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!init) {
            initPaint();
        }
    }

    private void initPaint() {
        setPadding(0, 0, 0, 0);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(RingColor);      //ring的颜色
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        bigRadius = ((float) width / 2);
        smallRadius = (float) width / 3;
        content_X = (float) width / 2;
        content_Y = (float) height / 2;
        init = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(RingColor);      //ring的颜色
        Path path = new Path();
        path.reset();
        /*画圆*/
        path.addCircle(content_X, content_Y, bigRadius - 15, Path.Direction.CCW);
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        paint.setColor(Color.WHITE);
        path.addCircle(content_X, content_Y, smallRadius, Path.Direction.CCW);
        path.close();
        canvas.drawPath(path, paint);
        getSectorClip(canvas, startAngle);
        path.reset();
        paint.setColor(Color.WHITE);
        path.addCircle(content_X, content_Y, smallRadius - 5, Path.Direction.CCW);
        path.close();
        canvas.drawPath(path, paint);
        if (text != null) {
            paint.setColor(getResources().getColor(R.color.bg_header));
            paint.setFakeBoldText(true);
            paint.setTextSize(TEXTSIZE);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, width / 2, height * 7 / 13, paint);
        }
    }

    /**
     * 返回一个扇形的剪裁区
     *
     * @param canvas     画笔
     * @param startAngle 起始角度
     */
    private void getSectorClip(Canvas canvas, float startAngle) {
        paint.setColor(PecentColor);//进度的颜色
        Path path = new Path();
        // 下面是获得一个三角形的剪裁区
        path.moveTo(content_X, content_Y); // 圆心
        path.lineTo(
                (float) (content_X + bigRadius * Math.cos(startAngle * Math.PI / 180)), // 起始点角度在圆上对应的横坐标

                (float) (content_Y + bigRadius * Math.sin(startAngle * Math.PI / 180))); // 起始点角度在圆上对应的纵坐标
        path.lineTo(
                (float) (content_X + bigRadius * Math.cos(SweepAngle * Math.PI / 180)), // 终点角度在圆上对应的横坐标

                (float) (content_Y + bigRadius * Math.sin(SweepAngle * Math.PI / 180))); // 终点点角度在圆上对应的纵坐标
        path.close();
        // //设置一个正方形，内切圆
        RectF rectF = new RectF(content_X - bigRadius, content_Y - bigRadius, content_X + bigRadius,
                content_Y + bigRadius);
        // 下面是获得弧形剪裁区的方法
        path.addArc(rectF, startAngle, SweepAngle - startAngle);
        canvas.drawPath(path, paint);


    }

    /**
     * 返回一个扇形的剪裁区
     *
     * @param canvas     画笔
     * @param startAngle 起始角度
     */
    private void getSmallSectorClip(Canvas canvas, float startAngle) {
        paint.setColor(Color.WHITE);
        Path path = new Path();
        // 下面是获得一个三角形的剪裁区
        path.moveTo(content_X, content_Y); // 圆心
        path.lineTo(
                (float) (content_X + smallRadius * Math.cos(startAngle * Math.PI / 180)), // 起始点角度在圆上对应的横坐标

                (float) (content_Y + smallRadius * Math.sin(startAngle * Math.PI / 180))); // 起始点角度在圆上对应的纵坐标
        path.lineTo(
                (float) (content_X + smallRadius * Math.cos(SweepAngle * Math.PI / 180)), // 终点角度在圆上对应的横坐标

                (float) (content_Y + smallRadius * Math.sin(SweepAngle * Math.PI / 180))); // 终点点角度在圆上对应的纵坐标
        path.close();
        // //设置一个正方形，内切圆
        RectF rectF = new RectF(content_X - smallRadius, content_Y - smallRadius, content_X + smallRadius,
                content_Y + smallRadius);
        // 下面是获得弧形剪裁区的方法
        path.addArc(rectF, startAngle, SweepAngle - startAngle);
        canvas.drawPath(path, paint);


    }

    /**
     * @param startAngle 百分比
     */
    public void setAngle(float startAngle) {
        SweepAngle = (360 * startAngle / 100 + 270);
    }

    public void setText(String text) {
        this.text = text;
    }
}
