package DAY6;

import java.util.Comparator;
import java.util.TreeMap;

public class Code01_Comparator {
	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}
	// 任何比较器：
	// compare方法里，遵循一个统一的规范：
	// 返回负数的时候，认为第一个参数应该排在前面
	// 返回正数的时候，认为第二个参数应该排在前面
	// 返回0的时候，认为无所谓谁放前面

	//升序
	public static class IdAscendingComparator implements Comparator<Student> {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.id - o2.id;
		}
	}
	//降序
	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}
	}
	public static class IdAscendingAgeDescendingOrder implements Comparator<Student> {
		// 根据id从小到大，但是如果id一样，按照年龄从大到小
		@Override
		public int compare(Student o1, Student o2) {
			return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
		}
	}

public static void main(String[] args){

	// 假如比较值相等，treemap认为对象一样，只返回一个
	// 传入IdAscendingComparator 可得
	TreeMap<Student, String> treeMap = new TreeMap<>(new IdDescendingComparator());
//	TreeMap<Student, String> treeMap2 = new TreeMap<>((a, b) -> a.id != b.id ? (a.id - b.id): (a.hashCode()) - b.hashCode());
	Student student1 = new Student("A", 4, 40);
	Student student2 = new Student("B", 2, 21);
	Student student3 = new Student("C", 6, 12);
	Student student4 = new Student("D", 8, 62);
	Student student5 = new Student("E", 0, 42);
	treeMap.put(student1, "我是学生1，我的名字叫A");
	treeMap.put(student2, "我是学生2，我的名字叫B");
	treeMap.put(student3, "我是学生3，我的名字叫C");
	treeMap.put(student4, "我是学生4，我的名字叫D");
	treeMap.put(student5, "我是学生5，我的名字叫E");
	for (Student s : treeMap.keySet()) {
		System.out.println(s.name + "," + s.id + "," + s.age);
	}
}
}

