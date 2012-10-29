package com.ambiesense.persistent;

import java.util.Observable;

public abstract class AbstractPersistentObject extends Observable
implements PersistentObject {

	private String id = UUIDGenerator.createId();

	/**
	 * Returns the unique AmbieSense id of this object, whether it has been stored or not in a database.
	 */

	public String getId() {
		return id;
	}
	/**
	 * Sets the unique AmbieSense identification for this object.
	 * Warning: It should only be set by the id of the object read from the database.
	 */

	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null ||
				!(o instanceof PersistentObject)) {

			return false;
		}
		PersistentObject other
		= (PersistentObject)o;
		// if the id is missing, return false
		if (id == null) return false;
		// equivalence by id
		return id.equals(other.getId());
	}

	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		} else {
			return super.hashCode();
		}
	}

	public String toString() {
		return this.getClass().getName()
		+ "[id=" + id + "]";
	}
}