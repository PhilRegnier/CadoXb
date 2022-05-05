package com.example.cadox.bo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Article implements Parcelable {
    private int id;
    private String intitule;
    private String description;
    private float prix;
    private boolean achete; //acheté ou non
    private LocalDate dateAchat;
    private byte niveau; // 0 - 5 (+ ou - satisfait)
    private String url;

    protected Article(Parcel in) {
        id = in.readInt();
        intitule = in.readString();
        description = in.readString();
        prix = in.readFloat();
        achete = in.readByte() != 0;
        niveau = in.readByte();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(intitule);
        dest.writeString(description);
        dest.writeFloat(prix);
        dest.writeByte((byte) (achete ? 1 : 0));
        dest.writeByte(niveau);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public String getPrixToString() {
        DecimalFormat decimalFormat = new DecimalFormat( "#,###,###.## €");
        return decimalFormat.format(this.prix);
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isAchete() {
        return achete;
    }

    public void setAchete(boolean etat) {
        this.achete = etat;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public String getDateAchatToString() {
        Locale local = Locale.getDefault();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy",local);
        return this.dateAchat != null ? "Acheté le : " + dateFormat.format(this.dateAchat) : "";
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public byte getNiveau() {
        return niveau;
    }

    public void setNiveau(byte niveau) {
        this.niveau = niveau;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article(){}

    public Article(int id, String intitule, String description, float prix, byte niveau, String url) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.prix = prix;
        this.niveau = niveau;
        this.url = url;

        this.achete = false;
        this.dateAchat = null;
    }


}
