package com.example.dvid.sudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


/**
 * Created by Dvid on 26/03/2017.
 */
//Class này xử lý đồ họa trong game
public class PuzzleView extends View {
    private final Game game;
    //mún hỉu rõ Context sreach google-sama
    public PuzzleView(Context context) {
        super(context);
        this.game = (Game) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }
    //Chiều Rộng + Cao của 1 tile
    private float width;
    private float height;
    //tương tụ với ô con trỏ
    private int selX;
    private int selY;
    private final Rect selRect = new Rect();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 9f;
        height = h / 9f;
        getRect(selX, selY, selRect);
        super.onSizeChanged(w, h, oldw, oldh);
    }
    private void getRect(int x, int y, Rect rect) {
        rect.set((int) (x * width), (int) (y * height),
                (int) (x * width + width), (int) (y * height + height));
    }
    //Vẽ bàn cho game
    //Đầu tiên tô background trước
    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        //Xác định màu cho mấy đường kẻ
        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_dark));
        Paint hilite = new Paint();
        hilite.setColor(getResources().getColor(R.color.puzzle_hilite));
        Paint light = new Paint();
        light.setColor(getResources().getColor(R.color.puzzle_light));
        //Kẻ bảng (kẻ mấy đường PHỤ)
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * height, getWidth(), i * height, light);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
            canvas.drawLine(i * width, 0, i * width, getHeight(), light);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
        }
        //Kẻ bảng (kẻ mấy đường CHÍNH)
        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0) {
                continue;
            }
            canvas.drawLine(0, i * height, getWidth(), i * height, dark);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
            canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
        }

        //Tô màu cho Numbers đưa vào
        Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(getResources().getColor(R.color.forceground));
        foreground.setStyle(Paint.Style.FILL);
        foreground.setTextSize(height * 0.75f);
        foreground.setTextScaleX(width / height);
        foreground.setTextAlign(Paint.Align.CENTER);
        //Draw the number in the center of the tiles--> cho số vào chính giữa mỗi ô
        Paint.FontMetrics fm = foreground.getFontMetrics();
        //Centering in X:use alignment (and X at midpoint)
        float x = width / 2;
        //Centering in Y:meansure ascent/descent 1st
        float y = height / 2 - (fm.ascent + fm.descent) / 2;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawText(this.game.getTileString(i, j),
                        i * width + x, j * height + y, foreground);
            }
        }
        //Draw the selection-->Màu cho ô người chơi trỏ tới
        Paint selected = new Paint();
        selected.setColor(getResources().getColor(R.color.selected));
        canvas.drawRect(selRect, selected);
    }
    //Người dùng di chuyển ô trỏ tới
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP :
                select(selX, selY - 1);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN :
                select(selX, selY + 1);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT :
                select(selX-1, selY );
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT :
                select(selX+1, selY);
                break;
            default:
                return super.onKeyDown(keyCode,event);

            case KeyEvent.KEYCODE_0:
            case KeyEvent.KEYCODE_SPACE:
                setSelectedTile(0);
                break;
            case KeyEvent.KEYCODE_1:
                setSelectedTile(1);
                break;
            case KeyEvent.KEYCODE_2:
                setSelectedTile(2);
                break;
            case KeyEvent.KEYCODE_3:
                setSelectedTile(3);
                break;
            case KeyEvent.KEYCODE_4:
                setSelectedTile(4);
                break;
            case KeyEvent.KEYCODE_5:
                setSelectedTile(5);
                break;
            case KeyEvent.KEYCODE_6:
                setSelectedTile(6);
                break;
            case KeyEvent.KEYCODE_7:
                setSelectedTile(7);
                break;
            case KeyEvent.KEYCODE_8:
                setSelectedTile(8);
                break;
            case KeyEvent.KEYCODE_9:
                setSelectedTile(9);
                break;
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:
               game.showKeypadOrError(selX,selY);
                break;
        }
        return true;
    }
    protected void setSelectedTile(int title) {
        if(game.setTileIfVaild(selX,selY,title)){
            invalidate();
        }
        else {
            startAnimation(AnimationUtils.loadAnimation(game,R.anim.a));
            //Toast toast = Toast.makeText(this, R.string.kiem_tra_lai, Toast.LENGTH_SHORT);
          //  toast.setGravity(Gravity.CENTER, 0, 0);
            //toast.show();
        }
    }

    public void select (int x,int y){
        invalidate(selRect);
        selX = Math.min(Math.max(x,0),8);
        selY = Math.min(Math.max(y,0),8);
        getRect(selX,selY,selRect);
        invalidate(selRect);
    }
    //Touch vào hiện keypad
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()!= MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }
        select ((int) (event.getX()/width),
                (int) (event.getY()/height));
        game.showKeypadOrError(selX,selY);
        return true;
    }
}
