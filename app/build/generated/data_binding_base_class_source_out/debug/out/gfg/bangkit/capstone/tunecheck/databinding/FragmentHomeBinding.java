// Generated by view binder compiler. Do not edit!
package gfg.bangkit.capstone.tunecheck.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView;
import gfg.bangkit.capstone.tunecheck.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final AAChartView aaChartView;

  @NonNull
  public final TextView tvJumlahPelaporan;

  @NonNull
  public final TextView tvJumlahVerifikasi;

  @NonNull
  public final TextView tvTotalUser;

  private FragmentHomeBinding(@NonNull FrameLayout rootView, @NonNull AAChartView aaChartView,
      @NonNull TextView tvJumlahPelaporan, @NonNull TextView tvJumlahVerifikasi,
      @NonNull TextView tvTotalUser) {
    this.rootView = rootView;
    this.aaChartView = aaChartView;
    this.tvJumlahPelaporan = tvJumlahPelaporan;
    this.tvJumlahVerifikasi = tvJumlahVerifikasi;
    this.tvTotalUser = tvTotalUser;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.aa_chart_view;
      AAChartView aaChartView = ViewBindings.findChildViewById(rootView, id);
      if (aaChartView == null) {
        break missingId;
      }

      id = R.id.tvJumlahPelaporan;
      TextView tvJumlahPelaporan = ViewBindings.findChildViewById(rootView, id);
      if (tvJumlahPelaporan == null) {
        break missingId;
      }

      id = R.id.tvJumlahVerifikasi;
      TextView tvJumlahVerifikasi = ViewBindings.findChildViewById(rootView, id);
      if (tvJumlahVerifikasi == null) {
        break missingId;
      }

      id = R.id.tvTotalUser;
      TextView tvTotalUser = ViewBindings.findChildViewById(rootView, id);
      if (tvTotalUser == null) {
        break missingId;
      }

      return new FragmentHomeBinding((FrameLayout) rootView, aaChartView, tvJumlahPelaporan,
          tvJumlahVerifikasi, tvTotalUser);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
