package com.example.demo.dao.impl;

import com.example.demo.dao.BoardDao;
import com.example.demo.model.Board;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Board> getAllBoard(){
        return sqlSessionTemplate.selectList("getAllBoard");
    }
    public int createBoard(Board board){
        return sqlSessionTemplate.insert("createBoard",board);
    }
}
