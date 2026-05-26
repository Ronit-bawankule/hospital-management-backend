package com.hospital.hospitalweb;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Matches React payload: "name"
    private String name;

    private int age;

    // ✅ Matches React payload: "disease"
    private String disease;

    private String nurseName;
    private String nurseId;

    private boolean xray;
    private boolean mri;
    private boolean operation;
    private boolean icu;
    private int icuDays;

    // Relationship: Many patients can belong to one doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true) // ✅ allow null if doctor not provided
    private Doctor doctor;

    public Patient() {}

    public Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }

    public String getNurseName() { return nurseName; }
    public void setNurseName(String nurseName) { this.nurseName = nurseName; }

    public String getNurseId() { return nurseId; }
    public void setNurseId(String nurseId) { this.nurseId = nurseId; }

    public boolean isXray() { return xray; }
    public void setXray(boolean xray) { this.xray = xray; }

    public boolean isMri() { return mri; }
    public void setMri(boolean mri) { this.mri = mri; }

    public boolean isOperation() { return operation; }
    public void setOperation(boolean operation) { this.operation = operation; }

    public boolean isIcu() { return icu; }
    public void setIcu(boolean icu) { this.icu = icu; }

    public int getIcuDays() { return icuDays; }
    public void setIcuDays(int icuDays) { this.icuDays = icuDays; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
