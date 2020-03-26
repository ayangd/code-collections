package ayangd.hrmanage;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;

public class EmployerTable implements Serializable {
	private static final long serialVersionUID = 8697437649680264043L;
	
	String type;
	ArrayList<Employer> data;
	int nameCol;
	
	public EmployerTable(String type) {
		this.type = type;
		nameCol = 4;
		data = new ArrayList<>();
	}
	
	public EmployerTable add(Employer e) {
		data.add(e);
		if (nameCol < e.name.length())
			nameCol = e.name.length();
		return this;
	}
	
	public EmployerTable add(AbstractList<Employer> es) {
		data.addAll(es);
		return this;
	}
	
	public EmployerTable remove(int index) {
		data.remove(index);
		return this;
	}
	
	public int size() {
		return data.size();
	}
	
	public EmployerTable showTable() {
		int tmp = (8 + nameCol - type.length()) / 2;
		for (int i = 0; i < tmp; i++) {
			System.out.print("=");
		}
		System.out.printf("[ %s%s ]", type, (tmp == 12 + nameCol - tmp - 4 - type.length() - (type.length() % 2 == 1 ? 1 : 0)) ? " " : "");
		for (int i = 0; i < tmp; i++) {
			System.out.print("=");
		}
		System.out.println();
		
		System.out.printf(String.format("Index  %%-%ds  Age\n", nameCol), "Name");
		
		int index = 1;
		for (Employer e: data) {
			System.out.printf(String.format("%%5d  %%-%ds  %%d\n", nameCol), index++, e.name, e.age);
		}
		return this;
	}
}
