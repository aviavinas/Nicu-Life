package com.nicu.life.Recycler;

public class Card {

    public static class Category {
        private String name, icon;
        private Long count;

        public Category() {}

        public Category(String name, String icon, Long count) {
            this.name = name;
            this.icon = icon;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public Long getCount() {
            return count;
        }
    }

    public static class Product {
        private String title, img, desc;
        private Long price;

        public Product() {}

        public Product(String title, String img, String desc, Long price) {
            this.title = title;
            this.img = img;
            this.desc = desc;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getImg() {
            return img;
        }

        public String getDesc() {
            return desc;
        }

        public Long getPrice() {
            return price;
        }
    }
}