package com.funnums.funnums.uihelpers;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Paint;

public class UIButton
{
    private Rect buttonRect;
    private boolean buttonDown = false;
    private Bitmap buttonImage, buttonDownImage;

    public UIButton(int left, int top, int right, int bottom,
                    Bitmap buttonImage, Bitmap buttonPressedImage)
    {


        buttonRect = new Rect(left, top, right, bottom);
        this.buttonImage = buttonImage;
        this.buttonDownImage = buttonPressedImage;
    }



    public void setRect(int left, int top)
    {
        buttonRect = new Rect(left, top, left + buttonImage.getWidth(),top + buttonImage.getHeight());
    }

    public void render(Canvas g, Paint p)
    {
        Bitmap currentButtonImage = buttonDown ? buttonDownImage : buttonImage;
        g.drawBitmap(currentButtonImage, buttonRect.left, buttonRect.top, p);
    }

    //might use this to scale buttons rather than always draw them as the size of the .png file
    /*public void drawImage(Bitmap bitmap, int x, int y, int width, int height) {
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dstRect.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }*/

    //repsond when user touches button
    public void onTouchDown(int touchX, int touchY) {
        if (buttonRect.contains(touchX, touchY)) {
            buttonDown = true;
        } else {
            buttonDown = false;
        }
    }

    //user lifted finger from button
    public void cancel() {
        buttonDown = false;
    }

    //true when button is pressed
    public boolean isPressed(int touchX, int touchY) {
        return buttonDown && buttonRect.contains(touchX, touchY);
    }
}