package com.example.nowcoder.serializable;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author James
 */
public class SerializableTest {

    @Test
    public void testDataFile() throws IOException, ClassNotFoundException {
        SerialData serialData = new SerialData(1L, "cd","1122");
        String filePath = "testdata";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(serialData);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object object = ois.readObject();
        Assert.assertTrue(object instanceof SerialData);
        SerialData serData = (SerialData) object;
        Assert.assertNull(serData.getPassword());
        Assert.assertEquals(1L, serData.getId().intValue());
        Assert.assertEquals("cd", serData.getUserName());
    }

    /**
     * deep clone
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testDataMemory() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        SerialData serialData = new SerialData(1L, "cd","1122");
        oos.writeObject(serialData);
        ByteArrayInputStream bas = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bas);
        Object object = ois.readObject();
        Assert.assertTrue(object instanceof SerialData);
        SerialData serData = (SerialData) object;
        Assert.assertNull(serData.getPassword());
        Assert.assertEquals(1L, serData.getId().intValue());
        Assert.assertEquals("cd", serData.getUserName());
    }
}
