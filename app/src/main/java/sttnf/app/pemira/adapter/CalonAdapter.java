package sttnf.app.pemira.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import sttnf.app.pemira.R;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.util.ItemClickListener;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

@Animate(Animation.SCALE_UP_ASC)
@Layout(R.layout.cardview)
public class CalonAdapter {
    @View(R.id.txt_capres) private TextView txtCapres;
    @View(R.id.img_avatar) private ImageView imgAvatar;
    @View(R.id.txt_cawapres) private TextView txtCawapres;

    private ItemClickListener listener;
    private Context context;
    private Calon calons;

    private int position;

    public CalonAdapter(int position) {
        this.position = position;
    }

    public CalonAdapter with(Context context) {
        this.context = context;
        return this;
    }

    public CalonAdapter model(Calon calons) {
        this.calons = calons;
        return this;
    }

    public CalonAdapter click(ItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    @Resolve
    private void onResolved() {
        txtCapres.setText(calons.getName());
        txtCawapres.setText(calons.getCawapres());
        Glide.with(context)
                .load(calons.getHeader())
                .centerCrop()
                .into(imgAvatar);
    }

    @Click(R.id.card_item)
    private void onClick() {
        listener.onClick(position);
    }
}
