package courses.dao;

import courses.entity.Teacher;
import courses.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImplTeacher extends EntityDaoImpl<Teacher> {

    private static EntityManager em;

    public EntityDaoImplTeacher() {
        super(Teacher.class);
    }

    public void enrollTeacher(Teacher teacher) {
        em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(teacher);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public List<Object[]> findCoursesForTeacher(String teacherSurname) {
        em = HibernateUtil.getEntityManager();
        Query query = em.createQuery(
                "SELECT c.id, c.description, c.hours, t.surname, t.name " +
                        "FROM courses.entity.Course c " +
                        "LEFT JOIN c.teachers t " +
                        "WHERE t.surname =:teacherSurname");
        query.setParameter("teacherSurname", teacherSurname);
        List<Object[]> list = query.getResultList();
        em.close();
        return list;
    }
}
