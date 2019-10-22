package com.example.analogclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MyClockView extends View {
    private Context context;
      private Paint paint;
    private Drawer draw;
private State state;
private ImageView img;

static class State{
        private long startTime;
        private long cummulatedTime;
        private long elapsedTime;
        //time units
        public long seconds;
        public long minutes;
        public long hours;

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getCummulatedTime() {
            return cummulatedTime;
        }

        public void setCummulatedTime(long cummulatedTime) {
            this.cummulatedTime = cummulatedTime;
        }

        public long getElapsedTime(){
            long time=startTime>0?(System.nanoTime()-startTime):0;
            elapsedTime = time+cummulatedTime;
            return elapsedTime;
        }

        public void setElapsedTime(long elapsedTime) {
            this.elapsedTime = elapsedTime;
        }

        public long getHours() {
            return hours;
        }

        public void setHours(long hours) {
            this.hours = hours;
        }

        public long getMinutes() {
            return minutes;
        }

        public void setMinutes(long minutes) {
            this.minutes = minutes;
        }

        public long getSeconds() {
            return seconds;
        }

        public void setSeconds(long seconds) {
            this.seconds = seconds;
        }
    }


    public MyClockView(Context ctx,ImageView img) {
        super(ctx);
        this.context = ctx;
        state = new State();
        paint = new Paint();
        draw = new Drawer();
        setImg(img);
        draw.setPaint(paint);

        //imageObject = (ImageView) findViewById(R.id.clockbg);
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw oval
        draw.getPaint().setColor(Color.WHITE);
        draw.drawOval(canvas,getImg());
        //draw lines
        //double angle;
        //int posXline,posYline;
       //  angle = Math.toRadians((15-state.getSeconds())*6);
         //posXline = (int) (Math.cos(angle)*imageObject.getWidth()-draw.getMargin());
      //   posYline = (int) Math.sin(angle)* imageObject.getHeight()-draw.getMargin();
        //draw line start point is (O,O) according to image scale
        draw.getPaint().setColor(Color.RED);
        draw.drawLine(canvas,getImg().getWidth()/2,getImg().getHeight()/2,getImg().getWidth()/2,getImg().getHeight());
        canvas.save();
        postInvalidateDelayed(500);
        invalidate();
    }


    public void ticking(Canvas canvas,ImageView imageObject){
        double alpha = (state.getSeconds()/60) * 2*Math.PI;
        //changer seconds en angle
        double Teta =((Math.PI)/2)-alpha;
        long R = imageObject.getWidth()/2;//radius  rayon
        double x= R+R*Math.cos(Teta);
        double y = (imageObject.getHeight()/2) -R*Math.sin(Teta);
    canvas.drawLine((float)imageObject.getWidth()-R,(float) imageObject.getHeight()-R,(float) x,(float)y,draw.getPaint());



}




}


