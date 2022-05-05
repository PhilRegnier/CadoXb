package com.example.cadox.ui.articleview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadox.R;
import com.example.cadox.bo.Article;
import com.example.cadox.databinding.ActivityArticleViewBinding;
import com.example.cadox.repository.ArticleRepository;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class ArticleViewActivity extends AppCompatActivity {

    //cabler la vue à son manager
    private ArticleRepository articleManager;
    private Article currentArticle;

    //composant binding
    private ActivityArticleViewBinding binding;


    public ArticleViewActivity() {
        articleManager = ArticleRepository.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //lier le controleur - view au binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_view);

        //monter un article en mémoire pour le test
        /*
        currentArticle = new Article(1, "Des lunettes de soleil (memory)", "RAY-BAN RB 4259 601/19 51/20",
                85.0f, (byte)3, "https://www.optical-center.fr/lunettes-de-soleil/lunettes-de-soleil-RAY-BAN-RB-4259-60119-5120-25318.html?gclid=EAIaIQobChMIitHizMWe5QIVloXVCh1X6gw_EAQYASABEgLu0PD_BwE");
         */
        currentArticle = articleManager.getFirstArticle();

        //abonner les composants à leur écouteur
        binding.imageButtonInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //afficher le Toast
                Toast toast = Toast.makeText(ArticleViewActivity.this, "Page Internet disponible : " + currentArticle.getUrl(),Toast.LENGTH_LONG);
                toast.show();
            }
        });
        binding.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //afficher la boite de dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(ArticleViewActivity.this);
                builder.setTitle(R.string.alert_dialog_title)
                        .setMessage(R.string.alert_dialog_msg)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // action à prévoir
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
                //builder.create().show();
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
                //au changement d'état de la case à cocher,
                //1- mettre à jour l'état de l'article
                currentArticle.setAchete(isChecked);
                //2- mettre à jour la date d'achat
                currentArticle.setDateAchat(currentArticle.isAchete() ? LocalDate.now() : null);
                //3- rafraichir l'affichage
                displayData();
                //4-mettre à jour la source de données
                articleManager.replace(currentArticle);
            }
        });

        //afficher les données
        displayData();

    }

    private void displayData(){
        //alimenter la variable définie dans le layout
        binding.setArticle(currentArticle); //==> déclenche la mise à jour des champs
    }

}
