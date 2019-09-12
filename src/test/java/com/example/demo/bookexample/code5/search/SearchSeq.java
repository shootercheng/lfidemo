package com.example.demo.bookexample.code5.search;

import lombok.Data;

/**
 * @author chengdu
 * @date 2019/9/12.
 */
@Data
public class SearchSeq {

    private Integer start;

    private Integer end;

    public SearchSeq(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}
