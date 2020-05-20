package com.example.performance.code3.reference.model;

/**
 * @author James
 */
public class TestBean {
    private Long id;

    private String userName;

    public TestBean() {
    }

    public TestBean(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    @Override
    public void finalize() throws Throwable {
        int hashcode = hashCode();
        System.out.println("finalize........."+ userName +" "+ hashcode);
        super.finalize();
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
