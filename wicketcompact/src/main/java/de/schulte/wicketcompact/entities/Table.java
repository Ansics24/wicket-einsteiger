package de.schulte.wicketcompact.entities;

import javax.validation.constraints.NotNull;

public class Table extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private Integer seatCount;

    private Boolean orderableElectronically;

    public Table(String name, Integer seatCount) {
        this.name = name;
        this.seatCount = seatCount;
        this.orderableElectronically = Boolean.TRUE;
    }

    public Table() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getOrderableElectronically() {
        return orderableElectronically;
    }

    public void setOrderableElectronically(Boolean orderableElectronically) {
        this.orderableElectronically = orderableElectronically;
    }
}
