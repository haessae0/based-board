package com.haessae0.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST TEST - API")
@Transactional //디비는 롤백 상태로 묶여버린다.
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("show {api} posts list")
    @Test
    void givenNothing_whenRequstArticles_thenReturnsArticlesJsonResponse() throws Exception {
        //GIVEN

        //WHEN & Then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("show {api} post")
    @Test
    void givenNothing_whenRequstArticle_thenReturnsArticleJsonResponse() throws Exception {
        //GIVEN

        //WHEN & Then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("show {api} post's comments")
    @Test
    void givenNothing_whenRequstArticleCommentsFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
        //GIVEN

        //WHEN & Then
        mvc.perform(get("/api/articles/5/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("show {api} comments list")
    @Test
    void givenNothing_whenRequstArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        //GIVEN

        //WHEN & Then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("show {api} comments list")
    @Test
    void givenNothing_whenRequstArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
        //GIVEN

        //WHEN & Then
        mvc.perform(get("/api/articleComments/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
