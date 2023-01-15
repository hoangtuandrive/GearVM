package com.gearvmstore.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailPK implements Serializable {
	private static final long serialVersionUID = 3300237691902448633L;
	private Long orderId;
	private Long productId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDetailPK that = (OrderDetailPK) o;
		return orderId == that.orderId && productId == that.productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	public OrderDetailPK() {
	}
}
