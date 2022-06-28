package com.milk.pay.dto.title;

import java.util.List;

public class ListTitleDto {
    
    private List<TitleDto> results;

    private Integer page;

    private Integer allResultsSize;

    public List<TitleDto> getResults() {
        return results;
    }

    public void setResults(List<TitleDto> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getAllResultsSize() {
        return allResultsSize;
    }

    public void setAllResultsSize(Integer allResultsSize) {
        this.allResultsSize = allResultsSize;
    }
}
