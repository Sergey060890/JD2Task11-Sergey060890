package courses.entityTest;

import courses.dao.EntityDaoImplCourse;
import courses.entity.Course;
import courses.util.HibernateUtil;
import courses.utilsTest.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static courses.constans.ConstantsCourse.*;
import static org.junit.Assert.assertTrue;

public class CourseTest {

    @Test
    public void jpqlCourse() {
        Course course = Utils.createCourse();
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        Course course2 = entityManager.find(Course.class, course.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(course2);
        Assert.assertNotNull(course2.getId());
        Assert.assertEquals("Course description not equals",
                COURSE_DESCRIPTION, course.getDescription());
    }

    @Test
    public void insertTestCourse() {
        Course course = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course);
        Assert.assertNotNull(daoCourse.getEntity(COURSE_INSERT_ID));
    }

    @Test
    public void deleteTestCourse()
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        Course course = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course);
        daoCourse.delete(course);
    }

    @Test
    public void deleteIdTestCourse() {
        Course course = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course);
        daoCourse.deleteById(COURSE_DELETE_BYID);
        Assert.assertNull(daoCourse.getEntity(COURSE_DELETE_BYID));
    }

    @Test
    public void updateTestCourse() {
        Course course = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course);
        course.setHours(COURSE_SET);
        Assert.assertNotEquals(course.toString(),
                daoCourse.getEntity(COURSE_UPDATE_ID).toString());
        daoCourse.update(course);
        Assert.assertEquals(course.toString(),
                daoCourse.getEntity(COURSE_UPDATE_ID).toString());
    }

    @Test
    public void getEntityTestCourse() {
        Course course = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course);
        Assert.assertNotNull(daoCourse.getEntity(COURSE_GET_ID).toString());
    }

    @Test
    public void selectTestCourse() {
        Course course = Utils.createCourse();
        EntityDaoImplCourse daoCourse = new EntityDaoImplCourse();
        daoCourse.insert(course);
        List courses = daoCourse.select();
        Assert.assertEquals(courses.toString(),
                daoCourse.select().toString());
    }
}
