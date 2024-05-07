package com.oasm.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO<T> {

    private List<T> content;    // 페이지에 표시할 내용
    @Builder.Default
    private int pageNumber = 1; // 현재 페이지 번호
    @Builder.Default
    private int pageSize = 10;  // 페이지 크기
    @Builder.Default
    private int groupSize = 10; // 그룹 당 페이지 수
    private int groupFirstPage; // 그룹 내 첫번째 페이지
    private int groupLastPage;  // 그룹 내 마지막 페이지
    private int totalElements;  // 전체 항목 수
    private int totalPages;     // 전체 페이지 수

    // 전체 페이지 수 계산을 위한 생성자
    public PageDTO(List<T> lists, int no, int elements, int size) {
        this.content = lists;
        this.pageNumber = no;
        this.totalElements = elements;
        this.pageSize = size;

        if (elements % size == 0) {
            this.totalPages = elements/size;
        } else {
            this.totalPages = (elements/size) + 1;
        }
    }

    // 첫 페이지 여부
    public boolean isFirst() {
        return pageNumber == 1;
    }

    // 마지막 페이지 여부
    public boolean isLast() {
        return pageNumber == totalPages;
    }

    // 첫 번째 페이지로 이동하는 페이지 번호 반환
    public int getFirstPageNumber() {
        return 1;
    }

    // 이전 페이지로 이동하는 페이지 번호 반환
    public int getPreviousPageNumber() {
        return Math.max(1, pageNumber - 1);
    }

    // 다음 페이지로 이동하는 페이지 번호 반환
    public int getNextPageNumber() {
        return Math.min(totalPages, pageNumber + 1);
    }

    // 마지막 페이지로 이동하는 페이지 번호 반환
    public int getLastPageNumber() {
        return totalPages;
    }

    // 특정 페이지로 이동하는 페이지 번호 반환
    public int getPageNumber(int targetPage) {
        return Math.min(Math.max(1, targetPage), totalPages);
    }

    public int getGroupFirstPage() {
        int currentPageGroup = (pageNumber - 1) / groupSize; // 현재 페이지가 속한 페이지 그룹
        int firstPg = currentPageGroup * groupSize + 1; // 현재 그룹의 첫 페이지 번호
        return firstPg;
    }

    public int getGroupLastPage() {
        int currentPageGroup = (pageNumber - 1) / groupSize; // 현재 페이지가 속한 페이지 그룹
        int groupFirstPage = currentPageGroup * groupSize + 1; // 현재 그룹의 첫 페이지 번호
        int lastPg = Math.min(groupFirstPage + groupSize - 1, totalPages); // 현재 그룹의 마지막 페이지 번호
        return lastPg;
    }


    // 페이지 수 설정
    public int getTotalPages(){
        if (this.totalElements % this.pageSize == 0) {
            return this.totalElements/this.pageSize;
        } else {
            return (this.totalElements/this.pageSize) + 1;
        }
    }
    
    // offset 계산
    public int calculateOffset() {
        return (pageNumber - 1) * pageSize;
    }


}
