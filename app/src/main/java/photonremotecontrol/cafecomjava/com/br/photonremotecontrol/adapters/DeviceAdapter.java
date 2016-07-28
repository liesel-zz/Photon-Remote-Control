package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.Model.Device;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.R;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.utils.Utils;

/**
 * Created by Joel Backschat on 28/07/2016.
 */
public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    private ArrayList<Device> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.txtDeviceLabel)public TextView txtDeviceLabel;
        @BindView(R.id.txtOnlineDateLabel)public TextView txtOnlineDateLabel;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    public void add(int position, Device item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public DeviceAdapter(ArrayList<Device> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DeviceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_device, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Device name = mDataset.get(position);
        holder.txtDeviceLabel.setText(mDataset.get(position).getName());
        if (mDataset.get(position).getLastOnlineVerifed() != null){
            holder.txtOnlineDateLabel.setText(Utils.dateToString(mDataset.get(position).getLastOnlineVerifed()));
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

