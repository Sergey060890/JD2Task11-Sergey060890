package courses.entityTest;

import courses.dao.EntityDaoImplTask;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Task;
import courses.util.HibernateUtil;
import courses.utilsTest.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static courses.constans.ConstantsTask.*;
import static org.junit.Assert.assertTrue;

public class TaskTest {

    @Test
    public void jpqlTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(mark);
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(task);
        Assert.assertEquals("Task description not equals",
                TASK_DESCRIPTION, task.getDescription());
    }

    @Test
    public void insertTestTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityDaoImplTask daoTask = new EntityDaoImplTask();
        daoTask.insert(course);
        daoTask.insert(mark);
        daoTask.insert(task);
        Assert.assertNotNull(daoTask.getEntity(TASK_INSERT_ID));
    }

    @Test
    public void deleteTestTask()
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityDaoImplTask daoTask = new EntityDaoImplTask();
        daoTask.insert(mark);
        daoTask.insert(task);
        daoTask.delete(task);
        Assert.assertNotNull(task);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void deleteIdTestTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityDaoImplTask daoTask = new EntityDaoImplTask();
        daoTask.insert(course);
        daoTask.insert(mark);
        daoTask.insert(task);
        daoTask.deleteById(TASK_DELETE_BYID);
        Assert.assertNotNull(task);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void updateTestTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityDaoImplTask daoTask = new EntityDaoImplTask();
        task.setDescription(TASK_SET_DESCRIPTION);
        daoTask.insert(course);
        daoTask.insert(mark);
        daoTask.insert(task);
        daoTask.update(task);
        Assert.assertEquals(task.getDescription(),
                TASK_SET_DESCRIPTION);
    }

    @Test
    public void getEntityTestTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityDaoImplTask daoTask = new EntityDaoImplTask();
        daoTask.insert(course);
        daoTask.insert(mark);
        daoTask.insert(task);
        Assert.assertNotNull(daoTask.getEntity(TASK_GET_ID).toString());
    }

    @Test
    public void selectTestTask() {
        Course course = Utils.createCourse();
        Mark mark = Utils.createMark();
        Task task = Utils.createTask(mark, course);
        EntityDaoImplTask daoTask = new EntityDaoImplTask();
        daoTask.insert(course);
        daoTask.insert(mark);
        daoTask.insert(task);
        List tasks = daoTask.select();
        Assert.assertEquals(tasks.toString(), daoTask.select().toString());
    }
}
