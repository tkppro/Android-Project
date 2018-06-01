package sourcecode.thangdang.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 30-May-18.
 */

public class RankingAdapter extends ArrayAdapter<Ranking> {
    private ArrayList<Ranking> mLists;
    Context context;

    private static class ViewHolder {
        TextView tvName;
        TextView tvTime;

    }

    public RankingAdapter(ArrayList<Ranking> mLists, Context context) {
        super(context, R.layout.row_item_ranking, mLists);
        this.mLists = mLists;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Ranking ranking = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_ranking, parent, false);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.tvName.setText(ranking.getmName());
        viewHolder.tvTime.setText(ranking.getmTime());

        // Return the completed view to render on screen
        return convertView;
    }
}
