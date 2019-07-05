package io.jenkins.plugins.rolestrategy;

import hudson.security.Permission;

/**
 * A wrapper for efficient serialization of a {@link Permission}
 * when stored as a part of an {@link AbstractRole}.
 */
public final class PermissionWrapper {
    // should've been final but needs to be setup when the
    // object is deserialized from the XML config
    private transient Permission permission;
    private final String id;

    /**
     * Constructor.
     *
     * @param id the id of the permission this {@link PermissionWrapper} contains.
     */
    public PermissionWrapper(String id) {
        this.id = id;
        permission = Permission.fromId(id);
    }

    /**
     * Used to setup the permission when deserialized
     *
     * @return the {@link PermissionWrapper}
     */
    @SuppressWarnings("unused")
    private Object readResolve() {
        permission = Permission.fromId(id);
        return this;
    }

    /**
     * Get the permission corresponding to this {@link PermissionWrapper}
     *
     * @return the permission corresponding to this {@link PermissionWrapper}
     */
    public Permission getPermission() {
        return permission;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionWrapper that = (PermissionWrapper) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
