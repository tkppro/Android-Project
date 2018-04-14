package sourcecode.thangdang.finalproject;

import android.content.SharedPreferences;
import android.view.View;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Admin on 12-Apr-18.
 */

public class SaveStatus {
    SharedPreferences pre;
    final SharedPreferences.Editor edit;
    public SaveStatus(SharedPreferences pre){
        this.pre = pre;
        edit=pre.edit();
    }

    public void setMusicStatus(String status){
       edit.putString("music",status);
       edit.commit();
    }

    public String getMusicStatus(){
        return pre.getString("music","on");
    }

    public void setSoundStatus(String status){
       edit.putString("sound",status);
       edit.commit();
    }

    public String getSoundStatus(){
        return pre.getString("sound","on");
    }

    public int changeStringToStatus(String status){
        if(status.equals("on"))
         return View.VISIBLE;

        return View.INVISIBLE;
    }

    public String changeStatusToString(int status){
        if(status == View.VISIBLE)
            return "on";
        return "off";
    }


}
