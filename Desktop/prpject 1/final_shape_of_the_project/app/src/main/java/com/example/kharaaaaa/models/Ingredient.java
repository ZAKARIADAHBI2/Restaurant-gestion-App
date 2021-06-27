package com.example.kharaaaaa.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Ingredient implements Parcelable {
    private long id;
    private String name;
    private double Amout;
    private double price;
    private String Unit;
    private long recipeId;



    public Ingredient(String name, double Amout ,double price , String Unit ) {
        this.name = name;
        this.Amout= Amout;
        this.price= price;
        this.Unit = Unit;
    }

    public Ingredient(long id, String name,double Amout ,double price , String Unit, long recipeId) {
        this.id= id;
        this.name= name;
        this.Amout=Amout;
        this.price=price;
        this.Unit=Unit;
        this.recipeId = recipeId;

    }

    private Ingredient(Parcel in) {
        id = in.readLong();
        name = in.readString();
        Amout = in.readDouble();
        price = in.readDouble();
        Unit= in.readString();
        recipeId = in.readLong();
    }

public double grantotal(){
        double tot=0;
        tot= getPrice()*getAmout();
        return tot;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getAmout() {
        return Amout;
    }
    public double getPrice() {
        return price;
    }
  public void setPrice(double price){this.price=price;}
    public String getUnit() {
        return Unit;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAmout(double Amout) {
        this.Amout = Amout;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeDouble(Amout);
        parcel.writeDouble(price);
        parcel.writeString(Unit);
        parcel.writeLong(recipeId);
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {

        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='"  + name +
                ", Amout='" + Amout +
                ", price='" + price +
                ", Unit='"  + Unit + '\'' +
                ", recipeId="+ recipeId +

                    '}';
    }
}
