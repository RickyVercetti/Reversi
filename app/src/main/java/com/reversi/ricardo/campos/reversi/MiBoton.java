package com.reversi.ricardo.campos.reversi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Button;

/**
 * Created by Ricardo on 17/2/17.
 */

public class MiBoton extends Button {

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
