package sourcecode.thangdang.finalproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class LevelTwoActivity extends AppCompatActivity implements View.OnClickListener{
    private GameItem mGameItem1, mGameItem2, mGameItem3, mGameItem4;
    private Button mBtnItem1, mBtnItem2, mBtnItem3, mBtnItem4, mBtnSetting, mBtnResume, mBtnQuit, mBtnTip;
    private TextView mTvResult, mTvCountTime, tvItem1, tvItem2, tvItem3, tvItem4;
    private boolean isFinish = false;
    private boolean isPause = false;
    private long RemainingTime = 0;
    private boolean checkItem1 = false, checkItem2 = false, checkItem3 = false, checkItem4 = false;
    CountDownTimer cdt;
    MediaPlayer song;
    int percent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        mGameItem1 = new GameItem(this,mBtnItem1, this.getResources().getString(R.string.item_1_lv2), R.raw.bed_sound);
        mGameItem2 = new GameItem(this,mBtnItem2, this.getResources().getString(R.string.item_2_lv2),R.raw.curtain_sound);
        mGameItem3 = new GameItem(this,mBtnItem3, this.getResources().getString(R.string.item_3_lv2),R.raw.lamp_sound);
        mGameItem4 = new GameItem(this,mBtnItem4, this.getResources().getString(R.string.item_4_lv2),R.raw.pillow_sound);

        init();
        setOnClick();
        gameStart();
    }

    public void init(){
        mBtnItem1 = (Button)findViewById(R.id.bedroom_item_bed);
        mBtnItem2 = (Button)findViewById(R.id.bedroom_item_curtain);
        mBtnItem3 = (Button)findViewById(R.id.bedroom_item_lamp);
        mBtnItem4 = (Button)findViewById(R.id.bedroom_item_pillow);
        mTvResult = (TextView)findViewById(R.id.tv_result);
        mTvCountTime = (TextView)findViewById(R.id.tv_timing);
        mBtnSetting = (Button)findViewById(R.id.btn_setting);

    }
    public void setOnClick(){
        mBtnItem1.setOnClickListener(this);
        mBtnItem2.setOnClickListener(this);
        mBtnItem3.setOnClickListener(this);
        mBtnItem4.setOnClickListener(this);
        mBtnSetting.setOnClickListener(this);

    }
    public void gameStart(){

        mBtnItem1.setClickable(true);
        mBtnItem2.setClickable(true);
        mBtnItem3.setClickable(true);
        mBtnItem4.setClickable(true);

        startTime(30000);
        percent = 0;
        isFinish = false;
        song= MediaPlayer.create(this,R.raw.lv_2);
        song.setLooping(true);
        song.start();
        mTvResult.setText(String.valueOf(percent) + "%");
    }

    //call when lose the game
    public void gameStop(){
        song.stop();

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.lose_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        if(!isFinishing())
            dialog.show();

        song = MediaPlayer.create(this,R.raw.lose_sound);
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
                finish();
            }
        });

    }
    @Override
    public void onClick(View view) {
        if(percent == 75) {
            winDialog();
            //alertBuilder();
            isPause = true;
        }
        switch (view.getId()){
            case R.id.bedroom_item_bed:
                checkItem1 = true;
                mGameItem1.isTouching();
                if(percent <= 75) {
                    percent += 25;
                    mBtnItem1.setClickable(false);
                    mTvResult.setText(String.valueOf(percent) + "%");
                }
                else
                    mTvResult.setText("You won");
                break;
            case R.id.bedroom_item_curtain:
                checkItem2 = true;
                mGameItem2.isTouching();
                if(percent <= 75) {
                    percent += 25;
                    mBtnItem2.setClickable(false);
                    mTvResult.setText(String.valueOf(percent) + "%");
                }
                else
                    mTvResult.setText("You won");
                break;
            case R.id.bedroom_item_lamp:
                checkItem3 = true;
                mGameItem3.isTouching();
                if(percent <= 75) {
                    percent += 25;
                    mBtnItem3.setClickable(false);
                    mTvResult.setText(String.valueOf(percent) + "%");
                }
                else
                    mTvResult.setText("You won");
                break;
            case R.id.bedroom_item_pillow:
                checkItem4 = true;
                mGameItem4.isTouching();
                if(percent <= 75) {
                    percent += 25;
                    mBtnItem4.setClickable(false);
                    mTvResult.setText(String.valueOf(percent) + "%");
                }
                else
                    mTvResult.setText("You won");
                break;
            case R.id.btn_setting:
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
        if(!isFinishing())
            pauseDialog.show();

        mBtnTip = (Button)pauseDialog.findViewById(R.id.btn_tip);
        mBtnResume = (Button)pauseDialog.findViewById(R.id.btn_resume);
        mBtnQuit = (Button)pauseDialog.findViewById(R.id.btn_quit);

        // create tips dialog and add contents
        // content: find an item and highlight the text. If found it, hide the text with gray color.
        mBtnTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseDialog.dismiss();
                tipsDialog(mGameItem1.getText(), mGameItem2.getText(), mGameItem3.getText(), mGameItem4.getText());
            }
        });

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

    private void tipsDialog(String item1, String item2, String item3, String item4) {
        final Dialog tipsDialog = new Dialog(this);
        tipsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        tipsDialog.setContentView(R.layout.tips_dialog);
        tipsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tipsDialog.setCanceledOnTouchOutside(false);

        tipsDialog.show();

        //init text view
        tvItem1 = (TextView)tipsDialog.findViewById(R.id.tv_item1);
        tvItem2 = (TextView)tipsDialog.findViewById(R.id.tv_item2);
        tvItem3 = (TextView)tipsDialog.findViewById(R.id.tv_item3);
        tvItem4 = (TextView)tipsDialog.findViewById(R.id.tv_item4);

        tvItem1.setText(item1);
        tvItem2.setText(item2);
        tvItem3.setText(item3);
        tvItem4.setText(item4);
        if(checkItem1)
            tvItem1.setTextColor(Color.parseColor("#bababa"));
        if(checkItem2)
            tvItem2.setTextColor(Color.parseColor("#bababa"));
        if(checkItem3)
            tvItem3.setTextColor(Color.parseColor("#bababa"));
        if(checkItem4)
            tvItem4.setTextColor(Color.parseColor("#bababa"));

        Button btnBack = (Button)tipsDialog.findViewById(R.id.btn_back_to_game);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipsDialog.dismiss();
                pauseDialog();
            }
        });

    }

    public void winDialog(){
        song.stop();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        if (!isFinishing() && checkItem1 && checkItem2 && checkItem3 || checkItem4 ) {
            dialog.show();
            isFinish = true;
        }
        song = MediaPlayer.create(this,R.raw.win_sound);
        song.start();

        Button btnMenu = (Button) dialog.findViewById(R.id.btn_menu);
        Button btnNextLevel = (Button) dialog.findViewById(R.id.btn_next);
        TextView tv = (TextView)dialog.findViewById(R.id.tv_time_win);
        long timing = (30000 - RemainingTime) / 1000;
        tv.setText("Your time: " + timing + "s");
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTheGameFinish();
                dialog.dismiss();
                finish();
            }
        });

        btnNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextLevel();
                dialog.dismiss();
                finish();
            }
        });

    }

    private void nextLevel(){
        Intent intent = new Intent(this, LevelThreeActivity.class);
        this.startActivity(intent);
        finish();
    }

    public void isTheGameFinish(){
        if(percent == 100)
            isFinish = true;
        Intent intent = new Intent();
        intent.putExtra("package",isFinish);
        setResult(Configs.REQ_CODE_CHOOSE_LEVEL_TWO,intent);

        song.stop();
        finish();
    }
}
