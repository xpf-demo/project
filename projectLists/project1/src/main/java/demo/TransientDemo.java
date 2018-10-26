package demo;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientDemo {
	
	public static void main(String[] args) {
		
		User user = new User();
		user.setName("谢攀峰");
		user.setPassword("123456789");
		user.setAge(20);
		User.setAddress("深圳");
		
		ObjectOutputStream oos;
		try {
			System.out.println("序列化之前：");
			System.out.println(user);
			oos = new ObjectOutputStream(new FileOutputStream("C:/user.txt"));
			oos.writeObject(user);//将对象写入输出流中
			oos.flush();//刷新缓存
			oos.close();//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/user.txt"));
			User user1 = (User)ois.readObject();
			ois.close();
			System.out.println("序列化之后：");
			System.out.println(user1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}


class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	private transient String password;//被transient修饰的变量无法被序列化，可用于敏感信息的保存
	private static String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		User.address = address;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", password=" + password + "]";
	}
	
}

class Dog implements Externalizable{
	
	private transient String name = "哈哈哈哈哈哈哈或";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}
	
}
