import com.anvasy.model.Article;
import com.anvasy.model.User;
import com.anvasy.services.ArticleService;
import com.anvasy.services.UserService;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void testGetAllAndSaveArticles() {
        ArticleService articleService = new ArticleService();
        List<Article> articleList = articleService.getAll();
        assertNotNull(articleList);

        Map<String, Object> articles = articleService.getAll(1);
        assertNotNull(articles);

        Article article = new Article();
        article.setTopic("TEST TOPIC");
        article.setContent("TEST CONTENT");
        article.setSummary("TEST SUMMARY");

        assertNotNull(articleService.getSession());
        articleService.save(article);
        articleService.close();

        deleteArticle();
    }

    @Test
    public void testGetAllAndSaveUsers() {
        UserService userService = new UserService();
        List<User> userList = userService.getAll();
        assertNotNull(userList);

        User user = new User();
        user.setUsername("TEST USERNAME");
        user.setPassword("TEST PASSWORD");

        userService.register(user);
        userService.close();

        UserService service = new UserService();
        assertNotNull(service.getSession());
        user = service.get("TEST USERNAME", "TEST PASSWORD");
        user.setName("TEST NAME");
        user.setSurname("TEST SURNAME");
        user.setRole("admin");
        service.update(user);
        deleteUser();
    }

    private void deleteArticle() {
        ArticleService articleService = new ArticleService();
        Query query = articleService.getSession().createQuery("from Article order by id DESC");
        query.setMaxResults(1);
        Article last = (Article) query.uniqueResult();

        Article article = articleService.get(last.getId());
        articleService.delete(article.getId());
    }

    private void deleteUser() {
        UserService userService = new UserService();
        Query query = userService.getSession().createQuery("from User order by id DESC");
        query.setMaxResults(1);
        User last = (User) query.uniqueResult();

        userService.delete(last.getId());
    }
}
