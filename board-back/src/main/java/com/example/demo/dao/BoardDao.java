package com.example.demo.dao;


import com.example.demo.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    List<Board> getAllBoard();

    int createBoard(Board board);
}
