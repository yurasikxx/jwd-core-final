package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private final Role role;
    private final Rank rank;

    protected CrewMemberCriteria(final CrewMemberCriteriaBuilder builder) {
        super();
        this.role = builder.role;
        this.rank = builder.rank;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }


    public static class CrewMemberCriteriaBuilder extends Criteria.Builder<CrewMemberCriteria> {
        private Role role;
        private Rank rank;

        public CrewMemberCriteriaBuilder byRole(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder byRank(Rank rank) {
            this.rank = rank;
            return this;
        }

        @Override
        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this);
        }
    }

}