package model;

public class Product {
    private int proid;
    private int catid;
    private String proname;
    private String image;
    private String prostatus;
    private int quantity;
    private int price;
    Category cate;

    public Product() {
    }

    public Product(String proname, String image, String prostatus, int quantity, int price) {
        this.proname = proname;
        this.image = image;
        this.prostatus = prostatus;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Product(int proid, int catid, String proname, String image, String prostatus, int quantity, int price) {
        this.proid = proid;
        this.catid = catid;
        this.proname = proname;
        this.image = image;
        this.prostatus = prostatus;
        this.quantity = quantity;
        this.price = price;
    }

    
    public Product(int proid, int catid, String proname, String image, String prostatus, int quantity, int price, Category cate) {
        this.proid = proid;
        this.catid = catid;
        this.proname = proname;
        this.image = image;
        this.prostatus = prostatus;
        this.quantity = quantity;
        this.price = price;
        this.cate = cate;
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProstatus() {
        return prostatus;
    }

    public void setProstatus(String prostatus) {
        this.prostatus = prostatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }


    
}
