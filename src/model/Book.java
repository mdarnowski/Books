package model;

import exception.ValidationException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Book implements Serializable {
    public static final String EXTENT_FILENAME = "Book.obj";
    private static List<Book> extent = new ArrayList<>();

    private long id;
    private String name;
    private LocalDate releaseDate;
    private Set<String> author = new HashSet<>();
    private ColorOfTheCover colorOfTheCover;
    private int price;
    private Integer discount;

    private static Set<String> knownAuthors = new HashSet<>();

    private Publisher publisher;

    public Book(long id, String name, LocalDate releaseDate, String author, ColorOfTheCover colorOfTheCover, int price) {
        this.id = id;
        setName(name);
        setReleaseDate(releaseDate);
        addAuthor(author);
        setColorOfTheCover(colorOfTheCover);
        setPrice(price);
        extent.add(this);
    }

    public Book(long id, String name, LocalDate releaseDate, String author, ColorOfTheCover colorOfTheCover, int price,
	Integer discount, Publisher publisher) {
        this.id = id;
        setName(name);
        setReleaseDate(releaseDate);
        addAuthor(author);
        setColorOfTheCover(colorOfTheCover);
        setPrice(price);
        setDiscount(discount);
        setPublisher(publisher);
        extent.add(this);
    }

    public static Set<String> getKnownAuthors() {
        return Collections.unmodifiableSet(knownAuthors);
    }

    public static void addKnownAuthors(String aut) {
        if (aut == null || "".equals(aut.trim()))
            throw new ValidationException("author cannot be empty");
        knownAuthors.add(aut);
    }

    public static void removeKnownAuthors(String aut) {
        if (knownAuthors.size() < 2)
            throw new ValidationException("cannot remove the last known author");
        knownAuthors.remove(aut);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || "".equals(name.trim()))
            throw new ValidationException("name cannot be empty");

        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate == null)
            throw new ValidationException("releaseDate cannot be null");

        this.releaseDate = releaseDate;
    }

    public Set<String> getAuthor() {
        return Collections.unmodifiableSet(author);
    }

    public void addAuthor(String aut) {
        if (aut == null || "".equals(aut.trim()))
            throw new ValidationException("author cannot be empty");
        this.author.add(aut);
    }

    public void removeAuthor(String aut) {
        if (this.author.size() < 2)
            throw new ValidationException("cannot remove the last author");
        this.author.remove(aut);
    }

    public ColorOfTheCover getColorOfTheCover() {
        return colorOfTheCover;
    }

    public void setColorOfTheCover(ColorOfTheCover colorOfTheCover) {
        if (colorOfTheCover == null)
            throw new ValidationException("color of the cover cannot be null");
        this.colorOfTheCover = colorOfTheCover;
    }

    public void setDiscount(Integer discount) {
        if(discount != null && discount < 0)
            throw new ValidationException("discount cannot be less than zero");

        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price < 0)
            throw new ValidationException("price cannot be less than zero");

        this.price = price;
    }

    public Integer getPriceAfterDiscount(){
        if(discount == null)
            return price;
        return price - discount;
    }

    public static List<Book> getExtent(){
        return Collections.unmodifiableList(extent);
    }

    public static void saveExtent(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXTENT_FILENAME))) {
            oos.writeObject(extent);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadExtent(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXTENT_FILENAME))) {
            extent = (List<Book>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static List<Book> findByAuthor(String cat){
        if(cat == null || "".equals(cat.trim()))
            return Collections.emptyList();

        return extent.stream().filter(a -> a.author.contains(cat))
                .collect(Collectors.toList());
    }

    public static List<Book> findByColorOfTheCover(ColorOfTheCover colorOfTheCover){
        if(colorOfTheCover == null)
            return Collections.emptyList();

        return extent.stream().filter(a -> a.colorOfTheCover.equals(colorOfTheCover))
                .collect(Collectors.toList());
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + releaseDate + ", " + author.toString() + ", " + colorOfTheCover + ", " + price;
    }
}
