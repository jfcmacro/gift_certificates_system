package com.epam.esm.dao;

import com.epam.esm.models.Tag;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class JdbcTagDao implements TagDao {
    private static final String INSERT_SQL = "INSERT INTO tag (tag_id, name) VALUES (?, ?)";
    private static final String DELETE_SQL = "DELETE FROM tag WHERE tag_id=?";
    private static final String SELECT_ONE_SQL = "SELECT * FROM tag WHERE tag_id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM tag";

    private final DataSource dataSource;

    public JdbcTagDao(DataSource dataSource) {
	this.dataSource = dataSource;
    }

    @Override
    public void insert(Tag tag) {
	try (Connection conn = dataSource.getConnection();
	     PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
	    prepareStatement(ps, tag);
	    ps.executeUpdate();
	}
	catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public void delete(Tag tag) {
	try (Connection conn = dataSource.getConnection();
	     PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
	    ps.setInt(1, tag.getId());
	    ps.executeUpdate();
	}
	catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public Tag findTagById(int id) {
	try (Connection conn = dataSource.getConnection();
	     PreparedStatement ps = conn.prepareStatement(SELECT_ONE_SQL)) {
	    ps.setInt(1, id);
	    Tag tag = null;
	    try (ResultSet rs = ps.executeQuery()) {
		if (rs.next())
		    tag = toTag(rs);
	    }
	    return tag;
	}
	catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public List<Tag> findAll() {
	try (Connection conn = dataSource.getConnection();
	     PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
	     ResultSet rs = ps.executeQuery()) {
	    
	    List<Tag> tags = new ArrayList<>();
	    while (rs.next()) {
		tags.add(toTag(rs));
	    }
	    return tags;
	}
	catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private Tag toTag(ResultSet rs) throws SQLException {
	Tag tag = new Tag();
	tag.setId(rs.getInt("tag_id"));
	tag.setName(rs.getString("name"));
	return tag;
    }

    private void prepareStatement(PreparedStatement ps, Tag tag)
	throws SQLException {
	ps.setInt(1, tag.getId());
	ps.setString(2, tag.getName());
    }
}

