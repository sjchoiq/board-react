package com.example.demo.service;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
    private static Logger logger = LoggerFactory.getLogger(BoardService.class);
    @Autowired
    private BoardRepository boardRepository;

    public Page<Board> getAllBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board createBoard(Board board){
        board.setCreatedTime(new Date());
        return boardRepository.save(board);
    }

    public ResponseEntity<Board> getBoard(Integer no) {
        Board board = boardRepository.findById(no).orElseThrow( () -> new ResourceNotFoundException("Not exist Board Daqta by no : ["+no+"]"));
        return ResponseEntity.ok(board);
    }

    public ResponseEntity<Board> updateBoard(Integer no, Board updateBoard) {
        Board board = boardRepository.findById(no).orElseThrow(()-> new ResourceNotFoundException("Not exist Board Data by no : [\"+no+\"]"));
        board.setType(updateBoard.getType());
        board.setTitle(updateBoard.getTitle());
        board.setContents(updateBoard.getContents());
        board.setUpdatedTime(new Date());
        logger.error(new Date().toString());

        Board endUpdateedBoard = boardRepository.save(board);
        return ResponseEntity.ok(endUpdateedBoard);
    }

    public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer no) {
        Board board = boardRepository.findById(no).orElseThrow(()-> new ResourceNotFoundException("Not exist Board Data by no : [\"+no+\"]"));

        boardRepository.delete(board);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    public int findAllCount() {
        return (int) boardRepository.count();
    }

    public ResponseEntity<Map> getPagingBoard(Integer p_num) {
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
        pu.setObjectCountTotal(findAllCount());
        pu.setCalcForPaging();

        System.out.println("p_num : "+p_num);
        System.out.println(pu.toString());

        if (list == null || list.size() == 0) {
            return null;
        }

        result = new HashMap<>();
        result.put("pagingData", pu);
        result.put("list", list);

        return ResponseEntity.ok(result);
    }

    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
