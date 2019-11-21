package com.nicu.life.Recycler;

import java.util.Date;

public class Card {

    public static class Shop {
        private String name, address;

        public Shop() {}

        public Shop(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
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

    public static class Order {
        private String item;
        private Long qnt, amt, orderId;
        private Date date;

        public Order() {}

        public Order(String item, Long qnt, Long amt, Long orderId, Date date) {
            this.item = item;
            this.qnt = qnt;
            this.amt = amt;
            this.orderId = orderId;
            this.date = date;
        }

        public String getItem() {
            return item;
        }

        public Long getQnt() {
            return qnt;
        }

        public Long getAmt() {
            return amt;
        }

        public Long getOrderId() {
            return orderId;
        }

        public Date getDate() {
            return date;
        }
    }
}