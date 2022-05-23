package managment.implementation;

import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Task;
import courses.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

import static managment.implementation.ConstantsForTest.TASK_DESC_1;
import static managment.implementation.ConstantsForTest.TASK_DESC_2;

public class TeacherServiceImplTest extends Assert {

    @Test
    public void jpqlTask() {
        Course course = UtilsForTest.getCourse1();
        Task task = UtilsForTest.getTask();
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(task);
        Assert.assertEquals("Task description not equals",
                TASK_DESC_1, task.getDescription());
    }

//    @Test
//    public void addTaskTest() {
//        EntityDaoImplTeacher daoTeacher
//                = new EntityDaoImplTeacher();
//        daoTeacher.insert(Teacher.builder().name("111").surname("2222").build());
//        TeacherServiceImpl ts = new TeacherServiceImpl(daoTeacher);
//
//        Task task = ts.addTask(TASK_DESC_1);
//        assertEquals(TASK_DESC_1, task.getDescription());
//    }
//
//    @Test
//    public void deleteTaskTest() {
//
//        EntityDaoImplTeacher daoTeacher
//                = new EntityDaoImplTeacher();
//        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(daoTeacher);
//
//        task = teacherServiceImpl.addTask(TASK_DESC_2);
//        teacherServiceImpl.deleteTask(task.getId());
//        assertNull("Task was not delete",
//                teacherServiceImpl.getTask(task.getId()));
//    }
//
//    @Test
//    public void showTaskTest() {
//        EntityDaoImplTeacher daoTeacher
//                = new EntityDaoImplTeacher();
//        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(daoTeacher);
//        Set<Task> setOfTasks = Set.of(getTask(),getTaskForDelTest());
//        teacherServiceImpl.showTask();
//
//    }
//
//    @Test
//    public void showCourseForTeacherTest() {
//        EntityDaoImplTeacher daoTeacher
//                = new EntityDaoImplTeacher();
//        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl(daoTeacher);
//        teacherServiceImpl.showTask();
//    }
//

}