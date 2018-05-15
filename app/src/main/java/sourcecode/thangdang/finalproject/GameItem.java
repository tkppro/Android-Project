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
    private String text;
    Dialog dialog;
    private SoundManager sound;
    private int idSound;
    Context context;
    public GameItem(Context context, Button mButton, String text, int idSound) {
        this.mButton = mButton;
        this.text = text;
        sound = SoundManager.getInstance();
        sound.init(context);
        this.idSound = idSound;
        this.context = context;
    }

    public Button getmButton() {
        return mButton;
    }

    public void setmButton(Button mButton) {
        this.mButton = mButton;
    }

    public void isTouching() {

        sound.playSound(idSound);
//        dialog = new Dialog(context);
//       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle(text);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
