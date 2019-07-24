package it.capgemini.clubOlympia.implementations.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.capgemini.clubOlympia.entities.Client;
import it.capgemini.clubOlympia.entities.Coach;
import it.capgemini.clubOlympia.entities.Sex;
import it.capgemini.clubOlympia.entities.Surface;
import it.capgemini.clubOlympia.entities.TipoSport;
import it.capgemini.clubOlympia.entities.TrainingCamp;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPATrainingCampDAOTest {

	public final static String CLIENT_FIRSTNAME = "CLIENT_FIRSTNAME";
	public final static String CLIENT_LASTNAME = "CLIENT_LASTNAME";
	public final static LocalDate CLIENT_DATEOFBIRTH = LocalDate.now();
	public final static Sex SEX = Sex.UNDECIDED;
	public final static String CLIENT_BIO = "CLIENT_BIO";
	
	public final static String COURT_NAME = "COURT_NAME";
	public final static Surface SURFACE = Surface.HARDCOURT;
	public final static int COURT_NUM_PLAYERS = 4;
	public final static int COURT_COST = 4;


	public final static LocalDateTime START_1 = LocalDateTime.of(2000, 1, 12, 21, 00); 
	public final static LocalDateTime START_2 = LocalDateTime.of(2000, 2, 12, 21, 00); 
	public final static LocalDateTime START_3 = LocalDateTime.of(2000, 3, 12, 21, 00);
	
	public final static LocalDateTime END_1 = LocalDateTime.of(2000, 1, 22, 21, 00); 
	public final static LocalDateTime END_2 = LocalDateTime.of(2000, 2, 22, 21, 00); 
	public final static LocalDateTime END_3 = LocalDateTime.of(2000, 3, 22, 21, 00); 
	

	public final static LocalDateTime START_TEST = LocalDateTime.of(2000, 1, 13, 21, 00); 
	public final static LocalDateTime END_TEST = LocalDateTime.of(2000, 3, 22, 21, 00);
	
	private TrainingCamp t1, t2, t3, t4;
	private Coach coach;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		coach = new Coach(0, CLIENT_FIRSTNAME, CLIENT_LASTNAME, CLIENT_DATEOFBIRTH, SEX,CLIENT_BIO);
		
		testEntityManager.persist(coach);
		
		t1 = new TrainingCamp(0, START_1, END_1, coach, COURT_COST, TipoSport.TENNIS);
		t2 = new TrainingCamp(0, START_2, END_2, coach, COURT_COST, TipoSport.TENNIS);
		t3 = new TrainingCamp(0, START_3, END_3, coach, COURT_COST, TipoSport.TENNIS);		
		t4 = new TrainingCamp(0, START_2, END_2, coach, COURT_COST, TipoSport.CALCIO);

		testEntityManager.persist(t1);
		testEntityManager.persist(t2);
		testEntityManager.persist(t3);
		testEntityManager.persist(t4);
		testEntityManager.flush();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	 @Autowired
	 private EntityManager testEntityManager ;
	 
	 
	@Test
	public void testByTimeRangeAndSport() {
		
		JPATrainingCampDAO campDao = new JPATrainingCampDAO();
		campDao.manager = testEntityManager;
		
		Iterable<TrainingCamp> result = campDao.byTimeRangeAndSport(START_TEST, END_TEST, TipoSport.TENNIS);
		List<TrainingCamp> target = new ArrayList<>();
		result.forEach(target::add);
		
		assertEquals(2, target.size());
		
	}
	
	@Test
	public void testClientEnrollment() {
		
		Client client = new Client(0, CLIENT_FIRSTNAME, CLIENT_LASTNAME, CLIENT_DATEOFBIRTH, SEX);

		testEntityManager.persist(client);
		
		JPATrainingCampDAO campDao = new JPATrainingCampDAO();
		campDao.manager = testEntityManager;
		
		TrainingCamp tCamp = campDao.findById(t1.getId(), TrainingCamp.class);
		assertEquals(0, tCamp.getClients().size());
		
		tCamp.enroll(client);
		tCamp = campDao.findById(t1.getId(), TrainingCamp.class);
		assertEquals(1, tCamp.getClients().size());
		
	}

}
