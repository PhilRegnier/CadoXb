package com.example.cadox.ui.articleview;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.cadox.R;
import com.example.cadox.bo.Article;
import com.example.cadox.databinding.FragmentDetailBinding;
import com.example.cadox.repository.ArticleRepository;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;

public class DetailFragment extends Fragment {

    private ArticleRepository articleManager;
    private Article currentArticle;
    private FragmentDetailBinding binding;

    public DetailFragment() {
        articleManager = ArticleRepository.getInstance();
    }

    private void displayData(){
        binding.setArticle(currentArticle); //==> déclenche la mise à jour des champs
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        currentArticle = articleManager.getFirstArticle();

        binding.imageButtonInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailFragment.this.getActivity(), "Page Internet disponible : " + currentArticle.getUrl(), Toast.LENGTH_LONG).show();
            }
        });
        binding.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailFragment.this.getActivity());
                builder.setTitle(R.string.alert_dialog_title)
                        .setMessage(R.string.alert_dialog_msg)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Article article = currentArticle;

                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        binding.imageButtonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, getString(R.string.snackbar_msg), Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.snackbar_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // action à prévoir
                    }
                });
                snackbar.show();
            }
        });

        binding.checkBoxEtat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentArticle.setAchete(isChecked);
                currentArticle.setDateAchat(currentArticle.isAchete() ? LocalDate.now() : null);
                displayData();
                articleManager.replace(currentArticle);
            }
        });

        displayData();

        return binding.getRoot();
    }

}