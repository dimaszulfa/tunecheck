// Generated by view binder compiler. Do not edit!
package gfg.bangkit.capstone.tunecheck.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import gfg.bangkit.capstone.tunecheck.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final TextInputEditText etEmail;

  @NonNull
  public final TextInputEditText etFirstName;

  @NonNull
  public final TextInputEditText etLastName;

  @NonNull
  public final TextInputEditText etPassword;

  @NonNull
  public final TextInputEditText etUsername;

  @NonNull
  public final TextView lbPassword;

  @NonNull
  public final TextView lbemail;

  @NonNull
  public final TextView lbusername;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final LinearLayout name;

  @NonNull
  public final LinearLayout termsandLogin;

  @NonNull
  public final TextView tvLogin;

  @NonNull
  public final TextView tvRegister;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLogin,
      @NonNull TextInputEditText etEmail, @NonNull TextInputEditText etFirstName,
      @NonNull TextInputEditText etLastName, @NonNull TextInputEditText etPassword,
      @NonNull TextInputEditText etUsername, @NonNull TextView lbPassword,
      @NonNull TextView lbemail, @NonNull TextView lbusername, @NonNull ConstraintLayout main,
      @NonNull LinearLayout name, @NonNull LinearLayout termsandLogin, @NonNull TextView tvLogin,
      @NonNull TextView tvRegister) {
    this.rootView = rootView;
    this.btnLogin = btnLogin;
    this.etEmail = etEmail;
    this.etFirstName = etFirstName;
    this.etLastName = etLastName;
    this.etPassword = etPassword;
    this.etUsername = etUsername;
    this.lbPassword = lbPassword;
    this.lbemail = lbemail;
    this.lbusername = lbusername;
    this.main = main;
    this.name = name;
    this.termsandLogin = termsandLogin;
    this.tvLogin = tvLogin;
    this.tvRegister = tvRegister;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnLogin;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.etEmail;
      TextInputEditText etEmail = ViewBindings.findChildViewById(rootView, id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.etFirstName;
      TextInputEditText etFirstName = ViewBindings.findChildViewById(rootView, id);
      if (etFirstName == null) {
        break missingId;
      }

      id = R.id.etLastName;
      TextInputEditText etLastName = ViewBindings.findChildViewById(rootView, id);
      if (etLastName == null) {
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

      id = R.id.lbPassword;
      TextView lbPassword = ViewBindings.findChildViewById(rootView, id);
      if (lbPassword == null) {
        break missingId;
      }

      id = R.id.lbemail;
      TextView lbemail = ViewBindings.findChildViewById(rootView, id);
      if (lbemail == null) {
        break missingId;
      }

      id = R.id.lbusername;
      TextView lbusername = ViewBindings.findChildViewById(rootView, id);
      if (lbusername == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.name;
      LinearLayout name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.termsandLogin;
      LinearLayout termsandLogin = ViewBindings.findChildViewById(rootView, id);
      if (termsandLogin == null) {
        break missingId;
      }

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

      return new ActivityRegisterBinding((ConstraintLayout) rootView, btnLogin, etEmail,
          etFirstName, etLastName, etPassword, etUsername, lbPassword, lbemail, lbusername, main,
          name, termsandLogin, tvLogin, tvRegister);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
