package com.example.demo.service.impl;
import com.example.demo.dao.BoardDao;
import com.example.demo.model.Board;
import com.example.demo.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final static Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Autowired
    private BoardDao boardDao;

    @Override
    public List<Board> getAllBoard(){
        return boardDao.getAllBoard();
    }
    @Override
    public int createBoard(Board board){
        board.setCreatedTime(new Date());
        return boardDao.createBoard(board);
    }
//    @Autowired
//    private AlertAllDao alertAllDao;
//
//    @Override
//    public List<AlertData> alertAllList() {
//        return alertAllDao.alertAllList();
//    }
//
//    @Override
//    public AlertData alertAllAlertData(AlertData info) {
//        return alertAllDao.alertAllAlertData(info);
//    }
//
//    @Override
//    public int insertAlertAll(AlertData info) {
//        return alertAllDao.insertAlertAll(info);
//    }
//
//    @Override
//    public int updateAlertAll(AlertData info) {
//        return alertAllDao.updateAlertAll(info);
//    }
//
//    @Override
//    public int monitoringAlertAll(AlertData info) {
//        return alertAllDao.monitoringAlertAll(info);
//    }
//
//    @Override
//    public int checkLogAlertAll(AlertData info) {
//        return alertAllDao.checkLogAlertAll(info);
//    }
//
//    @Override
//    public int initAlertAll() {
//        return alertAllDao.initAlertAll();
//    }
//
//    @Override
//    public int deleteAlertAll(AlertData info) {
//        return alertAllDao.deleteAlertAll(info);
//    }
}
