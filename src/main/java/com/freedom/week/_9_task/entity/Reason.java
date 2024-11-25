package com.freedom.week._9_task.entity;

import jakarta.persistence.*;
import lombok.Getter;

import javax.annotation.processing.Generated;

@Entity
@Table (name = "reason")
@Getter
public class Reason extends  BaseClass {


    private String reason;
}
