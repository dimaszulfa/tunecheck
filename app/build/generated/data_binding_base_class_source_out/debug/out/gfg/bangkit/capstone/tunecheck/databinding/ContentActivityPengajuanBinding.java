// Generated by view binder compiler. Do not edit!
package gfg.bangkit.capstone.tunecheck.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import gfg.bangkit.capstone.tunecheck.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContentActivityPengajuanBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnAjukanPengaduan;

  @NonNull
  public final LinearLayout btnLocation;

  @NonNull
  public final LinearLayout btnMonth;

  @NonNull
  public final EditText etLocation;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView tvDate;

  @NonNull
  public final Space tvDescription;

  @NonNull
  public final TextView tvPlaceholder;

  private ContentActivityPengajuanBinding(@NonNull RelativeLayout rootView,
      @NonNull Button btnAjukanPengaduan, @NonNull LinearLayout btnLocation,
      @NonNull LinearLayout btnMonth, @NonNull EditText etLocation, @NonNull ImageView imageView,
      @NonNull TextView tvDate, @NonNull Space tvDescription, @NonNull TextView tvPlaceholder) {
    this.rootView = rootView;
    this.btnAjukanPengaduan = btnAjukanPengaduan;
    this.btnLocation = btnLocation;
    this.btnMonth = btnMonth;
    this.etLocation = etLocation;
    this.imageView = imageView;
    this.tvDate = tvDate;
    this.tvDescription = tvDescription;
    this.tvPlaceholder = tvPlaceholder;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentActivityPengajuanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentActivityPengajuanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_activity_pengajuan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentActivityPengajuanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAjukanPengaduan;
      Button btnAjukanPengaduan = ViewBindings.findChildViewById(rootView, id);
      if (btnAjukanPengaduan == null) {
        break missingId;
      }

      id = R.id.btnLocation;
      LinearLayout btnLocation = ViewBindings.findChildViewById(rootView, id);
      if (btnLocation == null) {
        break missingId;
      }

      id = R.id.btnMonth;
      LinearLayout btnMonth = ViewBindings.findChildViewById(rootView, id);
      if (btnMonth == null) {
        break missingId;
      }

      id = R.id.etLocation;
      EditText etLocation = ViewBindings.findChildViewById(rootView, id);
      if (etLocation == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.tvDate;
      TextView tvDate = ViewBindings.findChildViewById(rootView, id);
      if (tvDate == null) {
        break missingId;
      }

      id = R.id.tvDescription;
      Space tvDescription = ViewBindings.findChildViewById(rootView, id);
      if (tvDescription == null) {
        break missingId;
      }

      id = R.id.tvPlaceholder;
      TextView tvPlaceholder = ViewBindings.findChildViewById(rootView, id);
      if (tvPlaceholder == null) {
        break missingId;
      }

      return new ContentActivityPengajuanBinding((RelativeLayout) rootView, btnAjukanPengaduan,
          btnLocation, btnMonth, etLocation, imageView, tvDate, tvDescription, tvPlaceholder);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}