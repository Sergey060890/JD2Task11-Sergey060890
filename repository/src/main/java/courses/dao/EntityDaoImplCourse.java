package courses.dao;

import courses.entity.Course;
import courses.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImplCourse extends EntityDaoImpl<Course> {
    private static EntityManager em;

    public EntityDaoImplCourse() {
        super(Course.class);
    }



    public List<Object[]> listOfAllCourse() {
        em = HibernateUtil.getEntityManager();
        Query query = em.createQuery(
                "SELECT c.id, c.description, c.hours, " +
                        "t.surname, t.name " +
                        "FROM courses.entity.Course c " +
                        "left join c.teachers t");
        List<Object[]> list = query.getResultList();
        em.close();
        return list;
    }
}
