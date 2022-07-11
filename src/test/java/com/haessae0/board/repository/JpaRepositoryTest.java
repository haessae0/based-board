package com.haessae0.board.repository;

import com.haessae0.board.config.JpaConfig;
import com.haessae0.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        //GIVEN

        //WHEN
        List<Article> articles = articleRepository.findAll();
        //TEHN
        assertThat(articles).isNotNull().hasSize(1000);
    }

    @DisplayName("Insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        //GIVEN
        long previousCount = articleRepository.count();
        //WHEN
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));
        //TEHN
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("Update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        //GIVEN
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);
        //WHEN
        Article savedArticle = articleRepository.saveAndFlush(article);
        //TEHN
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("Delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        //GIVEN
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();
        //WHEN
        articleRepository.delete(article);
        //TEHN
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);

    }

}
