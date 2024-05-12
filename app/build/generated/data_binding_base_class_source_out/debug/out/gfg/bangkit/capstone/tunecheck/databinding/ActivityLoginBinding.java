// Generated by view binder compiler. Do not edit!
package gfg.bangkit.capstone.tunecheck.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import gfg.bangkit.capstone.tunecheck.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final TextInputEditText etPassword;

  @NonNull
  public final TextInputEditText etUsername;

  @NonNull
  public final TextInputLayout inputLayoutPassword;

  @NonNull
  public final TextInputLayout inputLayoutSignIn;

  @NonNull
  public final ImageView ivLogin;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView tvLogin;

  @NonNull
  public final TextView tvRegister;

  @NonNull
  public final TextView tvSignIn;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLogin,
      @NonNull TextInputEditText etPassword, @NonNull TextInputEditText etUsername,
      @NonNull TextInputLayout inputLayoutPassword, @NonNull TextInputLayout inputLayoutSignIn,
      @NonNull ImageView ivLogin, @NonNull ConstraintLayout main, @NonNull TextView tvLogin,
      @NonNull TextView tvRegister, @NonNull TextView tvSignIn) {
    this.rootView = rootView;
    this.btnLogin = btnLogin;
    this.etPassword = etPassword;
    this.etUsername = etUsername;
    this.inputLayoutPassword = inputLayoutPassword;
    this.inputLayoutSignIn = inputLayoutSignIn;
    this.ivLogin = ivLogin;
    this.main = main;
    this.tvLogin = tvLogin;
    this.tvRegister = tvRegister;
    this.tvSignIn = tvSignIn;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnLogin;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.etPassword;
      TextInputEditText etPassword = ViewBindings.findChildViewById(rootView, id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.etUsername;
      TextInputEditText etUsername = ViewBindings.findChildViewById(rootView, id);
      if (etUsername == null) {
        break missingId;
      }

      id = R.id.inputLayoutPassword;
      TextInputLayout inputLayoutPassword = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutPassword == null) {
        break missingId;
      }

      id = R.id.inputLayoutSignIn;
      TextInputLayout inputLayoutSignIn = ViewBindings.findChildViewById(rootView, id);
      if (inputLayoutSignIn == null) {
        break missingId;
      }

      id = R.id.ivLogin;
      ImageView ivLogin = ViewBindings.findChildViewById(rootView, id);
      if (ivLogin == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.tvLogin;
      TextView tvLogin = ViewBindings.findChildViewById(rootView, id);
      if (tvLogin == null) {
        break missingId;
      }

      id = R.id.tvRegister;
      TextView tvRegister = ViewBindings.findChildViewById(rootView, id);
      if (tvRegister == null) {
        break missingId;
      }

      id = R.id.tvSignIn;
      TextView tvSignIn = ViewBindings.findChildViewById(rootView, id);
      if (tvSignIn == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, btnLogin, etPassword, etUsername,
          inputLayoutPassword, inputLayoutSignIn, ivLogin, main, tvLogin, tvRegister, tvSignIn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
