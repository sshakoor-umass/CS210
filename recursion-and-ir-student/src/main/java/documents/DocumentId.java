// ============================================================
// DO NOT EDIT. This file is NOT part of your submission and is
// replaced with the original when your work is graded. Changes
// here may cause the autograder to fail!
// ============================================================

/*
 * Copyright 2021 Marc Liberatore.
 */

package documents;

public class DocumentId {
	@Override
	public String toString() {
		return "DocumentId=" + id;
	}

	public final String id;

	public DocumentId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DocumentId other = (DocumentId) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		return true;
	}
}
