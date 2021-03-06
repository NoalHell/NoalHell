package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    /**
     * Jpa的实体类管理工具：相当于Hirbernate的SessionFactory
     */
    private static EntityManagerFactory em;

    //静态代码块赋值
    static {
        //注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
        em = Persistence.createEntityManagerFactory("myJpa");
    }
    /**
     * 使用管理器工厂生产一个管理器对象
     * @return   管理器对象
     */
    public static EntityManager getEntityManager() {
        EntityManager entityManager = em.createEntityManager();
        return entityManager;
    }
}
