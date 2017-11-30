package com.unq.integrador.test.user;

import com.unq.integrador.publication.Property;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.reservation.Reservation;
import com.unq.integrador.score.GlobalScore;
import com.unq.integrador.score.category.OccupantScoreCategory;
import com.unq.integrador.score.category.OwnerScoreCategory;
import com.unq.integrador.score.category.value.OccupantScoreValue;
import com.unq.integrador.score.category.value.OwnerScoreValue;
import com.unq.integrador.score.reviewer.OccupantScore;
import com.unq.integrador.score.reviewer.OwnerScore;
import com.unq.integrador.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Mock(name = "publications")
    private Set<Publication> publications;
    @Mock(name = "reservations")
    private List<Reservation> reservations;
    @Spy
    private GlobalScore ownerScore = new GlobalScore();
    @Spy
    private GlobalScore occupantScore = new GlobalScore();
    private String name;
    private String lastName;
    private String email;
    private String phone;
    @InjectMocks
    private User user;
    private Publication publication;
    private Reservation reservation1, reservation2, reservation3;
    private String city1, city2, city3;
    private Publication publication1, publication2, publication3;
    private Property property1, property2, property3;
    private OwnerScoreCategory ownerScoreCategory1, ownerScoreategory2;
    private OccupantScoreCategory occupantScoreCategory1, occupantScoreCategory2;
    @Before
    public void setUp() {
        name = "John";
        lastName = "Doe";
        email = "jd@somedomain.com";
        phone = "1234-5678";
        publication = mock(Publication.class);
        reservation1 = mock(Reservation.class);
        reservation2 = mock(Reservation.class);
        reservation3 = mock(Reservation.class);
        publication1 = mock(Publication.class);
        publication2 = mock(Publication.class);
        publication3 = mock(Publication.class);
        property1 = mock(Property.class);
        property2 = mock(Property.class);
        property3 = mock(Property.class);
        city1 = "Bernal";
        city2 = "Quilmes";
        city3 = "Don Bosco";
        ownerScoreCategory1 = getOwnerScoreCategoryMock("Category 1");
        ownerScoreategory2 = getOwnerScoreCategoryMock("Category 2");
        occupantScoreCategory1 = getOccupantScoreCategoryMock("Category 1");
        occupantScoreCategory2 = getOccupantScoreCategoryMock("Category 2");
        user = new User(name, lastName, email, phone);
        reservations = mock(List.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPublication() {
        user.addPublication(publication);
        verify(publications).add(publication);
    }

    @Test
    public void testRemovePublication() {
        user.removePublication(publication);
        verify(publications).remove(publication);
    }

    @Test
    public void getName() {
        assertEquals(name, user.getName());
    }

    @Test
    public void getLastName() {
        assertEquals(lastName, user.getLastName());
    }

    @Test
    public void getEmail() {
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getPhone() {
        assertEquals(phone, user.getPhone());
    }

    @Test
    public void testGetFutureReservations() {
        LocalDate now = LocalDate.now();
        when(reservation1.getStartDate()).thenReturn(now.plusDays(2));
        when(reservation2.getStartDate()).thenReturn(now.plusDays(3));
        when(reservation3.getStartDate()).thenReturn(now.minusDays(2));
        when(reservations.stream()).thenReturn(Arrays.asList(new Reservation[]{reservation1, reservation2, reservation3}).stream());
        List<Reservation> reservations = user.getFutureReservations();
        assertTrue(reservations.contains(reservation1));
        assertTrue(reservations.contains(reservation2));
        assertFalse(reservations.contains(reservation3));
    }

    @Test
    public void testGetReservationCities() {
        prepareReservationsWithPublications();
        Set<String> cities = user.getReservationsCities();
        assertEquals(2, cities.size());
        assertTrue(cities.contains(city1));
        assertTrue(cities.contains(city2));
        assertFalse(cities.contains(city3));
    }

    @Test
    public void testOwnerScore() {
        //Configurar dos objetos OwnerScore, donde cada uno tiene una calificación para las categorías ownerScoreCategory1 y ownerScoreategory2
        OwnerScore ownerScore1 = new OwnerScore(mock(User.class));
        ownerScore1.addScoreValue(new OwnerScoreValue(ownerScoreCategory1, 1));
        ownerScore1.addScoreValue(new OwnerScoreValue(ownerScoreategory2, 3));
        OwnerScore ownerScore2 = new OwnerScore(mock(User.class));
        ownerScore2.addScoreValue(new OwnerScoreValue(ownerScoreCategory1, 1));
        ownerScore2.addScoreValue(new OwnerScoreValue(ownerScoreategory2, 1));

        //Configurar dos publicaciones, cada una con una reserva con un owner score de los anteriores
        Publication publication1 = getPublicationMock(new Reservation[]{
                getReservationWithOwnerScoreMock(ownerScore1)
        });
        Publication publication2 = getPublicationMock(new Reservation[]{
                getReservationWithOwnerScoreMock(ownerScore2)
        });
        //Mockear el valor devuelto por el mensaje stream() de la coleccioón de publicaciones
        when(publications.stream()).thenReturn(Arrays.asList(new Publication[]{publication1, publication2}).stream());
        //Pedir el score como dueño
        GlobalScore score = user.getScoreAsOwner();
        verify(ownerScore).clear();
        //Verificar que el global score tenga dos score values
        assertEquals(2, score.getScoreValues().size());
        //Verificamos los valores globales calculados
        score.getScoreValues().forEach(scoreValue -> {
            assertTrue(scoreValue.getCategory().equals(ownerScoreCategory1) || scoreValue.getCategory().equals(ownerScoreategory2));
            if (scoreValue.getCategory().equals(ownerScoreCategory1)) {
                assertEquals(new Integer(1), new Integer(scoreValue.getValue()));
            } else {
                assertEquals(new Integer(2), new Integer(scoreValue.getValue()));
            }
        });
    }

    @Test
    public void testOccupantScore() {
        //Configurar dos objetos Occupantscore, donde cada uno tiene una calificación para las categorías ownerScoreCategory1 y ownerScoreategory2
        OccupantScore occupantScore1 = new OccupantScore(mock(User.class));
        occupantScore1.addScoreValue(new OccupantScoreValue(occupantScoreCategory1, 3));
        occupantScore1.addScoreValue(new OccupantScoreValue(occupantScoreCategory2, 3));
        OccupantScore occupantScore2 = new OccupantScore(mock(User.class));
        occupantScore2.addScoreValue(new OccupantScoreValue(occupantScoreCategory1, 5));
        occupantScore2.addScoreValue(new OccupantScoreValue(occupantScoreCategory2, 5));
        reservation1 = getReservationWithOccupantScoreMock(occupantScore1);
        reservation2 = getReservationWithOccupantScoreMock(occupantScore2);
        //Mockear el valor devuelto por el mensaje stream() de la colección de publicaciones
        when(reservations.stream()).thenReturn(Arrays.asList(new Reservation[]{reservation1, reservation2}).stream());
        //Pedir el score como dueño
        GlobalScore score = user.getScoreAsOccupant();
        verify(occupantScore).clear();
        //Verificar que el global score tenga dos score values
        assertEquals(2, score.getScoreValues().size());
        //Verificamos los valores globales calculados
        score.getScoreValues().forEach(scoreValue -> {
            assertTrue(scoreValue.getCategory().equals(occupantScoreCategory1) || scoreValue.getCategory().equals(occupantScoreCategory2));
            if (scoreValue.getCategory().equals(occupantScoreCategory1)) {
                assertEquals(new Integer(4), new Integer(scoreValue.getValue()));
            } else {
                assertEquals(new Integer(4), new Integer(scoreValue.getValue()));
            }
        });
    }

    @Test
    public void testGetCityReservations() {
        prepareReservationsWithPublications();
        List<Reservation> reservations = user.getCityReservations(city1);
        assertEquals(reservations.size(), 2);
        assertEquals(reservations.get(0).getPublication().getProperty().getCity(), city1);
        assertEquals(reservations.get(1).getPublication().getProperty().getCity(), city1);
    }

    @Test
    public void testGetRegistrationDate() {
        assertThat(user.getRegistrationDate(), instanceOf(LocalDate.class));
    }

    @Test
    public void testGetConcretedReservationsByPropertyCount() {
        preparePublicationsWithProperties();
        assertEquals(new Long(2), user.getConcretedReservationsAsOwnerCount(property1));
    }

    @Test
    public void testGetConcretedReservationsCount() {
        preparePublicationsWithProperties();
        assertEquals(new Long(3), user.getConcretedReservationsAsOwnerCount());
    }

    @Test
    public void testGetConcretedReservationsProperties() {
        preparePublicationsWithProperties();
        Set<Property> properties = user.getConcretedReservationsPropertiesAsOwner();
        assertTrue(properties.contains(property1));
        assertFalse(properties.contains(property2));
        assertTrue(properties.contains(property3));

    }

    private Publication getPublicationMock(Reservation[] reservations) {
        Publication publication = mock(Publication.class);
        when(publication.getFinalizedReservations()).thenReturn(Arrays.asList(reservations));
        return publication;
    }

    private Reservation getReservationWithOwnerScoreMock(OwnerScore score) {
        Reservation reservation = mock(Reservation.class);
        when(reservation.getOwnerScore()).thenReturn(score);
        return reservation;
    }

    private Reservation getReservationWithOccupantScoreMock(OccupantScore score) {
        Reservation reservation = mock(Reservation.class);
        when(reservation.getOccupantScore()).thenReturn(score);
        when(reservation.isFinalized()).thenReturn(true);
        return reservation;
    }

    private OwnerScoreCategory getOwnerScoreCategoryMock(String name) {
        OwnerScoreCategory category = mock(OwnerScoreCategory.class);
        when(category.getName()).thenReturn(name);
        return category;
    }

    private OccupantScoreCategory getOccupantScoreCategoryMock(String name) {
        OccupantScoreCategory category = mock(OccupantScoreCategory.class);
        when(category.getName()).thenReturn(name);
        return category;
    }

    private void prepareReservationsWithPublications() {
        when(reservation1.getPublication()).thenReturn(publication1);
        when(reservation2.getPublication()).thenReturn(publication2);
        when(reservation3.getPublication()).thenReturn(publication3);
        when(publication1.getProperty()).thenReturn(property1);
        when(publication2.getProperty()).thenReturn(property2);
        when(publication3.getProperty()).thenReturn(property3);
        when(property1.getCity()).thenReturn(city1);
        when(property2.getCity()).thenReturn(city2);
        when(property3.getCity()).thenReturn(city1);
        when(reservations.stream()).thenReturn(Arrays.asList(new Reservation[]{reservation1, reservation2, reservation3}).stream());
    }

    private void preparePublicationsWithProperties() {
        when(publication1.getProperty()).thenReturn(property1);
        when(publication2.getProperty()).thenReturn(property1);
        when(publication3.getProperty()).thenReturn(property3);
        when(publication1.getFinalizedReservations()).thenReturn(Arrays.asList(new Reservation[]{reservation1}));
        when(publication2.getFinalizedReservations()).thenReturn(Arrays.asList(new Reservation[]{reservation2}));
        when(publication3.getFinalizedReservations()).thenReturn(Arrays.asList(new Reservation[]{reservation3}));
        when(reservation1.getPublication()).thenReturn(publication1);
        when(reservation2.getPublication()).thenReturn(publication2);
        when(reservation3.getPublication()).thenReturn(publication3);
        when(publications.stream()).thenReturn(Arrays.asList(new Publication[]{publication1, publication2, publication3}).stream());
    }


}
