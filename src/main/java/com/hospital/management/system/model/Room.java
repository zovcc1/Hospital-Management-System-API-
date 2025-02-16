package com.hospital.management.system.model;
import com.hospital.management.system.enums.RoomType;
import com.hospital.management.system.enums.Status;
import jakarta.persistence.*;

/**
 * Represents a room in the hospital management system.
 * Each room is associated with a specific department and has a type and status.
 *
 * <p>Rooms are essential components of the hospital infrastructure,
 * providing spaces for patient care, examination, and treatment.</p>
 */
@Entity
public class Room {

    /**
     * The unique identifier for the room.
     * This ID is automatically generated and used to reference the room
     * within the system.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    /**
     * The department to which this room belongs.
     * This establishes a many-to-one relationship with the DepartmentService class,
     * indicating that multiple rooms can be associated with a single department.
     */

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;

    /**
     * The current status of the room.
     * This field uses an enumerated type to represent various possible statuses
     * for the room, such as available, occupied, or under maintenance.
     */
    @Enumerated(EnumType.STRING)
    private Status status=Status.VACANT;

    /**
     * The type of the room.
     * This field indicates the specific function of the room (e.g., normal, surgical, ICU)
     * using an enumerated type from the RoomType enum.
     */
    @Enumerated(EnumType.STRING)
    private RoomType type;

    public Room(Integer id, Department department, Status status, RoomType type) {
        this.id = id;
        this.department = department;
        this.status = status;
        this.type = type;
    }

    public Room() {
    }

    public Integer getId() {
        return this.id;
    }

    public Department getDepartment() {
        return this.department;
    }

    public Status getStatus() {
        return this.status;
    }

    public RoomType getType() {
        return this.type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Room)) return false;
        final Room other = (Room) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$department = this.getDepartment();
        final Object other$department = other.getDepartment();
        if (this$department == null ? other$department != null : !this$department.equals(other$department))
            return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Room;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $department = this.getDepartment();
        result = result * PRIME + ($department == null ? 43 : $department.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    public String toString() {
        return "Room(id=" + this.getId() + ", department=" + this.getDepartment() + ", status=" + this.getStatus() + ", type=" + this.getType() + ")";
    }
}
