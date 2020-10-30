package epam.domain;

/**
 * Expected fields:
 * id {@link Long} - entity id - required
 * name {@link String} - entity name - required
 */
public abstract class AbstractBaseEntity implements BaseEntity {

    @Override
    public Long getId() {
        // todo
        return null;
    }

    @Override
    public String getName() {
        // todo
        return null;
    }
}
