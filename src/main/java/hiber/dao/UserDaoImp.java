package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        Query<?> query = sessionFactory.getCurrentSession().createQuery("from User");
        return (List<User>) query.getResultList();
    }

    @Override
    public User getUserByCar(String carModel, int carSeries) {
        Query<?> query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User AS u INNER JOIN u.userCar AS c WHERE c.model = :carModel AND c.series = :carSeries");
        query.setParameter("carModel", carModel);
        query.setParameter("carSeries", carSeries);
        return (User) query.getSingleResult();
    }

}
