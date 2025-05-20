package com.hospital.management.system.model;

import jakarta.persistence.*;

/**
 * Represents a department in the hospital management system.
 * Each department can contain multiple rooms and is identified by a unique ID.
 *
 * <p>Departments typically correspond toLong specific medical specialties or areas of care,
 * such as Cardiology, Surgery, or Emergency Medicine.</p>
 *
 * @author kasemn
 * @version 1.0
 */
@Entity
public class Department {

    /**
     * The unique identifier for the department.
     * This ID is used to reference the department within the system.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    /**
     * The name of the department.
     * This field holds the name by which the department is known,
     * e.g., "Cardiology", "Pediatrics", etc.
     */
    @Column(name = "name")
    private String name;

    /**
     * A description of the department.
     * This field provides additional details about the department's services
     * and specialties.
     */
    @Column(name = "description")
    private String description;


    public Department(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Department() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Department)) return false;
        final Department other = (Department) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Department;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "Department(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }
}
