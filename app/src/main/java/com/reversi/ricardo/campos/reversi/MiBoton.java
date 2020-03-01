package com.reversi.ricardo.campos.reversi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Button;

public class MiBoton extends android.support.v7.widget.AppCompatButton {

    public MiBoton(Context context) {
        super(context);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(0,50,50,50);
    }

    Paint paint;

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(this.getWidth()/2,this.getHeight()/2,this.getWidth()/3,paint);
    }
}
