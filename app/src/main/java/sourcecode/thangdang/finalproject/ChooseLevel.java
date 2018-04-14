package sourcecode.thangdang.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLevel extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnLevelOne, mBtnLevelTwo, mBtnLevelThree, mBtnLevelFour, mBtnLevelFive, mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        init();
        setClickListener();

    }

    public void init(){
        mBtnLevelOne = (Button)findViewById(R.id.btn_level_one);
        mBtnLevelTwo = (Button)findViewById(R.id.btn_level_two);
        mBtnLevelThree = (Button)findViewById(R.id.btn_level_three);
        mBtnLevelFour = (Button)findViewById(R.id.btn_level_four);
        mBtnLevelFive = (Button)findViewById(R.id.btn_level_five);
    }

    public void setClickListener(){
        mBtnLevelOne.setOnClickListener(this);
        mBtnLevelTwo.setOnClickListener(this);
        mBtnLevelThree.setOnClickListener(this);
        mBtnLevelFour.setOnClickListener(this);
        mBtnLevelFive.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_level_one:
                Intent intent = new Intent(this,LevelOneActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_level_two:
                Intent intentTwo = new Intent(this,LevelTwoActivity.class);
                this.startActivity(intentTwo);
                break;
            case R.id.btn_level_three:
                Intent intentThree = new Intent(this,LevelThreeActivity.class);
                this.startActivity(intentThree);
                break;
            case R.id.btn_level_four:
                Intent intentFour = new Intent(this,LevelFourActivity.class);
                this.startActivity(intentFour);
                break;
            case R.id.btn_level_five:
                Intent intentFive = new Intent(this,LevelOneActivity.class);
                this.startActivity(intentFive);
                break;

        }
    }
}
