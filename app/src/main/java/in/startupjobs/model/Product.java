package in.startupjobs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String title;
    private String description;
    private String price;

    public Product(String title, String description, String price) {

        this.title = title;
        this.description = description;
        this.price = price;

    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(description);
        out.writeString(price);
    }
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    private Product(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readString();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
