/*=================================================================
AmbieSense Context Software GPL Source Code.

Copyright (C) 2005 and onwards by AmbieSense Limited, 
 and/or its subsidiaries and co-founders. AmbieSense Limited
 is an R&D-focused company incorporated in Scotland.

This file is part of the AmbieSense Context Software GPL Source Code.

The AmbieSense Context Software GPL Source Code is free software: 
you can redistribute it and/or modify it under the terms of the 
GNU General Public License Version 3 as published by
the Free Software Foundation.

The AmbieSense Context Software Source Code is distributed in 
the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or 
FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with The AmbieSense Context Software Source Code. 
If not, see <http://www.gnu.org/licenses/>.

In addition, the The AmbieSense Context Software Source Code is also 
subject to certain additional terms. You should have received a copy 
of these additional terms immediately following the terms and 
conditions of the GNU General Public License which 
accompanied the The AmbieSense Context Software Source Code. 
If not, please request a copy in writing from id Software at the address below.

If you have questions concerning this license or the applicable 
additional terms, you may contact in writing: 
AmbieSense Limited, 7 Queens Gardens, Aberdeen, 
AB25 4YD, Scotland; or by email: 
kontactATambiesense.com (replace "AT" with "@").
============================================================ */

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