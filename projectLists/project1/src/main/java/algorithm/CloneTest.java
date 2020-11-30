package algorithm;

public class CloneTest {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Person p1 = new Person("zhangsan", 18, new Address("中国", "深圳"));
		Person p2 = (Person)p1.clone();
		System.out.println(p1 == p2);
		System.out.println(p1.getAddress() == p2.getAddress());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("-----------修改---------------");
		p1.setAddress(new Address("中国", "广州"));
		System.out.println(p1);
		System.out.println(p2);
	}
}


class Person implements Cloneable{
	
	private String name;
	private int age;
	private Address address;
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Person p = (Person)super.clone();
		p.address = (Address)address.clone();
		return p;
	}

	public Person() {
		super();
	}

	public Person(String name, int age, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
}


class Address implements Cloneable{
	
	private String contry;
	private String shenfen;
	
	
	
	public Address() {
		super();
	}

	public Address(String contry, String shenfen) {
		super();
		this.contry = contry;
		this.shenfen = shenfen;
	}
	
	public String getContry() {
		return contry;
	}
	public void setContry(String contry) {
		this.contry = contry;
	}
	public String getShenfen() {
		return shenfen;
	}
	public void setShenfen(String shenfen) {
		this.shenfen = shenfen;
	}

	@Override
	public String toString() {
		return "Address [contry=" + contry + ", shenfen=" + shenfen + "]";
	}
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
