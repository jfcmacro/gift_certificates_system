package com.epam.esm.dao;

import com.epam.esm.models.Tag;
import java.util.List;

public interface TagDao {
    void insert(Tag tag);
    void delete(Tag tag);
    Tag findTagById(int id);
    List<Tag> findAll();
}

