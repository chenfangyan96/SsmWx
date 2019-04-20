package Wx.Service.impl;

        import Wx.Service.ArticleService;
        import Wx.dao.ArticleDao;
        import Wx.model.Article;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public List<Article> findUserList(){
        return articleDao.findAllArticle();
    }

}
