package com.vaannila.student;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vaannila.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Address address1 = new Address("OMR Road", "Chennai", "TN", "600097");
			Address address2 = new Address("Ring Road", "Banglore", "Karnataka", "560000");
			Student student1 = new Student("Eswar11", address1);
			Student student2 = new Student("Joe11", address2);
			session.save(student1);
			session.save(student2);
			
			/*List<Address> list = session.createCriteria(Address.class).list();
			
			for(int i=0;i<list.size();i++){
				Address address = (Address) list.get(i);
				System.out.println(address.getAddressId()+" : "+address.getCity());
			}*/
			
			List<Student> list = session.createCriteria(Student.class).list();
			System.out.println(list);		
			
			for(int i=0;i<list.size();i++){
				Student student = (Student) list.get(i);
				System.out.println(student.getStudentId()+" : "+student.getStudentAddress().getStreet());
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}