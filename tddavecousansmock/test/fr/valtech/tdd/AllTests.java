package fr.valtech.tdd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.valtech.tdd.dao.ChambreDaoImplTest;
import fr.valtech.tdd.dao.ReservationDaoImplTest;

@RunWith(Suite.class)
@SuiteClasses(value = {
// les tests de DAO
		ChambreDaoImplTest.class,//
		ReservationDaoImplTest.class, })
public class AllTests {
}
