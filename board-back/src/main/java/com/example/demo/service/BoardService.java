package com.example.demo.service;


import com.example.demo.model.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoard();

    int createBoard(Board board);
}
