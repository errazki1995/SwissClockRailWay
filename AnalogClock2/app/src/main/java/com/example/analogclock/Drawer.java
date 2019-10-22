package com.example.analogclock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;

public class Drawer {

    //do not forget to set the Margin
Paint paint;
ImageView imageObject;
static int margin= 7;


    public Drawer(){
        paint = new Paint();

    }

    public int getMargin(){
        return  margin;
    }
    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void drawOval(Canvas canvas, ImageView imageObject){
        canvas.drawOval(imageObject.getX(),imageObject.getY(),imageObject.getWidth()-margin,imageObject.getHeight()-margin,getPaint());
   }

   public void drawLine(Canvas canvas, float theStartPointX,float theStartPointY,float stopPointX,float stopPointY){
       canvas.drawLine(theStartPointX,theStartPointY,stopPointX,stopPointY,getPaint());
   }



}
