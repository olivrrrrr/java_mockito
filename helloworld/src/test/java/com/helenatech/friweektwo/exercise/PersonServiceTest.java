package com.helenatech.friweektwo.exercise;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class PersonServiceTest {

    private PersonDAO personDAO;
    private PersonService underTest;

    @BeforeEach
    void setUp() {
        // TODO: create a mock for personDAO
        personDAO = mock(PersonDAO.class);
        // TODO: create an instance of underTest and pass personDAO into it
        underTest = new PersonService(personDAO);
    }

    /*
       TODO: Test all these methods.
        You might need to create additional methods. Check test coverage
    */

//    Good luck :)

    @Test
    void itCanSavePerson() {
        // given
        Person Oliver = new Person(1, "Oliver", 23);
        when(personDAO.savePerson(Oliver)).thenReturn(1);
        // when
        int result = underTest.savePerson(Oliver);
        // then
        assertThat(result).isEqualTo(1);

        // Captor

        ArgumentCaptor<Person> personArgumentCaptor =
                ArgumentCaptor.forClass(Person.class);

        verify(personDAO)
                .savePerson(personArgumentCaptor.capture());

       Person capturedPerson = personArgumentCaptor.getValue();

       assertThat(capturedPerson).isEqualTo(Oliver);
    }

    @Test
    void itCanDeletePerson() {
        // given
        Person Oliver = new Person(2, "Oliver", 23 );
        List<Person> people = List.of(Oliver);
        // when
        when(personDAO.getPeople()).thenReturn(people);
        when(personDAO.deletePerson(2)).thenReturn(1);
        // then
        int result = underTest.deletePerson(2);
        assertThat(result).isEqualTo(1);

        // Captor
        ArgumentCaptor<Integer> idArgumentCaptor =
                ArgumentCaptor.forClass(Integer.class);

        verify(personDAO)
                .deletePerson(idArgumentCaptor.capture());

        Integer capturedInt = idArgumentCaptor.getValue();

        assertThat(capturedInt).isEqualTo(2);

    }

    @Test
    void canGetPeopleFromDB() {
        // given
        Person Oliver = new Person(2, "Oliver", 23 );
        List<Person> people = List.of(Oliver);
        // when
        when(personDAO.getPeople()).thenReturn(people);
        // then
        assertThat(underTest.getPeople()).isEqualTo(people);
    }

    @Test
    void canGetPersonById() {
        // given
        Person Oliver = new Person(2, "Oliver", 23);
        List<Person> people = List.of(Oliver);
        // We need to ONLY mock PERSONDAO dependency
        when(personDAO.getPeople()).thenReturn(people);
        // when
        Optional<Person> actual = underTest.getPersonById(2);
        // then
        assertThat(actual).isEqualTo(Optional.of(Oliver));
    }




}