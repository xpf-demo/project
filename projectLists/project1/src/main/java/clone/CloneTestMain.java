package clone;

public class CloneTestMain {
	
	public static void main(String[] args) throws Exception {
		Person person =  new Person(15, "zhangsan", new Address("湖北", "武汉"));
        
        Person clonePerson = (Person) person.deepClone();
        
        System.out.println(person);
        System.out.println(clonePerson);
        
        System.out.println(person.toString());
        System.out.println(clonePerson.toString());
        
        clonePerson.setName("wangwu");
        clonePerson.setAge(20);
        Address address = clonePerson.getAddress();
        address.setStreet("襄阳");
        System.out.println(person.toString());
        System.out.println(clonePerson.toString());
	}

}
