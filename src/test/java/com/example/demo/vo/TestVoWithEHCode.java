package com.example.demo.vo;

/**
 * @author chengdu
 * @date 2019/8/3.
 */
public class TestVoWithEHCode extends Object {

    private Integer id;

    private String url;

    private String name;

    public TestVoWithEHCode(Integer id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestVoWithEHCode)) return false;

        TestVoWithEHCode that = (TestVoWithEHCode) o;

        if (!id.equals(that.id)) return false;
        if (!url.equals(that.url)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
