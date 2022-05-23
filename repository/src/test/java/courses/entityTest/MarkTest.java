package courses.entityTest;

import courses.dao.EntityDaoImplMark;
import courses.entity.Mark;
import courses.util.HibernateUtil;
import courses.utilsTest.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static courses.constans.ConstantsMark.*;
import static org.junit.Assert.assertTrue;

public class MarkTest {

    @Test
    public void jpqlMark() {
        Mark mark = Utils.createMark();
        EntityManager entityManager = HibernateUtil.getEntityManager();//Java Persistence API
        entityManager.getTransaction().begin();
        entityManager.persist(mark);
        Mark mark2 = entityManager.find(Mark.class, mark.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertNotNull(mark2);
        Assert.assertNotNull(mark2.getId());
        Assert.assertEquals("Mark not equals", MARK_GRADE, mark.getMark());
    }

    @Test
    public void insertTestMark() {
        Mark mark = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark);
        Assert.assertNotNull(daoMark.getEntity(MARK_INSERT_ID));
    }

    @Test
    public void deleteTestMark()
            throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        Mark mark = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark);
        daoMark.delete(mark);
        Assert.assertNotNull(mark);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void deleteIdTestMark() {
        Mark mark = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark);
        daoMark.deleteById(MARK_DELETE_BYID);
        Assert.assertNotNull(mark);
        System.out.println("Attention. There are dependent tables!");
    }

    @Test
    public void updateTestMark() {
        Mark mark = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark);
        mark.setMark(MARK_SET);
        Assert.assertNotEquals(mark.toString(),
                daoMark.getEntity(MARK_UPDATE_ID).toString());
        daoMark.update(mark);
        Assert.assertEquals(mark.toString(),
                daoMark.getEntity(MARK_UPDATE_ID).toString());
    }

    @Test
    public void getEntityTestMark() {
        Mark mark = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark);
        Assert.assertNotNull(daoMark.getEntity(MARK_GET_ID).toString());
    }

    @Test
    public void selectTestMark() {
        Mark mark = Utils.createMark();
        EntityDaoImplMark daoMark = new EntityDaoImplMark();
        daoMark.insert(mark);
        List marks = daoMark.select();
        Assert.assertEquals(marks.toString(),
                daoMark.select().toString());
    }
}
