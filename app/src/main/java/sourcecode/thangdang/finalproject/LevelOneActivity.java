package sourcecode.thangdang.finalproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class LevelOneActivity extends AppCompatActivity implements View.OnClickListener {
    private GameItem mGameItem1, mGameItem2, mGameItem3, mGameItem4;
    private Button mBtnItem1, mBtnItem2, mBtnItem3, mBtnItem4, mBtnTip, mBtnResume, mBtnQuit;
    private TextView mTvResult, mTvCountTime;
    private boolean isFinish = false;
    private boolean isPause = false;
    private long RemainingTime = 0;
    CountDownTimer cdt;
    MediaPlayer song;
    int percent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        mGameItem1 = new GameItem(this,mBtnItem1, this.getResources().getString(R.string.item_1_lv1), R.raw.tooth_brush);
        mGameItem2 = new GameItem(this,mBtnItem2, this.getResources().getString(R.string.item_2_lv1),R.raw.toilet);
        mGameItem3 = new GameItem(this,mBtnItem3, this.getResources().getString(R.string.item_3_lv1),R.raw.mirror);
        mGameItem4 = new GameItem(this,mBtnItem4, this.getResources().getString(R.string.item_4_lv1),R.raw.sink);

        song= MediaPlayer.create(this,R.raw.lv_1);
        song.setLooping(true);
        song.start();

        init();
        setOnClick();
        startTime(30000);

    }

    public void gameStart(){
        mBtnItem1.setClickable(true);
        mBtnItem2.setClickable(true);
        mBtnItem3.setClickable(true);
        mBtnItem4.setClickable(true);

        startTime(30000);
        percent = 0;
        isFinish = false;
        mTvResult.setText(String.valueOf(percent) + "%");
    }

    //call when lose the game
    public void gameStop(){
        mBtnItem1.setClickable(false);
        mBtnItem2.setClickable(false);
        mBtnItem3.setClickable(false);
        mBtnItem4.setClickable(false);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.lose_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        if(!isFinishing())
            dialog.show();

        song = MediaPlayer.create(this,R.raw.sound_lose);
        song.start();

        Button btnReplay = (Button)dialog.findViewById(R.id.btn_replay);
        Button btnBackToMenu = (Button)dialog.findViewById(R.id.btn_back_menu);

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStart();
                dialog.dismiss();
            }
        });

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTheGameFinish();
                dialog.dismiss();
            }
        });
        song.stop();

    }
    public void init(){
        mBtnItem1 = (Button)findViewById(R.id.btn_item1);
        mBtnItem2 = (Button)findViewById(R.id.btn_item2);
        mBtnItem3 = (Button)findViewById(R.id.btn_item3);
        mBtnItem4 = (Button)findViewById(R.id.btn_item4);
        mTvResult = (TextView)findViewById(R.id.tv_result);
        mTvCountTime = (TextView)findViewById(R.id.tv_timing);
        mBtnTip = (Button)findViewById(R.id.btn_tip);
    }
    public void setOnClick(){
        mBtnItem1.setOnClickListener(this);
        mBtnItem2.setOnClickListener(this);
        mBtnItem3.setOnClickListener(this);
        mBtnItem4.setOnClickListener(this);
        mBtnTip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(percent >= 75) {
            winDialog();
            isPause = true;
        }
        switch (view.getId()){
            case R.id.btn_item1:
                mGameItem1.isTouching();
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_item2:
                mGameItem2.isTouching();
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_item3:
                mGameItem3.isTouching();
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_item4:
                mGameItem4.isTouching();
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_tip:
                isPause = true;
                pauseDialog();
                stopTime();
                break;
        }

    }

    public void startTime(long millis){
        cdt = new CountDownTimer(millis, 1000) {

            public void onTick(long millisUntilFinished) {
                if(isPause)
                    cancel();
                else {
                    mTvCountTime.setText("Seconds remaining: " + millisUntilFinished / 1000);
                    RemainingTime = millisUntilFinished;
                }
            }

            public void onFinish() {
                mTvCountTime.setText("You lose! Click back to exit.");
                song.stop();
                if(!isFinish)
                    gameStop();

            }
        };
        cdt.start();
    }
    public void stopTime(){
        cdt.cancel();
    }
    public void pauseDialog(){

        final Dialog pauseDialog = new Dialog(this);
        pauseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pauseDialog.setContentView(R.layout.pause_dialog);
        pauseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pauseDialog.setCanceledOnTouchOutside(false);
        pauseDialog.show();

        mBtnResume = (Button)pauseDialog.findViewById(R.id.btn_resume);
        mBtnQuit = (Button)pauseDialog.findViewById(R.id.btn_quit);

        mBtnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPause = false;
                pauseDialog.dismiss();
                startTime(RemainingTime);
            }
        });

        mBtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTheGameFinish();
                pauseDialog.dismiss();
            }
        });
    }

    public void winDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        if (!isFinishing()) {
            dialog.show();
            isFinish = true;
        }
        Button btnReplay = (Button) dialog.findViewById(R.id.btn_replay);
        Button btnNextLevel = (Button) dialog.findViewById(R.id.btn_next);
        //need to change to back to menu button insteads of replay button
//        btnReplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //stopTime();
//                gameStart();
//                dialog.dismiss();
//
//            }
//        });

        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextLevel();
                dialog.dismiss();
            }
        });

    }

    private void nextLevel(){
        Intent intent = new Intent(this, LevelTwoActivity.class);
        this.startActivity(intent);
        finish();
    }

    public void isTheGameFinish(){
            if(percent == 100)
                isFinish = true;
            Intent intent = new Intent();
            intent.putExtra("package",isFinish);
            setResult(Configs.REQ_CODE_CHOOSE_LEVEL,intent);
            finish();
    }

}
