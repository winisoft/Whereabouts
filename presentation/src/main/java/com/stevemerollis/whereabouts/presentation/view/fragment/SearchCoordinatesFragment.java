package com.stevemerollis.whereabouts.presentation.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.view.SearchCoordinatesView;

import butterknife.Bind;

public class SearchCoordinatesFragment extends BaseFragment implements SearchCoordinatesView {

    @Bind(R.id.scf_et_vicinity) EditText et_vicinity;
    @Bind(R.id.scf_iv_found) ImageView iv_found;
    @Bind(R.id.scf_iv_not_found) ImageView iv_not_found;
    @Bind(R.id.scf_tv_results) TextView tv_results;
    @Bind(R.id.scf_btn_find) Button btn_find;
    @Bind(R.id.scf_btn_ok) Button btn_ok;
    @Bind(R.id.scf_btn_cancel) Button btn_cancel;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override public Context context() { return getActivity(); }

    @Override public void showFoundImage(boolean show) { iv_found.setVisibility(show ? View.VISIBLE : View.GONE); }

    @Override public void showNotFoundImage(boolean show) { iv_not_found.setVisibility(show ? View.VISIBLE : View.GONE); }

    @Override public void setResults(String results) { if (results != null) { tv_results.setText(results); } }
}
