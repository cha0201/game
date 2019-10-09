package com.example.game.response;

import com.example.game.entity.GameTask;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonDeserialize(builder = QueryGameTaskResponse.QueryGameTaskResponseBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGameTaskResponse {

    private long total;

    private int pages;

    private int pageNum;

    private int pageSize;

    private  List<GameTask> data;

    @JsonPOJOBuilder(withPrefix = "")
    public static class QueryGameTaskResponseBuilder {

    }


}
