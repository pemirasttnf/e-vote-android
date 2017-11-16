package sttnf.app.pemira.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.pemira.R;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.util.ItemClickListener;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class CalonAdapter extends RecyclerView.Adapter<CalonAdapter.Holder> {

    private ItemClickListener listener;
    private ArrayList<Calon> datas;
    private Context context;

    public CalonAdapter(ArrayList<Calon> datas, ItemClickListener listener) {
        this.listener = listener;
        this.datas = datas;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_calon, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, final int position) {
        Glide.with(context).load(datas.get(position).getAvatar()).centerCrop().into(holder.imgAvatar);
        holder.txtName.setText(datas.get(position).getName());
        holder.txtNim.setText(datas.get(position).getNim());
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onClick(position, v);
            }
        });
    }

    @Override public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_item) LinearLayout cardItem;
        @BindView(R.id.img_avatar) ImageView imgAvatar;
        @BindView(R.id.txt_name) TextView txtName;
        @BindView(R.id.txt_nim) TextView txtNim;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
