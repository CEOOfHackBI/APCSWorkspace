import java.util.LinkedList;
import java.util.Queue;

import Problems2.Student;


public class Problems2 {

	/**
	 * @param args
	 */
	
	
	
	
	//Problem 14
	public class Student
	{
		private String name;
		private double GPA;
		
		public Queue<Student> cutAtGPA(Queue<Student> students, double minGPA)
		{
			Queue<Student> honors = new LinkedList<Student>();
			
			while(!students.isEmpty())
			{
				if(students.peek().GPA>=minGPA)
					honors.add(students.remove());
				else
					students.remove();
			}
			
			
			return honors;
		}
	}
	
	
	//Problem 16
	
	public static <E> Queue<E> copy(Queue<E> q)
	{
		Queue<E> copy=new LinkedList<E>();
		
		if(q.isEmpty())
			return copy;
		
		
		for(int i=0;i<q.size();i++)
		{
			copy.add(q.peek());
			q.add(q.remove());
		}
		
		
		return copy;
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}