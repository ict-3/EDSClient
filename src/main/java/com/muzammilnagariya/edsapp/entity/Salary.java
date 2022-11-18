/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.muzammilnagariya.edsapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author muzz
 */
@Entity
@Table(name = "salary")
@NamedQueries({
    @NamedQuery(name = "Salary.findAll", query = "SELECT s FROM Salary s"),
    @NamedQuery(name = "Salary.findBySalId", query = "SELECT s FROM Salary s WHERE s.salId = :salId"),
    @NamedQuery(name = "Salary.findByDeptId", query = "SELECT s FROM Salary s WHERE s.empId.deptId.deptId = :deptId"),
    @NamedQuery(name = "Salary.findByAmountAndDeptId", query = "SELECT s FROM Salary s WHERE s.amount >= :amount and s.empId.deptId.deptId = :deptId"),
    @NamedQuery(name = "Salary.findByAgeRange", query = "SELECT s FROM Salary s WHERE s.empId.age > :start and s.empId.age < :end"),
    @NamedQuery(name = "Salary.findByDateRange", query = "SELECT s FROM Salary s WHERE s.empId.joiningDate BETWEEN :fromDate AND :endDate"),
    @NamedQuery(name = "Salary.findByAmount", query = "SELECT s FROM Salary s WHERE s.amount >= :amount")})
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "salId")
    private Integer salId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "empId", referencedColumnName = "empId")
    @ManyToOne(optional = false)
    private Employee empId;

    public Salary() {
    }

    public Salary(Integer salId) {
        this.salId = salId;
    }

    public Salary(Integer salId, int amount) {
        this.salId = salId;
        this.amount = amount;
    }

    public Integer getSalId() {
        return salId;
    }

    public void setSalId(Integer salId) {
        this.salId = salId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salId != null ? salId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salary)) {
            return false;
        }
        Salary other = (Salary) object;
        if ((this.salId == null && other.salId != null) || (this.salId != null && !this.salId.equals(other.salId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muzammilnagariya.edsapp.entity.Salary[ salId=" + salId + " ]";
    }
    
}
