package ayangd.hrmanage;

import java.io.Serializable;

public class Employer implements Serializable {
	private static final long serialVersionUID = -3610573239917669913L;
	
	String name;
	int age;
	
	public Employer(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
