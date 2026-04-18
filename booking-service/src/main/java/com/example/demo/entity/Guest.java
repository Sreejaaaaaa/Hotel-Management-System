package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Gender is required")
    private String gender;

    public Guest() {}

	public int getId() {return id;	}
	public void setId(int id) {this.id = id;	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;	}

	public String getGender() {return gender;	}
	public void setGender(String gender) {this.gender = gender;	}
    
    
}