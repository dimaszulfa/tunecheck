// Generated by view binder compiler. Do not edit!
package gfg.bangkit.capstone.tunecheck.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import gfg.bangkit.capstone.tunecheck.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentListBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageView btnSearch;

  @NonNull
  public final RecyclerView rvReports;

  @NonNull
  public final EditText searchEditText;

  @NonNull
  public final TextView tvPelaporan;

  private FragmentListBinding(@NonNull FrameLayout rootView, @NonNull ImageView btnSearch,
      @NonNull RecyclerView rvReports, @NonNull EditText searchEditText,
      @NonNull TextView tvPelaporan) {
    this.rootView = rootView;
    this.btnSearch = btnSearch;
    this.rvReports = rvReports;
    this.searchEditText = searchEditText;
    this.tvPelaporan = tvPelaporan;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSearch;
      ImageView btnSearch = ViewBindings.findChildViewById(rootView, id);
      if (btnSearch == null) {
        break missingId;
      }

      id = R.id.rvReports;
      RecyclerView rvReports = ViewBindings.findChildViewById(rootView, id);
      if (rvReports == null) {
        break missingId;
      }

      id = R.id.searchEditText;
      EditText searchEditText = ViewBindings.findChildViewById(rootView, id);
      if (searchEditText == null) {
        break missingId;
      }

      id = R.id.tvPelaporan;
      TextView tvPelaporan = ViewBindings.findChildViewById(rootView, id);
      if (tvPelaporan == null) {
        break missingId;
      }

      return new FragmentListBinding((FrameLayout) rootView, btnSearch, rvReports, searchEditText,
          tvPelaporan);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
