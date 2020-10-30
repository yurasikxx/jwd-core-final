package epam.domain;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        // todo - via java.lang.enum methods!
        return null;
    }


    public static Role resolveRoleById(int id) {
        // todo
        return null;
    }
}
