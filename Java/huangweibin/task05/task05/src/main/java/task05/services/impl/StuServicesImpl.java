package task05.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task05.dao.StuDao;
import task05.pojo.Students;
import task05.services.StuServices;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class StuServicesImpl implements StuServices {
    @Autowired
    private StuDao stuDao;

    @Override
    public int querySum() {
        return stuDao.querySum();
    }

    @Override
    public int queryWorkSum() {
        return stuDao.queryWorkSum();
    }

    @Override
    public List<Students> queryFrontList() {
        return stuDao.queryFrontList();
    }
}
