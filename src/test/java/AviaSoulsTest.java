import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;


public class AviaSoulsTest {
    Ticket ticket0 = new Ticket("city1", "city3", 100, 17, 21);
    Ticket ticket1 = new Ticket("city1", "city2", 100, 18, 21);
    Ticket ticket2 = new Ticket("city1", "city2", 20, 11, 20);
    Ticket ticket3 = new Ticket("city1", "city2", 30, 12, 16);
    Ticket ticket4 = new Ticket("city1", "city2", 400, 14, 15);
    Ticket ticket5 = new Ticket("city1", "city2", 50, 19, 23);

    @Test
    public void CompareToPriceNegativeResult() {

        int expected = -1;
        int actual = ticket2.compareTo(ticket1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareToPricePositiveResult() {

        int expected = 1;
        int actual = ticket1.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void CompareToPriceNeutralResult() {

        int expected = 0;
        int actual = ticket0.compareTo(ticket1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void search() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);

        Ticket[] expected = {ticket2, ticket3, ticket5, ticket1, ticket4};
        Ticket[] actual = ticket.search("city1", "city2");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchIfFromNotRelevant() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);
        Ticket ticket6 = new Ticket("city2", "city2", 50, 19, 23);
        ticket.add(ticket6);

        Ticket[] expected = {ticket2, ticket3, ticket5, ticket1, ticket4};
        Ticket[] actual = ticket.search("city1", "city2");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoOneTicket() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);
        Ticket ticket6 = new Ticket("city1", "city3", 50, 19, 23);
        ticket.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = ticket.search("city2", "city3");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchExactlyOneTicket() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);
        Ticket ticket6 = new Ticket("city1", "city3", 50, 19, 23);
        ticket.add(ticket6);

        Ticket[] expected = {ticket6};
        Ticket[] actual = ticket.search("city1", "city3");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchIfToNotRelevant() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);
        Ticket ticket6 = new Ticket("city1", "city3", 50, 19, 23);
        ticket.add(ticket6);

        Ticket[] expected = {ticket2, ticket3, ticket5, ticket1, ticket4};
        Ticket[] actual = ticket.search("city1", "city2");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ComparatorTravelTimeNegativeResult() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = -1;
        int actual = comparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ComparatorTravelTimePositiveResult() {

        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 1;
        int actual = comparator.compare(ticket2, ticket1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ComparatorTravelTimeNeutralResult() {

        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket3);
        ticket.add(ticket5);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;
        int actual = comparator.compare(ticket3, ticket5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchAndSortBy() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket4, ticket1, ticket3, ticket5, ticket2};
        Ticket[] actual = ticket.searchAndSortBy("city1", "city2", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchAndSortByIfFromNotRelevant() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);
        Ticket ticket6 = new Ticket("city2", "city2", 50, 19, 23);
        ticket.add(ticket6);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket4, ticket1, ticket3, ticket5, ticket2};
        Ticket[] actual = ticket.searchAndSortBy("city1", "city2", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchAndSortByIfFromRelevant() {
        AviaSouls ticket = new AviaSouls();
        ticket.add(ticket1);
        ticket.add(ticket2);
        ticket.add(ticket3);
        ticket.add(ticket4);
        ticket.add(ticket5);
        Ticket ticket6 = new Ticket("city1", "city3", 50, 19, 23);
        ticket.add(ticket6);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket4, ticket1, ticket3, ticket5, ticket2};
        Ticket[] actual = ticket.searchAndSortBy("city1", "city2", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

}
