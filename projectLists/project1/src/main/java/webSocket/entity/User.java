package webSocket.entity;

public class User {

	private Long id;

	private String name;

	private String password;
	
	private String roomsName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoomsName() {
		return roomsName;
	}

	public void setRoomsName(String roomsName) {
		this.roomsName = roomsName;
	}
	
	

}
