package epam.domain;

/**
 * Expected fields:
 * mission name {@link String} - required
 * start date {@link java.time.LocalDate} - required
 * end date {@link java.time.LocalDate} - required
 * distance {@link Long} - mission distance - required
 * assignedSpaceShift {@link Spaceship}
 * assignedCrew {@link java.util.List<CrewMember>} - list of mission members based on ship capacity
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
}
