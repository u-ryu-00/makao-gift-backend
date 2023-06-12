package kr.megaptera.makaogift.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserId {
    @Column(name = "user_id")
    private String value;

    public UserId() {
    }

    public UserId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "UserId(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == UserId.class &&
                this.value.equals(((UserId) other).value);
    }
}
