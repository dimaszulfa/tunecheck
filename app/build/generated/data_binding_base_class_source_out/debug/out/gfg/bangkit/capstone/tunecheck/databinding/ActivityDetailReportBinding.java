// Generated by view binder compiler. Do not edit!
package gfg.bangkit.capstone.tunecheck.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import gfg.bangkit.capstone.tunecheck.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailReportBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnVerif;

  @NonNull
  public final ImageView ivImageDetailLaporan;

  @NonNull
  public final LinearLayout main;

  @NonNull
  public final TextView tvDateDetail;

  @NonNull
  public final EditText tvIsiLaporan;

  @NonNull
  public final TextView tvUsername;

  private ActivityDetailReportBinding(@NonNull LinearLayout rootView, @NonNull Button btnVerif,
      @NonNull ImageView ivImageDetailLaporan, @NonNull LinearLayout main,
      @NonNull TextView tvDateDetail, @NonNull EditText tvIsiLaporan,
      @NonNull TextView tvUsername) {
    this.rootView = rootView;
    this.btnVerif = btnVerif;
    this.ivImageDetailLaporan = ivImageDetailLaporan;
    this.main = main;
    this.tvDateDetail = tvDateDetail;
    this.tvIsiLaporan = tvIsiLaporan;
    this.tvUsername = tvUsername;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailReportBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailReportBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail_report, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailReportBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnVerif;
      Button btnVerif = ViewBindings.findChildViewById(rootView, id);
      if (btnVerif == null) {
        break missingId;
      }

      id = R.id.ivImageDetailLaporan;
      ImageView ivImageDetailLaporan = ViewBindings.findChildViewById(rootView, id);
      if (ivImageDetailLaporan == null) {
        break missingId;
      }

      LinearLayout main = (LinearLayout) rootView;

      id = R.id.tvDateDetail;
      TextView tvDateDetail = ViewBindings.findChildViewById(rootView, id);
      if (tvDateDetail == null) {
        break missingId;
      }

      id = R.id.tvIsiLaporan;
      EditText tvIsiLaporan = ViewBindings.findChildViewById(rootView, id);
      if (tvIsiLaporan == null) {
        break missingId;
      }

      id = R.id.tvUsername;
      TextView tvUsername = ViewBindings.findChildViewById(rootView, id);
      if (tvUsername == null) {
        break missingId;
      }

      return new ActivityDetailReportBinding((LinearLayout) rootView, btnVerif,
          ivImageDetailLaporan, main, tvDateDetail, tvIsiLaporan, tvUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
