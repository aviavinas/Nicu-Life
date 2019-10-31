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

    public static class Room {
        private String title, address, img;
        private Long price, room, toilet;

        public Room() {}

        public Room(String title, String address, String img, Long price, Long room, Long toilet) {
            this.title = title;
            this.address = address;
            this.img = img;
            this.price = price;
            this.room = room;
            this.toilet = toilet;
        }

        public String getTitle() {
            return title;
        }

        public String getAddress() {
            return address;
        }

        public String getImg() {
            return img;
        }

        public Long getPrice() {
            return price;
        }

        public Long getRoom() {
            return room;
        }

        public Long getToilet() {
            return toilet;
        }
    }

    public static class Service {
        private String name, desc, img;
        private Long price;

        public Service() {}

        public Service(String name, String desc, String img, Long price) {
            this.name = name;
            this.desc = desc;
            this.img = img;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public String getImg() {
            return img;
        }

        public Long getPrice() {
            return price;
        }
    }
}