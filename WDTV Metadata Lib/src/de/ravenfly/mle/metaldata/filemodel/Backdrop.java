package de.ravenfly.mle.metaldata.filemodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "backdrop")
public class Backdrop {

	@XmlValue
	private String backdrop;

	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backdrop == null) ? 0 : backdrop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Backdrop other = (Backdrop) obj;
		if (backdrop == null) {
			if (other.backdrop != null)
				return false;
		} else if (!backdrop.equals(other.backdrop))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Backdrop [backdrop=" + backdrop + "]";
	}
}
