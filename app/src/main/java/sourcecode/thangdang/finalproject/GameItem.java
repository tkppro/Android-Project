package sourcecode.thangdang.finalproject;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Admin on 15-Apr-18.
 */

public class GameItem {
    private Button mButton;
    private boolean isTouching;
    private String text;
    Dialog dialog;

    public GameItem(Button mButton, String text) {
        this.mButton = mButton;
        this.isTouching = false;
        this.text = text;
    }

    public Button getmButton() {
        return mButton;
    }

    public void setmButton(Button mButton) {
        this.mButton = mButton;
    }

    public void isTouching(Context context) {
        dialog = new Dialog(context);
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTouching(boolean touching) {
        isTouching = touching;
    }
}
