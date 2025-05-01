package com.example.api_medicale.tools;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrescritId implements Serializable {

    private Long consultationId;
    private Long medicamentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrescritId)) return false;
        PrescritId that = (PrescritId) o;
        return Objects.equals(consultationId, that.consultationId) &&
                Objects.equals(medicamentId, that.medicamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultationId, medicamentId);
    }
}
