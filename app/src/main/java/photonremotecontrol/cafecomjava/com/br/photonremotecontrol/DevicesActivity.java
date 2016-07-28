package photonremotecontrol.cafecomjava.com.br.photonremotecontrol;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joel Backschat on 28/07/2016.
 */
public class DevicesActivity extends Fragment {
    private LayoutInflater inflater;
    @BindView(R.id.recycler_view_devices) RecyclerView recycler_view_devices;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View v = inflater.inflate(R.layout.devices, container, false);
        ButterKnife.bind(this.getActivity());
        return v;

    }

}
