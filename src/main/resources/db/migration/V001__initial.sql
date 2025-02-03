CREATE SCHEMA IF NOT EXISTS pds;

CREATE TABLE IF NOT EXISTS pds.patients (
    patient_id UUID NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (patient_id)
);

CREATE TABLE pds.patient_data (
    patient_data_id UUID NOT NULL,
    patient_id UUID NOT NULL,
    blood_pressure VARCHAR(20) NOT NULL,
    heart_rate INT NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    PRIMARY KEY (patient_data_id)
);
