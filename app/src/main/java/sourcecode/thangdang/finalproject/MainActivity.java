package sourcecode.thangdang.finalproject;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnPlay, mBtnHelp, mBtnOption, mBtnHome, mBtnRanking;
    SoundManager sound;
    MediaPlayer song;
    SaveStatus saveGameStatus;
    private ListView mLvResult;
    ArrayList<Ranking> lists = new ArrayList<>();
    private static RankingAdapter rankingAdapter;
    private MyDatabase db;
    private Button mBtnExitOps, mBtnSoundOn, mBtnSoundOff, mBtnMusicOn, mBtnMusicOff, mBtnSaveOpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDatabase(this);

        saveGameStatus = new SaveStatus(getSharedPreferences("game status",MODE_PRIVATE));

        song= MediaPlayer.create(MainActivity.this,R.raw.home_sound);
        song.setLooping(true);
        if(saveGameStatus.isMusicOn())
            song.start();

//        Ranking ranking = new Ranking();
//        ranking.setmId(1);
//        ranking.setmName("Thang");
//        ranking.setmTime("21");
//        lists.add(ranking);
//        ranking = (Ranking) getIntent().getExtras().getSerializable("record");
//        lists.add(ranking);

        sound = SoundManager.getInstance();
        sound.init(this);
        init();
        setClickListener();
    }

    public void init(){
        mBtnPlay = (Button)findViewById(R.id.btn_play);
        mBtnHome = (Button)findViewById(R.id.btn_home);
        mBtnHelp = (Button)findViewById(R.id.btn_help);
        mBtnOption = (Button)findViewById(R.id.btn_option);
        mBtnRanking = (Button)findViewById(R.id.btn_ranking);
    }

    public void setClickListener(){
        mBtnPlay.setOnClickListener(this);
        mBtnOption.setOnClickListener(this);
        mBtnHelp.setOnClickListener(this);
        mBtnHome.setOnClickListener(this);
        mBtnRanking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                sound.playSound(R.raw.button_effect);
                Intent intent =  new Intent(this,ChooseLevel.class);
                this.startActivity(intent);
                song.stop();
                break;
            case R.id.btn_home:
                sound.playSound(R.raw.button_effect);
                break;
            case R.id.btn_help:
                sound.playSound(R.raw.button_effect);
                helpDialog();
                break;
            case R.id.btn_option:
                sound.playSound(R.raw.button_effect);
                //dialogOption();
                //alertBuilder();
                break;
            case R.id.btn_ranking:
                sound.playSound(R.raw.button_effect);
                rankingDialog();

                break;

        }
    }

    public void rankingDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ranking_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        mLvResult = (ListView)dialog.findViewById(R.id.lv_ranking);
        //lists = db.getAllRankings();

        rankingAdapter = new RankingAdapter(lists,getApplicationContext());
        mLvResult.setAdapter(rankingAdapter);

        db.close();

        Button btnBack = (Button)dialog.findViewById(R.id.btn_back_ranking);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    public void helpDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.help_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public  void  dialogOption() {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_option_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        song.isPlaying();
        dialog.show();

        mBtnExitOps = (Button)dialog.findViewById(R.id.btn_exit_option);
        mBtnMusicOn = (Button)dialog.findViewById(R.id.btn_music_option_on);
        mBtnMusicOff = (Button)dialog.findViewById(R.id.btn_music_option_off);
        mBtnSoundOn = (Button)dialog.findViewById(R.id.btn_sound_option_on);
        mBtnSoundOff = (Button)dialog.findViewById(R.id.btn_sound_option_off);
        mBtnSaveOpt = (Button)dialog.findViewById(R.id.btn_save_option);

        Log.d("check","music status: " + saveGameStatus.getMusicStatus() + " " + "sound status: " + saveGameStatus.getSoundStatus());

        if(saveGameStatus.isSoundOn()){
            mBtnSoundOn.setVisibility(View.VISIBLE);
            mBtnSoundOff.setVisibility(View.INVISIBLE);
        }
        else {
            mBtnSoundOn.setVisibility(View.INVISIBLE);
            mBtnSoundOff.setVisibility(View.VISIBLE);
        }

        if(saveGameStatus.isMusicOn()){
            mBtnMusicOn.setVisibility(View.VISIBLE);
            mBtnMusicOff.setVisibility(View.INVISIBLE);
        }
        else {
            mBtnMusicOn.setVisibility(View.INVISIBLE);
            mBtnMusicOff.setVisibility(View.VISIBLE);
        }


        mBtnExitOps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                dialog.dismiss();
            }
        });
        mBtnSoundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnSoundOn.setVisibility(View.INVISIBLE);
                mBtnSoundOff.setVisibility(View.VISIBLE);
            }
        });
        mBtnSoundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                mBtnSoundOff.setVisibility(View.INVISIBLE);
                mBtnSoundOn.setVisibility(View.VISIBLE);
            }
        });

        mBtnMusicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                mBtnMusicOn.setVisibility(View.INVISIBLE);
                mBtnMusicOff.setVisibility(View.VISIBLE);
            }
        });

        mBtnMusicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                mBtnMusicOff.setVisibility(View.INVISIBLE);
                mBtnMusicOn.setVisibility(View.VISIBLE);
            }
        });

        mBtnSaveOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                if(mBtnMusicOff.getVisibility() == View.VISIBLE) {
                    saveGameStatus.setMusicStatus(saveGameStatus.changeStatusToString(View.VISIBLE));
                    Log.d("btn","check" + " music off: "+saveGameStatus.getMusicStatus());
                    song.pause();
                }
                if(mBtnMusicOff.getVisibility() == View.INVISIBLE) {
                    saveGameStatus.setMusicStatus(saveGameStatus.changeStatusToString(View.INVISIBLE));
                    Log.d("btn","check" +" music on: " + saveGameStatus.getMusicStatus());
                    song.start();
                }
                if(mBtnSoundOff.getVisibility() == View.VISIBLE) {
                    saveGameStatus.setSoundStatus(saveGameStatus.changeStatusToString(View.VISIBLE));
                    Log.d("btn","check" +" sound off:" + saveGameStatus.getSoundStatus());
                    sound.setMuted(true);
                }
                if(mBtnSoundOff.getVisibility() == View.INVISIBLE) {
                    saveGameStatus.setSoundStatus(saveGameStatus.changeStatusToString(View.INVISIBLE));
                    Log.d("btn","check" +" sound on:" + saveGameStatus.getSoundStatus());
                    sound.setMuted(false);
                }

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 1 && resultCode == RESULT_OK) {
            Ranking ranking = (Ranking) data.getExtras().getSerializable("record");
            Log.d("check","value" + ranking.getmName() + "-" + ranking.getmTime());
            lists.add(ranking);
        }
    }
}
