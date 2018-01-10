import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * Created by cai.tian on 2017/7/6.
 */
public class demo2 {

    public static void main(String [] args){
        Jedis jedis = new Jedis("10.198.197.127");
        String keys = "name";
        // 删数据
        //jedis.del("person");
        // 存数据
        jedis.set(keys, "tc");
        // 取数据
        String value = jedis.get(keys);
        System.out.println(value);

        //存对象
        Person p=new Person();  //peson类记得实现序列化接口 Serializable
        p.setAge("ss");
        p.setName("姚波");
        jedis.set("person".getBytes(), serialize(p));
        byte[] byt=jedis.get("person".getBytes());
        Object obj=unserizlize(byt);
        if(obj instanceof Person){
            System.out.println(obj);
        }
    }

    //序列化
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    static class  Person implements Serializable{
        String age;
        String name;

        public void setAge(String age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {

            return age;
        }

        public String getName() {
            return name;
        }
    }
}
