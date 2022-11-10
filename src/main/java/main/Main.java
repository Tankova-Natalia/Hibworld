package main;

import main.hibworld.City;
import main.hibworld.Country;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        TypedQuery<City> query1 = session.createQuery("from City as c where c.country.name='Turkey'");
        List<City> list1 = query1.getResultList();
        System.out.println(1);
        System.out.println();
        System.out.println("Name\tPopulation\tCountry");
        int i = 1;
        for (Iterator<City> iterator = list1.iterator(); iterator.hasNext();){
            City city = iterator.next();
            System.out.printf("%2d%20s%8d%8s\n",i,city.getName(),city.getPopulation(),city.getCountry().getName());
            i++;
        }

        System.out.println();
        System.out.println(2);
        System.out.println("Name\tPopulation\tCountry");
        TypedQuery<City> query2 = session.createQuery("from City as c where c.country.name='United Kingdom' order by c.population").setMaxResults(10);
        List<City> list2 = query2.getResultList();
        i = 1;
        for (Iterator<City> iterator = list2.iterator() ; iterator.hasNext();){
            City city = iterator.next();
            System.out.printf("%2d%15s%8d%15s\n",i,city.getName(),city.getPopulation(),city.getCountry().getName());
            i++;
        }

        System.out.println();
        System.out.println(3);
        System.out.println("Name\tPopulation\tCountry");
        TypedQuery<City> query3 = session.createQuery("from City as c where c.country.continent='Oceania'");
        List<City> list3 = query3.getResultList();
        i = 1;
        for (Iterator<City> iterator = list3.iterator() ; iterator.hasNext();){
            City city = iterator.next();
            System.out.printf("%2d%15s%8d%15s%10s\n",i,city.getName(),city.getPopulation(),city.getCountry().getName(), city.getCountry().getContinent());
            i++;
        }

        System.out.println();
        System.out.println(4);
        System.out.println("Name\tPopulation\tCountry");
        TypedQuery<Long> query4 = session.createQuery("select sum(population) from City as c where c.country.name='United Kingdom'");
        Long sum = query4.getSingleResult();
        System.out.println("General population size is " + sum);

        System.out.println();
        System.out.println(5);
        System.out.println("Name\tPopulation\tCountry");
        TypedQuery<Integer> query5 = session.createQuery("select country.capital.population from City as c where c.name='Cambridge'");
        List<Integer> countries = query5.getResultList();
        for (Iterator<Integer> iterator = countries.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }

        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
}
/**/

