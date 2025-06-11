package com.example.finalproject_basicjaplearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
    //Tạo ra 1 custom View cho phép người dùng vẽ tay lên màn hình
    private Paint paint; //dùng để thiết lập màu vẽ, độ dày nét, kiểu vẽ
    private Path path;  // đường vẽ hiện tại
    private Bitmap bitmap; //lưu hình ảnh tổng thể
    private Canvas bitmapCanvas;    //dùng bitmapCanvas.drawPath() để ghi vào bitmap

    //Hàm khởi tạo kế thừa từ view và gọi hàm init
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);   //làm mượt nét vẽ
        paint.setStrokeWidth(20f); // Tăng độ dày nét vẽ
        paint.setColor(Color.BLACK);    //màu đen
        paint.setStyle(Paint.Style.STROKE); //vẽ nét
        paint.setStrokeJoin(Paint.Join.ROUND);  //bo tròn khi vẽ
        paint.setStrokeCap(Paint.Cap.ROUND);    //đầu nét tròn

        path = new Path();  //đường vẽ hiện tại
    }

    //Lưu các nét vẽ vào 1 bitmap
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawColor(Color.WHITE); // Nền trắng
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawPath(path, paint);   //hiển thị nét vẽ tạm thời trên bitmap
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);  //bắt đầu nét vẽ tại điểm chạm
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y); // Kéo dài đường vẽ tới điểm mới
                break;
            case MotionEvent.ACTION_UP:
                bitmapCanvas.drawPath(path, paint); // Vẽ xong thì lưu vào bitmap
                path.reset();   //xóa đường vẽ tạm
                break;
            default:
                return false;
        }
        invalidate(); // Yêu cầu gọi lại onDraw để cập nhật vẽ
        return true;
    }

    //lấy ra hình ảnh đã vẽ
    public Bitmap getBitmap() {
        return bitmap;
    }

    // Xóa canvas
    public void clear() {
        bitmapCanvas.drawColor(Color.WHITE);
        invalidate();
    }
}

