package com.example.demo.util;

import lombok.Data;

@Data
public class PagingUtil {
    Integer currentPageNum;         // 현재 페이지 번호

    Integer objectCountTotal;       // 전체 글 수
    Integer objectCountPerPage;     // 한 화면에 출력할 오브젝트 수
    Integer objectStartNum;         // 한 화면에 표시되는 오브젝트의 시작
    Integer objectEndNum;           // 한 화면에 표시되는 오브젝트의 마지막

    Integer pageNumCountTotal;      // 전체 페이지 수
    Integer pageNumCountPerPage;    // 한 화면에 출력할 페이지 번호 수
    Integer pageNumStart;           // 한 화면에 출력되는 페이지 번호의 시작
    Integer pageNumEnd;             // 한 화면에 출력되는 페이지 번호의 마지막

    Boolean isPrev;                 // 이전 페이지 표시 여부
    Boolean isNext;                 // 다음 페이지 표시여부

    public PagingUtil(){
        this.currentPageNum = 1;
        this.objectCountPerPage = 10;
        this.pageNumCountPerPage = 10;

        setObjectStartAndEnd();
    }
    public PagingUtil(int currentPageNum) {
        this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
        this.objectCountPerPage = 10;
        this.pageNumCountPerPage = 10;

        setObjectStartAndEnd();
    }
    public PagingUtil(int currentPageNum, int objectCountPerPage, int pageNumCountPerPage) {
        this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
        this.objectCountPerPage = (0 < objectCountPerPage) ? objectCountPerPage : 10 ;
        this.pageNumCountPerPage = (0 < pageNumCountPerPage) ? pageNumCountPerPage : 10 ;

        setObjectStartAndEnd();
    }
    public void setObjectStartAndEnd() {
        this.objectEndNum = currentPageNum * objectCountPerPage;
        this.objectStartNum = (objectEndNum - 1) - (objectCountPerPage - 1);

    }
    public boolean setCalcForPaging(Integer objectCountTotal) {
        if (objectCountTotal == null) {
            return false;
        }

        try {

            this.objectCountTotal = objectCountTotal;
            this.pageNumCountTotal = (int) Math.ceil((double)objectCountTotal / objectCountPerPage);

            int tmpPageNumStart = ((int) Math.ceil(currentPageNum / pageNumCountPerPage)
                    * pageNumCountPerPage);
            int tmpPageNumEnd = 0;

            if (tmpPageNumStart == 0) {
                this.pageNumStart = 1;
                tmpPageNumEnd = tmpPageNumStart + pageNumCountPerPage;
            } else if (tmpPageNumStart == currentPageNum) {
                this.pageNumStart = tmpPageNumStart - (pageNumCountPerPage - 1);
                tmpPageNumEnd = currentPageNum;
            } else {
                this.pageNumStart = tmpPageNumStart + 1;
                tmpPageNumEnd = pageNumStart + pageNumCountPerPage;
            }


            this.pageNumEnd = (pageNumCountTotal < tmpPageNumEnd) ? pageNumCountTotal : tmpPageNumEnd;

            this.isPrev = (currentPageNum > pageNumCountPerPage) ? true : false;
            this.isNext = (pageNumEnd < pageNumCountTotal || (pageNumStart < pageNumEnd && currentPageNum < pageNumCountTotal)  ) ? true : false;

            this.objectEndNum = (objectCountTotal < objectEndNum) ? objectCountTotal : objectEndNum;
            return true;

        } catch (Exception e) {e.printStackTrace(); return false;}

    }
    public boolean setCalcForPaging() {
        return setCalcForPaging(this.objectCountTotal);
    }

}
