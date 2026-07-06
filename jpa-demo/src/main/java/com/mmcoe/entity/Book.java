package com.mmcoe.entity;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private int isbn;

    @Column(name="title", length=30)
    private String title;

    @Column(name="author", length=30)
    private String author;

    private double price;

    public Book() {}

    public Book(int isbn,
                String title,
                String author,
                double price) {

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }


	public int getIsbn() {
		return isbn;
	}


	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}
	
	
	
	
}