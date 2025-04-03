package xyz.wochib70.jimmer.integration.springboot.entity;

import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.util.List;

@Entity
public interface BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    @Key
    String name();

    @Nullable
    String website();

    @OneToMany(mappedBy = "store")
    List<Book> books();
}
