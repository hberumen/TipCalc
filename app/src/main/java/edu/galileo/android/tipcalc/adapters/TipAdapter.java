package edu.galileo.android.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.models.TipRecord;

/**
 * Created by hberumen on 01/06/16.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private Context context;
    private List<TipRecord> dataSet;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(OnItemClickListener onItemClickListener, List<TipRecord> dataSet, Context context) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.dataSet = dataSet;
    }

    public TipAdapter(OnItemClickListener onItemClickListener, Context context) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.dataSet = new ArrayList<TipRecord>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = dataSet.get(position);
        String strTip = String.format(
                    context.getString(R.string.global_message_tip),element.getTip());
        holder.txtContent.setText(strTip);
        holder.txtTimeStamp.setText(element.getDateFormatted());
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void add(TipRecord record){
        dataSet.add(0,record);
        notifyDataSetChanged();
    }

    public void clear(){
        dataSet.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtContent) TextView txtContent;
        @BindView(R.id.txtTimeStamp) TextView txtTimeStamp;
        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}
